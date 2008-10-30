package org.openmeetings.calendar;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmeetings.app.data.user.Usermanagement;



public class TestDatabaseStructureGetUserStart extends TestCase {

	

	private static final Log log = LogFactory.getLog(TestDatabaseStructureGetUserStart.class);

	

	public TestDatabaseStructureGetUserStart(String testname){

		super(testname);

	}

	

	public void testAddingGroup(){

		try {

			Usermanagement.getInstance().getUser(new Long(1));

		} catch (Exception err) {

			log.error("[testAddingGroup]",err);

		}

		

		

	}



}
