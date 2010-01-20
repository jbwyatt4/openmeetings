<?php
//Global variable and constant declarations
$link = null;
define("USER_TIMEOUT", 10);
//user states
define("USER_LOGGED_OFF", 0);
define("USER_LOGGED_IN", 1);

define("OM_ADMIN_LOGIN", 'admin');
define("OM_ADMIN_PASS", 'pass');
define("OM_SERVER_ADDRESS", 'http://127.0.0.1:8080');

//call states
define("CALL_INVALID", 0);
define("CALL_WAITING", 1);
define("CALL_REJECTED", 2);
define("CALL_ANSWERED", 3);

define("USER_LOGGED_OFF_STATE", 'LOGGED_OFF');
define("USER_LOGGED_ON_STATE", 'LOGGED_ON');

//Debug info
define("OK_RESULT",'O');
define("FAIL_RESULT",'F');
define("METHOD_REGISTER",'R');
define("METHOD_DROPRECORDS",'D');
define("METHOD_UPDATESTATE",'U');
define("METHOD_MAKECALL",'M');
define("METHOD_ANSWERCALL",'A');
define("METHOD_REJECTCALL",'J');
define("METHOD_CLEARCALL",'C');
define("METHOD_GETCALLERLINK",'L');
define("METHOD_GETCALLEELINK",'E');

//System functions and utilities
function initDB()
{
	global $link;
	$link = mysql_connect('127.0.0.1', 'root', 'toor');
	if (!$link)
	{
		die('Not connected : ' . mysql_error());
	}
	else
	{
		if ( !mysql_select_db( 'om_server', $link ) )
		{
			die('No database om_server : ' . mysql_error());
		}
		else
		{
			dropTimedOutRecords();
		}
	}
}

function buildMessage($result,$method,$message)
{
	return $result."|".$method."|".$message;
}

function dieWithMessage($method, $message)
{
	die( buildMessage( FAIL_RESULT, $method, $message ) );
}

function showResult($method,$message)
{
	print buildMessage( OK_RESULT, $method, $message );
}

function dropTimedOutRecords()
{
	global $link;
	//We're dropping the records that have been timed out from both calls and logged users tables
	$timeout = time()-USER_TIMEOUT*60;
	$query = 'DELETE FROM logged_users WHERE last_present < '.$timeout;
	//print $query;
	if (! mysql_query($query, $link) )
	{
		dieWithMessage(METHOD_DROPRECORDS, 'Failed to delete records in logged_users : ' . mysql_error());
	}
	
	$query = 'DELETE FROM calls WHERE last_updated < '.$timeout;
	//print $query;
	if (! mysql_query($query, $link) )
	{
		dieWithMessage(METHOD_DROPRECORDS, 'Failed to delete records in calls : ' . mysql_error());
	}
}

function disposeAndClose()
{
	global $link;
	mysql_close($link);
}

//Request Function definitions go here
//Every function must have two arguments max
function registerUser($userId, $displayName, $clientId)
{
	global $link;
	$userHash = mysql_real_escape_string($userId);
	$displayName = mysql_real_escape_string($displayName);
	$clientId = mysql_real_escape_string($clientId);
	$currentTime = time();
	
	$query = "DELETE FROM logged_users WHERE user_hash='".$userHash."'";
	
	//print $query;
	
	if (! mysql_query($query, $link) )
	{
		dieWithMessage(METHOD_REGISTER,'Failed to delete existing records in logged_users : ' . mysql_error());
	}
	
	$query = "INSERT INTO logged_users VALUES ( '".$userHash."',".$currentTime.','.USER_LOGGED_IN.",'".$displayName."','".$clientId."')";
	
	//print $query;
	
	if (! mysql_query($query, $link) )
	{
		dieWithMessage(METHOD_REGISTER,'Failed to insert registered user in logged_users : ' . mysql_error());
	}
	
	showResult( METHOD_REGISTER, $userHash );
}

