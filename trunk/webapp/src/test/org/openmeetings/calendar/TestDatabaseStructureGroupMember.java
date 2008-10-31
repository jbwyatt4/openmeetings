package org.openmeetings.calendar;



import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;



import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;

import org.openmeetings.app.data.calendar.daos.AppointmentCategoryDaoImpl;
import org.openmeetings.app.data.calendar.daos.AppointmentDaoImpl;
import org.openmeetings.app.data.calendar.daos.MeetingMemberDaoImpl;
import org.openmeetings.app.data.user.Usermanagement;
import org.openmeetings.app.hibernate.beans.adresses.Adresses;
import org.openmeetings.app.hibernate.beans.calendar.Appointment;



public class TestDatabaseStructureGroupMember extends TestCase {

	

	private static final Log log = LogFactory.getLog(TestDatabaseStructureGroupMember.class);

	

	public TestDatabaseStructureGroupMember(String testname){

		super(testname);

	}

	

	public void testAddingGroup(){

		try {
			
			
			//MeetingMemberDaoImpl.getInstance().addMeetingMember("Adddd", "dir", "1", "2", 1L, 1L, "test");	
			//MeetingMemberDaoImpl.getInstance().addMeetingMember(firstname, lastname, memberStatus, appointmentStatus, appointmentId, userid, email)
			
			
			//MeetingMemberDaoImpl.getInstance().getMeetingMemberById(1L);
			//MeetingMemberDaoImpl.getInstance().deleteMeetingMember(2L);
			MeetingMemberDaoImpl.getInstance().updateMeetingMember(1l,"bbbbbb", "dir", "1", "2", 1L, 1L, "test"); 
		} catch (Exception err) {

			log.error("[testAddingMeeting]",err);

		}

		

		

	}



}

