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
import org.openmeetings.app.data.calendar.management.AppointmentLogic;
import org.openmeetings.app.data.user.Usermanagement;
import org.openmeetings.app.hibernate.beans.calendar.Appointment;
import org.openmeetings.app.remote.CalendarService;



public class CopyOfTestDatabaseStructureAppointment extends TestCase {

	

	private static final Log log = LogFactory.getLog(CopyOfTestDatabaseStructureAppointment.class);

	

	public CopyOfTestDatabaseStructureAppointment(String testname){

		super(testname);

	}

	

	public void testAddingGroup(){

		try {
		Appointment listAppoints = AppointmentLogic.getInstance().getNextAppointment();
		if(listAppoints != null)
		log.debug("Termin: "+listAppoints.getAppointmentName()+" startDate: "+listAppoints.getAppointmentStarttime()+ " endDate: "+listAppoints.getAppointmentEndtime());
			//AppointmentDaoImpl.getInstance().getNextAppointmentById(1L);
			//AppointmentDaoImpl.getInstance().addAppointment("testap2", "Pforzheim", "zweiter",Calendar.getInstance().getTime() , 
					//Calendar.getInstance().getTime(), null, true, null, null, 3L);
			//AppointmentDaoImpl.getInstance().addAppointment("testap", "erster Test",Calendar.getInstance().getTime() , 
			//		Calendar.getInstance().getTime(), true, false, false, false, new Long(1));
			//log.debug("Anzahl: "+listAppoints.size());
			/*for (Appointment appoints : listAppoints) {
				log.debug("Termin: "+appoints.getAppointmentName()+" startDate: "+appoints.getAppointmentStarttime()+ " endDate: "+appoints.getAppointmentEndtime());
			}
			
			for (Iterator<Appointment> iter = listAppoints.iterator();iter.hasNext();) {
				log.debug(iter.next());
			}*/
			
			//AppointmentDaoImpl.getInstance().updateAppointment(1L,"neu", "erster Test",Calendar.getInstance().getTime() , 
					//Calendar.getInstance().getTime(), true, false, false, false, new Long(1));

		} catch (Exception err) {

			log.error("[testAddingGroup]",err);

		}

		

		

	}



}

