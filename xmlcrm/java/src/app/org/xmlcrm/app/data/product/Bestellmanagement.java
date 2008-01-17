package org.xmlcrm.app.data.product;

import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.xmlcrm.app.hibernate.beans.shop.transstatus;
import org.xmlcrm.app.hibernate.beans.user.Userwaren;
import org.xmlcrm.app.hibernate.utils.HibernateUtil;
import org.xmlcrm.utils.math.Calender;

public class Bestellmanagement {

	public Bestellmanagement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean checkUserLevel(long user_level) {
		boolean ret = false;
		if (user_level < 2) {
			ret = false;
		} else {
			ret = true;
		}
		return ret;
	}

	public String addWarenkorb(int USER_ID, int ARTICLE_ID, int amount) {
		String result = "Fehler im Bestellvorgang";
		//		if (USER_ID!=0 && ARTICLE_ID!=0){
		//			long thistime = CalenderI.getTimeStampMili();
		//			Userwaren userwaren = new Userwaren();
		//			userwaren.setUSER_ID(USER_ID);	
		//			userwaren.setStarttime(thistime);
		//			userwaren.setUpdatetime(thistime);
		//			userwaren.setARTICLE_ID(ARTICLE_ID);
		//			userwaren.setStatus(1);
		//			userwaren.setMenge(amount);
		//			userwaren.setZAHLUNGS_ID(0);
		//			userwaren.setComment("startTransaction");
		//	        try {   
		//	            Object idf = HibernateUtil.createSession(); 			Session session = HibernateUtil.getSession();
		//	            Transaction tx = session.beginTransaction();
		//	            session.save(userwaren);
		//	            session.flush();   
		//	            session.clear();
		//	            session.refresh(userwaren);
		//	            tx.commit();
		//	            HibernateUtil.closeSession(idf);
		//	            result = "Der Artikel wurde erfolgreich dem Waren hinzugefügt";
		//	        } catch( HibernateException ex ) {
		//	        	result = "Error: "+ex;
		//	        } catch ( Exception ex2 ){
		//	        	result = "Error: "+ex2;
		//	        }
		//		} else {
		//			result = "Um einen Bestellvorgang auszulösen muss mindestens eine USER_ID und eine ARTICLE_ID angegeben werden";
		//		}
		return result;
	}

	public String updateWarenkorb(int WAREN_ID, int status, int ZAHLUNGS_ID,
			int LIEFER_ID, int amount, String comment) {
		String result = "Fehler im Bestellvorgang";
		try {
//			Object idf = HibernateUtil.createSession();
//			Session session = HibernateUtil.getSession();
//			Transaction tx = session.beginTransaction();
//			String hqlUpdate = "update userwaren set updatetime= :updatetime, status= :status, ZAHLUNGS_ID= :ZAHLUNGS_ID, LIEFER_ID= :LIEFER_ID, comment= :comment, menge= :menge where WAREN_ID = :WAREN_ID";
//			int updatedEntities = session.createQuery(hqlUpdate).setLong(
//					"updatetime", CalenderI.getTimeStampMili()).setInteger(
//					"status", status).setInteger("ZAHLUNGS_ID", ZAHLUNGS_ID)
//					.setInteger("LIEFER_ID", LIEFER_ID).setString("comment",
//							comment).setInteger("menge", amount).setInteger(
//							"WAREN_ID", WAREN_ID).executeUpdate();
//			//session.flush();
//
//			tx.commit();
//			HibernateUtil.closeSession(idf);
//			result = "Erfolgreich" + updatedEntities;
		} catch (HibernateException ex) {
			result = "Error: " + ex;
		} catch (Exception ex2) {
			result = "Error: " + ex2;
		}
		return result;
	}

