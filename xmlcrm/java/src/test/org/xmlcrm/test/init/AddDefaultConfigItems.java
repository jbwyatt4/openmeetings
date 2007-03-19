package org.xmlcrm.test.init;

import junit.framework.TestCase;

import org.xmlcrm.app.data.basic.Configurationmanagement;

public class AddDefaultConfigItems extends TestCase {
	
	public AddDefaultConfigItems(String testname){
		super(testname);
	}
	
	public void testaddDefaultConfiItems(){
		
		Configurationmanagement.getInstance().addConfByKey(3, "allow_frontend_register", "1", 1, "");
		
		Configurationmanagement.getInstance().addConfByKey(3, "default_group_id", "1", 1, "");
		
		
	}

}
