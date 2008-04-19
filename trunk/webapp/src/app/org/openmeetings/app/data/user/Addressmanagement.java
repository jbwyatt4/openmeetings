package org.openmeetings.app.data.user;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openmeetings.app.hibernate.beans.adresses.Adresses;
import org.openmeetings.app.hibernate.beans.adresses.States;
import org.openmeetings.app.hibernate.utils.HibernateUtil;

public class Addressmanagement {

	private static final Log log = LogFactory.getLog(Addressmanagement.class);

	private static Addressmanagement instance = null;

	public static synchronized Addressmanagement getInstance() {
		if (instance == null) {
			instance = new Addressmanagement();
		}
		return instance;
	}

	/**
	 * adds a new record to the adress table
	 * @param street
	 * @param zip
	 * @param town
	 * @param states_id
	 * @param additionalname
	 * @param comment
	 * @param fax
	 * @return id of generated Adress-Object or NULL
	 */
	public Long saveAddress(String street, String zip, String town,
			long states_id, String additionalname, String comment, String fax) {
		try {
			States st = Statemanagement.getInstance().getStateById(states_id);

			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();

			Adresses adr = new Adresses();
			adr.setAdditionalname(additionalname);
			adr.setComment(comment);
			adr.setStarttime(new Date());
			adr.setFax(fax);
			adr.setStreet(street);
			adr.setTown(town);
			adr.setZip(zip);
			adr.setStates(st);

			Long id = (Long) session.save(adr);

			tx.commit();
			HibernateUtil.closeSession(idf);

			log.debug("added id " + id);

			return id;
		} catch (HibernateException ex) {
			log.error(ex);
		} catch (Exception ex2) {
			log.error(ex2);
		}
		return null;
	}

	/**
	 * gets an adress by its id
	 * @param adresses_id
	 * @return Adress-Object or NULL
	 */
	public Adresses getAdressbyId(long adresses_id) {
		try {
			String hql = "select c from Adresses as c where c.adresses_id = :adresses_id";
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setLong("adresses_id", adresses_id);
			Adresses addr = (Adresses) query.uniqueResult();
			tx.commit();
			HibernateUtil.closeSession(idf);
			return addr;
		} catch (HibernateException ex) {
			log.error(ex);
		} catch (Exception ex2) {
			log.error(ex2);
		}
		return null;
	}
	
	/**
	 * updates an Adress-Record by its given Id
	 * @param adresses_id
	 * @param street
	 * @param zip
	 * @param town
	 * @param states_id
	 * @param additionalname
	 * @param comment
	 * @param fax
	 * @return the updated Adress-Object or null
	 */
	public Adresses updateAdress(long adresses_id, String street, String zip, String town,
			long states_id, String additionalname, String comment, String fax) {
		try {
			States st = Statemanagement.getInstance().getStateById(states_id);
			
			Adresses adr = this.getAdressbyId(adresses_id);

			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();

			adr.setAdditionalname(additionalname);
			adr.setComment(comment);
			adr.setUpdatetime(new Date());
			adr.setFax(fax);
			adr.setStreet(street);
			adr.setTown(town);
			adr.setZip(zip);
			adr.setStates(st);

			session.update(adr);

			tx.commit();
			HibernateUtil.closeSession(idf);

			return adr;
		} catch (HibernateException ex) {
			log.error(ex);
		} catch (Exception ex2) {
			log.error(ex2);
		}
		return null;
	}
	
	/**
	 * 
	 * @param addr
	 * @return
	 */
	public Adresses updateAdress(Adresses addr) {
		try {
			Adresses addrRemote = this.getAdressbyId(addr.getAdresses_id());
			addr.setEmails(addrRemote.getEmails());
			addr.setPhones(addrRemote.getPhones());
			
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();

			session.update(addr);
			session.flush();

			tx.commit();
			HibernateUtil.closeSession(idf);

			return addr;
		} catch (HibernateException ex) {
			log.error(ex);
		} catch (Exception ex2) {
			log.error(ex2);
		}
		return null;
	}	

}
