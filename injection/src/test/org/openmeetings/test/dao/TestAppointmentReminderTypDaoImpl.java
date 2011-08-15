package org.openmeetings.test.dao;

import static junit.framework.Assert.assertNotNull;

import org.junit.Test;
import org.openmeetings.app.data.calendar.daos.AppointmentReminderTypDaoImpl;
import org.openmeetings.app.persistence.beans.calendar.AppointmentReminderTyps;
import org.openmeetings.test.AbstractOpenmeetingsSpringTest;
import org.springframework.beans.factory.annotation.Autowired;

public class TestAppointmentReminderTypDaoImpl extends AbstractOpenmeetingsSpringTest {

	@Autowired
	private AppointmentReminderTypDaoImpl appointmentReminderTypDaoImpl;

	@Test
	public void testAppointmentReminderTypDaoImpl() throws Exception {

		Long id = appointmentReminderTypDaoImpl.addAppointmentReminderTyps(1L,
				"Reminder", "comment");
		assertNotNull("Cann't add Reminder Typ", id);

		AppointmentReminderTyps reminderTyps = appointmentReminderTypDaoImpl
				.getAppointmentReminderTypById(id);
		assertNotNull("Cann't get Reminder Typ", reminderTyps);

		id = appointmentReminderTypDaoImpl.deleteAppointmentReminderTyp(id);
		assertNotNull("Cann't delete Reminder Typ", id);
	}
}
