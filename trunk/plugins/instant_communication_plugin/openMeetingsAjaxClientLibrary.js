var updateInterval;
var incomingCallPresent = false;

//current user display name id
var currentUser;
var userid = null;
var instanceId = '0';

//caller id, display name
var caller = null;
var callerName = null;

//callee id and display name
var callee = null;
var calleeName = null;

//server address
var server_address = 'http://127.0.0.1:8888/om_server.php';

function getXmlHttp(){
  var xmlhttp;
  try {
    xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
  } catch (e) {
    try {
      xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    } catch (E) {
      xmlhttp = false;
    }
  }
  if (!xmlhttp && typeof XMLHttpRequest!='undefined') {
    xmlhttp = new XMLHttpRequest();
  }
  return xmlhttp;
}

function queryServer(params)
{
	var request = getXmlHttp();
	
	request.open('POST', server_address, true);
	request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	request.setRequestHeader("Content-length", params.length);
	request.setRequestHeader("Connection", "close");
	request.onreadystatechange = function() 
	{
		if (request.readyState == 4) 
		{
			if(request.status == 200) 
			{
				var result = request.responseText.split('|');
				
				processServerMessage(result,request.responseText);
			}
		}
	}	
	request.send(params);
}

function logMessage(message)
{	
	om_onstatusmessage(message);
}

function processServerMessage( result, fullmessage )
{
	if ( result[0] == 'O' )
	{
		switch ( result[1] )
		{
			case 'R':
			{
				//Register user
				
				updateInterval = setInterval( updateStatus, 15000 );
				loginstatuschange( true );
				
				break;
			}
			case 'U':
			{
				logMessage( result[2] );
				//Update user state
				if ( result[2] != 'U0OK' )
				{
					var incomingCallFound = false;

					//We are still online
					//if we have unanswered calls, we must inform a user
					var messages = result[2].split('\n');
					
					for ( var i = 0; i < messages.length; i++)
					{
						if ( messages[i] != 'U1OK' )
						{			
							//Checking for incoming calls
							if ( messages[i].match('^CALL') == 'CALL' )
							{
								var callerStrArray = messages[i].substr(4,messages[i].length-4).split('^@^');
								
								caller = callerStrArray[1];
								callerName = callerStrArray[0];

								getCalleeLink( caller, userid );		

								incomingCallPresent = true;					
								incomingCallFound = true;

								om_onincomingcallstatechanged( true );
								
								break;
							}
							
							//Checking for answered calls
							if ( messages[i].match('^ANSW') == 'ANSW' )
							{
								var calleeStrArray = messages[i].substr(4,messages[i].length-4).split('^@^');

								calleeName = calleeStrArray[0];
								callee = calleeStrArray[1];																
								
								getCallerLink( userid, callee );
								
								break;
							}
							
							//Checking for rejected calls
							if ( messages[i].match('^RJCT') == 'RJCT' )
							{
								alert('call rej');
								var calleeStrArray = messages[i].substr(4,messages[i].length-4).split('^@^');

								calleeName = calleeStrArray[0];
								callee = calleeStrArray[1];

								om_oncallrejected( calleeName );								
								
								break;
							}
							
							//Checking for being logged from another place
							if ( messages[i].match('^LIAP') == 'LIAP' )
							{																
								alert("Logged in from another place");

								loginstatuschange(false);

								break;
							}
							
							if ( messages[i] != '')
							alert('Unprocessed message: '+messages[i]);
						}
					}

					if (( !incomingCallFound )&&(incomingCallPresent))
					{
						incomingCallPresent = false;
						om_onincomingcallstatechanged( false );
					}
				}
				else
				{
					loginstatuschange(false);
				}				
				
				break;
			}
			case 'M':
			{
				//Make call
				if ( result[2] == 'MOK' )
				{
					om_onmakecall( true )
				}
				else
				{
					om_onmakecall(false);
				}
				break;
			}
			case 'A':
			{
				//Answer call
				//Do nothing

				break;
			}
			case 'C':
			{
				//Clear call
				//Do nothing

				break;
			}
			case 'J':
			{
				//Reject call
				//Do nothing

				break;
			}
			case 'L':
			{
				//Get caller link
				om_oncallanswered( calleeName, result[2] ); 

				break;
			}
			case 'E':
			{
				//Get callee link
				om_onincomingcall( callerName, result[2] );

				break;
			}
			default:
			{
				alert( 'OK, method='+result[1]+'; Message: '+result[2]);
				
				break;
			}
		}
	}
	else
	if ( result[0] == 'F' )
	{
		//Failure, display it
		//TODO: remove after diag is done
		alert('Error, method='+result[1]+'; Message: '+result[2]);
	}
	else
	{
		alert('Error in script:'+fullmessage);
	}
}

