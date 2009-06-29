package org.openmeetings.servlet.outputhandler;

import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		
			// define Method
			String method = request.getParameter("service");
			
			
			// Streming Client says GO!
			if(method.equals(METHOD_START)){
				startStreaming(request);
			}
			
	}
	
	
	/**
	 * Notify Clients, that Sharing Clients starts streaming
	 */
	private void startStreaming(HttpServletRequest request) throws ServletException{
		
		String room = request.getParameter("room");
		
		if(room == null || room.length() < 1)
			throw new ServletException("RTPMethodServlet.startStreaming : no parameter room!");
		
		
		// TODO get Userdefinitions from ServletCall
		String width, height, jpegquality, sid = null;
		
		// TODO Change RTP - Session with these detailvalues
		RTPScreenSharingSession session;
		
		try{
			session= RTPStreamingHandler.getSessionForRoom(room, sid);
		}catch(Exception e){
			//
		}
		
		/** Notify Clients, that user started streaming -> showing users button for Appletstart */
		LinkedHashMap<String,Object> hs = new LinkedHashMap<String,Object>();
		
		ScopeApplicationAdapter.getInstance().sendMessageByRoomAndDomain(Long.valueOf(room).longValue(),hs);
		
	}



	
	
}
