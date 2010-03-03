<?php

if(!defined('sugarEntry') || !sugarEntry) die('Not A Valid Entry Point');

require_once("openmeetings_gateway.php");

global $current_user;
global $system_config;

/*
echo '<pre>';
print_r($current_user);
echo '</pre>';
*/


//echo '<pre>';
//print_r($GLOBALS);
//print_r($system_config);
//echo "info: $system_config->settings->info_openmeetings_username";
//echo "info username from system_config:".$system_config->settings[info_openmeetings_username];
//echo '</pre>';


$becomemoderator = 1;	
	
	$openmeetings_gateway = new openmeetings_gateway();
	if ($openmeetings_gateway->openmeetings_loginuser()) {
		
		//set User-Object to Session ... is deprecated
		//$returnVal = $openmeetings_gateway->openmeetings_setUserObject($USER->username,$USER->firstname,
		//				$USER->lastname,$USER->picture,$USER->email);
		
		// Simulate the User automatically
		//echo "openmeetings_setUserObjectWithExternalUser<br/>";
	/*	$returnVal = $openmeetings_gateway->openmeetings_setUserObjectWithExternalUser($current_user->user_name,$current_user->first_name,
						$current_user->last_name,$current_user->picture,$current_user->email1,100 ,"openmeetings");
						
		if ($returnVal>0) {

			$iframe_d = "http://".$system_config->settings[info_openmeetings_url].":".
					$system_config->settings[info_openmeetings_http_port].
					"/openmeetings/main.swf8.swf?lzproxied=solo" .
					"&roomid=1" .					
					"&sid=".$openmeetings_gateway->session_id .					
					"&language=".$system_config->settings[info_openmeetings_language].					
					"&picture=1".
					"&user_id=1".
					"&wwwroot="."openmeetings" .
					"&moodleRoom=1" .
					"&becomemoderator=".$becomemoderator;

					//echo "iframe:  $iframe_d";
*/



$roomhash = $openmeetings_gateway->openmeetings_setUserObjectAndGenerateRoomHash($current_user->user_name,$current_user->first_name,
						$current_user->last_name,$current_user->picture,$current_user->email1,/*$current_user->id,*/100 ,"openmeetings", $current_user->openmeetings_my_roomid_c, 1, 1);


		//set User-Object to Session ... is deprecated
		//$returnVal = $openmeetings_gateway->openmeetings_setUserObject($USER->username,$USER->firstname,
		//				$USER->lastname,$USER->picture,$USER->email);
										
		if (!empty($roomhash)) {

			$iframe_d = "http://".$system_config->settings[info_openmeetings_url].":".
					$system_config->settings[info_openmeetings_http_port].
					"/openmeetings/?" .
					"scopeRoomId=" . $current_user->openmeetings_my_roomid_c .
					"&secureHash=" .$roomhash.	
					"&lzproxied=solo" .								
					"&language=".$system_config->settings[info_openmeetings_language];					
			/*	
			printf("<iframe src='%s' width='%s' height='600px' />",$iframe_d,
					"1000px");
			*/
			/* with videoconference.php

					$iframe_d = "videoconference.php?" .
							"sid=".$openmeetings_gateway->session_id .
							"&roomid=1" .
							"&language=".$system_config->settings[info_openmeetings_language].
							"&red5host=".$system_config->settings[info_openmeetings_url].
							"&red5httpPort=".$system_config->settings[info_openmeetings_http_port].
							"&picture=1".
							"&user_id=1".
							"&wwwroot="."openmeetings" .
							"&becomemoderator=".$becomemoderator;

					printf("<iframe src='%s' width='%s' height='%s' />",$iframe_d,
							"100%",
							"100%");	
							
		*/


			//echo "\n<p>\n";
			//echo get_module_title($mod_strings['LBL_MODULE_NAME'], $mod_strings['LBL_MODULE_NAME'].": ".$focus->full_name." (".$focus->user_name.")", true);
			//echo "\n</p>\n";
			global $theme;
			global $app_list_strings;
			$theme_path="themes/".$theme."/";
			$image_path=$theme_path."images/";
			require_once($theme_path.'layout_utils.php');


			$xtpl=new XTemplate ('modules/Openmeetings/DetailView.html');
			$xtpl->assign("MOD", $mod_strings);
			$xtpl->assign("APP", $app_strings);

			$xtpl->assign("THEME", $theme);
			$xtpl->assign("GRIDLINE", $gridline);
			$xtpl->assign("IMAGE_PATH", $image_path);$xtpl->assign("PRINT_URL", "index.php?".$GLOBALS['request_string']);
			$xtpl->assign("ID", $focus->id);
			$xtpl->assign("USER_NAME", $focus->user_name);
			$xtpl->assign("FULL_NAME", $focus->full_name);
			$xtpl->assign("IFRAME", $iframe_d);

			$xtpl->parse('main');
			$xtpl->out('main');

			
					
		}
	} else {
		echo "Could not login User to OpenMeetings, check your OpenMeetings Module Configuration";
		exit();
	}
?>
