package org.xmlcrm.test.rooms;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmlcrm.app.data.conference.Roommanagement;


public class AddRoomTypes extends TestCase {
	
	private static final Log log = LogFactory.getLog(AddRoomTypes.class);
	
	public AddRoomTypes(String testname){
		super(testname);
	}
	
	public void testAddToomTypes(){
		
		long conference_Id = Roommanagement.getInstance().addRoomType("conference");
		log.error("conference_Id: "+conference_Id);
		long audience_Id = Roommanagement.getInstance().addRoomType("audience");
		log.error("audience_Id: "+audience_Id);
		
	}

}
