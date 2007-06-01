package org.xmlcrm.app.data.basic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AuthLevelmanagement {
	
	private static final Log log = LogFactory.getLog(AuthLevelmanagement.class);

	private AuthLevelmanagement() {}
	
	private static AuthLevelmanagement instance = null;
	
	public static synchronized AuthLevelmanagement getInstance() {
		if (instance == null) {
			instance = new AuthLevelmanagement();
		}
		return instance;
	}

	public boolean checkUserLevel(long USER_LEVEL) {
		if (USER_LEVEL > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkModLevel(long USER_LEVEL) {
		if (USER_LEVEL > 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkAdminLevel(long USER_LEVEL) {
		if (USER_LEVEL > 2) {
			return true;
		} else {
			return false;
		}
	}

}
