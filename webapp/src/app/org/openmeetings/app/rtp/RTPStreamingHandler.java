package org.openmeetings.app.rtp;

import java.util.HashMap;
import java.util.Iterator;

import org.openmeetings.app.data.basic.Sessionmanagement;
import org.openmeetings.app.data.conference.Roommanagement;
import org.openmeetings.app.data.user.Usermanagement;
import org.openmeetings.app.hibernate.beans.rooms.Rooms;
import org.openmeetings.app.hibernate.beans.user.Users;
import org.openmeetings.app.remote.red5.ScopeApplicationAdapter;
import org.openmeetings.servlet.outputhandler.ScreenRequestHandler;
import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;

/**
 * 
 * @author o.becherer
 *
 */
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

			// TODO also use maximum RTP Port to give admins a chance to administrate a range 
			// of ports that should be open ;-)
			
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
		 * Retrieving Session data for Room 
		 */
		//---------------------------------------------------------------------------------------------
		public static RTPScreenSharingSession getSessionForRoom(String room, String sid) throws Exception{
			log.debug("getSessionForRoom");
			
			if(room == null || room.length() <1)
				throw new Exception("InputVal room not valid");
			
			Long users_id = Sessionmanagement.getInstance().checkSession(sid);
			Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);

			Rooms myRoom= Roommanagement.getInstance().getRoomById(user_level, Long.parseLong(room));
		
			if(myRoom == null)
				throw new Exception("no room available for ID " + room);
						
			Iterator<Rooms> miter = rtpSessions.keySet().iterator();
			
			RTPScreenSharingSession session = null;
			
			while(miter.hasNext()){
				Rooms rooms = miter.next();
				
				System.out.println("Rooms id in Cache : " + rooms.getRooms_id());
				
				if(rooms.getRooms_id().intValue() == myRoom.getRooms_id().intValue()){
					session = rtpSessions.get(rooms);
					
					if(session == null)
						log.error("No Session for ID " + myRoom.getRooms_id());
				}
				else
					log.debug("not equal ");
			}
			
			if(session == null)
				throw new Exception("no RTPSession for Room " + room);
			
			return session;
		}
		//---------------------------------------------------------------------------------------------
		
		
		/**
		 * Store Session for Room
		 */
		//---------------------------------------------------------------------------------------------
		public static RTPScreenSharingSession storeSessionForRoom(String room, Long sharing_user_id) throws Exception{
			log.debug("storeSessionForRoom : " + room);
			
			RTPScreenSharingSession session = new RTPScreenSharingSession();
			
			if(room == null || room.length() <1)
				throw new Exception("InputVal room not valid");
			
			Long user_level = Usermanagement.getInstance().getUserLevelByID(sharing_user_id);
			Rooms myRoom= Roommanagement.getInstance().getRoomById(user_level, Long.parseLong(room));
			
			if(myRoom == null)
				throw new Exception("no Room for ID " + room);
			
			// Define Room
			session.setRoom(myRoom);
			
			Users user = Usermanagement.getInstance().getUserById(sharing_user_id);
			
			if(user == null)
				throw new Exception("No User for id " + sharing_user_id);
			
			session.setSharingUser(user);
			
			// RTP Sharer IP + Port + Streamdata are defined on ServletCall (-> streamer start)
			
			rtpSessions.put(myRoom, session);
			
			return session;
			
		}
		//---------------------------------------------------------------------------------------------
		
		
		
}
