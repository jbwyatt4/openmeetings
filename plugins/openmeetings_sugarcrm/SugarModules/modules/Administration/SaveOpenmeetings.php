<?php
if(!defined('sugarEntry') || !sugarEntry) die('Not A Valid Entry Point');


//require_once('modules/Users/User.php');
require_once('modules/MySettings/TabController.php');

$display_tabs_def = urldecode($_REQUEST['display_tabs_def']);
$hide_tabs_def = urldecode($_REQUEST['hide_tabs_def']);
$remove_tabs_def = urldecode($_REQUEST['remove_tabs_def']);

$DISPLAY_ARR = array();
$HIDE_ARR = array();
$REMOVE_ARR = array();


parse_str($display_tabs_def,$DISPLAY_ARR);
parse_str($hide_tabs_def,$HIDE_ARR);
parse_str($remove_tabs_def,$REMOVE_ARR);

if (isset($_POST['record']) && !is_admin($current_user) && $_POST['record'] != $current_user->id) sugar_die("Unauthorized access to administration.");
elseif (!isset($_POST['record']) && !is_admin($current_user)) echo ("Unauthorized access to user administration.");

    
$openmeetings_url = $_REQUEST["openmeetings_url"];
$openmeetings_http_port = $_REQUEST["openmeetings_http_port"];
$openmeetings_username = $_REQUEST["openmeetings_username"];  
$openmeetings_password = $_REQUEST["openmeetings_password"]; 
$openmeetings_language = $_REQUEST["openmeetings_language"];


  if(is_admin($current_user)){  
 
 $sq = "update config set value='$openmeetings_url' where category='info' and name='openmeetings_url'";
global $db;
$result= $db->query($sq, true);

$sq = "update config set value='$openmeetings_http_port' where category='info' and name='openmeetings_http_port'";
global $db;
$result= $db->query($sq, true);


$sq = "update config set value='$openmeetings_username' where category='info' and name='openmeetings_username'";
global $db;
$result= $db->query($sq, true);

$sq = "update config set value='$openmeetings_password' where category='info' and name='openmeetings_password'";
global $db;
$result= $db->query($sq, true);

$sq = "update config set value='$openmeetings_language' where category='info' and name='openmeetings_language'";
global $db;
$result= $db->query($sq, true);


  
  }





// Flag to determine whether to save a new password or not.
if(isset($_REQUEST['return_module']) && $_REQUEST['return_module'] != "") $return_module = $_REQUEST['return_module'];
else $return_module = "Users";
if(isset($_REQUEST['return_action']) && $_REQUEST['return_action'] != "") $return_action = $_REQUEST['return_action'];
else $return_action = "DetailView";
if(isset($_REQUEST['return_id']) && $_REQUEST['return_id'] != "") $return_id = $_REQUEST['return_id'];

$GLOBALS['log']->debug("Saved record with id of ".$return_id);


$return_action = "DetailViewOpenmeetings";
$redirect = "index.php?action={$return_action}&module={$return_module}";
header("Location: {$redirect}");
?>
