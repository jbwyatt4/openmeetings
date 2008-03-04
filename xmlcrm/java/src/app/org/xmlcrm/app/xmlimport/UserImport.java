package org.xmlcrm.app.xmlimport;

import java.io.InputStream;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xmlcrm.app.data.user.Addressmanagement;
import org.xmlcrm.app.data.user.Emailmanagement;
import org.xmlcrm.app.data.user.Organisationmanagement;
import org.xmlcrm.app.data.user.Usermanagement;
import org.xmlcrm.app.hibernate.beans.user.Users;
import org.xmlcrm.utils.math.Calender;

public class UserImport {
	
	private static final Log log = LogFactory.getLog(UserImport.class);
	
	public UserImport() {	}

	private static UserImport instance = null;

	public static synchronized UserImport getInstance() {
		if (instance == null) {
			instance = new UserImport();
		}
		return instance;
	}	
	
	public Long addUsersByDocument(InputStream is) throws Exception {
		
		SAXReader reader = new SAXReader();
        Document document = reader.read(is);
        
        Element root = document.getRootElement();
        
        for (Iterator i = root.elementIterator(); i.hasNext(); ) {
        	Element itemObject = (Element) i.next();
        	if (itemObject.getName().equals("users")) {
        		this.addUsersByDocument(itemObject);
        	}
        }
        
        return null;        
	}
	
	private Long addUsersByDocument(Element userRoot) throws Exception {
        
        for (Iterator i = userRoot.elementIterator( "user" ); i.hasNext(); ) {
            Element itemUsers = (Element) i.next();
            
            Users us = new Users();

            us.setAge(Calender.parseDate(itemUsers.element("age").getText()));
            us.setAvailible(Integer.valueOf(itemUsers.element("availible").getText()).intValue());
			us.setDeleted(itemUsers.element("deleted").getText());
			us.setFirstname(itemUsers.element("firstname").getText());
			us.setLastname(itemUsers.element("lastname").getText());
			us.setLogin(itemUsers.element("login").getText());
			us.setPassword(itemUsers.element("pass").getText());
			
			us.setPictureuri(itemUsers.element("pictureuri").getText());
			if (itemUsers.element("language_id").getText().length()>0)
				us.setLanguage_id(Long.valueOf(itemUsers.element("language_id").getText()).longValue());
				
			us.setStatus(Integer.valueOf(itemUsers.element("status").getText()).intValue());
			us.setRegdate(Calender.parseDate(itemUsers.element("regdate").getText()));
			us.setTitle_id(Integer.valueOf(itemUsers.element("title_id").getText()).intValue());
			us.setLevel_id(Long.valueOf(itemUsers.element("level_id").getText()).longValue());
			
			
			String additionalname = itemUsers.element("additionalname").getText();
			String comment = itemUsers.element("comment").getText();
			// A User can not have a deleted Adress, you cannot delete the
			// Adress of an User
			// String deleted = u.getAdresses().getDeleted()
			// Phone Number not done yet
			String fax = itemUsers.element("fax").getText();
			Long state_id = Long.valueOf(itemUsers.element("state_id").getText()).longValue();
			String street = itemUsers.element("street").getText();
			String town = itemUsers.element("town").getText();
			String zip = itemUsers.element("zip").getText();
			
			boolean mailCheck = true;
			
			for (Iterator itMail = itemUsers.elementIterator("emails");itMail.hasNext(); ){
				Element itemElement = (Element) itMail.next();
				for (Iterator mailIterator = itemElement.elementIterator("mail");mailIterator.hasNext(); ){
					Element mailElement = (Element) mailIterator.next();
					if (!Emailmanagement.getInstance().checkUserEMail(mailElement.getText())) mailCheck=false;
				}	
			}  			
			
			//check for duplicate Login or mail:
			if (Usermanagement.getInstance().checkUserLogin(us.getLogin()) && mailCheck) {
			
				Long address_id = Addressmanagement.getInstance().saveAddress(street, zip, town, state_id, additionalname, "",fax);
				
				us.setAdresses(Addressmanagement.getInstance().getAdressbyId(address_id));
				
				for (Iterator itMail = itemUsers.elementIterator("emails");itMail.hasNext(); ){
					Element itemElement = (Element) itMail.next();
					for (Iterator mailIterator = itemElement.elementIterator("mail");mailIterator.hasNext(); ){
						Element mailElement = (Element) mailIterator.next();
						Long adress_emails_id = Emailmanagement.getInstance().registerEmail(mailElement.getText(), address_id,"");
					}	
				}   
				
				Long user_id = Usermanagement.getInstance().addUser(us);
				
				for (Iterator itOrga = itemUsers.elementIterator("organisations");itOrga.hasNext(); ){
					Element itemElement = (Element) itOrga.next();
					for (Iterator orgIterator = itemElement.elementIterator("organisation_id");orgIterator.hasNext(); ){
						Element orgElement = (Element) orgIterator.next();
						Long organisation_id = Long.valueOf(orgElement.getText()).longValue();
						Organisationmanagement.getInstance().addUserToOrganisation(new Long(3), us.getUser_id(), organisation_id, null, "");
					}	
				}    
				
	        }
        }
		
		return null;
	}	

}
