package org.openmeetings.utils.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import org.openmeetings.app.data.basic.Configurationmanagement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author swagner
 *
 */
public class SmtpAuthenticator extends Authenticator{
	@Autowired
	private Configurationmanagement cfgManagement;
	
	@Override
	public PasswordAuthentication getPasswordAuthentication() {
        //String username = "openmeetings@xmlcrm.org";
		String username = cfgManagement.getConfKey(3, "email_username").getConf_value();
        //String password = "tony123";
		String password = cfgManagement.getConfKey(3, "email_userpass").getConf_value();
	       
		return new PasswordAuthentication(username,password);
	}

	
}
