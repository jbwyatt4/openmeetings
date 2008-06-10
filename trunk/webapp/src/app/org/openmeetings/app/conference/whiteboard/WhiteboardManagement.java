package org.openmeetings.app.conference.whiteboard;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmeetings.app.remote.Application;

public class WhiteboardManagement {
	
	private static final Log log = LogFactory.getLog(WhiteboardManagement.class);

	private WhiteboardManagement() {}
	
	private static WhiteboardManagement instance = null;
	
	public static synchronized WhiteboardManagement getInstance() {
		if (instance == null) {
			instance = new WhiteboardManagement();
		}
		return instance;
	}
	
	public void addWhiteBoardObject(Long room_id, HashMap whiteboardObj) {
		try {
			log.debug("addWhiteBoardObject: "+whiteboardObj);
			
			log.debug("whiteboardObj 0: Event: "+whiteboardObj.get(0));
			log.debug("whiteboardObj 1: Event: "+whiteboardObj.get(1));
			log.debug("whiteboardObj 2: Event: "+whiteboardObj.get(2));
			log.debug("whiteboardObj 3: Event: "+whiteboardObj.get(3));
			
			//log.debug("whiteboardObj NUMB3: Event: "+whiteboardObj.get(3).getClass().getName());
			
			Date dateOfEvent = (Date) whiteboardObj.get(1);
			String action = whiteboardObj.get(2).toString();	
			Map actionObject = (Map) whiteboardObj.get(3);
			
			log.debug("action: "+action);
			
			if (action.equals("draw") || action.equals("redo")){
				HashMap<String,Map> roomList = Application.getWhiteBoardObjectListByRoomId(room_id);
				
				//log.debug(actionObject);
				//log.debug(actionObject.size()-1);
				//log.debug(actionObject.get(actionObject.size()-1));
				
				String objectOID = actionObject.get(actionObject.size()-1).toString();
				log.debug("objectOID: "+objectOID);
				roomList.put(objectOID, actionObject);
				Application.setWhiteBoardObjectListRoomObj(room_id, roomList);
			} else if (action.equals("clear")) {
				HashMap<String,Map> roomList = Application.getWhiteBoardObjectListByRoomId(room_id);
				roomList = new HashMap<String,Map>();
				Application.setWhiteBoardObjectListRoomObj(room_id, roomList);
			} else if (action.equals("delete") && action.equals("undo")) {
				HashMap<String,Map> roomList = Application.getWhiteBoardObjectListByRoomId(room_id);
				String objectOID = actionObject.get(actionObject.size()-1).toString();
				roomList.remove(objectOID);
				Application.setWhiteBoardObjectListRoomObj(room_id, roomList);
			} else if (action.equals("size") || action.equals("editProp") 
					|| action.equals("editText")) {
				HashMap<String,Map> roomList = Application.getWhiteBoardObjectListByRoomId(room_id);
				String objectOID = actionObject.get(actionObject.size()-1).toString();
				Map roomItem = roomList.get(objectOID);
				roomList.put(objectOID, actionObject);
				Application.setWhiteBoardObjectListRoomObj(room_id, roomList);
			} else {
				log.warn("Unkown Type: "+action+" actionObject: "+actionObject);
			}
			
		} catch (Exception err) {
			log.error("[addWhiteBoardObject]",err);
		}
	}
	
}
