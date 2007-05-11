package org.xmlcrm.app.installation;

import org.xmlcrm.app.data.basic.Configurationmanagement;
import org.xmlcrm.app.data.basic.Navimanagement;
import org.xmlcrm.app.data.user.Organisationmanagement;
import org.xmlcrm.app.data.user.Salutationmanagement;
import org.xmlcrm.app.data.user.Usermanagement;

public class ImportInitvalues {
	
	private static ImportInitvalues instance;

	private ImportInitvalues() {}

	public static synchronized ImportInitvalues getInstance() {
		if (instance == null) {
			instance = new ImportInitvalues();
		}
		return instance;
	}	
	
	public void loadMainMenu(){
		Usermanagement.getInstance().addUserLevel("User", 1);
		Usermanagement.getInstance().addUserLevel("Moderator", 2);
		Usermanagement.getInstance().addUserLevel("Admin", 3);	
		
		Navimanagement.getInstance().addGlobalStructure("home", 1, 124, true, true, 1, "home");
		
		Navimanagement.getInstance().addGlobalStructure("conf", 2, 1, false, true, 1, "conference");
		Navimanagement.getInstance().addMainStructure("conf1", 1, 2, true, true, 1, "meeting", 2);
		Navimanagement.getInstance().addMainStructure("conf2", 2, 3, true, true, 1, "classroom", 2);
		
		Navimanagement.getInstance().addGlobalStructure("settings", 3, 4, false, true, 1, "setings");
		Navimanagement.getInstance().addMainStructure("userself", 1, 5, true, false, 1, "userself",3);
		Navimanagement.getInstance().addMainStructure("roomconfiguremod", 2, 192, true, false, 1, "roomconfiguremod",3);
		
		Navimanagement.getInstance().addGlobalStructure("admin", 4, 6, false, true, 2, "admin");
		Navimanagement.getInstance().addMainStructure("useradmin", 1, 125, true, false, 2, "useradmin",4);
		Navimanagement.getInstance().addMainStructure("groupadmin", 1, 126, true, false, 2, "groupadmin",4);
		Navimanagement.getInstance().addMainStructure("orgadmin", 1, 127, true, false, 3, "orgadmin",4);
		Navimanagement.getInstance().addMainStructure("roomadmin", 1, 186, true, false, 3, "roomadmin",4);
			
	}
	
	public void loadSalutations(){
		
		Salutationmanagement.getInstance().addUserSalutation("Herr");
		Salutationmanagement.getInstance().addUserSalutation("Frau");
		
	}
	
	public void loadConfiguration(String allowfrontendRegister, String smtpServer, String referer, String mailauthname, 
			String mailauthpass, String default_lang){
		
		//"1"
		Configurationmanagement.getInstance().addConfByKey(3, "allow_frontend_register", allowfrontendRegister, null, "");
		
		Configurationmanagement.getInstance().addConfByKey(3, "default_group_id", "1", null, "");
		
		//this domain_id is the Organisation of users who register through the frontend
		Configurationmanagement.getInstance().addConfByKey(3, "default_domain_id", "1", null, "");
		
		//"smtp.xmlcrm.org"
		Configurationmanagement.getInstance().addConfByKey(3, "smtp_server", smtpServer, null, "this is the smtp server to send messages");
		//"openmeetings@xmlcrm.org"
		Configurationmanagement.getInstance().addConfByKey(3, "system_email_addr", referer, null, "all send EMails by the system will have this address");
		//"openmeetings@xmlcrm.org"
		Configurationmanagement.getInstance().addConfByKey(3, "email_username", mailauthname, null, "System auth email username");
		//"tony123"
		Configurationmanagement.getInstance().addConfByKey(3, "email_userpass", mailauthpass, null, "System auth email password");		
		//"EN"
		Configurationmanagement.getInstance().addConfByKey(3, "default_lang", default_lang, null, "Default System Language for tamplates");
		
		Configurationmanagement.getInstance().addConfByKey(3, "register_mail_subject", "SignUp", 1, "The Subject for Mails sended at registration");
					
		
	}
	
	public void loadInitUserAndOrganisation(String username, String userpass, String email, String defaultOrganisationName) {
		//Add user
		long user_id = Usermanagement.getInstance().registerUserInit(new Long(3),3, 1, 1, 
				username, userpass, "lastname", "firstname", 
				email, new java.util.Date(), "street", "no", "fax", 
				"zip", 1, "town", 0, false, null);
		
		//Add default group
		long organisation_id = Organisationmanagement.getInstance().addOrganisation(defaultOrganisationName, user_id);
		
		//Add user to default group
		Organisationmanagement.getInstance().addUserToOrganisation(1, organisation_id, 1,"");		
	}
	
}
