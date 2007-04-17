package org.xmlcrm.test.basic;

import org.xmlcrm.app.data.basic.Fieldmanagment;
import org.xmlcrm.app.data.basic.Languagemanagement;

import junit.framework.TestCase;

public class StartUpLanguageFieldsConference extends TestCase {
	
	public StartUpLanguageFieldsConference(String testname){
		super(testname);
	}
	
	public void testStartUpLanguageFieldsConference(){
		
		Languagemanagement.getInstance().addLanguage("deutsch");
		Languagemanagement.getInstance().addLanguage("english");
		Languagemanagement.getInstance().addLanguage("french");		
		Languagemanagement.getInstance().addLanguage("spanish");		
		
		Fieldmanagment.getInstance().addFourFieldValues("conference", 1, "Konferenz", "Confernce", "Conférence", "Conferencia");
		Fieldmanagment.getInstance().addFourFieldValues("meeting", 2, "Meeting", "Meeting", "Meeting", "Encuentro");
		Fieldmanagment.getInstance().addFourFieldValues("classroom", 3, "Auditorium", "Auditorium", "Auditorium", "Auditorio");
		Fieldmanagment.getInstance().addFourFieldValues("settings", 4, "Einstellungen", "Settings", "Paramètres", "Calibración");
		Fieldmanagment.getInstance().addFourFieldValues("benutzer", 5, "Benutzer", "User", "Client", "Usuario");
		Fieldmanagment.getInstance().addFourFieldValues("admin", 6, "Administration", "Administration", "Administration", "Administration");
		Fieldmanagment.getInstance().addFourFieldValues("stop", 7, "Stop", "Stop", "Stop", "Stop");
		Fieldmanagment.getInstance().addFourFieldValues("record", 8, "Record", "Record", "Record", "Record");
		Fieldmanagment.getInstance().addFourFieldValues("nofile", 9, "Keine Datei vorhanden", "Keine Datei vorhanden", "Keine Datei vorhanden", "Keine Datei vorhanden");
		Fieldmanagment.getInstance().addFourFieldValues("recordbyteacher", 10, "Aufnahme nur für Lehrer verfügbar", "Aufnahme nur für Lehrer verfügbar", "Aufnahme nur für Lehrer verfügbar", "Aufnahme nur für Lehrer verfügbar");
		Fieldmanagment.getInstance().addFourFieldValues("connectedusers", 11, "verbunden Benutzer:", "verbunden Benutzer:", "verbunden Benutzer:", "verbunden Benutzer:");
		Fieldmanagment.getInstance().addFourFieldValues("startconf", 12, "Konferenz starten", "Konferenz starten", "Konferenz starten", "Konferenz starten");
		Fieldmanagment.getInstance().addFourFieldValues("myname", 13, "Mein Name", "Mein Name", "Mein Name", "Mein Name");
		Fieldmanagment.getInstance().addFourFieldValues("videoconference", 14, "VideoConference", "VideoConference", "VideoConference", "VideoConference");
		Fieldmanagment.getInstance().addFourFieldValues("import", 15, "Präsentation importieren", "Präsentation importieren", "Präsentation importieren", "Präsentation importieren");
		Fieldmanagment.getInstance().addFourFieldValues("refreshfiles", 16, "Liste neu laden", "Liste neu laden", "Liste neu laden", "Liste neu laden");
		Fieldmanagment.getInstance().addFourFieldValues("tomainfile", 17, "Zum Hauptverzeichnis", "Zum Hauptverzeichnis", "Zum Hauptverzeichnis", "Zum Hauptverzeichnis");
		Fieldmanagment.getInstance().addFourFieldValues("newpoll", 18, "neue Umfrage", "neue Umfrage", "neue Umfrage", "neue Umfrage");
		Fieldmanagment.getInstance().addFourFieldValues("newpollheader", 19, "Eine neue Umfrage für die Konferenz.", "Eine neue Umfrage für die Konferenz.", "Eine neue Umfrage für die Konferenz.", "Eine neue Umfrage für die Konferenz.");
		Fieldmanagment.getInstance().addFourFieldValues("question", 20, "Frage:", "Frage:", "Frage:", "Frage:");
		Fieldmanagment.getInstance().addFourFieldValues("polltype", 21, "Umfragenart:", "Umfragenart:", "Umfragenart:", "Umfragenart:");
		Fieldmanagment.getInstance().addFourFieldValues("create", 22, "anlegen", "anlegen", "anlegen", "anlegen");
		Fieldmanagment.getInstance().addFourFieldValues("infomessage", 23, "Info: Jeder verbundene Benutzer erhält eine Nachricht mit der neuen Umfrage.", "Info: Jeder verbundene Benutzer erhält eine Nachricht mit der neuen Umfrage.", "Info: Jeder verbundene Benutzer erhält eine Nachricht mit der neuen Umfrage.", "Info: Jeder verbundene Benutzer erhält eine Nachricht mit der neuen Umfrage.");
		Fieldmanagment.getInstance().addFourFieldValues("creatpoll", 24, "Umfrage anlegen", "Umfrage anlegen", "Umfrage anlegen", "Umfrage anlegen");
		Fieldmanagment.getInstance().addFourFieldValues("cancel", 25, "abbrechen", "abbrechen", "abbrechen", "abbrechen");
		Fieldmanagment.getInstance().addFourFieldValues("yesno", 26, "Ja/Nein", "Ja/Nein", "Ja/Nein", "Ja/Nein");
		Fieldmanagment.getInstance().addFourFieldValues("numeric", 27, "Numerisch 1-10", "Numerisch 1-10", "Numerisch 1-10", "Numerisch 1-10");
		Fieldmanagment.getInstance().addFourFieldValues("poll", 28, "Umfrage", "Umfrage", "Umfrage", "Umfrage");
		Fieldmanagment.getInstance().addFourFieldValues("moderation", 29, "Sie müssen Moderator/Lehrer in diesem Raum sein um eine Umfrage anzulegen.", "Sie müssen Moderator/Lehrer in diesem Raum sein um eine Umfrage anzulegen.", "Sie müssen Moderator/Lehrer in diesem Raum sein um eine Umfrage anzulegen.", "Sie müssen Moderator/Lehrer in diesem Raum sein um eine Umfrage anzulegen.");
		Fieldmanagment.getInstance().addFourFieldValues("vote", 30, "Ihr Stimme wurde abgegeben.", "Ihr Stimme wurde abgegeben.", "Ihr Stimme wurde abgegeben.", "Ihr Stimme wurde abgegeben.");
		Fieldmanagment.getInstance().addFourFieldValues("alreadyvoted", 31, "Sie haben für diese Umfrage bereits ihr Votum abgegeben.", "Sie haben für diese Umfrage bereits ihr Votum abgegeben.", "Sie haben für diese Umfrage bereits ihr Votum abgegeben.", "Sie haben für diese Umfrage bereits ihr Votum abgegeben.");
		Fieldmanagment.getInstance().addFourFieldValues("voting", 32, "abstimmen!", "abstimmen!", "abstimmen!", "abstimmen!");
		Fieldmanagment.getInstance().addFourFieldValues("answer", 33, "Ihre Antwort:", "Ihre Antwort:", "Ihre Antwort:", "Ihre Antwort:");
		Fieldmanagment.getInstance().addFourFieldValues("yes", 34, "Ja", "Ja", "Ja", "Ja");
		Fieldmanagment.getInstance().addFourFieldValues("no", 35, "Nein", "Nein", "Nein", "Nein");
		Fieldmanagment.getInstance().addFourFieldValues("questionwant", 36, "will wissen:", "will wissen:", "will wissen:", "will wissen:");
		Fieldmanagment.getInstance().addFourFieldValues("pollresults", 37, "Umfrageergebnisse", "Umfrageergebnisse", "Umfrageergebnisse", "Umfrageergebnisse");
		Fieldmanagment.getInstance().addFourFieldValues("question", 38, "Frage:", "Frage:", "Frage:", "Frage:");
		Fieldmanagment.getInstance().addFourFieldValues("results", 39, "Antworten:", "Antworten:", "Antworten:", "Antworten:");
		Fieldmanagment.getInstance().addFourFieldValues("answers", 40, "Ergebnis:", "Ergebnis:", "Ergebnis:", "Ergebnis:");
		Fieldmanagment.getInstance().addFourFieldValues("nopoll", 41, "Es gibt zur Zeit keine Umfage.", "Es gibt zur Zeit keine Umfage.", "Es gibt zur Zeit keine Umfage.", "Es gibt zur Zeit keine Umfage.");
		Fieldmanagment.getInstance().addFourFieldValues("votings", 42, "abstimmen!", "abstimmen!", "abstimmen!", "abstimmen!");
		Fieldmanagment.getInstance().addFourFieldValues("meeting", 43, "Meeting (max 4 Plätze)", "Meeting (max 4 Plätze)", "Meeting (max 4 Plätze)", "Meeting (max 4 Plätze)");
		Fieldmanagment.getInstance().addFourFieldValues("conference", 44, "Conference (max 50 Plätze)", "Conference (max 50 Plätze)", "Conference (max 50 Plätze)", "Conference (max 50 Plätze)");
		Fieldmanagment.getInstance().addFourFieldValues("type", 45, "Modus", "Modus", "Modus", "Modus");
		Fieldmanagment.getInstance().addFourFieldValues("remainingseats", 46, "verbleibende Plätze", "verbleibende Plätze", "verbleibende Plätze", "verbleibende Plätze");
		Fieldmanagment.getInstance().addFourFieldValues("alreadychosen", 47, "Bereits vergeben", "Bereits vergeben", "Bereits vergeben", "Bereits vergeben");
		Fieldmanagment.getInstance().addFourFieldValues("enter", 48, "Eintreten", "Eintreten", "Eintreten", "Eintreten");
		Fieldmanagment.getInstance().addFourFieldValues("modleave", 49, "Der Moderator/Lehrer dieses Raums hat den Raum verlassen.", "Der Moderator/Lehrer dieses Raums hat den Raum verlassen.", "Der Moderator/Lehrer dieses Raums hat den Raum verlassen.", "Der Moderator/Lehrer dieses Raums hat den Raum verlassen.");
		Fieldmanagment.getInstance().addFourFieldValues("systemmessage", 50, "Systemnachricht", "Systemnachricht", "Systemnachricht", "Systemnachricht");
		Fieldmanagment.getInstance().addFourFieldValues("chossedevice", 51, "Geräteauswahl", "Geräteauswahl", "Geräteauswahl", "Geräteauswahl");
		Fieldmanagment.getInstance().addFourFieldValues("choosecam", 52, "Kamera wählen:", "Kamera wählen:", "Kamera wählen:", "Kamera wählen:");
		Fieldmanagment.getInstance().addFourFieldValues("choosemic", 53, "Mikrophon wählen:", "Mikrophon wählen:", "Mikrophon wählen:", "Mikrophon wählen:");
		Fieldmanagment.getInstance().addFourFieldValues("ok", 54, "ok", "ok", "ok", "ok");
		Fieldmanagment.getInstance().addFourFieldValues("cancel2", 55, "abbrechen", "abbrechen", "abbrechen", "abbrechen");
		Fieldmanagment.getInstance().addFourFieldValues("reconeectneeded", 56, "Sie müssen sich erneut verbinden damit die Änderungen wirksam werden.", "Sie müssen sich erneut verbinden damit die Änderungen wirksam werden.", "Sie müssen sich erneut verbinden damit die Änderungen wirksam werden.", "Sie müssen sich erneut verbinden damit die Änderungen wirksam werden.");
		Fieldmanagment.getInstance().addFourFieldValues("editsetup", 57, "Einstellungen ändern.", "Einstellungen ändern.", "Einstellungen ändern.", "Einstellungen ändern.");
		Fieldmanagment.getInstance().addFourFieldValues("course", 58, "Kurs:", "Kurs:", "Kurs:", "Kurs:");
		Fieldmanagment.getInstance().addFourFieldValues("language", 59, "Kurssprache:", "Kurssprache:", "Kurssprache:", "Kurssprache:");
		Fieldmanagment.getInstance().addFourFieldValues("ok2", 60, "ok", "ok", "ok", "ok");
		Fieldmanagment.getInstance().addFourFieldValues("cancel3", 61, "abbrechen", "abbrechen", "abbrechen", "abbrechen");
		Fieldmanagment.getInstance().addFourFieldValues("clearwhiteboard", 62, "Zeichenbrett leeren", "Zeichenbrett leeren", "Zeichenbrett leeren", "Zeichenbrett leeren");
		Fieldmanagment.getInstance().addFourFieldValues("clearwhiteboardquestion", 63, "Soll dass Zeichenbrett geleert werden bevor ein neues Bild hinzugefügt wird?", "Soll dass Zeichenbrett geleert werden bevor ein neues Bild hinzugefügt wird?", "Soll dass Zeichenbrett geleert werden bevor ein neues Bild hinzugefügt wird?", "Soll dass Zeichenbrett geleert werden bevor ein neues Bild hinzugefügt wird?");
		Fieldmanagment.getInstance().addFourFieldValues("dontaskagain", 64, "Nicht nochmal fragen", "Nicht nochmal fragen", "Nicht nochmal fragen", "Nicht nochmal fragen");
		Fieldmanagment.getInstance().addFourFieldValues("no", 65, "nein", "nein", "nein", "nein");
		Fieldmanagment.getInstance().addFourFieldValues("editsetup2", 66, "Einstellungen bearbeiten", "Einstellungen bearbeiten", "Einstellungen bearbeiten", "Einstellungen bearbeiten");
		Fieldmanagment.getInstance().addFourFieldValues("needconfirmationwhiteboard", 67, "Bestätigung anfordern bevor das Zeichenbrett geleert wird.", "Bestätigung anfordern bevor das Zeichenbrett geleert wird.", "Bestätigung anfordern bevor das Zeichenbrett geleert wird.", "Bestätigung anfordern bevor das Zeichenbrett geleert wird.");
		Fieldmanagment.getInstance().addFourFieldValues("userinfo", 68, "User Info", "User Info", "User Info", "User Info");
		Fieldmanagment.getInstance().addFourFieldValues("cleardrawarea", 69, "Clear DrawArea", "Clear DrawArea", "Clear DrawArea", "Clear DrawArea");
		Fieldmanagment.getInstance().addFourFieldValues("undo", 70, "Undo", "Undo", "Undo", "Undo");
		Fieldmanagment.getInstance().addFourFieldValues("redo", 71, "Redo", "Redo", "Redo", "Redo");
		Fieldmanagment.getInstance().addFourFieldValues("selectobject", 72, "Select an Object", "Select an Object", "Select an Object", "Select an Object");
		Fieldmanagment.getInstance().addFourFieldValues("text", 73, "Text", "Text", "Text", "Text");
		Fieldmanagment.getInstance().addFourFieldValues("paint", 74, "Paint", "Paint", "Paint", "Paint");
		Fieldmanagment.getInstance().addFourFieldValues("drawline", 75, "Draw line", "Draw line", "Draw line", "Draw line");
		Fieldmanagment.getInstance().addFourFieldValues("drawu", 76, "Draw underline", "Draw underline", "Draw underline", "Draw underline");
		Fieldmanagment.getInstance().addFourFieldValues("rect", 77, "Rectangle", "Rectangle", "Rectangle", "Rectangle");
		Fieldmanagment.getInstance().addFourFieldValues("ellipse", 78, "Ellipse", "Ellipse", "Ellipse", "Ellipse");
		Fieldmanagment.getInstance().addFourFieldValues("arrow", 79, "Arrow", "Arrow", "Arrow", "Arrow");
		Fieldmanagment.getInstance().addFourFieldValues("deletechosen", 80, "delete chosen Item", "delete chosen Item", "delete chosen Item", "delete chosen Item");
		Fieldmanagment.getInstance().addFourFieldValues("appliymod", 81, "Apply for moderation", "Apply for moderation", "Apply for moderation", "Apply for moderation");
		Fieldmanagment.getInstance().addFourFieldValues("apply", 82, "apply", "apply", "apply", "apply");
		Fieldmanagment.getInstance().addFourFieldValues("cancel", 83, "cancel", "cancel", "cancel", "cancel");
		Fieldmanagment.getInstance().addFourFieldValues("mod", 84, "Become moderator", "Become moderator", "Become moderator", "Become moderator");
		Fieldmanagment.getInstance().addFourFieldValues("close", 85, "close", "close", "close", "close");
		Fieldmanagment.getInstance().addFourFieldValues("italic", 86, "italic", "italic", "italic", "italic");
		Fieldmanagment.getInstance().addFourFieldValues("bold", 87, "bold", "bold", "bold", "bold");
		Fieldmanagment.getInstance().addFourFieldValues("waiting", 88, "WAITING", "WAITING", "WAITING", "WAITING");
		Fieldmanagment.getInstance().addFourFieldValues("applyMessage", 89, "A User wants to apply for moderation:", "A User wants to apply for moderation:", "A User wants to apply for moderation:", "A User wants to apply for moderation:");
		Fieldmanagment.getInstance().addFourFieldValues("accept", 90, "accept", "accept", "accept", "accept");
		Fieldmanagment.getInstance().addFourFieldValues("reject", 91, "reject", "reject", "reject", "reject");
		Fieldmanagment.getInstance().addFourFieldValues("cancel", 92, "cancel", "cancel", "cancel", "cancel");
		Fieldmanagment.getInstance().addFourFieldValues("sendmodrequestmessage", 93, "Sending request to following Users", "Sending request to following Users", "Sending request to following Users", "Sending request to following Users");
		Fieldmanagment.getInstance().addFourFieldValues("accept", 94, "Accepted", "Accepted", "Accepted", "Accepted");
		Fieldmanagment.getInstance().addFourFieldValues("reject", 95, "Rejected", "Rejected", "Rejected", "Rejected");
		Fieldmanagment.getInstance().addFourFieldValues("changemod", 96, "Change Moderator", "Change Moderator", "Change Moderator", "Change Moderator");
		Fieldmanagment.getInstance().addFourFieldValues("nonmoderrormessage", 97, "You are not moderating this course!", "You are not moderating this course!", "You are not moderating this course!", "You are not moderating this course!");
		Fieldmanagment.getInstance().addFourFieldValues("moderator", 98, "Moderator:", "Moderator:", "Moderator:", "Moderator:");
		Fieldmanagment.getInstance().addFourFieldValues("roomfullmessage", 99, "This Room is full. Sorry please try again later.", "This Room is full. Sorry please try again later.", "This Room is full. Sorry please try again later.", "This Room is full. Sorry please try again later.");
		Fieldmanagment.getInstance().addFourFieldValues("elllipse", 100, "Ellipse", "Ellipse", "Ellipse", "Ellipse");
		Fieldmanagment.getInstance().addFourFieldValues("close", 101, "close", "close", "close", "close");
		Fieldmanagment.getInstance().addFourFieldValues("AuthError", 102, "Eingabefehler", "input data error", "input data error", "input data error");
		Fieldmanagment.getInstance().addFourFieldValues("min4username", 103, "Der Benutzername muss mindestens 4 Zeichen lang sein", "username must be 4 characters at least", "username must be 4 characters at least", "username must be 4 characters at least");
		Fieldmanagment.getInstance().addFourFieldValues("min4pass", 104, "Das Passwort muss mindestens 4 Zeichen lang sein", "userpass must be 4 characters at least", "userpass must be 4 characters at least", "userpass must be 4 characters at least");
		Fieldmanagment.getInstance().addFourFieldValues("usernametaken", 105, "Der Benutzername ist bereits vergeben", "The username is already taken", "The username is already taken", "The username is already taken");
		Fieldmanagment.getInstance().addFourFieldValues("emailtaken", 106, "Die EMail ist bereits registriert", "The email is already registered", "The email is already registered", "The email is already registered");
		Fieldmanagment.getInstance().addFourFieldValues("emailtaken", 107, "Ein Fehler wurde geworfen bitte kontaktieren Sie das Admin Team", "System error please contact System-Admins", "System error please contact System-Admins", "System error please contact System-Admins");
		Fieldmanagment.getInstance().addFourFieldValues("Authlogin", 108, "Login", "Login", "Login", "Login");
		Fieldmanagment.getInstance().addFourFieldValues("Authuser", 109, "Benutzer:", "User:", "User:", "User:");
		Fieldmanagment.getInstance().addFourFieldValues("Authpass", 110, "Pass:", "Pass:", "Pass:", "Pass:");
		Fieldmanagment.getInstance().addFourFieldValues("Authlang", 111, "Language", "Language", "Language", "Language");
		Fieldmanagment.getInstance().addFourFieldValues("Authreg", 112, "Login", "Login", "Login", "Login");
		Fieldmanagment.getInstance().addFourFieldValues("regformhead", 113, "Sign Up", "Sign Up", "Sign Up", "Sign Up");
		Fieldmanagment.getInstance().addFourFieldValues("regformuser", 114, "Benutzer:", "User:", "User:", "User:");
		Fieldmanagment.getInstance().addFourFieldValues("regformpass", 115, "Pass:", "Pass:", "Pass:", "Pass:");
		Fieldmanagment.getInstance().addFourFieldValues("regformretype", 116, "Wiederhole:", "ReType:", "ReType:", "ReType:");
		Fieldmanagment.getInstance().addFourFieldValues("regformfirstname", 117, "Vorname:", "Firstname:", "Firstname:", "Firstname:");
		Fieldmanagment.getInstance().addFourFieldValues("regformlastname", 118, "Nachname:", "Lastname:", "Lastname:", "Lastname:");
		Fieldmanagment.getInstance().addFourFieldValues("regformmail", 119, "EMail:", "Mail:", "Mail:", "Mail:");
		Fieldmanagment.getInstance().addFourFieldValues("regformstate", 120, "Land:", "Country:", "Country:", "Country:");
		Fieldmanagment.getInstance().addFourFieldValues("regformbtn1", 121, "Registrieren", "Register", "Register", "Register");
		Fieldmanagment.getInstance().addFourFieldValues("regformbtn2", 122, "Abbrechen", "Cancel", "Cancel", "Cancel");
		Fieldmanagment.getInstance().addFourFieldValues("Authbtn2", 123, "Register", "Register", "Register", "Register");
		Fieldmanagment.getInstance().addFourFieldValues("dashboard", 124, "Home", "home", "home", "home");
		Fieldmanagment.getInstance().addFourFieldValues("useradmin", 125, "Benutzer", "Users", "Users", "Users");
		Fieldmanagment.getInstance().addFourFieldValues("groupadmin", 126, "Gruppen", "Groups", "Groups", "groups");
		Fieldmanagment.getInstance().addFourFieldValues("orgadmin", 127, "Organisationen", "Organisations", "Organisations", "Organisations");
		Fieldmanagment.getInstance().addFourFieldValues("headconf", 128, "Konferenzräume", "Conference-Rooms", "Conference-Rooms", "Conference-Rooms");
		Fieldmanagment.getInstance().addFourFieldValues("conf_pub", 129, "öffentlich", "public", "public", "public");
		Fieldmanagment.getInstance().addFourFieldValues("head_org", 130, "Organisation", "organisation", "organisation", "organisation");
		Fieldmanagment.getInstance().addFourFieldValues("btn_enterroom", 131, "betreten", "enter", "enter", "enter");
		Fieldmanagment.getInstance().addFourFieldValues("useralterself_login", 132, "Benutzer", "Login", "Login", "Login");
		Fieldmanagment.getInstance().addFourFieldValues("useralterself_pass", 133, "Passwort", "Password", "Password", "Password");
		Fieldmanagment.getInstance().addFourFieldValues("useralterself_passretype", 134, "Wiederhole", "Retype", "Retype", "Retype");
		Fieldmanagment.getInstance().addFourFieldValues("useralterself_firstname", 135, "Vorname", "Firstname", "Firstname", "Firstname");
		Fieldmanagment.getInstance().addFourFieldValues("useralterself_lastname", 136, "Nachname", "Lastname", "Lastname", "Lastname");
		Fieldmanagment.getInstance().addFourFieldValues("useralterself_email", 137, "EMail", "Mail", "Mail", "Mail");
		Fieldmanagment.getInstance().addFourFieldValues("useralterself_birth", 138, "Geburtstag", "Birthday", "Birthday", "Brithday");
		Fieldmanagment.getInstance().addFourFieldValues("useralterself_streetno", 139, "Straße/Nr.", "Street/No", "Street/No", "Street/No");
		Fieldmanagment.getInstance().addFourFieldValues("useralterself_town", 140, "PLZ/Stadt", "ZIP/Town", "ZIP/Town", "ZIP/Town");
		Fieldmanagment.getInstance().addFourFieldValues("useralterself_state", 141, "Land", "Country", "Country", "Country");
		Fieldmanagment.getInstance().addFourFieldValues("useralterself_adresscomment", 142, "Adressinfo", "Adress-Info", "Adress-Info", "Adress-Info");
		Fieldmanagment.getInstance().addFourFieldValues("useralterself_header", 143, "Benutzerdaten", "Userdata", "Userdata", "Userdata");
		Fieldmanagment.getInstance().addFourFieldValues("savemenubar_savelabel", 144, "speichern", "save", "save", "save");
		Fieldmanagment.getInstance().addFourFieldValues("savemenubar_savelabel", 145, "Speichervorgang", "Save", "Save", "Save");
		Fieldmanagment.getInstance().addFourFieldValues("admin_userlist_user_id", 146, "User-ID", "USER-ID", "USER-ID", "USER-ID");
		Fieldmanagment.getInstance().addFourFieldValues("admin_userlist_login", 147, "Login", "login", "login", "login");
		Fieldmanagment.getInstance().addFourFieldValues("admin_userlist_firstnam", 148, "Vorname", "firstname", "firstname", "firstname");
		Fieldmanagment.getInstance().addFourFieldValues("admin_userlist_lastname", 149, "Nachname", "lastname", "lastname", "lastname");
		Fieldmanagment.getInstance().addFourFieldValues("turnoverlist_next", 150, "vorwärts", "show next", "show next", "show next");
		Fieldmanagment.getInstance().addFourFieldValues("turnoverlist_pre", 151, "zurück", "show pre", "show pre", "show pre");
		
		
	}
	

}
