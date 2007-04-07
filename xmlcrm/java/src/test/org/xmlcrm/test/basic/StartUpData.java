package org.xmlcrm.test.basic;

import org.xmlcrm.app.data.basic.Configurationmanagement;
import org.xmlcrm.app.data.basic.Fieldmanagment;
import org.xmlcrm.app.data.basic.Languagemanagement;
import org.xmlcrm.app.data.basic.Navimanagement;
import org.xmlcrm.app.data.user.Organisationmanagement;
import org.xmlcrm.app.data.user.Statemanagement;
import org.xmlcrm.app.data.user.Salutationmanagement;
import org.xmlcrm.app.data.user.Usermanagement;

import junit.framework.TestCase;

public class StartUpData extends TestCase {
	
	public StartUpData (String testname){
		super(testname);
	}
	
	public void testGenerateBasicNavi(){
		
		Languagemanagement.getInstance().addLanguage("deutsch");
		Languagemanagement.getInstance().addLanguage("english");
		Languagemanagement.getInstance().addLanguage("french");		
		Languagemanagement.getInstance().addLanguage("spanish");
		
		Fieldmanagment.getInstance().addField("conference");
		Fieldmanagment.getInstance().addFieldValueByFieldAndLanguage(1, 1, "Konferenz");
		Fieldmanagment.getInstance().addFieldValueByFieldAndLanguage(1, 2, "Confernce");
		Fieldmanagment.getInstance().addFieldValueByFieldAndLanguage(1, 3, "Conférence");
		Fieldmanagment.getInstance().addFieldValueByFieldAndLanguage(1, 4, "Conferencia");
		
		Fieldmanagment.getInstance().addField("meeting");
		Fieldmanagment.getInstance().addFieldValueByFieldAndLanguage(2, 1, "Meeting");
		Fieldmanagment.getInstance().addFieldValueByFieldAndLanguage(2, 2, "Meeting");
		Fieldmanagment.getInstance().addFieldValueByFieldAndLanguage(2, 3, "Meeting");
		Fieldmanagment.getInstance().addFieldValueByFieldAndLanguage(2, 4, "Encuentro");
		
		Fieldmanagment.getInstance().addField("classroom");
		Fieldmanagment.getInstance().addFieldValueByFieldAndLanguage(3, 1, "Auditorium");
		Fieldmanagment.getInstance().addFieldValueByFieldAndLanguage(3, 2, "Auditorium");
		Fieldmanagment.getInstance().addFieldValueByFieldAndLanguage(3, 3, "Auditorium");
		Fieldmanagment.getInstance().addFieldValueByFieldAndLanguage(3, 4, "Auditorio");

		Fieldmanagment.getInstance().addField("settings");
		Fieldmanagment.getInstance().addFieldValueByFieldAndLanguage(4, 1, "Einstellungen");
		Fieldmanagment.getInstance().addFieldValueByFieldAndLanguage(4, 2, "Settings");
		Fieldmanagment.getInstance().addFieldValueByFieldAndLanguage(4, 3, "Paramètres");
		Fieldmanagment.getInstance().addFieldValueByFieldAndLanguage(4, 4, "Calibración");

		Fieldmanagment.getInstance().addField("benutzer");
		Fieldmanagment.getInstance().addFieldValueByFieldAndLanguage(5, 1, "Benutzer");
		Fieldmanagment.getInstance().addFieldValueByFieldAndLanguage(5, 2, "User");
		Fieldmanagment.getInstance().addFieldValueByFieldAndLanguage(5, 3, "Client");
		Fieldmanagment.getInstance().addFieldValueByFieldAndLanguage(5, 4, "Usuario");
		
		Fieldmanagment.getInstance().addField("admin");
		Fieldmanagment.getInstance().addFieldValueByFieldAndLanguage(6, 1, "Benutzer");
		Fieldmanagment.getInstance().addFieldValueByFieldAndLanguage(6, 2, "User");
		Fieldmanagment.getInstance().addFieldValueByFieldAndLanguage(6, 3, "Client");
		Fieldmanagment.getInstance().addFieldValueByFieldAndLanguage(6, 4, "Usuario");	
		
		Usermanagement.getInstance().addUserLevel("User", 1);
		
		Usermanagement.getInstance().addUserLevel("Moderator", 2);
		
		Usermanagement.getInstance().addUserLevel("Admin", 3);	
		
		Navimanagement.getInstance().addGlobalStructure("conf", 1, 1, false, true, 1, "conference");
		Navimanagement.getInstance().addMainStructure("conf1", 1, 2, true, false, 1, "meeting", 1);
		Navimanagement.getInstance().addMainStructure("conf2", 2, 3, true, false, 1, "classroom", 1);
		
		Navimanagement.getInstance().addGlobalStructure("settings", 2, 4, false, true, 1, "setings");
		Navimanagement.getInstance().addMainStructure("userself", 1, 5, true, false, 1, "userself",2);
		
		Navimanagement.getInstance().addGlobalStructure("admin", 3, 6, false, true, 1, "admin");

		Salutationmanagement.getInstance().addUserSalutation("Herr");
		Salutationmanagement.getInstance().addUserSalutation("Frau");
		
		//TODO: Load States from seperate XML-File
		Statemanagement.getInstance().addState("Deutschland");
		Statemanagement.getInstance().addState("France");
		Statemanagement.getInstance().addState("Italy");
		Statemanagement.getInstance().addState("Spain");
		Statemanagement.getInstance().addState("USA");
		Statemanagement.getInstance().addState("United Kingdom");
		Statemanagement.getInstance().addState("Ireland");
		Statemanagement.getInstance().addState("Danemark");
		
		//Add user
		long user_id = Usermanagement.getInstance().registerUserInit(new Long(3),3, 1, 1, 
				"swagner", "test", "lastname", "firstname", 
				"seba.wagner@gmail.com", 0, "street", "no", "fax", 
				"zip", 1, "town", 0);
		
		//Add default group
		long organisation_id = Organisationmanagement.getInstance().addOrganisation("default", user_id);
		
		//Add user to default group
		long organisation_usersid = Organisationmanagement.getInstance().addUserToOrganisation(1, organisation_id, 1,"");
		
		Configurationmanagement.getInstance().addConfByKey(3, "allow_frontend_register", "1", 1, "");
		
		Configurationmanagement.getInstance().addConfByKey(3, "default_group_id", "1", 1, "");
		
		//this domain_id is the Organisation of users who register through the frontend
		Configurationmanagement.getInstance().addConfByKey(3, "default_domain_id", "1", 1, "");
		
		Configurationmanagement.getInstance().addConfByKey(3, "smtp_server", "smtp.xmlcrm.org", 1, "this is the smtp server to send messages");
		Configurationmanagement.getInstance().addConfByKey(3, "system_email_addr", "openmeetings@xmlcrm.org", 1, "all send EMails by the system will have this address");
		Configurationmanagement.getInstance().addConfByKey(3, "email_username", "openmeetings@xmlcrm.org", 1, "System auth email username");
		Configurationmanagement.getInstance().addConfByKey(3, "email_userpass", "tony123", 1, "System auth email password");		
			
		Configurationmanagement.getInstance().addConfByKey(3, "default_lang", "EN", 1, "Default System Language for tamplates");
		Configurationmanagement.getInstance().addConfByKey(3, "register_mail_subject", "SignUp", 1, "The Subject for Mails sended at registration");
				
		//Todo: Load default language ID from Database
		
	}
}
