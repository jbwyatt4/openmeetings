package org.openmeetings.test.init;

import org.junit.Test;
import org.openmeetings.app.data.user.Salutationmanagement;
import org.openmeetings.test.AbstractOpenmeetingsSpringTest;
import org.springframework.beans.factory.annotation.Autowired;

public class TestAddTitles extends AbstractOpenmeetingsSpringTest {

	@Autowired
	private Salutationmanagement salutationmanagement;

	@Test
	public void testaddTestTitles() {
		salutationmanagement.addUserSalutation("Herr", 261);
		salutationmanagement.addUserSalutation("Frau", 262);
	}

}
