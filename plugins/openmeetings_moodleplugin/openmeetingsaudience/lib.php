<?php  // $Id: lib.php,v 1.0 2008/05/14 12:00:00 Sebastian Wagner Exp $

require_once($CFG->dirroot.'/config.php');
require_once($CFG->dirroot.'/mod/openmeetingsaudience/openmeetingsaudience_gateway.php');


function openmeetingsaudience_add_instance($openmeetingsaudience) {
	global $USER, $CFG;

	$openmeetingsaudience_gateway = new openmeetingsaudience_gateway();
	if ($openmeetingsaudience_gateway->openmeetings_loginuser()) {
		$openmeetingsaudience->room_id = $openmeetingsaudience_gateway->openmeetings_createroom_audience($openmeetingsaudience,2);
	} else {
		echo "Could not login User to openmeetingsaudience, check your openmeetingsaudience Module Configuration";
		exit();
	}

	if ($openmeetingsaudience->room_id<=0) {
		echo "No room created";
		exit();
	}
    # May have to add extra stuff in here #
    return insert_record("openmeetingsaudience", $openmeetingsaudience);
}


function openmeetingsaudience_update_instance($openmeetingsaudience) {

    $openmeetingsaudience->timemodified = time();
    $openmeetingsaudience->id = $openmeetingsaudience->instance;

    # May have to add extra stuff in here #

    return update_record("openmeetingsaudience", $openmeetingsaudience);
}


function openmeetingsaudience_delete_instance($id) {

    if (! $openmeetingsaudience = get_record("openmeetingsaudience", "id", "$id")) {
        return false;
    }

    $result = true;

    # Delete any dependent records here #

    if (! delete_records("openmeetingsaudience", "id", "$openmeetingsaudience->id")) {
        $result = false;
    }

    return $result;
}


function openmeetingsaudience_user_outline($course, $user, $mod, $openmeetingsaudience) {
    return $return;
}


function openmeetingsaudience_user_complete($course, $user, $mod, $openmeetingsaudience) {
    return true;
}


function openmeetingsaudience_print_recent_activity($course, $isteacher, $timestart) {
    global $CFG;

    return false;  //  True if anything was printed, otherwise false 
}


function openmeetingsaudience_cron () {
    global $CFG;

    return true;
}


function openmeetingsaudience_grades($openmeetingsaudienceid) {
   return NULL;
}


function openmeetingsaudience_get_participants($openmeetingsaudienceid) {
    return false;
}

function openmeetingsaudience_scale_used ($openmeetingsaudienceid,$scaleid) {
    $return = false;

    return $return;
}

?>