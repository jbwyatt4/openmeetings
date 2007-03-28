package org.xmlcrm.app.remote;

import java.util.List;
import java.util.HashSet;
import java.util.LinkedHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.xmlcrm.app.hibernate.beans.basic.Configuration;
import org.xmlcrm.app.hibernate.beans.basic.Sessiondata;

import org.xmlcrm.app.hibernate.beans.shop.lieferarten;
import org.xmlcrm.app.hibernate.beans.shop.products;
import org.xmlcrm.app.hibernate.beans.shop.zahlungsarten;

import org.xmlcrm.app.hibernate.beans.user.Users_Usergroups;
import org.xmlcrm.app.hibernate.beans.user.Users;
import org.xmlcrm.app.hibernate.beans.user.Userwaren;
import org.xmlcrm.app.hibernate.beans.user.Usergroups;

import org.xmlcrm.app.data.basic.*;
import org.xmlcrm.app.data.user.Usermanagement;
import org.xmlcrm.app.data.user.Statemanagement;

public class MainService {
	
	private static final Log log = LogFactory.getLog(MainService.class);
	
	private ResHandler ResHandler;
	public MainService(){
		ResHandler = new ResHandler();
	}
	
	public List getLanguages(){
		return Languagemanagement.getInstance().getLanguages();
	}
	
	public List getLanguageById(int language_id){
		return Fieldmanagment.getInstance().getAllFieldsByLanguage(language_id);
	}
   
