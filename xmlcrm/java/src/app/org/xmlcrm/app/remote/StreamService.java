package org.xmlcrm.app.remote;

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
import org.xmlcrm.utils.math.Calender;

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
	
	public Long stopRecordMeetingStream(String recordingName){
		try {
			IConnection current = Red5.getConnectionLocal();
			RoomClient currentClient = Application.getClientList().get(current.getClient().getId());
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
			
			roomRecording.put("endtime", new java.util.Date());
			roomRecording.put("enduser", currentClient);
			
			this.saveToFile(roomRecording, roomname, recordingName);
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
	
	private void saveToFile(LinkedHashMap<String,Object> roomRecording, String roomname, String recordingName) {
		try {

			XStream xStream = new XStream(new XppDriver());
			xStream.setMode(XStream.NO_REFERENCES);
			String xmlObject = xStream.toXML(roomRecording);
			
			IScope scope = Red5.getConnectionLocal().getScope().getParent();
			String current_dir = scope.getResource("upload/").getFile().getAbsolutePath();
			
			String recordingPath = current_dir + "recordings";
			
			File recordingDir = new File(recordingPath);
			if (!recordingDir.exists()) {
				boolean c = recordingDir.mkdir();
				if (!c) log.error("COULD NOT WRITE TO DISK "+recordingPath);
			}
			
			String roomRecordingPath = recordingPath + File.separatorChar + roomname;
			
			File roomDir = new File(roomRecordingPath);
			if (!recordingDir.exists()) {
				boolean c = recordingDir.mkdir();
				if (!c) log.error("COULD NOT WRITE TO DISK "+roomRecordingPath);
			}	
			
			String filePath = roomRecordingPath + File.separatorChar + recordingName;
			
			FileOutputStream fos = new FileOutputStream(filePath);
			PrintStream p = new PrintStream( fos );
			p.print(xmlObject);
			p.close();
			fos.close();
		} catch (Exception err) {
			log.error("[saveToFile]",err);
		}
	}

}
