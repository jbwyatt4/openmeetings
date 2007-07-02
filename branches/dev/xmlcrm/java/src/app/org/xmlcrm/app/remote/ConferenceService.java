package org.xmlcrm.app.remote;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmlcrm.app.data.basic.Sessionmanagement;
import org.xmlcrm.app.data.beans.basic.SearchResult;
import org.xmlcrm.app.data.user.Usermanagement;
import org.xmlcrm.app.data.conference.Roommanagement;
import org.xmlcrm.app.hibernate.beans.rooms.Rooms;

/**
 * 
 * @author swagner
 *
 */
public class ConferenceService {
	
	private static final Log log = LogFactory.getLog(ConferenceService.class);
	
	/**
	 * get a List of all availible Rooms of this organisation
	 * @param SID
	 * @param organisation_id
	 * @return
	 */
	public List getRoomsByOrganisationAndType(String SID, long organisation_id, long roomtypes_id){
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        long User_level = Usermanagement.getInstance().getUserLevelByID(users_id);
        return Roommanagement.getInstance().getRoomsOrganisationByOrganisationIdAndRoomType(User_level, organisation_id, roomtypes_id);
	}
	
	/**
	 * gets all rooms of an organisation
	 * TODO:check if the requesting user is also member of that organisation
	 * @param SID
	 * @param organisation_id
	 * @return
	 */
	public SearchResult getRoomsByOrganisation(String SID, long organisation_id, int start, int max, String orderby, boolean asc){
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        long User_level = Usermanagement.getInstance().getUserLevelByID(users_id);
        return Roommanagement.getInstance().getRoomsOrganisationByOrganisationId(User_level, organisation_id, start, max, orderby, asc);
	}	
	
	/**
	 * get a List of all public availible rooms
	 * @param SID
	 * @param organisation_id
	 * @return
	 */
	public List getRoomsPublic(String SID, long roomtypes_id){
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        long User_level = Usermanagement.getInstance().getUserLevelByID(users_id);
        log.error("getRoomsPublic User_Level: "+User_level);
        return Roommanagement.getInstance().getPublicRooms(User_level, roomtypes_id);
	}
	
	/**
	 * 
	 * @param SID
	 * @return
	 */
	public List getRoomTypes(String SID){
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        long USER_LEVEL = Usermanagement.getInstance().getUserLevelByID(users_id);
        return Roommanagement.getInstance().getAllRoomTypes(USER_LEVEL);
	}
	
	/**
	 * 
	 * @param SID
	 * @param rooms_id
	 * @return
	 */
	public Rooms getRoomById(String SID, long rooms_id){
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        long USER_LEVEL = Usermanagement.getInstance().getUserLevelByID(users_id);
        return Roommanagement.getInstance().getRoomById(USER_LEVEL,rooms_id);
	}
	
	/**
	 * gets a list of all availible rooms
	 * @param SID
	 * @param start
	 * @param max
	 * @param orderby
	 * @param asc
	 * @return
	 */
	public SearchResult getRooms(String SID, int start, int max, String orderby, boolean asc){
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        long USER_LEVEL = Usermanagement.getInstance().getUserLevelByID(users_id);
        return Roommanagement.getInstance().getRooms(USER_LEVEL, start, max, orderby, asc);
	}
	
	/**
	 * get all Organisations of a room
	 * @param SID
	 * @param rooms_id
	 * @return
	 */
	public List getOrganisationByRoom(String SID,long rooms_id){
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        long USER_LEVEL = Usermanagement.getInstance().getUserLevelByID(users_id);
        return Roommanagement.getInstance().getOrganisationsByRoom(USER_LEVEL, rooms_id);
	}

	
	/**
	 * 
	 * @param SID
	 * @param name
	 * @param roomtypes_id
	 * @return
	 */
	public Long addRoomPublic(String SID, String name, long roomtypes_id){
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        long User_level = Usermanagement.getInstance().getUserLevelByID(users_id);
        return Roommanagement.getInstance().addRoom(User_level, name, roomtypes_id,"", true,null);
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
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        long User_level = Usermanagement.getInstance().getUserLevelByID(users_id);
        Long rooms_id = Roommanagement.getInstance().addRoom(User_level, name, roomtypes_id,"", ispublic, null);
        return Roommanagement.getInstance().addRoomToOrganisation(User_level, rooms_id, organisation_id);
	}
	
	/**
	 * 
	 * @param SID
	 * @param argObject
	 * @return
	 */
	public Long saveOrUpdateRoom(String SID, Object argObject){
		try {
	        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
	        long User_level = Usermanagement.getInstance().getUserLevelByID(users_id);
	        LinkedHashMap argObjectMap = (LinkedHashMap) argObject;
	        LinkedHashMap organisations = (LinkedHashMap) argObjectMap.get("organisations");
	        Long rooms_id = Long.valueOf(argObjectMap.get("rooms_id").toString()).longValue();
	        log.error("rooms_id "+rooms_id);
	        if (rooms_id==0){
	        	return Roommanagement.getInstance().addRoom(User_level, argObjectMap.get("name").toString(), 
	        			Long.valueOf(argObjectMap.get("roomtypes_id").toString()).longValue(),
	        			argObjectMap.get("comment").toString(),
	        			Boolean.valueOf(argObjectMap.get("ispublic").toString()),organisations);
	        } else if (rooms_id>0){
	        	return Roommanagement.getInstance().updateRoom(User_level, rooms_id, 
	        			Long.valueOf(argObjectMap.get("roomtypes_id").toString()).longValue(), 
	        			argObjectMap.get("name").toString(), Boolean.valueOf(argObjectMap.get("ispublic").toString()),
	        			argObjectMap.get("comment").toString(),organisations);
	        }
		} catch (Exception e){
			log.error("saveOrUpdateRoom",e);
		}
		return null;
	}
	
	/**
	 * 
	 * @param SID
	 * @param rooms_id
	 * @return
	 */
	public Long deleteRoom(String SID, long rooms_id){
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        long USER_LEVEL = Usermanagement.getInstance().getUserLevelByID(users_id);
        return Roommanagement.getInstance().deleteRoomById(USER_LEVEL, rooms_id);
	}
	
}
