<?php
if(!defined('sugarEntry'))define('sugarEntry', true);
function pre_install() {
   require_once('include/utils.php');
   check_logic_hook_file("Meetings", "before_save", array(1, "Openmeetings",
   "modules/Openmeetings/openmeetingsRoomManagament.php", "openmeetingsRoomManagament",
   "createRoomWithMod"));

	check_logic_hook_file("Meetings", "before_save", array(2, "Openmeetings",
   "modules/Openmeetings/openmeetingsRoomManagament.php", "openmeetingsRoomManagament",
   "getInvitationHash"));
	
	check_logic_hook_file("Meetings", "before_save", array(2, "Openmeetings",
   "modules/Openmeetings/openmeetingsRoomManagament.php", "openmeetingsRoomManagament",
   "sendInvitationHash"));
	
	

/*
$hook_array['after_login'][] = Array(1, 'OpenMeetings', 'modules/Openmeetings/openmeetingsRoomManagament.php','openmeetingsRoomManagament', 'createMyRoomWithMod');



$hook_array['before_save'][] = Array(1, 'OpenMeetings', 'modules/Openmeetings/openmeetingsRoomManagament.php','openmeetingsRoomManagament', 'createRoomWithMod');

//$hook_array['before_save'][] = Array(2, 'OpenMeetings', 'modules/Openmeetings/openmeetingsRoomManagament.php','openmeetingsRoomManagament', 'getInvitationHash');

$hook_array['before_save'][] = Array(2, 'OpenMeetings', 'modules/Openmeetings/openmeetingsRoomManagament.php','openmeetingsRoomManagament', 'sendInvitationHash');  

*/
}
?>

