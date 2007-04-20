package org.xmlcrm.app.remote;

import java.util.Date;
import java.util.List;
import java.util.LinkedHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmlcrm.app.data.basic.Sessionmanagement;
import org.xmlcrm.app.data.beans.basic.SearchResult;
import org.xmlcrm.app.data.user.Usermanagement;
import org.xmlcrm.app.data.user.Salutationmanagement;
import org.xmlcrm.app.data.user.Organisationmanagement;
import org.xmlcrm.app.hibernate.beans.user.Users;

public class UserService {
	
	private static final Log log = LogFactory.getLog(UserService.class);	
	
	/**
	 * get your own user-object
	 * @param SID
	 * @param user_id
	 * @return
	 */
	public Users getUserSelf(String SID, long user_id){
        int users_id = Sessionmanagement.getInstance().checkSession(SID);
        return Usermanagement.getInstance().getUser(users_id);
	}
	
	/**
	 * get user by id, admin only
	 * @param SID
	 * @param user_id
	 * @return
	 */
	public Users getUserById(String SID, long user_id){
        int users_id = Sessionmanagement.getInstance().checkSession(SID);
        long USER_LEVEL = Usermanagement.getInstance().getUserLevelByID(users_id);
        return Usermanagement.getInstance().checkAdmingetUserById(USER_LEVEL,user_id);
	}
	
	/**
	 * get all availible Salutations
	 * @param SID
	 * @return
	 */
	public List getUserSalutations(String SID){
        int users_id = Sessionmanagement.getInstance().checkSession(SID);
        long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(users_id);
        return Salutationmanagement.getInstance().getUserSalutations(User_LEVEL);
	}
	
	/**
	 * search a user by a string
	 * @param SID
	 * @param searchstring
	 * @return
	 */
    public List searchUser(String SID,String searchstring, int max, int start){   	
        int users_id = Sessionmanagement.getInstance().checkSession(SID);
        long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(users_id);    	
    	return Usermanagement.getInstance().searchUser(User_LEVEL,searchstring,max,start);
    }    
    
    /**
     * 
     * @param SID
     * @param DATA_KEY
     * @param DATA
     * @param Comment
     * @return
     */
    public Users addUserdata(String SID,String DATA_KEY,String DATA,String Comment){
        int users_id = Sessionmanagement.getInstance().checkSession(SID);
        long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(users_id);
        Users users = new Users();
        String ret = "Add Userdata Self";
        if(User_LEVEL>1){
            ret = Usermanagement.getInstance().addUserdata(users_id,DATA_KEY,DATA,Comment);
            if (ret.equals("success")){
                users = Usermanagement.getInstance().getUser(new Long(users_id));
            } else {
                users.setFirstname(ret);
            }
        } else {
            ret = "Nur als registrierter Benutzer kann man seinen Account lšschen";
            users.setFirstname(ret);
        }
        return users;
    }    
    
    /**
     * update user userdata self
     * @param SID
     * @param DATA_ID
     * @param DATA_KEY
     * @param DATA
     * @param Comment
     * @return
     */
    public Users updateUserdata(String SID, int DATA_ID, String DATA_KEY, String DATA, String Comment){
        int users_id = Sessionmanagement.getInstance().checkSession(SID);
        long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(users_id);
        if(User_LEVEL>1){
            Usermanagement.getInstance().updateUserdata(DATA_ID,users_id,DATA_KEY,DATA,Comment);
            return Usermanagement.getInstance().getUser(new Long(users_id));
        } else {
            return null;
        }
    }	
    
    /**
     * gets a List of users of an organisation
     * the user who calls this method must be an moderator at least
     * @param SID
     * @param organisation_id
     * @param start
     * @param max
     * @param orderby
     * @return
     */
    public List getorgUserList(String SID, long organisation_id, int start, int max, String orderby){
        int users_id = Sessionmanagement.getInstance().checkSession(SID);
        long USER_LEVEL = Usermanagement.getInstance().getUserLevelByID(users_id);
        return Organisationmanagement.getInstance().getOrganisationUsers(USER_LEVEL, users_id, organisation_id, start, max, orderby);
    }
    
    /**
     * gets a hole user-list(admin-role only)
     * @param SID
     * @param start
     * @param max
     * @param orderby
     * @return
     */
    public SearchResult getUserList(String SID, int start, int max, String orderby){
        int users_id = Sessionmanagement.getInstance().checkSession(SID);
        long USER_LEVEL = Usermanagement.getInstance().getUserLevelByID(users_id);
        return Usermanagement.getInstance().getUsersList(USER_LEVEL, start, max, orderby);
    }
    
