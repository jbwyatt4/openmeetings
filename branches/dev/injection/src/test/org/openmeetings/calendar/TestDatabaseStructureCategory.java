package org.openmeetings.calendar;

import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.openmeetings.app.data.calendar.daos.AppointmentCategoryDaoImpl;
import org.openmeetings.app.persistence.beans.calendar.AppointmentCategory;
import org.springframework.beans.factory.annotation.Autowired;

public class TestDatabaseStructureCategory extends TestCase {

	@Autowired
	private AppointmentCategoryDaoImpl appointmentCategoryDaoImpl;

	private static final Logger log = Logger
			.getLogger(TestDatabaseStructureCategory.class);

	public TestDatabaseStructureCategory(String testname) {

		super(testname);

	}

	public void testAddingGroup() {

		try {

			// appointmentCategoryDaoImpl.addAppointmentCategory(2L, "neu2",
			// "test");
			// appointmentCategoryDaoImpl.addAppointmentCategory("dritte");
			// appointmentCategoryDaoImpl.updateAppointmentCategory(2L, "alt");

			List<AppointmentCategory> appointmentCategory = appointmentCategoryDaoImpl
					.getAppointmentCategoryList();
			log.debug("Anzahl: " + appointmentCategory.size());

			for (int x = 0; x < appointmentCategory.size(); x++) {
				log.debug("id: " + appointmentCategory.get(x).getCategoryId());

			}

		} catch (Exception err) {

			log.error("[testAddingGroup]", err);

		}

	}

}
