package org.xmlcrm.test.init;

import junit.framework.TestCase;

import org.xmlcrm.app.data.basic.Fieldmanagment;

public class AddDefaultfield extends TestCase {
	
	public AddDefaultfield (String testname){
		super(testname);
	}
	
	public void testaddDefaultField(){
		
		Fieldmanagment.getInstance().addFourFieldValues("headconf", 128, "Konferenzräume", "Conference-Rooms", "Conference-Rooms", "Conference-Rooms");
		Fieldmanagment.getInstance().addFourFieldValues("conf_pub", 129, "öffentlich", "public", "public", "public");
		Fieldmanagment.getInstance().addFourFieldValues("head_org", 130, "Organisation", "organisation", "organisation", "organisation");
		
		
	}

}
