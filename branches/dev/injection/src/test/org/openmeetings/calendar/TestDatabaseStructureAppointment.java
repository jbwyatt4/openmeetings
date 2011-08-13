package org.openmeetings.calendar;



//import groovy.sql.Sql;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.openmeetings.app.data.calendar.daos.AppointmentDaoImpl;
import org.openmeetings.app.persistence.beans.calendar.Appointment;
import org.springframework.beans.factory.annotation.Autowired;



public class TestDatabaseStructureAppointment extends TestCase {

	private static final Logger log = Logger.getLogger(TestDatabaseStructureAppointment.class);
	@Autowired
	private AppointmentDaoImpl appointmentDao;

	

	public TestDatabaseStructureAppointment(String testname){

		super(testname);

	}

	

	public void testAddingGroup(){

		try {
			
				Calendar cal = Calendar.getInstance();
				cal.set(2008, 9, 2);
				cal.get(Calendar.DAY_OF_MONTH);
				cal.getTime();
				
				SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
				Date date = format.parse( "2008-17-08" );
				Date date2 = format.parse( "2008-18-08" );
		
				List<Appointment> listAppoints =	appointmentDao.getAppointmentsByRange(1L, date, date2);
			//List<Appointment> listAppoints = AppointmentDaoImpl.getInstance().searchAppointmentsByName("%");
			//AppointmentDaoImpl.getInstance().getNextAppointmentById(1L);
			//AppointmentDaoImpl.getInstance().addAppointment("mezo",1L, "Pforzheim", "zweiter", Calendar.getInstance().getTime() , 
				//date, null, true, null, null, 1L,1L);
			//AppointmentDaoImpl.getInstance().addAppointment("testap", "erster Test",Calendar.getInstance().getTime() , 
					///Calendar.getInstance().getTime(), true, false, false, false, new Long(1), 1L);
			log.debug("Anzahl: "+listAppoints.size());
			
			
			for (Appointment appoints : listAppoints) {
				log.debug("Termin: "+appoints.getAppointmentName()+" startDate: "+appoints.getAppointmentStarttime()+ " endDate: "+appoints.getAppointmentEndtime());
				log.debug("MeetingMembers: "+appoints.getMeetingMember().size());
			}
			
			for (Iterator<Appointment> iter = listAppoints.iterator();iter.hasNext();) {
				log.debug(""+iter.next());
			}
			
			//AppointmentDaoImpl.getInstance().updateAppointment(1L,"neu", "erster Test",Calendar.getInstance().getTime() , 
					//Calendar.getInstance().getTime(), true, false, false, false, new Long(1));
			//log.debug("AppointmentReminderTypDaoImpl: "+AppointmentReminderTypDaoImpl.getInstance().getAppointmentReminderTypById(1L));
		} catch (Exception err) {

			log.error("[testAddingGroup]",err);

		}

		

		

	}



}
