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

	public Fieldlanguagesvalues getFieldByIdAndLanguage(Long fieldvalues_id, Long language_id) {
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("select f from Fieldlanguagesvalues f WHERE f.language_id = :language_id AND f.fieldvalues_id = :fieldvalues_id");
			query.setLong("fieldvalues_id", fieldvalues_id);
			query.setLong("language_id", language_id);
			Fieldlanguagesvalues flv = (Fieldlanguagesvalues) query.uniqueResult();
			tx.commit();
			HibernateUtil.closeSession(idf);
			
			return flv;
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
	
	/**
	 * update given Field and its Label by IDs
	 * @param fieldvalues_id
	 * @param name
	 * @param fieldlanguagesvalues_id
	 * @param value
	 * @return
	 */
	public Long updateLabel(Long fieldvalues_id, String name, Long fieldlanguagesvalues_id, String value) {
		try {
			Fieldvalues fv = this.getFieldvaluesById(fieldvalues_id);
			if (fv==null) {
				return new Long(-24);
			} else {
				fv.setName(name);
				fv.setUpdatetime(new Date());
				this.updateField(fv);
			}
			Fieldlanguagesvalues flv = this.getFieldlanguagesvaluesById(fieldlanguagesvalues_id);
			if (flv==null) {
				return new Long(-25);
			} else {
				flv.setUpdatetime(new Date());
				flv.setValue(value);
				this.updateFieldLanguagesLabel(flv);
			}
			return new Long(1);
		} catch (HibernateException ex) {
			log.error("[updateFieldLanguagesLabel]: ",ex);
		} catch (Exception ex2) {
			log.error("[updateFieldLanguagesLabel]: ",ex2);
		}
		return new Long(-1);
	}
	
	private Fieldvalues getFieldvaluesById(Long fieldvalues_id) throws Exception {
		String hql = "select f from Fieldvalues f WHERE f.fieldvalues_id = :fieldvalues_id ";
		Object idf = HibernateUtil.createSession();
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(hql);
		query.setLong("fieldvalues_id", fieldvalues_id);
		Fieldvalues fv = (Fieldvalues) query.uniqueResult();
		tx.commit();
		HibernateUtil.closeSession(idf);
		return fv;
	}
	
	private Fieldlanguagesvalues getFieldlanguagesvaluesById(Long fieldlanguagesvalues_id) throws Exception {
		String hql = "select f from Fieldlanguagesvalues f WHERE f.fieldlanguagesvalues_id = :fieldlanguagesvalues_id ";
		Object idf = HibernateUtil.createSession();
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(hql);
		query.setLong("fieldlanguagesvalues_id", fieldlanguagesvalues_id);
		Fieldlanguagesvalues flv = (Fieldlanguagesvalues) query.uniqueResult();
		tx.commit();
		HibernateUtil.closeSession(idf);
		return flv;
	}
	
	private void updateField(Fieldvalues fv) throws Exception {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.update(fv);
			tx.commit();
			HibernateUtil.closeSession(idf);
	}
	
	private void updateFieldLanguagesLabel(Fieldlanguagesvalues flv) throws Exception {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.update(flv);
			tx.commit();
			HibernateUtil.closeSession(idf);
	}	

}
