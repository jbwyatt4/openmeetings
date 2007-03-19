package org.xmlcrm.app.data.user;

import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.xmlcrm.app.hibernate.beans.adresses.Emails;
import org.xmlcrm.app.hibernate.utils.HibernateUtil;
import org.xmlcrm.app.remote.ResHandler;
import org.xmlcrm.utils.mail.MailHandler;
import org.xmlcrm.utils.math.Calender;

public class Emailmanagement {

	private static final Log log = LogFactory.getLog(Emailmanagement.class);
	
	public Emailmanagement(){}
    private static Emailmanagement instance = null;
    public static synchronized Emailmanagement getInstance(){
        if(instance == null)
        {
            instance = new Emailmanagement();
        }

        return instance;
    }		
    private boolean checkUserLevel(int USER_LEVEL){
        if (USER_LEVEL>1){
            return true;
        } else {
            return false;
        }
    }     
	public Emails[] getemails(Long USER_ID){
		Emails emails[] = new Emails[1];
	    try {
	    	Session session = HibernateUtil.currentSession();
	    	Transaction tx = session.beginTransaction();
			Query query = session.createQuery("select c from emails as c where c.USER_ID = :USER_ID");
			query.setLong("USER_ID", USER_ID.longValue());	
			int count = query.list().size();
			emails = new Emails[count];
			int k = 0;
			for (Iterator it2 = query.iterate(); it2.hasNext();) {
				emails[k] = (Emails) it2.next();
				k++;
			}
	    	tx.commit();
	    	HibernateUtil.closeSession();
        } catch( HibernateException ex ) {
        	log.error(ex);
        } catch ( Exception ex2 ){
        	log.error(ex2);
        }
		return emails;
	}
	public Emails[] getemailsCon(int CONTACT_ID){
		Emails emails[] = new Emails[1];
	    try {
	    	Session session = HibernateUtil.currentSession();
	    	Transaction tx = session.beginTransaction();
			Query query = session.createQuery("select c from emails as c where c.CONTACT_ID = :CONTACT_ID");
			query.setInteger("CONTACT_ID", CONTACT_ID);	
			int count = query.list().size();
			emails = new Emails[count];
			int k = 0;
			for (Iterator it2 = query.iterate(); it2.hasNext();) {
				emails[k] = (Emails) it2.next();
				k++;
			}
	    	tx.commit();
	    	HibernateUtil.closeSession();
        } catch( HibernateException ex ) {
        	log.error(ex);
        } catch ( Exception ex2 ){
        	log.error(ex2);
        }
		return emails;
	}	
	public String registerEmail(String EMail, long User_ID, String Username, String Userpass){
		String succ = "invalid email";
//		Emails emails = new Emails();
//		long time = CalenderI.getTimeStampMili();
//		emails.setEmail(EMail);
//		emails.setUSER_ID(User_ID);
//		emails.setStartdate(time);
//		emails.setUpdatedate(time);
//        try {   
//            Session session = HibernateUtil.currentSession();
//            Transaction tx = session.beginTransaction();
//            session.save(emails);
//            session.flush();   
//            session.clear();
//            session.refresh(emails);
//            tx.commit();
//            HibernateUtil.closeSession();   
//            succ = "valid email";
//        } catch( HibernateException ex ) {
//        	succ = "Error: "+ex;
//        } catch ( Exception ex2 ){
//        	succ = "Error: "+ex2;
//        }
		MailHandler MailHandler = new MailHandler();
		String data = "";
		data += "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>";
		data += "<html xmlns='http://www.w3.org/1999/xhtml' xml:lang='de' lang='de'>";
		data += "<head>";
		data += "<meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1' /></head><body";	
		data += "<b>Herzlich Willkommen im XMLShop</b><br />";
		data += "Kaufen Sie auf einer der innovativsten Plattformen ein! Hier bekommen Sie alle Waren f&uuml;r Ihren Bedarf in ansprechender und lebensnaher Form pr&auml;sentiert.<br />";
		data += "<br /><b>Ihre Zugansdaten:</b>";
		data += "<br />Username: "+Username;
		data += "<br />Passwort: "+Userpass;
		data += "<br />EMail: "+EMail+"<br /><br />";
		data += "Viel Spa&szlig; beim Einkauf w&uuml;nscht Ihnen dass XMLShop-Team";
		data += "<br />http://www.xmlshopsystem.xulu";
		data += "<br />service@xmlshopsystem.xulu</body></html>";
		succ = MailHandler.sendMail(EMail,"Willkommen im XMLShop",data);		
		return succ;
	}
	public String addEmailCon(String EMail, int CONTACT_ID){
		String succ = "invalid email";
//		Emails emails = new Emails();
//		long time = CalenderI.getTimeStampMili();
//		emails.setEmail(EMail);
//		emails.setCONTACT_ID(CONTACT_ID);
//		emails.setStartdate(time);
//		emails.setUpdatedate(time);
//        try {   
//            Session session = HibernateUtil.currentSession();
//            Transaction tx = session.beginTransaction();
//            session.save(emails);
//            session.flush();   
//            session.clear();
//            session.refresh(emails);
//            tx.commit();
//            HibernateUtil.closeSession();   
//            succ = "valid email";
//        } catch( HibernateException ex ) {
//        	succ = "Error: "+ex;
//        } catch ( Exception ex2 ){
//        	succ = "Error: "+ex2;
//        }		
		return succ;
	}	
	public String deleteEMailByID(int EMAIL_ID){
		String result = "Fehler im Bestellvorgang";
	    try {
	    	Session session = HibernateUtil.currentSession();
	    	Transaction tx = session.beginTransaction();    	
	        String hqlDelete = "delete emails where EMAIL_ID = :EMAIL_ID";
	        int deletedEntities = session.createQuery( hqlDelete )
	                            .setInteger( "EMAIL_ID", EMAIL_ID )
	                            .executeUpdate();
	        //session.flush(); 
	
	    	tx.commit();
	    	HibernateUtil.closeSession();
	    	result = "Erfolgreich"+deletedEntities;
        } catch( HibernateException ex ) {
        	log.error(ex);
        } catch ( Exception ex2 ){
        	log.error(ex2);
        }
		return result;
	}
	public String deleteEMailByUserID(int USER_ID){
		String result = "Fehler im Bestellvorgang";
	    try {
	    	Session session = HibernateUtil.currentSession();
	    	Transaction tx = session.beginTransaction();    	
	        String hqlDelete = "delete emails where USER_ID = :USER_ID";
	        int deletedEntities = session.createQuery( hqlDelete )
	                            .setInteger( "USER_ID", USER_ID )
	                            .executeUpdate();
	        //session.flush(); 
	
	    	tx.commit();
	    	HibernateUtil.closeSession();
	    	result = "Erfolgreich"+deletedEntities;
        } catch( HibernateException ex ) {
        	log.error(ex);
        } catch ( Exception ex2 ){
        	log.error(ex2);
        }
		return result;
	}
	public boolean checkUserEMail(String DataName, String DataValue){
		boolean UserLevel = true;
		//TODO: Reactivate Email validation
//		try {
//	    	Session session = HibernateUtil.currentSession();
//	    	Transaction tx = session.beginTransaction();    
//	    	Query query = session.createQuery("select c from emails as c where c.email = :DataValue");
//			query.setString("DataValue", DataValue);
//			int count = query.list().size();
//			Emails[] emails = new Emails[count];
//			int k = 0;
//			for (Iterator it2 = query.iterate(); it2.hasNext();) {
//				emails[k] = (Emails) it2.next();
//				k++;
//			}
//			if (count!=0){
//				for  (int vars=0;vars<emails.length;vars++){
//					if (emails[vars].getUSER_ID() != 0){
//						UserLevel = false;
//						break;
//					}
//				}
//			}
//	    	tx.commit();
//	    	HibernateUtil.closeSession();
//	    } catch( HibernateException ex ) {
//	    	System.out.println("Error: "+ex);	
//	    } catch ( Exception ex2 ){
//	    	System.out.println("Error: "+ex2);	
//	    }
		return UserLevel;
	}
    public String updateUserEmail(int MAIL_ID, Long USER_ID,String email){
        String res = "Fehler beim Update";
        try {
//            Session session = HibernateUtil.currentSession();
//            Transaction tx = session.beginTransaction();        
//            String hqlUpdate = "update Emails set email= :email, USER_ID = :USER_ID, updatedate = :updatedate where MAIL_ID= :MAIL_ID";
//            int updatedEntities = session.createQuery( hqlUpdate )
//                                .setString("email",email)
//                                .setInteger( "USER_ID", USER_ID )
//                                .setLong( "updatedate", CalenderI.getTimeStampMili() )
//                                .setInteger( "MAIL_ID", MAIL_ID )
//                                .executeUpdate();
//            res = "Success"+updatedEntities;
//            tx.commit();
//            HibernateUtil.closeSession();
        } catch( HibernateException ex ) {
        	log.error(ex);
        } catch ( Exception ex2 ){
        	log.error(ex2);
        }
        return res;
    }  
    public String updateContactEmail(int MAIL_ID, int Contact_ID,String email){
        String res = "Fehler beim Update";
        try {
//            Session session = HibernateUtil.currentSession();
//            Transaction tx = session.beginTransaction();        
//            String hqlUpdate = "update emails set email= :email, CONTACT_ID = :CONTACT_ID, updatedate = :updatedate where MAIL_ID= :MAIL_ID";
//            int updatedEntities = session.createQuery( hqlUpdate )
//                                .setString("email",email)
//                                .setInteger( "CONTACT_ID", Contact_ID )
//                                .setLong( "updatedate", CalenderI.getTimeStampMili() )
//                                .setInteger( "MAIL_ID", MAIL_ID )
//                                .executeUpdate();
//            res = "Success"+updatedEntities;
//            tx.commit();
//            HibernateUtil.closeSession();
        } catch( HibernateException ex ) {
        	log.error(ex);
        } catch ( Exception ex2 ){
        	log.error(ex2);
        }
        return res;
    } 
    
