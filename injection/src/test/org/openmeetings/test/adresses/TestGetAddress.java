package org.openmeetings.test.adresses;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openmeetings.app.data.user.Addressmanagement;
import org.openmeetings.app.persistence.beans.adresses.Adresses;
import org.openmeetings.test.AbstractOpenmeetingsSpringTest;
import org.springframework.beans.factory.annotation.Autowired;

public class TestGetAddress extends AbstractOpenmeetingsSpringTest {

	@Autowired
	private Addressmanagement addressmanagement;

	private static final Logger log = Logger.getLogger(TestGetAddress.class);

	@Test
	public void testGetAdress() {

		Adresses adresses = addressmanagement.getAdressbyId(1);

		log.error("Adresses: " + adresses.getStates().getName());
		log.error("Adresses: " + adresses.getEmail());
		log.error("Adresses: " + adresses.getStreet());
		log.error("Adresses: " + adresses.getTown());

	}

}
