package org.xmlcrm.app.data.user;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.xmlcrm.app.hibernate.beans.domain.Organisation;
import org.xmlcrm.app.hibernate.beans.domain.Organisation_Users;
import org.xmlcrm.app.hibernate.utils.HibernateUtil;

public class Organisationmanagement {
	private static final Log log = LogFactory
			.getLog(Organisationmanagement.class);

	private static Organisationmanagement instance = null;

	public static synchronized Organisationmanagement getInstance() {
		if (instance == null) {
			instance = new Organisationmanagement();
		}
		return instance;
	}

	/**
	 * adds a new organisation to the table organisation
	 * @param titelname
	 * @param user_id
	 */
	public Long addOrganisation(String orgname, long user_id) {
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Organisation org = new Organisation();
			org.setName(orgname);
			org.setInsertedby(user_id);
			org.setDeleted("false");
			org.setStarttime(new Date());
			long id = (Long) session.save(org);
			tx.commit();
			HibernateUtil.closeSession(idf);
			return id;
		} catch (HibernateException ex) {
			log.error("[addOrganisation]" + ex);
		} catch (Exception ex2) {
			log.error("[addOrganisation]" + ex2);
		}
		return null;
	}

	/**
	 * Gets an organisation by its id
	 * @param organisation_id
	 * @return
	 */
	public Organisation getOrganisationById(long organisation_id) {
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session
					.createQuery("select c from Organisation as c where c.organisation_id = :organisation_id AND deleted != :deleted");
			query.setLong("organisation_id", organisation_id);
			query.setString("deleted", "true");
			List ll = query.list();
			tx.commit();
			HibernateUtil.closeSession(idf);
			if (ll.size() > 0) {
				return (Organisation) ll.get(0);
			}
		} catch (HibernateException ex) {
			log.error("[getOrganisationById]" + ex);
		} catch (Exception ex2) {
			log.error("[getOrganisationById]" + ex2);
		}
		return null;
	}

	/**
	 * Adds a user to a given organisation-unit
	 * @param user_id
	 * @param organisation_id
	 * @param insertedby
	 * @return
	 */
	public Long addUserToOrganisation(long user_id, long organisation_id,
			long insertedby, String comment) {
		try {
			Organisation org = this.getOrganisationById(organisation_id);
			log.error("org: " + org.getName());

			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Organisation_Users orgUser = new Organisation_Users();
			orgUser.setOrganisation(org);
			orgUser.setUser_id(user_id);
			orgUser.setDeleted("false");
			orgUser.setStarttime(new Date());
			orgUser.setComment(comment);
			long id = (Long) session.save(orgUser);
			tx.commit();
			HibernateUtil.closeSession(idf);
			return id;
		} catch (HibernateException ex) {
			log.error("[addOrganisation]" + ex);
		} catch (Exception ex2) {
			log.error("[addOrganisation]" + ex2);
		}
		return null;
	}

}
