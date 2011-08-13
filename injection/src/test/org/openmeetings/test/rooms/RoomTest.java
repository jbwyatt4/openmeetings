package org.openmeetings.test.rooms;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.openmeetings.app.data.conference.Roommanagement;
import org.springframework.beans.factory.annotation.Autowired;

public class RoomTest extends TestCase {

	private static final Logger log = Logger.getLogger(RoomTest.class);

	@Autowired
	private Roommanagement roommanagement;

	public RoomTest(String testname) {
		super(testname);
	}

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
