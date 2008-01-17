package org.xmlcrm.app.data.termine;

import java.util.List;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.xmlcrm.app.hibernate.beans.user.Users;
import org.xmlcrm.app.hibernate.utils.HibernateUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.xmlcrm.app.hibernate.beans.termine.Terminestatus;

public class Terminstatusmanagement {

	private static final Log log = LogFactory.getLog(Terminstatusmanagement.class);

	private static Terminstatusmanagement instance;

	private Terminstatusmanagement() {}

	public static synchronized Terminstatusmanagement getInstance() {
		if (instance == null) {
			instance = new Terminstatusmanagement();
		}
		return instance;
	}
	
	/**
	 * gets a Terminestatus-Object by its ID
	 * ordered by its status_id
	 * @param tStatus_id
	 * @return
	 */
	public Terminestatus getTerminestatusById(Long tStatus_id){
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Terminestatus.class);
			crit.addOrder(Order.asc("status_id"));
			crit.add(Restrictions.eq("status_id", tStatus_id));
			crit.add(Restrictions.eq("deleted", "false"));
			List ll = crit.list();
			tx.commit();
			HibernateUtil.closeSession(idf);
			if (ll.size()>0){
				return (Terminestatus) ll.get(0);
			}
		} catch( HibernateException ex ) {
        	log.error("[getTerminestatusById]: "+ex);
        } catch ( Exception ex2 ){
        	log.error("[getTerminestatusById]: "+ex2);
        } 
        return null;
	}
	
	/**
	 * get a list of all TerminStatus-Object availible
	 * @return
	 */
	public List getAllTerminestatus(){
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Terminestatus.class);
			crit.add(Restrictions.eq("deleted", "false"));
			List ll = crit.list();
			tx.commit();
			HibernateUtil.closeSession(idf);
			return ll;
		} catch( HibernateException ex ) {
        	log.error("[getTerminestatusById]: "+ex);
        } catch ( Exception ex2 ){
        	log.error("[getTerminestatusById]: "+ex2);
        } 
        return null;
	}
	
	
	/**
	 * adds a new TerminStatus
	 * will be added during installation/initial
	 * @param description
	 * @param comment
	 * @return
	 */
	public Long addTerminStatus(String description, String comment){
		try {
			Terminestatus tStatus = new Terminestatus();
			tStatus.setDeleted("false");
			tStatus.setStarttime(new Date());
			tStatus.setDescription(description);
			tStatus.setComment(comment);
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Long tStatus_id = (Long)session.save(tStatus);
			tx.commit();
			HibernateUtil.closeSession(idf);
			return tStatus_id;
		} catch( HibernateException ex ) {
        	log.error("[addTerminStatus]: "+ex);
        } catch ( Exception ex2 ){
        	log.error("[addTerminStatus]: "+ex2);
        } 
        return null;
	}
	
	/**
	 * updates a TerminStatus by a given ID
	 * @param tStatus_id
	 * @param description
	 * @param comment
	 * @return
	 */
	public Long updateTerminStatus(long tStatus_id, String description, String comment){
		try {
			Terminestatus tStatus = this.getTerminestatusById(tStatus_id);
			tStatus.setUpdatetime(new Date());
			tStatus.setDescription(description);
			tStatus.setComment(comment);
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.update(tStatus);
			tx.commit();
			HibernateUtil.closeSession(idf);
			return tStatus.getStatus_id();
		} catch( HibernateException ex ) {
        	log.error("[updateTerminStatus]: "+ex);
        } catch ( Exception ex2 ){
        	log.error("[updateTerminStatus]: "+ex2);
        } 
        return null;
	}
	
	/**
	 * deletes a TerminStatus by a given ID
	 * @param tStatus_id
	 * @return
	 */
	public Long deleteTerminStatus(long tStatus_id){
		try {
			Terminestatus tStatus = this.getTerminestatusById(tStatus_id);
			tStatus.setUpdatetime(new Date());
			tStatus.setDeleted("true");
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.update(tStatus);
			tx.commit();
			HibernateUtil.closeSession(idf);
			return tStatus.getStatus_id();
		} catch( HibernateException ex ) {
        	log.error("[deleteTerminStatus]: "+ex);
        } catch ( Exception ex2 ){
        	log.error("[deleteTerminStatus]: "+ex2);
        } 
        return null;
	}

}
