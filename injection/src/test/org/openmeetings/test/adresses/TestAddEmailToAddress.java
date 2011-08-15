package org.openmeetings.test.adresses;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openmeetings.test.AbstractOpenmeetingsSpringTest;

public class TestAddEmailToAddress extends AbstractOpenmeetingsSpringTest {
	
	private static final Logger log = Logger.getLogger(TestAddEmailToAddress.class);

	@Test
	public void testGetAdress(){
		log.debug("testGetAddress");
		//long adresses_id = Emailmanagement.getInstance().registerEmail("seba.wagner@gmail.com", 1,"");
		//log.error("new adress: "+adresses_id);
	}
}
