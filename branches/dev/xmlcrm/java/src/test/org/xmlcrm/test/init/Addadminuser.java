package org.xmlcrm.test.init;

import junit.framework.TestCase;

import org.xmlcrm.app.data.user.Usermanagement;

public class Addadminuser extends TestCase{
	
	public Addadminuser(String testname){
		super(testname);
	}
	
	public void testAddADminUser(){
		
		Usermanagement.getInstance().registerUserInit(new Long(3), "swagner", "67810", "Wagner", "Sebastian", "seba.wagner@gmail.com", 23, "adress", "zip", "state", "town", 1);
		
	}

}
