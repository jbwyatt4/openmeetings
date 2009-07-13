package org.openmeetings.servlet.outputhandler;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.servlet.VelocityViewServlet;
import org.openmeetings.app.data.basic.Configurationmanagement;
import org.openmeetings.app.data.basic.Sessionmanagement;
import org.openmeetings.app.data.user.Usermanagement;
import org.openmeetings.app.hibernate.beans.recording.RoomClient;
import org.openmeetings.app.remote.red5.ClientListManager;
import org.openmeetings.app.remote.red5.ScopeApplicationAdapter;
import org.openmeetings.app.rtp.RTPScreenSharingSession;
import org.openmeetings.app.rtp.RTPStreamingHandler;
import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;

/**
 * 
 * @author o.becherer
 *
 */
public class RTPSharerServlet extends VelocityViewServlet{
	
	private static final Logger log = Red5LoggerFactory.getLogger(RTPSharerServlet.class, "openmeetings");
	
	@Override
	public Template handleRequest(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Context ctx) throws ServletException,
			IOException {

		try {
			
			String sid = httpServletRequest.getParameter("sid");
			if (sid == null) {
				sid = "default";
			}
			
			log.debug("sid: " + sid);

			Long users_id = Sessionmanagement.getInstance().checkSession(sid);
			Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);

			if (user_level > 0) {
				
				String publicSID = httpServletRequest.getParameter("publicSID");
				if (publicSID == null) {
					log.error("publicSID is empty: "+publicSID);
					return null;
				}
				
				String room = httpServletRequest.getParameter("room_id");
				
				if(room == null || room.length() < 1){
					log.error("room_id is emtpy!");
					return null;
				}
				
		        //Generate Unique Name to prevent browser from caching file
				Date t = new Date();
				
		        String requestedFile = room+"_"+t.getTime()+".jnlp";
				httpServletResponse.setContentType("text/html");
				httpServletResponse.setHeader("Content-Disposition","Inline; filename=\"" + requestedFile + "\"");
		        
				String template = "rtp_player_applet.vm";
				
				// Retrieve Data from RTPmanager
				RTPScreenSharingSession rsss = RTPStreamingHandler.getSessionForRoom(room, sid);
				
				if(rsss == null){
					log.error("no RTPSharingSession available");
					return null;
				}
				
				log.debug("Trying to connect on Stream (origin : " + rsss.getSharingIpAddress() + ")");
				
				// TODO : send RTP Stream not directly from sharer, but from RTP-Proxy within
				// RED5 !!!!
				//ctx.put("HOST", rsss.getSharingIpAddress());
				
				int port = RTPStreamingHandler.getNextFreeRTPPort();
				
				ctx.put("HOST", InetAddress.getLocalHost().getHostAddress());
				ctx.put("PORT", port);
				ctx.put("HEIGHT", rsss.getStreamHeight());
				ctx.put("WIDTH", rsss.getStreamWidth());
				
				log.debug("Put Variables to Velocity context : HOST=" + ctx.get("HOST") + ", PORT=" + ctx.get("PORT"));
				
				RoomClient rcl = ClientListManager.getInstance().getClientByPublicSID(publicSID);
				String ip = rcl.getUserip();
				
				log.debug("publicSID : " + publicSID + ", ID : " + ip);
				
				rsss.addNewViewer(ip, port);
				
				return getVelocityEngine().getTemplate(template);
			
			}
			
			return null;
			
		} catch (Exception er) {
			log.error("[RTPSharerServlet]",er);
			System.out.println("Error downloading: " + er);
		}
		return null;
	}
	
	
}