//Happens when user logs on or off
function updateState($userHash,$state,$clientId)
{
	global $link;
	$userHash = mysql_real_escape_string($userHash);
	$currentTime = time();
	
	switch ($state)
	{
		case USER_LOGGED_OFF_STATE:
		{
			$query = "UPDATE logged_users SET `state` = ".USER_LOGGED_OFF.", `last_present` = ".$currentTime." WHERE `user_hash` = '".$userHash."'";

			//print $query;
			
			if (! mysql_query($query, $link) )
			{
				dieWithMessage(METHOD_UPDATESTATE,'Failed to update registered user state to LOGGED_OFF in logged_users : ' . mysql_error());
			}
			
			//Reject all incoming and delete all outgoing calls
			$query = "UPDATE calls SET `state` = ".CALL_REJECTED.", `last_updated` = ".$currentTime." WHERE `to_id` = '".$userHash."'";
			
			//print $query;
			
			if (! mysql_query($query, $link) )
			{
				dieWithMessage(METHOD_UPDATESTATE,'Failed to update call state to REJECTED in calls : ' . mysql_error());
			}
			
			$query = "DELETE FROM calls WHERE `from_id` = '".$userHash."'";
			
			//print $query;
			
			if (! mysql_query($query, $link) )
			{
				dieWithMessage(METHOD_UPDATESTATE,'Failed to delete outgoing calls : ' . mysql_error());
			}
			
			showResult(METHOD_UPDATESTATE,"U0OK");
			
			break;
		}
		case USER_LOGGED_ON_STATE:		
		{		
			if ( notLoggedInFromAnotherPlace($userHash, $clientId) )
			{
			
				//A place to store the incoming call result from mysql
				$incomingCall = null;
				//A place to store the answered call result from mysql
				$answeredCall = null;
				//A place to store the rejected call result from mysql
				$rejectedCall = null;
			
				$query = "UPDATE logged_users SET `last_present` = ".$currentTime." WHERE `user_hash` = '".$userHash."'";

				//print $query;
			
				if (! mysql_query($query, $link) )
				{
					dieWithMessage(METHOD_UPDATESTATE,'Failed to update registered user state to LOGGED_ON in logged_users : ' . mysql_error());
				}
			
				$incomingCall = returnIncomingCallIfPresent($userHash);
			
				$answeredCall = returnAnsweredCallIfPresent($userHash);
			
				$rejectedCall = returnRejectedCallIfPresent($userHash);
			
				$result = "U1OK";
			
				if ( $incomingCall != null )
				{
					$result = "\nCALL".$incomingCall;
				}
			
				if ( $answeredCall != null )
				{
					$result = "\nANSW".$answeredCall;
				}
			
				if ( $rejectedCall != null )
				{
					$result = "\nRJCT".$rejectedCall;
				}
			
				showResult(METHOD_UPDATESTATE, $result);
		
				break;
			}
			else
			{
				//user logged in from another place
				showResult(METHOD_UPDATESTATE, "LIAP");
			}
		}
		default:
		{
			dieWithMessage(METHOD_UPDATESTATE,'Unexpected state, user ip logged');
			
			break;
		}
	}
}

function notLoggedInFromAnotherPlace($userHash, $clientId)
{
	global $link;

	$query = "SELECT * FROM logged_users WHERE `user_hash` = '".$userHash."' AND `client_id` = '".$clientId."'";
			
	$result = mysql_query($query, $link);

	if (!$result)
	{
		dieWithMessage( METHOD_UPDATESTATE, 'Failed to select a user during updatestate' );
	}
	else
	{
		if ( mysql_num_rows($result) > 0 )
		{
			return true;
		}
		
		return false;
	}
}

//updateState function support
function returnRejectedCallIfPresent($userHash)
{
	global $link;
	
	$query="SELECT c.to_id, u.user_data_escaped FROM calls c INNER JOIN logged_users u ON (c.to_id = u.user_hash) WHERE c.state = ".
	CALL_REJECTED." AND c.from_id = '".$userHash."'";
	
	$result = mysql_query($query, $link);
	
	if (!$result)
	{
		dieWithMessage(METHOD_UPDATESTATE,'Failed to get outgoing calls in updateState: ' . mysql_error());
	}
	else
	{
		if ( mysql_num_rows($result) > 0 )
		{
			$obj = mysql_fetch_object($result);
			
			return $obj->user_data_escaped.'^@^'.$obj->to_id;
		}
	}
	
	return null;
}

//updateState function support
function returnAnsweredCallIfPresent($userHash)
{
	global $link;
	
	$query="SELECT c.to_id, u.user_data_escaped FROM calls c INNER JOIN logged_users u ON (c.to_id = u.user_hash) WHERE c.state = ".
	CALL_ANSWERED." AND c.from_id = '".$userHash."'";
	
	$result = mysql_query($query, $link);
	
	if (!$result)
	{
		dieWithMessage(METHOD_UPDATESTATE,'Failed to get outgoing calls in updateState: ' . mysql_error());
	}
	else
	{
		if ( mysql_num_rows($result) > 0 )
		{
			$obj = mysql_fetch_object($result);
			
			return $obj->user_data_escaped.'^@^'.$obj->to_id;
		}
	}
	
	return null;
}

