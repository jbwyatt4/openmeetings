<?php 

 

if(!defined('sugarEntry') || !sugarEntry) die('Not A Valid Entry Point'); 

global $mod_strings;
global $current_user;
if (file_exists("custom/modules/Administration/Ext/Language/en_us.lang.ext.php")){
require_once("custom/modules/Administration/Ext/Language/en_us.lang.ext.php");
}
if (file_exists("custom/modules/Administration/Ext/Language/ge_ge.lang.ext.php")){
require_once("custom/modules/Administration/Ext/Language/ge_ge.lang.ext.php");
}

$guserid=$_REQUEST['record'];

        $module_menu[] = 
    Array("index.php?module=Administration&action=DetailViewOpenmeetings", $mod_strings['OPENMEETINGS_LINK_EDIT'],"logo");

?>
