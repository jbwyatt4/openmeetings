package org.xmlcrm.app.remote;

import org.xmlcrm.app.hibernate.beans.termine.Terminegroups;
import org.xmlcrm.app.hibernate.beans.termine.Termine_Todo_User;
import org.xmlcrm.app.hibernate.beans.termine.Termine_User;
import org.xmlcrm.app.hibernate.beans.termine.Terminevisual;
import org.xmlcrm.app.hibernate.beans.termine.Terminevisualmonth;

public class TermineService {
	private ResHandler ResHandler;
	public TermineService(){
		ResHandler = new ResHandler();
	}	
    /**
     * Terminmanagement
     */
    public Terminevisual getUserTermineDay(String SID,int syear,int smonth,int sday){
    	return ResHandler.getUserTermineDay(SID,syear,smonth,sday);
    }
    public Terminevisualmonth getUserTermineMonth(String SID,int syear,int smonth){ 
        return ResHandler.getUserTermineMonth(SID,syear,smonth);
    } 
    public Terminevisualmonth getUserTermineWeek(String SID,int syear,int smonth,int sday){ 
        return ResHandler.getUserTermineWeek(SID,syear,smonth,sday);
    }     
    public Terminevisual getGroupTermineDay(String SID,int syear,int smonth,int sday){ 
    	return ResHandler.getGroupTermineDay(SID,syear,smonth,sday);
    }
    public Terminevisual getUserTerminePeriod(String SID,int syear,int smonth,int sday,int eyear,int emonth,int eday){
    	return ResHandler.getUserTerminePeriod(SID,syear,smonth,sday,eyear,emonth,eday);
    }
    public Terminevisual getGroupTerminePeriod(String SID,int syear,int smonth,int sday,int eyear,int emonth,int eday){ 
    	return ResHandler.getGroupTerminePeriod(SID,syear,smonth,sday,eyear,emonth,eday);
    }    
    public Terminevisual getUserTermineLatest(String SID){  	
    	return ResHandler.getUserTermineLatest(SID);
    }
    public Terminevisualmonth getUserTermineLatestWeek(String SID){      
        return ResHandler.getUserTermineLatestWeek(SID);
    }      
    public Terminevisualmonth getUserTermineLatestMonth(String SID){      
        return ResHandler.getUserTermineLatestMonth(SID);
    }    
    public Terminevisual getGroupTermineLatest(String SID){
    	return ResHandler.getGroupTermineLatest(SID);
    }     
    public Termine_User getUserTermineByID(String SID,int UID){	
    	return ResHandler.getUserTermineByID(SID,UID);
    }
    public Terminegroups getGroupTermineByID(String SID,int UID){ 
    	return ResHandler.getGroupTermineByID(SID,UID);
    }   
    //							1			2			3			4		5		6		7			8			9		10		11			12				13				14				15					16		17				18
    public String addTerminUser(String SID,int syear,int smonth,int sday,int shour,int smin,int eyear,int emonth,int eday,int ehour,int emin,int terminstatus,String Comment,String description,String pubcomment,int open,String place, String message){    
        return ResHandler.addTerminUser(SID,syear,smonth,sday,shour,smin,eyear,emonth,eday,ehour,emin,terminstatus,Comment,description,pubcomment,open,place,message);
    }
    //							1			2			3			4		5		6		7			8			9		10		11			12				13				14				15					16		17				18
    public String addTerminGroup(String SID,int syear,int smonth,int sday,int shour,int smin,int eyear,int emonth,int eday,int ehour,int emin,int terminstatus,String Comment,String description,String pubcomment,int open,String place, String message){   
        return ResHandler.addTerminGroup(SID,syear,smonth,sday,shour,smin,eyear,emonth,eday,ehour,emin,terminstatus,Comment,description,pubcomment,open,place,message);
    }
    //							1			2			3			4		5		6		7			8			9		10		11			12				13				14				15					16		17				18		  19
    public String updateTermin(String SID,int TERMIN_ID,int syear,int smonth,int sday,int shour,int smin,int eyear,int emonth,int eday,int ehour,int emin,int terminstatus,String Comment,String description,String pubcomment,int open,String place, String message){
    	return ResHandler.updateTermin(SID,TERMIN_ID,syear,smonth,sday,shour,smin,eyear,emonth,eday,ehour,emin,terminstatus,Comment,description, pubcomment,open,place,message);
    }
    public String deleteTermine(String SID,int TERMIN_ID){
    	return ResHandler.deleteTermine(SID,TERMIN_ID);
    } 
    public Termine_Todo_User[] getUserTodoList(String SID){
        return ResHandler.getUserTodoList(SID);
    }
    public Termine_Todo_User getUserTodoItem(String SID,int UID){
        return ResHandler.getUserTodoItem(SID,UID);
    }    
    public Termine_Todo_User saveTodoListItem(String SID,String description,String name,String Comment,int STATUS_ID,int priority,String teilnehmer){
        return ResHandler.saveTodoListItem(SID,description,name,Comment,STATUS_ID,priority,teilnehmer);
    }
    public Termine_Todo_User updateTodoListItem(String SID,int UID, String name,String Comment,int priority,String description,String teilnehmer,int STATUS_ID){
        return ResHandler.updateTodoListItem(SID,UID,name,Comment,priority,description,teilnehmer,STATUS_ID);
    }    
    public String deleteUserItem(String SID,int UID){
        return ResHandler.deleteUserItem(SID,UID);
    }  
    
}
