package org.xmlcrm.app.data.termine;

import java.util.Iterator;
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.xmlcrm.app.hibernate.beans.termine.*;
import org.xmlcrm.app.hibernate.utils.HibernateUtil;
import org.xmlcrm.app.outpuhandlers.pdf.pdfobjects.pdfobject;
import org.xmlcrm.app.remote.ResHandler;
import org.xmlcrm.utils.math.Calender;

import org.hibernate.type.*;
import org.hibernate.*;

public class Terminmanagement {
	private ResHandler ResHandler;
    private TerminVisualManagement TerminVisualManagement;
    //private TerminVisualManagement TerminVisualManagement;
	private Calender CalenderI;
	public Terminmanagement(ResHandler handler) {
		super();
		// TODO Auto-generated constructor stub
		ResHandler = handler;
		CalenderI = new Calender();
        TerminVisualManagement = new TerminVisualManagement(this);
	}
    
    public ResHandler getResHandler() {
        return ResHandler;
    }

    public void setResHandler(ResHandler resHandler) {
        ResHandler = resHandler;
    }

    public TerminVisualManagement getTerminVisualManagement() {
        return TerminVisualManagement;
    }

    public void setTerminVisualManagement(
            TerminVisualManagement terminVisualManagement) {
        TerminVisualManagement = terminVisualManagement;
    }
    
    public TerminTodolistManagement getTerminTodolistManagement(){
        TerminTodolistManagement TerminTodolistManagement = new TerminTodolistManagement(this);
        return TerminTodolistManagement;
    }
    
