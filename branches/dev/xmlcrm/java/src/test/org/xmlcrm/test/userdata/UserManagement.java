package org.xmlcrm.test.userdata;

import java.util.List;

import org.xmlcrm.app.hibernate.beans.basic.Sessiondata;
import org.xmlcrm.app.hibernate.beans.user.Users;
import org.xmlcrm.app.remote.MainService;

import junit.framework.TestCase;

public class UserManagement extends TestCase {

	public UserManagement(String testname){
		super(testname);
	}
	
	public void testUsers(){
		
		MainService mService = new MainService();
		Sessiondata sessionData = mService.getsessiondata();
		
		Users us = mService.loginUser(sessionData.getSession_id(), "swagner", "67810");
		
		List users = mService.getAllUsers(sessionData.getSession_id(), 0, 100);
		
		System.out.println("Number of Users: "+users.size());
		
		Users users2 = (Users) users.get(5);
		
		System.out.println("User 2: "+users2.getLogin());
		
		Users user3 = mService.getUser(sessionData.getSession_id(), users2.getUser_id().intValue());
		
		System.out.println("user3: "+user3.getLogin());
		
		mService.logoutUser(sessionData.getSession_id());
		
	}
}
