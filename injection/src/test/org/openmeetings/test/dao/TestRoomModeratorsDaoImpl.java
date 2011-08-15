package org.openmeetings.test.dao;

import static junit.framework.Assert.assertNotNull;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.openmeetings.app.data.conference.Roommanagement;
import org.openmeetings.app.data.conference.dao.RoomModeratorsDaoImpl;
import org.openmeetings.app.data.user.Usermanagement;
import org.openmeetings.app.persistence.beans.rooms.RoomModerators;
import org.openmeetings.app.persistence.beans.rooms.Rooms;
import org.openmeetings.app.persistence.beans.user.Users;
import org.openmeetings.test.AbstractOpenmeetingsSpringTest;
import org.springframework.beans.factory.annotation.Autowired;

public class TestRoomModeratorsDaoImpl extends AbstractOpenmeetingsSpringTest {

	@Autowired
	private Usermanagement userManagement;
	@Autowired
	private Roommanagement roommanagement;
	@Autowired
	private RoomModeratorsDaoImpl roomModeratorsDao;

	@Test
	public void testRoomModeratorsDaoImpl() throws Exception {

		Long userId = 1L;
		Users user = userManagement.getUserById(userId);
		assertNotNull("Cann't get default user", user);

		List<Rooms> rooms = roommanagement.getPublicRooms(user.getLevel_id());
		assertNotNull("Cann't get public rooms fo default user", rooms);

		Rooms room = null;
		for (Iterator<Rooms> iter = rooms.iterator(); iter.hasNext();) {
			room = iter.next();
			break;
		}
		assertNotNull("Cann't get room for default user", room);

		Long rmId = roomModeratorsDao
				.addRoomModeratorByUserId(user, true, room.getRooms_id());
		assertNotNull("Cann't add room moderator", rmId);

		Long rooms_id = roommanagement.addExternalRoom("ExternalRoom",
				room.getRooms_id(), "comment", 10L, true, null, false, false,
				0, false, null, null, null, false, // allowUserQuestions
				false, // isAudioOnly
				false, // isClosed
				room.getRedirectURL(), false, true, false);
		assertNotNull("Cann't add external room ", rooms_id);

		RoomModerators rm = roomModeratorsDao
				.getRoomModeratorById(rmId);
		assertNotNull("Cann't get room moderator", rm);

		roomModeratorsDao.removeRoomModeratorByUserId(rmId);
	}
}
