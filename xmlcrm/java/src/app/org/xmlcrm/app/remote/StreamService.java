package org.xmlcrm.app.remote;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.LinkedHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XppDriver;

import org.red5.server.stream.ClientBroadcastStream;
import org.red5.server.api.IConnection;
import org.red5.server.api.Red5;
import org.red5.server.api.service.IServiceCapableConnection;

import org.xmlcrm.app.conference.videobeans.RoomClient;
import org.xmlcrm.app.data.basic.AuthLevelmanagement;
import org.xmlcrm.app.data.basic.Sessionmanagement;
import org.xmlcrm.app.data.user.Usermanagement;
import org.xmlcrm.utils.math.Calender;
import org.xmlcrm.app.hibernate.beans.user.Users;
import org.xmlcrm.app.hibernate.beans.domain.Organisation_Users;
import org.xmlcrm.app.data.conference.Roommanagement;
import org.xmlcrm.app.documents.CreateLibraryPresentation;
import org.xmlcrm.app.documents.LoadLibraryPresentation;
import org.xmlcrm.app.hibernate.beans.rooms.Rooms;
import org.xmlcrm.app.hibernate.beans.rooms.Rooms_Organisation;
import org.xmlcrm.app.hibernate.beans.recording.Recording;
import org.xmlcrm.app.data.record.Recordingmanagement;

/**
 * 
 * @author sebastianwagner
 *
 */
public class StreamService {
	
	private static final Log log = LogFactory.getLog(StreamService.class);
	
	private static LinkedHashMap<String,LinkedHashMap<String,Object>> roomRecordingList = new LinkedHashMap<String,LinkedHashMap<String,Object>>();
	
