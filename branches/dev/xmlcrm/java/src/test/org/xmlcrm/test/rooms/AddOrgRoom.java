package org.xmlcrm.test.rooms;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmlcrm.app.data.conference.Roommanagement;


public class AddOrgRoom extends TestCase {
	
	private static final Log log = LogFactory.getLog(AddOrgRoom.class);
	
	public AddOrgRoom(String testname){
		super(testname);
	}
	
	public void testAddOrgRoom(){
		
		long room = Roommanagement.getInstance().addRoom(3,"roomOrg", 1, "",true,null);
		Roommanagement.getInstance().addRoomToOrganisation(3,room, 1);
		
	}
	

}
