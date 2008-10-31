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
import org.openmeetings.app.data.user.Usermanagement;
import org.openmeetings.app.hibernate.beans.calendar.Appointment;
import org.openmeetings.app.remote.CalendarService;



public class TestDatabaseStructureAppointment extends TestCase {

	

	private static final Log log = LogFactory.getLog(TestDatabaseStructureAppointment.class);

	

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
				Date date = format.parse( "2008-09-03" );
		
			List<Appointment> listAppoints = AppointmentDaoImpl.getInstance().searchAppointmentsByName("%");
			//AppointmentDaoImpl.getInstance().getNextAppointmentById(1L);
			AppointmentDaoImpl.getInstance().addAppointment("mezo",1L, "Pforzheim", "zweiter", Calendar.getInstance().getTime() , 
					date, null, true, null, null, 1L);
			//AppointmentDaoImpl.getInstance().addAppointment("testap", "erster Test",Calendar.getInstance().getTime() , 
			//		Calendar.getInstance().getTime(), true, false, false, false, new Long(1));
			log.debug("Anzahl: "+listAppoints.size());
			
			for (Appointment appoints : listAppoints) {
				log.debug("Termin: "+appoints.getAppointmentName()+" startDate: "+appoints.getAppointmentStarttime()+ " endDate: "+appoints.getAppointmentEndtime());
			}
			
			for (Iterator<Appointment> iter = listAppoints.iterator();iter.hasNext();) {
				log.debug(iter.next());
			}
			
			//AppointmentDaoImpl.getInstance().updateAppointment(1L,"neu", "erster Test",Calendar.getInstance().getTime() , 
					//Calendar.getInstance().getTime(), true, false, false, false, new Long(1));

		} catch (Exception err) {

			log.error("[testAddingGroup]",err);

		}

		

		

	}



}

