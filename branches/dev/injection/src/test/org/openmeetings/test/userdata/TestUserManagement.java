package org.openmeetings.test.userdata;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.openmeetings.app.data.beans.basic.SearchResult;
import org.openmeetings.app.data.user.Addressmanagement;
import org.openmeetings.app.data.user.Organisationmanagement;
import org.openmeetings.app.data.user.Usermanagement;
import org.openmeetings.app.persistence.beans.basic.Sessiondata;
import org.openmeetings.app.persistence.beans.domain.Organisation;
import org.openmeetings.app.persistence.beans.user.Users;
import org.openmeetings.app.remote.MainService;
import org.openmeetings.app.remote.UserService;
import org.openmeetings.test.AbstractOpenmeetingsSpringTest;
import org.springframework.beans.factory.annotation.Autowired;
import static junit.framework.Assert.*;

public class TestUserManagement extends AbstractOpenmeetingsSpringTest {
	private final static String USER_NAME = "swagner";
	private final static String USER_PASS = "test";
	private final static String ORG_PREFIX = "Test Organization";
	private final static String ORG_COMMENT = "No comments";
	private final Long LEVEL_ADMIN = 3L;
	
	@Autowired
	private MainService mService;
	@Autowired
	private UserService uService;
	@Autowired
	private Usermanagement userManagement;
	@Autowired
	private Organisationmanagement organisationmanagement;
	private Random rnd;
	
	private static final Logger log = Logger.getLogger(TestUserManagement.class);	

	private Long createOrganisation(String namePrefix, Long userId) {
		String name = namePrefix + rnd.nextLong();
		Long orgId = organisationmanagement.addOrganisation(name, userId);
		assertTrue("Failed to add organisation", orgId != null && orgId > 0);
		return orgId;
	}
	
	private Long addUserToOrganisation(Long userId, Long orgId) {
		Long orgUserId = organisationmanagement.addUserToOrganisation(userId, orgId, userId, ORG_COMMENT);
		assertTrue("Failed to add user to organisation", orgUserId != null && orgUserId > 0);
		return orgUserId;
	}
	
	@Before
	public void setup() throws Exception {
		rnd = new Random();
		Users u = userManagement.getUserByLogin(USER_NAME);
		if (u == null) {
			Long userId = userManagement.addUser(LEVEL_ADMIN, 1, 1, "Admin FirstName", USER_NAME, "LastName", 1L, USER_PASS
				, 1L, new Date(), "12345", null, null, null, false, "GMT", false
				, null, null, true, false);
			assertTrue("Failed to create user", userId != null && userId > 0);
			List<Organisation> orgs = organisationmanagement.getOrganisations(LEVEL_ADMIN);
			Long orgId = -1L;
			if (orgs.isEmpty()) {
				orgId = createOrganisation(ORG_PREFIX, userId);
			} else {
				orgId = orgs.get(0).getOrganisation_id();
			}
			addUserToOrganisation(userId, orgId);
		}
	}
	
	private void addOrganisation(Long userId, Long orgId) {
		int count = organisationmanagement.getOrganisationsByUserId(LEVEL_ADMIN, userId, 0, Integer.MAX_VALUE, "name", true).size();
		addUserToOrganisation(userId, orgId);
		int count2 = organisationmanagement.getOrganisationsByUserId(LEVEL_ADMIN, userId, 0, Integer.MAX_VALUE, "name", true).size();
		assertEquals("Organisation count is not incremented", count + 1, count2);
	}
	
	@Test
	public void testAddAdditionalOrganisation() throws Exception {
		Users u = userManagement.getUserByLogin(USER_NAME);
		assertNotNull("Failed to fetch user", u);
		
		boolean found = false;
		for (Organisation o : organisationmanagement.getOrganisations(LEVEL_ADMIN)) {
			if (organisationmanagement.getOrganisation_UserByUserAndOrganisation(u.getUser_id(), o.getOrganisation_id()) == null){
				found = true;
				addOrganisation(u.getUser_id(), o.getOrganisation_id());
				break;
			}
		}
		if (!found) {
			addOrganisation(u.getUser_id(), createOrganisation(ORG_PREFIX, u.getUser_id()));
		}
	}
	
	@Test
	public void testUsers(){
		
		Sessiondata sessionData = mService.getsessiondata();
		
		Users us = (Users) mService.loginUser(sessionData.getSession_id(), USER_NAME, USER_PASS, false, 1L, -1L);
		
		SearchResult users = uService.getUserList(sessionData.getSession_id(), 0, 100, "firstname", false);
		
		assertNotNull("UserService return no results", users);
		
		log.error("Number of Users 1: "+users.getResult().size());
		log.error("Number of Users 2: "+users.getRecords());
		
		Users users2 = (Users) users.getResult().get(5);
		
		System.out.println("User 2: "+users2.getLogin());
		
		Users user3 = mService.getUser(sessionData.getSession_id(), users2.getUser_id().intValue());
		
		System.out.println("user3: "+user3.getLogin());
		
		mService.logoutUser(sessionData.getSession_id());
		
	}
}
