<?php

$hook_version = 1; 
$hook_array = Array(); 

$hook_array['before_save'] = Array(); 

$hook_array['before_save'][] = Array(1, 'OpenMeetings', 'modules/Openmeetings/openmeetingsRoomManagament.php','openmeetingsRoomManagament', 'createRoomWithMod');

$hook_array['before_save'][] = Array(2, 'OpenMeetings', 'modules/Openmeetings/openmeetingsRoomManagament.php','openmeetingsRoomManagament', 'getInvitationHash');


?>
