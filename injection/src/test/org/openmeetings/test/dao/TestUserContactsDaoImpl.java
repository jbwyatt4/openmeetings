package org.openmeetings.test.dao;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import org.junit.Test;
import org.openmeetings.app.data.user.Usermanagement;
import org.openmeetings.app.data.user.dao.UserContactsDaoImpl;
import org.openmeetings.app.persistence.beans.user.UserContacts;
import org.openmeetings.test.AbstractOpenmeetingsSpringTest;
import org.springframework.beans.factory.annotation.Autowired;

public class TestUserContactsDaoImpl extends AbstractOpenmeetingsSpringTest {
    @Autowired
    private Usermanagement userManagement;
	@Autowired
	private UserContactsDaoImpl userContactsDao;

	@Test
	public void testUserContactsDaoImpl() throws Exception	{
		assertNotNull("Cann't access to contacts dao implimentation", userContactsDao);
		Long id = userContactsDao.addUserContact(1L, 1L, false, "");
		assertTrue("New contact cann't added", id > 0);
		UserContacts userContact = userContactsDao.getUserContacts(id);

		assertTrue("Contact should be the same with user", userContact.getContact().equals(userManagement.getUserById(id)));
		
	}
	
}
