package org.openmeetings.test.adresses;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openmeetings.app.data.user.Addressmanagement;
import org.openmeetings.app.data.user.Statemanagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({})
@ContextConfiguration(locations={"/red5-applicationContext.xml"})
public class TestAdresses extends AbstractJUnit4SpringContextTests {

	@Autowired
	private Statemanagement statemanagement;
	@Autowired
	private Addressmanagement addressmanagement;

	private static final Logger log = Logger.getLogger(TestAdresses.class);

	@Test
	public void testAddAdress() {

		Long states_id = statemanagement.addState("Deutschland");

		System.out.println("states_id " + states_id);
		log.error("states_id: " + states_id);

		long adress_id = addressmanagement
				.saveAddress("street", "zip", "town", states_id,
						"additionalname", "comment", "fax", "phone", "email");

		System.out.println("adress_id " + adress_id);
		log.error("adress_id: " + adress_id);

	}

}
