package org.xmlcrm.servlet.outputhandler;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmlcrm.app.documents.InstallationDocument;


public class Install extends HttpServlet {

	private static final Log log = LogFactory.getLog(DownloadHandler.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void service(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws ServletException,
			IOException {

		try {
			
			String command = httpServletRequest.getParameter("command");
			
			if (command.equals(null)){
				log.error("command equals null");

				String working_dir = getServletContext().getRealPath("/")+File.separatorChar+InstallationDocument.installFolderName+File.separatorChar;
				
				File installerFile = new File(working_dir+InstallationDocument.installFileName);
				
				if (!installerFile.exists()){
					
					boolean b = installerFile.createNewFile();
					
					if (!b) {
						
					}
					
				}
				
			}
			
			
		} catch (Exception err) {
			log.error("Install: ",err);
		}
		
	}

}
