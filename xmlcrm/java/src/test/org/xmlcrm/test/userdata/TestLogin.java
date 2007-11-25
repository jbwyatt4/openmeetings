package org.xmlcrm.test.userdata;

import org.hibernate.Session;
import org.xmlcrm.app.hibernate.beans.basic.Sessiondata;
import org.xmlcrm.app.hibernate.beans.user.Users;
import org.xmlcrm.app.hibernate.utils.HibernateUtil;
import org.xmlcrm.app.remote.MainService;

import junit.framework.TestCase;

public class TestLogin extends TestCase {
	
	
	public TestLogin(String testname){
		super(testname);
	}
	
	public void testTestLogin(){
		
		MainService mService = new MainService();
		Sessiondata sessionData = mService.getsessiondata();
		
		Users us = (Users) mService.loginUser(sessionData.getSession_id(), "swagner", "test");
		
		System.out.println("us: "+us.getFirstname());
		
		mService.getLanguageById(1);
		
	}

}
