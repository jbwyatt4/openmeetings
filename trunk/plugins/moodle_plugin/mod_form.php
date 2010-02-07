<?php //$Id,v 1.0 2007/10/24 12:00:00 Serafim Panov Exp $ 

require_once ('moodleform_mod.php');

class mod_openmeetings_mod_form extends moodleform_mod {

    function definition() {

        global $COURSE;
        $mform    =& $this->_form;

//-------------------------------------------------------------------------------
    /// Adding the "general" fieldset, where all the common settings are showed
        $mform->addElement('header', 'general', get_string('general', 'form'));
    /// Adding the standard "name" field
        $mform->addElement('text', 'name', get_string('Room Name', 'openmeetings'), array('size'=>'64'));
        $mform->setType('name', PARAM_TEXT);
        $mform->addRule('name', null, 'required', null, 'client');
        
    /// Adding the "Room Type" field
    	$mform->addElement('select', 'type', get_string('Room Type', 'openmeetings'), array('1'=>'Conference Room', '2'=>'Audience Room', '3'=>'Restricted Room', '0'=>'Show Recording'));
    
    /// Some description
    	 $mform->addElement('static', 'description_room_recording_id', get_string('The Recording Field is only used if Room Type is Recording', 'openmeetings'), null);
        
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
    	
    
    	$mform->addElement('select', 'room_recording_id', get_string('Available Recordings to Shows', 'openmeetings'), $recordings);
        
    /// Adding the "Number of Participants" field
    	$mform->addElement('select', 'max_user', get_string('Max User', 'openmeetings'), array('2'=>'2', '4'=>'4', '8'=>'8', '16'=>'16', '24'=>'24', '36'=>'36', '50'=>'50', '100'=>'100', '200'=>'200', '500'=>'500', '1000'=>'1000'));
        
    /// Adding the "Is Moderated Room" field
    	$mform->addElement('select', 'is_moderated_room', get_string('Wait for teacher', 'openmeetings'), array('1'=>'Participants need to wait till the teacher enters the room','2' => 'Participants can already start (first User in Room becomes Moderator)'));
        
    /// Adding the "Room Language" field
    	$language_array = array ('1' => 'english',
	 							'2' => 'deutsch',
								'3' => 'french', 
								'4' => 'italian', 
								'5' => 'portugues', 
								'6' => 'portugues brazil',
								'7' => 'spanish', 
								'8' => 'russian', 
								'9' => 'swedish', 
								'10' => 'chinese simplified', 
								'11' => 'chinese traditional', 
								'12' => 'korean', 
								'13' => 'arabic', 
								'14' => 'japanese', 
								'15' => 'indonesian', 
								'16' => 'hungarian', 
							    '17' => 'turkish', 
							    '18' => 'ukrainian', 
							    '19' => 'thai', 
							    '20' => 'persian', 
							    '21' => 'czech', 
							    '22' => 'galician', 
							    '23' => 'finnish', 
							    '24' => 'polish', 
							    '25' => 'greek',
							    '26' => 'dutch',
							    '27' => 'hebrew');
    
    	$mform->addElement('select', 'language', get_string('Room Language', 'openmeetings'), $language_array);
            
    /// Adding the optional "intro" and "introformat" pair of fields
        $mform->addElement('htmleditor', 'intro', get_string('Comment', 'openmeetings'));
        $mform->setType('intro', PARAM_RAW);
        //$mform->addRule('intro', get_string('required'), 'required', null, 'client');
        $mform->setHelpButton('intro', array('writing', 'richtext'), false, 'editorhelpbutton');

        $mform->addElement('format', 'introformat', get_string('format'));

	//-------------------------------------------------------------------------------
        // add standard elements, common to all modules
        $this->standard_coursemodule_elements(array('groups'=>true, 'groupings'=>true, 'groupmembersonly'=>true));
        
	//-------------------------------------------------------------------------------
        // add standard buttons, common to all modules
        $this->add_action_buttons();

    }
}

?>