	public String deleteWarenkorbByID(int Waren_ID) {
		String result = "Fehler im Bestellvorgang";
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			String hqlDelete = "delete userwaren where WAREN_ID = :WAREN_ID";
			int deletedEntities = session.createQuery(hqlDelete).setInteger(
					"WAREN_ID", Waren_ID).executeUpdate();
			//session.flush(); 

			tx.commit();
			HibernateUtil.closeSession(idf);
			result = "Erfolgreich" + deletedEntities;
		} catch (HibernateException ex) {
			result = "Error: " + ex;
		} catch (Exception ex2) {
			result = "Error: " + ex2;
		}
		return result;
	}

	public String deleteWarenkorbByUserID(int USER_ID) {
		String result = "Fehler im Bestellvorgang";
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			String hqlDelete = "delete userwaren where USER_ID = :USER_ID";
			int deletedEntities = session.createQuery(hqlDelete).setInteger(
					"USER_ID", USER_ID).executeUpdate();
			//session.flush(); 

			tx.commit();
			HibernateUtil.closeSession(idf);
			result = "Erfolgreich" + deletedEntities;
		} catch (HibernateException ex) {
			result = "Error: " + ex;
		} catch (Exception ex2) {
			result = "Error: " + ex2;
		}
		return result;
	}

	public Userwaren[] getUserwaren(int USER_ID, long user_level) {
		Userwaren userwaren[] = new Userwaren[1];
		if (user_level > 1) {
			//			try {
			//		    	Object idf = HibernateUtil.createSession(); 			Session session = HibernateUtil.getSession();
			//		    	Transaction tx = session.beginTransaction();    
			//		    	Query query = session.createQuery("select c from userwaren as c where c.USER_ID = :USER_ID");
			//				query.setInteger("USER_ID", USER_ID);
			//				int count = query.list().size();
			//				userwaren = new Userwaren[count];
			//				int k = 0;
			//				for (Iterator it2 = query.iterate(); it2.hasNext();) {
			//					userwaren[k] = (Userwaren) it2.next();
			//					k++;
			//				}
			//		    	tx.commit();
			//		    	HibernateUtil.closeSession(idf);
			//		    	for  (int vars=0;vars<userwaren.length;vars++){
			//		    		//userwaren[vars].setZahlungsarten(ResHandler.getZahlungsmanagement().getZahlungsartenByID(userwaren[vars].getZAHLUNGS_ID()));
			//		    		userwaren[vars].setStarttimeDE(CalenderI.getDatumMili(userwaren[vars].getStarttime()));
			//		    		userwaren[vars].setUpdatetimeDE(CalenderI.getDatumMili(userwaren[vars].getUpdatetime()));
			//		    		userwaren[vars].setProducts(ResHandler.getProductmanagement().getProductsByID(userwaren[vars].getARTICLE_ID()));
			//		    		userwaren[vars].setTransstatus(getTransstatus(userwaren[vars].getStatus()));
			//		    		//userwaren[vars].setLieferarten(ResHandler.getLiefermanagement().getLieferartenByID(userwaren[vars].getLIEFER_ID()));
			//		    	}	    	
			//		    } catch( HibernateException ex ) {
			//		    	userwaren[0]=new Userwaren();
			//		    	userwaren[0].setComment("Error: "+ex);	
			//		    } catch ( Exception ex2 ){
			//		    	userwaren[0]=new Userwaren();
			//		    	userwaren[0].setComment("Error: "+ex2);	
			//		    }
		} else {
			userwaren[0] = new Userwaren();
			userwaren[0].setComment("Error: Keine Berechtigung");
		}
		return userwaren;
	}

	public Userwaren getUserwareByID(int WAREN_ID, long user_level) {
		Userwaren userwaren = new Userwaren();
		if (user_level > 1) {
			//			try {
			//		    	Object idf = HibernateUtil.createSession(); 			Session session = HibernateUtil.getSession();
			//		    	Transaction tx = session.beginTransaction();    
			//		    	Query query = session.createQuery("select c from userwaren as c where c.WAREN_ID = :WAREN_ID");
			//				query.setInteger("WAREN_ID", WAREN_ID);
			//				for (Iterator it2 = query.iterate(); it2.hasNext();) {
			//					userwaren = (Userwaren) it2.next();
			//				}
			//		    	tx.commit();
			//		    	HibernateUtil.closeSession(idf);
			//		    	userwaren.setZahlungsarten(ResHandler.getZahlungsmanagement().getZahlungsartenByID(userwaren.getZAHLUNGS_ID()));
			//	    		userwaren.setStarttimeDE(CalenderI.getDatumMili(userwaren.getStarttime()));
			//	    		userwaren.setUpdatetimeDE(CalenderI.getDatumMili(userwaren.getUpdatetime()));
			//	    		userwaren.setProducts(ResHandler.getProductmanagement().getProductsByID(userwaren.getARTICLE_ID()));
			//	    		userwaren.setTransstatus(getTransstatus(userwaren.getStatus()));
			//	    		userwaren.setLieferarten(ResHandler.getLiefermanagement().getLieferartenByID(userwaren.getLIEFER_ID()));
			//		    } catch( HibernateException ex ) {
			//		    	userwaren.setComment("Error: "+ex);	
			//		    } catch ( Exception ex2 ){
			//		    	userwaren.setComment("Error: "+ex2);	
			//		    }
		} else {
			userwaren.setComment("Error: Keine Berechtigung");
		}
		return userwaren;
	}

	public int countUserwaren(int USER_ID) {
		int userwaren = 0;
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session
					.createQuery("select c from userwaren as c where c.USER_ID = :USER_ID");
			query.setInteger("USER_ID", USER_ID);
			int count = query.list().size();
			userwaren = count;
			tx.commit();
			HibernateUtil.closeSession(idf);
		} catch (HibernateException ex) {
			System.out.println("Error: "+ex);
		} catch (Exception ex2) {
			System.out.println("Error: " +ex2);
		}
		return userwaren;
	}

	public int countUserStatus(int USER_ID, int status) {
		int userwaren = 0;
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session
					.createQuery("select c from userwaren as c where c.USER_ID = :USER_ID AND c.status = :status");
			query.setInteger("USER_ID", USER_ID);
			query.setInteger("status", status);
			int count = query.list().size();
			userwaren = count;
			tx.commit();
			HibernateUtil.closeSession(idf);
		} catch (HibernateException ex) {
			System.out.println("Error: "+ex);
		} catch (Exception ex2) {
			System.out.println("Error: " +ex2);
		}
		return userwaren;
	}

	private transstatus getTransstatus(int STATUS_ID) {
		transstatus transstatus = new transstatus();
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session
					.createQuery("select c from transstatus as c where c.STATUS_ID = :STATUS_ID");
			query.setInteger("STATUS_ID", STATUS_ID);
			for (Iterator it2 = query.iterate(); it2.hasNext();) {
				transstatus = (transstatus) it2.next();
			}
			tx.commit();
			HibernateUtil.closeSession(idf);
		} catch (HibernateException ex) {
			System.out.println("Error: "+ex);
		} catch (Exception ex2) {
			System.out.println("Error: " +ex2);
		}
		return transstatus;
	}
}
