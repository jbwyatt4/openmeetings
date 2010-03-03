<?php
if(!defined('sugarEntry') || !sugarEntry) die('Not A Valid Entry Point');


require_once('include/DetailView/DetailView.php');
require_once('include/export_utils.php');
require_once('include/timezone/timezones.php');
require_once('include/utils.php');
//require_once('modules/Users/User.php');
require_once('modules/Administration/Administration.php');

global $current_user;
global $theme;
global $app_strings;
global $mod_strings;
global $timezones;
 if (file_exists("custom/modules/Administration/Ext/Language/en_us.lang.ext.php")){
require_once("custom/modules/Administration/Ext/Language/en_us.lang.ext.php");
}
if (file_exists("custom/modules/Administration/Ext/Language/ge_ge.lang.ext.php")){
require_once("custom/modules/Administration/Ext/Language/ge_ge.lang.ext.php");
}
#if (!is_admin($current_user) && ($_REQUEST['record'] != $current_user->id)) sugar_die("Unauthorized access to administration.");

 

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
######################   OPENMEETINGS PORT

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


####################### OPENMEETINGS PASSWORD ##############################################
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
 # echo "aaa";
  #        exit;
$detailView = new DetailView();
$offset=0;
#if (isset($_REQUEST['offset']) || !empty($_REQUEST['record'])) {
	#$result = $detailView->processSugarBean("Administration", $focus, $offset);
	#if($result == null) {
	#    sugar_die($app_strings['ERROR_NO_RECORD']);
	#}
	#$focus=$result;
#} else {
#	header("Location: index.php?module=Administration&action=index");
#}


echo "\n<p>\n";
echo get_module_title($mod_strings['LBL_MODULE_NAME'], $mod_strings['LBL_MODULE_NAME'].": ".$focus->full_name." (".$focus->user_name.")", true);
echo "\n</p>\n";
global $theme;
global $app_list_strings;
$theme_path="themes/".$theme."/";
$image_path=$theme_path."images/";
require_once($theme_path.'layout_utils.php');


$GLOBALS['log']->info("Administration detail view");

$xtpl=new XTemplate ('modules/Administration/DetailViewOpenmeetings.html');
$xtpl->assign("MOD", $mod_strings);
$xtpl->assign("APP", $app_strings);

$xtpl->assign("THEME", $theme);
$xtpl->assign("GRIDLINE", $gridline);
$xtpl->assign("IMAGE_PATH", $image_path);$xtpl->assign("PRINT_URL", "index.php?".$GLOBALS['request_string']);
$xtpl->assign("ID", $focus->id);
$xtpl->assign("USER_NAME", $focus->user_name);
$xtpl->assign("FULL_NAME", $focus->full_name);

 $xtpl->assign('OPENMEETINGS_URL', $openmeetings_url);
 $xtpl->assign('OPENMEETINGS_HTTP_PORT', $openmeetings_http_port);  


///////////////////////////////////////////////////////////////////////////////
////	TO SUPPORT LEGACY XTEMPLATES
$xtpl->assign('FIRST_NAME', $focus->first_name);
$xtpl->assign('LAST_NAME', $focus->last_name);
////	END SUPPORT LEGACY XTEMPLATES
///////////////////////////////////////////////////////////////////////////////


require_once('modules/DynamicFields/templates/Files/DetailView.php');

if ($openmeetings_language == 1){
	$om_language = "englisch";
}
if ($openmeetings_language == 2){
	$om_language = "deutsch";
}
if ($openmeetings_language == 3){
	$om_language = "french";
}
if ($openmeetings_language == 4){
	$om_language = "italian";
}
if ($openmeetings_language == 5){
	$om_language = "portugues";
}
if ($openmeetings_language == 6){
	$om_language = "portugues brazil";
}
if ($openmeetings_language == 7){
	$om_language = "spanish";
}
if ($openmeetings_language == 8){
	$om_language = "russian";
}
if ($openmeetings_language == 9){
	$om_language = "swedish";
}
if ($openmeetings_language == 10){
	$om_language = "chinese simplified";
}
if ($openmeetings_language == 11){
	$om_language = "chinese traditional";
}
if ($openmeetings_language == 12){
	$om_language = "korean";
}
if ($openmeetings_language == 13){
	$om_language = "arabic";
}
if ($openmeetings_language == 14){
	$om_language = "japanese";
}
if ($openmeetings_language == 15){
	$om_language = "indonesian";
}
if ($openmeetings_language == 16){
	$om_language = "hungarian";
}
if ($openmeetings_language == 17){
	$om_language = "turkish";
}
if ($openmeetings_language == 18){
	$om_language = "ukrainian";
}
if ($openmeetings_language == 19){
	$om_language = "thai";
}
if ($openmeetings_language == 20){
	$om_language = "persian";
}
if ($openmeetings_language == 21){
	$om_language = "czech";
}
if ($openmeetings_language == 22){
	$om_language = "galician";
}
if ($openmeetings_language == 23){
	$om_language = "finnish";
}
if ($openmeetings_language == 24){
	$om_language = "polish";
}
if ($openmeetings_language == 25){
	$om_language = "greek";
}
if ($openmeetings_language == 26){
	$om_language = "dutch";
}
if ($openmeetings_language == 27){
	$om_language = "hebrew";
}


   
$xtpl->assign('OPENMEETINGS_USERNAME', $openmeetings_username);         
$xtpl->assign('OPENMEETINGS_LANGUAGE', $om_language);
   
$xtpl->parse("user_info.tabchooser");


$xtpl->parse("main");
$xtpl->out("main");


$xtpl->parse("user_info.layoutopts");
$xtpl->parse("user_info");
$xtpl->out("user_info");

 

echo "</td></tr>\n";

require_once('modules/SavedSearch/SavedSearch.php');
$savedSearch = new SavedSearch();
$json = getJSONobj();
$savedSearchSelects = $json->encode(array($GLOBALS['app_strings']['LBL_SAVED_SEARCH_SHORTCUT'] . '<br>' . $savedSearch->getSelect('Users')));
$str = "<script>
YAHOO.util.Event.addListener(window, 'load', SUGAR.util.fillShortcuts, $savedSearchSelects);
</script>";
echo $str;

?>
