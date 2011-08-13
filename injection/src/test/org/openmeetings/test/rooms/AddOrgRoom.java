package org.openmeetings.test.rooms;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.openmeetings.app.data.conference.Roommanagement;
import org.springframework.beans.factory.annotation.Autowired;

public class AddOrgRoom extends TestCase {

	private static final Logger log = Logger.getLogger(AddOrgRoom.class);

	@Autowired
	private Roommanagement roommanagement;

	public AddOrgRoom(String testname) {
		super(testname);
	}

	public void testAddOrgRoom() {

		long room = roommanagement.addRoom(3, "roomOrg", 1, "", new Long(4),
				true, null, false, false, null, false, null, true, false,
				false, "", "", "", null, null, null, false);
		roommanagement.addRoomToOrganisation(3, room, 1);

	}

}
