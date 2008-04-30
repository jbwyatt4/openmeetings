package org.openmeetings.app.data.basic.rooms;

import java.util.List;

import org.openmeetings.app.hibernate.beans.rooms.Rooms;

public class RoomsList {
	
	List<Rooms> roomList = null;

	public List<Rooms> getRoomList() {
		return roomList;
	}
	public void setRoomList(List<Rooms> roomList) {
		this.roomList = roomList;
	}

}
