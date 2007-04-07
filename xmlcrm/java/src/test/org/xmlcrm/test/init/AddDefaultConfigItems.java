package org.xmlcrm.test.init;

import junit.framework.TestCase;

import org.xmlcrm.app.data.basic.Configurationmanagement;

public class AddDefaultConfigItems extends TestCase {
	
	public AddDefaultConfigItems(String testname){
		super(testname);
	}
	
	public void testaddDefaultConfiItems(){
		
		Configurationmanagement.getInstance().addConfByKey(3, "default_lang", "DE", 1, "Default System Language for tamplates");
		Configurationmanagement.getInstance().addConfByKey(3, "register_mail_subject", "SignUp", 1, "The Subject for Mails sended at registration");
		
	}

}
