<?php

function displayInvitation()
{
	$file = file_get_contents('./invite.tmpl', true);
	print $file;
}

function displayUserPage()
{
	$file = file_get_contents('./user.tmpl', true);

	$file = str_replace( "%USERID%",$_REQUEST['UID'],$file);
	$file = str_replace( "%USERNAME%",$_REQUEST['UN'],$file);
	print $file;
}

if ($_REQUEST['UID']==NULL)
{
	displayInvitation();
}
else
{
	displayUserPage();
}
?>