	public List getNavi(String SID, long language_id){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);
		return Navimanagement.getInstance().getMainMenu(User_LEVEL,User_ID, language_id);
	}
  
	public Users getUser(String SID,int USER_ID){
		Users users = new Users();
		int User_ID = Sessionmanagement.getInstance().checkSession(SID);
		long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);
    	if(User_LEVEL>2){
    		users = Usermanagement.getInstance().getUser(new Long(USER_ID));
    	} else {
    		users.setFirstname("No rights to do this");
    	}
		return users;
	}
	
	/**
	 * This Method is jsut for testing
	 * you can find the corresponding
	 * CLietn Function in
	 * xmlcrm/auth/checkLoginData.lzx
	 * @param myObject2
	 * @return
	 */
	public int testObject(Object myObject2){
		try {
			LinkedHashMap myObject = (LinkedHashMap) myObject2;
			log.error("testObject "+myObject.size());
			log.error("testObject "+myObject.get(1));
			log.error("testObject "+myObject.get("stringObj"));
			return myObject.size();
		} catch (Exception e){
			log.error("ex: "+e);
		}
		return -1;
	}

	
	/**
	 * load this session id before doing anything else
	 * @return a unique session identifier
	 */
    public Sessiondata getsessiondata(){
        return Sessionmanagement.getInstance().startsession();
    }   
    
    /**
     * auth function, use the SID you get by getsessiondata
     * @param SID
     * @param Username
     * @param Userpass
     * @return a valid user account or an empty user with an error message and level -1
     */
    public Users loginUser(String SID, String Username, String Userpass){
    	System.out.println("loginUser 1: "+SID+" "+Username+" "+Userpass);
    	log.error("loginUser 2: "+SID+" "+Username+" "+Userpass);
        return Usermanagement.getInstance().loginUser(SID,Username,Userpass);
    } 
    
    /**
     * clear this session id
     * @param SID
     * @return string value if completed
     */
    public String logoutUser(String SID){
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	return Usermanagement.getInstance().logout(SID,User_ID);
    }
    
    /**
     * get a list of all states, needs no authentification to load
     * @return List of State-Objects or null
     */
    public List getStates(){
    	return Statemanagement.getInstance().getStates();
    }

    /**
     * Load if the users can register itself by using the form without logging in, 
     * needs no authentification to load
     * @param SID
     * @return
     */
    public Configuration allowFrontendRegister(String SID){
    	return Configurationmanagement.getInstance().getConfKey(3, "allow_frontend_register");
    }
    
    /**
     * Add a user register by an Object
     * see [registerUser] for the index of the Object
     * @param regObject
     * @return new users_id OR null if an exception, -1 if an error, -4 if mail already taken, -5 if username already taken, -3 if login or pass or mail is empty 
     */
    public Long registerUserByObject(Object regObjectObj){
    	try {
    		LinkedHashMap regObject = (LinkedHashMap) regObjectObj;
        	return Usermanagement.getInstance().registerUser(regObject.get("Username").toString(), regObject.get("Userpass").toString(), 
        			regObject.get("lastname").toString(), regObject.get("firstname").toString(), regObject.get("email").toString(), 
        			Integer.valueOf(regObject.get("age").toString()).intValue(), regObject.get("street").toString(), regObject.get("additionalname").toString(), 
        			regObject.get("fax").toString(), regObject.get("zip").toString(), 
        			Long.valueOf(regObject.get("states_id").toString()).longValue(), regObject.get("town").toString(), 
        			Long.valueOf(regObject.get("language_id").toString()).longValue());
    	} catch (Exception ex) {
    		log.error(ex);
    	}
    	return null;
    }
    
    /**
     * Register a new User
     * To allow the registering the config_key *allow_frontend_register* has to be the value 1
     * otherwise the user will get an error code
     * @deprecated use registerUserByObject instead
     * @param SID
     * @param Username
     * @param Userpass
     * @param lastname
     * @param firstname
     * @param email
     * @param age
     * @param street
     * @param additionalname
     * @param fax
     * @param zip
     * @param states_id
     * @param town
     * @param language_id
     * @return new users_id OR null if an exception, -1 if an error, -4 if mail already taken, -5 if username already taken, -3 if login or pass or mail is empty 
     */
	public Long registerUser(String SID, String Username, String Userpass, String lastname, 
				String firstname, String email, int age, String street, String additionalname, 
				String fax, String zip, long states_id, String town, long language_id){
    	return Usermanagement.getInstance().registerUser(Username, Userpass, lastname, firstname, email, 
    			age, street, additionalname, fax, zip, states_id, town, language_id);
	}
	
	/**
	 * add a new user, only administrators can do this
	 * TODO: moderators should be able to add new users too
	 * @param SID
	 * @param level_id
	 * @param availible
	 * @param status
	 * @param login
	 * @param Userpass
	 * @param lastname
	 * @param firstname
	 * @param email
	 * @param age
	 * @param street
	 * @param additionalname
	 * @param fax
	 * @param zip
	 * @param states_id
	 * @param town
	 * @param language_id
	 * @return new users_id OR null if an exception, -1 if an error, -4 if mail already taken, -5 if username already taken, -3 if login or pass or mail is empty 
	 */
	public Long addUserAdmin(String SID, long level_id, int availible, int status, 
			String login, String Userpass, String lastname, String firstname, 
			String email, int age, String street, String additionalname, String fax, 
			String zip, long states_id, String town, long language_id){
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);	
    	return Usermanagement.getInstance().registerUserInit(User_LEVEL,level_id, availible, status, 
				login, Userpass, lastname, firstname, email, age, street, additionalname, fax, 
				zip, states_id, town, language_id);
	}
	
	
	
    public String deleteUserIDSelf(String SID){
    	String ret = "Deleting User Self";
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);
    	if(User_LEVEL>=1){
    		ret = Usermanagement.getInstance().logout(SID,User_ID);
    		ret = Usermanagement.getInstance().deleteUserID(User_ID);
    	} else {
    		ret = "Nur als registrierter Benutzer kann man seinen Account lšschen";
    	}
    	return ret;
    }
    public String deleteUserAdmin(String SID, int user_idClient){
    	String ret = "Deleting User Self";
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
    public String updateUser(String SID, String login, String password, String lastname, String firstname, int age, String adresse, String Zip, String state, String town, int EMailID, String email, int rechnungsaddr, String raddresse, int lieferaddr, String laddresse,int availible, String telefon, String fax, String mobil){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        //User updates hisself, always allowed
        Long User_LEVEL = new Long(3);
        return Usermanagement.getInstance().updateUser(User_LEVEL,new Long(User_ID), new Long(0), login, password, lastname, firstname, age, adresse, Zip, state, town, rechnungsaddr, raddresse, lieferaddr,laddresse,availible,telefon,fax,mobil,EMailID,email);
    }
    public String updateUserAdmin(String SID, int user_idClient, int level_id, String login, String password, String lastname, String firstname, int age, String adresse, String Zip, String state, String town, int EMailID, String email, int rechnungsaddr, String raddresse, int lieferaddr, String laddresse,int availible, String telefon, String fax, String mobil){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);	
        return Usermanagement.getInstance().updateUser(User_LEVEL,new Long(user_idClient), new Long(level_id), login, password, lastname, firstname, age, adresse, Zip, state, town, rechnungsaddr, raddresse, lieferaddr,laddresse,availible,telefon,fax,mobil,EMailID,email);
    }    
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
    public Users[] searchUser(String SID,String searchstring){   	
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);    	
    	return Usermanagement.getInstance().searchUser(User_LEVEL,searchstring);
    }    
    public Users updateUserdata(String SID, int DATA_ID, String DATA_KEY, String DATA, String Comment){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);
        Users users = new Users();
        String ret = "Update Userdata Self";
        if(User_LEVEL>1){
            ret = Usermanagement.getInstance().updateUserdata(DATA_ID,User_ID,DATA_KEY,DATA,Comment);
            users = Usermanagement.getInstance().getUser(new Long(User_ID));
        } else {
            ret = "Nur als registrierter Benutzer kann man seinen Account löschen";
            users.setFirstname(ret);
        }
        return users;
    }
    
    /*
     * Shopsystem
     */
    public zahlungsarten[] getZahlungsarten(String SID){
    	return ResHandler.getZahlungsarten(SID);
    }
    public lieferarten[] getLieferarten(String SID){
    	return ResHandler.getLieferarten(SID);
    }     
	public products[] getProductsByCat(String SID){
		return ResHandler.getProductByCat(SID);
	}	
	public products[] searchProduct(String SID,String searchstring){
		return ResHandler.searchProduct(SID,searchstring);
	}    
	public products[] getProductsByCatID(String SID,String cat, int start){
		return ResHandler.getProductByCat(SID,start,cat);
	}
	public products[] getAllProductByCat(String SID,String cat){
		return ResHandler.getAllProductByCat(SID,cat);
	}
	public products getProductByID(String SID, int artnumber){
		return ResHandler.getProductByID(SID,artnumber);
	}	
	public Userwaren[] getUserwaren(String SID){
		return ResHandler.getUserwaren(SID);
	}
	public Userwaren getUserwarenByID(String SID,int WAREN_ID){
		return ResHandler.getUserwarenByID(SID,WAREN_ID);
	}	
	public String addWarenkorb(String SID, int ARTICLE_ID, int amount){
		return ResHandler.addWarenkorb(SID,ARTICLE_ID,amount);
	}
    public String updateWarenkorb(String SID, int WAREN_ID, int status, int ZAHLUNGS_ID, int LIEFER_ID, int amount, String  comment){
    	return ResHandler.updateWarenkorb(SID, WAREN_ID, status, ZAHLUNGS_ID, LIEFER_ID, amount, comment);
    }
    public String deleteWarenkorb(String SID, int WAREN_ID){
    	return ResHandler.deleteWarenkorb(SID,WAREN_ID);
    }
    
