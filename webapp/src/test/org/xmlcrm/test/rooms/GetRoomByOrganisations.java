package org.openmeetings.test.rooms;

import junit.framework.TestCase;
import java.util.List;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmeetings.app.data.conference.Roommanagement;
import org.openmeetings.app.hibernate.beans.rooms.*;

public class GetRoomByOrganisations extends TestCase {
	
	private static final Log log = LogFactory.getLog(GetRoomByOrganisations.class);
	
	public GetRoomByOrganisations(String testname){
		super(testname);
	}
	
	public void testAddOrgRoom(){
		
		List rooms = Roommanagement.getInstance().getRoomsOrganisationByOrganisationId(3,1);
		
		for (Iterator it = rooms.iterator(); it.hasNext();){
			
			Rooms_Organisation rOrg = (Rooms_Organisation) it.next();
			log.error(rOrg.getRoom().getName());
			
		}
		
	}

}
