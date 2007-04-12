package org.xmlcrm.app.remote;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmlcrm.app.data.basic.Configurationmanagement;
import org.xmlcrm.app.data.basic.Sessionmanagement;
import org.xmlcrm.app.data.user.Usermanagement;
import org.xmlcrm.app.hibernate.beans.basic.Configuration;

public class ConfigurationService {
	
	private static final Log log = LogFactory.getLog(ConfigurationService.class);
	
	/*
	 * Configuration Handlers
	 */    
    public Configuration[] getAllConf(String SID){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        long User_Level = Usermanagement.getInstance().getUserLevelByID(User_ID);     	
        return Configurationmanagement.getInstance().getAllConf(User_Level);
    }
    public Configuration getConfKey(String SID,String CONF_KEY){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        long User_Level = Usermanagement.getInstance().getUserLevelByID(User_ID);     	
        return Configurationmanagement.getInstance().getConfKey(User_Level,CONF_KEY);
    }
    public String addConfByKey(String SID,String CONF_KEY,String CONF_VALUE,String comment){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        long User_Level = Usermanagement.getInstance().getUserLevelByID(User_ID);     	
        return Configurationmanagement.getInstance().addConfByKey(User_Level,CONF_KEY,CONF_VALUE, User_ID,comment);
    }
    public String updateConfByUID(String SID,int UID,String CONF_KEY,String CONF_VALUE,String comment){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        long User_Level = Usermanagement.getInstance().getUserLevelByID(User_ID);     	
        return Configurationmanagement.getInstance().updateConfByUID(User_Level,UID,CONF_KEY,CONF_VALUE, User_ID,comment);
    }    
    public String deleteConfByUID(String SID,int UID){ 
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        long User_Level = Usermanagement.getInstance().getUserLevelByID(User_ID);     	
        return Configurationmanagement.getInstance().deleteConfByUID(User_Level,UID);
    }
	    
}
