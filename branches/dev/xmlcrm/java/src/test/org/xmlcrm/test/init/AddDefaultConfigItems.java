package org.xmlcrm.test.init;

import junit.framework.TestCase;

import org.xmlcrm.app.data.basic.Configurationmanagement;

public class AddDefaultConfigItems extends TestCase {
	
	public AddDefaultConfigItems(String testname){
		super(testname);
	}
	
	public void testaddDefaultConfiItems(){
		
		Configurationmanagement.getInstance().addConfByKey(3, "smtp_port", "587", null, "this is the smtp server port normally 25");
	}

}
