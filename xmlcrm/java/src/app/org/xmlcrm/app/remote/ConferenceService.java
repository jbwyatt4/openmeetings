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
        int users_id = Sessionmanagement.getInstance().checkSession(SID);
        long User_level = Usermanagement.getInstance().getUserLevelByID(users_id);
        return Roommanagement.getInstance().getRoomsOrganisationByOrganisationIdAndRoomType(User_level, organisation_id, roomtypes_id);
	}
	
	/**
	 * get a List of all public availible rooms
	 * @param SID
	 * @param organisation_id
	 * @return
	 */
	public List getRoomsPublic(String SID, long roomtypes_id){
        int users_id = Sessionmanagement.getInstance().checkSession(SID);
        long User_level = Usermanagement.getInstance().getUserLevelByID(users_id);
        return Roommanagement.getInstance().getPublicRooms(User_level, roomtypes_id);
	}
	
	/**
	 * 
	 * @param SID
	 * @param name
	 * @param roomtypes_id
	 * @return
	 */
	public Long addRoomPublic(String SID, String name, long roomtypes_id){
        int users_id = Sessionmanagement.getInstance().checkSession(SID);
        long User_level = Usermanagement.getInstance().getUserLevelByID(users_id);
        return Roommanagement.getInstance().addRoom(User_level, name, roomtypes_id, true);
	}
	
	/**
	 * 
	 * @param SID
	 * @param organisation_id
	 * @param name
	 * @param roomtypes_id
	 * @param ispublic
	 * @return
	 */
	public Long addRoomOrganisation(String SID, long organisation_id, String name, long roomtypes_id, boolean ispublic){
        int users_id = Sessionmanagement.getInstance().checkSession(SID);
        long User_level = Usermanagement.getInstance().getUserLevelByID(users_id);
        Long rooms_id = Roommanagement.getInstance().addRoom(User_level, name, roomtypes_id, ispublic);
        return Roommanagement.getInstance().addRoomToOrganisation(User_level, rooms_id, organisation_id);
	}
	
	/**
	 * 
	 * @param SID
	 * @param rooms_id
	 * @param name
	 * @param roomtypes_id
	 * @param ispublic
	 * @param comment
	 * @return
	 */
	public Long updateRoom(String SID, long rooms_id, String name, long roomtypes_id, boolean ispublic, String comment){
        int users_id = Sessionmanagement.getInstance().checkSession(SID);
        long User_level = Usermanagement.getInstance().getUserLevelByID(users_id);
        return Roommanagement.getInstance().updateRoom(User_level, rooms_id, roomtypes_id, name, ispublic, comment);
	}
	
	
	
}
