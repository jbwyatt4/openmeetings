package org.xmlcrm.app.remote;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmlcrm.app.data.basic.Sessionmanagement;
import org.xmlcrm.app.data.beans.basic.SearchResult;
import org.xmlcrm.app.data.user.Usermanagement;
import org.xmlcrm.app.data.user.Organisationmanagement;

public class OrganisationService {
	
	private static final Log log = LogFactory.getLog(OrganisationService.class);
	
	/**
	 * Loads a List of all availible Organisations (ADmin-role only)
	 * @param SID
	 * @return
	 */
	public SearchResult getOrganisations(String SID, int start ,int max, String orderby){
        int users_id = Sessionmanagement.getInstance().checkSession(SID);
        long USER_LEVEL = Usermanagement.getInstance().getUserLevelByID(users_id);
        return Organisationmanagement.getInstance().getOrganisations(USER_LEVEL,start,max,orderby);
	}
	
	
	
	/**
	 * @deprecated
	 * @param SID
	 * @param orgname
	 * @return
	 */
	public Long addOrganisation(String SID, String orgname){
        int users_id = Sessionmanagement.getInstance().checkSession(SID);
        long USER_LEVEL = Usermanagement.getInstance().getUserLevelByID(users_id);
        return Organisationmanagement.getInstance().addOrganisation(USER_LEVEL, orgname, users_id);
	}
	
	/**
	 * adds or updates an Organisation
	 * @param SID
	 * @param organisation_id
	 * @param orgname
	 * @return
	 */
	public Long saveOrUpdateorganisation(String SID, long organisation_id, String orgname){
        int users_id = Sessionmanagement.getInstance().checkSession(SID);
        long USER_LEVEL = Usermanagement.getInstance().getUserLevelByID(users_id);
        if (organisation_id==0){
        	return Organisationmanagement.getInstance().addOrganisation(USER_LEVEL, orgname, users_id);
        } else {
        	return Organisationmanagement.getInstance().updateOrganisation(USER_LEVEL, organisation_id, orgname, users_id);
        }
        
	}


}
