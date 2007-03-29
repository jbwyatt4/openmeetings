package org.xmlcrm.app.data.termine;

import java.util.Iterator;
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Order;

import org.xmlcrm.app.hibernate.beans.shop.products;
import org.xmlcrm.app.hibernate.beans.termine.Termine_Todo_User;
import org.xmlcrm.app.hibernate.beans.termine.Termine_Todolist;
import org.xmlcrm.app.hibernate.utils.HibernateUtil;
import org.xmlcrm.app.outpuhandlers.pdf.pdfobjects.pdfobject;
import org.xmlcrm.utils.math.Calender;
import org.xmlcrm.utils.stringhandlers.flashencoder;

public class TerminTodolistManagement {
	private Calender CalenderI;

	private Terminmanagement TerminmanagementI;

	public TerminTodolistManagement(Terminmanagement Terminmanagement) {
		super();
		// TODO Auto-generated constructor stub
		TerminmanagementI = Terminmanagement;
		CalenderI = new Calender();
	}

	private boolean checkUserLevel(long USER_LEVEL) {
		if (USER_LEVEL > 1) {
			return true;
		} else {
			return false;
		}
	}

	public Termine_Todo_User[] getUserTodoList(long USER_LEVEL, int USER_ID) {
		Termine_Todo_User[] termine_todo_user = new Termine_Todo_User[1];
		if (checkUserLevel(USER_LEVEL)) {
			//            try {
			//                Object idf = HibernateUtil.createSession(); 			Session session = HibernateUtil.getSession();
			//                Transaction tx = session.beginTransaction();
			//                Criteria crit = session.createCriteria(Termine_Todo_User.class)
			//                	.addOrder( Order.asc("priority") )
			//            		.add( Restrictions.eq( "USER_ID", new Integer(USER_ID) ) );
			//                termine_todo_user = new Termine_Todo_User[crit.list().size()];
			//                int k = 0;          
			//                for (Iterator it2 = crit.list().iterator(); it2.hasNext();) {
			//                	termine_todo_user[k] = (Termine_Todo_User) it2.next();
			//                    k++;
			//                }
			//                tx.commit();
			//                HibernateUtil.closeSession(idf);
			//                for (int vars=0;vars<termine_todo_user.length;vars++){
			//                    termine_todo_user[vars].setStarttimeDE(CalenderI.getDatumMili(termine_todo_user[vars].getStarttime()));
			//                    termine_todo_user[vars].setUpdatetimeDE(CalenderI.getDatumMili(termine_todo_user[vars].getUpdatetime()));                    
			//                    termine_todo_user[vars].setTermine_todolist(getListItemByID(termine_todo_user[vars].getTODO_ID()));
			//                }
			//            } catch( HibernateException ex ) {
			//            	termine_todo_user[0] = new Termine_Todo_User();
			//            	termine_todo_user[0].setComment("Error: "+ex);
			//            } catch ( Exception ex2 ){
			//            	termine_todo_user[0] = new Termine_Todo_User();
			//            	termine_todo_user[0].setComment("Error: "+ex2);
			//            }	
		} else {
			termine_todo_user[0] = new Termine_Todo_User();
			termine_todo_user[0].setComment("Error: No Permission");
		}

		return termine_todo_user;
	}

	public Termine_Todo_User getUserTodoItem(long USER_LEVEL, int USER_ID,
			int UID) {
		Termine_Todo_User termine_todo_user = new Termine_Todo_User();
		if (checkUserLevel(USER_LEVEL)) {
			//            try {
			//                Object idf = HibernateUtil.createSession(); 			Session session = HibernateUtil.getSession();
			//                Transaction tx = session.beginTransaction();
			//                Criteria crit = session.createCriteria(Termine_Todo_User.class)
			//                    .add( Restrictions.eq( "UID", new Integer(UID) ) )
			//                    .add( Restrictions.eq( "USER_ID", new Integer(USER_ID) ) );          
			//                for (Iterator it2 = crit.list().iterator(); it2.hasNext();) {
			//                    termine_todo_user = (Termine_Todo_User) it2.next();
			//                }
			//                tx.commit();
			//                HibernateUtil.closeSession(idf);
			//                termine_todo_user.setStarttimeDE(CalenderI.getDatumMili(termine_todo_user.getStarttime()));
			//                termine_todo_user.setUpdatetimeDE(CalenderI.getDatumMili(termine_todo_user.getUpdatetime()));                    
			//                termine_todo_user.setTermine_todolist(getListItemByID(termine_todo_user.getTODO_ID()));
			//            } catch( HibernateException ex ) {
			//                termine_todo_user.setComment("Error: "+ex);
			//            } catch ( Exception ex2 ){
			//                termine_todo_user.setComment("Error: "+ex2);
			//            }   
		} else {
			termine_todo_user.setComment("Error: No Permission");
		}
		return termine_todo_user;
	}

