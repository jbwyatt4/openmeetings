package org.openmeetings.test.init;

import org.junit.Test;
import org.openmeetings.app.data.user.Usermanagement;
import org.openmeetings.test.AbstractOpenmeetingsSpringTest;
import org.springframework.beans.factory.annotation.Autowired;

public class TestAddUserLevels extends AbstractOpenmeetingsSpringTest {
    @Autowired
    private Usermanagement userManagement;
	
    @Test
	public void testAddUserlevels(){
		
		userManagement.addUserLevel("User", 1);
		
		userManagement.addUserLevel("Moderator", 2);
		
		userManagement.addUserLevel("Admin", 3);
	}
}
