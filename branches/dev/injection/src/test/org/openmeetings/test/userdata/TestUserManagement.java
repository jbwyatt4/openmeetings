package org.openmeetings.test.userdata;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openmeetings.app.data.beans.basic.SearchResult;
import org.openmeetings.app.persistence.beans.basic.Sessiondata;
import org.openmeetings.app.persistence.beans.user.Users;
import org.openmeetings.app.remote.MainService;
import org.openmeetings.app.remote.UserService;
import org.openmeetings.test.AbstractOpenmeetingsSpringTest;
import org.springframework.beans.factory.annotation.Autowired;

public class TestUserManagement extends AbstractOpenmeetingsSpringTest {
	@Autowired
	private MainService mService;
	@Autowired
	private UserService uService;
	
	private static final Logger log = Logger.getLogger(TestUserManagement.class);	

	@Test
	public void testUsers(){
		
		Sessiondata sessionData = mService.getsessiondata();
		
		Users us = (Users) mService.loginUser(sessionData.getSession_id(), "swagner", "test",false,null,-1L);
		
		SearchResult users = uService.getUserList(sessionData.getSession_id(), 0, 100, "firstname", false);
		
		log.error("Number of Users 1: "+users.getResult().size());
		log.error("Number of Users 2: "+users.getRecords());
		
		Users users2 = (Users) users.getResult().get(5);
		
		System.out.println("User 2: "+users2.getLogin());
		
		Users user3 = mService.getUser(sessionData.getSession_id(), users2.getUser_id().intValue());
		
		System.out.println("user3: "+user3.getLogin());
		
		mService.logoutUser(sessionData.getSession_id());
		
	}
}
