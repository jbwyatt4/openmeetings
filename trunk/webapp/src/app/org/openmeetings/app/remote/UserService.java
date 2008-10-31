package org.openmeetings.app.remote;

import java.util.Date;
import java.util.List;
import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openmeetings.app.data.basic.Sessionmanagement;
import org.openmeetings.app.data.beans.basic.SearchResult;
import org.openmeetings.app.data.user.Usermanagement;
import org.openmeetings.app.data.user.Salutationmanagement;
import org.openmeetings.app.data.user.Organisationmanagement;
import org.openmeetings.app.hibernate.beans.user.Users;

import org.red5.io.utils.ObjectMap;

/**
 * 
 * @author swagner
 *
 */
public class UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);	
	
	/**
	 * get your own user-object
	 * @param SID
	 * @param user_id
	 * @return
	 */
	public Users getUserSelf(String SID){
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        return Usermanagement.getInstance().getUser(users_id);
	}
	
	public Long resetUserPwd(String SID, String email, String login, String applink){
		Sessionmanagement.getInstance().checkSession(SID);
		return Usermanagement.getInstance().resetUser(email, login, applink);
	}
	
	public Object getUserByHash(String SID, String hash) {
		Sessionmanagement.getInstance().checkSession(SID);
		return Usermanagement.getInstance().getUserByHash(hash);
	}
	
	public Object resetPassByHash(String SID, String hash, String pass) {
		Sessionmanagement.getInstance().checkSession(SID);
		return Usermanagement.getInstance().resetPassByHash(hash,pass);
	}
	
	/**
	 * get user by id, admin only
	 * @param SID
	 * @param user_id
	 * @return
	 */
	public Users getUserById(String SID, long user_id){
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);
        return Usermanagement.getInstance().checkAdmingetUserById(user_level,user_id);
	}
	
	/**
	 * get all availible Salutations
	 * @param SID
	 * @return
	 */
	public List getUserSalutations(String SID, long language_id){
        return Salutationmanagement.getInstance().getUserSalutations(language_id);
	}
	
	/**
	 * 
	 * @param SID
	 * @param searchcriteria login,lastname,firstname,user_id
	 * @param searchstring
	 * @param max
	 * @param start
	 * @param orderby login,lastname,firstname,user_id
	 * @param asc
	 * @return
	 */
    public List searchUser(String SID, String searchcriteria ,String searchstring, int max, int start, String orderby, boolean asc){   	
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);    	
    	return Usermanagement.getInstance().searchUser(user_level,searchcriteria,searchstring,max,start,orderby,asc);
    }    
    
    /**
     * get a list of all users of an organisation
     * @param SID
     * @param organisation_id
     * @param start
     * @param max
     * @param orderby
     * @param asc
     * @return
     */
    public List getOrgUserList(String SID, long organisation_id, int start, int max, String orderby, boolean asc){
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);
        return Organisationmanagement.getInstance().getUsersByOrganisationId(organisation_id, start, max, orderby, asc);
    }
    
    public List getUserListByModForm(String SID){
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);
        return Usermanagement.getInstance().getUserByMod(user_level, users_id);
    }
    
    /**
     * gat a list of all organisations of an user
     * @param SID
     * @param client_user
     * @param start
     * @param max
     * @param orderby
     * @param asc
     * @return
     */
    public List getOrganisationListByUser(String SID, long client_user, int start, int max, String orderby, boolean asc){
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);
        return Organisationmanagement.getInstance().getOrganisationsByUserId(user_level, client_user, start, max, orderby, asc);
    }    
    
    /**
     * gets a list of all not assigned organisations of a user
     * @param SID
     * @param client_user
     * @param start
     * @param max
     * @param orderby
     * @param asc
     * @return
     */
    public List getRestOrganisationListByUser(String SID, long client_user, int start, int max, String orderby, boolean asc){
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);
        return Organisationmanagement.getInstance().getRestOrganisationsByUserId(user_level, client_user, start, max, orderby, asc);
    }
    
    
    /**
     * gets a hole user-list(admin-role only)
     * @param SID
     * @param start
     * @param max
     * @param orderby
     * @return
     */
    public SearchResult getUserList(String SID, int start, int max, String orderby, boolean asc){
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);
        return Usermanagement.getInstance().getUsersList(user_level, start, max, orderby, asc);
    }
    
    /**
     * gets a user-list by search criteria
     * @param SID
     * @param search
     * @param start
     * @param max
     * @param orderby
     * @return
     */
    public SearchResult getAllUserBySearchRange(String SID, String search, int start, int max, String orderby, boolean asc){
        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
        Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);
        return Usermanagement.getInstance().getAllUserByRange(search, start, max, orderby, asc);
    }
    
    /**
     * updates the user profile, every user can update his own profile
     * @param SID
     * @param argObject
     * @return user_id or NULL or negativ value (error_id)
     */
    public Long updateUserSelfSmall(String SID, ObjectMap values ){
    	try {
	        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
	        Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);
	        if(user_level!=null && user_level>=1){
	        	return Usermanagement.getInstance().saveOrUpdateUser(new Long(3),values, users_id);
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
    		Long user_idClient = null;
    		if (argObjectMap.get("user_idClient")!=null){
    			user_idClient = Long.valueOf(argObjectMap.get("user_idClient").toString()).longValue();
    		}
    		//log.error("saveOrUpdateUser1: ");
	        Long users_id = Sessionmanagement.getInstance().checkSession(SID);
	        //log.error("saveOrUpdateUser2: ");
	        Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);	
	        
//	        log.error("saveOrUpdateUser1: "+argObjectMap.get("organisations"));
//	        log.error("saveOrUpdateUser2: "+argObjectMap.get("organisations").getClass());
//	        log.error("saveOrUpdateUser3: "+argObjectMap.get("user_idClient"));
	        //TODO: there is a BUG here: value send is Time as GMT but here it gets CEST which is wrong	  
	        //but maybe a OS-related-issue
	        //log.error("saveOrUpdateUser4: "+argObjectMap.get("userage"));
	        //log.error("saveOrUpdateUser5: "+argObjectMap.get("userage").getClass());
	        
	        LinkedHashMap organisations = (LinkedHashMap) argObjectMap.get("organisations");
	        Date age = null;
	        if (argObjectMap.get("userage") instanceof Date){
	        	age = (Date) argObjectMap.get("userage");
	        	log.error("saveOrUpdateUser7: "+age.getTimezoneOffset());
	        }	        

	        //log.error("saveOrUpdateUser6: "+age);
	       
    		if (user_idClient==null || user_idClient==0){
	        	return Usermanagement.getInstance().registerUserInit(user_level, 
	        			Long.valueOf(argObjectMap.get("level_id").toString()).longValue(), 
	        			Integer.valueOf(argObjectMap.get("availible").toString()).intValue(),Integer.valueOf(argObjectMap.get("status").toString()).intValue(),
	        			argObjectMap.get("login").toString(), argObjectMap.get("password").toString(), 
	        			argObjectMap.get("lastname").toString(), argObjectMap.get("firstname").toString(), 
	        			argObjectMap.get("email").toString(), age, 
	        			argObjectMap.get("street").toString(), argObjectMap.get("additionalname").toString(), 
	        			argObjectMap.get("fax").toString(), argObjectMap.get("zip").toString(), 
	        			Long.valueOf(argObjectMap.get("states_id").toString()).longValue(), argObjectMap.get("town").toString(), 
	        			0,
	        			false,organisations); 	
    		} else {
		        return Usermanagement.getInstance().updateUser(user_level,user_idClient, 
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
		        		organisations,
		        		Integer.valueOf(argObjectMap.get("title_id").toString()).intValue()); 
    		}
    	} catch (Exception ex) {
    		log.error("[saveOrUpdateUser]: ",ex);
    	}
    	return null;
    }    
    
    /**
     * deletes a user
     * @param SID
     * @param user_idClient
     * @return
     */
    public Long deleteUserAdmin(String SID, int user_idClient){
    	Long users_id = Sessionmanagement.getInstance().checkSession(SID);
    	long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);
    	if(user_level>=3){
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
