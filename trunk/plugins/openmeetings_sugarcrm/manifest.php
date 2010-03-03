<?php


	$manifest = array (
		 'acceptable_sugar_versions' => 
		  array (
	     	'5.1.0'
		  ),
		  'acceptable_sugar_flavors' =>
		  array(
		  	'CE', 'PRO','ENT'
		  ),
		  'readme'=>'',
		  'key'=>'openmeetings',
		  'author' => 'Eugen Schwert @ Webbase-Design.de',
		  'description' => 'Integrate Openmeeting in SugarCRM',
		  'icon' => '',
		  'is_uninstallable' => true,
		  'name' => 'openmeetings',
		  'published_date' => '2010-03-01 21:44:29',
		  'type' => 'module',
		  'version' => '1.0',
		  'remove_tables' => 'prompt',
          'silent' => 1, 
		  );
$installdefs = array (
  'id' => 'openmeetingsCE',
 
        'beans'=> array (
                 ), 
 
  'language' => 
  array ( 
  0 => 
    array (
      'from' => '<basepath>/SugarModules/include/language/en_us_openmeetings.lang.php',
      'to_module' => 'application',
      'language' => 'en_us',
    ),
  1 => 
    array (
      'from' => '<basepath>/SugarModules/modules/Meetings/language/en_us_openmeetings.lang.php',
      'to_module' => 'Meetings',
      'language' => 'en_us',
    ),
  2 => 
    array (
      'from' => '<basepath>/SugarModules/modules/Administration/language/en_us_openmeetings.lang.php',
      'to_module' => 'Administration',
      'language' => 'en_us',
    ),  
  3 => 
    array (
      'from' => '<basepath>/SugarModules/include/language/en_us_openmeetings.lang.php',
      'to_module' => 'application',
      'language' => 'en_us',
    ), 
  4 => 
    array (
      'from' => '<basepath>/SugarModules/modules/Home/language/en_us_openmeetings.lang.php',
      'to_module' => 'Home',
      'language' => 'en_us',
    ),
 ), 
  'menu'=> array( 
array('from'=> '<basepath>/SugarModules/modules/Administration/Ext/Menus/menu.ext.php',
'to_module'=> 'Administration', ), 
array('from'=> '<basepath>/SugarModules/modules/Meetings/Ext/Menus/menu.ext.php',
'to_module'=> 'Meetings', ),
array('from'=> '<basepath>/SugarModules/modules/Home/Ext/Menus/menu.ext.php',
'to_module'=> 'Home', ), 
),  
  'custom_fields' => 
  array (    
    'Meetingsis_openmeetings_c' => 
    array (
      'id' => 'Meetingsis_openmeetings_c',
      'name' => 'is_openmeetings_c',
      'label' => 'LBL_IS_OPENMEETINGS',
      'comments' => NULL,
      'help' => NULL,
      'module' => 'Meetings',
      'type' => 'bool',
      'max_size' => '255',
      'require_option' => '0',
      'default_value' => '0',
      'date_modified' => '2009-11-04 09:46:04',
      'deleted' => '0',
      'audited' => '0',
      'mass_update' => '0',
      'duplicate_merge' => '0',
      'reportable' => '0',
      'importable' => 'true',
      'ext1' => NULL,
      'ext2' => NULL,
      'ext3' => NULL,
      'ext4' => NULL,
    ),    
    'Openmeetings_roomname_c' => 
    array (
      'id' => 'Openmeetings_roomname_c',
      'name' => 'openmeetings_roomname_c',
      'label' => 'LBL_OPENMEETINGS_ROOMNAME',
      'comments' => NULL,
      'help' => NULL,
      'module' => 'Meetings',
      'type' => 'varchar',
      'max_size' => '245',
      'require_option' => '0',
      'default_value' => NULL,
      'date_modified' => '2009-11-02 08:53:12',
      'deleted' => '0',
      'audited' => '0',
      'mass_update' => '0',
      'duplicate_merge' => '0',
      'reportable' => '0',
      'importable' => 'true',
      'ext1' => NULL,
      'ext2' => NULL,
      'ext3' => NULL,
      'ext4' => NULL,
    ),
   'Openmeetings_roomid_c' => 
    array (
      'id' => 'Openmeetings_roomid_c',
      'name' => 'openmeetings_roomid_c',
      'label' => 'LBL_OPENMEETINGS_ROOMID',
      'comments' => NULL,
      'help' => NULL,
      'module' => 'Meetings',
      'type' => 'int',
      'max_size' => '11',
      'require_option' => '0',
      'default_value' => NULL,
      'date_modified' => '2009-11-06 08:53:12',
      'deleted' => '0',
      'audited' => '0',
      'mass_update' => '0',
      'duplicate_merge' => '0',
      'reportable' => '0',
      'importable' => 'true',
      'ext1' => NULL,
      'ext2' => NULL,
      'ext3' => NULL,
      'ext4' => NULL,
    ),	
   'Openmeetings_my_roomid_c' => 
    array (
      'id' => 'Openmeetings_my_roomid_c',
      'name' => 'openmeetings_my_roomid_c',
      'label' => 'LBL_OPENMEETINGS_MY_ROOMID',
      'comments' => NULL,
      'help' => NULL,
      'module' => 'Users',
      'type' => 'int',
      'max_size' => '11',
      'require_option' => '0',
      'default_value' => NULL,
      'date_modified' => '2009-11-06 08:53:12',
      'deleted' => '0',
      'audited' => '0',
      'mass_update' => '0',
      'duplicate_merge' => '0',
      'reportable' => '1',
      'importable' => 'true',
      'ext1' => NULL,
      'ext2' => NULL,
      'ext3' => NULL,
      'ext4' => NULL,
    ),	

  ),/*
'Openmeetings_language_c' => 
    array (
      'id' => 'Openmeetings_language_c',
      'name' => 'openmeetings_language_c',
      'label' => 'LBL_OPENMEETINGS_LANGUAGE',
      'comments' => NULL,
      'help' => NULL,
      'module' => 'Administration',
      'type' => 'enum',
      'max_size' => '255',
      'require_option' => '0',
      'default_value' => 'V',
      'date_modified' => '2009-11-05 09:30:60',
      'deleted' => '0',
      'audited' => '0',
      'mass_update' => '0',
      'duplicate_merge' => '0',
      'reportable' => '0',
      'importable' => 'true',
      'ext1' => '_list_dimdim_meet_type',
      'ext2' => NULL,
      'ext3' => NULL,
      'ext4' => NULL,
    ),*/
 'copy' => 
  array (
    0 => 
        array('from'=> '<basepath>/img/logo.png',
              'to'=> 'themes/default/images/logo.gif',
        ),
    1 => 
    array (
      'from' => '<basepath>/SugarModules/modules/Administration/DetailViewOpenmeetings.html',
      'to' => 'modules/Administration/DetailViewOpenmeetings.html',
    ),
    2 => 
    array (
      'from' => '<basepath>/SugarModules/modules/Administration/DetailViewOpenmeetings.php',
      'to' => 'modules/Administration/DetailViewOpenmeetings.php',
    ),
    3 => 
    array (
      'from' => '<basepath>/SugarModules/modules/Administration/EditViewOpenmeetings.php',
      'to' => 'modules/Administration/EditViewOpenmeetings.php',
    ),
    4 => 
    array (
      'from' => '<basepath>/SugarModules/modules/Administration/EditViewOpenmeetings.html',
      'to' => 'modules/Administration/EditViewOpenmeetings.html',
    ),
    5 => 
    array (
      'from' => '<basepath>/SugarModules/modules/Administration/SaveOpenmeetings.php',
      'to' => 'modules/Administration/SaveOpenmeetings.php',
    ),
    6 => 
    array (
      'from' => '<basepath>/openmeetings_gateway',
      'to' => 'modules/Openmeetings',
    ),
    7 => 
    array (
      'from' => '<basepath>/SugarModules/modules/Meetings/metadata',
      'to' => 'custom/modules/Meetings/metadata',
    ),
	8 =>
    array (
      'from' => '<basepath>/SugarModules/modules/Meetings/logic_hooks.php',
      'to' => 'custom/modules/Meetings/logic_hooks.php',
    ),
  ),
);
