package org.openmeetings.servlet.outputhandler;

import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openmeetings.app.hibernate.beans.recording.RoomClient;
import org.openmeetings.app.remote.red5.ClientListManager;
import org.openmeetings.app.remote.red5.ScopeApplicationAdapter;
import org.openmeetings.app.rtp.RTPScreenSharingSession;
import org.openmeetings.app.rtp.RTPStreamingHandler;
import org.openmeetings.servlet.outputhandler.ScreenRequestHandler;
import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;

/**
 * 
 * @author o.becherer
 *
 */
public class RTPMethodServlet extends HttpServlet{
	
	private static final Logger log = Red5LoggerFactory.getLogger(ScreenRequestHandler.class, "openmeetings");
	
	public static final String METHOD_START = "streamer_start";
	
	public static final String METHOD_STOP = "streamer_stop";

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		
			// define Method
			String method = request.getParameter("service");
			
			
			// Streming Client says GO!
			if(method.equals(METHOD_START)){
				startStreaming(request);
			} else if(method.equals(METHOD_STOP)){
				stopStreaming(request);
			}
			
	}
	
	
	/**
	 * @param request
	 */
	private void stopStreaming(HttpServletRequest request) {
		try {
			
			String room = request.getParameter("room");
			
			if(room == null || room.length() < 1)
				throw new ServletException("RTPMethodServlet.startStreaming : no parameter room!");
			
			// TODO get Userdefinitions from ServletCall
			String sid = null;
			
			// TODO Change RTP - Session with these detailvalues
			RTPScreenSharingSession session;
		
			session = RTPStreamingHandler.getSessionForRoom(room, sid);
			
			/** Notify Clients, that user started streaming -> showing users button for Appletstart */
			LinkedHashMap<String,Object> hs = new LinkedHashMap<String,Object>();
			hs.put("message", "stopStreaming");
			hs.put("session", session);
			
			ScopeApplicationAdapter.getInstance().sendMessageByRoomAndDomain(Long.valueOf(room).longValue(),hs);
			
		} catch(Exception err){
			log.error("[startStreaming]",err);
		}
	}


	/**
	 * Notify Clients, that Sharing Clients starts streaming
	 */
	private void startStreaming(HttpServletRequest request) throws ServletException {
		
		try{
			
			String room = request.getParameter("room");
			
			if(room == null || room.length() < 1)
				throw new ServletException("RTPMethodServlet.startStreaming : no parameter room!");
			
			// TODO get Userdefinitions from ServletCall *width, height* => what for? they are already in the RTPScreenSharingSession
			// -> Sharing Client has the possibility to alter Width/height/quality of the stream, so it should be notified at every Stream Start, i think
			
			String width, height, jpegquality, sid = null, publicSID = null;
			
			// TODO Change RTP - Session with these detailvalues
			RTPScreenSharingSession session;
		
			session = RTPStreamingHandler.getSessionForRoom(room, sid);
			
			//width, height should be also in the RTPScreenSharingSession Object 
			
			width=request.getParameter("width");
			
			if(width!= null && width.length() > 0){
				try{
					int width_i = Integer.parseInt(width);
					session.setStreamWidth(width_i);
				}
				catch(NumberFormatException nfe){
					log.error("Invalid parameter width as Servletparameter - ignored!");
				}
			}
			
			height=request.getParameter("height");
			
			if(height!= null && height.length() > 0){
				try{
					int height_i = Integer.parseInt(height);
					session.setStreamHeight(height_i);
				}
				catch(NumberFormatException nfe){
					log.error("Invalid parameter height as Servletparameter - ignored!");
				}
			}
			
			
			//we have to include the publicSID to get the RoomClient Object
			//also the HOST, PORT must be set correctly in the RTPScreenSharingSession-Object
			RoomClient rcl = ClientListManager.getInstance().getClientByPublicSID(publicSID);
			
			/** Notify Clients, that user started streaming -> showing users button for Appletstart */
			LinkedHashMap<String,Object> hs = new LinkedHashMap<String,Object>();
			hs.put("message", "startStreaming");
			//Set the User Object
			hs.put("rcl", rcl);
			//Set the Screen Sharing Object
			hs.put("session", session);
			
			ScopeApplicationAdapter.getInstance().sendMessageByRoomAndDomain(Long.valueOf(room).longValue(),hs);
			
			
		} catch(Exception err){
			log.error("[startStreaming]",err);
		}
		
		
		
	}



	
	
}
