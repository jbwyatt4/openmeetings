package org.xmlcrm.app.remote;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import org.xmlcrm.app.data.basic.Fieldmanagment;
import org.xmlcrm.app.data.beans.basic.ErrorResult;
import org.xmlcrm.app.hibernate.beans.lang.Fieldlanguagesvalues;

/**
 * 
 * @author swagner
 *
 */
public class ErrorService {
	
	private static final Log log = LogFactory.getLog(MainService.class);
	
	/**
	 * Gets an Error-Object by its id
	 * TODO: add error-code-handlers
	 * -20 duplicate FileName
	 * -21 FileName too short (length = 0)
	 * and make the persistent in the DataBase
	 * @param SID
	 * @param errorid
	 * @return
	 */
	public ErrorResult getErrorByCode(String SID, long errorid, long language_id){
        //Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        //long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);
        ErrorResult eResult = new ErrorResult();
        if (errorid==-20) {
        	eResult.setErrmessage("duplicate filename, please choose another filename");
        } else if (errorid==-21){
        	eResult.setErrmessage("filename too short");
        } else if (errorid<0){
        	log.error("errorid,language_id: "+errorid+"|"+language_id);
        	Fieldlanguagesvalues fValues = Fieldmanagment.getInstance().getFieldByIdAndLanguage(errorid*(-1),language_id);
        	if (fValues!=null) {
        		eResult.setErrmessage(fValues.getValue());
        	} else {
        		eResult.setErrmessage("Error ... please check your input no error ID given: "+errorid);
        	}
        } else {
        	eResult.setErrmessage("Error ... please check your input");
        }
        return eResult;
	}

}