	private Termine_Todo_User getUserListItemByID(int UID) {
		Termine_Todo_User termine_todo_user = new Termine_Todo_User();
		//        try {
		//            Object idf = HibernateUtil.createSession(); 			Session session = HibernateUtil.getSession();
		//            Transaction tx = session.beginTransaction();
		//            Criteria crit = session.createCriteria(Termine_Todo_User.class)
		//                .add( Restrictions.eq( "UID", new Integer(UID) ) );
		//            for (Iterator it2 = crit.list().iterator(); it2.hasNext();) {
		//                termine_todo_user = (Termine_Todo_User) it2.next();
		//            }
		//            tx.commit();
		//            HibernateUtil.closeSession(idf);                  
		//        } catch( HibernateException ex ) {
		//            termine_todo_user.setComment("Error: "+ex);
		//        } catch ( Exception ex2 ){
		//            termine_todo_user.setComment("Error: "+ex2);
		//        }   
		return termine_todo_user;
	}

	private Termine_Todolist getListItemByID(int TODO_ID) {
		Termine_Todolist termine_todolist = new Termine_Todolist();
		try {
			//            Object idf = HibernateUtil.createSession(); 			Session session = HibernateUtil.getSession();
			//            Transaction tx = session.beginTransaction();
			//            Criteria crit = session.createCriteria(Termine_Todolist.class)
			//            	.add( Restrictions.eq( "TODO_ID", new Integer(TODO_ID) ) );
			//            for (Iterator it2 = crit.list().iterator(); it2.hasNext();) {
			//            	termine_todolist = (Termine_Todolist) it2.next();
			//			}
			//            tx.commit();
			//            HibernateUtil.closeSession(idf);
			//            termine_todolist.setStarttimeDE(CalenderI.getDatumMili(termine_todolist.getStarttime()));
			//            termine_todolist.setUpdatetimeDE(CalenderI.getDatumMili(termine_todolist.getUpdatetime()));                    
		} catch (HibernateException ex) {
			termine_todolist.setComment("Error: " + ex);
		} catch (Exception ex2) {
			termine_todolist.setComment("Error: " + ex2);
		}
		return termine_todolist;
	}

	public Termine_Todo_User saveTodoListItem(long USER_LEVEL, int USER_ID,
			String description, String name, String Comment, int STATUS_ID,
			int priority, String teilnehmer) {
		Termine_Todo_User termine_todo_user = new Termine_Todo_User();
		//    	if (checkUserLevel(USER_LEVEL)){
		//    		Termine_Todolist termine_todolist = saveListItem(name,Comment,description,teilnehmer,STATUS_ID);
		//    		termine_todo_user = saveUserItem(Comment,USER_ID,termine_todolist.getTODO_ID(),USER_ID,priority);
		//    	} else {
		//    		termine_todo_user.setComment("Permission denied");
		//    	}
		return termine_todo_user;
	}

	private Termine_Todo_User saveUserItem(String Comment, int OWNER_ID,
			int TODO_ID, int USER_ID, int priority) {
		Termine_Todo_User termine_todo_user = new Termine_Todo_User();
		//		termine_todo_user.setComment(Comment);
		//		termine_todo_user.setOWNER_ID(OWNER_ID);
		//		termine_todo_user.setStarttime(CalenderI.getTimeStampMili());
		//		termine_todo_user.setTODO_ID(TODO_ID);
		//		termine_todo_user.setUpdatetime(CalenderI.getTimeStampMili());
		//		termine_todo_user.setUSER_ID(USER_ID);
		//		termine_todo_user.setPriority(priority);
		//    	try { 
		//            Object idf = HibernateUtil.createSession(); 			Session session = HibernateUtil.getSession();
		//            Transaction tx = session.beginTransaction();
		//            session.save(termine_todo_user);
		//            session.flush();   
		//            session.clear();
		//            session.refresh(termine_todo_user);
		//            tx.commit();
		//            HibernateUtil.closeSession(idf);
		//        } catch( HibernateException ex ) {
		//        	termine_todo_user.setComment("Error: "+ex);
		//        } catch ( Exception ex2 ){
		//        	termine_todo_user.setComment("Error: "+ex2);
		//        }  
		return termine_todo_user;
	}

