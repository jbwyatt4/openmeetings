package org.openmeetings.test.init;

import org.junit.Test;
import org.openmeetings.app.data.basic.Configurationmanagement;
import org.openmeetings.test.AbstractOpenmeetingsSpringTest;
import org.springframework.beans.factory.annotation.Autowired;

public class TestAddDefaultConfigItems extends AbstractOpenmeetingsSpringTest {
	@Autowired
	private Configurationmanagement cfgManagement;

	@Test
	public void testaddDefaultConfiItems(){
		cfgManagement.addConfByKey(3, "smtp_port", "587", null, "this is the smtp server port normally 25");
	}

}
