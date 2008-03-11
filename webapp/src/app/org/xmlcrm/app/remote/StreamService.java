package org.xmlcrm.app.remote;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.LinkedHashMap;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XppDriver;

import org.red5.server.stream.ClientBroadcastStream;
import org.red5.server.api.IConnection;
import org.red5.server.api.IScope;
import org.red5.server.api.Red5;
import org.red5.server.api.service.IPendingServiceCall;
import org.red5.server.api.service.IPendingServiceCallback;
import org.red5.server.api.service.IServiceCapableConnection;

import org.xmlcrm.app.conference.videobeans.RoomClient;
import org.xmlcrm.app.data.basic.AuthLevelmanagement;
import org.xmlcrm.app.data.basic.Sessionmanagement;
import org.xmlcrm.app.data.user.Usermanagement;
import org.xmlcrm.utils.math.Calender;
import org.xmlcrm.app.hibernate.beans.user.Users;
import org.xmlcrm.app.hibernate.beans.domain.Organisation_Users;
import org.xmlcrm.app.data.conference.Roommanagement;
import org.xmlcrm.app.hibernate.beans.rooms.Rooms;
import org.xmlcrm.app.hibernate.beans.rooms.Rooms_Organisation;
import org.xmlcrm.app.hibernate.beans.recording.Recording;
import org.xmlcrm.app.data.record.Recordingmanagement;

/**
 * 
 * @author sebastianwagner
 *
 */
public class StreamService implements IPendingServiceCallback {
	
	private static String fileNameXML = "recording_";
	private static String folderForRecordings = "recorded";
	
	
	private static final Log log = LogFactory.getLog(StreamService.class);
	
	private static LinkedHashMap<String,LinkedHashMap<String,Object>> roomRecordingList = new LinkedHashMap<String,LinkedHashMap<String,Object>>();
	
