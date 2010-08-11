<?php

if(!defined('sugarEntry') || !sugarEntry) die('Not A Valid Entry Point');

require_once("openmeetings_gateway.php");

global $current_user;
global $system_config;


class openmeetingsRoomManagament {


	function getRoomHash(&$focus, $event) {
		
		global $current_user;
		$becomemoderator = 1;	
	
		$openmeetings_gateway = new openmeetings_gateway();
		
	
		if ($openmeetings_gateway->openmeetings_loginuser()) {
		
				$roomid = $openmeetings_gateway->openmeetings_createroomwithmod($test);
		
		 		$roomhash = $openmeetings_gateway->openmeetings_setUserObjectAndGenerateRoomHash($current_user->user_name,$current_user->first_name,
						$current_user->last_name,$current_user->picture,$current_user->email1,/*$current_user->id,*/100 ,"openmeetings", $focus->openmeetings_roomid_c, 1, 1);
				echo   "room id: $roomid";
				echo "room hash:  $roomhash ";

		}
	}
		
	function createRoomWithMod(&$focus, $event) {
		global $sugar_config;
				
		if ($focus->is_openmeetings_c == false){
			$focus->openmeetings_roomname_c = "";
		}
		
		
		if ($focus->openmeetings_roomname_c == "" && $focus->is_openmeetings_c == true){

			$openmeetings_gateway = new openmeetings_gateway();
			if ($openmeetings_gateway->openmeetings_loginuser()) {

				$openmeetings->name = "SUGARCRM_MEETINGROOM_".$focus->id;
				$openmeetings->roomtypes_id = 1;
				$openmeetings->comment = 'Created by SOAP-Gateway for SUGARCM Platform';
				$openmeetings->numberOfPartizipants = 100;
				$openmeetings->ispublic = false;
				$openmeetings->appointment = false;
				$openmeetings->isDemoRoom = false;
				$openmeetings->demoTime = 0;
				$openmeetings->isModeratedRoom = true;
		
				$roomid = $openmeetings_gateway->openmeetings_createroomwithmod($openmeetings);	
				$focus->openmeetings_roomid_c = $roomid;
				
				$focus->openmeetings_roomname_c = ""; 		
				$focus->openmeetings_roomname_c = $sugar_config[site_url]."/index.php?module=Openmeetings&action=room_entrance&roomid=".$focus->openmeetings_roomid_c;
						
			} else {
				echo "Could not login User to OpenMeetings, check your OpenMeetings Module Configuration";
				//exit();
			}
		}
	}

/*
	function createMyRoomWithMod(&$focus, $event) {
		global $sugar_config;
				
		
		
		if ($sugar_config->openmeetings_username == "" & $focus->openmeetings_my_roomid_c == ""){

			$openmeetings_gateway = new openmeetings_gateway();
			if ($openmeetings_gateway->openmeetings_loginuser()) {
		
				$roomid = $openmeetings_gateway->openmeetings_createroomwithmod($test);	
				//$focus->openmeetings_roomid_c = $roomid;
				
				//$focus->openmeetings_roomname_c = ""; 		
				//$focus->openmeetings_my_room_c = $sugar_config[site_url]."/index.php?module=Openmeetings&action=room_entrance&roomid=".$roomid;
				
				$focus->openmeetings_my_roomid_c = $roomid;
				$focus->save(false);
						
			} else {
				echo "Could not login User to OpenMeetings, check your OpenMeetings Module Configuration";
				exit();
			}
		}
	}

*/

	function createMyRoomWithMod() {
		global $sugar_config;
		global $current_user;

		$openmeetings_gateway = new openmeetings_gateway();
		if ($openmeetings_gateway->openmeetings_loginuser()) {

		/*	'name' => 'SUGARCM_ROOM',
			'roomtypes_id' => 1,
			'comment' => 'Created by SOAP-Gateway for SUGARCM Platform',
			'numberOfPartizipants' => 100,
			'ispublic' => false,
			'appointment' => false, 
			'isDemoRoom' => false, 
			'demoTime' => 0, 
			'isModeratedRoom' => $isModeratedRoom
		*/

			$openmeetings->name = "SUGARCRM_MY_ROOM_".$current_user->id;
			$openmeetings->roomtypes_id = 1;
			$openmeetings->comment = 'Created by SOAP-Gateway for SUGARCM Platform';
			$openmeetings->numberOfPartizipants = 100;
			$openmeetings->ispublic = false;
			$openmeetings->appointment = false;
			$openmeetings->isDemoRoom = false;
			$openmeetings->demoTime = 0;
			$openmeetings->isModeratedRoom = true;
		
			$roomid = $openmeetings_gateway->openmeetings_createroomwithmod($openmeetings);	
				
			return $roomid;
						
		} else {
			echo "Could not login User to OpenMeetings, check your OpenMeetings Module Configuration";
			//exit();
		}
	}


