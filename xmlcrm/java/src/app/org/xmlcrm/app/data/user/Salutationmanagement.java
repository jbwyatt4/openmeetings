package org.xmlcrm.app.data.user;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.xmlcrm.app.hibernate.beans.user.Salutations;
import org.xmlcrm.app.hibernate.utils.HibernateUtil;

/**
 * 
 * @author swagner
 *
 */
public class Salutationmanagement {

	private static final Log log = LogFactory
			.getLog(Salutationmanagement.class);

	private static Salutationmanagement instance = null;

	private Salutationmanagement() {
	}

	public static synchronized Salutationmanagement getInstance() {
		if (instance == null) {
			instance = new Salutationmanagement();
		}
		return instance;
	}

	/**
	 * Adds a new Salutation to the table Titles
	 * @param titelname
	 */
	public void addUserSalutation(String titelname) {
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Salutations ti = new Salutations();
			ti.setName(titelname);
			ti.setDeleted("false");
			ti.setStarttime(new Date());
			session.save(ti);
			tx.commit();
			HibernateUtil.closeSession(idf);
		} catch (HibernateException ex) {
			log.error("[addUserSalutation]" + ex);
		} catch (Exception ex2) {
			log.error("[addUserSalutation]" + ex2);

		}
	}

}
