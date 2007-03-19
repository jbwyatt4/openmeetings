package org.xmlcrm.app.data.basic;

import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.xmlcrm.app.hibernate.beans.basic.Naviglobal;
import org.xmlcrm.app.hibernate.beans.basic.Navimain;
import org.xmlcrm.app.hibernate.beans.basic.Navisub;
import org.xmlcrm.app.hibernate.utils.HibernateUtil;

public class Navimanagement {
	
	private static final Log log = LogFactory.getLog(Navimanagement.class);
	
	private static Navimanagement instance;
	
	private Navimanagement(){};
	
	public static synchronized Navimanagement getInstance(){
		if (instance == null){
			instance = new Navimanagement();
		}
		return instance;
	}
	
	public List getMainMenu(long User_LEVEL,long USER_ID, long language_id){
		List ll = this.getMainMenu(User_LEVEL, USER_ID);
		for (Iterator it2 = ll.iterator(); it2.hasNext();) {
			Naviglobal navigl = (Naviglobal) it2.next();
			navigl.setLabel(Fieldmanagment.getInstance().getFieldByIdAndLanguage(navigl.getFieldvalues_id(), language_id));
			Set s = navigl.getMainnavi();
			for (Iterator it3 = s.iterator(); it3.hasNext();) {
				Navimain navim = (Navimain) it3.next();
				navim.setLabel(Fieldmanagment.getInstance().getFieldByIdAndLanguage(navim.getFieldvalues_id(), language_id));
				for (Iterator it4 = navim.getSubnavi().iterator(); it4.hasNext();) {
					Navisub navis = (Navisub) it4.next();
					navis.setLabel(Fieldmanagment.getInstance().getFieldByIdAndLanguage(navis.getFieldvalues_id(), language_id));
				}			
				
			}			
		}
		return ll;
	}
	
	public List getMainMenu(long User_LEVEL,long USER_ID){
		List navi = null;
	    try {
	    	
	    	Session session = HibernateUtil.currentSession();
	    	Transaction tx = session.beginTransaction();
           // Criteria crit = session.createCriteria();
	    	Query query = session.createQuery("select c from Naviglobal as c where c.level_id <= :level_id order by c.naviorder");
			query.setLong("level_id", User_LEVEL);
			navi = query.list();
			
	    	tx.commit();
	    	HibernateUtil.closeSession();

        } catch( HibernateException ex ) {
        	log.error(ex);
        } catch ( Exception ex2 ){
        	log.error(ex2);
        }
		return navi;
	}
    
    public void addGlobalStructure(String action,int naviorder,long fieldvalues_id, boolean isleaf, boolean isopen, long level_id, String name){
	    try {
	    	Naviglobal ng = new Naviglobal();
	    	ng.setAction(action);
	    	ng.setComment("");
	    	ng.setIcon("");
	    	ng.setNaviorder(naviorder);
	    	ng.setFieldvalues_id(fieldvalues_id);
	    	ng.setIsleaf(isleaf);
	    	ng.setIsopen(isopen);
	    	ng.setLevel_id(level_id);
	    	ng.setName(name);
	    	ng.setStarttime(new Date());
	    	
	    	Session session = HibernateUtil.currentSession();
	    	Transaction tx = session.beginTransaction();
           // Criteria crit = session.createCriteria();
	    	
	    	session.save(ng);
			
	    	tx.commit();
	    	HibernateUtil.closeSession();

        } catch( HibernateException ex ) {
        	log.error(ex);
        } catch ( Exception ex2 ){
        	log.error(ex2);
        }
    }
    
    public void addMainStructure(String action,int naviorder,long fieldvalues_id, boolean isleaf, boolean isopen, long level_id, String name, long global_id){
	    try {
	    	Navimain ng = new Navimain();
	    	ng.setAction(action);
	    	ng.setComment("");
	    	ng.setIcon("");
	    	ng.setFieldvalues_id(fieldvalues_id);
	    	ng.setIsleaf(isleaf);
	    	ng.setNaviorder(naviorder);
	    	ng.setIsopen(isopen);
	    	ng.setLevel_id(level_id);
	    	ng.setName(name);
	    	ng.setGlobal_id(global_id);
	    	ng.setStarttime(new Date());
	    	
	    	Session session = HibernateUtil.currentSession();
	    	Transaction tx = session.beginTransaction();
           // Criteria crit = session.createCriteria();
	    	
	    	session.save(ng);
			
	    	tx.commit();
	    	HibernateUtil.closeSession();

        } catch( HibernateException ex ) {
        	log.error(ex);
        } catch ( Exception ex2 ){
        	log.error(ex2);
        }
    }    
    
    public void addSubStructure(String action,int naviorder,long fieldvalues_id, boolean isleaf, boolean isopen, long level_id, String name, long main_id){
	    try {
	    	Navisub ng = new Navisub();
	    	ng.setAction(action);
	    	ng.setComment("");
	    	ng.setIcon("");
	    	ng.setNaviorder(naviorder);
	    	ng.setFieldvalues_id(fieldvalues_id);
	    	ng.setIsleaf(isleaf);
	    	ng.setIsopen(isopen);
	    	ng.setLevel_id(level_id);
	    	ng.setName(name);
	    	ng.setMain_id(main_id);
	    	ng.setStarttime(new Date());
	    	
	    	Session session = HibernateUtil.currentSession();
	    	Transaction tx = session.beginTransaction();
           // Criteria crit = session.createCriteria();
	    	
	    	session.save(ng);
			
	    	tx.commit();
	    	HibernateUtil.closeSession();

        } catch( HibernateException ex ) {
        	log.error(ex);
        } catch ( Exception ex2 ){
        	log.error(ex2);
        }
    }    
}
