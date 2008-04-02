package org.xmlcrm.test.userdata;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmlcrm.app.hibernate.beans.basic.Sessiondata;
import org.xmlcrm.app.hibernate.beans.user.Users;
import org.xmlcrm.app.remote.MainService;
import org.xmlcrm.app.remote.UserService;

import junit.framework.TestCase;

public class UpdateUserSelf extends TestCase {
	
	private static final Log log = LogFactory.getLog(UpdateUserSelf.class);
	
	public UpdateUserSelf(String testname){
		super(testname);
	}
	
}