    private boolean checkUserLevel(long USER_LEVEL){
        if (USER_LEVEL>1){
            return true;
        } else {
            return false;
        }
    }  
    public Terminevisual getUserTermineLatest(long USER_LEVEL,int USER_ID){
        Terminevisual terminevisual = new Terminevisual();
        long start = CalenderI.ParseDatum(CalenderI.getYear(),CalenderI.getMonth(),CalenderI.getDay(),0,0);
        long end = CalenderI.ParseDatum(CalenderI.getYear(),CalenderI.getMonth(),CalenderI.getNextDay(),0,0);
        if (checkUserLevel(USER_LEVEL)){
            try {
//                Session session = HibernateUtil.currentSession();
//                Transaction tx = session.beginTransaction();
//                Query query = session.createQuery("select c from termine_user as c where c.USER_ID = :USER_ID AND ( c.start > :start AND c.start < :end ) OR ( c.start < :start AND c.end > :end ) OR ( c.end > :start AND c.end < :end ) ORDER BY c.start");
//                query.setInteger("USER_ID",USER_ID);
//                query.setLong("start",start);
//                query.setLong("end",end);
//
//                int count = query.list().size();
//                Termine_User[] termine_user = new Termine_User[count];
//                int k = 0;          
//                for (Iterator it2 = query.iterate(); it2.hasNext();) {
//                    termine_user[k] = (Termine_User) it2.next();
//                    System.out.println("TerminID: "+termine_user[k].getTERMIN_ID());
//                    k++;
//                }
//                tx.commit();
//                HibernateUtil.closeSession();
//                for (int vars=0;vars<termine_user.length;vars++){
//                    termine_user[vars].setStartDE(CalenderI.getDatumMili(termine_user[vars].getStart()));
//                    termine_user[vars].setEndDE(CalenderI.getDatumMili(termine_user[vars].getEnd()));
//                    termine_user[vars].setStarttimeDE(CalenderI.getDatumMili(termine_user[vars].getStarttime()));
//                    termine_user[vars].setUpdatetimeDE(CalenderI.getDatumMili(termine_user[vars].getUpdatetime()));                    
//                    termine_user[vars].setTermine_status(getTerminStatus(termine_user[vars].getTerminstatus()));                   
//                    termine_user[vars].setTermine(getTerminById(termine_user[vars].getTERMIN_ID(),1));
//                }
//                terminevisual.setTerminestruktur(TerminVisualManagement.generateVisualSystem(termine_user));
            } catch( HibernateException ex ) {
                terminevisual.setComment("Error: "+ex);
            } catch ( Exception ex2 ){
                terminevisual.setComment("Error: "+ex2);
            }	
        } else {
            terminevisual.setComment("Error: No Permission");
        }
    	return terminevisual;    	
    }  
    public Terminevisualmonth getUserTermineLatestWeek(long USER_LEVEL,int USER_ID){
        return getUserTermineWeek(USER_LEVEL,USER_ID,CalenderI.getYear(),CalenderI.getMonth(),CalenderI.getDay());
    }
    public Terminevisualmonth getUserTermineWeek(long USER_LEVEL,int USER_ID,int SelectYear,int SelectMonth,int SelectDay){
        Terminevisualmonth terminevisualmonth = new Terminevisualmonth();
//        Terminevisual[] terminevisual = new Terminevisual[7];
//        int dayinweek = CalenderI.getWeekday(CalenderI.ParseDatum(SelectYear,SelectMonth,SelectDay,0,0));
//        System.out.println("dayinweek: "+dayinweek);
//        int SeldaysOfMonth = CalenderI.getDaysOfMonth(SelectMonth);
//        System.out.println("SeldaysOfMonth: "+SeldaysOfMonth);
//        int firstday = SelectDay-(dayinweek-1);
//        System.out.println("firstday: "+firstday);
//        // init Array Index
//        int index = 0;
//        int tempMonth = SelectMonth;
//        int tempYear = SelectYear;
//        int tempday = firstday;
//        for (int i=index;i<7;i++){
//            tempMonth = SelectMonth;
//            tempYear = SelectYear; 
//            tempday = firstday;
//            tempday+=i;
//            System.out.println("Index: "+i+" - tempday: "+tempday);
//            //If day is not in this Month
//            if (tempday<1){
//                //System.out.println("*** little found tempday "+tempday);
//                tempMonth -= 1;
//                //System.out.println("*** little found tempMonth "+tempMonth);
//                if (tempMonth<1){
//                    tempYear -= 1;
//                    tempMonth=12;
//                } 
//                //System.out.println("*** little found tempMonth "+tempMonth+" tempYear"+tempYear);
//                int daysOfMonth = CalenderI.getDaysOfMonth(tempMonth);
//                //System.out.println("*** little found daysOfMonth "+daysOfMonth);
//                tempday = daysOfMonth+tempday;
//                //System.out.println("*** little found tempday "+tempday);
//            }
//            int daysOfMonth = CalenderI.getDaysOfMonth(tempMonth);
//            if (tempday>daysOfMonth){
//                tempMonth = SelectMonth+1;
//                tempday = SeldaysOfMonth-firstday;
//                if (tempMonth>12){
//                    tempYear = SelectYear+1;
//                    tempMonth=1;
//                }
//            }
//            //System.out.println("Index: "+i+" - tempday: "+tempday);
//            terminevisual[i] = getUserTermineDayMiddlet(USER_LEVEL,USER_ID,tempYear,tempMonth,tempday,1);
//            terminevisual[i].setVisualdata(terminevisual[i].getVisualdata()+";"+CalenderI.getWeekdayDELong(i));
//        }
//        terminevisualmonth.setTerminevisual(terminevisual);
        return terminevisualmonth;
    }
    public Terminevisualmonth getUserTermineLatestMonth(long USER_LEVEL,int USER_ID){
        return getUserTermineMonth(USER_LEVEL,USER_ID,CalenderI.getYear(),CalenderI.getMonth());
    }
    public Terminevisualmonth getUserTermineMonth(long USER_LEVEL,int USER_ID,int SelectYear,int SelectMonth){
        //One month with weekly rows maximum 6 rows of 7 days = 42 days
        Terminevisualmonth terminevisualmonth = new Terminevisualmonth();
//        Terminevisual[] terminevisual = new Terminevisual[42];
//        
//        int daysofmonth = CalenderI.getDaysOfMonth(SelectMonth);
//        //System.out.println("Days of month "+daysofmonth);
//        //System.out.println("First day of month "+1);
//        int dayinweek = CalenderI.getWeekday(CalenderI.ParseDatum(SelectYear,SelectMonth,1,0,0));
//        //System.out.println("Dayin week dayinweek "+dayinweek);
//        int restofoldmonth =  dayinweek-1;
//        //System.out.println("Dayin week restofoldmonth "+restofoldmonth);
//        //init Array Index
//        int index = 0;
//        
//        //calc amount and Get Objects of previous month
//        int oldmonat = SelectMonth-1;
//        int year = SelectYear;
//        if (oldmonat<1){
//            oldmonat =12;
//            year--;
//        }
//        int daysofmonthOld = CalenderI.getDaysOfMonth(oldmonat);
//        //System.out.println("Gettin previous Days of month "+daysofmonth);         
//        for (int v=restofoldmonth;v>0;v--){
//            int dd = (daysofmonthOld-v)+1;
//            //System.out.println("former YEAR|MONAT|TAG -- "+year+"|"+oldmonat+"|"+dd);
//            terminevisual[index] = getUserTermineDayFast(USER_LEVEL,USER_ID,year,oldmonat,dd,3);
//            index++;
//        }
//        //Clear index of month and year, Get Objects of current month
//        //System.out.println("Gettin Next day starting day of month");
//        int thismonat = SelectMonth;
//        year = SelectYear;   
//        //System.out.println("daysofmonth: "+daysofmonth);
//        for (int v=0;v<daysofmonth;v++){
//            //System.out.println("current YEAR|MONAT|TAG -- "+year+"|"+thismonat+"|"+(v+1));
//            terminevisual[index] = getUserTermineDayFast(USER_LEVEL,USER_ID,year,thismonat,(v+1),3);
//            index++;
//        }
//        //Clear index of month and year, calc amount and Get Objects of next month
//        //System.out.println("index: "+index);
//        //System.out.println("Gettin Missing objects from next month ");
//        thismonat = SelectMonth+1;
//        year = SelectYear;        
//        if (thismonat>12){
//            thismonat=1;
//            year++;
//        }    
//        int left = 42 - index;
//        //System.out.println("left: "+left+"index: "+index);
//        for (int i=0;i<left;i++){
//            //System.out.println("nextmonth YEAR|MONAT|TAG -- "+year+"|"+thismonat+"|"+(i+1));
//            terminevisual[index] = getUserTermineDayFast(USER_LEVEL,USER_ID,year,thismonat,(i+1),3);
//            index++;
//        }
//        //System.out.println("index: "+index);
//        terminevisualmonth.setTerminevisual(terminevisual);
        return terminevisualmonth;
    }     
    public Terminevisual getUserTermineDay(long USER_LEVEL,int USER_ID,int syear,int smonth,int sday,int info){
        Terminevisual terminevisual = new Terminevisual();
        terminevisual.setVisualdata(sday+";"+smonth+";"+syear);
        long start = CalenderI.ParseDatum(syear,smonth,sday,0,0);
        long end = start+86400000;
        if (checkUserLevel(USER_LEVEL)){
//            try {
//                Session session = HibernateUtil.currentSession();
//                Transaction tx = session.beginTransaction();
//                Query query = session.createQuery("select c from termine_user as c where c.USER_ID = :USER_ID AND ( c.start > :start AND c.start < :end ) OR ( c.start < :start AND c.end > :end ) OR ( c.end > :start AND c.end < :end ) ORDER BY c.start");
//                query.setInteger("USER_ID",USER_ID);
//                query.setLong("start",start);
//                query.setLong("end",end);
//                int count = query.list().size();
//                Termine_User[] termine_user = new Termine_User[count];
//                int k = 0;                
//                for (Iterator it2 = query.iterate(); it2.hasNext();) {
//                    termine_user[k] = (Termine_User) it2.next();
//                    k++;
//                }
//                tx.commit();
//                HibernateUtil.closeSession();
//                for (int vars=0;vars<termine_user.length;vars++){
//                    termine_user[vars].setStartDE(CalenderI.getDatumMili(termine_user[vars].getStart()));
//                    termine_user[vars].setEndDE(CalenderI.getDatumMili(termine_user[vars].getEnd()));
//                    termine_user[vars].setStarttimeDE(CalenderI.getDatumMili(termine_user[vars].getStarttime()));
//                    termine_user[vars].setUpdatetimeDE(CalenderI.getDatumMili(termine_user[vars].getUpdatetime()));                    
//                    termine_user[vars].setTermine_status(getTerminStatus(termine_user[vars].getTerminstatus()));                   
//                    termine_user[vars].setTermine(getTerminById(termine_user[vars].getTERMIN_ID(),info));                   
//                }
//                terminevisual.setTerminestruktur(TerminVisualManagement.generateVisualSystem(termine_user));               
//            } catch( HibernateException ex ) {
//                terminevisual.setComment("Error: "+ex);
//            } catch ( Exception ex2 ){
//                terminevisual.setComment("Error: "+ex2);
//            }	
        } else {
            terminevisual.setComment("Error: No Permission");
        }
    	return terminevisual;    	
    }
    public Terminevisual getUserTermineDayFast(int USER_LEVEL,int USER_ID,int syear,int smonth,int sday,int info){
        Terminevisual terminevisual = new Terminevisual();
        terminevisual.setVisualdata(sday+";"+smonth+";"+syear);
        long start = CalenderI.ParseDatum(syear,smonth,sday,0,0);
        long end = start+86400000;
        if (checkUserLevel(USER_LEVEL)){
//            try {
//                Session session = HibernateUtil.currentSession();
//                Transaction tx = session.beginTransaction();
//                Query query = session.createQuery("select c from termine_user as c where c.USER_ID = :USER_ID AND ( c.start > :start AND c.start < :end ) OR ( c.start < :start AND c.end > :end ) OR ( c.end > :start AND c.end < :end ) ORDER BY c.start");
//                query.setInteger("USER_ID",USER_ID);
//                query.setLong("start",start);
//                query.setLong("end",end);
//                int count = query.list().size();
//                Termine_User[] termine_user = new Termine_User[count];
//                int k = 0;                
//                for (Iterator it2 = query.iterate(); it2.hasNext();) {
//                    termine_user[k] = (Termine_User) it2.next();
//                    k++;
//                }
//                tx.commit();
//                HibernateUtil.closeSession();
//                for (int vars=0;vars<termine_user.length;vars++){                 
//                    termine_user[vars].setTermine(getTerminById(termine_user[vars].getTERMIN_ID(),info));                   
//                }
//                terminevisual.setTerminestruktur(TerminVisualManagement.generateVisualSystemFast(termine_user));               
//            } catch( HibernateException ex ) {
//                terminevisual.setComment("Error: "+ex);
//            } catch ( Exception ex2 ){
//                terminevisual.setComment("Error: "+ex2);
//            }	
        } else {
            terminevisual.setComment("Error: No Permission");
        }
    	return terminevisual;    	
    } 
    public Terminevisual getUserTermineDayMiddlet(int USER_LEVEL,int USER_ID,int syear,int smonth,int sday,int info){
        Terminevisual terminevisual = new Terminevisual();
        terminevisual.setVisualdata(sday+";"+smonth+";"+syear);
        long start = CalenderI.ParseDatum(syear,smonth,sday,0,0);
        long end = start+86400000;
        if (checkUserLevel(USER_LEVEL)){
//            try {
//                Session session = HibernateUtil.currentSession();
//                Transaction tx = session.beginTransaction();
//                Query query = session.createQuery("select c from termine_user as c where c.USER_ID = :USER_ID AND ( c.start > :start AND c.start < :end ) OR ( c.start < :start AND c.end > :end ) OR ( c.end > :start AND c.end < :end ) ORDER BY c.start");
//                query.setInteger("USER_ID",USER_ID);
//                query.setLong("start",start);
//                query.setLong("end",end);
//                int count = query.list().size();
//                Termine_User[] termine_user = new Termine_User[count];
//                int k = 0;                
//                for (Iterator it2 = query.iterate(); it2.hasNext();) {
//                    termine_user[k] = (Termine_User) it2.next();
//                    k++;
//                }
//                tx.commit();
//                HibernateUtil.closeSession();
//                for (int vars=0;vars<termine_user.length;vars++){                 
//                    termine_user[vars].setTermine(getTerminById(termine_user[vars].getTERMIN_ID(),info));                   
//                }
//                terminevisual.setTerminestruktur(TerminVisualManagement.generateVisualSystemMiddlet(termine_user));               
//            } catch( HibernateException ex ) {
//                terminevisual.setComment("Error: "+ex);
//            } catch ( Exception ex2 ){
//                terminevisual.setComment("Error: "+ex2);
//            }   
        } else {
            terminevisual.setComment("Error: No Permission");
        }
        return terminevisual;       
    }    
    public Terminevisual getUserTerminePeriod(long USER_LEVEL,int USER_ID,int syear,int smonth,int sday,int eyear,int emonth,int eday){
        Terminevisual terminevisual = new Terminevisual();
        terminevisual.setVisualdata(sday+";"+smonth+";"+syear);
        long start = CalenderI.ParseDatum(syear,smonth,sday,0,0);
        long end = CalenderI.ParseDatum(eyear,emonth,eday,0,0);
        if (checkUserLevel(USER_LEVEL)){
//            try {
//                Session session = HibernateUtil.currentSession();
//                Transaction tx = session.beginTransaction();
//                Query query = session.createQuery("select c from termine_user as c where c.USER_ID = :USER_ID AND c.start > :start AND c.end < :end");
//                query.setInteger("USER_ID",USER_ID);
//                query.setLong("start",start);
//                query.setLong("end",end);
//                int count = query.list().size();
//                Termine_User[] termine_user = new Termine_User[count];
//                int k = 0;                  
//                for (Iterator it2 = query.iterate(); it2.hasNext();) {
//                    termine_user[k] = (Termine_User) it2.next();
//                    k++;
//                }
//                tx.commit();
//                HibernateUtil.closeSession();
//                for (int vars=0;vars<termine_user.length;vars++){
//                    termine_user[vars].setStartDE(CalenderI.getDatumMili(termine_user[vars].getStart()));
//                    termine_user[vars].setEndDE(CalenderI.getDatumMili(termine_user[vars].getEnd()));
//                    termine_user[vars].setStarttimeDE(CalenderI.getDatumMili(termine_user[vars].getStarttime()));
//                    termine_user[vars].setUpdatetimeDE(CalenderI.getDatumMili(termine_user[vars].getUpdatetime()));                    
//                    termine_user[vars].setTermine_status(getTerminStatus(termine_user[vars].getTerminstatus()));                   
//                    termine_user[vars].setTermine(getTerminById(termine_user[vars].getTERMIN_ID(),1));                  
//                }
//                terminevisual.setTerminestruktur(TerminVisualManagement.generateVisualSystem(termine_user));
//            } catch( HibernateException ex ) {
//                terminevisual.setComment("Error: "+ex);
//            } catch ( Exception ex2 ){
//                terminevisual.setComment("Error: "+ex2);
//            }	
        } else {
            terminevisual.setComment("Error: No Permission");
        }
    	return terminevisual;    	
    }    
    public Termine_User getUserTermineByUID(long USER_LEVEL,int USER_ID,int UID){
        Termine_User termine_user = new Termine_User();
        if (checkUserLevel(USER_LEVEL)){
//	        try {
//	            Session session = HibernateUtil.currentSession();
//	            Transaction tx = session.beginTransaction();
//	            Query query = session.createQuery("select c from termine_user as c where c.USER_ID = :USER_ID AND c.UID = :UID");
//	            query.setInteger("USER_ID",USER_ID);
//	            query.setInteger("UID",UID);        
//	            for (Iterator it2 = query.iterate(); it2.hasNext();) {
//	                termine_user = (Termine_User) it2.next();
//	            }
//	            tx.commit();
//	            HibernateUtil.closeSession();
//	            termine_user.setTermine(getTerminById(termine_user.getTERMIN_ID(),1));
//                termine_user.setStartDE(CalenderI.getDatumMili(termine_user.getStart()));
//                termine_user.setEndDE(CalenderI.getDatumMili(termine_user.getEnd()));
//                termine_user.setStarttimeDE(CalenderI.getDatumMili(termine_user.getStarttime()));
//                termine_user.setUpdatetimeDE(CalenderI.getDatumMili(termine_user.getUpdatetime()));                    
//	        } catch( HibernateException ex ) {
//	            termine_user.setComment("Error: "+ex);
//	        } catch ( Exception ex2 ){
//	            termine_user.setComment("Error: "+ex2);
//	        }
        } else {
        	termine_user.setComment("Error: No Permission");
        }	        
    	return termine_user;    	
    }    
    public Termine_User getUserTermineByTermin(int USER_ID,int TERMIN_ID){
        Termine_User termine_user = new Termine_User();
//        try {
//            Session session = HibernateUtil.currentSession();
//            Transaction tx = session.beginTransaction();
//            Query query = session.createQuery("select c from termine_user as c where c.USER_ID = :USER_ID AND c.TERMIN_ID = :TERMIN_ID");
//            query.setInteger("USER_ID",USER_ID);
//            query.setInteger("TERMIN_ID",TERMIN_ID);       
//            for (Iterator it2 = query.iterate(); it2.hasNext();) {
//                termine_user = (Termine_User) it2.next();
//            }
//            tx.commit();
//            HibernateUtil.closeSession();
//            termine_user.setStartDE(CalenderI.getDatumMili(termine_user.getStart()));
//            termine_user.setEndDE(CalenderI.getDatumMili(termine_user.getEnd()));
//            termine_user.setStarttimeDE(CalenderI.getDatumMili(termine_user.getStarttime()));
//            termine_user.setUpdatetimeDE(CalenderI.getDatumMili(termine_user.getUpdatetime()));              
//            termine_user.setTermine(getTerminById(termine_user.getTERMIN_ID(),1));
//        } catch( HibernateException ex ) {
//            termine_user.setComment("Error: "+ex);
//        } catch ( Exception ex2 ){
//            termine_user.setComment("Error: "+ex2);
//        }
    	return termine_user;    	
    }
    private Termine_User[] getUserTermineByTermin(int TERMIN_ID){
        Termine_User[] termine_user = new Termine_User[1];
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("select c from termine_user as c where c.TERMIN_ID = :TERMIN_ID");
            query.setInteger("TERMIN_ID",TERMIN_ID);       
            int count = query.list().size();
            termine_user = new Termine_User[count];
            int k = 0;          
            for (Iterator it2 = query.iterate(); it2.hasNext();) {
                termine_user[k] = (Termine_User) it2.next();
                k++;
            }
            tx.commit();
            HibernateUtil.closeSession();

        } catch( HibernateException ex ) {
            termine_user[0] = new Termine_User();
            termine_user[0].setComment("Error: "+ex);
        } catch ( Exception ex2 ){
            termine_user[0] = new Termine_User();
            termine_user[0].setComment("Error: "+ex2);
        }
        return termine_user;        
    }    
    public Terminevisual getGroupTermineLatest(long USER_LEVEL,Long GROUP_ID){
        Terminevisual terminevisual = new Terminevisual();
        long start = CalenderI.ParseDatum(CalenderI.getYear(),CalenderI.getMonth(),CalenderI.getDay(),0,0);
        long end = CalenderI.ParseDatum(CalenderI.getYear(),CalenderI.getMonth(),CalenderI.getNextDay(),0,0);  
        if (checkUserLevel(USER_LEVEL)){
//	        try {
//	            Session session = HibernateUtil.currentSession();
//	            Transaction tx = session.beginTransaction();
//	            Query query = session.createQuery("select c from termine_groups as c where c.GROUP_ID = :GROUP_ID AND c.start > :start AND c.end < :end");
//	            query.setInteger("GROUP_ID",GROUP_ID);
//	            query.setLong("start",start);
//	            query.setLong("end",end);
//	            int count = query.list().size();
//                Terminegroups[] termine_groups = new Terminegroups[count];
//	            int k = 0;          
//	            for (Iterator it2 = query.iterate(); it2.hasNext();) {
//	            	termine_groups[k] = (Terminegroups) it2.next();
//	                k++;
//	            }
//	            tx.commit();
//	            HibernateUtil.closeSession();
//	            for (int vars=0;vars<termine_groups.length;vars++){
//                    termine_groups[vars].setStartDE(CalenderI.getDatumMili(termine_groups[vars].getStart()));
//                    termine_groups[vars].setEndDE(CalenderI.getDatumMili(termine_groups[vars].getEnd()));
//                    termine_groups[vars].setStarttimeDE(CalenderI.getDatumMili(termine_groups[vars].getStarttime()));
//                    termine_groups[vars].setUpdatetimeDE(CalenderI.getDatumMili(termine_groups[vars].getUpdatetime()));                    
//	            	termine_groups[vars].setTermine(getTerminById(termine_groups[vars].getTERMIN_ID(),1));
//	            	termine_groups[vars].setTermine_status(getTerminStatus(termine_groups[vars].getTerminstatus())); 
//	            }
//                terminevisual.setTerminestruktur(TerminVisualManagement.generateVisualSystemGroup(termine_groups));
//	        } catch( HibernateException ex ) {
//                terminevisual.setComment("Error: "+ex);
//	        } catch ( Exception ex2 ){
//                terminevisual.setComment("Error: "+ex2);
//	        }
        } else {
            terminevisual.setComment("Error: No Permission");
        }
        return terminevisual;        
    }    
    public Terminevisual getGroupTermineDay(long USER_LEVEL,Long GROUP_ID,int syear,int smonth,int sday){
        Terminevisual terminevisual = new Terminevisual();
        long start = CalenderI.ParseDatum(syear,smonth,sday,0,0); 
        long end = start+86400000;
        if (checkUserLevel(USER_LEVEL)){
//	        try {
//	            Session session = HibernateUtil.currentSession();
//	            Transaction tx = session.beginTransaction();
//	            Query query = session.createQuery("select c from termine_groups as c where c.GROUP_ID = :GROUP_ID AND c.start > :start AND c.end < :end");
//	            query.setInteger("GROUP_ID",GROUP_ID);
//	            query.setLong("start",start);
//	            query.setLong("end",end);
//	            int count = query.list().size();
//                Terminegroups[] termine_groups = new Terminegroups[count];
//	            int k = 0;          
//	            for (Iterator it2 = query.iterate(); it2.hasNext();) {
//	            	termine_groups[k] = (Terminegroups) it2.next();
//	                k++;
//	            }
//	            tx.commit();
//	            HibernateUtil.closeSession();
//	            for (int vars=0;vars<termine_groups.length;vars++){
//                    termine_groups[vars].setStartDE(CalenderI.getDatumMili(termine_groups[vars].getStart()));
//                    termine_groups[vars].setEndDE(CalenderI.getDatumMili(termine_groups[vars].getEnd()));
//                    termine_groups[vars].setStarttimeDE(CalenderI.getDatumMili(termine_groups[vars].getStarttime()));
//                    termine_groups[vars].setUpdatetimeDE(CalenderI.getDatumMili(termine_groups[vars].getUpdatetime()));                      
//	            	termine_groups[vars].setTermine(getTerminById(termine_groups[vars].getTERMIN_ID(),1));
//	            	termine_groups[vars].setTermine_status(getTerminStatus(termine_groups[vars].getTerminstatus())); 
//	            }
//                terminevisual.setTerminestruktur(TerminVisualManagement.generateVisualSystemGroup(termine_groups));
//	        } catch( HibernateException ex ) {
//                terminevisual.setComment("Error: "+ex);
//	        } catch ( Exception ex2 ){
//                terminevisual.setComment("Error: "+ex2);
//	        }
        } else {
            terminevisual.setComment("Error: No Permission");
        }
        return terminevisual;        
    }
    public Terminevisual getGroupTerminePeriod(long USER_LEVEL,Long GROUP_ID,int syear,int smonth,int sday,int eyear,int emonth,int eday){
        Terminevisual terminevisual = new Terminevisual();
        long start = CalenderI.ParseDatum(syear,smonth,sday,0,0);
        long end = CalenderI.ParseDatum(eyear,emonth,eday,0,0);
        if (checkUserLevel(USER_LEVEL)){
//	        try {
//	            Session session = HibernateUtil.currentSession();
//	            Transaction tx = session.beginTransaction();
//	            Query query = session.createQuery("select c from termine_groups as c where c.GROUP_ID = :GROUP_ID AND c.start > :start AND c.end < :end");
//	            query.setInteger("GROUP_ID",GROUP_ID);
//	            query.setLong("start",start);
//	            query.setLong("end",end);
//	            int count = query.list().size();
//                Terminegroups[] termine_groups = new Terminegroups[count];
//	            int k = 0;          
//	            for (Iterator it2 = query.iterate(); it2.hasNext();) {
//	            	termine_groups[k] = (Terminegroups) it2.next();
//	                k++;
//	            }
//	            tx.commit();
//	            HibernateUtil.closeSession();
//	            for (int vars=0;vars<termine_groups.length;vars++){
//                    termine_groups[vars].setStartDE(CalenderI.getDatumMili(termine_groups[vars].getStart()));
//                    termine_groups[vars].setEndDE(CalenderI.getDatumMili(termine_groups[vars].getEnd()));
//                    termine_groups[vars].setStarttimeDE(CalenderI.getDatumMili(termine_groups[vars].getStarttime()));
//                    termine_groups[vars].setUpdatetimeDE(CalenderI.getDatumMili(termine_groups[vars].getUpdatetime()));                      
//	            	termine_groups[vars].setTermine(getTerminById(termine_groups[vars].getTERMIN_ID(),1));
//	            	termine_groups[vars].setTermine_status(getTerminStatus(termine_groups[vars].getTerminstatus())); 
//	            }
//                terminevisual.setTerminestruktur(TerminVisualManagement.generateVisualSystemGroup(termine_groups));
//	        } catch( HibernateException ex ) {
//                terminevisual.setComment("Error: "+ex);
//	        } catch ( Exception ex2 ){
//                terminevisual.setComment("Error: "+ex2);
//	        }
        } else {
            terminevisual.setComment("Error: No Permission");
        }
        return terminevisual;        
    }    
    public Terminegroups getGroupTermineByUID(long USER_LEVEL,Long GROUP_ID,int UID){
    	Terminegroups termine_groups = new Terminegroups();
    	if (checkUserLevel(USER_LEVEL)){
//	        try {
//	            Session session = HibernateUtil.currentSession();
//	            Transaction tx = session.beginTransaction();
//	            Query query = session.createQuery("select c from termine_groups as c where c.GROUP_ID = :GROUP_ID AND c.UID = :UID");
//	            query.setInteger("GROUP_ID",GROUP_ID);
//	            query.setInteger("UID",UID);       
//	            for (Iterator it2 = query.iterate(); it2.hasNext();) {
//	            	termine_groups = (Terminegroups) it2.next();
//	            }
//	            tx.commit();
//	            HibernateUtil.closeSession();
//                termine_groups.setStartDE(CalenderI.getDatumMili(termine_groups.getStart()));
//                termine_groups.setEndDE(CalenderI.getDatumMili(termine_groups.getEnd()));
//                termine_groups.setStarttimeDE(CalenderI.getDatumMili(termine_groups.getStarttime()));
//                termine_groups.setUpdatetimeDE(CalenderI.getDatumMili(termine_groups.getUpdatetime()));                
//	            termine_groups.setTermine(getTerminById(termine_groups.getTERMIN_ID(),1));
//	        } catch( HibernateException ex ) {
//	        	termine_groups.setComment("Error: "+ex);
//	        } catch ( Exception ex2 ){
//	        	termine_groups.setComment("Error: "+ex2);
//	        }
    	} else {
        	termine_groups.setComment("Error: No Permission");
        }	        
    	return termine_groups;    	
    } 
    public Terminegroups getGroupTermineByTermin(int GROUP_ID,int TERMIN_ID){
    	Terminegroups termine_groups = new Terminegroups();
//        try {
//            Session session = HibernateUtil.currentSession();
//            Transaction tx = session.beginTransaction();
//            Query query = session.createQuery("select c from termine_groups as c where c.GROUP_ID = :GROUP_ID AND c.TERMIN_ID = :TERMIN_ID");
//            query.setInteger("GROUP_ID",GROUP_ID);
//            query.setInteger("TERMIN_ID",TERMIN_ID);        
//            for (Iterator it2 = query.iterate(); it2.hasNext();) {
//            	termine_groups = (Terminegroups) it2.next();
//            }
//            tx.commit();
//            HibernateUtil.closeSession();
//            termine_groups.setTermine(getTerminById(termine_groups.getTERMIN_ID(),1));
//        } catch( HibernateException ex ) {
//        	termine_groups.setComment("Error: "+ex);
//        } catch ( Exception ex2 ){
//        	termine_groups.setComment("Error: "+ex2);
//        }
    	return termine_groups;    	
    }    
    private Terminegroups[] getGroupTermineByTermin(int TERMIN_ID){
        Terminegroups[] termine_groups = new Terminegroups[1];
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("select c from termine_groups as c where c.TERMIN_ID = :TERMIN_ID");
            query.setInteger("TERMIN_ID",TERMIN_ID);       
            int count = query.list().size();
            termine_groups = new Terminegroups[count];
            int k = 0;          
            for (Iterator it2 = query.iterate(); it2.hasNext();) {
                termine_groups[k] = (Terminegroups) it2.next();
                k++;
            }
            tx.commit();
            HibernateUtil.closeSession();

        } catch( HibernateException ex ) {
            termine_groups[0] = new Terminegroups();
            termine_groups[0].setComment("Error: "+ex);
        } catch ( Exception ex2 ){
            termine_groups[0] = new Terminegroups();
            termine_groups[0].setComment("Error: "+ex2);
        }
        return termine_groups;        
    }    
    private Termine getTerminById(int TERMIN_ID,int info){
        Termine termine = new Termine();
        try {
//            Session session = HibernateUtil.currentSession();
//            Transaction tx = session.beginTransaction();
//            Query query = session.createQuery("select c from termine as c where c.TERMIN_ID = :TERMIN_ID");
//            query.setInteger("TERMIN_ID",TERMIN_ID);     
//            for (Iterator it2 = query.iterate(); it2.hasNext();) {
//                termine = (Termine) it2.next();
//            }
//            tx.commit();
//            HibernateUtil.closeSession();
//            if (info==1){
//                //For Daily Visual
//                termine.setStartDE(CalenderI.getDatumMili(termine.getStart()));
//                termine.setEndDE(CalenderI.getDatumMili(termine.getEnd()));
//                termine.setStarttimeDE(CalenderI.getDatumMili(termine.getStarttime()));
//                termine.setUpdatetimeDE(CalenderI.getDatumMili(termine.getUpdatetime()));
//                termine.setTermine_status(getTerminStatus(termine.getTerminstatus())); 
//                //termine.setOwner_user(ResHandler.getUsermanagement().getUser(termine.getOwneruser()));
//                termine.setStarthour(CalenderI.parsehours(termine.getStart()));
//                termine.setStartmin(CalenderI.parseminutes(termine.getStart()));
//                
//                termine.setDurationhour(CalenderI.parsehoursduration(termine.getStart(),termine.getEnd()));
//                termine.setDurationminutes(CalenderI.parseminutesduration(termine.getStart(),termine.getEnd()));
//                termine.setDuration(CalenderI.parseduration(termine.getStart(),termine.getEnd()));
//                
//                termine.setStartUhrDE(CalenderI.getUhrzeitMili(termine.getStart()));
//                termine.setEndUhrDE(CalenderI.getUhrzeitMili(termine.getEnd()));
//                termine.setStartmonth(CalenderI.parsemonths(termine.getStart()));
//                termine.setStartyear(CalenderI.parseyears(termine.getStart()));
//                termine.setStartday(CalenderI.parsedays(termine.getStart()));
//                termine.setEndmonth(CalenderI.parsemonths(termine.getEnd()));
//                termine.setEndyear(CalenderI.parseyears(termine.getEnd()));
//                termine.setEndday(CalenderI.parsedays(termine.getEnd()));
//                termine.setEndhour(CalenderI.parsehours(termine.getEnd()));
//                termine.setEndmin(CalenderI.parseminutes(termine.getEnd()));  
//            } else if (info==2){
//                //Just plain database Types
//            } else if(info==3){
//                //For Month Visual
//                termine.setDuration(CalenderI.parseduration(termine.getStart(),termine.getEnd()));
//                termine.setStarthour(CalenderI.parsehours(termine.getStart()));
//                termine.setStartmin(CalenderI.parseminutes(termine.getStart()));         
//                termine.setStartday(CalenderI.parsedays(termine.getStart()));
//                termine.setEndday(CalenderI.parsedays(termine.getEnd()));
//                termine.setEndhour(CalenderI.parsehours(termine.getEnd()));
//                termine.setEndmin(CalenderI.parseminutes(termine.getEnd()));      
//                termine.setEndUhrDE(CalenderI.getUhrzeitMili(termine.getEnd()));
//                termine.setStartUhrDE(CalenderI.getUhrzeitMili(termine.getStart()));
//            }
        } catch( HibernateException ex ) {
            termine.setComment("Error: "+ex);
        } catch ( Exception ex2 ){
            termine.setComment("Error: "+ex2);
        }
        return termine;
    }
    private Terminestatus getTerminStatus(int STATUS_ID){
        Terminestatus termine_status = new Terminestatus();
//        try {
//            Session session = HibernateUtil.currentSession();
//            Transaction tx = session.beginTransaction();
//            Query query = session.createQuery("select c from termine_status as c where c.STATUS_ID = :STATUS_ID");
//            query.setInteger("STATUS_ID",STATUS_ID);     
//            for (Iterator it2 = query.iterate(); it2.hasNext();) {
//                termine_status = (Terminestatus) it2.next();
//            }
//            tx.commit();
//            HibernateUtil.closeSession();   
//            termine_status.setStarttimeDE(CalenderI.getDatumMili(termine_status.getStarttime()));
//            termine_status.setUpdatetimeDE(CalenderI.getDatumMili(termine_status.getUpdatetime()));            
//        } catch( HibernateException ex ) {
//            termine_status.setComment("Error: "+ex);
//        } catch ( Exception ex2 ){
//            termine_status.setComment("Error: "+ex2);
//        }
        return termine_status;
    }    
    
    public String addTerminUser(long USER_LEVEL,int USER_ID,int syear,int smonth,int sday,int shour,int smin,int eyear,int emonth,int eday,int ehour,int emin,int terminstatus,String Comment,String description,String pubcomment,int open,String place, String message){   
    	String ret = "";
    	long start = CalenderI.ParseDatum(syear,smonth,sday,shour,smin);
        long end = CalenderI.ParseDatum(eyear,emonth,eday,ehour,emin); 
        int TERMIN_ID = saveTermin(pubcomment,description, start, end, open, USER_ID, place, terminstatus);
        ret = saveTerminUser(USER_ID,TERMIN_ID,Comment,start,end,USER_ID,message,terminstatus);
        return ret;
    }
    public String addTerminGroup(long USER_LEVEL,int USER_ID,Long GROUP_ID,int syear,int smonth,int sday,int shour,int smin,int eyear,int emonth,int eday,int ehour,int emin,int terminstatus,String Comment,String description,String pubcomment,int open,String place, String message){   
    	String ret = "";
    	long start = CalenderI.ParseDatum(syear,smonth,sday,shour,smin);
        long end = CalenderI.ParseDatum(eyear,emonth,eday,ehour,emin); 
        int TERMIN_ID = saveTermin(pubcomment,description, start, end, open, USER_ID, place, terminstatus);
        ret = saveTerminGroup(GROUP_ID,TERMIN_ID,Comment,start,end,USER_ID,message,terminstatus);
        return ret;
    }   
    private String saveTerminUser(int USER_ID,int TERMIN_ID,String Comment,long start,long end,int inventor_id,String message,int terminstatus){
    	String ret = "";
//    	Termine_User termine_user = new Termine_User();
//    	termine_user.setComment(Comment);
//    	termine_user.setEnd(end);
//    	termine_user.setINVITOR_ID(inventor_id);
//    	termine_user.setMessage(message);
//    	termine_user.setStart(start);
//    	termine_user.setStarttime(CalenderI.getTimeStampMili());
//    	termine_user.setTerminstatus(terminstatus);
//    	termine_user.setUpdatetime(CalenderI.getTimeStampMili());
//    	termine_user.setUSER_ID(USER_ID);
//    	termine_user.setTERMIN_ID(TERMIN_ID);
//    	try {   
//            Session session = HibernateUtil.currentSession();
//            Transaction tx = session.beginTransaction();
//            session.save(termine_user);
//            session.flush();   
//            session.clear();
//            session.refresh(termine_user);
//            tx.commit();
//            HibernateUtil.closeSession();
//        } catch( HibernateException ex ) {
//        	ret = "Error: "+ex;
//        } catch ( Exception ex2 ){
//        	ret = "Error: "+ex2;
//        }            
    	return ret;
    }
    private String saveTerminGroup(Long GROUP_ID,int TERMIN_ID,String Comment,long start,long end,int inventor_id,String message,int terminstatus){
    	String ret = "";
//    	Terminegroups termine_groups = new Terminegroups();
//    	termine_groups.setComment(Comment);
//    	termine_groups.setEnd(end);
//    	termine_groups.setINVITOR_ID(inventor_id);
//    	termine_groups.setMessage(message);
//    	termine_groups.setStart(start);
//    	termine_groups.setStarttime(CalenderI.getTimeStampMili());
//    	termine_groups.setTerminstatus(terminstatus);
//    	termine_groups.setUpdatetime(CalenderI.getTimeStampMili());
//    	termine_groups.setGROUP_ID(GROUP_ID);
//    	termine_groups.setTERMIN_ID(TERMIN_ID);
//    	try {   
//            Session session = HibernateUtil.currentSession();
//            Transaction tx = session.beginTransaction();
//            session.save(termine_groups);
//            session.flush();   
//            session.clear();
//            session.refresh(termine_groups);
//            tx.commit();
//            HibernateUtil.closeSession();
//        } catch( HibernateException ex ) {
//        	ret = "Error: "+ex;
//        } catch ( Exception ex2 ){
//        	ret = "Error: "+ex2;
//        } 
    	return ret;
    }
    private int saveTermin(String Comment,String description, long start, long end, int open, int owner_id, String place, int terminestatus){
    	int id = 0;
//    	Termine termine = new Termine();
//    	termine.setComment(Comment);
//    	termine.setDescription(description);
//    	termine.setEnd(end);
//    	termine.setOpen(open);
//    	termine.setOwneruser(owner_id);
//    	termine.setPlace(place);
//    	termine.setStart(start);
//    	termine.setStarttime(CalenderI.getTimeStampMili());
//    	termine.setUpdatetime(CalenderI.getTimeStampMili());
//    	termine.setTerminstatus(terminestatus);
//    	try {   
//            Session session = HibernateUtil.currentSession();
//            Transaction tx = session.beginTransaction();
//            session.save(termine);
//            session.flush();   
//            session.clear();
//            session.refresh(termine);
//            tx.commit();
//            HibernateUtil.closeSession();
//            id = termine.getTERMIN_ID();
//        } catch( HibernateException ex ) {
//        	System.out.println("Error: "+ex);
//        } catch ( Exception ex2 ){
//        	System.out.println("Error: "+ex2);
//        } 
    	return id;
    }
    public String updateTermin(long USER_LEVEL,int USER_ID,Long GROUP_ID,int TERMIN_ID,int syear,int smonth,int sday,int shour,int smin,int eyear,int emonth,int eday,int ehour,int emin,int terminstatus,String Comment,String description,String pubcomment,int open,String place, String message){
    	String ret = "";
//    	if (checkUserLevel(USER_LEVEL)){
//	    	long start = CalenderI.ParseDatum(syear,smonth,sday,shour,smin);
//	        long end = CalenderI.ParseDatum(eyear,emonth,eday,ehour,emin); 
//            Termine termine = getTerminById(TERMIN_ID,2);
//            Termine_User termine_user = getUserTermineByTermin( USER_ID, TERMIN_ID);
//            Terminegroups termine_groups = getGroupTermineByTermin(USER_ID, TERMIN_ID);
//	        if (USER_ID == termine.getOwneruser()){
//	        	System.out.println("update global");
//	        	ret = updateGlobalTermin(TERMIN_ID,pubcomment,description, start, end, open, USER_ID, place, terminstatus,message,termine.getStarttime());
//	        	//System.out.println("update group");
//                ret += updateUserTermin (USER_ID,termine_user.getUID(),TERMIN_ID,Comment,start,end,USER_ID,message,terminstatus,termine_user.getStarttime());
//	            if (termine_groups.getUID()>0){
//                    System.out.println("Update Group UID: "+termine_groups.getUID());
//	                ret += updateGroupTermin (GROUP_ID,termine_groups.getUID(),TERMIN_ID,Comment,start,end,USER_ID,message,terminstatus,termine_groups.getStarttime());
//                }
//	        } else {
//	        	ret = updateUserTermin (USER_ID,termine_user.getUID(),TERMIN_ID,Comment,termine_user.getStart(),termine_user.getEnd(),termine_user.getINVITOR_ID(),message,terminstatus,termine_user.getStarttime());
//	        }
//    	} else {
//    		ret = "Permission denied";
//    	}	        
    	return ret;
    }
    
    private String updateGlobalTermin(int TERMIN_ID,String comment,String description, long start, long end, int open, int owner_id, String place, int terminstatus,String message,long starttime){
    	String ret = "";
//        try {
//
//            Session session = HibernateUtil.currentSession();
//            Transaction tx = session.beginTransaction();    
//
//            Termine termine = new Termine();
//            termine.setTERMIN_ID(TERMIN_ID);
//            termine.setComment(comment);
//            termine.setEnd(end);
//            termine.setOpen(open);
//            termine.setPlace(place);
//            termine.setStart(start);
//            termine.setTerminstatus(terminstatus);
//            termine.setUpdatetime(CalenderI.getTimeStampMili());
//            termine.setStarttime(starttime);
//            termine.setOwneruser(owner_id);
//            termine.setDescription(description);
//
//            session.update(termine);
//            tx.commit();
//            HibernateUtil.closeSession();
//
//            System.out.println(ret);
//            
//        } catch( HibernateException ex ) {
//        	ret = "error updateGlobalTermin: "+ex;
//        } catch ( Exception ex2 ){
//        	ret = "error updateGlobalTermin: "+ex2;
//        }
    	return ret;
    }
    public String updateUserTermin (int USER_ID,int UID,int TERMIN_ID,String comment,long start,long end,int invitor_id,String message,int terminstatus,long starttime){
    	String ret = "";
//        try {
//        	System.out.println("start update updateUserTermin");
//            Session session = HibernateUtil.currentSession();
//            Transaction tx = session.beginTransaction();        
//
//            Termine_User termine_user = new Termine_User();
//            termine_user.setComment(comment);
//            termine_user.setEnd(end);
//            termine_user.setINVITOR_ID(invitor_id);
//            termine_user.setMessage(message);
//            termine_user.setStart(start);
//            termine_user.setStarttime(starttime);
//            termine_user.setUpdatetime(CalenderI.getTimeStampMili());
//            termine_user.setTERMIN_ID(TERMIN_ID);
//            termine_user.setTerminstatus(terminstatus);
//            termine_user.setUID(UID);
//            termine_user.setUSER_ID(USER_ID);
//            
//            session.update(termine_user);
//            tx.commit();
//            HibernateUtil.closeSession();
//            
//            ret = "Update Succesful";
//            
//            System.out.println(ret);
//        } catch( HibernateException ex ) {
//        	ret = "error updateUserTermin: "+ex;
//        } catch ( Exception ex2 ){
//        	ret = "error updateUserTermin: "+ex2;
//        }
    	return ret;
    }
    public String updateGroupTermin (int GROUP_ID,int UID,int TERMIN_ID,String comment,long start,long end,int invitor_id,String message,int terminstatus,long starttime){
    	String ret = "";
//        try {
//            Session session = HibernateUtil.currentSession();
//            Transaction tx = session.beginTransaction();        
//
//            Terminegroups termine_groups = new Terminegroups();
//            termine_groups.setComment(comment);
//            termine_groups.setEnd(end);
//            termine_groups.setINVITOR_ID(invitor_id);
//            termine_groups.setMessage(message);
//            termine_groups.setStart(start);
//            termine_groups.setStarttime(starttime);
//            termine_groups.setUpdatetime(CalenderI.getTimeStampMili());
//            termine_groups.setTERMIN_ID(TERMIN_ID);
//            termine_groups.setTerminstatus(terminstatus);
//            termine_groups.setUID(UID);
//            termine_groups.setGROUP_ID(GROUP_ID);
//            
//            session.update(termine_groups);
//            tx.commit();
//            HibernateUtil.closeSession();
//            ret = "Update Succesful ";
//        } catch( HibernateException ex ) {
//        	ret = "error1: "+ex;
//        } catch ( Exception ex2 ){
//        	ret = "error2: "+ex2;
//        }
    	return ret;
    } 
    
    public String deleteTermine(long USER_LEVEL,int USER_ID,Long GROUP_ID,int TERMIN_ID){
    	String ret = "";
//    	if (checkUserLevel(USER_LEVEL)){
//            Termine termine = getTerminById(TERMIN_ID,2);
//            if (USER_ID == termine.getOwneruser()){
//	        	ret = deleteGlobalTermin(termine);
//                Termine_User[] termine_user = getUserTermineByTermin( TERMIN_ID);
//                Terminegroups[] termine_groups = getGroupTermineByTermin( TERMIN_ID);  
//                for (int i=0;i<termine_user.length;i++){
//                	ret = deleteUserTermin (termine_user[i]);
//                }
//                if (termine_groups.length>0){
//                    for (int i=0;i<termine_user.length;i++){
//                    	ret = deleteGroupTermin (termine_groups[i]);
//                    }
//                }
//	        } else {
//	        	Termine_User termine_user = getUserTermineByTermin( USER_ID, TERMIN_ID);
//            	ret = deleteUserTermin (termine_user);
//            	Terminegroups termine_groups = getGroupTermineByTermin(USER_ID, TERMIN_ID); 
//                ret = deleteGroupTermin (termine_groups);
//	        }
//    	} else {
//    		ret = "Permission denied";
//    	}	    	
    	return ret;
    }
    private String deleteGlobalTermin(Termine termine){
    	String ret = "";
//    	if (termine.getTERMIN_ID()>0 && termine.getTERMIN_ID()==getTerminById(termine.getTERMIN_ID(),2).getTERMIN_ID() ){
//	    	try {
//	            Session session = HibernateUtil.currentSession();
//		    	Transaction tx = session.beginTransaction();    	
//		    	session.delete(termine);
//	            tx.commit();
//	            session.flush();
//	            session.clear();
//		    	HibernateUtil.closeSession();
//		    	ret = "Success";
//	        } catch( HibernateException ex ) {
//	        	ret = "error1: "+ex;
//	        } catch ( Exception ex2 ){
//	        	ret = "error2: "+ex2;
//	        }
//    	} else {
//    		ret = "empty1";
//    	}
        return ret;
    }
    private String deleteUserTermin(Termine_User termine_user){
    	String ret = "";
//    	if (termine_user.getUID()>0 && termine_user.getUID()==getUserTermineByUID(3,termine_user.getUSER_ID(),termine_user.getUID()).getUID() ){
//	    	try {
//	            Session session = HibernateUtil.currentSession();
//		    	Transaction tx = session.beginTransaction();    	
//		    	session.delete(termine_user);
//		    	tx.commit();
//	            session.flush();
//	            session.clear();
//		    	HibernateUtil.closeSession();
//		    	ret = "Success";
//	        } catch( HibernateException ex ) {
//	        	ret = "error1: "+ex;
//	        } catch ( Exception ex2 ){
//	        	ret = "error2: "+ex2;
//	        }
//    	} else {
//    		ret = "empty2";
//    	}	        
        return ret;
    }
    private String deleteGroupTermin(Terminegroups termine_groups){
    	String ret = "";
//    	if (termine_groups.getUID()>0 && termine_groups.getUID()==getGroupTermineByUID(3,termine_groups.getGROUP_ID(),termine_groups.getUID()).getUID()){
//	    	try {
//	            Session session = HibernateUtil.currentSession();
//		    	Transaction tx = session.beginTransaction();    	
//	            session.delete(termine_groups);
//		    	tx.commit();
//		    	session.flush();
//	            session.clear();	    	
//		    	HibernateUtil.closeSession();
//		    	ret = "Success";
//	        } catch( HibernateException ex ) {
//	        	ret = "error1: "+ex;
//	        } catch ( Exception ex2 ){
//	        	ret = "error2: "+ex2;
//	        }
//    	} else {
//    		ret = "empty3";
//    	}
        return ret;
    } 
    
    
    
    
    
    public ArrayList PrintUserTermineByUID(int USER_LEVEL,int USER_ID,int UID){
        Termine_User termine_user = new Termine_User();
        ArrayList ListI = new ArrayList();
        if (checkUserLevel(USER_LEVEL)){
//            try {
//                Session session = HibernateUtil.currentSession();
//                Transaction tx = session.beginTransaction();
//                Query query = session.createQuery("select c from termine_user as c where c.USER_ID = :USER_ID AND c.UID = :UID");
//                query.setInteger("USER_ID",USER_ID);
//                query.setInteger("UID",UID);        
//                for (Iterator it2 = query.iterate(); it2.hasNext();) {
//                    termine_user = (Termine_User) it2.next();
//                }
//                tx.commit();
//                HibernateUtil.closeSession();
//                ArrayList ListInner = new ArrayList();
//                termine_user.setStartDE(CalenderI.getDatumMili(termine_user.getStart()));
//                termine_user.setEndDE(CalenderI.getDatumMili(termine_user.getEnd()));
//                termine_user.setStarttimeDE(CalenderI.getDatumMili(termine_user.getStarttime()));
//                termine_user.setUpdatetimeDE(CalenderI.getDatumMili(termine_user.getUpdatetime()));                    
//                termine_user.setTermine_status(getTerminStatus(termine_user.getTerminstatus()));                   
//                termine_user.setTermine(getTerminById(termine_user.getTERMIN_ID(),2)); 
//                ListInner.add(new pdfobject("Termin: "," ",false));
//                ListInner.add(new pdfobject("Start: ",termine_user.getStartDE(),false));
//                ListInner.add(new pdfobject("Ende: ",termine_user.getEndDE(),false));
//                ListInner.add(new pdfobject("Beschreibung: ",termine_user.getTermine().getDescription(),false));
//                ListI.add(ListInner);                
//            } catch( HibernateException ex ) {
//                termine_user.setComment("Error: "+ex);
//            } catch ( Exception ex2 ){
//                termine_user.setComment("Error: "+ex2);
//            }
        } else {
            termine_user.setComment("Error: No Permission");
        }           
        return ListI;        
    }    
    
    public ArrayList PrintUserTermineDay(int USER_LEVEL,int USER_ID,int syear,int smonth,int sday){
        Terminevisual terminevisual = new Terminevisual();
        terminevisual.setVisualdata(sday+";"+smonth+";"+syear);
        ArrayList ListI = new ArrayList();
        long start = CalenderI.ParseDatum(syear,smonth,sday,0,0);
        long end = start+86400000;
        if (checkUserLevel(USER_LEVEL)){
            try {
//                Session session = HibernateUtil.currentSession();
//                Transaction tx = session.beginTransaction();
//                Query query = session.createQuery("select c from termine_user as c where c.USER_ID = :USER_ID AND ( c.start > :start AND c.start < :end ) OR ( c.start < :start AND c.end > :end ) OR ( c.end > :start AND c.end < :end ) ORDER BY c.start");
//                query.setInteger("USER_ID",USER_ID);
//                query.setLong("start",start);
//                query.setLong("end",end);
//                int count = query.list().size();
//                Termine_User[] termine_user = new Termine_User[count];
//                int k = 0;                
//                for (Iterator it2 = query.iterate(); it2.hasNext();) {
//                    termine_user[k] = (Termine_User) it2.next();
//                    k++;
//                }
//                tx.commit();
//                HibernateUtil.closeSession();
//                for (int vars=0;vars<termine_user.length;vars++){
//                    ArrayList ListInner = new ArrayList();
//                    termine_user[vars].setStartDE(CalenderI.getDatumMili(termine_user[vars].getStart()));
//                    termine_user[vars].setEndDE(CalenderI.getDatumMili(termine_user[vars].getEnd()));
//                    termine_user[vars].setStarttimeDE(CalenderI.getDatumMili(termine_user[vars].getStarttime()));
//                    termine_user[vars].setUpdatetimeDE(CalenderI.getDatumMili(termine_user[vars].getUpdatetime()));                    
//                    termine_user[vars].setTermine_status(getTerminStatus(termine_user[vars].getTerminstatus()));                   
//                    termine_user[vars].setTermine(getTerminById(termine_user[vars].getTERMIN_ID(),2)); 
//                    ListInner.add(new pdfobject("Termin: "," ",false));
//                    ListInner.add(new pdfobject("Start: ",termine_user[vars].getStartDE(),false));
//                    ListInner.add(new pdfobject("Ende: ",termine_user[vars].getEndDE(),false));
//                    ListInner.add(new pdfobject("Beschreibung: ",termine_user[vars].getTermine().getDescription(),false));
//                    ListI.add(ListInner);
//                }
//            
            } catch( HibernateException ex ) {
                terminevisual.setComment("Error: "+ex);
            } catch ( Exception ex2 ){
                terminevisual.setComment("Error: "+ex2);
            }   
        } else {
            terminevisual.setComment("Error: No Permission");
        }
        return ListI;       
    } 
    public ArrayList PrintUserTermineDay(int USER_LEVEL,int USER_ID,int syear,int smonth,int sday,ArrayList ListI){
        Terminevisual terminevisual = new Terminevisual();
        terminevisual.setVisualdata(sday+";"+smonth+";"+syear);
        long start = CalenderI.ParseDatum(syear,smonth,sday,0,0);
        long end = start+86400000;
        if (checkUserLevel(USER_LEVEL)){
            try {
//                Session session = HibernateUtil.currentSession();
//                Transaction tx = session.beginTransaction();
//                Query query = session.createQuery("select c from termine_user as c where c.USER_ID = :USER_ID AND ( c.start > :start AND c.start < :end ) OR ( c.start < :start AND c.end > :end ) OR ( c.end > :start AND c.end < :end ) ORDER BY c.start");
//                query.setInteger("USER_ID",USER_ID);
//                query.setLong("start",start);
//                query.setLong("end",end);
//                int count = query.list().size();
//                Termine_User[] termine_user = new Termine_User[count];
//                int k = 0;                
//                for (Iterator it2 = query.iterate(); it2.hasNext();) {
//                    termine_user[k] = (Termine_User) it2.next();
//                    k++;
//                }
//                tx.commit();
//                HibernateUtil.closeSession();
//                for (int vars=0;vars<termine_user.length;vars++){
//                    ArrayList ListInner = new ArrayList();
//                    termine_user[vars].setStartDE(CalenderI.getDatumMili(termine_user[vars].getStart()));
//                    termine_user[vars].setEndDE(CalenderI.getDatumMili(termine_user[vars].getEnd()));
//                    termine_user[vars].setStarttimeDE(CalenderI.getDatumMili(termine_user[vars].getStarttime()));
//                    termine_user[vars].setUpdatetimeDE(CalenderI.getDatumMili(termine_user[vars].getUpdatetime()));                    
//                    termine_user[vars].setTermine_status(getTerminStatus(termine_user[vars].getTerminstatus()));                   
//                    termine_user[vars].setTermine(getTerminById(termine_user[vars].getTERMIN_ID(),2)); 
//                    ListInner.add(new pdfobject("Termin: "," ",false));
//                    ListInner.add(new pdfobject("Start: ",termine_user[vars].getStartDE(),false));
//                    ListInner.add(new pdfobject("Ende: ",termine_user[vars].getEndDE(),false));
//                    ListInner.add(new pdfobject("Beschreibung: ",termine_user[vars].getTermine().getDescription(),false));
//                    ListI.add(ListInner);
//                }
//            
            } catch( HibernateException ex ) {
                terminevisual.setComment("Error: "+ex);
            } catch ( Exception ex2 ){
                terminevisual.setComment("Error: "+ex2);
            }   
        } else {
            terminevisual.setComment("Error: No Permission");
        }
        return ListI;       
    } 
    public ArrayList PrintUserTermineWeek(int USER_LEVEL,int USER_ID,int SelectYear,int SelectMonth,int SelectDay){
        ArrayList ListI = new ArrayList();
        Terminevisualmonth terminevisualmonth = new Terminevisualmonth();
        Terminevisual[] terminevisual = new Terminevisual[7];
        int dayinweek = CalenderI.getWeekday(CalenderI.ParseDatum(SelectYear,SelectMonth,SelectDay,0,0));
        //System.out.println("dayinweek: "+dayinweek);
        int SeldaysOfMonth = CalenderI.getDaysOfMonth(SelectMonth);
        //System.out.println("SeldaysOfMonth: "+SeldaysOfMonth);
        int firstday = SelectDay-(dayinweek-1);
        System.out.println("firstday: "+firstday);
        // init Array Index
        int index = 0;
        int tempMonth = SelectMonth;
        int tempYear = SelectYear;
        int tempday = firstday;
        for (int i=index;i<7;i++){
            tempMonth = SelectMonth;
            tempYear = SelectYear; 
            tempday = firstday;
            tempday+=i;
            System.out.println("Index: "+i+" - tempday: "+tempday);
            //If day is not in this Month
            if (tempday<1){
                //System.out.println("*** little found tempday "+tempday);
                tempMonth -= 1;
                //System.out.println("*** little found tempMonth "+tempMonth);
                if (tempMonth<1){
                    tempYear -= 1;
                    tempMonth=12;
                } 
                //System.out.println("*** little found tempMonth "+tempMonth+" tempYear"+tempYear);
                int daysOfMonth = CalenderI.getDaysOfMonth(tempMonth);
                //System.out.println("*** little found daysOfMonth "+daysOfMonth);
                tempday = daysOfMonth+tempday;
                //System.out.println("*** little found tempday "+tempday);
            }
            int daysOfMonth = CalenderI.getDaysOfMonth(tempMonth);
            if (tempday>daysOfMonth){
                tempMonth = SelectMonth+1;
                tempday = SeldaysOfMonth-firstday;
                if (tempMonth>12){
                    tempYear = SelectYear+1;
                    tempMonth=1;
                }
            }
            //System.out.println("Index: "+i+" - tempday: "+tempday);
            ListI = PrintUserTermineDay(USER_LEVEL,USER_ID,tempYear,tempMonth,tempday,ListI);
        }
        terminevisualmonth.setTerminevisual(terminevisual);
        return ListI;
    }    
    public ArrayList PrintUserTermineMonth(int USER_LEVEL,int USER_ID,int SelectYear,int SelectMonth){
        ArrayList ListI = new ArrayList();
        //One month with weekly rows maximum 6 rows of 7 days = 42 days
        Terminevisualmonth terminevisualmonth = new Terminevisualmonth();
        Terminevisual[] terminevisual = new Terminevisual[42];
        
        int daysofmonth = CalenderI.getDaysOfMonth(SelectMonth);
        //System.out.println("Days of month "+daysofmonth);
        //System.out.println("First day of month "+1);
        int dayinweek = CalenderI.getWeekday(CalenderI.ParseDatum(SelectYear,SelectMonth,1,0,0));
        //System.out.println("Dayin week dayinweek "+dayinweek);
        int restofoldmonth =  dayinweek-1;
        //System.out.println("Dayin week restofoldmonth "+restofoldmonth);
        //init Array Index
        int index = 0;
        
        //calc amount and Get Objects of previous month
        int oldmonat = SelectMonth-1;
        int year = SelectYear;
        if (oldmonat<1){
            oldmonat =12;
            year--;
        }
        int daysofmonthOld = CalenderI.getDaysOfMonth(oldmonat);
        //System.out.println("Gettin previous Days of month "+daysofmonth);         
        for (int v=restofoldmonth;v>0;v--){
            int dd = (daysofmonthOld-v)+1;
            //System.out.println("former YEAR|MONAT|TAG -- "+year+"|"+oldmonat+"|"+dd);
            ListI = PrintUserTermineDay(USER_LEVEL,USER_ID,year,oldmonat,dd);
            index++;
        }
        //Clear index of month and year, Get Objects of current month
        //System.out.println("Gettin Next day starting day of month");
        int thismonat = SelectMonth;
        year = SelectYear;   
        //System.out.println("daysofmonth: "+daysofmonth);
        for (int v=0;v<daysofmonth;v++){
            //System.out.println("current YEAR|MONAT|TAG -- "+year+"|"+thismonat+"|"+(v+1));
            ListI = PrintUserTermineDay(USER_LEVEL,USER_ID,year,thismonat,(v+1),ListI);
            index++;
        }
        //Clear index of month and year, calc amount and Get Objects of next month
        //System.out.println("index: "+index);
        //System.out.println("Gettin Missing objects from next month ");
        thismonat = SelectMonth+1;
        year = SelectYear;        
        if (thismonat>12){
            thismonat=1;
            year++;
        }    
        int left = 42 - index;
        //System.out.println("left: "+left+"index: "+index);
        for (int i=0;i<left;i++){
            //System.out.println("nextmonth YEAR|MONAT|TAG -- "+year+"|"+thismonat+"|"+(i+1));
            ListI = PrintUserTermineDay(USER_LEVEL,USER_ID,year,thismonat,(i+1),ListI);
            index++;
        }
        //System.out.println("index: "+index);
        
        return ListI;
    }    
}