//updateState function support
function returnIncomingCallIfPresent($userHash)
{
	global $link;
	
	$query="SELECT c.from_id, u.user_data_escaped FROM calls c INNER JOIN logged_users u ON (c.from_id = u.user_hash) WHERE c.state = ".
	CALL_WAITING." AND c.to_id = '".$userHash."'";
	
	$result = mysql_query($query, $link);
	
	if (!$result)
	{
		dieWithMessage(METHOD_UPDATESTATE,'Failed to get outgoing calls in updateState: ' . mysql_error());
	}
	else
	{
		if ( mysql_num_rows($result) > 0 )
		{
			$obj = mysql_fetch_object($result);
			
			return $obj->user_data_escaped.'^@^'.$obj->from_id;
		}
	}
	
	return null;
}

function clearCall($fromHash, $toHash)
{
	global $link;
	$fromHash = mysql_real_escape_string($fromHash);
	$toHash = mysql_real_escape_string($toHash);
	
	$query = "DELETE FROM calls WHERE `from_id`='".$fromHash."' AND `to_id`='".$toHash."'";
	
	if ( !mysql_query($query, $link) )
	{
		dieWithMessage(METHOD_CLEARCALL, 'Failed to clear call');
	}
	else
	{
		showResult( METHOD_CLEARCALL, $query );
	}
}

//Make a call
function makeCall($fromHash, $toHash)
{
	global $link;
	$fromHash = mysql_real_escape_string($fromHash);
	$toHash = mysql_real_escape_string($toHash);
	$currentTime = time();

	//dropping previous calls
	$query = "UPDATE calls SET `state` = ".CALL_INVALID.", `last_updated`=".$currentTime." WHERE `from_id` = '".$fromHash."'";
	
	//print $query;
	
	if (! mysql_query($query, $link) )
	{
		dieWithMessage(METHOD_MAKECALL,'Failed to delete calls in makecall : ' . mysql_error());
	}
	
	//checking if person having toHash is online
	$query = "SELECT * FROM logged_users WHERE (`user_hash` = '".$toHash."' AND `state` = ".USER_LOGGED_IN.')';
	
	//print $query;
	
	$result = mysql_query($query, $link);
	
	if (!$result )
	{
		dieWithMessage(METHOD_MAKECALL,'Failed to look for to_user in makecall : ' . mysql_error());
	}
	else
	{
		//Checking if there are logged in users with the given  toHash		
		if ( mysql_num_rows($result) > 0 )
		{
			$r_array = mysql_fetch_array($result, MYSQL_NUM);
			
			//Yes, there are, creating a call record			
			$query = "INSERT INTO calls VALUES ('".$fromHash."','".$toHash."',".$currentTime.','.CALL_WAITING.')';
			
			//print $query;
			
			if (! mysql_query($query, $link) )
			{
				dieWithMessage(METHOD_MAKECALL,'Failed to create record in calls: ' . mysql_error());
			}
			
			showResult(METHOD_MAKECALL,"MOK");
		}
		else
		{
			//They are offline
			showResult(METHOD_MAKECALL,"MOFFL");
		}
	}
}

//Answer a call
function answerCall( $fromHash, $toHash )
{
	global $link;
	$fromHash = mysql_real_escape_string($fromHash);
	$toHash = mysql_real_escape_string($toHash);
	$currentTime = time();	
	
	//checking if call exists and waiting
	$query = "SELECT * FROM calls WHERE ((`from_id` = '".$fromHash."' AND `to_id` = '".$toHash."') AND `state` = ".CALL_WAITING.')';
	
	//print $query;
	
	$result = mysql_query($query, $link);
	
	if ( !$result )
	{
		dieWithMessage(METHOD_ANSWERCALL,'Failed to select calls in answer'.mysql_error());
	}
	else
	{				
		if ( mysql_num_rows($result) > 0 )
		{
			$r_array = mysql_fetch_array($result, MYSQL_NUM);
			
			$query = "UPDATE calls SET `state`=".CALL_ANSWERED.", `last_updated` = ".$currentTime." WHERE `from_id`='".$fromHash."' AND `to_id`='".$toHash."'";
			
			//print $query;
			
			if (! mysql_query($query, $link) )
			{
				dieWithMessage(METHOD_ANSWERCALL,'Failed to update record in answercall: ' . mysql_error());
			}
			else
			{
				//Call answered
				showResult(METHOD_ANSWERCALL,"ANSWRD");
			}
		}
		else
		{
			//No calls remaining
			showResult(METHOD_ANSWERCALL,"NOCALLS");
		}
	}
}