	/**
	 * this function starts recording a Conference (Meeting or Event)
	 * 
	 * the temp-value contains an attribute called *streamlist*
	 * the streamlist all room-streaming events are inside
	 * avset means this is an streaming event where only the audio-video settings have been altered
	 * 
	 * Meaning of the boolean Values in the *streamlist* :
	 * streamstart = true && avset = false => an initial user (a user which has been already in the room when you started the recorder-session) 
	 * streamstart = true && avset = true => new user did start streaming
	 * streamstart = false && avset =  true => new user did has chosen his av/settings, if he has chosen *n* 
	 * 									(rcl.avsettings == "n") then he will not send any streamstart event cause he
	 * 									does not stream audio/video => show the picture of the user
	 * streamstart = false && avset = false => user did finished streaming
	 * 									if the user has rcl.avsettings != "n" stop playing the stream
	 * 
	 * 
	 * Meaning of the boolean Values in the *roomclients* :
	 * roomenter =  true => a new user OR initial user
	 * roomenter = false => a user has left the Conference 
	 * 
	 * @param conferenceType
	 * @param initwhiteboardvars
	 * @param roomRecordingsTableString
	 * @param comment
	 * @return
	 */
	public String recordMeetingStream(String conferenceType, Object initwhiteboardvars, 
			String roomRecordingsTableString, String comment){
		try {
			IConnection current = Red5.getConnectionLocal();
			RoomClient currentClient = Application.getClientList().get(current.getClient().getId());
			String roomname = currentClient.getUserroom();
			String orgdomain = currentClient.getDomain();
			String recordingName = generateFileName(Long.valueOf(currentClient.getBroadCastID()).toString());
			currentClient.setIsRecording(true);
			currentClient.setRoomRecordingName(recordingName);			
			Application.getClientList().put(current.getClient().getId(), currentClient);
			
			LinkedHashMap<String,Object> roomRecording = new LinkedHashMap<String,Object>();
			roomRecording.put("conferenceType", conferenceType);
			roomRecording.put("room_setup", Roommanagement.getInstance().getRoomById(currentClient.getRoom_id()));
			roomRecording.put("roomRecordingsTableString", roomRecordingsTableString);
			roomRecording.put("comment", comment);
			
			List<LinkedHashMap<String,Object>> roomStreams = new LinkedList<LinkedHashMap<String,Object>>();
			List<LinkedHashMap<String,Object>> roomClients = new LinkedList<LinkedHashMap<String,Object>>();
			
			//get all stream and start recording them
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				IConnection conn = it.next();
				if (conn instanceof IServiceCapableConnection) {
					RoomClient rcl = Application.getClientList().get(conn.getClient().getId());
					log.error("is this users still alive? :"+rcl);
					//Check if the Client is in the same room and same domain 
					if(roomname.equals(rcl.getUserroom()) && orgdomain.equals(rcl.getDomain())){
						
						((IServiceCapableConnection) conn).invoke("startedRecording",new Object[] { currentClient }, this);
						
						String remoteAdress = conn.getRemoteAddress();
						Date startDate = new Date();
						
						//add streamings to record File
						if (!conferenceType.equals("audience") || rcl.getIsMod()){
							LinkedHashMap<String,Object> roomStream = new LinkedHashMap<String,Object>();
							
							String streamName = generateFileName(Long.valueOf(rcl.getBroadCastID()).toString());
							
							//if the user does publish av, a, v
							if (!rcl.getAvsettings().equals("n")){	
								recordShow(conn, rcl.getBroadCastID(), streamName);
							} 
							
							roomStream.put("streamName", streamName);
							//stream starting
							roomStream.put("streamstart", true);
							//is not only a avset-event
							roomStream.put("avset", false);
							roomStream.put("remoteAdress", remoteAdress);
							roomStream.put("startdate",startDate);
							roomStream.put("starttime",0);
							roomStream.put("rcl", rcl);
							
							roomStreams.add(roomStream);
						} 

						//add room Clients enter/leave Events to record File
						LinkedHashMap<String,Object> roomClient = new LinkedHashMap<String,Object>();						
						roomClient.put("remoteAdress", remoteAdress);
						roomClient.put("roomenter", true);
						roomClient.put("startdate",startDate);
						roomClient.put("starttime",0);
						roomClient.put("rcl", rcl);
						
						roomClients.add(roomClient);
					}
				}
			}
			
			roomRecording.put("initwhiteboardvars", initwhiteboardvars);
			roomRecording.put("recordingName", recordingName);
			roomRecording.put("starttime", new java.util.Date());
			roomRecording.put("startedby", currentClient);
			
			//add Room Client enter/leave events
			roomRecording.put("roomclients", roomClients);
			
			//add Stream-Events
			roomRecording.put("streamlist", roomStreams);
			
			//add Whiteboard-Events
			LinkedList<Object> whiteBoardEvents = new LinkedList<Object>();
			roomRecording.put("whiteboard", whiteBoardEvents);
			
			//add Chat-Events
			LinkedList<Object> chatvaluesEvents = new LinkedList<Object>();
			roomRecording.put("chatvalues", chatvaluesEvents);
			
			roomRecordingList.put(recordingName, roomRecording);
			
			return recordingName;
		} catch (Exception err) {
			log.error("[recordMeetingStream]",err);
		}
		return null;
	}
	
	public Long stopRecordMeetingStream(String roomrecordingName){
		try {
			IConnection current = Red5.getConnectionLocal();
			RoomClient currentClient = Application.getClientList().get(current.getClient().getId());

			LinkedHashMap<String,Object> roomRecording = roomRecordingList.get(roomrecordingName);
			String roomname = currentClient.getUserroom();
			String orgdomain = currentClient.getDomain();	

			String conferenceType = (String) roomRecording.get("conferenceType");
			
			//get all stream and stop recording them
			//Todo: Check that nobody does Recording at the same time Issue 253
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				IConnection conn = it.next();
				if (conn instanceof IServiceCapableConnection) {
					RoomClient rcl = Application.getClientList().get(conn.getClient().getId());
					log.debug("is this users still alive? :"+rcl);
					//Check if the Client is in the same room and same domain 
					if(roomname.equals(rcl.getUserroom()) && orgdomain.equals(rcl.getDomain())){
						((IServiceCapableConnection) conn).invoke("stopedRecording",new Object[] { currentClient }, this);
					}
				}
			}
			return stopRecordAndSave(current, roomrecordingName, currentClient);
		} catch (Exception err) {
			log.error("[stopRecordAndSave]",err);
		}
		return new Long(-1);
	}
	
	public static Long stopRecordAndSave(IConnection current, String roomrecordingName, RoomClient currentClient){
		try {
			log.error("stopRecordAndSave "+currentClient.getUsername()+","+currentClient.getUserip());
			LinkedHashMap<String,Object> roomRecording = roomRecordingList.get(roomrecordingName);
			
			String roomname = currentClient.getUserroom();
			String orgdomain = currentClient.getDomain();	
			currentClient.setIsRecording(false);
			currentClient.setRoomRecordingName("");
			Application.getClientList().put(current.getClient().getId(), currentClient);
			
			
			String conferenceType = (String) roomRecording.get("conferenceType");
			
			//get all stream and stop recording them
			//Todo: Check that nobody does Recording at the same time Issue 253
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				IConnection conn = it.next();
				if (conn instanceof IServiceCapableConnection) {
					RoomClient rcl = Application.getClientList().get(conn.getClient().getId());
					log.error("is this users still alive? :"+rcl);
					//Check if the Client is in the same room and same domain 
					if(roomname.equals(rcl.getUserroom()) && orgdomain.equals(rcl.getDomain())){
						if (!conferenceType.equals("audience") || rcl.getIsMod()){
							//stop the recorded flv and add the event to the notifications
							log.error("*** sendClientBroadcastNotifications Any Client is Recording - stop that");
							stopRecordingShowForClient(conn, rcl, roomrecordingName, true);
						}
					}
				}
			}				
			
			String newRecordFileName = (String) roomRecording.get("roomRecordingsTableString");
			String comment = (String) roomRecording.get("comment");
			
			Date starttime = (Date) roomRecording.get("starttime");
			Date endtime =  new java.util.Date();
			Long duration = endtime.getTime() - starttime.getTime();
			roomRecording.put("endtime",endtime);
			roomRecording.put("enduser", currentClient);
			roomRecording.put("recordname", newRecordFileName);
			
			RoomClient startedClient = (RoomClient) roomRecording.get("startedby");
			Long recordedby = startedClient.getUser_id();
			Users us = null;
			if (recordedby!=null && recordedby>0){
				us = Usermanagement.getInstance().getUser(recordedby);
			}
			
			XStream xStream = new XStream(new XppDriver());
			xStream.setMode(XStream.NO_REFERENCES);
			String xmlString = xStream.toXML(roomRecording);
			
			//log.error(xmlString);
			
			//make persistent
			Long recording_id = Recordingmanagement.getInstance().addRecording(newRecordFileName, duration, "", currentClient.getRoom_id(), us, comment);
			
			//save XML to Disk
			IScope scope = Red5.getConnectionLocal().getScope().getParent();
			String current_dir = scope.getResource("upload/").getFile().getAbsolutePath();
			//System.out.println(current_dir  + File.separatorChar + this.folderForRecordings);
			File f = new File(current_dir  + File.separatorChar + folderForRecordings);
			if (!f.exists()){
				f.mkdir();
			}
			String fileName = f.getAbsolutePath() + File.separatorChar + fileNameXML+recording_id+".xml";
			//System.out.println("fileName"+fileName);
			PrintWriter pw = new PrintWriter(new FileWriter(fileName));
		    pw.println(xmlString);
		    pw.flush();
		    pw.close();
//		    
//		    List<LinkedHashMap<String,Object>> roomClients = (List<LinkedHashMap<String,Object>>)roomRecording.get("roomclients");
//		    for (Iterator<LinkedHashMap<String,Object>> iter = roomClients.iterator();iter.hasNext();){
//		    	LinkedHashMap<String,Object> roomClient = iter.next();
//		    	
//		    	log.debug("###### NEW RRR1: "+roomClient.get("roomenter"));
//				log.debug("###### NEW RRR2: "+roomClient.get("startdate"));
//				log.debug("###### NEW RRR3: "+roomClient.get("starttime"));
//				RoomClient rcl = (RoomClient) roomClient.get("rcl");
//				log.debug("###### NEW RRR4: "+rcl.getBroadCastID());
//				log.debug("###### NEW RRR5: "+rcl.getConnectedSince());
//				log.debug("###### NEW RRR6: "+rcl.getPublicSID());
//				log.debug("###### NEW RRR7: "+rcl.getUserip());   
//				
//		    }
		    
			//remove recording from Temp - List
			roomRecordingList.remove(roomrecordingName);
			
			return recording_id;
		} catch (Exception err) {
			log.error("[stopRecordAndSave]",err);
		}
		return new Long(-1);
	}	

	/**
	 * Start recording the publishing stream for the specified
	 *
	 * @param conn
	 */
	private static void recordShow(IConnection conn, long broadcastid, String streamName) throws Exception {
		log.debug("Recording show for: " + conn.getScope().getContextPath());
		log.debug("Name of CLient and Stream to be recorded: "+broadcastid);		
		log.debug("Application.getInstance()"+Application.getInstance());
		log.debug("Scope "+conn);
		log.debug("Scope "+conn.getScope());
		// Get a reference to the current broadcast stream.
		ClientBroadcastStream stream = (ClientBroadcastStream) Application.getInstance()
				.getBroadcastStream(conn.getScope(), Long.valueOf(broadcastid).toString());
		try {
			// Save the stream to disk.
			stream.saveAs(streamName, false);
		} catch (Exception e) {
			log.error("Error while saving stream: " + streamName, e);
		}
	}
	
	/**
	 * if doStopStream = false this use is already away, no connection can be stoped
	 * @param conn
	 * @param rcl
	 * @param roomrecordingName
	 * @param doStopStream
	 */
	public static void stopRecordingShowForClient(IConnection conn, RoomClient rcl, 
			String roomrecordingName, boolean doStopStream) {
		try {
			StreamService.addRoomClientEnterEventFunc(rcl, roomrecordingName, rcl.getUserip(), false);
			log.error("### stopRecordingShowForClient: "+rcl.getIsRecording()+","+rcl.getUsername()+","+rcl.getUserip());
			
			LinkedHashMap<String,Object> roomRecording = roomRecordingList.get(roomrecordingName);
			Date recordingsStartTime = (Date) roomRecording.get("starttime");
			Date currentDate = new Date();
			List<LinkedHashMap<String,Object>> roomStreams = (List<LinkedHashMap<String,Object>>)roomRecording.get("streamlist");
			
			LinkedHashMap<String,Object> roomStream = new LinkedHashMap<String,Object>();
			
			roomStream.put("streamName", "");
			roomStream.put("streamstart", false);
			roomStream.put("avset", false);
			roomStream.put("remoteAdress", conn.getRemoteAddress());
			roomStream.put("startdate",currentDate);
			roomStream.put("starttime",currentDate.getTime()-recordingsStartTime.getTime());
			roomStream.put("rcl", rcl);
			
			roomStreams.add(roomStream);
			
			roomRecording.put("streamlist",roomStreams);
			roomRecordingList.put(roomrecordingName, roomRecording);

			if ((rcl.getAvsettings().equals("a") || rcl.getAvsettings().equals("v") 
					|| rcl.getAvsettings().equals("av")) && doStopStream){
				stopRecordingShow(conn,rcl.getBroadCastID());
			}
			
		} catch (Exception err) {
			log.error("[stopRecordingShowForClient]",err);
		}
	}

	/**
	 * Stops recording the publishing stream for the specified
	 * IConnection.
	 *
	 * @param conn
	 */
	public static void stopRecordingShow(IConnection conn, long broadcastId) throws Exception {
		log.debug("** stopRecordingShow: "+conn);
		log.debug("### Stop recording show for broadcastId: "+ broadcastId + " || " + conn.getScope().getContextPath());
		ClientBroadcastStream stream = (ClientBroadcastStream) Application.getInstance().
												getBroadcastStream(conn.getScope(), Long.valueOf(broadcastId).toString());
		// Stop recording.
		stream.stopRecording();
	}
	
	public static String generateFileName(String streamid) throws Exception{
		String dateString = Calender.getTimeForStreamId(new java.util.Date());
		return streamid+"_"+dateString;
		
	}
	
	public List<Recording> getAllRecordingsForUser(String SID){
		try {
	        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
	        Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);  

	        if (AuthLevelmanagement.getInstance().checkUserLevel(user_level)){
	        	
	        	String whereClause = "";
	        	
	        	int i = 0;
	        	List<Rooms> rooms = Roommanagement.getInstance().getPublicRooms(user_level);
	        	for (Iterator<Rooms> iter = rooms.iterator();iter.hasNext();){
	        		Rooms room = iter.next();
	        		if (i==0) whereClause += " (";
	        		else whereClause += " OR";
	        		whereClause += " c.rooms.rooms_id = "+room.getRooms_id()+" ";
	        		i++;
	        	}
				
				Users us = Usermanagement.getInstance().getUser(users_id);
				
				for (Iterator<Organisation_Users> iter = us.getOrganisation_users().iterator();iter.hasNext();) {
					Organisation_Users orgUser = iter.next();
					Long organisation_id = orgUser.getOrganisation().getOrganisation_id();
					
					List<Rooms_Organisation> rOrgList = Roommanagement.getInstance().getRoomsOrganisationByOrganisationId(3, organisation_id);
					for (Iterator<Rooms_Organisation> iterOrgList = rOrgList.iterator();iterOrgList.hasNext();){
						Rooms_Organisation rOrg = iterOrgList.next();
		        		if (i==0) whereClause += " (";
		        		else whereClause += " OR";						
						whereClause += " c.rooms.rooms_id = "+rOrg.getRoom().getRooms_id()+" ";
						i++;
					}
					
				}
				if (whereClause.length()!=0) whereClause += ") AND ";
				List<Recording> rList = Recordingmanagement.getInstance().getRecordingsByWhereClause(whereClause);
				
				for (Iterator<Recording> iter = rList.iterator();iter.hasNext();) {
					Recording rec = iter.next();
					rec.setStarttimeAsString(Calender.getDateWithTimeByMiliSeconds(rec.getStarttime()));
				}
				
				return rList;
	        }

			
			
		} catch (Exception err) {
			log.error("[getAllRecordings]",err);
		}		
		return null;
	}
	
	public Recording getRecordingById(String SID, Long recording_id) {
		try {
	        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
	        Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);  

	        if (AuthLevelmanagement.getInstance().checkUserLevel(user_level)){
	        	Recording rec = Recordingmanagement.getInstance().getRecordingById(recording_id);
	        	
				XStream xStream = new XStream(new XppDriver());
				xStream.setMode(XStream.NO_REFERENCES);
				
				IScope scope = Red5.getConnectionLocal().getScope().getParent();
				String current_dir = scope.getResource("upload/").getFile().getAbsolutePath();
				//System.out.println(current_dir  + File.separatorChar + this.folderForRecordings);
				File f = new File(current_dir + File.separatorChar + folderForRecordings);
				if (!f.exists()){
					f.mkdir();
				}
				String fileName = f.getAbsolutePath() + File.separatorChar + fileNameXML+recording_id+".xml";
				//System.out.println("fileName"+fileName);
				BufferedReader reader = new BufferedReader(new FileReader(fileName));
			    String xmlString = "";
			    while (reader.ready()) {
			    	xmlString += reader.readLine();
			    }
				rec.setRoomRecording((LinkedHashMap<String, Object>)xStream.fromXML(xmlString));
				
				return rec;
	        }
		} catch (Exception err) {
			log.error("[getRecordingById]",err);
		}		
		return null;
	}
	
	public static void addRecordingByStreamId(IConnection conn, String streamId, 
			RoomClient rcl, String roomrecordingName) {
		try {
			LinkedHashMap<String,Object> roomRecording = roomRecordingList.get(roomrecordingName);
			List<LinkedHashMap<String,Object>> roomStreams = (List<LinkedHashMap<String,Object>>)roomRecording.get("streamlist");
			
			String conferenceType = (String) roomRecording.get("conferenceType");
			
			if (!conferenceType.equals("audience") || rcl.getIsMod()){
				Date recordingsStartTime = (Date) roomRecording.get("starttime");
				Date currentDate = new Date();
				
				LinkedHashMap<String,Object> roomStream = new LinkedHashMap<String,Object>();
				
				String streamName = generateFileName(Long.valueOf(rcl.getBroadCastID()).toString());
				String remoteAdress = conn.getRemoteAddress();
				
				//if the user does publish av, a, v
				if (!rcl.getAvsettings().equals("n")){
					recordShow(conn, rcl.getBroadCastID(), streamName);
				}
				
				roomStream.put("streamName", streamName);
				//this is a recording event
				roomStream.put("streamstart", true);
				//this is not an av event
				roomStream.put("avset", true);
				roomStream.put("remoteAdress", remoteAdress);
				roomStream.put("startdate",new java.util.Date());
				roomStream.put("starttime",currentDate.getTime()-recordingsStartTime.getTime());
				roomStream.put("rcl", rcl);
				
				roomStreams.add(roomStream);
				
				roomRecording.put("streamlist",roomStreams);
				roomRecordingList.put(roomrecordingName, roomRecording);
			}
		} catch (Exception err) {
			log.error("[addRecordingByStreamId]",err);
		}	
	}
	
	public static void addWhiteBoardEvent(String roomrecordingName,Object vars) {
		try {
			
			log.error("roomrecordingName: "+roomrecordingName);
			LinkedHashMap<String,Object> roomRecording = roomRecordingList.get(roomrecordingName);
			
			Date recordingsStartTime = (Date) roomRecording.get("starttime");
			Date currentDate = new Date();
			
			LinkedList<Object> whiteBoardEvents = (LinkedList<Object>) roomRecording.get("whiteboard");
			
			LinkedHashMap<String,Object> whiteBoardEvent = new LinkedHashMap<String,Object>();
			whiteBoardEvent.put("starttime",currentDate.getTime()-recordingsStartTime.getTime());
			whiteBoardEvent.put("action", vars);
			
			whiteBoardEvents.add(whiteBoardEvent);
			roomRecording.put("whiteboard", whiteBoardEvents);
			roomRecordingList.put(roomrecordingName, roomRecording);
			
		} catch (Exception err) {
			log.error("[addRecordingByStreamId]",err);
		}	
	}
	
	public static void addChatEvent(String roomrecordingName,Object vars) {
		try {
			LinkedHashMap<String,Object> roomRecording = roomRecordingList.get(roomrecordingName);
			
			Date recordingsStartTime = (Date) roomRecording.get("starttime");
			Date currentDate = new Date();
			
			LinkedList<Object> chatvaluesEvents = (LinkedList<Object>) roomRecording.get("chatvalues");
			
			LinkedHashMap<String,Object> chatvaluesEvent = new LinkedHashMap<String,Object>();
			chatvaluesEvent.put("starttime",currentDate.getTime()-recordingsStartTime.getTime());
			chatvaluesEvent.put("action", vars);
			
			chatvaluesEvents.add(chatvaluesEvent);
			roomRecording.put("chatvalues", chatvaluesEvents);
			roomRecordingList.put(roomrecordingName, roomRecording);
			
		} catch (Exception err) {
			log.error("[addRecordingByStreamId]",err);
		}	
	}
	
	
	public static void addRoomClientAVSetEvent(RoomClient rcl, String roomrecordingName, 
			String remoteAdress) {
		try {
			LinkedHashMap<String,Object> roomRecording = roomRecordingList.get(roomrecordingName);
			Date recordingsStartTime = (Date) roomRecording.get("starttime");
			Date currentDate = new Date();
			List<LinkedHashMap<String,Object>> roomStreams = (List<LinkedHashMap<String,Object>>)roomRecording.get("streamlist");
			
			LinkedHashMap<String,Object> roomStream = new LinkedHashMap<String,Object>();
						
			roomStream.put("streamName", "");
			roomStream.put("streamstart", false);
			roomStream.put("avset", true);
			roomStream.put("remoteAdress", remoteAdress);
			roomStream.put("startdate",currentDate);
			roomStream.put("starttime",currentDate.getTime()-recordingsStartTime.getTime());
			roomStream.put("rcl", rcl);
			
			roomStreams.add(roomStream);
			
			roomRecording.put("streamlist",roomStreams);
			roomRecordingList.put(roomrecordingName, roomRecording);			
		} catch (Exception err) {
			log.error("[addRoomClientAVSetEvent]",err);
		}	
	}	
	
	public static void addRoomClientEnterEventFunc(RoomClient rcl, String roomrecordingName, 
				String remoteAdress, boolean enter) {
		try {
			LinkedHashMap<String,Object> roomRecording = roomRecordingList.get(roomrecordingName);
			Date recordingsStartTime = (Date) roomRecording.get("starttime");
			Date currentDate = new Date();
			List<LinkedHashMap<String,Object>> roomClients = (List<LinkedHashMap<String,Object>>)roomRecording.get("roomclients");
			
			LinkedHashMap<String,Object> roomClient = new LinkedHashMap<String,Object>();						
			roomClient.put("remoteAdress", remoteAdress);
			roomClient.put("roomenter", enter);
			roomClient.put("startdate",currentDate);
			roomClient.put("starttime",currentDate.getTime()-recordingsStartTime.getTime());
//			
//			if (!enter) {
//				log.debug("###### NEW USER1: "+rcl.getBroadCastID());
//				log.debug("###### NEW USER2: "+rcl.getConnectedSince());
//				log.debug("###### NEW USER3: "+rcl.getPublicSID());
//				log.debug("###### NEW USER4: "+rcl.getUserip());
//			}
			roomClient.put("rcl", rcl);
			
			roomClients.add(roomClient);
			
			roomRecording.put("roomclients",roomClients);
			roomRecordingList.put(roomrecordingName, roomRecording);			
		} catch (Exception err) {
			log.error("[addRoomClientEnterEvent]",err);
		}	
	}	

	public Long clientCancelRecording(String roomrecordingName){
		try {
			
			IConnection current = Red5.getConnectionLocal();
			RoomClient currentClient = Application.getClientList().get(current.getClient().getId());
			Long rooms_id = currentClient.getRoom_id();
			String roomname = currentClient.getUserroom();
			String orgdomain = currentClient.getDomain();	
			currentClient.setIsRecording(false);
			currentClient.setRoomRecordingName("");
			Application.getClientList().put(current.getClient().getId(), currentClient);
			
			LinkedHashMap<String,Object> roomRecording = roomRecordingList.get(roomrecordingName);
			String conferenceType = (String) roomRecording.get("conferenceType");
			
			//get all stream and stop recording them
			//Todo: Check that nobody does Recording at the same time Issue 253
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				IConnection conn = it.next();
				if (conn instanceof IServiceCapableConnection) {
					RoomClient rcl = Application.getClientList().get(conn.getClient().getId());
					log.debug("is this users still alive? :"+rcl);
					//Check if the Client is in the same room and same domain 
					if(roomname.equals(rcl.getUserroom()) && orgdomain.equals(rcl.getDomain())){
						((IServiceCapableConnection) conn).invoke("stopedRecording",new Object[] { currentClient }, this);
						if (!conferenceType.equals("audience") || rcl.getIsMod()){
							//if the user does publish av, a, v
							if (!rcl.getAvsettings().equals("n")){
								stopRecordingShow(conn,rcl.getBroadCastID());
							}
						}
					}
				}
			}
			
			roomRecordingList.remove(roomrecordingName);
			
			return new Long(1);
		} catch (Exception err) {
			log.error("[cancelRecording]",err);
		}
		return new Long(-1);
	}
	
	public RoomClient checkForRecording(){
		try {
			IConnection current = Red5.getConnectionLocal();
			RoomClient currentClient = Application.getClientList().get(current.getClient().getId());
			String roomname = currentClient.getUserroom();
			String orgdomain = currentClient.getDomain();	
			
			//Check if any client in the same room is recording at the moment
			
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				IConnection cons = it.next();
				log.debug("cons Host: "+cons);
				if (cons instanceof IServiceCapableConnection) {
					if (!cons.equals(current)){
						log.debug("sending roomDisconnect to " + cons);
						RoomClient rcl = Application.getClientList().get(cons.getClient().getId());
						//Check if the Client is in the same room and same domain except its the current one
						if(roomname.equals(rcl.getUserroom()) && orgdomain.equals(rcl.getDomain())){					
							if (rcl.getIsRecording()){
								return rcl;
							}
						}
					}
				}
			}
			
		} catch (Exception err) {
			log.error("[cancelRecording]",err);
		}
		return null;
	}

	public void resultReceived(IPendingServiceCall arg0) {
		// TODO Auto-generated method stub
		log.debug("resultReceived"+arg0);
	}
	
	public Long deleteRecordedFile(String SID, Long recording_id){
		try {
	    	Long users_id = Sessionmanagement.getInstance().checkSession(SID);
	    	Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);
	    	if (AuthLevelmanagement.getInstance().checkUserLevel(user_level)){
	    		Recording rec = Recordingmanagement.getInstance().getRecordingById(recording_id);
	    		if (rec!=null) {
	    			rec.setDeleted("true");
	    			Recordingmanagement.getInstance().updateRecording(rec);
	    			return new Long(recording_id);
	    		}
	    	}
		} catch (Exception err) {
			log.error("[deleteRecordedFile]",err);
		}
		return new Long(-1);
	}
	
}
