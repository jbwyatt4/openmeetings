package org.xmlcrm.test.userdata;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmlcrm.app.hibernate.beans.basic.Sessiondata;
import org.xmlcrm.app.hibernate.beans.user.Users;
import org.xmlcrm.app.remote.MainService;

import junit.framework.TestCase;

public class UpdateUserSelf extends TestCase {
	
	private static final Log log = LogFactory.getLog(UpdateUserSelf.class);
	
	public UpdateUserSelf(String testname){
		super(testname);
	}
	
	public void testUserUpdate(){
		
		MainService mService = new MainService();
		Sessiondata sessionData = mService.getsessiondata();
		
		Users us = mService.loginUser(sessionData.getSession_id(), "swagner3", "test");
		
		System.out.println("us: "+us.getFirstname());
		
		String updateRes = mService.updateUser(sessionData.getSession_id(),"swagner3", "", "WagnerUp", "SebUp", 22, "StreetUp", "75UP", "StateUP", "PfUp", 1, "seba.up", 0, "rechnungsUP", 0, "lieferUp",1, "07Up", "07faxUP", "015UP");

		System.out.println("updateRes: "+updateRes);
		
		String logout = mService.logoutUser(sessionData.getSession_id());
		
		System.out.println("logout: "+logout);		
		
	}
	
	public void testUserUpdateAdmin(){
		
		MainService mService = new MainService();
		Sessiondata sessionData = mService.getsessiondata();
		
		Users us = mService.loginUser(sessionData.getSession_id(), "swagner", "67810");
		
		System.out.println("us: "+us.getFirstname());
		
		String updateRes = mService.updateUserAdmin(sessionData.getSession_id(), 3 , 3, "swagner3", "", "WagnerUpAdmin", "SebAdminUp", 22, "StreetUp", "75UP", "StateUP", "PfUp", 1, "seba.up", 0, "rechnungsUP", 0, "lieferUp",1, "07Up", "07faxUP", "015UP");

		System.out.println("updateRes: "+updateRes);
		
		String logout = mService.logoutUser(sessionData.getSession_id());
		
		System.out.println("logout: "+logout);		
		
	}
	
	
}
