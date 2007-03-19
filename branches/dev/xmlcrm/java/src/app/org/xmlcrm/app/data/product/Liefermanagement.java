package org.xmlcrm.app.data.product;

import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.xmlcrm.app.hibernate.beans.shop.lieferarten;
import org.xmlcrm.app.hibernate.utils.HibernateUtil;
import org.xmlcrm.app.remote.ResHandler;
import org.xmlcrm.utils.math.Calender;

public class Liefermanagement {
	private ResHandler ResHandler;
	private Calender CalenderI;
	public Liefermanagement(ResHandler handler) {
		super();
		// TODO Auto-generated constructor stub
		ResHandler = handler;
		CalenderI = new Calender();
	}
	public lieferarten getLieferartenByID(int LIEFER_ID){
		lieferarten lieferarten = new lieferarten();
		try {
	    	Session session = HibernateUtil.currentSession();
	    	Transaction tx = session.beginTransaction();    
	    	Query query = session.createQuery("select c from lieferarten as c where c.LIEFER_ID = :LIEFER_ID");
			query.setInteger("LIEFER_ID", LIEFER_ID);
			for (Iterator it2 = query.iterate(); it2.hasNext();) {
				lieferarten = (lieferarten) it2.next();
			}
	    	tx.commit();
	    	HibernateUtil.closeSession();
	    	lieferarten.setStarttimeDE(CalenderI.getDatumMili(lieferarten.getStarttime()));
	    	lieferarten.setUpdatetimeDE(CalenderI.getDatumMili(lieferarten.getUpdatetime()));
	    } catch( HibernateException ex ) {
	    	lieferarten.setComment("Error: "+ex);	
	    } catch ( Exception ex2 ){
	    	lieferarten.setComment("Error: "+ex2);	
	    }
		return lieferarten;
	}	
	public lieferarten[] getAllLieferarten(long User_LEVEL){
		lieferarten lieferarten[] = new lieferarten[1];
		if (User_LEVEL > 1){
			try {
		    	Session session = HibernateUtil.currentSession();
		    	Transaction tx = session.beginTransaction();    
		    	Query query = session.createQuery("select c from lieferarten as c where c.freigeschalten = :freigeschalten");
				query.setInteger("freigeschalten", 1);
				int count = query.list().size();
				lieferarten = new lieferarten[count];
				int k=0;
				for (Iterator it2 = query.iterate(); it2.hasNext();) {
					lieferarten[k] = (lieferarten) it2.next();
					k++;
				}
		    	tx.commit();
		    	HibernateUtil.closeSession();
		    	for  (int vars=0;vars<lieferarten.length;vars++){
		    		lieferarten[vars].setStarttimeDE(CalenderI.getDatumMili(lieferarten[vars].getStarttime()));
		    		lieferarten[vars].setUpdatetimeDE(CalenderI.getDatumMili(lieferarten[vars].getUpdatetime()));
		    	}
		    } catch( HibernateException ex ) {
		    	lieferarten[0]= new lieferarten();
		    	lieferarten[0].setComment("Error: "+ex);	
		    } catch ( Exception ex2 ){
		    	lieferarten[0]= new lieferarten();
		    	lieferarten[0].setComment("Error: "+ex2);	
		    }
		} else {
			lieferarten[0]= new lieferarten();
			lieferarten[0].setComment("Permission denied");	
		}
		return lieferarten;
	}	
}
