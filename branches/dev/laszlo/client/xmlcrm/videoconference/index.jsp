<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>OpenMeetings</title>
	<script type="text/javascript" src="swfobject.js" ></script>   
</head>
<body align="center" valign="middle" align="center" onLoad="focusSWF()">

<div width="300" height="500" align="center" valign="middle" id="swfobject_view" class="swfobject_view" name="swfobject_viewDoc" style="background-color: #ffffff">
Please download the latest Flash Player<br />
<a href="http://www.macromedia.com/go/getflashplayer">http://www.macromedia.com/go/getflashplayer</a>
</div>

<script type="text/javascript">

// load instance of SWFObject
loadSWFObject("swfobject_view", "main.lzx?debug=true&lzr=swf8&lzt=swf", "lzapp", "1000", "700", "8.0.15", "#ffffff");

// write SWFObject into div container
function loadSWFObject(div_id, src, movie_id, w, h, v, bgcolor) {

var so = new SWFObject(src, movie_id, w, h, v, bgcolor);
             so.addParam("quality", "high");
             so.addParam("scale", "noscale");
             so.addParam("salign", "LT");
             so.addParam("id", "myMovie");
             so.addParam("name", movie_id); 
             so.addParam("allowScriptAccess", "always");
             so.write(div_id);

}	// end of SWFObject

function focusSWF(){

    if(navigator.plugins&&navigator.mimeTypes&&navigator.mimeTypes.length){
    }else {
        document.getElementById('lzapp').focus();
    }
}   
</script>
</body>
</html>