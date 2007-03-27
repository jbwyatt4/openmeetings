package org.xmlcrm.test.adresses;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmlcrm.app.hibernate.beans.adresses.Adresses;
import org.xmlcrm.app.data.user.Adressmanagement;

import junit.framework.TestCase;

public class TestgetAdress extends TestCase {
	
	private static final Log log = LogFactory.getLog(TestgetAdress.class);
	
	public TestgetAdress(String testname){
		super(testname);
	}
	
	public void testGetAdress(){
		
		Adresses adresses = Adressmanagement.getInstance().getAdressbyId(1);
		
		log.error("Adresses: "+adresses.getStates().getName());
		log.error("Adresses: "+adresses.getEmails().size());
		log.error("Adresses: "+adresses.getStreet());
		log.error("Adresses: "+adresses.getTown());
		
	}

}
