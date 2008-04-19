package org.openmeetings.test.userdata;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmeetings.app.hibernate.beans.basic.Sessiondata;
import org.openmeetings.app.hibernate.beans.user.Users;
import org.openmeetings.app.remote.MainService;
import org.openmeetings.app.remote.UserService;

import junit.framework.TestCase;

public class UpdateUserSelf extends TestCase {
	
	private static final Log log = LogFactory.getLog(UpdateUserSelf.class);
	
	public UpdateUserSelf(String testname){
		super(testname);
	}
	
}
