<?php
if(!defined('sugarEntry') || !sugarEntry) die('Not A Valid Entry Point');


require_once('XTemplate/xtpl.php');

require_once('include/export_utils.php');
require_once('modules/Users/User.php');
require_once('modules/Users/Forms.php');
require_once('modules/Users/UserSignature.php');
require_once('modules/Administration/Administration.php');
require_once('include/javascript/javascript.php');   

$admin = new Administration();
$admin->retrieveSettings("notify");

global $app_strings;
global $app_list_strings;
global $mod_strings;
  if (file_exists("custom/modules/Users/Ext/Language/en_us.lang.ext.php")){
require_once("custom/modules/Users/Ext/Language/en_us.lang.ext.php");
}
if (file_exists("custom/modules/Users/Ext/Language/ge_ge.lang.ext.php")){
require_once("custom/modules/Users/Ext/Language/ge_ge.lang.ext.php");
}
$admin = new Administration();
$admin->retrieveSettings();


if(!is_admin($current_user) ) sugar_die("Unauthorized access to administration.");
 

##########################         OPENMEETINGS ADDRESS

 $sq = "select * from config where category='info' and name='openmeetings_url'";
global $db;
$result= $db->query($sq, true);
              while($row = $db->fetchByAssoc($result))
            {
                $openmeetings_url = $row['value'];
           }
     if  ($openmeetings_url==""){
        $sq = "insert into config (category,name,value) values ('info','openmeetings_url','localhost')";
  $result= $db->query($sq, 1);   
  $openmeetings_url='localhost';        
         }  
###################### OPENMEETINGS PORT
$sq = "select * from config where category='info' and name='openmeetings_http_port'";
global $db;
$result= $db->query($sq, true);
              while($row = $db->fetchByAssoc($result))
            {
                $openmeetings_http_port = $row['value'];
           }
     if  ($openmeetings_http_port==""){
        $sq = "insert into config (category,name,value) values ('info','openmeetings_http_port','5080')";
  $result= $db->query($sq, 1);   
  $openmeetings_http_port='5080';        
         }  
######################

####################### OPENMEETINGS USERNAME ##############################################
$sq = "select * from config where category='info' and name='openmeetings_username'";
global $db;
$result= $db->query($sq, true);
              while($row = $db->fetchByAssoc($result))
            {
                $openmeetings_username = $row['value'];
           }
     if  ($openmeetings_username==""){
        $sq = "insert into config (category,name,value) values ('info','openmeetings_username','admin')";
  $result= $db->query($sq, 1);   
  $openmeetings_username='admin';        
         }  


####################### OPENMEETINGS USEPASS ##############################################
$sq = "select * from config where category='info' and name='openmeetings_password'";
global $db;
$result= $db->query($sq, true);
              while($row = $db->fetchByAssoc($result))
            {
                $openmeetings_password = $row['value'];
           }
     if  ($openmeetings_password==""){
        $sq = "insert into config (category,name,value) values ('info','openmeetings_password','****')";
  $result= $db->query($sq, 1);   
  $openmeetings_password='admin';        
         }  


####################### OPENMEETINGS LANGUAGE ##############################################
$sq = "select * from config where category='info' and name='openmeetings_language'";
global $db;
$result= $db->query($sq, true);
              while($row = $db->fetchByAssoc($result))
            {
                $openmeetings_language = $row['value'];
           }
     if  ($openmeetings_language==""){
        $sq = "insert into config (category,name,value) values ('info','openmeetings_language','1')";
  $result= $db->query($sq, 1);   
  $openmeetings_language='1';        
         }  
############################################################################################## 
           
$focus = new User();


if(isset($_REQUEST['record'])) {
    $focus->retrieve($_REQUEST['record']);
}
if(isset($_REQUEST['isDuplicate']) && $_REQUEST['isDuplicate'] == 'true') {
	$focus->id = "";
	$focus->user_name = "";
}
echo "\n<p>\n";
echo get_module_title($mod_strings['LBL_MODULE_NAME'], $mod_strings['LBL_MODULE_NAME'].": ".$focus->first_name." ".$focus->last_name."(".$focus->user_name.")", true);
echo "\n</p>\n";
global $theme;
$theme_path='themes/'.$theme.'/';
$image_path=$theme_path.'images/';
require_once($theme_path.'layout_utils.php');

