package org.xmlcrm.app.remote;

import java.util.List;
import java.util.LinkedHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmlcrm.app.data.basic.AuthLevelmanagement;
import org.xmlcrm.app.data.basic.Languagemanagement;
import org.xmlcrm.app.data.basic.Sessionmanagement;
import org.xmlcrm.app.data.user.Usermanagement;
import org.xmlcrm.app.data.basic.Fieldmanagment;
import org.xmlcrm.app.data.beans.basic.SearchResult;
import org.xmlcrm.app.hibernate.beans.lang.Fieldlanguagesvalues;
import org.xmlcrm.app.hibernate.beans.lang.Fieldvalues;

/**
 * 
 * @author sebastianwagner
 *
 */
public class LanguageService {
	
	private static final Log log = LogFactory.getLog(LanguageService.class);
	
	/**
	 * get a List of all availible Languages
	 * @return
	 */
	public List getLanguages(){
		return Languagemanagement.getInstance().getLanguages();
	}
	
	/**
	 * get all fileds of a given Language_id
	 * @param language_id
	 * @return
	 */
	public List<Fieldlanguagesvalues> getLanguageById(Long language_id){
		return Fieldmanagment.getInstance().getAllFieldsByLanguage(language_id);
	}
	
	public Fieldvalues getFieldvalueById(String SID, Long fieldvalues_id, Long language_id) {
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);
        if (AuthLevelmanagement.getInstance().checkAdminLevel(user_level)) {
        	return Fieldmanagment.getInstance().getFieldvaluesById(fieldvalues_id, language_id);
        }
        return null;
	}
	
	public Long addLanguage(String SID, String langName) {
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);
        if (AuthLevelmanagement.getInstance().checkAdminLevel(user_level)) {
        	if (langName.length()==0) return new Long(-30);
        	return Languagemanagement.getInstance().addLanguage(langName);
        }
        return null;
	}
	
	public Long updateLanguage(String SID, Long language_id, String langName) {
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);
        if (AuthLevelmanagement.getInstance().checkAdminLevel(user_level)) {
        	if (langName.length()==0) return new Long(-30);
        	return Languagemanagement.getInstance().updateFieldLanguage(language_id, langName, "false");
        }
        return null;
	}
	
	public Long deleteLanguage(String SID, Long language_id) {
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);
        if (AuthLevelmanagement.getInstance().checkAdminLevel(user_level)) {
        	return Languagemanagement.getInstance().updateFieldLanguage(language_id, "", "true");
        }
        return null;
	}
	
	public Long deleteFieldlanguagesvaluesById(String SID, Long fieldlanguagesvalues_id) {
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);
        if (AuthLevelmanagement.getInstance().checkAdminLevel(user_level)){
        	return Fieldmanagment.getInstance().deleteFieldlanguagesvaluesById(fieldlanguagesvalues_id);
        }
        return null;
	}
	
	/**
	 * 
	 * @param SID
	 * @param start
	 * @param max
	 * @param orderby
	 * @param asc
	 * @param language_id
	 * @return
	 */
	public SearchResult getFieldsByLanguage(String SID, int start, int max, String orderby, boolean asc, Long language_id){
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);
        if (AuthLevelmanagement.getInstance().checkAdminLevel(user_level)){
        	return Fieldmanagment.getInstance().getFieldsByLanguage(start, max, orderby, asc, language_id);
        }
		return null;
	}
	
	/**
	 * 
	 * @param SID
	 * @param values
	 * @return
	 */
	public Long saveOrUpdateLabel(String SID, LinkedHashMap<Object,Object> values)  {
		try {
			Long users_id = Sessionmanagement.getInstance().checkSession(SID);
			Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);
			Long fieldvalues_id = Long.valueOf(values.get("fieldvalues_id").toString()).longValue();
			String name = values.get("name").toString(); 
			Long fieldlanguagesvalues_id = Long.valueOf(values.get("fieldlanguagesvalues_id").toString()).longValue();
			Long language_id = Long.valueOf(values.get("language_id").toString()).longValue();
			String value = values.get("value").toString(); 
			if (AuthLevelmanagement.getInstance().checkAdminLevel(user_level)){
				if (fieldvalues_id>0 && fieldlanguagesvalues_id>0){
					log.error("UPDATE LABEL");
					return Fieldmanagment.getInstance().updateLabel(fieldvalues_id, name, fieldlanguagesvalues_id, value);
				} else if (fieldvalues_id>0 && fieldlanguagesvalues_id==0) {
					log.error("INSERT NEW LABEL");
					return Fieldmanagment.getInstance().addAndUpdateLabel(fieldvalues_id, name, value, language_id);
				} else {
					log.error("INSERT NEW FIELD AND LABEL");
					return Fieldmanagment.getInstance().addFieldAndLabel(name, value, language_id);
				}
			}
			return new Long(-26);	
		} catch (Exception e) {
			log.error("[saveOrUpdateLabel]",e);
		}
		return new Long(-1);	
	}	

}
