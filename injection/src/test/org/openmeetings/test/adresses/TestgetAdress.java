package org.openmeetings.test.adresses;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.openmeetings.app.data.user.Addressmanagement;
import org.openmeetings.app.persistence.beans.adresses.Adresses;
import org.springframework.beans.factory.annotation.Autowired;

public class TestgetAdress extends TestCase {

	@Autowired
	private Addressmanagement addressmanagement;

	private static final Logger log = Logger.getLogger(TestgetAdress.class);

	public TestgetAdress(String testname) {
		super(testname);
	}

	public void testGetAdress() {

		Adresses adresses = addressmanagement.getAdressbyId(1);

		log.error("Adresses: " + adresses.getStates().getName());
		log.error("Adresses: " + adresses.getEmail());
		log.error("Adresses: " + adresses.getStreet());
		log.error("Adresses: " + adresses.getTown());

	}

}
