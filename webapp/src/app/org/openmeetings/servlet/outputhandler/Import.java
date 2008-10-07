package org.openmeetings.servlet.outputhandler;

import http.utils.multipartrequest.ServletMultipartRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.openmeetings.app.data.basic.Sessionmanagement;
import org.openmeetings.app.data.user.Addressmanagement;
import org.openmeetings.app.data.user.Emailmanagement;
import org.openmeetings.app.data.user.Organisationmanagement;
import org.openmeetings.app.data.user.Usermanagement;
import org.openmeetings.app.xmlimport.LanguageImport;
import org.openmeetings.app.xmlimport.UserImport;
import org.openmeetings.app.hibernate.beans.user.*;
import org.openmeetings.utils.math.CalendarPatterns;

public class Import extends HttpServlet {

	private static final Logger log = LoggerFactory.getLogger(Import.class);
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
			String sid = httpServletRequest.getParameter("sid");
			if (sid == null) {
				sid = "default";
			}
			System.out.println("sid: " + sid);

			String moduleName = httpServletRequest.getParameter("moduleName");
			if (moduleName == null) {
				moduleName = "moduleName";
			}
			System.out.println("moduleName: " + sid);
			Long users_id = Sessionmanagement.getInstance().checkSession(sid);
			Long user_level = Usermanagement.getInstance().getUserLevelByID(
					users_id);

			System.out.println("users_id: " + users_id);
			System.out.println("user_level: " + user_level);

			// if (user_level!=null && user_level > 0) {
			if (true) {
				if (moduleName.equals("users")) {
					log.error("Import Users");
					String organisation = httpServletRequest.getParameter("secondid");
					if (organisation == null) {
						organisation = "0";
					}
					Long organisation_id = Long.valueOf(organisation).longValue();
					System.out.println("organisation_id: " + organisation_id);

					ServletMultipartRequest upload = new ServletMultipartRequest(httpServletRequest, 104857600); // max100 mb
					InputStream is = upload.getFileContents("Filedata");
					
					UserImport.getInstance().addUsersByDocument(is);

				} else if (moduleName.equals("language")) {
					log.error("Import Language");
					String language = httpServletRequest.getParameter("secondid");
					if (language == null) {
						language = "0";
					}
					Long language_id = Long.valueOf(language).longValue();
					System.out.println("language_id: " + language_id);

					ServletMultipartRequest upload = new ServletMultipartRequest(httpServletRequest, 104857600); // max100 mb
					InputStream is = upload.getFileContents("Filedata");
					
					LanguageImport.getInstance().addLanguageByDocument(language_id, is);
					
				}
			} else {
				System.out.println("ERROR LangExport: not authorized FileDownload "+ (new Date()));
			}	
	
		} catch (Exception er) {
			log.error("ERROR ", er);
			System.out.println("Error exporting: " + er);
			er.printStackTrace();
		}
	}

}
