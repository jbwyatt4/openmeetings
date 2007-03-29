package org.xmlcrm.app.data.user;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.xmlcrm.app.hibernate.beans.adresses.Adresses;
import org.xmlcrm.app.hibernate.beans.adresses.States;
import org.xmlcrm.app.hibernate.utils.HibernateUtil;

public class Adressmanagement {

	private static final Log log = LogFactory.getLog(Adressmanagement.class);

	private static Adressmanagement instance = null;

	public static synchronized Adressmanagement getInstance() {
		if (instance == null) {
			instance = new Adressmanagement();
		}
		return instance;
	}

	public Long saveAdress(String street, String zip, String town,
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

	public Adresses getAdressbyId(long adresses_id) {
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session
					.createQuery("select c from Adresses as c where c.adresses_id = :adresses_id");
			query.setLong("adresses_id", adresses_id);
			List ll = query.list();
			tx.commit();
			HibernateUtil.closeSession(idf);
			if (ll.size() > 0) {
				return (Adresses) ll.get(0);
			}
		} catch (HibernateException ex) {
			log.error(ex);
		} catch (Exception ex2) {
			log.error(ex2);
		}
		return null;
	}

}
