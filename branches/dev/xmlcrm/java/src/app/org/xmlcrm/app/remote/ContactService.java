package org.xmlcrm.app.remote;

import org.xmlcrm.app.data.contact.Contactmanagement;
import org.xmlcrm.app.hibernate.beans.contact.congroups;
import org.xmlcrm.app.hibernate.beans.contact.contactfreigabe;
import org.xmlcrm.app.hibernate.beans.contact.Contacts;
import org.xmlcrm.app.hibernate.beans.user.Users;

/**
 * 
 * @author swagner
 *
 */
public class ContactService {
	
	/**
	 * Contact-Handlers
	 
	    public Contacts[] getUsercontacts(String SID,int maxRes){
	    	return Contactmanagement.getInstance().getUsercontacts( SID,maxRes);
	    }
	    public Users[] getAllFreeUser(String SID,int maxRes){
	    	return ResHandler.getAllFreeUser(SID,maxRes);
	    }    
	    public Contacts getContactsByID(String SID,int CONTACT_ID){
	    	return ResHandler.getContactsByID( SID, CONTACT_ID);
	    }
	    public Users getFreeUserByID(String SID,int USER_ID){
	    	return ResHandler.getFreeUserByID(SID,USER_ID);
	    }    
	    public String updateContact(String SID,int CONTACT_ID,String firstname, String lastname, String adress,String zip,String town,String state,String comment,String telefon, String fax,String mobil,String title,int EMailID, String email, int FREIGABE_ID){
	    	return ResHandler.updateContact( SID,CONTACT_ID,firstname, lastname, adress,zip,town,state,comment,telefon, fax,mobil,title,EMailID, email, FREIGABE_ID);
	    }
	    public String addContact(String SID,String firstname, String lastname, String adress,String zip,String town,String state,String comment,String telefon, String fax,String mobil,String title, String email, int FREIGABE_ID){
	    	return ResHandler.addContact( SID,firstname, lastname, adress, zip, town, state, comment, telefon,  fax, mobil, title,  email, FREIGABE_ID);
	    } 
	    public String deleteContact(String SID,int CONTACT_ID){
	        return ResHandler.deleteContact( SID,CONTACT_ID);
	    }
	    public Contacts[] searchContactsByUser(String SID,String searchstring,String searchcriteria,int searchMax, int start){
	    	return ResHandler.searchContactsByUser(SID, searchstring,searchcriteria,searchMax, start);
	    }
	    public Contacts[] searchContactsByAdm(String SID,String searchstring,String searchcriteria,int searchMax, int start){
	    	return ResHandler.searchContactsByAdm(SID,searchstring,searchcriteria,searchMax, start);
	    }
	    
	    public congroups[] getUserContactGroups(String SID){
	    	return ResHandler.getUserContactGroups(SID);
	    }
	    public congroups getContactGroupsByID(String SID,int CONGROUP_ID){
	    	return ResHandler.getContactGroupsByID(SID,CONGROUP_ID);
	    }     
	    public String addUserContactGroup(String SID,String name,int freigabe,String description,String comment){
	    	return ResHandler.addUserContactGroup(SID,name,freigabe,description,comment);
	    }
	    public String deleteUserContactGroup(String SID,int CONGROUP_ID){
	    	return ResHandler.deleteUserContactGroup(SID,CONGROUP_ID);
	    }
	    public String updateCongroup(String SID,int CONGROUP_ID,String name,int freigabe,String description,String comment){
	    	return ResHandler.updateCongroup(SID,CONGROUP_ID,name,freigabe,description,comment);
	    } 
	    
	 
	    public congroups getUserContactGroup(String SID, int CONGROUP_ID){
	    	return ResHandler.getUserContactGroup( SID, CONGROUP_ID);
	    }
	    public congroups searchContactGroupsByID(String SID,int CONGROUP_ID,String searchstring,String searchcriteria,int searchMax, int start){
	    	return ResHandler.searchContactGroupsByID(SID,CONGROUP_ID,searchstring,searchcriteria,searchMax,start);
	    }    
	    public String addContactToGroup(String SID,int CONTACT_ID,int CONUSER_ID,int CONGROUP_ID,String comment){
	    	return ResHandler.addContactToGroup(SID,CONTACT_ID,CONUSER_ID,CONGROUP_ID, comment);
	    }
	    public String updateContactGroup(String SID,int UID,int CONGROUP_ID, String comment){
	    	return ResHandler.updateContactGroup(SID,UID,CONGROUP_ID, comment);
	    }
	    public String deleteContactFromGroup(String SID,int CONTACT_ID,int CONUSER_ID,int CONGROUP_ID){
	    	return ResHandler.deleteContactFromGroup(SID,CONTACT_ID,CONUSER_ID,CONGROUP_ID);
	    } 
	    
	    public contactfreigabe[] getFreigabeDecr(String SID){
	        return ResHandler.getFreigabeDecr(SID);
	    }
	*/ 
}
