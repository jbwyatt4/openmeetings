package org.openmeetings.test.rooms;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openmeetings.app.data.conference.Roommanagement;
import org.openmeetings.test.AbstractOpenmeetingsSpringTest;
import org.springframework.beans.factory.annotation.Autowired;

public class TestRoom extends AbstractOpenmeetingsSpringTest {

	private static final Logger log = Logger.getLogger(TestRoom.class);

	@Autowired
	private Roommanagement roommanagement;

	@Test
	public void testRoomTest() {
		// Public Rooms
		long room1 = roommanagement.addRoom(3, "room1", 1, "", new Long(4),
				true, null, false, false, null, false, null, true, false,
				false, "", "", "", null, null, null, false);
		log.error("room1: " + room1);
		long room2 = roommanagement.addRoom(3, "room1", 2, "", new Long(4),
				true, null, true, false, null, false, null, true, false, false,
				"", "", "", null, null, null, false);
		log.error("room2: " + room2);

	}

}
