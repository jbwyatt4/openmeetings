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
import org.openmeetings.app.data.user.Usermanagement;
import org.openmeetings.app.hibernate.beans.calendar.Appointment;
import org.openmeetings.app.hibernate.beans.calendar.AppointmentCategory;



public class TestDatabaseStructureCategory extends TestCase {

	

	private static final Log log = LogFactory.getLog(TestDatabaseStructureCategory.class);

	

	public TestDatabaseStructureCategory(String testname){

		super(testname);

	}

	

	public void testAddingGroup(){

		try {

			//AppointmentCategoryDaoImpl.getInstance().addAppointmentCategory(2L, "neu2", "test");
			//AppointmentCategoryDaoImpl.getInstance().addAppointmentCategory("dritte");
			//AppointmentCategoryDaoImpl.getInstance().updateAppointmentCategory(2L, "alt");
			
		List<AppointmentCategory> appointmentCategory = AppointmentCategoryDaoImpl.getInstance().getAppointmentCategoryList(1L);
		log.debug("Anzahl: "+appointmentCategory.size());
		
		
		for(int x=0; x<appointmentCategory.size(); x++){
			log.debug("id: " +appointmentCategory.get(x).getCategoryId()) ;
			
		}
		
		} catch (Exception err) {

			log.error("[testAddingGroup]",err);

		}

		

		

	}



}