    public String sendNewPass(long User_ID, String Userpass){
        String succ = "invalid email";
        String mail = "";
        try {   
//            Session session = HibernateUtil.currentSession();
//            Transaction tx = session.beginTransaction();
//            Query query = session.createQuery("select c from emails as c where c.USER_ID = :USER_ID");
//            query.setInteger("USER_ID", User_ID);   
//            int count = query.list().size();
//            Emails[] emails = new Emails[count];
//            int k = 0;
//            for (Iterator it2 = query.iterate(); it2.hasNext();) {
//                emails[k] = (Emails) it2.next();
//                k++;
//            }
//            tx.commit();
//            HibernateUtil.closeSession();
//            mail = emails[0].getEmail();
        } catch( HibernateException ex ) {
            succ = "Error: "+ex;
        } catch ( Exception ex2 ){
            succ = "Error: "+ex2;
        }
        MailHandler MailHandler = new MailHandler();
        String data = "";
        data += "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>";
        data += "<html xmlns='http://www.w3.org/1999/xhtml' xml:lang='de' lang='de'>";
        data += "<head>";
        data += "<meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1' /></head><body";   
        data += "<b>Ihr Passwort wurde neu gesetzt</b><br />";
        data += "<br /><b>Ihr neues Passwort:</b>";
        data += "<br />Passwort: "+Userpass;
        data += "<br />EMail: "+mail+"<br /><br />";
        data += "Sie können Ihr Passwort in ihrem Benutzerkonto jederzeit ändern";
        data += "<br />http://www.xmlshopsystem.xulu";
        data += "<br />service@xmlshopsystem.xulu</body></html>";
        succ = MailHandler.sendMail(mail,"Willkommen im XMLShop",data);        
        return succ;
    }    
}
