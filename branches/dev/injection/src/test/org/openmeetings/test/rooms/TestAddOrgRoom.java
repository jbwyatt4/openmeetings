package org.openmeetings.test.rooms;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openmeetings.app.data.conference.Roommanagement;
import org.openmeetings.test.AbstractOpenmeetingsSpringTest;
import org.springframework.beans.factory.annotation.Autowired;

public class TestAddOrgRoom extends AbstractOpenmeetingsSpringTest {

	private static final Logger log = Logger.getLogger(TestAddOrgRoom.class);

	@Autowired
	private Roommanagement roommanagement;

	@Test
	public void testAddOrgRoom() {

		long room = roommanagement.addRoom(3, "roomOrg", 1, "", new Long(4),
				true, null, false, false, null, false, null, true, false,
				false, "", "", "", null, null, null, false);
		roommanagement.addRoomToOrganisation(3, room, 1);

	}

}
