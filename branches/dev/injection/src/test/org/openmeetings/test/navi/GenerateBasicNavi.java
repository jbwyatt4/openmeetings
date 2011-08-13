package org.openmeetings.test.navi;

import junit.framework.TestCase;

import org.openmeetings.app.data.basic.Navimanagement;
import org.springframework.beans.factory.annotation.Autowired;

public class GenerateBasicNavi extends TestCase {

	@Autowired
	private Navimanagement navimanagement;

	public GenerateBasicNavi(String testname) {
		super(testname);
	}

	public void testGenerateBasicNavi() {

		navimanagement.addGlobalStructure("conf", 1, 1, false, true, 1,
				"conference", "false", 586L);
		navimanagement.addMainStructure("conf1", 1, 2, true, false, 1,
				"meeting", 1, "false");
		navimanagement.addMainStructure("conf2", 2, 3, true, false, 1,
				"classroom", 1, "false");

		navimanagement.addGlobalStructure("settings", 2, 4, false, true, 1,
				"setings", "false", 586L);
		navimanagement.addMainStructure("userself", 1, 5, true, false, 1,
				"userself", 2, "false");

		navimanagement.addGlobalStructure("admin", 3, 6, false, true, 1,
				"admin", "false", 586L);

	}

}
