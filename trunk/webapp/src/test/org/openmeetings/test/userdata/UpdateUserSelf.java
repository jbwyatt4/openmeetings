package org.openmeetings.test.userdata;

import org.slf4j.Logger;
import org.red5.logging.Red5LoggerFactory;
import org.openmeetings.app.hibernate.beans.basic.Sessiondata;
import org.openmeetings.app.hibernate.beans.user.Users;
import org.openmeetings.app.remote.MainService;
import org.openmeetings.app.remote.UserService;

import junit.framework.TestCase;

public class UpdateUserSelf extends TestCase {
	
	private static final Logger log = Red5LoggerFactory.getLogger(UpdateUserSelf.class, "openmeetings");
	
	public UpdateUserSelf(String testname){
		super(testname);
	}
	
}