    /**
     * updates the user profile, every user can update his own profile
     * @param SID
     * @param argObject
     * @return user_id or NULL or negativ value (error_id)
     */
    public Long updateUserSelfSmall(String SID, Object argObject){
    	try {
	        int users_id = Sessionmanagement.getInstance().checkSession(SID);
	        long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(users_id);
	        if(User_LEVEL>1){
	        	LinkedHashMap argObjectMap = (LinkedHashMap) argObject;
	            return Usermanagement.getInstance().updateUser(3,new Long(users_id), new Long(0), 
	            		argObjectMap.get("login").toString(), argObjectMap.get("password").toString(), 
	            		argObjectMap.get("lastname").toString(), argObjectMap.get("firstname").toString(), 
	            		new Date(), argObjectMap.get("street").toString(), 
	            		argObjectMap.get("additionalname").toString(),
	            		argObjectMap.get("zip").toString(), Long.valueOf(argObjectMap.get("states_id").toString()).longValue(), 
	            		argObjectMap.get("town").toString(), 1,
	            		argObjectMap.get("telefon").toString(),argObjectMap.get("fax").toString(),
	            		argObjectMap.get("mobil").toString(),argObjectMap.get("email").toString(),
	            		argObjectMap.get("comment").toString(),
	            		Integer.valueOf(argObjectMap.get("status").toString()).intValue(),
	            		null);
	        } else {
	            return new Long(-2);
	        }
    	} catch (Exception err){
    		log.error("[updateUserSelfSmall] "+err);
    		return new Long(-1);
    	}
    }    
    
    /**
     * 
     * @param SID
     * @param regObjectObj
     * @return
     */
    public Long saveOrUpdateUser(String SID, Object regObjectObj){
    	try {
    		LinkedHashMap argObjectMap = (LinkedHashMap) regObjectObj;
    		Long user_idClient = Long.valueOf(argObjectMap.get("user_idClient").toString()).longValue();
	        int users_id = Sessionmanagement.getInstance().checkSession(SID);
	        long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(users_id);	
	        
	        log.error("saveOrUpdateUser1: "+argObjectMap.get("organisations"));
	        log.error("saveOrUpdateUser2: "+argObjectMap.get("organisations").getClass());
	        log.error("saveOrUpdateUser3: "+argObjectMap.get("user_idClient"));
	        //TODO: there is a BUG here: value send is Time as GMT but here it gets CEST which is wrong	  
	        //but maybe a OS-related-issue
	        log.error("saveOrUpdateUser4: "+argObjectMap.get("userage"));
	        log.error("saveOrUpdateUser5: "+argObjectMap.get("userage").getClass());
	        
	        LinkedHashMap organisations = (LinkedHashMap) argObjectMap.get("organisations");
	        Date age = (Date) argObjectMap.get("userage");

	        log.error("saveOrUpdateUser6: "+age);
	        log.error("saveOrUpdateUser7: "+age.getTimezoneOffset());
	        
    		if (user_idClient==null || user_idClient==0){
	        	return Usermanagement.getInstance().registerUserInit(User_LEVEL, 
	        			Long.valueOf(argObjectMap.get("level_id").toString()).longValue(), 
	        			Integer.valueOf(argObjectMap.get("availible").toString()).intValue(),Integer.valueOf(argObjectMap.get("status").toString()).intValue(),
	        			argObjectMap.get("login").toString(), argObjectMap.get("password").toString(), 
	        			argObjectMap.get("lastname").toString(), argObjectMap.get("firstname").toString(), 
	        			argObjectMap.get("email").toString(), age, 
	        			argObjectMap.get("street").toString(), argObjectMap.get("additionalname").toString(), 
	        			argObjectMap.get("fax").toString(), argObjectMap.get("zip").toString(), 
	        			Long.valueOf(argObjectMap.get("states_id").toString()).longValue(), argObjectMap.get("town").toString(), 
	        			Long.valueOf(argObjectMap.get("states_id").toString()).longValue(),
	        			false,organisations); 	
    		} else {
		        return Usermanagement.getInstance().updateUser(User_LEVEL,user_idClient, 
		        		Long.valueOf(argObjectMap.get("level_id").toString()).longValue(), argObjectMap.get("login").toString(), 
		        		argObjectMap.get("password").toString(), argObjectMap.get("lastname").toString(), 
		        		argObjectMap.get("firstname").toString(), age, 
		        		argObjectMap.get("street").toString(), argObjectMap.get("additionalname").toString(), 
		        		argObjectMap.get("zip").toString(), Long.valueOf(argObjectMap.get("states_id").toString()).longValue(), 
		        		argObjectMap.get("town").toString(), Integer.valueOf(argObjectMap.get("availible").toString()).intValue(),
		        		argObjectMap.get("telefon").toString(),argObjectMap.get("fax").toString(),
		        		argObjectMap.get("mobil").toString(),
		        		argObjectMap.get("email").toString(),argObjectMap.get("comment").toString(),
		        		Integer.valueOf(argObjectMap.get("status").toString()).intValue(),
		        		organisations); 
    		}
    	} catch (Exception ex) {
    		log.error("[saveOrUpdateUser]: ",ex);
    	}
    	return null;
    }    
    
    public Long deleteUserAdmin(String SID, int user_idClient){
    	int users_id = Sessionmanagement.getInstance().checkSession(SID);
    	long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(users_id);
    	if(User_LEVEL>=3){
    		if (users_id!=user_idClient){
    		return Usermanagement.getInstance().deleteUserID(user_idClient);
    		} else {
    			return new Long(-10);
    		}
    	} else {
    		return new Long(-11);
    	}
    } 
    
    
}