//Reject a call
function rejectCall($fromHash,$toHash)
{
	global $link;
	$fromHash = mysql_real_escape_string($fromHash);
	$toHash = mysql_real_escape_string($toHash);
	$currentTime = time();
	
	//checking if call exists and waiting
	$query = "SELECT * FROM calls WHERE ((`from_id` = '".$fromHash."' AND `to_id` = '".$toHash."') AND `state` = ".CALL_WAITING.')';
	
	//print $query;
	
	$result = mysql_query($query, $link);
	
	if ( !$result )
	{
		dieWithMessage(METHOD_REJECTCALL,'Failed to select calls in answer'.mysql_error());
	}
	else
	{				
		if ( mysql_num_rows($result) > 0 )
		{
			$r_array = mysql_fetch_array($result, MYSQL_NUM);
			
			$query = "UPDATE calls SET `state`=".CALL_REJECTED.", `last_updated` = ".$currentTime." WHERE `from_id`='".$fromHash."' AND `to_id`='".$toHash."'";
			
			//print $query;
			
			if (! mysql_query($query, $link) )
			{
				dieWithMessage(METHOD_REJECTCALL,'Failed to update record in answercall: ' . mysql_error());
			}
			else
			{
				//Call answered
				showResult(METHOD_REJECTCALL,"RJCTD");
			}
		}
		else
		{
			//No calls remaining
			showResult(METHOD_REJECTCALL,"NOCALLS");
		}
	}
}

function getDisplayName( $hash )
{
	global $link;
	
	$query = "SELECT user_data_escaped FROM logged_users WHERE user_hash = '".$hash."'";
	
	$result = mysql_query($query, $link);
	
	if (! $result )
	{
		dieWithMessage(METHOD_GETCALLERLINK,'Failed to find caller record in logged_users' . mysql_error());
	}
	else
	{		
		if ( mysql_num_rows($result) > 0 )
		{
			$r_array = mysql_fetch_array($result, MYSQL_NUM);
			
			return $r_array[0];
		}	
		else
		{
			return 'Joe Unknown';
		}
	}
}

function getCallerLink( $fromHash, $toHash )
{
	global $link;
	$fromHash = mysql_real_escape_string($fromHash);
	$toHash = mysql_real_escape_string($toHash);
	
	$callerName = getDisplayName($fromHash);
	
	$userClient = new SoapClient(OM_SERVER_ADDRESS.'/services/UserService?wsdl');
	$roomClient = new SoapClient(OM_SERVER_ADDRESS.'/services/RoomService?wsdl');
	
	$session = $userClient->getSession();
	$loginResult = $userClient->loginUser(array('SID'=>$session->return->session_id,'username'=>OM_ADMIN_LOGIN, 'userpass'=>OM_ADMIN_PASS));
	
	if ($loginResult > 0)
	{
		//Successfully logged in, searching for a room, if its created
		
		$confs = $roomClient->getRooms(array('SID'=>$session->return->session_id, 
			'start'=>0, 'max'=>100, 'orderby'=>'name', 'asc'=>true))->return->result;			
			
		$roomId = null;
		
		foreach ( $confs as $value )
		{
			if ($value->name == $fromHash.$toHash)
			{
				//room exists, returning link to it
				$roomId = $value->rooms_id;				
			}
		}
		
		if ( $roomId == null )
		{
			//room doesn't exist, creating one and returning a link
			$addRoomResult = $roomClient->addRoomWithModeration(array('SID'=>$session->return->session_id,
			'name'=>$fromHash.$toHash,'roomtypes_id'=>1,'comment'=>'Call room','numberOfPartizipants'=>2,
			'ispublic'=>true,'appointment'=>false,'isDemoRoom'=>false,'demoTime'=>0,'isModeratedRoom'=>true))->return;
			
			if ( $addRoomResult > 0 )
			{
				$roomId = $addRoomResult;
			}
			else
			{
				dieWithMessage( METHOD_GETCALLERLINK, 'Failed to add a room' );
			}
		}

		$setUserObjectResult = $userClient->setUserObjectAndGenerateRoomHash(array('SID'=>$session->return->session_id,
		'username'=>$callerName,'firstname'=>$callerName, 'lastname'=>'', 'profilePictureUrl'=>'',
		'email'=>'','externalUserId'=>0,'externalUserType'=>'caller','room_id'=>$roomId, 'becomeModeratorAsInt'=>2, 
		'showAudioVideoTestAsInt'=>2))->return;

		if ( $setUserObjectResult != null )
		{
			showResult(METHOD_GETCALLERLINK, OM_SERVER_ADDRESS.'/openmeetings/main.lzx.swf8.swf?secureHash='.$setUserObjectResult);
		}
		else
		{
			dieWithMessage(METHOD_GETCALLERLINK,"Setting user object failed");
		}
	}
	else
	{
		dieWithMessage(METHOD_GETCALLERLINK, "Login failed");
	}
}

