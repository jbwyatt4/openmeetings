package org.xmlcrm.app.remote;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import org.xmlcrm.app.data.basic.Fieldmanagment;
import org.xmlcrm.app.data.beans.basic.ErrorResult;
import org.xmlcrm.app.data.basic.ErrorManagement;
import org.xmlcrm.app.hibernate.beans.basic.ErrorValues;
import org.xmlcrm.app.hibernate.beans.lang.Fieldlanguagesvalues;
import org.xmlcrm.app.hibernate.utils.HibernateUtil;

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
	public ErrorResult getErrorByCode(String SID, Long errorid, Long language_id){
        //Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        //long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);

        if (errorid<0){
        	log.error("errorid,language_id: "+errorid+"|"+language_id);
        	ErrorValues eValues = ErrorManagement.getInstance().getErrorValuesById(errorid*(-1));
	        if (eValues!=null){
	        	log.error(eValues.getFieldvalues());
	        	log.error(eValues.getFieldvalues().getFieldvalues_id());
	        	log.error(eValues.getErrorType());
	        	log.error(eValues.getErrorType().getErrortype_id());
	        	log.error(eValues.getErrorType().getFieldvalues());
	        	log.error(eValues.getErrorType().getFieldvalues().getFieldvalues_id());
	        	Fieldlanguagesvalues errorValue = Fieldmanagment.getInstance().getFieldByIdAndLanguage(eValues.getFieldvalues().getFieldvalues_id(),language_id);
	        	Fieldlanguagesvalues typeValue = Fieldmanagment.getInstance().getFieldByIdAndLanguage(eValues.getErrorType().getFieldvalues().getFieldvalues_id(),language_id);
	        	if (errorValue!=null) {
	        		return new ErrorResult(errorid,errorValue.getValue(),typeValue.getValue());
	        	}
        	}
        } else {
        	return new ErrorResult(errorid,"Error ... please check your input","Error");
        }
        
        return null;
	}
	

}
