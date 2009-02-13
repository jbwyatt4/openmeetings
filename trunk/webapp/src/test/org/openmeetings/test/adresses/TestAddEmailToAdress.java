package org.openmeetings.test.adresses;

import org.slf4j.Logger;
import org.red5.logging.Red5LoggerFactory;
import org.openmeetings.app.data.user.Emailmanagement;
import org.openmeetings.app.hibernate.beans.adresses.Adresses;

import junit.framework.TestCase;

public class TestAddEmailToAdress extends TestCase {
	
	private static final Logger log = Red5LoggerFactory.getLogger(TestAddEmailToAdress.class, "openmeetings");
	
	public TestAddEmailToAdress(String testname){
		super(testname);
	}
	
	public void testGetAdress(){
		
		//long adresses_id = Emailmanagement.getInstance().registerEmail("seba.wagner@gmail.com", 1,"");
		
		//log.error("new adress: "+adresses_id);

		
	}
}
