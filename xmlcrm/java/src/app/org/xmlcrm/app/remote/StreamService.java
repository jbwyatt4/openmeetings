package org.xmlcrm.app.remote;

import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.red5.server.stream.ClientBroadcastStream;
import org.red5.server.api.IConnection;
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
			
			//get all stream and start recording them
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				IConnection conn = it.next();
				if (conn instanceof IServiceCapableConnection) {
					RoomClient rcl = Application.getClientList().get(conn.getClient().getId());
					log.error("is this users still alive? :"+rcl);
					//Check if the Client is in the same room and same domain 
					if(roomname.equals(rcl.getUserroom()) && orgdomain.equals(rcl.getDomain())){
						String streamName = this.generateFileName(rcl.getStreamid());
						String remoteAdress = conn.getRemoteAddress();
						String streamid = rcl.getStreamid();
						
						this.recordShow(conn, rcl.getStreamid(), streamName);
						
						
					}
				}
			}
			return recordingName;
		} catch (Exception err) {
			log.error("[recordMeetingStream]",err);
		}
		return null;
	}
	
	public void stopRecordMeetingStream(){
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
	}	

	/**
	 * Start recording the publishing stream for the specified
	 *
	 * @param conn
	 */
	private void recordShow(IConnection conn, String streamid, String streamName) {
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
	private void stopRecordingShow(IConnection conn, String streamid) {
		log.debug("Stop recording show for: " + conn.getScope().getContextPath());
		// Get a reference to the current broadcast stream.
		ClientBroadcastStream stream = (ClientBroadcastStream) Application.getInstance().getBroadcastStream(
				conn.getScope(), streamid);
		// Stop recording.
		stream.stopRecording();
	}
	
	private String generateFileName(String streamid) {
		String dateString = Calender.getTimeForStreamId(new java.util.Date());
		return streamid+"_"+dateString;
		
	}

}
