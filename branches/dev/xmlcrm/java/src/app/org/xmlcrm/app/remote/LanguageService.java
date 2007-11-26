package org.xmlcrm.app.remote;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmlcrm.app.data.basic.AuthLevelmanagement;
import org.xmlcrm.app.data.basic.Sessionmanagement;
import org.xmlcrm.app.data.user.Usermanagement;
import org.xmlcrm.app.data.basic.Fieldmanagment;

public class LanguageService {
	
	private static final Log log = LogFactory.getLog(LanguageService.class);
	
	
	
	public Long updateLabel(String SID, Long fieldvalues_id, String name, 
			Long fieldlanguagesvalues_id, String value) {
		Long users_id = Sessionmanagement.getInstance().checkSession(SID);
		Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);
		if (AuthLevelmanagement.getInstance().checkAdminLevel(user_level)){
			return Fieldmanagment.getInstance().updateLabel(fieldvalues_id, name, fieldlanguagesvalues_id, value);
		}
		return new Long(-26);		
	}

}
