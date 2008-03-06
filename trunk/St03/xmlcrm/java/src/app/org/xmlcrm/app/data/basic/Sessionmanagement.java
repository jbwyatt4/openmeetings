package org.xmlcrm.app.data.basic;

import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.util.Calendar;

import org.xmlcrm.app.hibernate.beans.basic.Sessiondata;
import org.xmlcrm.app.hibernate.utils.HibernateUtil;
import org.xmlcrm.utils.math.Calender;
import org.xmlcrm.utils.math.MD5Calc;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 * 
 * @author swagner
 * This Class handles all session management
 * 
 * TODO: Delete all inactive session by a scheduler
 *
 */
public class Sessionmanagement {
 
	private static final Log log = LogFactory.getLog(Sessionmanagement.class);

	private static Sessionmanagement instance;

	private Sessionmanagement() {
	}

	public static synchronized Sessionmanagement getInstance() {
		if (instance == null) {
			instance = new Sessionmanagement();
		}
		return instance;
	}

	/**
	 * creates a new session-object in the database
	 * @return
	 */
	public Sessiondata startsession() {
		//log.error("startsession User: || ");
		
		MD5Calc md5 = new MD5Calc();

		long thistime = new Date().getTime();
		String chsum = md5.do_checksum(String.valueOf(thistime).toString());
		Sessiondata sessiondata = new Sessiondata();
		sessiondata.setSession_id(chsum);
		sessiondata.setRefresh_time(new Date());
		sessiondata.setStarttermin_time(new Date());
		sessiondata.setUser_id(null);
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.flush();
			session.save(sessiondata);
			session.flush();
			session.refresh(sessiondata);
			tx.commit();
			HibernateUtil.closeSession(idf);
		} catch (HibernateException ex) {
			log.error("[startsession]: " ,ex);
		} catch (Exception ex2) {
			log.error("[startsession]: " ,ex2);
		}

		return sessiondata;
	}

	/**
	 * check if a given sessionID is loged in
	 * @param SID
	 * @return
	 */
	public Long checkSession(String SID) {
		try {
			log.debug("checkSession User: || "+SID);
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			
			session.flush();
			Criteria crit = session.createCriteria(Sessiondata.class);
			crit.add(Restrictions.eq("session_id", SID));

			int count = crit.list().size();
			Sessiondata sessiondata = null;
			for (Iterator it2 = crit.list().iterator(); it2.hasNext();) {
				sessiondata = (Sessiondata) it2.next();
			}
			
			if (sessiondata!=null) session.refresh(sessiondata);
			session.flush();
			tx.commit();
			HibernateUtil.closeSession(idf);
			if (sessiondata!=null) log.error("checkSession USER_ID: "+sessiondata.getUser_id());
				
			if (sessiondata!=null) updatesession(SID);
			if (sessiondata==null || count == 0 || sessiondata.equals(null) ||
					sessiondata.getUser_id()==null || sessiondata.getUser_id().equals(null) || sessiondata.getUser_id().equals(new Long(0)) ) {
				return new Long(0);
			} else {
				return sessiondata.getUser_id();
			}			
		} catch (HibernateException ex) {
			log.error("[checkSession]: " ,ex);
		} catch (Exception ex2) {
			log.error("[checkSession]: " ,ex2);
		}
		return null;
	}

	/**
	 * update the session of a user with a new user id
	 * this is needed to see if the session is loggedin
	 * @param SID
	 * @param USER_ID
	 */
	public void updateUser(String SID, long USER_ID) {
		try {
			log.debug("updateUser User: "+USER_ID+" || "+SID);
			
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.flush();
			Criteria crit = session.createCriteria(Sessiondata.class);
			crit.add(Restrictions.eq("session_id", SID));

			List fullList = crit.list();
			if (fullList.size() == 0){
				log.error("Could not find session to update: "+SID);
				return;
			} else {
				log.error("Found session to update: "+SID);
			}
			tx.commit();
			HibernateUtil.closeSession(idf);
			
			Sessiondata sd = (Sessiondata) fullList.get(0);
			log.debug("Found session to update: "+sd.getSession_id()+ " userId: "+USER_ID);
			
			idf = HibernateUtil.createSession();
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			sd.setRefresh_time(new Date());
			session.refresh(sd);
			sd.setUser_id(USER_ID);
			session.flush();
			session.update(sd);
			session.flush();
			tx.commit();
			HibernateUtil.closeSession(idf);
			
			log.debug("session updated User: "+USER_ID);

		} catch (HibernateException ex) {
			log.error("[updateUser]: " ,ex);
		} catch (Exception ex2) {
			log.error("[updateUser]: " ,ex2);
		}
	}

	/**
	 * update the session every time a user makes a request
	 * @param SID
	 */
	private void updatesession(String SID) {
		try {
			log.debug("****** updatesession: "+SID);
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Sessiondata.class);
			crit.add(Restrictions.eq("session_id", SID));
			List fullList = crit.list();
			tx.commit();
			HibernateUtil.closeSession(idf);			
			if (fullList.size() == 0) {
				log.error("Found NO session to updateSession: ");

			} else {
				log.debug("Found session to updateSession: ");
				Sessiondata sd = (Sessiondata) fullList.iterator().next();
				log.debug("Found session to updateSession sd "+sd.getUser_id()+" "+sd.getSession_id());
				sd.setRefresh_time(new Date());
				
				Object idf2 = HibernateUtil.createSession();
				Session session2 = HibernateUtil.getSession();
				Transaction tx2 = session2.beginTransaction();				
				session2.update(sd);
				session2.flush();
				tx2.commit();
				HibernateUtil.closeSession(idf2);	
			}
			
		} catch (HibernateException ex) {
			log.error("[updatesession]: " ,ex);
		} catch (Exception ex2) {
			log.error("[updatesession]: " ,ex2);
		}
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	private List getSessionToDelete(Date date){
		try {
			log.debug("****** sessionToDelete: "+date);
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Sessiondata.class);
			crit.add(Restrictions.lt("refresh_time", date));
			List fullList = crit.list();
			tx.commit();
			HibernateUtil.closeSession(idf);
			return fullList;
		} catch (HibernateException ex) {
			log.error("[getSessionToDelete]: " ,ex);
		} catch (Exception ex2) {
			log.error("[getSessionToDelete]: " ,ex2);
		}
		return null;
	}
	
	/**
	 * 
	 *
	 */
	public void clearSessionTable(){
		try {
			log.debug("****** clearSessionTable: ");
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTimeInMillis(rightNow.getTimeInMillis()-1800000);
		    List l = this.getSessionToDelete(rightNow.getTime());
		    log.debug("clearSessionTable: "+l.size());
		    for (Iterator it = l.iterator();it.hasNext();){
				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				Sessiondata sData = (Sessiondata) it.next();
				session.delete(sData);
				tx.commit();
				HibernateUtil.closeSession(idf);
		    }
		    
		} catch (HibernateException ex) {
			log.error("clearSessionTable",ex);
		} catch (Exception err) {
			log.error("clearSessionTable",err);
		}
	}
}
