package org.xmlcrm.app.data.basic;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.xmlcrm.app.hibernate.beans.lang.Fieldlanguagesvalues;
import org.xmlcrm.app.hibernate.beans.lang.Fieldvalues;
import org.xmlcrm.app.hibernate.beans.user.Users;
import org.xmlcrm.app.hibernate.utils.HibernateUtil;

public class Fieldmanagment {
	
	private static final Log log = LogFactory.getLog(Fieldmanagment.class);
	
	private static Fieldmanagment instance = null;
	private Fieldmanagment() {}
	
	public static synchronized Fieldmanagment getInstance(){
		if (instance == null){
			instance = new Fieldmanagment();
		}
		return instance;
	}
	
	public Fieldlanguagesvalues getFieldByIdAndLanguage(long fieldvalues_id, long language_id){

	    try {
	    	Session session = HibernateUtil.currentSession();
	    	Transaction tx = session.beginTransaction();

			Query query = session.createQuery("select f from Fieldlanguagesvalues f WHERE f.language_id = :language_id AND f.fieldvalues_id = :fieldvalues_id");
			query.setLong("fieldvalues_id", fieldvalues_id);
			query.setLong("language_id", language_id);
			
			if (query.list().size()>0){
				return (Fieldlanguagesvalues)query.list().get(0);
			} else {
				log.error("nothing found :"+fieldvalues_id+" "+language_id);
			}
	    	tx.commit();
	    	HibernateUtil.closeSession();

        } catch( HibernateException ex ) {
        	log.error("[getFieldByIdAndLanguage]: "+ex);
        } catch ( Exception ex2 ){
        	log.error("[getFieldByIdAndLanguage]: "+ex2);
        }
		return null;
	}
	
	public List getAllFieldsByLanguage(int language_id){
	    try {
	    	Session session = HibernateUtil.currentSession();
	    	Transaction tx = session.beginTransaction();

			Query query = session.createQuery("select f from Fieldlanguagesvalues f WHERE f.language_id = :language_id ");
			query.setLong("language_id", language_id);
			List returnList = query.list();
	    	tx.commit();
	    	HibernateUtil.closeSession();
	    	
	    	return returnList;
        } catch( HibernateException ex ) {
        	log.error("[getConfKey]: "+ex);
        } catch ( Exception ex2 ){
        	log.error("[getConfKey]: "+ex2);
        }
		return null;
	}
	
	public void addFourFieldValues(String name, long field_id, String german, String english, String french, String spanish){
		this.addField(name);
		this.addFieldValueByFieldAndLanguage(field_id, 1, german);
		this.addFieldValueByFieldAndLanguage(field_id, 2, english);
		this.addFieldValueByFieldAndLanguage(field_id, 3, french);
		this.addFieldValueByFieldAndLanguage(field_id, 4, spanish);
	}
	
	public void addFieldValueByFieldAndLanguage(long field_id, long language_id, String fieldvalue){
	    try {
	    	Session session = HibernateUtil.currentSession();
	    	Transaction tx = session.beginTransaction();
			
	    	Fieldlanguagesvalues flv = new Fieldlanguagesvalues();
	    	flv.setStarttime(new Date());
	    	flv.setValue(fieldvalue);
	    	flv.setLanguage_id(language_id);
	    	flv.setFieldvalues_id(field_id);

	    	session.save(flv);
	    	
	    	tx.commit();
	    	HibernateUtil.closeSession();
        } catch( HibernateException ex ) {
        	log.error("[getConfKey]: "+ex);
        } catch ( Exception ex2 ){
        	log.error("[getConfKey]: "+ex2);
        }
	}
	
	public void addField(String fieldName){
	    try {
	    	Session session = HibernateUtil.currentSession();
	    	Transaction tx = session.beginTransaction();
			
	    	Fieldvalues fl = new Fieldvalues();
	    	fl.setStarttime(new Date());
	    	fl.setName(fieldName);

	    	session.save(fl);
	    	
	    	tx.commit();
	    	HibernateUtil.closeSession();
        } catch( HibernateException ex ) {
        	log.error("[getConfKey]: "+ex);
        } catch ( Exception ex2 ){
        	log.error("[getConfKey]: "+ex2);
        }
	}
	
	public List getFields(long language_id){
		
	    try {
	    	Session session = HibernateUtil.currentSession();
	    	Transaction tx = session.beginTransaction();

			Query query = session.createQuery("select c from Fieldvalues c, Fieldlanguagesvalues f WHERE f.language_id = :language_id ");
			query.setLong("language_id", language_id);
			List returnList = query.list();
	    	tx.commit();
	    	HibernateUtil.closeSession();
	    	
	    	return returnList;
        } catch( HibernateException ex ) {
        	log.error("[getConfKey]: "+ex);
        } catch ( Exception ex2 ){
        	log.error("[getConfKey]: "+ex2);
        }
		return null;
	}

}
