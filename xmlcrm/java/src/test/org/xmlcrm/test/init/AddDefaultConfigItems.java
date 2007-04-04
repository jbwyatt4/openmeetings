package org.xmlcrm.test.init;

import junit.framework.TestCase;

import org.xmlcrm.app.data.basic.Configurationmanagement;

public class AddDefaultConfigItems extends TestCase {
	
	public AddDefaultConfigItems(String testname){
		super(testname);
	}
	
	public void testaddDefaultConfiItems(){
		
		Configurationmanagement.getInstance().addConfByKey(3, "smtp_server", "smtp.xmlcrm.org", 1, "this is the smtp server to send messages");
		Configurationmanagement.getInstance().addConfByKey(3, "system_email_addr", "openmeetings@xmlcrm.org", 1, "all send EMails by the system will have this address");
		Configurationmanagement.getInstance().addConfByKey(3, "email_username", "openmeetings@xmlcrm.org", 1, "System auth email username");
		Configurationmanagement.getInstance().addConfByKey(3, "email_userpass", "tony123", 1, "System auth email password");		
		
	}

}
