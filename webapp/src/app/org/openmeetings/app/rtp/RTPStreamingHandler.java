package org.openmeetings.app.rtp;

import java.util.HashMap;
import java.util.Iterator;

import org.openmeetings.app.data.basic.Sessionmanagement;
import org.openmeetings.app.data.conference.Roommanagement;
import org.openmeetings.app.data.user.Usermanagement;
import org.openmeetings.app.hibernate.beans.rooms.Rooms;
import org.openmeetings.servlet.outputhandler.ScreenRequestHandler;
import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;

public class RTPStreamingHandler {
	
	
		private static final Logger log = Red5LoggerFactory.getLogger(ScreenRequestHandler.class, "openmeetings");
	
		/** Contains all RTPSessions*/
		public static HashMap<Rooms, RTPScreenSharingSession> rtpSessions = new HashMap<Rooms, RTPScreenSharingSession>();

		/** Minimal Limit for valid RTP Ports */
		public static final int minimalRTPPort = 22220;
		
		/** Maximum for valid RTP Ports */
		public static final int maximalRTPPort = 24000;
		
		/** Define The Port a Sharer should send his RTPStream on */
		//---------------------------------------------------------------------------------------------
		public static int getNextFreeRTPPort(){
			log.debug("getNextFreeRTPPort");
			
			Iterator<Rooms> riter = rtpSessions.keySet().iterator();
			
			int currentPort = minimalRTPPort;
			
			if(rtpSessions.size() < 1)
				return currentPort;
			
			
			boolean portBlocked = true;
			
			while(portBlocked){
			
				while(riter.hasNext()){
					Rooms r = riter.next();
					RTPScreenSharingSession session = rtpSessions.get(r);
				
					if(session.getIncomingRTPPort() == currentPort){
						portBlocked = true;
						break;
					}
				}
				
				if(portBlocked)
					currentPort ++;
			}
			
			return currentPort;
			
		}
		//---------------------------------------------------------------------------------------------
		
		/**
		 * retrieving Session data for Room 
		 */
		public static RTPScreenSharingSession getSessionForRoom(String room, String sid) throws Exception{
			log.debug("getSessionForRoom");
			
			if(room == null || room.length() <1)
				throw new Exception("InputVal room not valid");
			
			Long users_id = Sessionmanagement.getInstance().checkSession(sid);
			Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);

		
			Rooms myRoom= Roommanagement.getInstance().getRoomById(user_level, Long.parseLong(room));
		
			if(myRoom == null)
				throw new Exception("no room available for ID " + room);
			
			if(!rtpSessions.containsKey(myRoom))
				throw new Exception("no RTPSession available for Room " + room);
		
			return rtpSessions.get(myRoom);
		}
		
		
}
