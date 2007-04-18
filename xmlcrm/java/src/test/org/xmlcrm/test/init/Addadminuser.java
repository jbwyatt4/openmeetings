package org.xmlcrm.test.init;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmlcrm.app.data.user.Usermanagement;
import org.xmlcrm.app.data.user.Statemanagement;
import org.xmlcrm.test.adresses.TestAddEmailToAdress;

public class Addadminuser extends TestCase{
	
	private static final Log log = LogFactory.getLog(Addadminuser.class);
	
	public Addadminuser(String testname){
		super(testname);
	}
	
	public void testAddADminUser(){
		Statemanagement.getInstance().addState("Deutschland");
		
		//(long USER_LEVEL,String login, String Userpass, String lastname, String firstname, String email, int age, String street, String additionalname, String fax, String zip, long states_id, String town, long language_id)
		long user_id = Usermanagement.getInstance().registerUserInit(new Long(3), 3, 1, 1, "swagner4", "67810", "Wagner", "Sebastian", "seba.wagner@gmail.com", 23, "Bleichstra§e", "92", "fax number", "75173", 1, "Pforzheim", 1, true);
		
		log.error("new User: "+user_id);
	}

}
