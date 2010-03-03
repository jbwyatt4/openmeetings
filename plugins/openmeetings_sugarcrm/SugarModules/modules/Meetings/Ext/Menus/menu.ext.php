<?php 


if(!defined('sugarEntry') || !sugarEntry) die('Not A Valid Entry Point'); 

require_once("modules/Openmeetings/openmeetingsRoomManagament.php");
global $mod_strings;
global $current_user;
global $system_config;

  if ($system_config->settings[info_openmeetings_username]!=""){


   	if ($current_user->openmeetings_my_roomid_c == ""){

		$omRoomManagament = new openmeetingsRoomManagament();
		$roomid = $omRoomManagament-> createMyRoomWithMod();
		$current_user->openmeetings_my_roomid_c = $roomid;
		$current_user->save();		
	
	}


	$module_menu[] = 
		Array("index.php?module=Openmeetings&action=view", $mod_strings['LBL_OPENMEETINGS_STARTADHOC'],"logo");
  }



?>
