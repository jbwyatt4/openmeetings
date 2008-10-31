package org.openmeetings.calendar;



import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;



import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;

import org.openmeetings.app.data.beans.basic.SearchResult;
import org.openmeetings.app.data.calendar.daos.AppointmentCategoryDaoImpl;
import org.openmeetings.app.data.calendar.daos.AppointmentDaoImpl;
import org.openmeetings.app.data.calendar.daos.MeetingMemberDaoImpl;
import org.openmeetings.app.data.user.Usermanagement;
import org.openmeetings.app.hibernate.beans.adresses.Adresses;
import org.openmeetings.app.hibernate.beans.calendar.Appointment;
import org.openmeetings.app.hibernate.beans.user.Users;



public class TestDatabaseStructureUsersSearch extends TestCase {

	

	private static final Log log = LogFactory.getLog(TestDatabaseStructureUsersSearch.class);

	

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

