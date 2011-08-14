package org.openmeetings.calendar;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.openmeetings.app.data.user.dao.UsersDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class TestDatabaseStructureGetUserStart extends TestCase {
	private static final Logger log = Logger.getLogger(TestDatabaseStructureGetUserStart.class);
	@Autowired
	private UsersDaoImpl usersDao;

	public TestDatabaseStructureGetUserStart(String testname) {
		super(testname);
	}

	public void testAddingGroup() {
		try {
			usersDao.getUser(new Long(1));
		} catch (Exception err) {
			log.error("[testAddingGroup]", err);
		}
	}
}
