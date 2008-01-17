package org.xmlcrm.test.adresses;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmlcrm.app.data.user.Emailmanagement;
import org.xmlcrm.app.hibernate.beans.adresses.Adresses;

import junit.framework.TestCase;

public class TestAddEmailToAdress extends TestCase {
	
	private static final Log log = LogFactory.getLog(TestAddEmailToAdress.class);
	
	public TestAddEmailToAdress(String testname){
		super(testname);
	}
	
	public void testGetAdress(){
		
		long adresses_id = Emailmanagement.getInstance().registerEmail("seba.wagner@gmail.com", 1,"");
		
		log.error("new adress: "+adresses_id);

		
	}
}
