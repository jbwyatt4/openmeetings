package org.xmlcrm.app.data.termine;

import java.util.ArrayList;
import java.util.Iterator;

import org.xmlcrm.app.hibernate.beans.termine.Terminegroups;
import org.xmlcrm.app.hibernate.beans.termine.Termine_User;
import org.xmlcrm.app.hibernate.beans.termine.Terminemiddletvisual;
import org.xmlcrm.app.hibernate.beans.termine.Terminesinglevisual;
import org.xmlcrm.app.hibernate.beans.termine.Terminestruktur;
import org.xmlcrm.utils.math.Calender;
/**
Generates the tables for the Visual output of the Events.

@author Sebastian Wagner; 
*/
public class TerminVisualManagement {
    private Terminmanagement Terminmanagement;
    private Calender CalenderI;
    public TerminVisualManagement(Terminmanagement Terminmanagement) {
        super();
        // TODO Auto-generated constructor stub
        Terminmanagement = Terminmanagement;
        CalenderI = new Calender();
    }
    private boolean checkUserLevel(int USER_LEVEL){
        if (USER_LEVEL>1){
            return true;
        } else {
            return false;
        }
    }
    /**
     * This Function will generate a 2-dimensional-Array which contains all Events/Termins of an user
     * and orders these events (x) ideal/optimal so that you can output in a table and a user can have a quick
     * look at it to see what he has todo today
     * 
     * Example Table:
     * Events(x)
     * ---------------------------------
     * hours # 	x
     * 	   1 # 	x  x
     * 	   2 # 	x  x  x
     *     3 #     x  x
     *     4 #        x
     *     5 # 	x
     *     6 # 	x  x
     *     7 # 	x  x  x
     *     8 #     x
     *     ...
     * 
     * Because we don't know how many rows we will need we have use to ArrayList instead of manipulating the
     * Arrays directly and than reparse it in Array-Objects so that it can be serialised by Axis for sending
     * over the wire
     * 
     * @param ter_user
     * @return
     * 
     */
    public Terminestruktur[] generateVisualSystem(Termine_User[] ter_user){
        Terminestruktur[] terminestruktur = new Terminestruktur[1];
        try {
            ArrayList VisualList = new ArrayList();
            ArrayList TempList = new ArrayList();
            for (int i=0;i<ter_user.length;i++){
                if (i==0){
                	//The first Event will always be added to the first row
                    TempList.add(ter_user[i]);
                } else {
                	
                	/*
                	 * Check if the events-start-time is later than the end-time of the previous event
                	 * If so you can add this event in the same row than the previous because it is 
                	 * 'later'.
                	 */
                    System.out.println("VisualList1 "+TempList.size());
//                    //System.out.println()
//                    Termine_User tUserTemp = new Termine_User();
//                    if (TempList.size()>0){
//                        tUserTemp = (Termine_User) TempList.get(TempList.size()-1);
//                    }
//                    //termine_user termine_userTEMP = (termine_user) TempList2.get(TempList2.size());
//                    if(tUserTemp.getTermine().getEnd()<=ter_user[i].getTermine().getStart()){
//                        TempList.add(ter_user[i]);                        
//                    } else {
//                    	/*
//                    	 * This Events start time IS earlier then the end-time of the previous event
//                    	 * We need now to check if there is a previous row where we can add the event
//                    	 * if there is one than eadd the event in this row otherwise start a new one
//                    	 */
//                    	boolean substart = true;
//                    	for (Iterator it = VisualList.iterator(); it.hasNext();) {
//                    		ArrayList ListMy = (ArrayList) it.next();
//                    		Termine_User termine_user = (Termine_User)ListMy.get(ListMy.size()-1);
//                    		if (termine_user.getEnd()<=ter_user[i].getTermine().getStart()){
//                    			//We found a previous row so add it without making a new row
//                    			ListMy.add(ter_user[i]);
//                    			substart = false;
//                    		}
//                    	}
//                    	if (substart){
//	                        VisualList.add(TempList);
//	                        TempList = new ArrayList();
//	                        TempList.add(ter_user[i]);
//                    	}
//                    }
                }
            } 
//            //Add the last row anyway
//            VisualList.add(TempList);
//            
//            //Generate the Array-Objects from the ArrayList
//            terminestruktur = new Terminestruktur[VisualList.size()];
//            int k=0;
//            for (Iterator it = VisualList.iterator(); it.hasNext();) {
//                ArrayList ListMy = (ArrayList) it.next();
//                Termine_User[] termine_user = new Termine_User[ListMy.size()];
//                terminestruktur[k] = new Terminestruktur(); 
//                int l=0;
//                for (Iterator it2 = ListMy.iterator(); it2.hasNext();) {
//                    termine_user[l] = (Termine_User) it2.next();
//                    termine_user[l].getTermine().setVisualrowlength(VisualList.size());
//                    l++;
//                }
//                terminestruktur[k].setTermine_user(termine_user);
//                k++;
//            }
        } catch (Exception err){
            System.out.println("generateViualSystem *** Error: "+err);
        }
        return terminestruktur;
    }
    public Terminestruktur[] generateVisualSystemFast(Termine_User[] ter_user){
        Terminestruktur[] terminestruktur = new Terminestruktur[1];
        try {
            ArrayList VisualList = new ArrayList();
            ArrayList TempList = new ArrayList();
            for (int i=0;i<ter_user.length;i++){
                if (i==0){
                	//The first Event will always be added to the first row
                    TempList.add(ter_user[i]);
                } else {
                	
                	/*
                	 * Check if the events-start-time is later than the end-time of the previous event
                	 * If so you can add this event in the same row than the previous because it is 
                	 * 'later'.
                	 */
                    System.out.println("VisualList1 "+TempList.size());
                    //System.out.println()
//                    Termine_User tUserTemp = new Termine_User();
//                    if (TempList.size()>0){
//                        tUserTemp = (Termine_User) TempList.get(TempList.size()-1);
//                    }
//                    //termine_user termine_userTEMP = (termine_user) TempList2.get(TempList2.size());
//                    if(tUserTemp.getTermine().getEnd()<=ter_user[i].getTermine().getStart()){
//                        TempList.add(ter_user[i]);                        
//                    } else {
//                    	/*
//                    	 * This Events start time IS earlier then the end-time of the previous event
//                    	 * We need now to check if there is a previous row where we can add the event
//                    	 * if there is one than eadd the event in this row otherwise start a new one
//                    	 */
//                    	boolean substart = true;
//                    	for (Iterator it = VisualList.iterator(); it.hasNext();) {
//                    		ArrayList ListMy = (ArrayList) it.next();
//                    		Termine_User termine_user = (Termine_User)ListMy.get(ListMy.size()-1);
//                    		if (termine_user.getEnd()<=ter_user[i].getTermine().getStart()){
//                    			//We found a previous row so add it without making a new row
//                    			ListMy.add(ter_user[i]);
//                    			substart = false;
//                    		}
//                    	}
//                    	if (substart){
//	                        VisualList.add(TempList);
//	                        TempList = new ArrayList();
//	                        TempList.add(ter_user[i]);
//                    	}
//                    }
                }
            } 
            //Add the last row anyway
            VisualList.add(TempList);
            
            //Generate the Array-Objects from the ArrayList
            terminestruktur = new Terminestruktur[VisualList.size()];
            int k=0;
//            for (Iterator it = VisualList.iterator(); it.hasNext();) {
//                ArrayList ListMy = (ArrayList) it.next();
//                Terminesinglevisual[] terminesinglevisual = new Terminesinglevisual[ListMy.size()];
//                terminestruktur[k] = new Terminestruktur(); 
//                int l=0;
//                for (Iterator it2 = ListMy.iterator(); it2.hasNext();) {
//                    Termine_User  ter = (Termine_User) it2.next();
//                    terminesinglevisual[l] = new Terminesinglevisual();
//                    terminesinglevisual[l].setVisualrowlength(VisualList.size());
//                    String start = ter.getTermine().getStartmin()+","+ter.getTermine().getStarthour()+","+ter.getTermine().getStartday()+","+ter.getTermine().getStartmonth()+","+ter.getTermine().getStartyear();
//                    String end = ter.getTermine().getEndmin()+","+ter.getTermine().getEndhour()+","+ter.getTermine().getEndday()+","+ter.getTermine().getEndmonth()+","+ter.getTermine().getEndyear();
//                    terminesinglevisual[l].setStart(start);
//                    terminesinglevisual[l].setEnd(end);
//                    terminesinglevisual[l].setDuration(ter.getTermine().getDuration());
//                    terminesinglevisual[l].setPlace(ter.getTermine().getPlace());
//                    terminesinglevisual[l].setTERMIN_ID(ter.getTERMIN_ID());
//                    l++;
//                }
//                terminestruktur[k].setTerminesinglevisual(terminesinglevisual);
//                k++;
//            }
        } catch (Exception err){
            System.out.println("generateViualSystem *** Error: "+err);
        }
        return terminestruktur;
    }
    public Terminestruktur[] generateVisualSystemMiddlet(Termine_User[] ter_user){
        Terminestruktur[] terminestruktur = new Terminestruktur[1];
        try {
            ArrayList VisualList = new ArrayList();
            ArrayList TempList = new ArrayList();
            for (int i=0;i<ter_user.length;i++){
                if (i==0){
                    //The first Event will always be added to the first row
                    TempList.add(ter_user[i]);
                } else {
                    
                    /*
                     * Check if the events-start-time is later than the end-time of the previous event
                     * If so you can add this event in the same row than the previous because it is 
                     * 'later'.
                     */
                    System.out.println("VisualList1 "+TempList.size());
                    //System.out.println()
//                    Termine_User tUserTemp = new Termine_User();
//                    if (TempList.size()>0){
//                        tUserTemp = (Termine_User) TempList.get(TempList.size()-1);
//                    }
//                    //termine_user termine_userTEMP = (termine_user) TempList2.get(TempList2.size());
//                    if(tUserTemp.getTermine().getEnd()<=ter_user[i].getTermine().getStart()){
//                        TempList.add(ter_user[i]);                        
//                    } else {
//                        /*
//                         * This Events start time IS earlier then the end-time of the previous event
//                         * We need now to check if there is a previous row where we can add the event
//                         * if there is one than eadd the event in this row otherwise start a new one
//                         */
//                        boolean substart = true;
//                        for (Iterator it = VisualList.iterator(); it.hasNext();) {
//                            ArrayList ListMy = (ArrayList) it.next();
//                            Termine_User termine_user = (Termine_User)ListMy.get(ListMy.size()-1);
//                            if (termine_user.getEnd()<=ter_user[i].getTermine().getStart()){
//                                //We found a previous row so add it without making a new row
//                                ListMy.add(ter_user[i]);
//                                substart = false;
//                            }
//                        }
//                        if (substart){
//                            VisualList.add(TempList);
//                            TempList = new ArrayList();
//                            TempList.add(ter_user[i]);
//                        }
//                    }
                }
            } 
            //Add the last row anyway
            VisualList.add(TempList);
            
            //Generate the Array-Objects from the ArrayList
            terminestruktur = new Terminestruktur[VisualList.size()];
            int k=0;
            for (Iterator it = VisualList.iterator(); it.hasNext();) {
                ArrayList ListMy = (ArrayList) it.next();
                Terminemiddletvisual[] terminemiddletvisual = new Terminemiddletvisual[ListMy.size()];
                terminestruktur[k] = new Terminestruktur(); 
                int l=0;
//                for (Iterator it2 = ListMy.iterator(); it2.hasNext();) {
//                    Termine_User  ter = (Termine_User) it2.next();
//                    terminemiddletvisual[l] = new Terminemiddletvisual();
//                    terminemiddletvisual[l].setVisualrowlength(VisualList.size());
//                    String start = ter.getTermine().getStartmin()+","+ter.getTermine().getStarthour()+","+ter.getTermine().getStartday()+","+ter.getTermine().getStartmonth()+","+ter.getTermine().getStartyear();
//                    String end = ter.getTermine().getEndmin()+","+ter.getTermine().getEndhour()+","+ter.getTermine().getEndday()+","+ter.getTermine().getEndmonth()+","+ter.getTermine().getEndyear();
//                    terminemiddletvisual[l].setStart(start);
//                    terminemiddletvisual[l].setEnd(end);
//                    terminemiddletvisual[l].setDuration(ter.getTermine().getDuration());
//                    terminemiddletvisual[l].setPlace(ter.getTermine().getPlace());
//                    terminemiddletvisual[l].setTERMIN_ID(ter.getTERMIN_ID());
//                    terminemiddletvisual[l].setMessage(ter.getMessage());
//                    terminemiddletvisual[l].setOpen(ter.getTermine().getOpen());
//                    terminemiddletvisual[l].setDescription(ter.getTermine().getDescription());
//                    terminemiddletvisual[l].setTerminstatus(ter.getTermine().getTerminstatus());
//                    l++;
//                }
                terminestruktur[k].setTerminemiddletvisual(terminemiddletvisual);
                k++;
            }
        } catch (Exception err){
            System.out.println("generateViualSystem *** Error: "+err);
        }
        return terminestruktur;
    }    
    public Terminestruktur[] generateVisualSystemGroup(Terminegroups[] ter_groups){
        Terminestruktur[] terminestruktur = new Terminestruktur[1];
        try {
            ArrayList VisualList = new ArrayList();
            ArrayList TempList = new ArrayList();
            for (int i=0;i<ter_groups.length;i++){
                if (i==0){
                	//The first Event will always be added to the first row
                    TempList.add(ter_groups[i]);
                } else {
                	
                	/*
                	 * Check if the events-start-time is later than the end-time of the previous event
                	 * If so you can add this event in the same row than the previous because it is 
                	 * 'later'.
                	 */
//                    if(ter_groups[i-1].getTermine().getEnd()<=ter_groups[i].getTermine().getStart()){
//                        TempList.add(ter_groups[i]);                        
//                    } else {
//                    	/*
//                    	 * This Events start time IS earlier then the end-time of the previous event
//                    	 * We need now to check if there is a previous row where we can add the event
//                    	 * if there is one than eadd the event in this row otherwise start a new one
//                    	 */
//                    	boolean substart = true;
//                    	for (Iterator it = VisualList.iterator(); it.hasNext();) {
//                    		ArrayList ListMy = (ArrayList) it.next();
//                    		Terminegroups termine_groups = (Terminegroups)ListMy.get(ListMy.size()-1);
//                    		if (termine_groups.getEnd()<=ter_groups[i].getTermine().getStart()){
//                    			//We found a previous row so add it without making a new row
//                    			ListMy.add(ter_groups[i]);
//                    			substart = false;
//                    		}
//                    	}
//                    	if (substart){
//	                        VisualList.add(TempList);
//	                        TempList = new ArrayList();
//	                        TempList.add(ter_groups[i]);
//                    	}
//                    }
                }
            } 
            //Add the last row anyway
//            VisualList.add(TempList);
//            
//            //Generate the Array-Objects from the ArrayList
//            terminestruktur = new Terminestruktur[VisualList.size()];
//            int k=0;
//            for (Iterator it = VisualList.iterator(); it.hasNext();) {
//                ArrayList ListMy = (ArrayList) it.next();
//                Terminegroups[] termine_groups = new Terminegroups[ListMy.size()];
//                terminestruktur[k] = new Terminestruktur(); 
//                int l=0;
//                for (Iterator it2 = ListMy.iterator(); it2.hasNext();) {
//                	termine_groups[l] = (Terminegroups) it2.next();
//                	termine_groups[l].getTermine().setVisualrowlength(VisualList.size());
//                    l++;
//                }
//                terminestruktur[k].setTermine_groups(termine_groups);
//                k++;
//            }
        } catch (Exception err){
            System.out.println("generateViualSystem *** Error: "+err);
        }
        return terminestruktur;
    }    
}
