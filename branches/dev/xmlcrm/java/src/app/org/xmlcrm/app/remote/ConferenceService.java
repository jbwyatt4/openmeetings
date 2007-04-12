package org.xmlcrm.app.remote;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmlcrm.app.data.basic.Sessionmanagement;
import org.xmlcrm.app.data.user.Usermanagement;
import org.xmlcrm.app.data.conference.Roommanagement;

public class ConferenceService {
	
	private static final Log log = LogFactory.getLog(ConferenceService.class);
	
	/**
	 * get a List of all availible Rooms of this organisation
	 * @param SID
	 * @param organisation_id
	 * @return
	 */
	public List getRoomsByOrganisationAndType(String SID, long organisation_id, long roomtypes_id){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        long User_level = Usermanagement.getInstance().getUserLevelByID(User_ID);
        return Roommanagement.getInstance().getRoomsOrganisationByOrganisationIdAndRoomType(User_level, organisation_id, roomtypes_id);
	}
	
	/**
	 * get a List of all public availible rooms
	 * @param SID
	 * @param organisation_id
	 * @return
	 */
	public List getRoomsPublic(String SID, long roomtypes_id){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        long User_level = Usermanagement.getInstance().getUserLevelByID(User_ID);
        return Roommanagement.getInstance().getPublicRooms(User_level, roomtypes_id);
	}
	
}
