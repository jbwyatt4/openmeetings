package org.xmlcrm.app.data.basic;

import java.util.Iterator;
import java.util.List;
import java.util.Date;

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
		MD5Calc md5 = new MD5Calc("MD5");

		long thistime = Calender.getInstance().getTimeStampMili();
		String chsum = md5.do_checksum(String.valueOf(thistime).toString());
		Sessiondata sessiondata = new Sessiondata();
		sessiondata.setSession_id(chsum);
		sessiondata.setRefresh_time(new Date());
		sessiondata.setStarttermin_time(new Date());
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.save(sessiondata);
			session.flush();
			session.clear();
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
	public int checkSession(String SID) {
		int ret = 0;
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session
					.createQuery("select c from Sessiondata as c where c.session_id = :session_id");
			query.setString("session_id", SID);
			int count = query.list().size();
			Sessiondata sessiondata = new Sessiondata();
			for (Iterator it2 = query.iterate(); it2.hasNext();) {
				sessiondata = (Sessiondata) it2.next();
			}

			if (count == 0 || sessiondata.getUser_id() == new Long(0)) {
				ret = 0;
			} else {
				ret = sessiondata.getUser_id().intValue();
			}

			tx.commit();
			HibernateUtil.closeSession(idf);
			updatesession(SID);
		} catch (HibernateException ex) {
			log.error("[checkSession]: " ,ex);
		} catch (Exception ex2) {
			log.error("[checkSession]: " ,ex2);
		}
		return ret;
	}

	/**
	 * update the session of a user with a new user id
	 * this is needed to see if the session is loggedin
	 * @param SID
	 * @param USER_ID
	 */
	public void updateUser(String SID, long USER_ID) {
		try {
			//log.error("updateUser User: "+USER_ID);
			
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();

			Criteria crit = session.createCriteria(Sessiondata.class);
			crit.add(Restrictions.eq("session_id", SID));

			List fullList = crit.list();
			if (fullList.size() == 0)
				return;
			Sessiondata sd = (Sessiondata) fullList.get(0);
			sd.setRefresh_time(new Date());
			sd.setUser_id(USER_ID);

			session.update(sd);

			tx.commit();
			HibernateUtil.closeSession(idf);
			
			//log.error("session updated User: "+USER_ID);

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
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Sessiondata.class);
			crit.add(Restrictions.eq("session_id", SID));
			List fullList = crit.list();
			if (fullList.size() == 0)
				return;
			Sessiondata sd = (Sessiondata) fullList.iterator().next();
			sd.setRefresh_time(new Date());
			session.update(sd);
			tx.commit();
			HibernateUtil.closeSession(idf);
		} catch (HibernateException ex) {
			log.error("[updatesession]: " ,ex);
		} catch (Exception ex2) {
			log.error("[updatesession]: " ,ex2);
		}
	}
}
