package org.xmlcrm.app.remote;

import java.util.LinkedHashMap;

import org.xmlcrm.app.data.basic.Configurationmanagement;
import org.xmlcrm.app.data.basic.Sessionmanagement;
import org.xmlcrm.app.data.beans.basic.SearchResult;
import org.xmlcrm.app.data.user.Usermanagement;
import org.xmlcrm.app.hibernate.beans.basic.Configuration;

/**
 * 
 * @author swagner
 *
 */
public class ConfigurationService {
	
	/*
	 * Configuration Handlers
	 */    
    public SearchResult getAllConf(String SID, int start ,int max, String orderby, boolean asc){
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        Long User_Level = Usermanagement.getInstance().getUserLevelByID(users_id);     	
        return Configurationmanagement.getInstance().getAllConf(User_Level, start, max, orderby, asc);
    }
    public Configuration getConfByConfigurationId(String SID,long configuration_id){
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        Long User_Level = Usermanagement.getInstance().getUserLevelByID(users_id);     	
        return Configurationmanagement.getInstance().getConfByConfigurationId(User_Level,configuration_id);
    }
    public Long saveOrUpdateConfiguration(String SID,LinkedHashMap values){
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        Long User_Level = Usermanagement.getInstance().getUserLevelByID(users_id);     	
        return Configurationmanagement.getInstance().saveOrUpdateConfiguration(User_Level,values, users_id);
    }    
    public Long deleteConfiguration(String SID,LinkedHashMap values){
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        Long User_Level = Usermanagement.getInstance().getUserLevelByID(users_id);     	
        return Configurationmanagement.getInstance().deleteConfByConfiguration(User_Level, values, users_id);
    }
	    
}
