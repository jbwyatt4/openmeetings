package org.openmeetings.test.rooms;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openmeetings.app.data.conference.Roommanagement;


public class AddRoomTypes extends TestCase {
	
	private static final Logger log = LoggerFactory.getLogger(AddRoomTypes.class);
	
	public AddRoomTypes(String testname){
		super(testname);
	}
	
	public void testAddToomTypes(){
		
//		long conference_Id = Roommanagement.getInstance().addRoomType("conference");
//		log.error("conference_Id: "+conference_Id);
//		long audience_Id = Roommanagement.getInstance().addRoomType("audience");
//		log.error("audience_Id: "+audience_Id);
		
		long room = Roommanagement.getInstance().addRoom(3,"public Audience Room", 2,"", new Long(4), false, null,
				290, 280, 2, 2,
				400,
				true, 296, 2, 592, 660,
				true, 2, 284, 310, 290);
		Roommanagement.getInstance().addRoomToOrganisation(3,room, 1);
		
		long room2 = Roommanagement.getInstance().addRoom(3,"private Audience Room", 2,"", new Long(4), true, null,
				290, 280, 2, 2,
				400,
				true, 296, 2, 592, 660,
				true, 2, 284, 310, 290);
		Roommanagement.getInstance().addRoomToOrganisation(3,room2, 1);
		
	}

}
