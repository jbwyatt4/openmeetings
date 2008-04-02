package org.xmlcrm.test.domain;

import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmlcrm.app.data.user.Organisationmanagement;
import org.xmlcrm.app.hibernate.beans.domain.Organisation;;

public class TestUserGroupAggregation extends TestCase {
	
	private static final Log log = LogFactory.getLog(TestUserGroupAggregation.class);
	
	public TestUserGroupAggregation(String testname){
		super(testname);
	}
	
	public void testitNow(){
		
		List orgUser = Organisationmanagement.getInstance().getOrganisationsByUserId(3, 1, 0, 100, "organisation_id", true);
		
		log.error(orgUser.size());
		
		for (Iterator it2 = orgUser.iterator();it2.hasNext();){
			Organisation orgUserObj = (Organisation) it2.next();
			log.error(orgUserObj.getOrganisation_id());
			log.error(orgUserObj.getName());
		}
		
		List orgUser2 = Organisationmanagement.getInstance().getRestOrganisationsByUserId(3, 1, 0, 100, "organisation_id", true);
		
		log.error(orgUser2.size());
		
		for (Iterator it2 = orgUser2.iterator();it2.hasNext();){
			Organisation orgUserObj = (Organisation) it2.next();
			log.error(orgUserObj.getOrganisation_id());
			log.error(orgUserObj.getName());
		}		
		
	}
}