	function getInvitationHash(&$focus, $event) {

		global $sugar_config;
		global $system_config;
		global $timedate;


		$date_start_in_db_fmt=$timedate->swap_formats($focus->date_start, $timedate->get_date_time_format(true, $current_user),  $timedate->get_db_date_time_format());

		$date_start_array=split(" ",trim($date_start_in_db_fmt));
		$date_time_start =DateTimeUtil::get_time_start($date_start_array[0],$date_start_array[1]);

		$date_start_timestamp=mktime($date_time_start->hour,$date_time_start->min,$date_time_start->sec,$date_time_start->month,$date_time_start->day);

		$startDateString = date("d.m.Y", $date_start_timestamp); //dd.mm.yyyy
		$startTimeString = date("H:i", $date_start_timestamp);


		$date_end_timestamp = $date_start_timestamp + (( $focus->duration_hours * 3600 )+ ($focus->duration_minutes * 60));

		$endDateString = date("d.m.Y", $date_end_timestamp); //dd.mm.yyyy
		$endTimeString = date("H:i", $date_end_timestamp);


/*

echo '<pre>'; 

echo $startDateString;
echo "\n";

echo $startTimeString;
echo "\n";


echo $date_start_in_db_fmt;
echo "\n";

echo "endDateString ". $endDateString;
echo "\n";
echo "endTimeString ". $endTimeString;
echo "\n";
exit();

echo '</pre>';
*/	
		
		if ($focus->online_meeting_url_c  == "" && $focus->is_openmeetings_c == true && $_REQUEST['send_invites']=="1"){
			$_REQUEST['send_invites'] = '0';
			
			$openmeetings_gateway = new openmeetings_gateway();
			if ($openmeetings_gateway->openmeetings_loginuser()) {

				$admin = new Administration();
	 			$admin->retrieveSettings();
			
				//$notify_user = $focus->get_notification_recipients();


				foreach($focus->users_arr as $user_id) {
					$notify_user = new User();
					$notify_user->retrieve($user_id);
					$notify_user->new_assigned_user_name = $notify_user->full_name;
					$GLOBALS['log']->info("Notifications: recipient is $notify_user->new_assigned_user_name");
			
					$invitation_hash = $openmeetings_gateway->getInvitationHash($notify_user->name, $focus->openmeetings_roomid_c, false, "123", 2, $startDateString, $startTimeString, $endDateString, $endTimeString);
						
					$notify_user->online_meeting_url_temp = "http://".$system_config->settings[info_openmeetings_url].":".
							$system_config->settings[info_openmeetings_http_port].
							"/openmeetings/?" .
							"invitationHash=" .$invitation_hash;
			

					//$admin = new Administration();
		 			//$admin->retrieveSettings();

					$focus->send_assignment_notifications($notify_user, $admin);
				}

			
				foreach($focus->contacts_arr as $contact_id) {
					$notify_user = new Contact();
					$notify_user->retrieve($contact_id);
					$notify_user->new_assigned_user_name = $notify_user->name;
					$GLOBALS['log']->info("Notifications: recipient is $notify_user->new_assigned_user_name");
			
					$invitation_hash = $openmeetings_gateway->getInvitationHash($notify_user->name, $focus->openmeetings_roomid_c, false, "123", 2, $startDateString, $startTimeString, $endDateString, $endTimeString);
						
					$notify_user->online_meeting_url_temp = "http://".$system_config->settings[info_openmeetings_url].":".
							$system_config->settings[info_openmeetings_http_port].
							"/openmeetings/?" .
							"invitationHash=" .$invitation_hash;
			

					//$admin = new Administration();
		 			//$admin->retrieveSettings();

					$focus->send_assignment_notifications($notify_user, $admin);
			
				}

					foreach($focus->leads_arr as $lead_id) {
					$notify_user = new Lead();
					$notify_user->retrieve($lead_id);
					$notify_user->new_assigned_user_name = $notify_user->full_name;
					$GLOBALS['log']->info("Notifications: recipient is $notify_user->new_assigned_user_name");

					$invitation_hash = $openmeetings_gateway->getInvitationHash($notify_user->name, $focus->openmeetings_roomid_c, false, "123", 2, $startDateString, $startTimeString, $endDateString, $endTimeString);
						
					$notify_user->online_meeting_url_temp = "http://".$system_config->settings[info_openmeetings_url].":".
							$system_config->settings[info_openmeetings_http_port].
							"/openmeetings/?" .
							"invitationHash=" .$invitation_hash;
			

					//$admin = new Administration();
		 			//$admin->retrieveSettings();

					$focus->send_assignment_notifications($notify_user, $admin);
			
				}
			
			} else {
				echo "Could not login User to OpenMeetings, check your OpenMeetings Module Configuration";
				//exit();
			}
		}
	}




function sendInvitationHash(&$focus, $event) {
		global $sugar_config;
		global $system_config;
		global $current_user;
		
				
		if ($focus->online_meeting_url_c == "" & $focus->is_openmeetings_c == true){

			$openmeetings_gateway = new openmeetings_gateway();
			if ($openmeetings_gateway->openmeetings_loginuser()) {

			
				foreach($focus->contacts_arr as $contact_id) {
					$notify_user = new Contact();
					$notify_user->retrieve($contact_id);

					$result = $openmeetings_gateway->openmeetings_sendInvitationHash($current_user->name, "message", "http://".$system_config->settings[info_openmeetings_url].":".$system_config->settings[info_openmeetings_http_port]."/openmeetings/", $notify_user->email1, "Openmeeting Invetation: ".$focus->name, $focus->openmeetings_roomid_c, "", false, "123", 1, "$focus->date_start", "$focus->time_start", "$focus->date_end", "$focus->time_end", $system_config->settings[info_openmeetings_language], true);
		
						
				}

			
			} else {
				echo "Could not login User to OpenMeetings, check your OpenMeetings Module Configuration";
				//exit();
			}
		}
	}




}


?>
