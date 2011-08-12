package org.openmeetings.test.dao;

import java.util.Iterator;
import java.util.List;

import org.openmeetings.app.data.conference.Roommanagement;
import org.openmeetings.app.data.conference.dao.RoomModeratorsDaoImpl;
import org.openmeetings.app.data.user.Usermanagement;
import org.openmeetings.app.persistence.beans.rooms.RoomModerators;
import org.openmeetings.app.persistence.beans.rooms.Rooms;
import org.openmeetings.app.persistence.beans.user.Users;
import org.openmeetings.test.dao.base.AbstractTestCase;
import org.springframework.beans.factory.annotation.Autowired;

public class RoomModeratorsDaoImplTest extends AbstractTestCase {
    @Autowired
    private Usermanagement userManagement;

	public RoomModeratorsDaoImplTest(String name){
		super(name);
	}
	
	final public void testRoomModeratorsDaoImpl() throws Exception {

		Long userId = 1L;
		Users user = userManagement.getUserById(userId);
		assertNotNull("Cann't get default user", user);
		
		List<Rooms> rooms = Roommanagement.getInstance().getPublicRooms(user.getLevel_id());
		assertNotNull("Cann't get public rooms fo default user", rooms);

		Rooms room = null;
		for (Iterator<Rooms> iter = rooms.iterator(); iter.hasNext();){
    		room = iter.next();
    		break;
    	}
		assertNotNull("Cann't get room for default user", room);
		
		Long rmId = RoomModeratorsDaoImpl.getInstance().addRoomModeratorByUserId(user, true, room.getRooms_id());
		assertNotNull("Cann't add room moderator", rmId);

    	Long rooms_id =  Roommanagement.getInstance().addExternalRoom(
    			"ExternalRoom",
    			room.getRooms_id(), "comment", 10L, true,
				null, false, false, 0, false,
				null, null, null, 
				false, //allowUserQuestions
				false, //isAudioOnly
				false, //isClosed
				room.getRedirectURL(),
				false, true, false);
		assertNotNull("Cann't add external room ", rooms_id);
		
		RoomModerators rm = RoomModeratorsDaoImpl.getInstance().getRoomModeratorById(rmId); 
		assertNotNull("Cann't get room moderator", rm);
		
		RoomModeratorsDaoImpl.getInstance().removeRoomModeratorByUserId(rmId);
	}
}