function getCallerLink( caller, callee )
{
	var getCallerLinkParams = 'method=getCallerLink&arg0='+caller+'&arg1='+callee;
	queryServer( getCallerLinkParams );
}

function getCalleeLink( caller, callee )
{
	var getCalleeLinkParams = 'method=getCalleeLink&arg0='+caller+'&arg1='+callee;
	queryServer( getCalleeLinkParams );
}

function clearCall( caller, callee )
{
	var clearCallParams = 'method=clearCall&arg0='+caller+'&arg1='+callee;
	queryServer( clearCallParams);
}

function arrangeCall(user_from, user_to)
{
	callee = user_to;
	var callParams = 'method=makeCall&arg0='+user_from+'&arg1='+user_to;
	queryServer( callParams );
}

function updateStatus()
{
	var updateParams = 'method=updateState&arg0='+userid+'&arg1=LOGGED_ON'+'&arg2='+instanceId;
	queryServer(updateParams);
}

function om_makecall(id)
{
	arrangeCall(userid, id);
}

function om_answercall()
{
	var answerParams = 'method=answerCall&arg0='+caller+'&arg1='+userid;
	queryServer( answerParams);
}

function om_rejectCall()
{
	var answerParams = 'method=rejectCall&arg0='+caller+'&arg1='+userid;
	queryServer( answerParams);
}

function om_clearoutgoingcall()
{
	clearCall( userid, callee );
}

function om_clearincomingcall()
{
	clearCall( caller, userid );
}

function om_setinstanceid( newInstance )
{
	instanceId = newInstance;
}

function om_login(id, name)
{
	userid = id;
	currentUser = name;
	var loginParams = 'method=registerUser&arg0='+id+'&arg1='+name+'&arg2='+instanceId;
	queryServer(loginParams);
}

function om_logoff()
{
	var logoutParams = 'method=updateState&arg0='+userid+'&arg1=LOGGED_OFF';
	queryServer(logoutParams);
}

function om_setserveraddress( newAddress )
{
	server_address = newAddress;
}

function om_setomlink(link)
{
	window.onLoad = function(){

	om_link = link;

	//var wh = window.open( link, "CachedOMWindow" );

	var str = "<object type='application/x-shockwave-flash' data='"+link+"' width='1' height='1'><param name='movie' value='"+link+"'/></object>";

	var obj = document.createElement('para');
	obj.innerHTML = str;

	document.body.appendChild(obj);
	}
}

//Events:
//om_onstatuschange - changed status (online/offline)
//om_onmakecall - call has been made, user is offline or all is ok
//om_onincomingcall - we got call
//om_oncallanswered - our call has been answered
//om_oncallrejected - our call has been rejected
//om_onstatusmessage - engine has something to display

function loginstatuschange(status)
{
	if (!status)
	{
		clearInterval( updateInterval );
	}
	om_onstatuschange( status );
}

function om_onstatuschange( status )
{
	if ( status )
	{
		om_onstatusmessage('Logged on');
	}
	else
	{
		om_onstatusmessage('Logged off');		
	}
}

function om_onmakecall( result )
{
	if ( result )
	{
		om_onstatusmessage( 'Makecall ok' );
	}
	else
	{
		om_onstatusmessage('Makecall failed - User is offline');
	}
}

function om_onincomingcall( callerName, link )
{
	om_onstatusmessage(callerName+' called, link to call - '+link);
}

function om_oncallanswered( calleeName, link )
{
	om_onstatusmessage(calleeName+' answered our call, link to call - '+link);
}

function om_oncallrejected( calleeName )
{
	om_onstatusmessage(calleeName+' rejected our call');
}

function om_onincomingcallstatechanged( state )
{
	om_onstatusmessage('Incoming call state changed');
}

function om_onstatusmessage(message)
{
	alert( message );
}