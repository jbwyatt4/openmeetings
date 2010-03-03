<?php
if(!defined('sugarEntry') || !sugarEntry) die('Not A Valid Entry Point');

require_once('lib/nusoap.php');


class openmeetings_gateway {
	
	var $session_id = "";

	/**
	 * TODO: Get Error Service and show detailed Error Message
	 */
	function openmeetings_loginuser() {
		global $current_user;
		global $system_config;

		$client_userService = new nusoap_client_om("http://".$system_config->settings[info_openmeetings_url].":".$system_config->settings[info_openmeetings_http_port]."/openmeetings/services/UserService?wsdl", "wsdl");
		$client_userService->setUseCurl(true);
		//echo "Client inited"."<br/>";
		$err = $client_userService->getError();
		if ($err) {
			echo '<h2>Constructor error</h2><pre>' . $err . '</pre>';
			echo '<h2>Debug</h2><pre>' . htmlspecialchars($client->getDebug(), ENT_QUOTES) . '</pre>';
			exit();
		}  
	
		$result = $client_userService->call('getSession');
		if ($client_userService->fault) {
			echo '<h2>Fault (Expect - The request contains an invalid SOAP body)</h2><pre>'; print_r($result); echo '</pre>';
		} else {
			$err = $client_userService->getError();
			if ($err) {
				echo '<h2>Error</h2><pre>' . $err . '</pre>';
			} else {
				//echo '<h2>Result</h2><pre>'; print_r($result); echo '</pre>';
				$this->session_id = $result["return"]["session_id"];
				//echo '<h2>Result</h2><pre>'; printf(); echo '</pre>';
				$params = array(
	    			'SID' => $this->session_id,
	    			'username' => $system_config->settings[info_openmeetings_username],
	    			'userpass' => $system_config->settings[info_openmeetings_password]
				);
							
				$result = $client_userService->call('loginUser',$params);
				//echo '<h2>Params</h2><pre>'; print_r($params); echo '</pre>';
				if ($client_userService->fault) {
					echo '<h2>Fault (Expect - The request contains an invalid SOAP body)</h2><pre>'; print_r($result); echo '</pre>';
				} else {
					$err = $client_userService->getError();
					if ($err) {
						echo '<h2>Error</h2><pre>' . $err . '</pre>';
					} else {
						//echo '<h2>Result</h2><pre>'; print_r($result); echo '</pre>';
						$returnValue = $result["return"];	
						//echo '<h2>returnValue</h2><pre>'; printf($returnValue); echo '</pre>';		
					}
				}
			}
		}   
		if ($returnValue>0){
	    	return true;
		} else {
			return false;
		}
	}

	/**
	 * TODO: Check Error handling
	 * 
	 * @deprecated this method is deprecated
	 * 
	 */
	function openmeetings_createroom($openmeetings,$roomtypes_id) {
		global $current_user;
		global $system_config;

	
		//		echo $CFG->openmeetings_red5host."<br/>";
		//		echo $CFG->openmeetings_red5port."<br/>";	
		//		foreach ($CFG as $key => $value){
		//    		echo "KEY: ".$key." Value: ".$value."<br/>";
		//    	}
    	$course_name = 'MOODLE_COURSE_ID_'.$openmeetings->course.'_NAME_'.$openmeetings->name;
    	//echo "CourseName: ".$course_name."<br/>";	
		
		//echo $client_userService."<br/>";
	    
	 	$client_roomService = new nusoap_client_om("http://".$system_config->settings[info_openmeetings_url].":".$system_config->settings[info_openmeetings_http_port]."/openmeetings/services/UserService?wsdl", true);
		
		$err = $client_roomService->getError();
		if ($err) {
			echo '<h2>Constructor error</h2><pre>' . $err . '</pre>';
			echo '<h2>Debug</h2><pre>' . htmlspecialchars($client->getDebug(), ENT_QUOTES) . '</pre>';
			exit();
		}  
		$params = array(
			'SID' => $this->session_id,
			'name' => $course_name,
			'roomtypes_id' => $roomtypes_id,
			'comment' => 'Created by SOAP-Gateway for Moodle Platform',
			'numberOfPartizipants' => 4,
			'ispublic' => true,
			'videoPodWidth' => 270, 
			'videoPodHeight' => 280,
			'videoPodXPosition' => 2, 
			'videoPodYPosition' => 2, 
			'moderationPanelXPosition' => 400, 
			'showWhiteBoard' => true, 
			'whiteBoardPanelXPosition' => 276, 
			'whiteBoardPanelYPosition' => 2, 
			'whiteBoardPanelHeight' => 592, 
			'whiteBoardPanelWidth' => 660, 
			'showFilesPanel' => true, 
			'filesPanelXPosition' => 2, 
			'filesPanelYPosition' => 284, 
			'filesPanelHeight' => 310, 
			'filesPanelWidth' => 270
		);
		$result = $client_roomService->call('addRoom',$params);
		if ($client_roomService->fault) {
			echo '<h2>Fault (Expect - The request contains an invalid SOAP body)</h2><pre>'; print_r($result); echo '</pre>';
		} else {
			$err = $client_roomService->getError();
			if ($err) {
				echo '<h2>Error</h2><pre>' . $err . '</pre>';
			} else {
				//echo '<h2>Result</h2><pre>'; print_r($result["return"]); echo '</pre>';
				return $result["return"];
			}
		}   
		return -1;
	}
	
