package org.openmeetings.test.dao;

import org.openmeetings.app.data.calendar.daos.AppointmentReminderTypDaoImpl;
import org.openmeetings.app.persistence.beans.calendar.AppointmentReminderTyps;
import org.openmeetings.test.dao.base.AbstractTestCase;
import org.springframework.beans.factory.annotation.Autowired;

public class AppointmentReminderTypDaoImplTest extends AbstractTestCase {

	public AppointmentReminderTypDaoImplTest(String name) {
		super(name);
	}

	@Autowired
	private AppointmentReminderTypDaoImpl appointmentReminderTypDaoImpl;

	final public void testAppointmentReminderTypDaoImpl() throws Exception {

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
