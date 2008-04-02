package org.xmlcrm.test.init;

import junit.framework.TestCase;

import org.xmlcrm.app.data.user.Usermanagement;

public class AddUserLevels extends TestCase {
	
	public AddUserLevels(String testname){
		super(testname);
	}

	public void testAddUserlevels(){
		
		Usermanagement.getInstance().addUserLevel("User", 1);
		
		Usermanagement.getInstance().addUserLevel("Moderator", 2);
		
		Usermanagement.getInstance().addUserLevel("Admin", 3);
	}
}
