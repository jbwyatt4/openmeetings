package org.xmlcrm.app.remote;

import java.util.ArrayList;

import org.xmlcrm.app.hibernate.beans.basic.Naviglobal;
import org.xmlcrm.app.hibernate.beans.basic.Configuration;
import org.xmlcrm.app.hibernate.beans.basic.Sessiondata;
import org.xmlcrm.app.hibernate.beans.contact.congroups;
import org.xmlcrm.app.hibernate.beans.contact.contactfreigabe;
import org.xmlcrm.app.hibernate.beans.contact.Contacts;
import org.xmlcrm.app.hibernate.beans.shop.lieferarten;
import org.xmlcrm.app.hibernate.beans.shop.products;
import org.xmlcrm.app.hibernate.beans.shop.zahlungsarten;
import org.xmlcrm.app.hibernate.beans.termine.Terminegroups;
import org.xmlcrm.app.hibernate.beans.termine.Termine_Todo_User;
import org.xmlcrm.app.hibernate.beans.termine.Termine_User;
import org.xmlcrm.app.hibernate.beans.termine.Terminevisual;
import org.xmlcrm.app.hibernate.beans.termine.Terminevisualmonth;
import org.xmlcrm.app.hibernate.beans.user.Users_Usergroups;
import org.xmlcrm.app.hibernate.beans.user.Usergroups;
import org.xmlcrm.app.hibernate.beans.user.Users;
import org.xmlcrm.app.hibernate.beans.user.Userwaren;
import org.xmlcrm.app.data.basic.Configurationmanagement;
import org.xmlcrm.app.data.basic.Navimanagement;
import org.xmlcrm.app.data.basic.Sessionmanagement;
import org.xmlcrm.app.data.contact.Contactmanagement;
import org.xmlcrm.app.data.contact.Freigabemanagement;
import org.xmlcrm.app.data.product.Bestellmanagement;
import org.xmlcrm.app.data.product.Liefermanagement;
import org.xmlcrm.app.data.product.Productmanagement;
import org.xmlcrm.app.data.product.Zahlungsmanagement;
import org.xmlcrm.app.data.termine.Terminmanagement;
import org.xmlcrm.app.data.user.Emailmanagement;
import org.xmlcrm.app.data.user.Groupmanagement;
import org.xmlcrm.app.data.user.Usermanagement;
import org.xmlcrm.app.outpuhandlers.pdf.handler.Documentmanagement;

public class ResHandler {
	private final int maxResults=10;
	private ResHandler ResHandler;
	private Productmanagement Productmanagement;
    private Bestellmanagement Bestellmanagement;
    private Zahlungsmanagement Zahlungsmanagement;
    private Liefermanagement Liefermanagement;  
    private Freigabemanagement Freigabemanagement;
    private Terminmanagement Terminmanagement;
    private Documentmanagement Documentmanagement;    
	public ResHandler(){
		ResHandler = this;
		Productmanagement = new Productmanagement(ResHandler);
        Bestellmanagement = new Bestellmanagement(ResHandler);
        Zahlungsmanagement = new Zahlungsmanagement(ResHandler);
        Liefermanagement = new Liefermanagement(ResHandler);
        Freigabemanagement = new Freigabemanagement(ResHandler);
        Terminmanagement = new Terminmanagement(ResHandler);
        Documentmanagement = new Documentmanagement(ResHandler);
	}

	
	/**
	 * Definiton of WebService Methods
	 */
  