	private Termine_Todolist saveListItem(String name, String Comment,
			String description, String teilnehmer, int STATUS_ID) {
		Termine_Todolist termine_todolist = new Termine_Todolist();
		//		termine_todolist.setComment(Comment);
		//        termine_todolist.setName(name);
		//		termine_todolist.setSTATUS_ID(STATUS_ID);
		//		termine_todolist.setDescription(description);
		//		termine_todolist.setStarttime(CalenderI.getTimeStampMili());
		//		termine_todolist.setUpdatetime(CalenderI.getTimeStampMili());
		//		termine_todolist.setTeilnehmer(teilnehmer);
		//    	try {   
		//            Object idf = HibernateUtil.createSession(); 			Session session = HibernateUtil.getSession();
		//            Transaction tx = session.beginTransaction();
		//            session.save(termine_todolist);
		//            session.flush();   
		//            session.clear();
		//            session.refresh(termine_todolist);
		//            tx.commit();
		//            HibernateUtil.closeSession(idf);
		//        } catch( HibernateException ex ) {
		//            termine_todolist.setComment("Error: "+ex);
		//        } catch ( Exception ex2 ){
		//            termine_todolist.setComment("Error: "+ex2);
		//        }  
		return termine_todolist;
	}

	public Termine_Todo_User updateTodoListItem(long USER_LEVEL, int USER_ID,
			int UID, String name, String Comment, int priority,
			String description, String teilnehmer, int STATUS_ID) {
		Termine_Todo_User termine_todo_user = new Termine_Todo_User();
		if (checkUserLevel(USER_LEVEL)) {
			termine_todo_user = getUserListItemByID(UID);
			System.out.println("UserListItemByID UID: " + UID);
			//            System.out.println("UserListItemByID UID2: "+termine_todo_user.getUID());
			//            if (termine_todo_user.getUID()>0){
			//                System.out.println("UserListItemByID OWNER_ID: "+termine_todo_user.getOWNER_ID());
			//                System.out.println("UserListItemByID USER_ID: "+USER_ID);
			//                if (termine_todo_user.getOWNER_ID()==USER_ID){
			//                    System.out.println("updateListItem description: "+description);
			//                    termine_todo_user = updateUserItem(USER_ID,UID,Comment,priority);
			//                    Termine_Todolist termine_todolist = updateListItem(termine_todo_user.getTODO_ID(),name,Comment,description,teilnehmer,STATUS_ID);
			//                    termine_todo_user.setTermine_todolist(termine_todolist);
			//                } else {
			//                    termine_todo_user = updateUserItem(USER_ID,UID,Comment,priority);
			//                    termine_todo_user.setTermine_todolist(getListItemByID(termine_todo_user.getTODO_ID()));
			//                }
			//            } else {
			//                termine_todo_user.setComment("UID given is not stored at database"); 
			//            }
		} else {
			termine_todo_user.setComment("Permission denied");
		}
		return termine_todo_user;
	}

	private Termine_Todo_User updateUserItem(int USER_ID, int UID,
			String Comment, int priority) {
		Termine_Todo_User termine_todo_user = getUserListItemByID(UID);
		//		termine_todo_user.setComment(Comment);
		//		termine_todo_user.setUpdatetime(CalenderI.getTimeStampMili());
		//		termine_todo_user.setUSER_ID(USER_ID);
		//		termine_todo_user.setPriority(priority);
		//        System.out.println("updateUserItem UID: "+UID);
		//    	try { 
		//            Object idf = HibernateUtil.createSession(); 			Session session = HibernateUtil.getSession();
		//            Transaction tx = session.beginTransaction();
		//            session.update(termine_todo_user);
		//            session.flush();   
		//            session.clear();
		//            session.refresh(termine_todo_user);
		//            tx.commit();
		//            HibernateUtil.closeSession(idf);
		//        } catch( HibernateException ex ) {
		//            termine_todo_user.setComment("Error: "+ex);
		//        } catch ( Exception ex2 ){
		//            termine_todo_user.setComment("Error: "+ex2);
		//        }  
		return termine_todo_user;
	}

