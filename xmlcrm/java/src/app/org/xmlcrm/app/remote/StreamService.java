package org.xmlcrm.app.remote;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.LinkedHashMap;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XppDriver;

import org.red5.server.stream.ClientBroadcastStream;
import org.red5.server.api.IConnection;
import org.red5.server.api.IScope;
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
			
			LinkedHashMap<String,Object> roomRecording = new LinkedHashMap<String,Object>();
			
			List<Object> roomStreams = new LinkedList<Object>();
			
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
						roomStream.put("starttime",new java.util.Date());
						roomStream.put("rcl", rcl);
						
						roomStreams.add(roomStream);
					}
				}
			}
			roomRecording.put("streamlist", roomStreams);
			roomRecording.put("recordingName", recordingName);
			roomRecording.put("starttime", new java.util.Date());
			roomRecording.put("startedby", currentClient);
			
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
	private void recordShow(IConnection conn, String streamid, String streamName) throws Exception {
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
	private void stopRecordingShow(IConnection conn, String streamid) throws Exception {
		log.debug("Stop recording show for: " + conn.getScope().getContextPath());
		// Get a reference to the current broadcast stream.
		ClientBroadcastStream stream = (ClientBroadcastStream) Application.getInstance().getBroadcastStream(
				conn.getScope(), streamid);
		// Stop recording.
		stream.stopRecording();
	}
	
	private String generateFileName(String streamid) throws Exception{
		String dateString = Calender.getTimeForStreamId(new java.util.Date());
		return streamid+"_"+dateString;
		
	}
	
	public LinkedHashMap<String,Object> getAllRecordings(String SID){
		try {
	        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
	        Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);  
	        
	        if (AuthLevelmanagement.getInstance().checkUserLevel(user_level)){
	        	LinkedHashMap<String,Object> returnList = new LinkedHashMap<String,Object>();
	        	
				
				returnList.put("publicRooms", Roommanagement.getInstance().getPublicRooms(user_level));
				
				Users us = Usermanagement.getInstance().getUser(users_id);
				
				LinkedList<List> privateRooms = new LinkedList<List>();
				
				for (Iterator<Organisation_Users> iter = us.getOrganisation_users().iterator();iter.hasNext();) {
					Organisation_Users orgUser = iter.next();
					Long organisation_id = orgUser.getOrganisation().getOrganisation_id();
					privateRooms.add(Roommanagement.getInstance().getRoomsOrganisationByOrganisationId(user_level, organisation_id));
				}
				
				returnList.put("privateRooms",privateRooms);
				
				return returnList;
	        }

			
			
		} catch (Exception err) {
			log.error("[getAllRecordings]",err);
		}		
		return null;
	}

}
