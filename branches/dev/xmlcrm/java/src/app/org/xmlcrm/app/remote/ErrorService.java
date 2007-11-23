package org.xmlcrm.app.remote;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import org.xmlcrm.app.data.basic.Fieldmanagment;
import org.xmlcrm.app.data.beans.basic.ErrorResult;
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

        if (errorid==-20) {
        	return new ErrorResult(errorid,"duplicate filename, please choose another filename");
        } else if (errorid==-21){
        	return new ErrorResult(errorid,"filename too short");
        } else if (errorid<0){
        	log.error("errorid,language_id: "+errorid+"|"+language_id);
        	ErrorValues eValues = this.getErrorValuesById(errorid*(-1));
	        if (eValues!=null){
	        	Fieldlanguagesvalues fValues = Fieldmanagment.getInstance().getFieldByIdAndLanguage(eValues.getFieldvalues().getFieldvalues_id(),language_id);
	        	if (fValues!=null) {
	        		return new ErrorResult(errorid,fValues.getValue());
	        	}
        	}
        } else {
        	return new ErrorResult(errorid,"Error ... please check your input");
        }
        
        return null;
	}
	
	public ErrorValues getErrorValuesById(Long errorValuesId) {
		try {
			String hql = "select c from ErrorValues as c " +
					" where c.errorValuesId = :errorValuesId " +
					" AND c.deleted != :deleted";
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setLong("errorValuesId", errorValuesId);
			query.setString("deleted", "true");
			ErrorValues e = (ErrorValues) query.uniqueResult();
			tx.commit();
			HibernateUtil.closeSession(idf);
			return e;
		} catch (HibernateException ex) {
			log.error("[getErrorValuesById]",ex);
		} catch (Exception ex2) {
			log.error("[getErrorValuesById]",ex2);
		}
		return null;
	}	
	

}
