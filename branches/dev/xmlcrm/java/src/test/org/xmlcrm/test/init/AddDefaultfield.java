package org.xmlcrm.test.init;

import junit.framework.TestCase;

import org.xmlcrm.app.data.basic.Fieldmanagment;

public class AddDefaultfield extends TestCase {
	
	public AddDefaultfield (String testname){
		super(testname);
	}
	
	public void testaddDefaultField(){
		
		Fieldmanagment.getInstance().addFourFieldValues("organisationtablelist_idrow", 164, "Organisations-ID", "Organisation-ID", "Organisation-ID", "Organisation-ID");
		Fieldmanagment.getInstance().addFourFieldValues("organisationtablelist_namerow", 165, "Name", "name", "name", "name");
		
	}

}
