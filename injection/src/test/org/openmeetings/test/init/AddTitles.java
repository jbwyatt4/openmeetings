package org.openmeetings.test.init;

import junit.framework.TestCase;

import org.openmeetings.app.data.user.Salutationmanagement;
import org.springframework.beans.factory.annotation.Autowired;

public class AddTitles extends TestCase {

	@Autowired
	private Salutationmanagement salutationmanagement;

	public AddTitles(String testname) {
		super(testname);
	}

	public void testaddTestTitles() {

		salutationmanagement.addUserSalutation("Herr", 261);
		salutationmanagement.addUserSalutation("Frau", 262);

	}

}