	function openmeetings_createroomwithmod($openmeetings) {
		global $current_user;
		global $system_config;
		
	    	
	 	$client_roomService = new nusoap_client_om("http://".$system_config->settings[info_openmeetings_url].":".$system_config->settings[info_openmeetings_http_port]."/openmeetings/services/RoomService?wsdl", true);
		echo $client_userService."<br/>";
		$err = $client_roomService->getError();
		if ($err) {
			echo '<h2>Constructor error</h2><pre>' . $err . '</pre>';
			echo '<h2>Debug</h2><pre>' . htmlspecialchars($client->getDebug(), ENT_QUOTES) . '</pre>';
			exit();
		}  
		
		//$isModeratedRoom = true;
		#if ($openmeetings->is_moderated_room == 1) {
		#	$isModeratedRoom = true;
		#}
		/*
		$params = array(
			'SID' => $this->session_id,
			'name' => 'SUGARCM_ROOM',
			'roomtypes_id' => 1,
			'comment' => 'Created by SOAP-Gateway for SUGARCM Platform',
			'numberOfPartizipants' => 100,
			'ispublic' => false,
			'appointment' => false, 
			'isDemoRoom' => false, 
			'demoTime' => 0, 
			'isModeratedRoom' => $isModeratedRoom
		);
		*/
		$params = array(
			'SID' => $this->session_id,
			'name' => $openmeetings->name,
			'roomtypes_id' => $openmeetings->roomtypes_id,
			'comment' => $openmeetings->comment,
			'numberOfPartizipants' => $openmeetings->numberOfPartizipants,
			'ispublic' => $openmeetings->ispublic,
			'appointment' => $openmeetings->appointment, 
			'isDemoRoom' => $openmeetings->isDemoRoom, 
			'demoTime' => $openmeetings->demoTime, 
			'isModeratedRoom' => $openmeetings->isModeratedRoom
		);

		$result = $client_roomService->call('addRoomWithModeration',$params);
		if ($client_roomService->fault) {
			echo '<h2>Fault (Expect - The request contains an invalid SOAP body)</h2><pre>'; print_r($result); echo '</pre>';
		} else {
			$err = $client_roomService->getError();
			if ($err) {
				echo '<h2>Error</h2><pre>' . $err . '</pre>';
			} else {
				//echo '<h2>Result</h2><pre>'; print_r($result["return"]); echo '</pre>';
				return $result["return"];
			}
		}   
		return -1;
	}
	
	/*
	 * Usage if this Method will work if you have no need to simulate always the same user in 
	 * OpenMeetings, if you want to do this check the next method that also remembers the 
	 * ID of the external User
	 * 
	 * 
	 */
	function openmeetings_setUserObject($username, $firstname, $lastname, $profilePictureUrl, $email) {
	    	global $current_user;
		global $system_config;

	 	$client_userService = new nusoap_client_om("http://".$system_config->settings[info_openmeetings_url].":".$system_config->settings[info_openmeetings_http_port]."/openmeetings/services/UserService?wsdl", true);
		
		$err = $client_userService->getError();
		if ($err) {
			echo '<h2>Constructor error</h2><pre>' . $err . '</pre>';
			echo '<h2>Debug</h2><pre>' . htmlspecialchars($client->getDebug(), ENT_QUOTES) . '</pre>';
			exit();
		}  
		$params = array(
			'SID' => $this->session_id,
			'username' => $username,
			'firstname' => $firstname,
			'lastname' => $lastname,
			'profilePictureUrl' => $profilePictureUrl,
			'email' => $email
		);
		$result = $client_userService->call('setUserObject',$params);
		if ($client_roomService->fault) {
			echo '<h2>Fault (Expect - The request contains an invalid SOAP body)</h2><pre>'; print_r($result); echo '</pre>';
		} else {
			$err = $client_userService->getError();
			if ($err) {
				echo '<h2>Error</h2><pre>' . $err . '</pre>';
			} else {
				//echo '<h2>Result</h2><pre>'; print_r($result["return"]); echo '</pre>';
				return $result["return"];
			}
		}   
		return -1;
	}
	
