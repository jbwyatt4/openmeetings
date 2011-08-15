package org.openmeetings.test.rooms;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openmeetings.app.data.conference.Roommanagement;
import org.openmeetings.test.AbstractOpenmeetingsSpringTest;
import org.springframework.beans.factory.annotation.Autowired;

public class TestAddRoomTypes extends AbstractOpenmeetingsSpringTest {

	private static final Logger log = Logger.getLogger(TestAddRoomTypes.class);

	@Autowired
	private Roommanagement roommanagement;

	@Test
	public void testAddToomTypes() {

		// long conference_Id = roommanagement.addRoomType("conference");
		// log.error("conference_Id: "+conference_Id);
		// long audience_Id = roommanagement.addRoomType("audience");
		// log.error("audience_Id: "+audience_Id);

		long room = roommanagement.addRoom(3, "public Audience Room", 2, "",
				new Long(4), false, null, false, false, null, false, null,
				true, false, false, "", "", "", null, null, null, false);
		roommanagement.addRoomToOrganisation(3, room, 1);

		long room2 = roommanagement.addRoom(3, "private Audience Room", 2, "",
				new Long(4), true, null, false, false, null, false, null, true,
				false, false, "", "", "", null, null, null, false);
		roommanagement.addRoomToOrganisation(3, room2, 1);

	}

}
