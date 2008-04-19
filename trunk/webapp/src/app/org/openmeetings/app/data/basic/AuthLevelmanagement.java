package org.openmeetings.app.data.basic;

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

	public boolean checkUserLevel(Long user_level) {
		if (user_level > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkModLevel(Long user_level) {
		if (user_level > 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkAdminLevel(Long user_level) {
		if (user_level > 2) {
			return true;
		} else {
			return false;
		}
	}

}