	/**
	 * Sets the User Id and remembers the User, 
	 * the value for $systemType is any Flag but usually should always be the same, 
	 * it only has a reason if you have more then one external Systems, so the $userId will not 
	 * be unique, then you can use the $systemType to give each system its own scope
	 * 
	 * so a unique external user is always the pair of: $userId + $systemType
	 * 
	 * in this case the $systemType is 'moodle'
	 * 
	 */
	function openmeetings_setUserObjectWithExternalUser($username, $firstname, $lastname, $profilePictureUrl, $email, $userId, $systemType) {
	    	global $current_user;
		global $system_config;

	 	$client_userService = new nusoap_client_om("http://".$system_config->settings[info_openmeetings_url].":".$system_config->settings[info_openmeetings_http_port]."/openmeetings/services/UserService?wsdl", true);
		
		$err = $client_userService->getError();
		if ($err) {
			echo '<h2>Constructor error</h2><pre>' . $err . '</pre>';
			echo '<h2>Debug</h2><pre>' . htmlspecialchars($client->getDebug(), ENT_QUOTES) . '</pre>';
			exit();
		}  
		$params = array(
			'SID' => $this->session_id,
			'username' => $username,
			'firstname' => $firstname,
			'lastname' => $lastname,
			'profilePictureUrl' => $profilePictureUrl,
			'email' => $email,
			'externalUserId' => $userId,
			'externalUserType' => $systemType
		);
		$result = $client_userService->call('setUserObjectWithExternalUser',$params);
		if ($client_roomService->fault) {
			echo '<h2>Fault (Expect - The request contains an invalid SOAP body)</h2><pre>'; print_r($result); echo '</pre>';
		} else {
			$err = $client_userService->getError();
			if ($err) {
				echo '<h2>Error</h2><pre>' . $err . '</pre>';
			} else {
				//echo '<h2>Result</h2><pre>'; print_r($result["return"]); echo '</pre>';
				return $result["return"];
			}
		}   
		return -1;
	}

	function openmeetings_setUserObjectAndGenerateRoomHash($username, $firstname, $lastname, 
			$profilePictureUrl, $email, $externalUserId, $externalUserType, $room_id, $becomeModeratorAsInt, $showAudioVideoTestAsInt) {
	    	global $current_user;
		global $system_config;

	 	$client_userService = new nusoap_client_om("http://".$system_config->settings[info_openmeetings_url].":".$system_config->settings[info_openmeetings_http_port]."/openmeetings/services/UserService?wsdl", true);
		
		$err = $client_userService->getError();
		if ($err) {
			echo '<h2>Constructor error</h2><pre>' . $err . '</pre>';
			echo '<h2>Debug</h2><pre>' . htmlspecialchars($client->getDebug(), ENT_QUOTES) . '</pre>';
			exit();
		}  
		$params = array(
			'SID' => $this->session_id,
			'username' => $username,
			'firstname' => $firstname,
			'lastname' => $lastname,
			'profilePictureUrl' => $profilePictureUrl,
			'email' => $email,
			'externalUserId' => $externalUserId,
			'externalUserType' => $systemType,
			'room_id' => $room_id,
			'becomeModeratorAsInt' => $becomeModeratorAsInt,
			'showAudioVideoTestAsInt'=> $showAudioVideoTestAsInt
			
		);
		$result = $client_userService->call('setUserObjectAndGenerateRoomHashByURL',$params);
		if ($client_userService->fault) {
			echo '<h2>Fault (Expect - The request contains an invalid SOAP body)</h2><pre>'; print_r($result); echo '</pre>';
		} else {
			$err = $client_userService->getError();
			if ($err) {
				echo '<h2>Error</h2><pre>' . $err . '</pre>';
			} else {
				//echo '<h2>Result</h2><pre>'; print_r($result["return"]); echo '</pre>';
				return $result["return"];
			}
		}   
		return -1;
	}

