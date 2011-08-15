package org.openmeetings.test.init;

import org.junit.Test;
import org.openmeetings.test.AbstractOpenmeetingsSpringTest;

public class TestAddDefaultField extends AbstractOpenmeetingsSpringTest {

	@Test
	public void testaddDefaultField(){
		
//		fieldmanagment.addFourFieldValues("organisationtablelist_idrow", 164, "Organisations-ID", "Organisation-ID", "Organisation-ID", "Organisation-ID");
//		fieldmanagment.addFourFieldValues("organisationtablelist_namerow", 165, "Name", "name", "name", "name");
//		fieldmanagment.addFourFieldValues("uservalue_levelid1", 166, "Benutzer", "user", "user", "user");
//		fieldmanagment.addFourFieldValues("uservalue_levelid2", 167, "Moderator", "mod", "mod", "mod");
//		fieldmanagment.addFourFieldValues("uservalue_levelid3", 168, "Admin", "admin", "admin", "admin");
//		fieldmanagment.addFourFieldValues("uservalue_levellabel", 169, "Benuterrolle", "userlevel", "userlevel", "userlevel");
//		fieldmanagment.addFourFieldValues("orgvalue_header", 170, "Organisation", "organisation", "organisation", "organisation");
//		fieldmanagment.addFourFieldValues("orgvalue_orgname", 171, "Name", "name", "name", "name");
//		fieldmanagment.addFourFieldValues("orgvalue_orgname", 172, "Organisation hinzufuegen", "add organisation", "add organisation", "add organisation");
//		fieldmanagment.addFourFieldValues("orgvalue_orgname", 173, "Organisation hinzufuegen", "add organisation", "add organisation", "add organisation");
//		fieldmanagment.addFourFieldValues("orgvalue_userwin", 174, "abbrechen", "cancel", "cancel", "cancel");
//		fieldmanagment.addFourFieldValues("orgvalue_userwin", 175, "hinzufuegen", "add", "add", "add");
//		fieldmanagment.addFourFieldValues("orgvalue_userwin", 176, "Organisation entfernen", "remove organisation", "remove organisation", "remove organisation");
//		fieldmanagment.addFourFieldValues("orgvalue_userlist", 177, "Benutzer", "user", "user", "user");
//		fieldmanagment.addFourFieldValues("orgvalue_userlistadd", 178, "Benutzer hinzufuegen", "add user", "add user", "add user");
//		fieldmanagment.addFourFieldValues("orgvalue_userlistdelete", 179, "Benutzer entfernen", "delete user", "delete user", "delete user");
//		fieldmanagment.addFourFieldValues("orgvalue_userwinheader", 180, "Benutzer hinzufuegen", "add user", "add user", "add user");
//		fieldmanagment.addFourFieldValues("orgvalue_userwinsearchfield", 181, "Benutzer suchen", "search user", "search user", "search user");
//		fieldmanagment.addFourFieldValues("orgvalue_userwinsearchbtn", 182, "suchen", "search", "search", "search");
//		fieldmanagment.addFourFieldValues("orgvalue_userwinsearchresult", 183, "Benutzer", "user", "user", "user");
//		fieldmanagment.addFourFieldValues("loginwin_chooseorganisation", 184, "Organisation", "organisation", "organisation", "organisation");
//		fieldmanagment.addFourFieldValues("loginwin_chooseorganisationbtn", 185, "auswaehlen", "enter", "enter", "enter");
//		fieldmanagment.addFourFieldValues("navi_roomadmin", 186, "Konferenzraeume", "conferencerooms", "conferencerooms", "conferencerooms");
//		fieldmanagment.addFourFieldValues("roomadmin_header", 187, "Konferenzraeume", "Conferencerooms", "Conferencerooms", "Conferencerooms");
//		fieldmanagment.addFourFieldValues("roomadmin_header", 188, "ID", "id", "id", "id");
//		fieldmanagment.addFourFieldValues("roomadmin_header", 189, "Name", "Name", "Name", "Name");
//		fieldmanagment.addFourFieldValues("roomadmin_header", 190, "oeffentlich", "public", "public", "public");
//		fieldmanagment.addFourFieldValues("roomadmin_header", 191, "Organisationen", "organisations", "organisations", "organisations");
		
//		fieldmanagment.addFourFieldValues("roomadmin_header", 192, "Konferenzraeume", "Conferencerooms", "Conferencerooms", "Conferencerooms");
//		fieldmanagment.addFourFieldValues("roomvalue_name", 193, "Name", "name", "name", "name");
//		fieldmanagment.addFourFieldValues("roomvalue_type", 194, "Typ", "type", "type", "type");
//		fieldmanagment.addFourFieldValues("roomvalue_ispublic", 195, "oeffentlich", "public", "public", "public");
//		fieldmanagment.addFourFieldValues("roomvalue_comment", 196, "Kommentar", "comment", "comment", "comment");
//		fieldmanagment.addFourFieldValues("whiteboard_saveicon", 197, "Speichern", "save", "save", "save");
//		fieldmanagment.addFourFieldValues("whiteboard_openicon", 198, "oeffnen", "load", "load", "load");
//		fieldmanagment.addFourFieldValues("whiteboard_saveaswinheader", 199, "Speichern unter", "save as", "save as", "save as");
//		fieldmanagment.addFourFieldValues("whiteboard_saveaswintext", 200, "Dateiname", "filename", "filename", "filename");
//		fieldmanagment.addFourFieldValues("whiteboard_saveaswintext", 201, "Dateiname", "filename", "filename", "filename");
//		fieldmanagment.addFourFieldValues("whiteboard_saveaswinbtn1", 202, "Abbrechen", "cancel", "cancel", "cancel");
//		fieldmanagment.addFourFieldValues("whiteboard_saveaswinbtn2", 203, "Speichern", "save", "save", "save");
//		fieldmanagment.addFourFieldValues("rpcerrorwin_header", 204, "Fehler", "error", "error", "error");
//		fieldmanagment.addFourFieldValues("loadwml_header", 205, "Laden", "loading", "loading", "loading");
//		fieldmanagment.addFourFieldValues("loadwml_messsload", 206, "Objekte geladen", "objects loaded", "objects loaded", "objects loaded");
//		fieldmanagment.addFourFieldValues("loadwml_messsync", 207, "Synchronisiere Clients. Restliche Clients: ", "snychronizing clients, clients to wait:", "snychronizing clients, clients to wait:", "snychronizing clients, clients to wait:");
//		fieldmanagment.addFourFieldValues("loadimage_messload", 208, "Lade Bilddaten", "Loading Imagedata", "Loading Imagedata", "Loading Imagedata");
//		fieldmanagment.addFourFieldValues("loadimage_messsync", 209, "Synchronisiere Clients. Restliche Clients: ", "snychronizing clients, clients to wait:", "snychronizing clients, clients to wait:", "snychronizing clients, clients to wait:");
//		fieldmanagment.addFourFieldValues("loadwml_confirmheader", 210, "Zeichenbrett leeren", "clear drawarea", "clear drawarea", "clear drawarea");
//		fieldmanagment.addFourFieldValues("loadwml_confirmmess", 211, "Zeichnbrett leeren, alle bisherigen aenderungen gehen damit verloren!", "clear drawarea, all data on whiteboard will be lost", "clear drawarea, all data on whiteboard will be lost", "clear drawarea, all data on whiteboard will be lost");
//		fieldmanagment.addFourFieldValues("loadwml_confirmmess2", 212, "Bestaetigung anfordern vor dem Laden einer Datei", "Confirm before loading a file", "Confirm before loading a file", "Confirm before loading a file");
//		fieldmanagment.addFourFieldValues("send_invitation_btn", 213, "Einladung versenden", "Send invitation", "Send invitation", "Send invitation");
//		fieldmanagment.addFourFieldValues("send_invitationwin_header", 214, "Einladung versenden", "Send invitation", "Send invitation", "Send invitation");
//		fieldmanagment.addFourFieldValues("send_invitationwin_subject", 215, "Betreff", "Subject", "Subject", "Subject");
//		fieldmanagment.addFourFieldValues("send_invitationwin_recipient", 216, "Empfaenger", "Recipient", "Recipient", "Recipient");
//		fieldmanagment.addFourFieldValues("send_invitationwin_message", 217, "Nachricht", "Message", "Message", "Message");
//		fieldmanagment.addFourFieldValues("send_invitationwin_btn_confirm", 218, "abschicken", "Send", "Send", "Send");
//		fieldmanagment.addFourFieldValues("send_invitationwin_btn_cancel", 219, "abbrechen", "cancel", "cancel", "cancel");
//		fieldmanagment.addFourFieldValues("send_chat_text_btn", 220, "senden", "send", "appeler", "emitir");
//		fieldmanagment.addFourFieldValues("invited_userwin_header", 221, "Benutzerdaten", "Userdata", "userdata", "userdata");
//		fieldmanagment.addFourFieldValues("invited_userwin_subject", 222, "Nickname fuer diese Konference", "Your nickname for this conference", "Your nickname for this conference", "Your nickname for this conference");
//		fieldmanagment.addFourFieldValues("invited_userwin_login", 223, "Spitzname/Alias", "nick", "nick", "nick");
//		fieldmanagment.addFourFieldValues("invited_userwin_firstname", 224, "Vorname", "firstname", "firstname", "firstname");
//		fieldmanagment.addFourFieldValues("invited_userwin_lastname", 225, "Nachname", "lastname", "lastname", "lastname");
//		fieldmanagment.addFourFieldValues("invited_userwin_mail", 226, "EMail", "email", "email", "email");
//		fieldmanagment.addFourFieldValues("invited_userwin_lang", 227, "Sprache", "language", "language", "language");
//		fieldmanagment.addFourFieldValues("invited_userwin_enter", 228, "Absenden", "enter", "enter", "enter");
		
	}

}
