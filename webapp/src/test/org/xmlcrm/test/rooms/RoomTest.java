package org.xmlcrm.test.rooms;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import junit.framework.TestCase;

import org.xmlcrm.app.data.conference.Roommanagement;

public class RoomTest extends TestCase {
	
	private static final Log log = LogFactory.getLog(RoomTest.class);
	
	public RoomTest(String testname){
		super(testname);
	}
	
	public void testRoomTest(){
		//Public Rooms
		long room1 = Roommanagement.getInstance().addRoom(3, "room1", 1, "", new Long(4), true, null);
		log.error("room1: "+room1);
		long room2 = Roommanagement.getInstance().addRoom(3, "room1", 2, "", new Long(4), true, null);
		log.error("room2: "+room2);
		
	}

}
