package org.openmeetings.test.rooms;

import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.openmeetings.app.data.conference.Roommanagement;
import org.openmeetings.app.persistence.beans.rooms.Rooms_Organisation;
import org.springframework.beans.factory.annotation.Autowired;

public class GetRoomByOrganisations extends TestCase {

	private static final Logger log = Logger
			.getLogger(GetRoomByOrganisations.class);

	@Autowired
	private Roommanagement roommanagement;

	public GetRoomByOrganisations(String testname) {
		super(testname);
	}

	public void testAddOrgRoom() {

		List rooms = roommanagement.getRoomsOrganisationByOrganisationId(3, 1);

		for (Iterator it = rooms.iterator(); it.hasNext();) {

			Rooms_Organisation rOrg = (Rooms_Organisation) it.next();
			log.error(rOrg.getRoom().getName());

		}

	}

}
