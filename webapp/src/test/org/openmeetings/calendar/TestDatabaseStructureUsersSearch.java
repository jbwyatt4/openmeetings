package org.openmeetings.calendar;

import junit.framework.TestCase;
import org.slf4j.Logger;
import org.red5.logging.Red5LoggerFactory;

import org.openmeetings.app.data.beans.basic.SearchResult;
import org.openmeetings.app.data.user.Usermanagement;


public class TestDatabaseStructureUsersSearch extends TestCase {

	private static final Logger log = Red5LoggerFactory.getLogger(TestDatabaseStructureUsersSearch.class, "openmeetings");

	public TestDatabaseStructureUsersSearch(String testname){
		super(testname);
	}

	public void testAddingGroup(){

		try {
			
			 SearchResult users = Usermanagement.getInstance().getAllUserByRange("first", 0, 10, "orderby", true);
			 log.debug("[result]" + users.getResult().size());
			 log.debug("[records]"+ users.getRecords());
			 
			 //GroupMemberDaoImpl.getInstance().addGroupMember("Ad", "dir", Calendar.getInstance().getTime(), "1", "2", "interpol155", 1L, 1L);	
			//GroupMemberDaoImpl.getInstance().addGroupMember(firstname, lastname, age, memberStatus, appointmentStatus, password, adresses_id, appointmentId);
			
			//GroupMemberDaoImpl.getInstance().getGroupMemberById(1L);
			//GroupMemberDaoImpl.getInstance().deleteGroupMember(2L);
			//GroupMemberDaoImpl.getInstance().updateGroupMember(1L, "Eugen", "Schwert", Calendar.getInstance().getTime(), "1", "2", "interpol155", 1L, 1L);
		} catch (Exception err) {

			log.error("[testAddingGroup]",err);

		}

		

		

	}



}

