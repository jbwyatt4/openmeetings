package org.openmeetings.test.init;

import junit.framework.TestCase;

import org.openmeetings.app.data.basic.Configurationmanagement;
import org.springframework.beans.factory.annotation.Autowired;

public class AddDefaultConfigItems extends TestCase {
	@Autowired //FIXME
	private Configurationmanagement cfgManagement;
	
	public AddDefaultConfigItems(String testname){
		super(testname);
	}
	
	public void testaddDefaultConfiItems(){
		
		cfgManagement.addConfByKey(3, "smtp_port", "587", null, "this is the smtp server port normally 25");
	}

}
