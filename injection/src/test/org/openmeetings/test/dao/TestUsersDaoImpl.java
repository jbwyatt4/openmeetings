package org.openmeetings.test.dao;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import org.junit.Test;
import org.openmeetings.app.data.user.Usermanagement;
import org.openmeetings.app.data.user.dao.UsersDaoImpl;
import org.openmeetings.app.persistence.beans.user.Users;
import org.openmeetings.test.AbstractOpenmeetingsSpringTest;
import org.springframework.beans.factory.annotation.Autowired;

public class TestUsersDaoImpl extends AbstractOpenmeetingsSpringTest {
    @Autowired
    private Usermanagement userManagement;
	@Autowired
	private UsersDaoImpl usersDao;
	
	@Test
	public void testUsersDaoImpl() throws Exception {
		assertNotNull("Cann't access to user dao implimentation", usersDao);
		
		// 
		Users users = new Users();
		// add user
		users.setFirstname("firstname");
		users.setLastname("lastname");
		users.setLogin("login");
		users.setPassword("password");
		Long user_id = userManagement.addUser(users);
		assertTrue("Cann't add user", user_id > 0);
		users = userManagement.getUserByIdAndDeleted(user_id);
		assertNotNull("User should not be null", users);
		
	}

}
