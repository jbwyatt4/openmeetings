package org.openmeetings.test.init;

import junit.framework.TestCase;

import org.openmeetings.app.data.user.Usermanagement;
import org.springframework.beans.factory.annotation.Autowired;

public class AddUserLevels extends TestCase {
    @Autowired
    private Usermanagement userManagement;
	
	public AddUserLevels(String testname){
		super(testname);
	}

	public void testAddUserlevels(){
		
		userManagement.addUserLevel("User", 1);
		
		userManagement.addUserLevel("Moderator", 2);
		
		userManagement.addUserLevel("Admin", 3);
	}
}
