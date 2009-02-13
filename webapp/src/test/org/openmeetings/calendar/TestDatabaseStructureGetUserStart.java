package org.openmeetings.calendar;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.red5.logging.Red5LoggerFactory;
import org.openmeetings.app.data.user.dao.UsersDaoImpl;

public class TestDatabaseStructureGetUserStart extends TestCase {

	

	private static final Logger log = Red5LoggerFactory.getLogger(TestDatabaseStructureGetUserStart.class, "openmeetings");

	

	public TestDatabaseStructureGetUserStart(String testname){

		super(testname);

	}

	

	public void testAddingGroup(){

		try {

			UsersDaoImpl.getInstance().getUser(new Long(1));

		} catch (Exception err) {

			log.error("[testAddingGroup]",err);

		}

		

		

	}



}
