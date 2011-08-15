package org.openmeetings.test.adresses;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openmeetings.app.data.user.Addressmanagement;
import org.openmeetings.app.data.user.Statemanagement;
import org.openmeetings.test.AbstractOpenmeetingsSpringTest;
import org.springframework.beans.factory.annotation.Autowired;

public class TestAddresses extends AbstractOpenmeetingsSpringTest {
	@Autowired
	private Statemanagement statemanagement;
	@Autowired
	private Addressmanagement addressmanagement;

	private static final Logger log = Logger.getLogger(TestAddresses.class);

	@Test
	public void testAddAddress() {

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