	private Termine_Todolist updateListItem(int TODO_ID, String name,
			String Comment, String description, String teilnehmer, int STATUS_ID) {
		Termine_Todolist termine_todolist = getListItemByID(TODO_ID);
		termine_todolist.setComment(Comment);
		//        termine_todolist.setName(name);
		//		termine_todolist.setSTATUS_ID(STATUS_ID);
		//		termine_todolist.setDescription(description);
		//		termine_todolist.setUpdatetime(CalenderI.getTimeStampMili());
		//		termine_todolist.setTeilnehmer(teilnehmer);
		//        System.out.println("updateListItem TODO_ID: "+TODO_ID);
		//        System.out.println("updateListItem description: "+description);
		//    	try {   
		//            Object idf = HibernateUtil.createSession(); 			Session session = HibernateUtil.getSession();
		//            Transaction tx = session.beginTransaction();
		//            session.update(termine_todolist);
		//            session.flush();   
		//            session.clear();            
		//            session.refresh(termine_todolist);
		//            tx.commit();
		//            HibernateUtil.closeSession(idf);
		//        } catch( HibernateException ex ) {
		//        	termine_todolist.setComment("Error: "+ex);
		//        } catch ( Exception ex2 ){
		//        	termine_todolist.setComment("Error: "+ex2);
		//        }  
		return termine_todolist;
	}

	public String deleteUserItem(long USER_LEVEL, int USER_ID, int UID) {
		String ret = "";
		if (checkUserLevel(USER_LEVEL)) {
			//            Termine_Todo_User termine_todo_user = getUserListItemByID(UID);
			//            if (termine_todo_user.getUID()>0 && termine_todo_user.getUSER_ID()==USER_ID){
			//                if (termine_todo_user.getUSER_ID()==termine_todo_user.getOWNER_ID()){
			//                    ret = deleteListItem(termine_todo_user.getTODO_ID());
			//                }
			//                try {
			//                    Object idf = HibernateUtil.createSession(); 			Session session = HibernateUtil.getSession();
			//                    Transaction tx = session.beginTransaction();        
			//                    session.delete(termine_todo_user);
			//                    tx.commit();
			//                    session.flush();
			//                    session.clear();            
			//                    HibernateUtil.closeSession(idf);
			//                    ret = "Success";
			//                } catch( HibernateException ex ) {
			//                    ret = "error1: "+ex;
			//                } catch ( Exception ex2 ){
			//                    ret = "error2: "+ex2;
			//                }
			//            } else {
			//                ret = "Object does not exist or isn't yours";
			//            }
		} else {
			ret = "Permission denied";
		}
		return ret;
	}

	private String deleteListItem(int TODO_ID) {
		String ret = "";
		Termine_Todolist termine_todolist = getListItemByID(TODO_ID);
		//        if (termine_todolist.getTODO_ID()>0){
		//            try {
		//                Object idf = HibernateUtil.createSession(); 			Session session = HibernateUtil.getSession();
		//                Transaction tx = session.beginTransaction();        
		//                session.delete(termine_todolist);
		//                tx.commit();
		//                session.flush();
		//                session.clear();            
		//                HibernateUtil.closeSession(idf);
		//                ret = "Success";
		//            } catch( HibernateException ex ) {
		//                ret = "error1: "+ex;
		//            } catch ( Exception ex2 ){
		//                ret = "error2: "+ex2;
		//            }
		//        }
		return ret;
	}

