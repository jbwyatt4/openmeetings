package org.xmlcrm.servlet.outputhandler;

import java.util.LinkedHashMap;

import http.utils.multipartrequest.MultipartRequest;
import http.utils.multipartrequest.ServletMultipartRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.xmlcrm.app.data.basic.Sessionmanagement;
import org.xmlcrm.app.data.user.Usermanagement;
import org.xmlcrm.utils.stringhandlers.StringComparer;

import org.xmlcrm.app.remote.Application;

public class ScreenServlet extends HttpServlet {

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
				System.out.println("sid: " + sid);
				
				String room = httpServletRequest.getParameter("room");
				if (room == null) {
					room = "default";
				}
				System.out.println("sid: " + sid);	

				String domain = httpServletRequest.getParameter("domain");
				if (domain == null) {
					domain = "default";
				}
	
				Long users_id = Sessionmanagement.getInstance().checkSession(sid);
				Long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(users_id);
				
				if (User_LEVEL > 0) {

					//make a complete name out of domain(organisation) + roomname
					String roomName = domain + "_" + room;
					//trim whitespaces cause it is a directory name
					roomName = StringUtils.deleteWhitespace(roomName);

					//Get the current User-Directory

					String current_dir = getServletContext().getRealPath("/");
					System.out.println("Current_dir: " + current_dir);

					String working_dir = "";
					System.out.println(MultipartRequest.MAX_READ_BYTES);

					working_dir = current_dir + "desktop" + File.separatorChar + roomName + File.separatorChar;

					//Add the Folder for the Room if it does not exist yet
					File localFolder = new File(working_dir);
					if (!localFolder.exists()) {
						localFolder.mkdir();
					}

					System.out.println("#### UploadHandler working_dir: "+ working_dir);
					
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

					System.out.println("*****2 ***** completeName: "+ completeName + newFileSystemExtName);
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
					
					Application.getInstance().sendMessageByRoomAndDomain(room,domain,hs);
	
				} else {
					System.out.println("user not logged in");
				}

			}
		} catch (Exception e) {
			System.out.println("ee " + e);
			e.printStackTrace();
			throw new ServletException(e);
		}

	}

}
