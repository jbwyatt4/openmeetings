package org.openmeetings.calendar;



//import groovy.sql.Sql;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;



import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;

import org.openmeetings.app.data.calendar.daos.AppointmentCategoryDaoImpl;
import org.openmeetings.app.data.calendar.daos.AppointmentDaoImpl;
import org.openmeetings.app.data.calendar.daos.AppointmentReminderTypDaoImpl;
import org.openmeetings.app.data.user.Usermanagement;
import org.openmeetings.app.hibernate.beans.calendar.Appointment;
import org.openmeetings.app.hibernate.beans.calendar.AppointmentReminderTyps;
import org.openmeetings.app.remote.CalendarService;



public class TestDatabaseStructureAppointmentReminderTyp extends TestCase {

	

	private static final Log log = LogFactory.getLog(TestDatabaseStructureAppointmentReminderTyp.class);

	

	public TestDatabaseStructureAppointmentReminderTyp(String testname){

		super(testname);

	}

	

	public void testAddingGroup(){

		try {
			
				Calendar cal = Calendar.getInstance();
				cal.set(2008, 9, 2);
				cal.get(Calendar.DAY_OF_MONTH);
				cal.getTime();
				
				SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
				Date date = format.parse( "2008-11-07" );
				Date date2 = format.parse( "2008-11-08" );
		
				AppointmentReminderTypDaoImpl.getInstance().addAppointmentReminderTyps(1L, "test 5 min", "comment");
				List<AppointmentReminderTyps> listAppoints =	AppointmentReminderTypDaoImpl.getInstance().getAppointmentReminderTypList(1L);
			
			log.debug("Anzahl: "+listAppoints.size());
			
			for (AppointmentReminderTyps appoints : listAppoints) {
				//log.debug("Termin: "+appoints.getAppointmentName()+" startDate: "+appoints.getAppointmentStarttime()+ " endDate: "+appoints.getAppointmentEndtime());
				log.debug("AppointmentReminderTyps: "+appoints.getName());
			}
			
			//for (Iterator<Appointment> iter = listAppoints.iterator();iter.hasNext();) {
				//log.debug(iter.next());
			//}
			
			
		} catch (Exception err) {

			log.error("[testAddingGroup]",err);

		}

		

		

	}



}

