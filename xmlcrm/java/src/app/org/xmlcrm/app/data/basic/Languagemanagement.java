package org.xmlcrm.app.data.basic;

import java.util.List;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.xmlcrm.app.hibernate.utils.HibernateUtil;

import org.xmlcrm.app.hibernate.beans.lang.FieldLanguage;

public class Languagemanagement {
	
	private static final Log log = LogFactory.getLog(Languagemanagement.class);
	
	private Languagemanagement(){}
    private static Languagemanagement instance = null;
    public static synchronized Languagemanagement getInstance(){
        if(instance == null)
        {
            instance = new Languagemanagement();
        }

        return instance;
    }
    
	public void addLanguage(String langName){
	    try {
	    	Session session = HibernateUtil.currentSession();
	    	Transaction tx = session.beginTransaction();
			
	    	FieldLanguage fl = new FieldLanguage();
	    	fl.setStarttime(new Date());
	    	fl.setName(langName);
	    	
	    	session.save(fl);
	    	
	    	tx.commit();
	    	HibernateUtil.closeSession();
        } catch( HibernateException ex ) {
        	log.error("[getConfKey]: "+ex);
        } catch ( Exception ex2 ){
        	log.error("[getConfKey]: "+ex2);
        }
	}
	
	public List getLanguages(){
	    try {
	    	Session session = HibernateUtil.currentSession();
	    	Transaction tx = session.beginTransaction();
	    	Query query = session.createQuery("select c from FieldLanguage as c");
	    	List ll = query.list();
	    	tx.commit();
	    	HibernateUtil.closeSession();
	    	
	    	return ll;
        } catch( HibernateException ex ) {
        	log.error("[getConfKey]: "+ex);
        } catch ( Exception ex2 ){
        	log.error("[getConfKey]: "+ex2);
        }
        return null;
	}
    
}
