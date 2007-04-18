package org.xmlcrm.app.data.user;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.xmlcrm.app.data.basic.AuthLevelmanagement;
import org.xmlcrm.app.hibernate.beans.domain.Organisation;
import org.xmlcrm.app.hibernate.beans.domain.Organisation_Users;
import org.xmlcrm.app.hibernate.beans.user.Users;
import org.xmlcrm.app.hibernate.utils.HibernateUtil;

/**
 * 
 * @author swagner
 *
 */
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
	 * 
	 * @param USER_LEVEL
	 * @return
	 */
	public List getOrganisations(long USER_LEVEL) {
		try {
			if (AuthLevelmanagement.getInstance().checkAdminLevel(USER_LEVEL)){
				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				Criteria crit = session.createCriteria(Organisation.class);
				crit.add(Restrictions.eq("deleted", "false"));
				List ll = crit.list();
				tx.commit();
				HibernateUtil.closeSession(idf);
				return ll;
			}
		} catch (HibernateException ex) {
			log.error("[getOrganisationById]" + ex);
		} catch (Exception ex2) {
			log.error("[getOrganisationById]" + ex2);
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
			Query query = session.createQuery("select c from Organisation as c where c.organisation_id = :organisation_id AND deleted != :deleted");
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
			log.error("[addUserToOrganisation]" + ex);
		} catch (Exception ex2) {
			log.error("[addUserToOrganisation]" + ex2);
		}
		return null;
	}
	
	
	private boolean checkUserContainsOrganisation(long users_id, long organisation_id){
		try {
			Users us = Usermanagement.getInstance().getUser(users_id);
			for (Iterator it = us.getOrganisation_users().iterator(); it.hasNext();){
				Organisation_Users orguser = (Organisation_Users) it.next();
				if (orguser.getOrganisation().getOrganisation_id()==organisation_id)
					return true;
			}
		} catch (HibernateException ex) {
			log.error("[checkUserContainsOrganisation]" + ex);
		} catch (Exception ex2) {
			log.error("[checkUserContainsOrganisation]" + ex2);
		}
		return false;
	}
	
	
	
	/**
	 * Filter all Organisations by user
	 * TODO: Add sorting
	 * @param USER_LEVEL
	 * @param users_id
	 * @param organisation_id
	 * @param start
	 * @param max
	 * @param orderby
	 * @return
	 */
	public List getOrganisationUsers(long USER_LEVEL, long users_id, long organisation_id, int start, int max, String orderby){
		try {
			if (AuthLevelmanagement.getInstance().checkUserLevel(USER_LEVEL)){
				if (this.checkUserContainsOrganisation(users_id, organisation_id)){
					//get all users
					Object idf = HibernateUtil.createSession();
					Session session = HibernateUtil.getSession();
					Transaction tx = session.beginTransaction();
					Criteria crit = session.createCriteria(Organisation_Users.class);
					crit.setMaxResults(max);
					crit.setFirstResult(start);
					List userOrg = crit.list();
					tx.commit();
					HibernateUtil.closeSession(idf);
					List<Users> userL = new LinkedList<Users>();
					for (Iterator it = userOrg.iterator();it.hasNext();){
						Users us = (Users) it.next();
						userL.add(Usermanagement.getInstance().getUser(us.getUser_id()));
					}
					//Collections.sort(userL,new UsersFirstnameComperator());
					return userL;
				}
			}
		} catch (HibernateException ex) {
			log.error("[getOrganisationUsers]" + ex);
		} catch (Exception ex2) {
			log.error("[getOrganisationUsers]" + ex2);
		}
		return null;
	}

}
