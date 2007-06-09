package org.xmlcrm.app.remote;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmlcrm.app.data.basic.Sessionmanagement;
import org.xmlcrm.app.data.beans.basic.SearchResult;
import org.xmlcrm.app.data.user.Usermanagement;
import org.xmlcrm.app.data.user.Organisationmanagement;
import org.xmlcrm.app.hibernate.beans.domain.Organisation;

/**
 * 
 * @author swagner
 *
 */
public class OrganisationService {
	
	private static final Log log = LogFactory.getLog(OrganisationService.class);
	
	/**
	 * Loads a List of all availible Organisations (ADmin-role only)
	 * @param SID
	 * @return
	 */
	public SearchResult getOrganisations(String SID, int start ,int max, String orderby, boolean asc){
		try {
	        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
	        long USER_LEVEL = Usermanagement.getInstance().getUserLevelByID(users_id);
	        return Organisationmanagement.getInstance().getOrganisations(USER_LEVEL,start,max,orderby,asc);
		} catch (Exception e){
			log.error("getOrganisations",e);
		}
		return null;
	}
	
	/**
	 * get an organisation by a given id
	 * @param SID
	 * @param organisation_id
	 * @return
	 */
	public Organisation getOrganisationById(String SID, long organisation_id){
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        long USER_LEVEL = Usermanagement.getInstance().getUserLevelByID(users_id);
        return Organisationmanagement.getInstance().getOrganisationById(USER_LEVEL, organisation_id);
	}
	
	/**
	 * deletes a organisation by a given id
	 * @param SID
	 * @param organisation_id
	 * @return
	 */
	public Long deleteOrganisation(String SID, long organisation_id){
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        long USER_LEVEL = Usermanagement.getInstance().getUserLevelByID(users_id);
        return Organisationmanagement.getInstance().deleteOrganisation(USER_LEVEL, organisation_id, users_id);
	}
	
	/**
	 * adds or updates an Organisation
	 * @param SID
	 * @param organisation_id
	 * @param orgname
	 * @return
	 */
	public Long saveOrUpdateOrganisation(String SID, Object regObjectObj){
		try {
	        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
	        long USER_LEVEL = Usermanagement.getInstance().getUserLevelByID(users_id);
	        LinkedHashMap argObjectMap = (LinkedHashMap) regObjectObj;
	        long organisation_id = Long.valueOf(argObjectMap.get("organisation_id").toString()).longValue();
	        LinkedHashMap users = (LinkedHashMap) argObjectMap.get("users");
	        if (organisation_id==0){
	        	return Organisationmanagement.getInstance().addOrganisation(USER_LEVEL, argObjectMap.get("orgname").toString(), users_id, users);
	        } else {
	        	return Organisationmanagement.getInstance().updateOrganisation(USER_LEVEL, organisation_id, argObjectMap.get("orgname").toString(), users_id, users);
	        }
		} catch (Exception err) {
			log.error("saveOrUpdateOrganisation",err);
		}
		return null;
        
	}
	
	/**
	 * gets all users of a given organisation
	 * @param SID
	 * @param organisation_id
	 * @param start
	 * @param max
	 * @param orderby
	 * @param asc
	 * @return
	 */
	public List getUsersByOrganisation(String SID, long organisation_id, int start, int max, String orderby, boolean asc){
		try {   
	        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
	        long USER_LEVEL = Usermanagement.getInstance().getUserLevelByID(users_id);
	        return Organisationmanagement.getInstance().getUsersByOrganisationId(USER_LEVEL, organisation_id, start, max, orderby, asc);
		} catch (Exception err) {
			log.error("getUsersByOrganisation",err);
		}
		return null;
	}


}
