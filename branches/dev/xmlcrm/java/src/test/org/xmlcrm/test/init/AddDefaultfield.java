package org.xmlcrm.test.init;

import junit.framework.TestCase;

import org.xmlcrm.app.data.basic.Fieldmanagment;

public class AddDefaultfield extends TestCase {
	
	public AddDefaultfield (String testname){
		super(testname);
	}
	
	public void testaddDefaultField(){
		
		Fieldmanagment.getInstance().addFourFieldValues("admin_userlist_user_id", 146, "User-ID", "USER-ID", "USER-ID", "USER-ID");
		Fieldmanagment.getInstance().addFourFieldValues("admin_userlist_login", 147, "Login", "login", "login", "login");
		Fieldmanagment.getInstance().addFourFieldValues("admin_userlist_firstnam", 148, "Vorname", "firstname", "firstname", "firstname");
		Fieldmanagment.getInstance().addFourFieldValues("admin_userlist_lastname", 149, "Nachname", "lastname", "lastname", "lastname");
		Fieldmanagment.getInstance().addFourFieldValues("turnoverlist_next", 150, "vorwärts", "show next", "show next", "show next");
		Fieldmanagment.getInstance().addFourFieldValues("turnoverlist_pre", 151, "zurück", "show pre", "show pre", "show pre");
		
	}

}
