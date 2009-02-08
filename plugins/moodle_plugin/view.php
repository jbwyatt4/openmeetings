<?php  // $Id: view.php,v 1.0 2007/10/24 12:00:00 Serafim Panov Exp $ 


    require_once("../../config.php");
    require_once("lib.php");
    require_once("openmeetings_gateway.php");


    $id = optional_param('id', 0, PARAM_INT); // Course Module ID, or
    $a  = optional_param('a', 0, PARAM_INT);  // whiteboard ID
    $g  = optional_param('g');

    if ($id) {
        if (! $cm = get_record("course_modules", "id", $id)) {
            error("Course Module ID was incorrect");
        }
    
        if (! $course = get_record("course", "id", $cm->course)) {
            error("Course is misconfigured");
        }
    
        if (! $openmeetings = get_record("openmeetings", "id", $cm->instance)) {
            error("Course module is incorrect");
        }

    } else {
        if (! $openmeetings = get_record("openmeetings", "id", $a)) {
            error("Course module is incorrect");
        }
        if (! $course = get_record("course", "id", $openmeetings->course)) {
            error("Course is misconfigured");
        }
        if (! $cm = get_coursemodule_from_instance("openmeetings", $openmeetings->id, $course->id)) {
            error("Course Module ID was incorrect");
        }
    }

    require_login($course->id);

    add_to_log($course->id, "openmeetings", "view", "view.php?id=$cm->id", "$openmeetings->id");

/// Print the page header

    if ($course->category) {
        $navigation = "<a href=\"../../course/view.php?id=$course->id\">$course->shortname</a> ->";
    } else {
        $navigation = '';
    }

    $stropenmeetingss = get_string("modulenameplural", "openmeetings");
    $stropenmeetings  = get_string("modulename", "openmeetings");

    print_header("$course->shortname: $openmeetings->name", "$course->fullname",
                 "$navigation <a href=index.php?id=$course->id>$stropenmeetingss</a> -> $openmeetings->name", 
                  "", "", true, update_module_button($cm->id, $course->id, $stropenmeetings), 
                  navmenu($course, $cm));

/// Print the main part of the page

    //print_simple_box_start('center', '100%', '#ffffff', 10); 
    
    $colors = Array ("FFFF00", "CCCC00", "FFCC00", "CC9933", "996600", "FF9900", "CC9966", "CC6600", "996633", "663300", "FF6600", "CC6633", "993300", "660000", "FF6633", "CC3300", "FF3300", "FF0000", "CC0000", "990000", "FF3333", "FF0033", "CC0033", "CC6666", "CC3333", "993333", "990033", "330000", "FF3366", "FF0066", "CC3366", "996666", "663333", "9966CC", "9966FF", "6600CC", "6633CC", "663399", "330033", "3333FF", "3300FF", "3300CC", "3333CC", "000099", "000066", "99CCCC", "66CCCC", "339999", "669999", "006666", "336666", "66CC66", "669966", "336633", "003300", "006600", "CCCC66", "CCCC33", "999966", "999933", "999900", "666600");
    
    $colorid = rand (0, 61);
    
    $sitelink = str_replace("http://", "", $CFG->wwwroot);
    
    
    $moduleid = get_record ("modules", "name", "openmeetings");
    
    $coursedata = get_record ("course_modules", "course", $cm->course, "module", $moduleid->id, "instance", $cm->instance);
    
    if ($coursedata->groupmode != 0 && empty($g)) {
        //print_r (groups_get_groups($cm->course));
        $usergroups = groups_get_groups_for_user($USER->id, $cm->course);
        if (count($usergroups) > 1) {
            $table->head  = array ("Your Groups");
            $table->align = array ("center");
            
            $groupstitles = "";
            
            foreach ($usergroups as $usergroup) {
                $groupdata = get_record ("groups", "id", $usergroup);
                $groupstitles .= '<a href="view.php?id='.$id.'&g='.$usergroup.'">'.$groupdata->name.'</a><br />';
            }
            $table->data[] = array ($groupstitles);
            
            print_table($table);
            
            $show = "false";
        }
        else
        {
            $thelink = $sitelink."/".$course->id."/".$cm->instance."/".$usergroups[0];
        }
    }
    else
    {
        if (empty($g)) {
            $thelink = $sitelink."/".$course->id."/".$cm->instance;
        }
        else
        {
            $thelink = $sitelink."/".$course->id."/".$cm->instance."/".$g;
        }
    }
    
    if ($show != "false") {

//    	echo $USER->username."<br/>";
//    	echo $USER->firstname."<br/>";
//    	echo $USER->lastname."<br/>";
//    	echo $USER->email."<br/>";
//    	echo $USER->picture."<br/>";

		$openmeetings_gateway = new openmeetings_gateway();
		if ($openmeetings_gateway->openmeetings_loginuser()) {
			//set User-Object to Session
			$returnVal = $openmeetings_gateway->openmeetings_setUserObject($USER->username,$USER->firstname,
							$USER->lastname,$USER->picture,$USER->email);
			if ($returnVal>0) {
				$iframe_d = "videoconference.php?" .
						"sid=".$openmeetings_gateway->session_id .
						"&roomid=".$openmeetings->room_id .
						"&language=".$CFG->openmeetings_openmeetingsLanguage .
						"&red5host=".$CFG->openmeetings_red5host .
						"&red5httpPort=".$CFG->openmeetings_red5port;

				printf("<iframe src='%s' width='%s' height='%s' />",$iframe_d,
						$CFG->openmeetings_openmeetingsiFrameWidth,
						$CFG->openmeetings_openmeetingsiFrameHeight);					
				
			}
		} else {
			echo "Could not login User to OpenMeetings, check your OpenMeetings Module Configuration";
			exit();
		}
    }


    //print_simple_box_end();

/// Finish the page
    print_footer($course);
?>