function getCalleeLink( $fromHash, $toHash )
{
	global $link;
	$fromHash = mysql_real_escape_string($fromHash);
	$toHash = mysql_real_escape_string($toHash);
	
	$callerName = getDisplayName($toHash);
	
	$userClient = new SoapClient(OM_SERVER_ADDRESS.'/services/UserService?wsdl');
	$roomClient = new SoapClient(OM_SERVER_ADDRESS.'/services/RoomService?wsdl');
	
	$session = $userClient->getSession();
	$loginResult = $userClient->loginUser(array('SID'=>$session->return->session_id,'username'=>OM_ADMIN_LOGIN, 'userpass'=>OM_ADMIN_PASS));
	
	if ($loginResult > 0)
	{
		//Successfully logged in, searching for a room, if its created
		
		$confs = $roomClient->getRooms(array('SID'=>$session->return->session_id, 
			'start'=>0, 'max'=>100, 'orderby'=>'name', 'asc'=>true))->return->result;			
			
		$roomId = null;
		
		foreach ( $confs as $value )
		{
			if ($value->name == $fromHash.$toHash)
			{
				//room exists, returning link to it
				$roomId = $value->rooms_id;				
			}
		}
		
		if ( $roomId == null )
		{
			//room doesn't exist, creating one and returning a link
			$addRoomResult = $roomClient->addRoomWithModeration(array('SID'=>$session->return->session_id,
			'name'=>$fromHash.$toHash,'roomtypes_id'=>1,'comment'=>'Call room','numberOfPartizipants'=>2,
			'ispublic'=>true,'appointment'=>false,'isDemoRoom'=>false,'demoTime'=>0,'isModeratedRoom'=>true))->return;
			
			if ( $addRoomResult > 0 )
			{
				$roomId = $addRoomResult;
			}
			else
			{
				dieWithMessage( METHOD_GETCALLEELINK, 'Failed to add a room' );
			}
		}

		$setUserObjectResult = $userClient->setUserObjectAndGenerateRoomHash(array('SID'=>$session->return->session_id,
		'username'=>$callerName,'firstname'=>$callerName, 'lastname'=>'', 'profilePictureUrl'=>'',
		'email'=>'','externalUserId'=>0,'externalUserType'=>'caller','room_id'=>$roomId, 'becomeModeratorAsInt'=>0, 
		'showAudioVideoTestAsInt'=>2))->return;

		if ( $setUserObjectResult != null )
		{
			showResult(METHOD_GETCALLEELINK, OM_SERVER_ADDRESS.'/openmeetings/main.lzx.swf8.swf?secureHash='.$setUserObjectResult);
		}
		else
		{
			dieWithMessage(METHOD_GETCALLEELINK,"Setting user object failed");
		}
	}
	else
	{
		dieWithMessage(METHOD_GETCALLEELINK, "Login failed");
	}
}

//End of function definitions

if ( $_REQUEST["method"] && ($_REQUEST["arg0"] && $_REQUEST["arg1"]) )
{
	initDB();
	
	call_user_func( $_REQUEST["method"], $_REQUEST["arg0"], $_REQUEST["arg1"], $_REQUEST["arg2"] );
	
	disposeAndClose();
}
else
{
	dieWithMessage('METHOD ERROR',"No method given or argument list is broken");
}
?>
