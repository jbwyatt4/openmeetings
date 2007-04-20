package org.xmlcrm.app.remote;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.xmlcrm.app.data.basic.Sessionmanagement;
import org.xmlcrm.app.data.beans.basic.ErrorResult;
import org.xmlcrm.app.data.user.Usermanagement;

public class ErrorService {
	
	private static final Log log = LogFactory.getLog(MainService.class);
	
	/**
	 * Gets an Error-Object by its id
	 * TODO: add error-code-handlers
	 * and make the persistent in the DataBase
	 * @param SID
	 * @param errorid
	 * @return
	 */
	public ErrorResult getErrorByCode(String SID, long errorid){
        int users_id = Sessionmanagement.getInstance().checkSession(SID);
        long USER_LEVEL = Usermanagement.getInstance().getUserLevelByID(users_id);
        ErrorResult eResult = new ErrorResult();
        eResult.setErrmessage("Error ... please check your input");
        return eResult;
	}

}
