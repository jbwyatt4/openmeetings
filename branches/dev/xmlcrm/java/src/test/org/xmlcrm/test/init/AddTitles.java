package org.xmlcrm.test.init;

import junit.framework.TestCase;

import org.xmlcrm.app.data.user.Usermanagement;

public class AddTitles extends TestCase {
	
	public AddTitles(String testname){
		super(testname);
	}
	
	public void testaddTestTitles(){
		
		Usermanagement.getInstance().addUserTitels("Herr");
		Usermanagement.getInstance().addUserTitels("Frau");
		
	}

}
