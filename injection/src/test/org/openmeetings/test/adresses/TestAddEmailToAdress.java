package org.openmeetings.test.adresses;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({})
@ContextConfiguration(locations={"classpath:/openmeetings-applicationContext.xml"})
public class TestAddEmailToAdress extends AbstractJUnit4SpringContextTests {
	
	private static final Logger log = Logger.getLogger(TestAddEmailToAdress.class);

	@Test
	public void testGetAdress(){
		//long adresses_id = Emailmanagement.getInstance().registerEmail("seba.wagner@gmail.com", 1,"");
		//log.error("new adress: "+adresses_id);
	}
}
