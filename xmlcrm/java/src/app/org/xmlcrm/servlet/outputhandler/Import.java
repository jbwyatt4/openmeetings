package org.xmlcrm.servlet.outputhandler;

import http.utils.multipartrequest.ServletMultipartRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xmlcrm.app.data.basic.Sessionmanagement;
import org.xmlcrm.app.data.user.Addressmanagement;
import org.xmlcrm.app.data.user.Emailmanagement;
import org.xmlcrm.app.data.user.Usermanagement;
import org.xmlcrm.app.hibernate.beans.adresses.Adresses_Emails;
import org.xmlcrm.app.hibernate.beans.user.*;
import org.xmlcrm.utils.math.Calender;

public class Import extends HttpServlet {

	private static final Log log = LogFactory.getLog(Import.class);
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
			
			String organisation = httpServletRequest.getParameter("organisation");
			if (organisation == null) {
				organisation = "0";
			}
			Long organisation_id = Long.valueOf(organisation).longValue();
			System.out.println("organisation_id: " + organisation_id);

			Long users_id = Sessionmanagement.getInstance().checkSession(sid);
			Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);

			System.out.println("users_id: "+users_id);
			System.out.println("user_level: "+user_level);
			
			//if (user_level!=null && user_level > 0) {
			if (true) {
				
				ServletMultipartRequest upload = new ServletMultipartRequest(httpServletRequest, 104857600); // max 100 mb
				InputStream is = upload.getFileContents("Filedata");
				
			} else {
				System.out.println("ERROR LangExport: not authorized FileDownload "+(new Date()));
			}
	
		} catch (Exception er) {
			log.error("ERROR ", er);
			System.out.println("Error exporting: " + er);
			er.printStackTrace();
		}
	}
	
	public Long getUsersByDocument(InputStream is) throws Exception {
		
		SAXReader reader = new SAXReader();
        Document document = reader.read(is);
        
        Element root = document.getRootElement();
        
        for (Iterator i = root.elementIterator( "user" ); i.hasNext(); ) {
            Element itemUsers = (Element) i.next();
            
            Users us = new Users();
            
            String age = itemUsers.element("age").getText();
            us.setAvailible(Integer.valueOf(itemUsers.element("availible").getText()).intValue());
			us.setDeleted(itemUsers.element("deleted").getText());
			us.setFirstname(itemUsers.element("firstname").getText());
			us.setLastname(itemUsers.element("lastname").getText());
			us.setLogin(itemUsers.element("login").getText());
			us.setPassword(itemUsers.element("pass").getText());
			
			us.setPictureuri(itemUsers.element("pictureuri").getText());
			us.setLanguage_id(Long.valueOf(itemUsers.element("language_id").getText()).longValue());
				
			us.setStatus(Integer.valueOf(itemUsers.element("status").getText()).intValue());
			String regdate = itemUsers.element("regdate").getText();
			us.setTitle_id(Integer.valueOf(itemUsers.element("title_id").getText()).intValue());
			us.setLevel_id(Long.valueOf(itemUsers.element("level_id").getText()).longValue());
			
			
			String additionalname = itemUsers.element("additionalname").getText();
			String comment = itemUsers.element("comment").getText();
			//A User can not have a deleted Adress, you cannot delete the Adress of an User
			//String deleted = u.getAdresses().getDeleted()
			//Phone Number not done yet
			String fax = itemUsers.element("fax").getText();
			Long state_id = Long.valueOf(itemUsers.element("state_id").getText()).longValue();
			String street = itemUsers.element("street").getText();
			String town = itemUsers.element("town").getText();
			String zip = itemUsers.element("zip").getText();
			
			Long address_id = Addressmanagement.getInstance().saveAddress(street, zip, town, state_id, additionalname, "",fax);
			
			us.setAdresses(Addressmanagement.getInstance().getAdressbyId(address_id));
			
			Long user_id = Usermanagement.getInstance().addUser(us);
			
			for (Iterator itMail = itemUsers.elementIterator("emails");itMail.hasNext(); ){
				Element itemElement = (Element) itMail.next();
				String mail = itemElement.element("mail").getText();
				Long adress_emails_id = Emailmanagement.getInstance().registerEmail(mail, address_id,"");
			}
			
			
			String email = itemUsers.element("email").getText();            
            
        }
		
		return null;
	}
}
