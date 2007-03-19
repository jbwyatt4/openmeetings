package org.xmlcrm.test.basic;

import org.xmlcrm.app.data.basic.Fieldmanagment;

import junit.framework.TestCase;

public class StartUpLanguageFieldsConference extends TestCase {
	
	public StartUpLanguageFieldsConference(String testname){
		super(testname);
	}
	
	public void testStartUpLanguageFieldsConference(){
		
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
	
		
	}
	

}
