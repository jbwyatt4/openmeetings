<?php

if (!defined('MOODLE_INTERNAL')) {
	die('Direct access to this script is forbidden.');    ///  It must be included from a Moodle page
}

require_once ($CFG->dirroot.'/course/moodleform_mod.php');

class mod_openmeetings_mod_form extends moodleform_mod {

	function definition() {

		global $COURSE;
		$mform    =& $this->_form;

		//-------------------------------------------------------------------------------
		/// Adding the "general" fieldset, where all the common settings are showed
		$mform->addElement('header', 'general', get_string('general', 'form'));
		/// Adding the standard "name" field
		$mform->addElement('text', 'name', get_string('Room_Name', 'openmeetings'), array('size'=>'64'));
		$mform->setType('name', PARAM_TEXT);
		$mform->addRule('name', null, 'required', null, 'client');

		$mform->addElement('hidden', 'room_id', 'Room Id', array('size'=>'64'));

		/// Adding the "Room Type" field
		$mform->addElement('select', 'type', get_string('Room_Type', 'openmeetings'), array('1'=>get_string('Conference', 'openmeetings'), '2'=>get_string('Audience', 'openmeetings'), '3'=>get_string('Restricted', 'openmeetings'), '0'=>get_string('Recording', 'openmeetings')));

		/// Some description
		//$mform->addElement('static', 'description_room_recording_id', get_string('recordings_label', 'openmeetings'), null);

		/// Adding the "Available Recordings to Shows" field

		//$recordings = array('1'=>'Recording 1', '2'=>'Recording 2');
		$recordings = array();
			
		$openmeetings_gateway = new openmeetings_gateway();
		if ($openmeetings_gateway->openmeetings_loginuser()) {

			$recordingsArray = $openmeetings_gateway->openmeetings_getRecordingsByExternalRooms();


			foreach ($recordingsArray as $key => $value) {
				//there is a bug, if a List has the length of 1 the type is wrong
				if (is_array($value)) {
					//echo "Das Element " . $value["flvRecordingId"] . " enthält den Wert: " . $value["fileName"] . "<br>";
					$recordings[$value["flvRecordingId"]] = $value["fileName"];
				} else {
					//echo "Das Element " . $recordingsArray["flvRecordingId"] . " enthält den Wert: " . $recordingsArray["fileName"] . "<br>";
					$recordings[$recordingsArray["flvRecordingId"]] = $recordingsArray["fileName"];
					break;
				}
			}


		}
			
		$mform->addElement('select', 'room_recording_id', get_string('recordings_show', 'openmeetings'), $recordings);

		/// Adding the "Number of Participants" field
		$mform->addElement('select', 'max_user', get_string('Max_User', 'openmeetings'), array('2'=>'2', '4'=>'4', '8'=>'8', '16'=>'16', '24'=>'24', '36'=>'36', '50'=>'50', '100'=>'100', '150'=>'150', '200'=>'200', '250'=>'250', '500'=>'500', '1000'=>'1000'));

		/// Adding the "Is Moderated Room" field
		$mform->addElement('select', 'is_moderated_room', get_string('Wait_for_teacher', 'openmeetings'), array('1'=> get_string('Moderation_TYPE_1', 'openmeetings'),'2' => get_string('Moderation_TYPE_2', 'openmeetings'),'3' => get_string('Moderation_TYPE_3', 'openmeetings')));

		$mform->addElement('select', 'allow_recording', get_string('Allow_Recording', 'openmeetings'), array('1'=> get_string('Recording_TYPE_2', 'openmeetings'),'2' => get_string('Recording_TYPE_2', 'openmeetings')));
			
			
		/// Adding the "Room Language" field
		$language_array = array ('1' => 'english',
									'2' => 'deutsch', 
									'3' => 'deutsch (studIP)', 
									'4' => 'french', 
									'5' => 'italian', 
									'6' => 'portugues', 
									'7' => 'portugues brazil', 
									'8' => 'spanish', 
									'9' => 'russian', 
									'10' => 'swedish', 
									'11' => 'chinese simplified', 
									'12' => 'chinese traditional', 
									'13' => 'korean', 
									'14' => 'arabic', 
									'15' => 'japanese', 
									'16' => 'indonesian', 
									'17' => 'hungarian', 
									'18' => 'turkish', 
									'19' => 'ukrainian', 
									'20' => 'thai', 
									'21' => 'persian', 
									'22' => 'czech', 
									'23' => 'galician', 
									'24' => 'finnish', 
									'25' => 'polish', 
									'26' => 'greek', 
									'27' => 'dutch', 
									'28' => 'hebrew', 
									'29' => 'catalan', 
									'30' => 'bulgarian', 
									'31' => 'danish', 
									'32' => 'slovak');
			
		$mform->addElement('select', 'language', get_string('Room_Language', 'openmeetings'), $language_array);

		/// Adding the optional "intro" and "introformat" pair of fields
		$mform->addElement('htmleditor', 'intro', get_string('Comment', 'openmeetings'));
		$mform->setType('intro', PARAM_RAW);
		//$mform->addRule('intro', get_string('required'), 'required', null, 'client');
		//$mform->setHelpButton('intro', array('writing', 'richtext'), false, 'editorhelpbutton');

		//$mform->addElement('format', 'introformat', get_string('format', 'openmeetings'));
		//$this->add_intro_editor(true, get_string('description', 'mplayer'));

		$this->add_intro_editor(true);

		//-------------------------------------------------------------------------------
		// add standard elements, common to all modules
		//$this->standard_coursemodule_elements(array('groups'=>true, 'groupings'=>true, 'groupmembersonly'=>true));
		$this->standard_coursemodule_elements();

		//-------------------------------------------------------------------------------
		// add standard buttons, common to all modules
		$this->add_action_buttons();

	}
}

?>