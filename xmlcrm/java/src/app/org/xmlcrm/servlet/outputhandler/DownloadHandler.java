package org.xmlcrm.servlet.outputhandler;




import http.utils.multipartrequest.MultipartRequest;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DownloadHandler extends HttpServlet {
	
	private static final Log log = LogFactory.getLog(DownloadHandler.class);

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void service(HttpServletRequest httpServletRequest, 
			HttpServletResponse httpServletResponse) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	try {
    		
    		/* 
    		 * TODO: Adding authentification stuff 
    		 */
    		
			String moduleName = httpServletRequest.getParameter("moduleName");
			if (moduleName == null) {
				moduleName = "nomodule";
			}
			
			String parentPath = httpServletRequest.getParameter("parentPath");
			if (parentPath == null) {
				parentPath = "";
			}
			
			String roomName = httpServletRequest.getParameter("roomName");
			if(roomName == null){
				roomName = "default";
			}			
			//trim whitespaces cause it is a directory name
			roomName = StringUtils.deleteWhitespace(roomName);
			
			//Get the current Directory
			String current_dir = getServletContext().getRealPath("/../../../");
			System.out.println("Current_dir: "+current_dir);
			
			String working_dir = "";
			System.out.println(MultipartRequest.MAX_READ_BYTES);
			
			System.out.println("#### moduleName: "+moduleName);
			
			if (moduleName.equals("videoconf1")){
				working_dir = current_dir+File.separatorChar+"lps-latest"+File.separatorChar+"upload1"+File.separatorChar;
			} else if(moduleName.equals("videoconf1ppt")){
				working_dir = current_dir+File.separatorChar+"lps-latest"+File.separatorChar+"upload1ppt"+File.separatorChar;
			}
			
			//Add the Folder for the Room
				
			
			if (moduleName.equals("videoconf1")){
				if (parentPath.length()!=0){
					working_dir = working_dir+roomName+File.separatorChar+parentPath+File.separatorChar;	
				} else {
					working_dir = working_dir+roomName+File.separatorChar;
				}
			} else {
				working_dir = working_dir+roomName+File.separatorChar;
			}
			
			if (!moduleName.equals("nomodule")){
				
	    		String requestedFile = httpServletRequest.getParameter("fileName");
	    		log.info("requestedFile: "+requestedFile);
	    		
	    		String full_path = working_dir + requestedFile;
	    		RandomAccessFile rf = new RandomAccessFile(full_path,"r");
	
				httpServletResponse.reset();
				httpServletResponse.resetBuffer();
				OutputStream out = httpServletResponse.getOutputStream();
				httpServletResponse.setContentType("APPLICATION/OCTET-STREAM");
				httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\""+requestedFile+"\"" );
				httpServletResponse.setHeader("Content-Length",""+rf.length());
				
				byte[] buffer = new byte[1024];
				int readed = -1;
				
				while( (readed =  rf.read(buffer, 0, buffer.length)) > -1)
				{
					out.write(buffer, 0, readed);
				}
				
				rf.close();
				
				out.flush();
				out.close();
				
			}
			
    	} catch (Exception er){
    		System.out.println("Error downloading: "+er);
    	}
	}	
	
}