	public ArrayList generateSinglePDFDoc(int USER_ID, int UID) {
		flashencoder flashencoder = new flashencoder();
		ArrayList ListI = new ArrayList();
		Termine_Todo_User termine_todo_user = new Termine_Todo_User();
		try {
			//            Object idf = HibernateUtil.createSession(); 			Session session = HibernateUtil.getSession();
			//            Transaction tx = session.beginTransaction();
			//            Criteria crit = session.createCriteria(Termine_Todo_User.class)
			//                .add( Restrictions.eq( "UID", new Integer(UID) ) )
			//                .add( Restrictions.eq( "USER_ID", new Integer(USER_ID) ) );          
			//            for (Iterator it2 = crit.list().iterator(); it2.hasNext();) {
			//                termine_todo_user = (Termine_Todo_User) it2.next();
			//            }
			//            tx.commit();
			//            HibernateUtil.closeSession(idf);
			//            termine_todo_user.setStarttimeDE(CalenderI.getDatumMili(termine_todo_user.getStarttime()));
			//            termine_todo_user.setUpdatetimeDE(CalenderI.getDatumMili(termine_todo_user.getUpdatetime()));                    
			//            termine_todo_user.setTermine_todolist(getListItemByID(termine_todo_user.getTODO_ID()));
			//            
			//            String start="";
			//            String end="";
			//            String description = "";
			//            String name="";
			//            String status="";
			//            ArrayList ListInner = new ArrayList();
			//            
			//            termine_todo_user.setStarttimeDE(CalenderI.getDatumMili(termine_todo_user.getStarttime()));
			//            termine_todo_user.setUpdatetimeDE(CalenderI.getDatumMili(termine_todo_user.getUpdatetime()));                    
			//            termine_todo_user.setTermine_todolist(getListItemByID(termine_todo_user.getTODO_ID()));
			//            name = termine_todo_user.getTermine_todolist().getName();
			//            ListInner.add(new pdfobject("Name",name,false));  
			//            start = termine_todo_user.getStarttimeDE();
			//            ListInner.add(new pdfobject("Angelegt am",start,false));
			//            end = termine_todo_user.getUpdatetimeDE(); 
			//            ListInner.add(new pdfobject("Letzte Veränderung",end,false));
			//            description = termine_todo_user.getTermine_todolist().getDescription();
			//            description = flashencoder.substr(description,"#31#","'");
			//            description = flashencoder.substr(description,"#32#","<");
			//            description = flashencoder.substr(description,"#33#",">");   
			//            ListInner.add(new pdfobject("Beschreibung",description,true));                    
			//            status = String.valueOf(termine_todo_user.getTermine_todolist().getSTATUS_ID()).toString();   
			//            ListInner.add(new pdfobject("status",status,false));                     
			//            ListI.add(ListInner);            

		} catch (HibernateException ex) {
			termine_todo_user.setComment("Error: " + ex);
		} catch (Exception ex2) {
			termine_todo_user.setComment("Error: " + ex2);
		}
		return ListI;
	}

	public ArrayList generatePDFDoc(int USER_ID) {
		flashencoder flashencoder = new flashencoder();
		ArrayList ListI = new ArrayList();
		Termine_Todo_User[] termine_todo_user = new Termine_Todo_User[1];
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Termine_Todo_User.class)
					.addOrder(Order.asc("priority")).add(
							Restrictions.eq("USER_ID", new Integer(USER_ID)));
			termine_todo_user = new Termine_Todo_User[crit.list().size()];
			int k = 0;
			for (Iterator it2 = crit.list().iterator(); it2.hasNext();) {
				termine_todo_user[k] = (Termine_Todo_User) it2.next();

				k++;
			}
			tx.commit();
			HibernateUtil.closeSession(idf);
			String start = "";
			String end = "";
			String description = "";
			String name = "";
			String status = "";
			//            for (int vars=0;vars<termine_todo_user.length;vars++){
			//                ArrayList ListInner = new ArrayList();
			//                termine_todo_user[vars].setStarttimeDE(CalenderI.getDatumMili(termine_todo_user[vars].getStarttime()));
			//                termine_todo_user[vars].setUpdatetimeDE(CalenderI.getDatumMili(termine_todo_user[vars].getUpdatetime()));                    
			//                termine_todo_user[vars].setTermine_todolist(getListItemByID(termine_todo_user[vars].getTODO_ID()));
			//                name = termine_todo_user[vars].getTermine_todolist().getName();
			//                ListInner.add(new pdfobject("Name",name,false));  
			//                start = termine_todo_user[vars].getStarttimeDE();
			//                ListInner.add(new pdfobject("Angelegt am",start,false));
			//                end = termine_todo_user[vars].getUpdatetimeDE(); 
			//                ListInner.add(new pdfobject("Letzte Veränderung",end,false));
			//                description = termine_todo_user[vars].getTermine_todolist().getDescription();
			//                description = flashencoder.substr(description,"#31#","'");
			//                description = flashencoder.substr(description,"#32#","<");
			//                description = flashencoder.substr(description,"#33#",">");   
			//                ListInner.add(new pdfobject("Beschreibung",description,true));                    
			//                status = String.valueOf(termine_todo_user[vars].getTermine_todolist().getSTATUS_ID()).toString();   
			//                ListInner.add(new pdfobject("status",status,false));                     
			//                ListI.add(ListInner);
			//            }
		} catch (HibernateException ex) {
			termine_todo_user[0] = new Termine_Todo_User();
			termine_todo_user[0].setComment("Error: " + ex);
		} catch (Exception ex2) {
			termine_todo_user[0] = new Termine_Todo_User();
			termine_todo_user[0].setComment("Error: " + ex2);
		}
		return ListI;
	}

}
