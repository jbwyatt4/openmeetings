package org.openmeetings.test.rooms;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openmeetings.app.data.conference.Roommanagement;
import org.openmeetings.app.persistence.beans.rooms.Rooms_Organisation;
import org.openmeetings.test.AbstractOpenmeetingsSpringTest;
import org.springframework.beans.factory.annotation.Autowired;

public class TestGetRoomByOrganisations extends AbstractOpenmeetingsSpringTest {

	private static final Logger log = Logger
			.getLogger(TestGetRoomByOrganisations.class);

	@Autowired
	private Roommanagement roommanagement;

	@Test
	public void testAddOrgRoom() {

		List rooms = roommanagement.getRoomsOrganisationByOrganisationId(3, 1);

		for (Iterator it = rooms.iterator(); it.hasNext();) {

			Rooms_Organisation rOrg = (Rooms_Organisation) it.next();
			log.error(rOrg.getRoom().getName());

		}

	}

}
