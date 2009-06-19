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
    	$mform->addElement('select', 'type', get_string('Room Type', 'openmeetings'), array('1'=>'Conference Room', '2'=>'Audience Room'));
        $mform->addRule('type', null, 'required', null, 'client');
        
    /// Adding the "Number of Participants" field
    	$mform->addElement('select', 'max_user', get_string('Max User', 'openmeetings'), array('2'=>'2', '4'=>'4', '8'=>'8', '16'=>'16', '24'=>'24', '36'=>'36', '50'=>'50', '100'=>'100', '200'=>'200', '500'=>'500', '1000'=>'1000'));
        $mform->addRule('max_user', null, 'required', null, 'client');
        
    /// Adding the "Is Moderated Room" field
    	$mform->addElement('select', 'is_moderated_room', get_string('Wait for teacher', 'openmeetings'), array('1'=>'Participants need to wait till the teacher enters the room','2' => 'Participants can already start (first User in Room becomes Moderator)'));
        $mform->addRule('is_moderated_room', null, 'required', null, 'client');
        
    /// Adding the "Room Language" field
    	$language_array = array ('1' => 'english',
	 							'2' => 'deutsch',
								'3' => 'french', 
								'4' => 'italian', 
								'5' => 'portugues', 
								'6' => 'spanish', 
								'7' => 'russian', 
								'8' => 'swedish', 
								'9' => 'chinese simplified', 
								'10' => 'chinese traditional', 
								'11' => 'korean', 
								'12' => 'arabic', 
								'13' => 'japanese', 
								'14' => 'indonesian', 
								'15' => 'hungarian', 
							    '16' => 'turkish', 
							    '17' => 'ukrainian', 
							    '18' => 'thai', 
							    '19' => 'persian', 
							    '20' => 'czech', 
							    '21' => 'galician');
    
    	$mform->addElement('select', 'language', get_string('Room Language', 'openmeetings'), $language_array);
        $mform->addRule('language', null, 'required', null, 'client');
            
    /// Adding the optional "intro" and "introformat" pair of fields
        $mform->addElement('htmleditor', 'intro', get_string('Comment', 'openmeetings'));
        $mform->setType('intro', PARAM_RAW);
        $mform->addRule('intro', get_string('required'), 'required', null, 'client');
        $mform->setHelpButton('intro', array('writing', 'richtext'), false, 'editorhelpbutton');

        $mform->addElement('format', 'introformat', get_string('format'));

//-------------------------------------------------------------------------------
        // add standard elements, common to all modules
        $this->standard_coursemodule_elements();
//-------------------------------------------------------------------------------
        // add standard buttons, common to all modules
        $this->add_action_buttons();

    }
}

?>