	public products[] getProductByCat(String SID){
		int User_ID = Sessionmanagement.getInstance().checkSession(SID);
		return Productmanagement.getProductsByCat();
	}
	public products[] searchProduct(String SID,String searchstring){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);		
		return Productmanagement.searchProduct(searchstring);
	}
    public Sessiondata getsessiondata(){
        return Sessionmanagement.getInstance().startsession();
    }


    public products[] getProductByCat(String SID,int start,String cat){
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	return Productmanagement.getProductsByCat(start,maxResults,cat);
    }
    public products[] getAllProductByCat(String SID,String cat){
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	return Productmanagement.getAllProductsByCat(cat);
    }    
    public products getProductByID(String SID,int artnumber){
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	return Productmanagement.getProductsByID(artnumber);
    }    


    



    public Userwaren[] getUserwaren(String SID){
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);
    	return Bestellmanagement.getUserwaren(User_ID,User_LEVEL);
    }
    public Userwaren getUserwarenByID(String SID,int WAREN_ID){
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);
    	return Bestellmanagement.getUserwareByID(WAREN_ID,User_LEVEL);
    }    

    public String addWarenkorb(String SID, int ARTICLE_ID, int amount){
    	String ans = "Bestellvorgang";
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);
    	if (Bestellmanagement.checkUserLevel(User_LEVEL)){
    		ans = Bestellmanagement.addWarenkorb(User_ID,ARTICLE_ID, amount);
    	}
    	return ans;
    }
    public String updateWarenkorb(String SID, int WAREN_ID, int status, int ZAHLUNGS_ID, int LIEFER_ID, int amount, String  comment){
    	String ans = "Bestellvorgang";
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);
    	if (Bestellmanagement.checkUserLevel(User_LEVEL)){
    		ans = Bestellmanagement.updateWarenkorb(WAREN_ID, status, ZAHLUNGS_ID, LIEFER_ID, amount, comment);
    	}
    	return ans;
    }
    public String deleteWarenkorb(String SID, int WAREN_ID){
    	String ans = "Bestellvorgang";
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);
    	if (Bestellmanagement.checkUserLevel(User_LEVEL)){
    		ans = Bestellmanagement.deleteWarenkorbByID(WAREN_ID);
    	}
    	return ans;
    }    
    
    public zahlungsarten[] getZahlungsarten(String SID){
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);
    	return Zahlungsmanagement.getAllZahlungsarten(User_LEVEL);       	
    }  
    public lieferarten[] getLieferarten(String SID){
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);
    	return Liefermanagement.getAllLieferarten(User_LEVEL);    	
    }     
    
    /**
     * CRM Modul Contact Management
     */
    
    /* Contacts */
    public Contacts[] getUsercontacts(String SID,int maxRes){
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);
    	return Contactmanagement.getInstance().getContactsByUser(User_ID,User_LEVEL,maxRes);
    }
    public Users[] getAllFreeUser(String SID,int maxRes){
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);
    	return Usermanagement.getInstance().getAllFreeUser(User_LEVEL,maxRes,User_ID);
    }
    public Contacts getContactsByID(String SID,int CONTACT_ID){
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);
    	return Contactmanagement.getInstance().getContactsByID(CONTACT_ID,User_LEVEL);
    }
    public Users getFreeUserByID(String SID,int USER_ID){
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);    	
    	return Usermanagement.getInstance().getFreeUserByID(User_LEVEL,USER_ID,User_ID);
    } 
    public String updateContact(String SID,int CONTACT_ID,String firstname, String lastname, String adress,String zip,String town,String state,String comment,String telefon, String fax,String mobil,String title,int EMailID, String email, int FREIGABE_ID){
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);  
    	return Contactmanagement.getInstance().updateContact(User_LEVEL,User_ID,CONTACT_ID,firstname, lastname, adress,zip,town,state,comment,telefon, fax,mobil,title,EMailID, email, FREIGABE_ID);
    }
    public String addContact(String SID,String firstname, String lastname, String adress,String zip,String town,String state,String comment,String telefon, String fax,String mobil,String title, String email, int FREIGABE_ID){
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);   	
    	return Contactmanagement.getInstance().addContact(User_LEVEL,User_ID,firstname, lastname, adress, zip, town, state, comment, telefon, fax, mobil, title, email, FREIGABE_ID);
    }
    public String deleteContact(String SID,int CONTACT_ID){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);   
        return Contactmanagement.getInstance().deleteContact( User_LEVEL, CONTACT_ID, User_ID);
    }
    public Contacts[] searchContactsByUser(String SID,String searchstring,String searchcriteria,int searchMax, int start){
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);
        return Contactmanagement.getInstance().searchContactsByUser(User_ID,User_LEVEL,searchstring,searchcriteria,searchMax, start);
    }
    public Contacts[] searchContactsByAdm(String SID,String searchstring,String searchcriteria,int searchMax, int start){
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);
    	return Contactmanagement.getInstance().searchContact(User_LEVEL,searchstring,searchcriteria,searchMax, start);
    }
    
    public congroups[] getUserContactGroups(String SID){
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);
    	return Contactmanagement.getInstance().getContactGroups(User_ID,User_LEVEL);
    }
    
    public congroups getContactGroupsByID(String SID,int CONGROUP_ID){
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID); 
    	return Contactmanagement.getInstance().getContactGroupsByID(User_LEVEL,CONGROUP_ID,User_ID,0);
    }    
    public String addUserContactGroup(String SID,String name,int freigabe,String description,String comment){
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID); 
    	return Contactmanagement.getInstance().addContactGroup(User_LEVEL,User_ID,name,freigabe,description,comment);
    }
    public String deleteUserContactGroup(String SID,int CONGROUP_ID){
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	long User_LEVEL = Usermanagement.getInstance().getUserLevelByID(User_ID);     	
    	return Contactmanagement.getInstance().deleteContactgroup(CONGROUP_ID,User_LEVEL,User_ID);
    }
    public String updateCongroup(String SID,int CONGROUP_ID,String name,int freigabe,String description,String comment){
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	long User_Level = Usermanagement.getInstance().getUserLevelByID(User_ID);     	
    	return Contactmanagement.getInstance().updateConGroup(User_Level,User_ID,CONGROUP_ID,name,freigabe,description,comment);
    } 
    

    public congroups getUserContactGroup(String SID, int CONGROUP_ID){
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	long User_Level = Usermanagement.getInstance().getUserLevelByID(User_ID); 
    	System.out.println("getUserContactGroup USER_LEVEL: "+User_Level);
    	return Contactmanagement.getInstance().getContactGroupsByID(User_Level,CONGROUP_ID,User_ID,1);
    }
    public congroups searchContactGroupsByID(String SID,int CONGROUP_ID,String searchstring,String searchcriteria,int searchMax, int start){
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	long User_Level = Usermanagement.getInstance().getUserLevelByID(User_ID); 
    	return Contactmanagement.getInstance().searchContactGroupsByID(User_Level,CONGROUP_ID,User_ID,searchstring,searchcriteria,searchMax,start);
    }
    public String addContactToGroup(String SID,int CONTACT_ID,int CONUSER_ID,int CONGROUP_ID,String comment){
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	long User_Level = Usermanagement.getInstance().getUserLevelByID(User_ID); 
    	return Contactmanagement.getInstance().addContactToGroup(User_Level,CONTACT_ID,CONUSER_ID,CONGROUP_ID, comment);
    }
    public String updateContactGroup(String SID,int UID,int CONGROUP_ID, String comment){
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	long User_Level = Usermanagement.getInstance().getUserLevelByID(User_ID); 
    	return Contactmanagement.getInstance().updateContactGroup(User_Level,UID,CONGROUP_ID, comment);
    }
    public String deleteContactFromGroup(String SID,int CONTACT_ID,int CONUSER_ID,int CONGROUP_ID){
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);
    	long User_Level = Usermanagement.getInstance().getUserLevelByID(User_ID); 
    	return Contactmanagement.getInstance().deleteContactFromGroup(User_ID,User_Level,CONTACT_ID,CONUSER_ID, CONGROUP_ID);
    }

    public contactfreigabe[] getFreigabeDecr(String SID){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        long User_Level = Usermanagement.getInstance().getUserLevelByID(User_ID); 
        return Freigabemanagement.getFreigabeDecr(User_Level);
    }
    
    
    /**
     * Definition of Usergroupmanagement
     */
    public Usergroups[] getAllGroups(String SID){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        long User_Level = Usermanagement.getInstance().getUserLevelByID(User_ID); 
        return Groupmanagement.getInstance().getAllGroup(new Long(User_Level));
    }

    public Users_Usergroups getSingleGroup(String SID,int GROUP_ID){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        long User_Level = Usermanagement.getInstance().getUserLevelByID(User_ID); 
        return Groupmanagement.getInstance().getSingleGroup(new Long(User_Level),new Long(GROUP_ID));
    }    
    public Users_Usergroups getGroupUsers(String SID,int GROUP_ID){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        long User_Level = Usermanagement.getInstance().getUserLevelByID(User_ID); 
        return Groupmanagement.getInstance().getGroupUsers(new Long(User_Level),new Long(GROUP_ID));
    }
    public String addUserToGroup(String SID,int GROUP_ID,int USER_ID,String comment){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        long User_Level = Usermanagement.getInstance().getUserLevelByID(User_ID); 
        return Groupmanagement.getInstance().addUserToGroup(new Long(User_Level),new Long(GROUP_ID),new Long(USER_ID),comment);
    }
    public String updateUserGroup(String SID,int UID,int GROUP_ID,int USER_ID,String comment){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        long User_Level = Usermanagement.getInstance().getUserLevelByID(User_ID); 
        return Groupmanagement.getInstance().updateUserGroup(new Long(User_Level),new Long(UID),new Long(GROUP_ID),new Long(USER_ID),comment);
    }
    public String deleteUserGroupByID(String SID,int UID){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        long User_Level = Usermanagement.getInstance().getUserLevelByID(User_ID); 
        return Groupmanagement.getInstance().deleteUserGroupByID(new Long(User_Level),new Long(UID));
    }
    public String addGroup(String SID,String name,int freigabe,String description,String comment){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        long User_Level = Usermanagement.getInstance().getUserLevelByID(User_ID); 
        return Groupmanagement.getInstance().addGroup(new Long(User_Level),new Long(User_ID),new Long(freigabe),name,description,comment);
    }
    public String updateGroup(String SID,int GROUP_ID,int freigabe, String name, String description, String comment){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        long User_Level = Usermanagement.getInstance().getUserLevelByID(User_ID); 
        return Groupmanagement.getInstance().updateGroup(new Long(User_Level),new Long(User_ID),new Long(freigabe),new Long(GROUP_ID), name, description, comment);
    }
    public String deleteGroup(String SID,int GROUP_ID){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        long User_Level = Usermanagement.getInstance().getUserLevelByID(User_ID); 
        return Groupmanagement.getInstance().deleteGroup(new Long(User_Level),new Long(GROUP_ID));
    }
    	
    	
    /**
     * Terminmanagement
     */
    public Terminevisual getUserTermineDay(String SID,int syear,int smonth,int sday){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);   	
    	return Terminmanagement.getUserTermineDay(Usermanagement.getInstance().getUserLevelByID(User_ID),User_ID,syear,smonth,sday,1);
    }
    public Terminevisualmonth getUserTermineMonth(String SID,int syear,int smonth){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);      
        return Terminmanagement.getUserTermineMonth(Usermanagement.getInstance().getUserLevelByID(User_ID),User_ID,syear,smonth);
    } 
    public Terminevisualmonth getUserTermineWeek(String SID,int syear,int smonth,int sday){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);      
        return Terminmanagement.getUserTermineWeek(Usermanagement.getInstance().getUserLevelByID(User_ID),User_ID,syear,smonth,sday);
    }    
    public Terminevisual getGroupTermineDay(String SID,int syear,int smonth,int sday){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);  
    	return Terminmanagement.getGroupTermineDay(Usermanagement.getInstance().getUserLevelByID(User_ID),Groupmanagement.getInstance().getUserGroupsSingle(User_ID).getUsergroup_id(),syear,smonth,sday);
    }
    public Terminevisual getUserTerminePeriod(String SID,int syear,int smonth,int sday,int eyear,int emonth,int eday){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);   	
    	return Terminmanagement.getUserTerminePeriod(Usermanagement.getInstance().getUserLevelByID(User_ID),User_ID,syear,smonth,sday,eyear,emonth,eday);
    }
    public Terminevisual getGroupTerminePeriod(String SID,int syear,int smonth,int sday,int eyear,int emonth,int eday){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);  
    	return Terminmanagement.getGroupTerminePeriod(Usermanagement.getInstance().getUserLevelByID(User_ID),Groupmanagement.getInstance().getUserGroupsSingle(User_ID).getUsergroup_id(),syear,smonth,sday,eyear,emonth,eday);
    }    
    public Terminevisual getUserTermineLatest(String SID){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);   	
    	return Terminmanagement.getUserTermineLatest(Usermanagement.getInstance().getUserLevelByID(User_ID),User_ID);
    }
    public Terminevisualmonth getUserTermineLatestWeek(String SID){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);      
        return Terminmanagement.getUserTermineLatestWeek(Usermanagement.getInstance().getUserLevelByID(User_ID),User_ID);
    }    
    public Terminevisualmonth getUserTermineLatestMonth(String SID){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);      
        return Terminmanagement.getUserTermineLatestMonth(Usermanagement.getInstance().getUserLevelByID(User_ID),User_ID);
    }
    public Terminevisual getGroupTermineLatest(String SID){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);  
    	return Terminmanagement.getGroupTermineLatest(Usermanagement.getInstance().getUserLevelByID(User_ID),Groupmanagement.getInstance().getUserGroupsSingle(User_ID).getUsergroup_id());
    }    
    public Termine_User getUserTermineByID(String SID,int UID){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);  	
    	return Terminmanagement.getUserTermineByUID(Usermanagement.getInstance().getUserLevelByID(User_ID),User_ID,UID);
    }
    public Terminegroups getGroupTermineByID(String SID,int UID){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);  
    	return Terminmanagement.getGroupTermineByUID(Usermanagement.getInstance().getUserLevelByID(User_ID),Groupmanagement.getInstance().getUserGroupsSingle(User_ID).getUsergroup_id(),UID);
    }    
    public String addTerminUser(String SID,int syear,int smonth,int sday,int shour,int smin,int eyear,int emonth,int eday,int ehour,int emin,int terminstatus,String Comment,String description,String pubcomment,int open,String place, String message){   
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);  
        return Terminmanagement.addTerminUser(Usermanagement.getInstance().getUserLevelByID(User_ID),User_ID,syear,smonth,sday,shour,smin,eyear,emonth,eday,ehour,emin,terminstatus,Comment,description,pubcomment,open,place,message);
    }
    public String addTerminGroup(String SID,int syear,int smonth,int sday,int shour,int smin,int eyear,int emonth,int eday,int ehour,int emin,int terminstatus,String Comment,String description,String pubcomment,int open,String place, String message){   
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID);  
        return Terminmanagement.addTerminGroup(Usermanagement.getInstance().getUserLevelByID(User_ID),User_ID,Groupmanagement.getInstance().getUserGroupsSingle(User_ID).getUsergroup_id(),syear,smonth,sday,shour,smin,eyear,emonth,eday,ehour,emin,terminstatus,Comment,description,pubcomment,open,place,message);
    }
    public String updateTermin(String SID,int TERMIN_ID,int syear,int smonth,int sday,int shour,int smin,int eyear,int emonth,int eday,int ehour,int emin,int terminstatus,String Comment,String description,String pubcomment,int open,String place, String message){
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID); 
    	Long GROUP_ID = Groupmanagement.getInstance().getUserGroupsSingle(User_ID).getUsergroup_id();
    	System.out.println("GROUP_ID: "+GROUP_ID);
    	return Terminmanagement.updateTermin(Usermanagement.getInstance().getUserLevelByID(User_ID),User_ID,GROUP_ID,TERMIN_ID,syear,smonth,sday,shour,smin,eyear,emonth,eday,ehour,emin,terminstatus,Comment,description,pubcomment,open,place,message);
    }
    public String deleteTermine(String SID,int TERMIN_ID){
    	int User_ID = Sessionmanagement.getInstance().checkSession(SID); 
    	return Terminmanagement.deleteTermine(Usermanagement.getInstance().getUserLevelByID(User_ID),User_ID,Groupmanagement.getInstance().getUserGroupsSingle(User_ID).getUsergroup_id(),TERMIN_ID);
    }   
    //Termin TodoList Management
    public Termine_Todo_User[] getUserTodoList(String SID){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID); 
        return Terminmanagement.getTerminTodolistManagement().getUserTodoList(Usermanagement.getInstance().getUserLevelByID(User_ID),User_ID);
    }
    public Termine_Todo_User getUserTodoItem(String SID,int UID){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID); 
        return Terminmanagement.getTerminTodolistManagement().getUserTodoItem(Usermanagement.getInstance().getUserLevelByID(User_ID),User_ID,UID);
    }    
    public Termine_Todo_User saveTodoListItem(String SID,String description, String name,String Comment,int STATUS_ID,int priority,String teilnehmer){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID); 
        return Terminmanagement.getTerminTodolistManagement().saveTodoListItem(Usermanagement.getInstance().getUserLevelByID(User_ID),User_ID,description,name,Comment,STATUS_ID,priority,teilnehmer);
    }
    public Termine_Todo_User updateTodoListItem(String SID,int UID, String name, String Comment,int priority,String description,String teilnehmer,int STATUS_ID){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        return Terminmanagement.getTerminTodolistManagement().updateTodoListItem(Usermanagement.getInstance().getUserLevelByID(User_ID),User_ID,UID,name, Comment,priority,description,teilnehmer,STATUS_ID);
    }
    public String deleteUserItem(String SID,int UID){
        int User_ID = Sessionmanagement.getInstance().checkSession(SID);
        return Terminmanagement.getTerminTodolistManagement().deleteUserItem(Usermanagement.getInstance().getUserLevelByID(User_ID),User_ID,UID);
    }
    
    
    
    
    
    
    
    
    /**
     * PDF-Function
     * @param SID
     * @return
     */
    public ArrayList TodoListgeneratePDFDoc(int USER_ID){
        return Terminmanagement.getTerminTodolistManagement().generatePDFDoc(USER_ID);
    }
    
	/**
	 * Definition of Resources
	 */
	public Productmanagement getProductmanagement(){
		return Productmanagement;
	}	  
    public Bestellmanagement getBestellmanagement(){
        return Bestellmanagement;
    }  
    public Zahlungsmanagement getZahlungsmanagement(){
        return Zahlungsmanagement;
    }  
    public Liefermanagement getLiefermanagement(){
        return Liefermanagement;
    }  
 
    public Freigabemanagement getFreigabemanagement(){
        return Freigabemanagement;
    }     
    public Documentmanagement getDocumentmanagement() {
        return Documentmanagement;
    }
    public void setDocumentmanagement(Documentmanagement documentmanagement) {
        Documentmanagement = documentmanagement;
    }
    public Terminmanagement getTerminmanagement() {
        return Terminmanagement;
    }
    public void setTerminmanagement(Terminmanagement terminmanagement) {
        Terminmanagement = terminmanagement;
    }  
    
}
