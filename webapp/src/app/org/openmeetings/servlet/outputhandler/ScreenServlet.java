package org.openmeetings.servlet.outputhandler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import http.utils.multipartrequest.MultipartRequest;
import http.utils.multipartrequest.ServletMultipartRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openmeetings.app.conference.videobeans.RoomClient;
import org.openmeetings.app.data.basic.Sessionmanagement;
import org.openmeetings.app.data.user.Usermanagement;
import org.openmeetings.utils.stringhandlers.StringComparer;

import org.openmeetings.app.remote.Application;

public class ScreenServlet extends HttpServlet {
	
	private static final Logger log = LoggerFactory.getLogger(ScreenServlet.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */ 
	protected void service(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws ServletException,
			IOException {
		try {
			
			if (httpServletRequest.getContentLength() > 0) {
			
				System.out.println("service: " + httpServletRequest.getProtocol());
	
				String sid = httpServletRequest.getParameter("sid");
				if (sid == null) {
					sid = "default";
				}
				log.debug("sid: " + sid);
				
				String publicSID = httpServletRequest.getParameter("publicSID");
				if (publicSID == null) {
					log.error("publicSID is empty: "+publicSID);
					return;
				}
				log.debug("publicSID: " + publicSID);	
				
				String room = httpServletRequest.getParameter("room");
				if (room == null) {
					room = "default";
				}
				log.debug("room: " + room);	

				String domain = httpServletRequest.getParameter("domain");
				if (domain == null) {
					domain = "default";
				}
				log.debug("domain: " + domain);
	
				Long users_id = Sessionmanagement.getInstance().checkSession(sid);
				Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);
				
				if (user_level > 0) {
					
					//check if this Client is still inside the Room
					boolean userIsInRoom = false;
					boolean doProcess = false;
					
					HashMap<String, RoomClient> clientList = Application.getClientList();
					for (Iterator iter = clientList.keySet().iterator();iter.hasNext();) {
						RoomClient rcl = clientList.get(iter.next());
						if (rcl.getPublicSID().equals(publicSID)) {
							log.debug("found RoomClient");
							if (rcl.getRoom_id() != null && rcl.getRoom_id().toString().equals(room)) {
								log.debug("User is inside Room");
								userIsInRoom = true;
								doProcess = true;
							} else {
								log.debug("User already left room, block Screen - logical Room Leave");
								OutputStream out = httpServletResponse.getOutputStream();
								String returnValue = "close";
								out.write(returnValue.getBytes());
								//return;
							}
						}
					}
					
					if (!userIsInRoom) {
						log.debug("User already left room, block Screen - Browser Closed");
						OutputStream out = httpServletResponse.getOutputStream();
						String returnValue = "close";
						out.write(returnValue.getBytes());
						//return;
					}

					if (doProcess) {
						//make a complete name out of domain(organisation) + roomname
						String roomName = domain + "_" + room;
						//trim whitespaces cause it is a directory name
						roomName = StringUtils.deleteWhitespace(roomName);
	
						//Get the current User-Directory
	
						String current_dir = getServletContext().getRealPath("/");
						log.debug("Current_dir: " + current_dir);
	
						String working_dir = "";
						log.debug("MAX_READ_BYTES",MultipartRequest.MAX_READ_BYTES);
	
						working_dir = current_dir + "desktop" + File.separatorChar + roomName + File.separatorChar;
	
						//Add the Folder for the Room if it does not exist yet
						File localFolder = new File(working_dir);
						if (!localFolder.exists()) {
							localFolder.mkdir();
						}
	
						log.debug("#### UploadHandler working_dir: "+ working_dir);
						
						ServletMultipartRequest upload = new ServletMultipartRequest(httpServletRequest, 104857600); // max 100 mb
	
						InputStream is = upload.getFileContents("Filedata");
	
						//trim whitespace
						String fileSystemName = StringUtils.deleteWhitespace(upload.getFileSystemName("Filedata"));
	
						String newFileSystemName = StringComparer.getInstance()
								.compareForRealPaths(
										fileSystemName.substring(0,
												fileSystemName.length() - 4));
						String newFileSystemExtName = fileSystemName.substring(
								fileSystemName.length() - 4,
								fileSystemName.length()).toLowerCase();
	
						//trim long names cause cannot output that
						if (newFileSystemName.length() >= 17) {
							newFileSystemName = newFileSystemName.substring(0,16);
						}
						
						String completeName = working_dir + newFileSystemName+"_"+sid;
	
						File f = new File(completeName + newFileSystemExtName);
						if (f.exists()) {
							f.delete();
						}
	
						log.debug("*****2 ***** completeName: "+ completeName + newFileSystemExtName);
						FileOutputStream fos = new FileOutputStream(completeName + newFileSystemExtName);
	
						byte[] buffer = new byte[1024];
						int len = 0;
						
						while ( (len= is.read(buffer, 0, buffer.length)) >-1 ) {
							fos.write(buffer, 0, len);
						}
	
						fos.close();
						is.close();	
						
						
						
						LinkedHashMap<String,Object> hs = new LinkedHashMap<String,Object>();
						hs.put("user", Usermanagement.getInstance().getUser(users_id));
						hs.put("message", "desktop");
						hs.put("action", "newSlide");
						hs.put("fileName", newFileSystemName+"_"+sid+newFileSystemExtName);
						
						
						
						Application.getInstance().sendMessageByRoomAndDomain(Long.valueOf(room).longValue(),hs);
					
					}
	
				} else {
					log.debug("user not logged in");
				}

				
				
//				OutputStream out = httpServletResponse.getOutputStream();
//				String returnValue = "ok";
//				out.write(returnValue.getBytes());
//				
			}
		} catch (Exception e) {
			log.error("ee " + e);
			e.printStackTrace();
			throw new ServletException(e);
		}

	}

}