	function openmeetings_sendInvitationHash($username, $message, $baseurl, $email, $subject, $room_id, $conferencedomain, $isPasswordProtected, $invitationpass, $valid, $validFromDate, $validFromTime, $validToDate, $validToTime, $language_id, $sendMail) {
	    	global $current_user;
		global $system_config;

	 	$client_userService = new nusoap_client_om("http://".$system_config->settings[info_openmeetings_url].":".$system_config->settings[info_openmeetings_http_port]."/openmeetings/services/RoomService?wsdl", true);
		
		$err = $client_userService->getError();
		if ($err) {
			echo '<h2>Constructor error</h2><pre>' . $err . '</pre>';
			echo '<h2>Debug</h2><pre>' . htmlspecialchars($client->getDebug(), ENT_QUOTES) . '</pre>';
			exit();
		}  
		$params = array(
			'SID' => $this->session_id,
			'username' => $username,
			'message' => $message,
			'baseurl' => $baseurl,
			'email' => $email,
			'subject' => $subject,
			'room_id' => $room_id,
			'conferencedomain' => $conferencedomain,
			'isPasswordProtected' => $isPasswordProtected,
			'invitationpass' => $invitationpass,
			'valid'=> $valid,
			'validFromDate' => $validFromDate,
			'validFromTime' => $validFromTime,
			'validToDate' => $validToDate,
			'validToTime' => $validToTime,
			'language_id' => $language_id,
			'sendMail' => $sendMail			
		);
		$result = $client_userService->call('sendInvitationHash', $params);
		if ($client_userService->fault) {
			echo '<h2>Fault (Expect - The request contains an invalid SOAP body)</h2><pre>'; print_r($result); echo '</pre>';
		} else {
			$err = $client_userService->getError();
			if ($err) {
				echo '<h2>Error</h2><pre>' . $err . '</pre>';
			} else {
				//echo '<h2>Result</h2><pre>'; print_r($result["return"]); echo '</pre>';
				return $result["return"];
			}
		}   
		return -1;
	}

	function sendInvitationHashWithDateObject($username, $message, $baseurl, $email, $subject, $room_id, $conferencedomain, $isPasswordProtected, $invitationpass, $valid, $fromDate, $toDate, $language_id, $sendMail) {
	    	global $current_user;
		global $system_config;

	 	$client_userService = new nusoap_client_om("http://".$system_config->settings[info_openmeetings_url].":".$system_config->settings[info_openmeetings_http_port]."/openmeetings/services/RoomService?wsdl", true);
		
		$err = $client_userService->getError();
		if ($err) {
			echo '<h2>Constructor error</h2><pre>' . $err . '</pre>';
			echo '<h2>Debug</h2><pre>' . htmlspecialchars($client->getDebug(), ENT_QUOTES) . '</pre>';
			exit();
		}  
		$params = array(
			'SID' => $this->session_id,
			'username' => $username,
			'message' => $message,
			'baseurl' => $baseurl,
			'email' => $email,
			'subject' => $subject,
			'room_id' => $room_id,
			'conferencedomain' => $conferencedomain,
			'isPasswordProtected' => $isPasswordProtected,
			'invitationpass' => $invitationpass,
			'valid'=> $valid,
			'fromDate' => $fromDate,
			'toDate' => $toDate,			
			'language_id' => $language_id,
			'sendMail' => $sendMail				
		);
		$result = $client_userService->call('sendInvitationHash', $params);
		if ($client_userService->fault) {
			echo '<h2>Fault (Expect - The request contains an invalid SOAP body)</h2><pre>'; print_r($result); echo '</pre>';
		} else {
			$err = $client_userService->getError();
			if ($err) {
				echo '<h2>Error</h2><pre>' . $err . '</pre>';
			} else {
				//echo '<h2>Result</h2><pre>'; print_r($result["return"]); echo '</pre>';
				return $result["return"];
			}
		}   
		return -1;
	}

function getInvitationHash($username, $room_id, $isPasswordProtected, $invitationpass, $valid, $validFromDate, $validFromTime, $validToDate, $validToTime) {
	    	global $current_user;
		global $system_config;

		//echo "room_id";
	 	$client_userService = new nusoap_client_om("http://".$system_config->settings[info_openmeetings_url].":".$system_config->settings[info_openmeetings_http_port]."/openmeetings/services/RoomService?wsdl", true);
		
		$err = $client_userService->getError();
		if ($err) {
			echo '<h2>Constructor error</h2><pre>' . $err . '</pre>';
			echo '<h2>Debug</h2><pre>' . htmlspecialchars($client->getDebug(), ENT_QUOTES) . '</pre>';
			exit();
		}  
		$params = array(
			'SID' => $this->session_id,
			'username' => $username,			
			'room_id' => $room_id,			
			'isPasswordProtected' => $isPasswordProtected,
			'invitationpass' => $invitationpass,
			'valid'=> $valid,
			'validFromDate' => $validFromDate,
			'validFromTime' => $validFromTime,
			'validToDate' => $validToDate,
			'validToTime' => $validToTime		
		);
		$result = $client_userService->call('getInvitationHash', $params);
		if ($client_userService->fault) {
			echo '<h2>Fault (Expect - The request contains an invalid SOAP body)</h2><pre>'; print_r($result); echo '</pre>';
		} else {
			$err = $client_userService->getError();
			if ($err) {
				echo '<h2>Error</h2><pre>' . $err . '</pre>';
			} else {
				//echo '<h2>Result</h2><pre>'; print_r($result["return"]); echo '</pre>';
				return $result["return"];
			}
		}   
		return -1;
	}



}

?>
