package org.openmeetings.servlet.outputhandler;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context; 
import org.apache.velocity.tools.view.servlet.VelocityViewServlet;

import org.apache.commons.lang.StringUtils;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openmeetings.app.data.basic.Configurationmanagement;
import org.openmeetings.app.data.basic.Sessionmanagement;
import org.openmeetings.app.data.user.Usermanagement;
import org.openmeetings.app.templates.ScreenCastTemplate;

public class ScreenRequestHandler extends VelocityViewServlet {
	
	private static final Logger log = LoggerFactory.getLogger(ScreenRequestHandler.class);
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	
	//Fix for red5-0.7.x
	
//	@Override
//	protected void service(HttpServletRequest httpServletRequest,
//			HttpServletResponse httpServletResponse) throws ServletException,
//			IOException {
//
//		try {
//			String sid = httpServletRequest.getParameter("sid");
//			if (sid == null) {
//				sid = "default";
//			}
//			System.out.println("sid: " + sid);
//
//			Long users_id = Sessionmanagement.getInstance().checkSession(sid);
//			long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);
//
//			if (user_level > 0) {
//				String room = httpServletRequest.getParameter("room");
//				if(room == null) room = "default";
//
//				String domain = httpServletRequest.getParameter("domain");
//				if(domain == null) domain = "default";	
//				
//				String rtmphostlocal = httpServletRequest.getParameter("rtmphostlocal");
//				if (rtmphostlocal == null) rtmphostlocal="default";
//				
//				String red5httpport = httpServletRequest.getParameter("red5httpport");
//				if (red5httpport == null) red5httpport="default";
//				
//				//make a complete name out of domain(organisation) + roomname
//				String roomName = domain+"_"+room;
//				//trim whitespaces cause it is a directory name
//				roomName = StringUtils.deleteWhitespace(roomName);
//				
//				String current_dir = getServletContext().getRealPath("/");
//				System.out.println("Current_dir: "+current_dir);				
//				
//				String jnlpString = ScreenCastTemplate.getInstance(current_dir).getScreenTemplate(rtmphostlocal, red5httpport, sid, room, domain);
//				
//				// Add the Folder for the Room
//
//				String requestedFile = roomName+".jnlp";
//				System.out.println("requestedFile: " + requestedFile);
//				System.out.println("jnlpString: " + jnlpString);				
//
//				httpServletResponse.reset();
//				httpServletResponse.resetBuffer();
//				OutputStream out = httpServletResponse.getOutputStream();
//				httpServletResponse.setContentType("application/x-java-jnlp-file");
//				httpServletResponse.setHeader("Content-Disposition","Inline; filename=\"" + requestedFile + "\"");
//
//				out.write(jnlpString.getBytes());
//
//				out.flush();
//				out.close();
//
//				
//				//return getVelocityEngine().getTemplate("screencast_template.vm");
//			}
//
//		} catch (Exception er) {
//			log.error("[ScreenRequestHandler]",er);
//			System.out.println("Error downloading: " + er);
//		}
//	}
	
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
				
				String room = httpServletRequest.getParameter("room");
				if(room == null) room = "default";

				String domain = httpServletRequest.getParameter("domain");
				if(domain == null) {
					log.error("domain is empty: "+domain);
					return null;
				}
				
				String rtmphostlocal = httpServletRequest.getParameter("rtmphostlocal");
				if (rtmphostlocal == null) {
					log.error("rtmphostlocal is empty: "+rtmphostlocal);
					return null;
				}
				
				String red5httpport = httpServletRequest.getParameter("red5httpport");
				if (red5httpport == null) {
					log.error("red5httpport is empty: "+red5httpport);
					return null;
				}
				
				//make a complete name out of domain(organisation) + roomname
				String roomName = domain+"_"+room;
				//trim whitespaces cause it is a directory name
				roomName = StringUtils.deleteWhitespace(roomName);
				
				String current_dir = getServletContext().getRealPath("/");
				log.debug("Current_dir: "+current_dir);			
				
				//String jnlpString = ScreenCastTemplate.getInstance(current_dir).getScreenTemplate(rtmphostlocal, red5httpport, sid, room, domain);
				
		        ctx.put("rtmphostlocal", rtmphostlocal); //rtmphostlocal
		        ctx.put("red5httpport", red5httpport); //red5httpport
		        ctx.put("webAppRootKey", "openmeetings"); //TODO: Query webAppRootKey by Servlet
		        ctx.put("SID", sid);
		        ctx.put("ROOM", room);
		        ctx.put("DOMAIN", domain);
		        ctx.put("PUBLIC_SID", publicSID);
		        
		        String requestedFile = roomName+".jnlp";
				httpServletResponse.setContentType("application/x-java-jnlp-file");
				httpServletResponse.setHeader("Content-Disposition","Inline; filename=\"" + requestedFile + "\"");
		        
		        
				// Check , which screenviewer is to be used
				org.openmeetings.app.hibernate.beans.basic.Configuration conf = Configurationmanagement.getInstance().getConfKey(user_level, "screen_viewer");
				
				String template = "screencast_template.vm";
				
				if(conf != null){
					String confVal = conf.getConf_value();
					
					try{
						int conf_i = Integer.parseInt(confVal);
						
						if(conf_i > 0)
							template = "screencast_jrdesktop.vm";
					
					}
					catch(Exception e){
						log.error("invalid configuration value for key screen_viewer!");
					}
				}
				
				return getVelocityEngine().getTemplate(template);
			
			}
			
			return null;
			
		} catch (Exception er) {
			log.error("[ScreenRequestHandler]",er);
			System.out.println("Error downloading: " + er);
		}
		return null;
	}
	
}
