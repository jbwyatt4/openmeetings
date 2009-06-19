<?php
/*
 * Created on 13.05.2008
 *
 * To change the template for this generated file go to
 * Window - Preferences - PHPeclipse - PHP - Code Templates
 */
 
//echo $_GET["sid"]."<br/>";
//echo $_GET["roomid"]."<br/>";
//echo $_GET["language"]."<br/>";
//echo $_GET["red5host"]."<br/>";
//echo $_GET["red5httpPort"]."<br/>";

$openmeetings_swfURL = "http://".$_GET["red5host"].":".$_GET["red5httpPort"]."/openmeetings/main.lzx.swf8.swf?" .
		"roomid=".$_GET["roomid"] .
		"&sid=".$_GET["sid"] .
		"&language=".$_GET["language"] .
		"&picture=".$_GET["picture"] .
		"&user_id=".$_GET["user_id"] .
		"&wwwroot=".$_GET["wwwroot"] .
		"&moodleRoom=1";

/*
 * For Debugging
 */
$openmeetings_swfURL = "http://192.168.0.31:8080/lps-4.3.0/openmeetings/maindebug.lzx?lzt=swf&lzr=swf8&lzproxied=solo" .
		"&roomid=".$_GET["roomid"] .
		"&sid=".$_GET["sid"] .
		"&language=".$_GET["language"] .
		"&picture=".$_GET["picture"] .
		"&user_id=".$_GET["user_id"] .
		"&wwwroot=".$_GET["wwwroot"] .
		"&moodleRoom=1";
				
echo $openmeetings_swfURL;

?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>OpenMeetings</title>
	<script type="text/javascript" src="swfobject.js"></script>
	<style type="text/css">
		
		/* hide from ie on mac \*/
		html {
			height: 100%;
			overflow: hidden;
		}
		
		#flashcontent {
			height: 100%;
		}
		/* end hide */
	
		body {
			height: 100%;
			margin: 0;
			padding: 0;
			background-color: #ffffff;
		}
	
	</style>
</head>
<body align="center" valign="middle" align="center" onLoad="focusSWF()">


	<div id="flashcontent">
		<strong>You need to upgrade your Flash Player</strong>
		This is replaced by the Flash content. 
		Place your alternate content here and users without the Flash plugin or with 
		Javascript turned off will see this. Content here allows you to leave out <code>noscript</code> 
		tags.
	</div>
	
	<script type="text/javascript">		
			var so = new SWFObject("<?PHP echo $openmeetings_swfURL; ?>", "lzapp", "100%", "100%", "8", "#ffffff");
			so.addParam("quality", "high");
			so.addParam("id", "lzapp");
			so.addParam("allowScriptAccess", "always");
			so.addParam("scale", "noscale");
			so.write("flashcontent");
			
			function focusSWF(){
			    if(navigator.plugins&&navigator.mimeTypes&&navigator.mimeTypes.length){
			    }else {
			        document.getElementById('lzapp').focus();
			    }
			}   		

	</script>	


</body>
</html>