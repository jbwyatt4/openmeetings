package org.xmlcrm.test.domain;

import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmlcrm.app.data.user.Organisationmanagement;
import org.xmlcrm.app.data.user.Usermanagement;
import org.xmlcrm.app.hibernate.beans.user.Users;
import org.xmlcrm.app.hibernate.beans.adresses.Adresses_Emails;
import org.xmlcrm.app.hibernate.beans.domain.Organisation_Users;

import junit.framework.TestCase;

public class TestAddGroup extends TestCase {
	
	private static final Log log = LogFactory.getLog(TestAddGroup.class);
	
	public TestAddGroup(String testname){
		super(testname);
	}
	
	public void testAddingGroup(){
		
		long organisation_id = Organisationmanagement.getInstance().addOrganisation("default", 1);

		log.error("new organisation: "+organisation_id);

		long organisation_usersid = Organisationmanagement.getInstance().addUserToOrganisation(new Long(3), 1, organisation_id, 1,"");
		
		log.error("new organisation_user: "+organisation_usersid);
		
		Users us = Usermanagement.getInstance().getUser(1);
		
		log.error(us.getLastname());
		log.error(us.getAdresses().getTown());
		for (Iterator it = us.getAdresses().getEmails().iterator(); it.hasNext();){
			Adresses_Emails addrMails = (Adresses_Emails) it.next();
			log.error(addrMails.getMail().getEmail());
		}
		log.error("size of domains: "+us.getOrganisation_users().size());
		for (Iterator it2 = us.getOrganisation_users().iterator(); it2.hasNext();){
			Organisation_Users orgUsers = (Organisation_Users) it2.next();
			log.error(orgUsers.getOrganisation().getName());
		}
	}

}
