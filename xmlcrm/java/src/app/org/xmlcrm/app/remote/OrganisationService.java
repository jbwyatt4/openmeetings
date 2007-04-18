package org.xmlcrm.app.remote;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmlcrm.app.data.basic.Sessionmanagement;
import org.xmlcrm.app.data.user.Usermanagement;
import org.xmlcrm.app.data.user.Organisationmanagement;

public class OrganisationService {
	
	private static final Log log = LogFactory.getLog(OrganisationService.class);
	
	/**
	 * Loads a List of all availible Organisations (ADmin-role only)
	 * @param SID
	 * @return
	 */
	public List getOrganisations(String SID){
        int users_id = Sessionmanagement.getInstance().checkSession(SID);
        long USER_LEVEL = Usermanagement.getInstance().getUserLevelByID(users_id);
        return Organisationmanagement.getInstance().getOrganisations(USER_LEVEL);
	}


}
