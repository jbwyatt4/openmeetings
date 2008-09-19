<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
      <link rel="SHORTCUT ICON" href="favicon.ico">
      <title>OpenMeetings</title>
	<script type="text/javascript" src="embed-compressed.js"></script>
   </head>
   <body onbeforeunload="return 'You will leave the meeting if you continue'">
  	    <script type="text/javascript">
              Lz.swfEmbed({url: 'main.lzx.lzr=swf8.swf', bgcolor: '#ffffff', width: '100%', height: '100%', id: 'lzapp', accessible: 'false', wmode: 'transparent'});
            </script>
   </body>
</html>



<!-- 

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

	<link rel="shortcut icon" href="favicon.ico"> 

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
		// <![CDATA[
		
		var so = new SWFObject("main.lzx.lzr=swf8.swf", "lzapp", "100%", "100%", "8", "#ffffff");
		so.addParam("quality", "high");
		so.addParam("id", "lzapp");
		so.addParam("allowScriptAccess", "always");
		so.addParam("scale", "noscale");
		so.addParam("vmode", "transparent" );
		so.write("flashcontent");
		
		function focusSWF(){
		
		    if(navigator.plugins&&navigator.mimeTypes&&navigator.mimeTypes.length){
		    }else {
		        document.getElementById('lzapp').focus();
		    }
		}   		
		
		// ]]>
	</script>	

</body>
</html>
-->