/*
 * Configuration Handlers
 */    
    public Configuration[] getAllConf(String SID){
        return ResHandler.getAllConf(SID);
    }
    public Configuration getConfKey(String SID,String CONF_KEY){
        return ResHandler.getConfKey(SID,CONF_KEY);
    }
    public String addConfByKey(String SID,String CONF_KEY,String CONF_VALUE,String comment){
        return ResHandler.addConfByKey(SID,CONF_KEY,CONF_VALUE,comment);
    }
    public String updateConfByUID(String SID,int UID,String CONF_KEY,String CONF_VALUE,String comment){
        return ResHandler.updateConfByUID(SID,UID,CONF_KEY,CONF_VALUE,comment);
    }    
    public String deleteConfByUID(String SID,int UID){ 
        return ResHandler.deleteConfByUID(SID,UID);
    }
    
/*
 * UserGroup Management Handlers
 */    
    public Usergroups[] getAllGroups(String SID){
        return ResHandler.getAllGroups(SID);
    }
    public List getAllUsers(String SID,int start, int max){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        long User_Level = Usermanagement.getInstance().getUserLevelByID(User_ID); 
        return Usermanagement.getInstance().getusersAdmin(User_Level,start,max);
    }    
    public Users_Usergroups getSingleGroup(String SID,int GROUP_ID){
        return ResHandler.getSingleGroup(SID, GROUP_ID);
    }      
    public Users_Usergroups getGroupUsers(String SID,int GROUP_ID){
        return ResHandler.getGroupUsers(SID,GROUP_ID);
    }
    public String addUserToGroup(String SID,int GROUP_ID,int USER_ID,String comment){
        return ResHandler.addUserToGroup(SID,GROUP_ID,USER_ID,comment);
    }
    public String updateUserGroup(String SID,int UID,int GROUP_ID,int USER_ID,String comment){
        return ResHandler.updateUserGroup(SID,UID,GROUP_ID,USER_ID,comment);
    }
    public String deleteUserGroupByID(String SID,int UID){
        return ResHandler.deleteUserGroupByID(SID,UID);
    }
    public String addGroup(String SID,String name,int freigabe,String description,String comment){
        return ResHandler.addGroup(SID,name,freigabe,description,comment);
    }
    public String updateGroup(String SID,int GROUP_ID,int freigabe, String name, String description, String comment){
        return ResHandler.updateGroup(SID,GROUP_ID,freigabe, name, description, comment);
    }
    public String deleteGroup(String SID,int GROUP_ID){
        return ResHandler.deleteGroup(SID,GROUP_ID);
    } 
    
    
}
