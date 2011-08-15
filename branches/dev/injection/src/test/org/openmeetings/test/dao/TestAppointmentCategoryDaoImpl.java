package org.openmeetings.test.dao;

import static junit.framework.Assert.assertNotNull;

import org.openmeetings.app.data.calendar.daos.AppointmentCategoryDaoImpl;
import org.openmeetings.app.persistence.beans.calendar.AppointmentCategory;
import org.openmeetings.test.AbstractOpenmeetingsSpringTest;
import org.springframework.beans.factory.annotation.Autowired;

public class TestAppointmentCategoryDaoImpl extends AbstractOpenmeetingsSpringTest {

	@Autowired
	private AppointmentCategoryDaoImpl appointmentCategoryDaoImpl;

	final public void testAppointmentCategoryDaoImpl() throws Exception {

		Long id = appointmentCategoryDaoImpl.addAppointmentCategory(1L,
				"Category", "comment");
		assertNotNull("Cann't add Appointment category", id);

		AppointmentCategory ac = appointmentCategoryDaoImpl
				.getAppointmentCategoryById(id);
		assertNotNull("Cann't get Appointment Category", ac);

		id = appointmentCategoryDaoImpl.deleteAppointmentCategory(id);
		assertNotNull("Cann't delete Appointment Category", id);
	}
}
