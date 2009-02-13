package org.openmeetings.test.domain;


import org.slf4j.Logger;
import org.red5.logging.Red5LoggerFactory;
import org.openmeetings.app.data.user.Organisationmanagement;
import org.openmeetings.app.data.user.dao.UsersDaoImpl;
import org.openmeetings.app.hibernate.beans.user.Users;


import junit.framework.TestCase;

public class TestAddGroup extends TestCase {
	
	private static final Logger log = Red5LoggerFactory.getLogger(TestAddGroup.class, "openmeetings");
	
	public TestAddGroup(String testname){
		super(testname);
	}
	
	public void testAddingGroup(){
		
		long organisation_id = Organisationmanagement.getInstance().addOrganisation("default", 1);

		log.error("new organisation: "+organisation_id);

		long organisation_usersid = Organisationmanagement.getInstance().addUserToOrganisation(new Long(3), new Long(1), organisation_id, new Long(1),"");
		
		log.error("new organisation_user: "+organisation_usersid);
		
		Users us = UsersDaoImpl.getInstance().getUser(new Long(1));
		
		log.error(us.getLastname());
		log.error(us.getAdresses().getTown());
		
		/*
		for (Iterator it = us.getAdresses().getEmails().iterator(); it.hasNext();){
			Adresses_Emails addrMails = (Adresses_Emails) it.next();
			log.error(addrMails.getMail().getEmail());
		}
		log.error("size of domains: "+us.getOrganisation_users().size());
		for (Iterator it2 = us.getOrganisation_users().iterator(); it2.hasNext();){
			Organisation_Users orgUsers = (Organisation_Users) it2.next();
			log.error(orgUsers.getOrganisation().getName());
		}
		*/
	}

}
