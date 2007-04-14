package org.xmlcrm.app.remote;

import java.util.LinkedHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmlcrm.app.data.basic.Sessionmanagement;
import org.xmlcrm.app.data.user.Usermanagement;
import org.xmlcrm.app.hibernate.beans.user.Users;

public class UserService {
	
	private static final Log log = LogFactory.getLog(UserService.class);	
	
	/**
	 * search a user by a string
	 * @param SID
	 * @param searchstring
	 * @return
	 */
    public Users[] searchUser(String SID,String searchstring){   	
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);    	
    	return Usermanagement.getInstance().searchUser(User_LEVEL,searchstring);
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
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);
        Users users = new Users();
        String ret = "Add Userdata Self";
        if(User_LEVEL>1){
            ret = Usermanagement.getInstance().addUserdata(User_ID,DATA_KEY,DATA,Comment);
            if (ret.equals("success")){
                users = Usermanagement.getInstance().getUser(new Long(User_ID));
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
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);
        if(User_LEVEL>1){
            Usermanagement.getInstance().updateUserdata(DATA_ID,User_ID,DATA_KEY,DATA,Comment);
            return Usermanagement.getInstance().getUser(new Long(User_ID));
        } else {
            return null;
        }
    }	
    
    /**
     * updates the user profile, every user can update his own profile
     * @param SID
     * @param argObject
     * @return user_id or NULL or negativ value (error_id)
     */
    public Long updateUserSelfSmall(String SID, Object argObject){
    	try {
	        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
	        long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);
	        if(User_LEVEL>1){
	        	LinkedHashMap argObjectMap = (LinkedHashMap) argObject;
	            return Usermanagement.getInstance().updateUser(3,new Long(User_ID), new Long(0), 
	            		argObjectMap.get("login").toString(), argObjectMap.get("password").toString(), 
	            		argObjectMap.get("lastname").toString(), argObjectMap.get("firstname").toString(), 
	            		0, argObjectMap.get("street").toString(), 
	            		argObjectMap.get("additionalname").toString(),
	            		argObjectMap.get("zip").toString(), Long.valueOf(argObjectMap.get("states_id").toString()).longValue(), 
	            		argObjectMap.get("town").toString(), 1,
	            		argObjectMap.get("telefon").toString(),argObjectMap.get("fax").toString(),
	            		argObjectMap.get("mobil").toString(),argObjectMap.get("email").toString(),
	            		argObjectMap.get("comment").toString());
	        } else {
	            return new Long(-2);
	        }
    	} catch (Exception err){
    		log.error("[updateUserSelfSmall] "+err);
    		return new Long(-1);
    	}
    }

    /**
     * @deprecated
     * @param SID
     * @param login
     * @param password
     * @param lastname
     * @param firstname
     * @param age
     * @param street
     * @param additionalname
     * @param Zip
     * @param states_id
     * @param town
     * @param EMailID
     * @param email
     * @param availible
     * @param telefon
     * @param fax
     * @param mobil
     * @param comment
     * @return
     */
    public Long updateUser(String SID, String login, String password, String lastname, String firstname, int age, String street, String additionalname, String Zip, long states_id, String town, String email, int availible, String telefon, String fax, String mobil, String comment){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        //User updates hisself, always allowed
        return Usermanagement.getInstance().updateUser(3,new Long(User_ID), new Long(0), login, password, lastname, firstname, age, street, additionalname, Zip, states_id, town, availible,telefon,fax,mobil,email,comment);
    }
    
    public Long updateUserByObject(String SID, Object argObject){
    	try{
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        //User updates hisself, always allowed
        LinkedHashMap argObjectMap = (LinkedHashMap) argObject;
        return Usermanagement.getInstance().updateUser(3,new Long(User_ID), new Long(0), 
        		argObjectMap.get("login").toString(), argObjectMap.get("password").toString(), 
        		argObjectMap.get("lastname").toString(), argObjectMap.get("firstname").toString(), 
        		Integer.valueOf(argObjectMap.get("age").toString()).intValue(), argObjectMap.get("street").toString(), 
        		argObjectMap.get("additionalname").toString(),
        		argObjectMap.get("Zip").toString(), Long.valueOf(argObjectMap.get("states_id").toString()).longValue(), 
        		argObjectMap.get("town").toString(), Integer.valueOf(argObjectMap.get("availible").toString()).intValue(),
        		argObjectMap.get("telefon").toString(),argObjectMap.get("fax").toString(),
        		argObjectMap.get("mobil").toString(),argObjectMap.get("email").toString(),
        		argObjectMap.get("comment").toString());
    	} catch (Exception er){
    		log.error("[updateUserByObject]"+er);
    	}
    	return null;
    }
    
    /**
     * @deprecated
     * @param SID
     * @param user_idClient
     * @param level_id
     * @param login
     * @param password
     * @param lastname
     * @param firstname
     * @param age
     * @param street
     * @param additionalname
     * @param Zip
     * @param states_id
     * @param town
     * @param EMailID
     * @param email
     * @param availible
     * @param telefon
     * @param fax
     * @param mobil
     * @param comment
     * @return
     */
    public Long updateUserAdmin(String SID, int user_idClient, int level_id, String login, String password, 
    		String lastname, String firstname, int age, String street, String additionalname, 
    		String Zip, long states_id, String town, String email, int availible, 
    		String telefon, String fax, String mobil, String comment){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);	
        return Usermanagement.getInstance().updateUser(User_LEVEL,new Long(user_idClient), new Long(level_id), login, password, lastname, firstname, age, street, additionalname, Zip, states_id, town, availible,telefon,fax,mobil,email,comment);
    } 
    
    
    public Long updateUserObjectAdmin(String SID, Object argObject){
    	try{
	    	LinkedHashMap argObjectMap = (LinkedHashMap) argObject;
	        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
	        long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);	
	        return Usermanagement.getInstance().updateUser(User_LEVEL,Long.valueOf(argObjectMap.get("user_idClient").toString()).longValue(), 
	        		Long.valueOf(argObjectMap.get("level_id").toString()).longValue(), argObjectMap.get("login").toString(), 
	        		argObjectMap.get("password").toString(), argObjectMap.get("lastname").toString(), 
	        		argObjectMap.get("firstname").toString(), Integer.valueOf(argObjectMap.get("age").toString()).intValue(), 
	        		argObjectMap.get("street").toString(), argObjectMap.get("additionalname").toString(), 
	        		argObjectMap.get("zip").toString(), Long.valueOf(argObjectMap.get("states_id").toString()).longValue(), 
	        		argObjectMap.get("town").toString(), Integer.valueOf(argObjectMap.get("availible").toString()).intValue(),
	        		argObjectMap.get("telefon").toString(),argObjectMap.get("fax").toString(),
	        		argObjectMap.get("mobil").toString(),
	        		argObjectMap.get("email").toString(),argObjectMap.get("comment").toString());
    	} catch (Exception err){
    		log.error("[updateUserObjectAdmin]"+err);
    	}
    	return null;
    } 
    
    
    public String deleteUserAdmin(String SID, int user_idClient){
    	String ret = "";
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);
    	if(User_LEVEL>=3){
    		ret = Usermanagement.getInstance().logout(SID,user_idClient);
    		ret = Usermanagement.getInstance().deleteUserID(user_idClient);
    	} else {
    		ret = "Nur als admin";
    	}
    	return ret;
    }      
}