	public String recordMeetingStream(){
		try {
			IConnection current = Red5.getConnectionLocal();
			RoomClient currentClient = Application.getClientList().get(current.getClient().getId());
			String roomname = currentClient.getUserroom();
			String orgdomain = currentClient.getDomain();
			String recordingName = this.generateFileName(currentClient.getStreamid());
			currentClient.setIsRecording(true);
			currentClient.setRoomRecordingName(recordingName);
			Application.getClientList().put(current.getClient().getId(), currentClient);
			
			LinkedHashMap<String,Object> roomRecording = new LinkedHashMap<String,Object>();
			
			List<LinkedHashMap<String,Object>> roomStreams = new LinkedList<LinkedHashMap<String,Object>>();
			
			//get all stream and start recording them
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				IConnection conn = it.next();
				if (conn instanceof IServiceCapableConnection) {
					RoomClient rcl = Application.getClientList().get(conn.getClient().getId());
					log.error("is this users still alive? :"+rcl);
					//Check if the Client is in the same room and same domain 
					if(roomname.equals(rcl.getUserroom()) && orgdomain.equals(rcl.getDomain())){
						
						LinkedHashMap<String,Object> roomStream = new LinkedHashMap<String,Object>();
						
						String streamName = this.generateFileName(rcl.getStreamid());
						String remoteAdress = conn.getRemoteAddress();
						
						this.recordShow(conn, rcl.getStreamid(), streamName);
						
						roomStream.put("streamName", streamName);
						roomStream.put("remoteAdress", remoteAdress);
						roomStream.put("startdate",new java.util.Date());
						roomStream.put("starttime",0);
						roomStream.put("rcl", rcl);
						
						roomStreams.add(roomStream);
					}
				}
			}
			roomRecording.put("streamlist", roomStreams);
			roomRecording.put("recordingName", recordingName);
			roomRecording.put("starttime", new java.util.Date());
			roomRecording.put("startedby", currentClient);
			
			LinkedList<Object> whiteBoardEvents = new LinkedList<Object>();
			roomRecording.put("whiteboard", whiteBoardEvents);
			
			roomRecordingList.put(recordingName, roomRecording);
			
			return recordingName;
		} catch (Exception err) {
			log.error("[recordMeetingStream]",err);
		}
		return null;
	}
	
	public Long stopRecordMeetingStream(String recordingName, String newRecordFileName, String comment){
		try {
			log.error("stopRecordMeetingStream");
			
			IConnection current = Red5.getConnectionLocal();
			RoomClient currentClient = Application.getClientList().get(current.getClient().getId());
			Long rooms_id = currentClient.getRoom_id();
			String roomname = currentClient.getUserroom();
			String orgdomain = currentClient.getDomain();	
			currentClient.setIsRecording(false);
			currentClient.setRoomRecordingName("");
			Application.getClientList().put(current.getClient().getId(), currentClient);
			
			//get all stream and start recording them
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				IConnection conn = it.next();
				if (conn instanceof IServiceCapableConnection) {
					RoomClient rcl = Application.getClientList().get(conn.getClient().getId());
					log.error("is this users still alive? :"+rcl);
					//Check if the Client is in the same room and same domain 
					if(roomname.equals(rcl.getUserroom()) && orgdomain.equals(rcl.getDomain())){
						this.stopRecordingShow(conn,rcl.getStreamid());
					}
				}
			}	
			LinkedHashMap<String,Object> roomRecording = roomRecordingList.get(recordingName);
			
			Date starttime = (Date) roomRecording.get("starttime");
			Date endtime =  new java.util.Date();
			Long duration = endtime.getTime() - starttime.getTime();
			roomRecording.put("endtime",endtime);
			roomRecording.put("enduser", currentClient);
			roomRecording.put("recordname", newRecordFileName);
			
			XStream xStream = new XStream(new XppDriver());
			xStream.setMode(XStream.NO_REFERENCES);
			String xmlString = xStream.toXML(roomRecording);
			
			log.error(xmlString);
			
			return Recordingmanagement.getInstance().addRecording(newRecordFileName, duration, xmlString, rooms_id);
		} catch (Exception err) {
			log.error("[stopRecordMeetingStream]",err);
		}
		return new Long(-1);
	}	

	/**
	 * Start recording the publishing stream for the specified
	 *
	 * @param conn
	 */
	private static void recordShow(IConnection conn, String streamid, String streamName) throws Exception {
		log.error("Recording show for: " + conn.getScope().getContextPath());
		log.error("Name of CLient and Stream to be recorded: "+streamid);		
		log.error("Application.getInstance()"+Application.getInstance());
		log.error("Scope "+conn);
		log.error("Scope "+conn.getScope());
		// Get a reference to the current broadcast stream.
		ClientBroadcastStream stream = (ClientBroadcastStream) Application.getInstance()
				.getBroadcastStream(conn.getScope(), streamid);
		try {
			// Save the stream to disk.
			stream.saveAs(streamName, false);
		} catch (Exception e) {
			log.error("Error while saving stream: " + streamName, e);
		}
	}

	/**
	 * Stops recording the publishing stream for the specified
	 * IConnection.
	 *
	 * @param conn
	 */
	public static void stopRecordingShow(IConnection conn, String streamid) throws Exception {
		log.debug("Stop recording show for: " + conn.getScope().getContextPath());
		// Get a reference to the current broadcast stream.
		ClientBroadcastStream stream = (ClientBroadcastStream) Application.getInstance().getBroadcastStream(
				conn.getScope(), streamid);
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
					
					List<Rooms_Organisation> rOrgList = Roommanagement.getInstance().getRoomsOrganisationByOrganisationId(user_level, organisation_id);
					for (Iterator<Rooms_Organisation> iterOrgList = rOrgList.iterator();iterOrgList.hasNext();){
						Rooms_Organisation rOrg = iterOrgList.next();
		        		if (i==0) whereClause += " (";
		        		else whereClause += " OR";						
						whereClause += " c.rooms.rooms_id = "+rOrg.getRoom().getRooms_id()+" ";
						i++;
					}
					
				}
				if (whereClause.length()!=0) whereClause += ") AND ";
				return Recordingmanagement.getInstance().getRecordingsByWhereClause(whereClause);
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
				rec.setRoomRecording((LinkedHashMap<String, Object>)xStream.fromXML(rec.getXmlString()));
				
				return rec;
	        }
		} catch (Exception err) {
			log.error("[getRecordingById]",err);
		}		
		return null;
	}
	
	public static void addRecordingByStreamId(IConnection conn, String streamId, RoomClient rcl, String roomrecordingName) {
		try {
			LinkedHashMap<String,Object> roomRecording = roomRecordingList.get(roomrecordingName);
			List<LinkedHashMap<String,Object>> roomStreams = (List<LinkedHashMap<String,Object>>)roomRecording.get("streamlist");
			
			Date recordingsStartTime = (Date) roomRecording.get("starttime");
			Date currentDate = new Date();
			
			LinkedHashMap<String,Object> roomStream = new LinkedHashMap<String,Object>();
			
			String streamName = generateFileName(rcl.getStreamid());
			String remoteAdress = conn.getRemoteAddress();
			
			recordShow(conn, rcl.getStreamid(), streamName);
			
			roomStream.put("streamName", streamName);
			roomStream.put("remoteAdress", remoteAdress);
			roomStream.put("startdate",new java.util.Date());
			roomStream.put("starttime",currentDate.getTime()-recordingsStartTime.getTime());
			roomStream.put("rcl", rcl);
			
			roomStreams.add(roomStream);
			
			roomRecording.put("streamlist",roomStreams);
			roomRecordingList.put(roomrecordingName, roomRecording);
			
		} catch (Exception err) {
			log.error("[addRecordingByStreamId]",err);
		}	
	}
	
	public static void addWhiteBoardEvent(String roomrecordingName,Object vars) {
		try {
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
}
