package org.openmeetings.app.conference.whiteboard;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
			
		} catch (Exception err) {
			log.error("[addWhiteBoardObject]",err);
		}
	}
}
