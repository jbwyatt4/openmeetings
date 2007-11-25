package org.xmlcrm.app.data.basic;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.xmlcrm.app.hibernate.beans.lang.Fieldlanguagesvalues;
import org.xmlcrm.app.hibernate.beans.lang.Fieldvalues;
import org.xmlcrm.app.hibernate.utils.HibernateUtil;

public class Fieldmanagment {

	private static final Log log = LogFactory.getLog(Fieldmanagment.class);

	private static Fieldmanagment instance = null;

	private Fieldmanagment() {
	}

	public static synchronized Fieldmanagment getInstance() {
		if (instance == null) {
			instance = new Fieldmanagment();
		}
		return instance;
	}

	public Fieldlanguagesvalues getFieldByIdAndLanguage(long fieldvalues_id,
			long language_id) {

		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();

			Query query = session
					.createQuery("select f from Fieldlanguagesvalues f WHERE f.language_id = :language_id AND f.fieldvalues_id = :fieldvalues_id");
			query.setLong("fieldvalues_id", fieldvalues_id);
			query.setLong("language_id", language_id);

			List ll = query.list();
			tx.commit();
			HibernateUtil.closeSession(idf);
			
			if (ll.size() > 0) {
				return (Fieldlanguagesvalues) ll.get(0);
			} else {
				log.error("nothing found :" + fieldvalues_id + " " + language_id);
			}			

		} catch (HibernateException ex) {
			log.error("[getFieldByIdAndLanguage]: " + ex);
		} catch (Exception ex2) {
			log.error("[getFieldByIdAndLanguage]: " + ex2);
		}
		return null;
	}

	public List getAllFieldsByLanguage(int language_id) {
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();

			Query query = session.createQuery("select f from Fieldlanguagesvalues f WHERE f.language_id = :language_id ");
			query.setLong("language_id", language_id);
			List returnList = query.list();
			tx.commit();
			HibernateUtil.closeSession(idf);

			return returnList;
		} catch (HibernateException ex) {
			log.error("[getConfKey]: " + ex);
		} catch (Exception ex2) {
			log.error("[getConfKey]: " + ex2);
		}
		return null;
	}


	public Long addFieldValueByFieldAndLanguage(long field_id,
			long language_id, String fieldvalue) {
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();

			Fieldlanguagesvalues flv = new Fieldlanguagesvalues();
			flv.setStarttime(new Date());
			flv.setValue(fieldvalue);
			flv.setLanguage_id(language_id);
			flv.setFieldvalues_id(field_id);
			flv.setDeleted("false");

			Long fieldlanguagesvaluesId = (Long) session.save(flv);

			tx.commit();
			HibernateUtil.closeSession(idf);
			
			return fieldlanguagesvaluesId;
		} catch (HibernateException ex) {
			log.error("[getConfKey]: ",ex);
		} catch (Exception ex2) {
			log.error("[getConfKey]: ",ex2);
		}
		return null;
	}

	public Long addField(String fieldName) {
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();

			Fieldvalues fl = new Fieldvalues();
			fl.setStarttime(new Date());
			fl.setName(fieldName);
			fl.setDeleted("false");

			Long fieldId = (Long)session.save(fl);

			tx.commit();
			HibernateUtil.closeSession(idf);
			
			return fieldId;
		} catch (HibernateException ex) {
			log.error("[getConfKey]: ",ex);
		} catch (Exception ex2) {
			log.error("[getConfKey]: ",ex2);
		}
		return null;
	}

	private void updateField(Fieldvalues fv) {
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.update(fv);
			tx.commit();
			HibernateUtil.closeSession(idf);
		} catch (HibernateException ex) {
			log.error("[updateFieldLanguagesLabel]: ",ex);
		} catch (Exception ex2) {
			log.error("[updateFieldLanguagesLabel]: ",ex2);
		}
	}
	
	private void updateFieldLanguagesLabel(Fieldlanguagesvalues flv) {
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.update(flv);
			tx.commit();
			HibernateUtil.closeSession(idf);
		} catch (HibernateException ex) {
			log.error("[updateFieldLanguagesLabel]: ",ex);
		} catch (Exception ex2) {
			log.error("[updateFieldLanguagesLabel]: ",ex2);
		}
	}	

}