$GLOBALS['log']->info('Administration edit view');
$xtpl=new XTemplate('modules/Administration/EditViewOpenmeetings.html');
$xtpl->assign('MOD', $mod_strings);
$xtpl->assign('APP', $app_strings);

if(isset($_REQUEST['error_string'])) $xtpl->assign('ERROR_STRING', '<span class="error">Error: '.$_REQUEST['error_string'].'</span>');
if(isset($_REQUEST['return_module'])) $xtpl->assign('RETURN_MODULE', $_REQUEST['return_module']);
if(isset($_REQUEST['return_action'])) $xtpl->assign('RETURN_ACTION', "DetailViewOpenmeetings");
if(isset($_REQUEST['return_id'])) $xtpl->assign('RETURN_ID', $_REQUEST['return_id']);
else { $xtpl->assign('RETURN_ACTION', 'ListView'); }

$xtpl->assign('JAVASCRIPT', get_set_focus_js().user_get_validate_record_js().user_get_chooser_js().user_get_confsettings_js().'<script type="text/javascript" language="Javascript" src="modules/Users/User.js"></script>');
$xtpl->assign('IMAGE_PATH', $image_path);$xtpl->assign('PRINT_URL', 'index.php?'.$GLOBALS['request_string']);
$xtpl->assign('ID', $focus->id);
$xtpl->assign('USER_NAME', $focus->user_name);


$xtpl->assign('OPENMEETINGS_USERNAME', $openmeetings_username);     
$xtpl->assign('OPENMEETINGS_PASSWORD', $openmeetings_password);     
//$xtpl->assign('OPENMEETINGS_LANGUAGE', $openmeetings_language);

$IS_ENGLISH = "";
if ($openmeetings_language == 1) {
	$IS_ENGLISH = "selected";
}
$xtpl->assign('IS_ENGLISH', $IS_ENGLISH);

$IS_GERMAN = "";
if ($openmeetings_language == "2") {
	$IS_GERMAN = "selected";
}
$xtpl->assign('IS_GERMAN', $IS_GERMAN);

$IS_FRENCH = "";
if ($openmeetings_language == "3") {
	$IS_FRENCH = "selected";
}
$xtpl->assign('IS_FRENCH', $IS_FRENCH);

$IS_ITALIAN = "";
if ($openmeetings_language == "4") {
	$IS_ITALIAN = "selected";
}
$xtpl->assign('IS_ITALIAN', $IS_ITALIAN);

$IS_PORTUGUES = "";
if ($openmeetings_language == "5") {
	$IS_PORTUGUES = "selected";
}
$xtpl->assign('IS_PORTUGUES', $IS_PORTUGUES);

$IS_PORTUGUES_BRAZIL = "";
if ($openmeetings_language == "6") {
	$IS_PORTUGUES_BRAZIL = "selected";
}
$xtpl->assign('IS_PORTUGUES_BRAZIL', $IS_PORTUGUES_BRAZIL);

$IS_SPANISH = "";
if ($openmeetings_language == "7") {
	$IS_SPANISH = "selected";
}
$xtpl->assign('IS_SPANISH', $IS_SPANISH);

$IS_RUSSIAN = "";
if ($openmeetings_language == "8") {
	$IS_RUSSIAN = "selected";
}
$xtpl->assign('IS_RUSSIAN', $IS_RUSSIAN);

$IS_SWEDISH = "";
if ($openmeetings_language == "9") {
	$IS_SWEDISH = "selected";
}
$xtpl->assign('IS_SWEDISH', $IS_SWEDISH);

$IS_SHINESE_SIM = "";
if ($openmeetings_language == "10") {
	$IS_SHINESE_SIM = "selected";
}
$xtpl->assign('IS_SHINESE_SIM', $IS_SHINESE_SIM);

