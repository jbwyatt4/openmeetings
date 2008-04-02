package org.xmlcrm.test.navi;

import junit.framework.TestCase;

import org.xmlcrm.app.data.basic.Navimanagement;

public class GenerateBasicNavi extends TestCase {
	
	public GenerateBasicNavi(String testname){
		super(testname);
	}
	
	public void testGenerateBasicNavi(){
		
		Navimanagement.getInstance().addGlobalStructure("conf", 1, 1, false, true, 1, "conference","false");
		Navimanagement.getInstance().addMainStructure("conf1", 1, 2, true, false, 1, "meeting", 1);
		Navimanagement.getInstance().addMainStructure("conf2", 2, 3, true, false, 1, "classroom", 1);
		
		Navimanagement.getInstance().addGlobalStructure("settings", 2, 4, false, true, 1, "setings","false");
		Navimanagement.getInstance().addMainStructure("userself", 1, 5, true, false, 1, "userself",2);
		
		Navimanagement.getInstance().addGlobalStructure("admin", 3, 6, false, true, 1, "admin","false");
		
	}

}
