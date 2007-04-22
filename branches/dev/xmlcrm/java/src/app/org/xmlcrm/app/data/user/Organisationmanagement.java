package org.xmlcrm.app.data.user;

import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Collections;
import java.util.LinkedHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.xmlcrm.app.data.beans.basic.SearchResult;
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
	 * adds a new organisation if userlevel is admin
	 * @param USER_LEVEL
	 * @param orgname
	 * @param user_id
	 * @return
	 */
	public Long addOrganisation(Long USER_LEVEL, String orgname, long user_id){
		try {
			if (AuthLevelmanagement.getInstance().checkAdminLevel(USER_LEVEL)){
				return this.addOrganisation(orgname, user_id);
			}
		} catch (Exception err){
			log.error("addOrganisation",err);
		}
		return null;
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
			log.error("[addOrganisation]" ,ex);
		} catch (Exception ex2) {
			log.error("[addOrganisation]" ,ex2);
		}
		return null;
	}
	
	/**
	 * 
	 * @param USER_LEVEL
	 * @param start
	 * @param max
	 * @param orderby
	 * @return
	 */
	public SearchResult getOrganisations(long USER_LEVEL, int start ,int max, String orderby) {
		try {
			if (AuthLevelmanagement.getInstance().checkAdminLevel(USER_LEVEL)){
				SearchResult sresult = new SearchResult();
				sresult.setObjectName(Organisation.class.getName());
				sresult.setRecords(this.selectMaxFromOrganisations());
				sresult.setResult(this.getOrganisations(start, max, orderby));
				return sresult;
			}
		} catch (HibernateException ex) {
			log.error("[getOrganisations]" ,ex);
		} catch (Exception ex2) {
			log.error("[getOrganisations]" ,ex2);
		}
		return null;
	}
	
	/**
	 * 
	 * @param USER_LEVEL
	 * @return
	 */
	public List getOrganisations(int start ,int max, String orderby) {
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Organisation.class);
			crit.add(Restrictions.eq("deleted", "false"));
			crit.setFirstResult(start);
			crit.setMaxResults(max);
			crit.addOrder(Order.asc(orderby));
			List ll = crit.list();
			tx.commit();
			HibernateUtil.closeSession(idf);
			return ll;
		} catch (HibernateException ex) {
			log.error("[getOrganisations]" ,ex);
		} catch (Exception ex2) {
			log.error("[getOrganisations]" ,ex2);
		}
		return null;
	}	
	
	/**
	 * 
	 * @return
	 */
	private Long selectMaxFromOrganisations(){
		try {
			//get all users
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("select max(c.organisation_id) from Organisation c where c.deleted = 'false'"); 
			List ll = query.list();
			tx.commit();
			HibernateUtil.closeSession(idf);
			log.error((Long)ll.get(0));
			return (Long)ll.get(0);				
		} catch (HibernateException ex) {
			log.error("[selectMaxFromUsers] "+ex);
		} catch (Exception ex2) {
			log.error("[selectMaxFromUsers] "+ex2);
		}
		return null;
	}	
	
	/**
	 * updates an organisation if USER_LEVEL is admin
	 * @param USER_LEVEL
	 * @param organisation_id
	 * @param orgname
	 * @param users_id
	 * @return
	 */
	public Long updateOrganisation(Long USER_LEVEL, long organisation_id, String orgname, long users_id){
		try {
			Organisation org = this.getOrganisationById(organisation_id);
			org.setName(orgname);
			org.setUpdatedby(users_id);
			org.setUpdatetime(new Date());
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.update(org);
			tx.commit();
			HibernateUtil.closeSession(idf);
			return org.getOrganisation_id();
		} catch (HibernateException hex){
			log.error("updateOrganisation",hex);
		} catch (Exception err){
			log.error("updateOrganisation",err);
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
			log.error("[getOrganisationById]",ex);
		} catch (Exception ex2) {
			log.error("[getOrganisationById]",ex2);
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
			log.error("[addUserToOrganisation]",ex);
		} catch (Exception ex2) {
			log.error("[addUserToOrganisation]",ex2);
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
			log.error("[checkUserContainsOrganisation]",ex);
		} catch (Exception ex2) {
			log.error("[checkUserContainsOrganisation]",ex2);
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
			log.error("[getOrganisationUsers]",ex);
		} catch (Exception ex2) {
			log.error("[getOrganisationUsers]",ex2);
		}
		return null;
	}
	
	/**
	 * checks if the orgId is in the list of orgIds which have been send as organisations
	 * @param orgId
	 * @param org
	 * @return
	 * @throws Exception
	 */
	private boolean checkOrgInList(Long orgId, LinkedHashMap org) throws Exception{
		for (Iterator it = org.keySet().iterator();it.hasNext();){
			Integer key = (Integer) it.next();
			Long newOrgId = Long.valueOf(org.get(key).toString()).longValue();
			log.error("[checkOrgInList]: newOrgId"+newOrgId);
			log.error("[checkOrgInList]: org"+org);
			if (newOrgId==orgId) return true;
		}
		return false;
	}
	
	/**
	 * checks if an orgId is already stored in the Users Organisations Object
	 * @param orgId
	 * @param org
	 * @return
	 * @throws Exception
	 */
	private boolean checkOrgInStoredList(long orgId, Set org) throws Exception {
		for (Iterator it = org.iterator();it.hasNext();){
			Organisation_Users orgUsers = (Organisation_Users) it.next();
			if (orgUsers.getOrganisation().getOrganisation_id()==orgId) return true;
		}
		return false;
	}
	
	/**
	 * updates users-organisations by a given params
	 * @param us
	 * @param organisations
	 * @return
	 */
	public Long updateUserOrganisationsByUser(Users us, LinkedHashMap organisations){
		try {
			LinkedList<Long> orgIdsToAdd = new LinkedList<Long>();
			LinkedList<Long> orgIdsToDelete = new LinkedList<Long>();
			
			if (us.getOrganisation_users()!=null){
				
				for (Iterator it = organisations.keySet().iterator();it.hasNext();){
					Integer key = (Integer) it.next();
					Long orgIdToAdd = Long.valueOf(organisations.get(key).toString()).longValue();
					boolean isAlreadyStored = this.checkOrgInStoredList(orgIdToAdd, us.getOrganisation_users());
					if (!isAlreadyStored) orgIdsToAdd.add(orgIdToAdd);
				}
				
				for (Iterator it = us.getOrganisation_users().iterator();it.hasNext();){
					Organisation_Users orgUsers = (Organisation_Users) it.next();
					Long orgIdStored = orgUsers.getOrganisation().getOrganisation_id();
					log.error("updateUserOrganisationsByUser check1 : "+orgIdStored);
					boolean shouldBeStored = this.checkOrgInList(orgIdStored,organisations);
					if (!shouldBeStored) orgIdsToDelete.add(orgIdStored);
				}

				log.error("updateUserOrganisationsByUser size ADD: "+orgIdsToAdd.size());
				log.error("updateUserOrganisationsByUser size DELETE: "+orgIdsToDelete.size());
			}
		} catch (Exception err){
			log.error("[updateUserOrganisationsByUser] ",err);
		}
		return null;
	}
	
	/**
	 * adds all send organisations to this User-Object (for newly registered/created Users)
	 * @param us
	 * @param org
	 * @return
	 */
	public Long addUserOrganisationsByHashMap(long us, LinkedHashMap org){
		try {
			if (org!=null){
				for (Iterator it = org.keySet().iterator();it.hasNext();){
					Integer key = (Integer) it.next();
					Long newOrgId = Long.valueOf(org.get(key).toString()).longValue();
					this.addUserToOrganisation(us, newOrgId, 1, "");
				}
			}
		} catch (Exception ex) {
			log.error("addUserOrganisationsByHashMap",ex);
		}
		return null;
	}

}