$IS_SHINESE_TRAD = "";
if ($openmeetings_language == "11") {
	$IS_SHINESE_TRAD = "selected";
}
$xtpl->assign('IS_SHINESE_TRAD', $IS_SHINESE_TRAD);

$IS_KOREAN = "";
if ($openmeetings_language == "12") {
	$IS_KOREAN = "selected";
}
$xtpl->assign('IS_KOREAN', $IS_KOREAN);

$IS_ARABIC = "";
if ($openmeetings_language == "13") {
	$IS_ARABIC = "selected";
}
$xtpl->assign('IS_ARABIC', $IS_ARABIC);

$IS_JAPANESE = "";
if ($openmeetings_language == "14") {
	$IS_JAPANESE = "selected";
}
$xtpl->assign('IS_JAPANESE', $IS_JAPANESE);

$IS_INDONESIAN = "";
if ($openmeetings_language == "15") {
	$IS_INDONESIAN = "selected";
}
$xtpl->assign('IS_INDONESIAN', $IS_INDONESIAN);

$IS_HUNGARIAN = "";
if ($openmeetings_language == "16") {
	$IS_HUNGARIAN = "selected";
}
$xtpl->assign('IS_HUNGARIAN', $IS_HUNGARIAN);

$IS_TRUKISH = "";
if ($openmeetings_language == "17") {
	$IS_TRUKISH = "selected";
}
$xtpl->assign('IS_TRUKISH', $IS_TRUKISH);

$IS_UKRAINIAN = "";
if ($openmeetings_language == "18") {
	$IS_UKRAINIAN = "selected";
}
$xtpl->assign('IS_UKRAINIAN', $IS_UKRAINIAN);

$IS_THAI = "";
if ($openmeetings_language == "19") {
	$IS_THAI = "selected";
}
$xtpl->assign('IS_THAI', $IS_THAI);

$IS_PERSIAN = "";
if ($openmeetings_language == "20") {
	$IS_PERSIAN = "selected";
}
$xtpl->assign('IS_PERSIAN', $IS_PERSIAN);

$IS_CZECH = "";
if ($openmeetings_language == "21") {
	$IS_CZECH = "selected";
}
$xtpl->assign('IS_CZECH', $IS_CZECH);

$IS_GALICIAN = "";
if ($openmeetings_language == "22") {
	$IS_GALICIAN = "selected";
}
$xtpl->assign('IS_GALICIAN', $IS_GALICIAN);

$IS_FINNNISH = "";
if ($openmeetings_language == "23") {
	$IS_FINNNISH = "selected";
}
$xtpl->assign('IS_FINNNISH', $IS_FINNNISH);

$IS_POLISH = "";
if ($openmeetings_language == "24") {
	$IS_POLISH = "selected";
}
$xtpl->assign('IS_POLISH', $IS_POLISH);

$IS_GREEK = "";
if ($openmeetings_language == "25") {
	$IS_GREEK = "selected";
}
$xtpl->assign('IS_GREEK', $IS_GREEK);


$IS_DUTCH = "";
if ($openmeetings_language == "26") {
	$IS_DUTCH = "selected";
}
$xtpl->assign('IS_DUTCH', $IS_DUTCH);


$IS_HEBREW = "";
if ($openmeetings_language == "27") {
	$IS_HEBREW = "selected";
}
$xtpl->assign('IS_DUTCH', $IS_HEBREW);


$xtpl->assign('LBL_OPENMEETINGS_LANGUAGE', $openmeetings_language);
$xtpl->assign('OPENMEETINGS_URL', $openmeetings_url); 
$xtpl->assign('OPENMEETINGS_HTTP_PORT', $openmeetings_http_port); 
  

if(!is_admin($current_user)){
    $xtpl->assign('OPENMEETINGS_URL_DISABLED', 'disabled');   
} 
   
if(!is_admin($current_user)){
    $xtpl->assign('ALLOWUSERS_STATUS', 'disabled');   
}  


 
   $xtpl->parse('main');  
$xtpl->out('main');
    
                                 



?>
