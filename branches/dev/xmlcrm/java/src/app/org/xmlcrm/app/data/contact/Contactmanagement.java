package org.xmlcrm.app.data.contact;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.*;
import org.hibernate.*;
import java.util.*;

import org.xmlcrm.app.data.user.Groupmanagement;
import org.xmlcrm.app.hibernate.beans.contact.congroups;
import org.xmlcrm.app.hibernate.beans.contact.Contactgroups;
import org.xmlcrm.app.hibernate.beans.contact.Contacts;
import org.xmlcrm.app.hibernate.beans.user.Users;
import org.xmlcrm.app.hibernate.utils.HibernateUtil;
import org.xmlcrm.utils.math.Calender;

import org.xmlcrm.app.data.user.Emailmanagement;

public class Contactmanagement {

	private static final Log log = LogFactory.getLog(Contactmanagement.class);

	private static Contactmanagement instance;

	private Contactmanagement() {
	}

	public static synchronized Contactmanagement getInstance() {
		if (instance == null) {
			instance = new Contactmanagement();
		}
		return instance;
	}

	/**
	 * 
	 * @param USER_ID
	 * @param user_level
	 * @return
	 */
	public Contacts[] getContactsByUser(int USER_ID, long user_level, int maxRes) {
		Contacts contacts[] = new Contacts[1];
		if (user_level > 1) {
			try {
				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				Query query = session
						.createQuery("select c from contacts as c where c.USER_ID = :USER_ID");
				query.setInteger("USER_ID", USER_ID);
				query.setMaxResults(maxRes);
				int count = query.list().size();
				contacts = new Contacts[count];
				int k = 0;
				for (Iterator it2 = query.iterate(); it2.hasNext();) {
					contacts[k] = (Contacts) it2.next();
					k++;
				}
				tx.commit();
				HibernateUtil.closeSession(idf);
			} catch (HibernateException ex) {
				log.error(ex);
			} catch (Exception ex2) {
				log.error(ex2);
			}
		} else {
			contacts[0] = new Contacts();
			contacts[0].setComment("Error: Keine Berechtigung");
		}
		return contacts;
	}

	public Contacts[] searchContactsByUser(int USER_ID, long user_level,
			String searchstring, String searchcriteria, int searchMax, int start) {
		Contacts contacts[] = new Contacts[1];
		if (user_level > 1) {
			try {
				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Criteria crit = session.createCriteria(Contacts.class);
				crit.add(Restrictions.ilike(searchcriteria, "%" + searchstring
						+ "%"));
				crit.setMaxResults(searchMax);
				crit.add(Restrictions.eq("USER_ID", new Integer(USER_ID)));
				crit.setFirstResult(start);
				List contactsZ = crit.list();
				contacts = new Contacts[crit.list().size()];
				int k = 0;
				for (Iterator it = contactsZ.iterator(); it.hasNext();) {
					contacts[k] = (Contacts) it.next();
					k++;
				}
				HibernateUtil.closeSession(idf);
			} catch (HibernateException ex) {
				log.error(ex);
			} catch (Exception ex2) {
				log.error(ex2);
			}
		} else {
			contacts[0] = new Contacts();
			contacts[0].setComment("Error: Keine Berechtigung");
		}
		return contacts;
	}

	public Contacts[] searchContact(long user_level, String searchstring,
			String searchcriteria, int searchMax, int start) {
		Contacts contacts[] = new Contacts[1];
		if (user_level > 2) {
			try {
				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Criteria crit = session.createCriteria(Contacts.class);
				crit.add(Restrictions.ilike(searchcriteria, "%" + searchstring
						+ "%"));
				crit.setMaxResults(searchMax);
				crit.setFirstResult(start);
				List contactsZ = crit.list();
				contacts = new Contacts[crit.list().size()];
				int k = 0;
				for (Iterator it = contactsZ.iterator(); it.hasNext();) {
					contacts[k] = (Contacts) it.next();
					k++;
				}
				HibernateUtil.closeSession(idf);
			} catch (HibernateException ex) {
				log.error(ex);
			} catch (Exception ex2) {
				log.error(ex2);
			}
		} else {
			contacts[0] = new Contacts();
			contacts[0].setFirstname("Error: No USER_ID given");
		}
		return contacts;
	}

	public Contacts searchContactByID(int CONTACT_ID, String searchstring,
			String searchcriteria, int searchMax, int start) {
		Contacts contacts = new Contacts();
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Criteria crit = session.createCriteria(Contacts.class);
			crit.add(Restrictions.ilike(searchcriteria, "%" + searchstring
					+ "%"));
			crit.add(Restrictions.eq("CONTACT_ID", new Integer(CONTACT_ID)));
			crit.setMaxResults(searchMax);
			crit.setFirstResult(start);
			List contactsZ = crit.list();
			for (Iterator it = contactsZ.iterator(); it.hasNext();) {
				contacts = (Contacts) it.next();
			}
			HibernateUtil.closeSession(idf);
		} catch (HibernateException ex) {
			log.error(ex);
		} catch (Exception ex2) {
			log.error(ex2);
		}
		return contacts;
	}

	/**
	 * 
	 * @param CONTACT_ID
	 * @param user_level
	 * @return
	 */
	public Contacts getContactsByID(int CONTACT_ID, long user_level) {
		Contacts contacts = new Contacts();
		if (user_level > 1) {
			try {
				//		    	Object idf = HibernateUtil.createSession(); 			Session session = HibernateUtil.getSession();
				//		    	Transaction tx = session.beginTransaction();    
				//		    	Query query = session.createQuery("select c from contacts as c where c.CONTACT_ID = :CONTACT_ID");
				//				query.setInteger("CONTACT_ID", CONTACT_ID);
				//				for (Iterator it2 = query.iterate(); it2.hasNext();) {
				//					contacts = (Contacts) it2.next();
				//				}
				//		    	tx.commit();
				//		    	HibernateUtil.closeSession(idf);
				//		    	contacts.setStarttimeDE(Calender.getInstance.getDatumMili(contacts.getStarttime()));
				//	    		contacts.setUpdatetimeDE(Calender.getInstance.getDatumMili(contacts.getUpdatetime()));
				//	    		contacts.setEmails(ResHandler.getEmailmanagement().getemailsCon(contacts.getCONTACT_ID()));	 
				//	    		contacts.setContactgroups(getGroupByContact(contacts.getCONTACT_ID()));
				//                contacts.setContactfreigabe(ResHandler.getFreigabemanagement().getFreigabeDecrByID(contacts.getFREIGABE_ID()));
				//                contacts.setConstyle(1);
			} catch (HibernateException ex) {
				log.error(ex);
			} catch (Exception ex2) {
				log.error(ex2);
			}
		} else {
			contacts.setComment("Error: Keine Berechtigung");
		}
		return contacts;
	}

	public String updateContact(long user_level, int USER_ID, int CONTACT_ID,
			String firstname, String lastname, String adress, String zip,
			String town, String state, String comment, String telefon,
			String fax, String mobil, String title, int EMailID, String email,
			int FREIGABE_ID) {
		String res = "Updating Contact";
		if (user_level > 1) {
			try {
				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				String hqlUpdate = "update contacts set firstname= :firstname, lastname = :lastname, updatetime = :updatetime, age = :age"
						+ ", adress = :adress, zip = :zip, town = :town, state = :state, comment = :comment, telefon = :telefon, fax = :fax"
						+ ", mobil = :mobil, title = :title, FREIGABE_ID = :FREIGABE_ID "
						+ "where CONTACT_ID= :CONTACT_ID";
				int updatedEntities = session.createQuery(hqlUpdate).setString(
						"firstname", firstname).setString("lastname", lastname)
						.setLong("updatetime",new Long(-1))
						.setString("adress", adress).setString("zip", zip)
						.setString("town", town).setString("state", state)
						.setString("comment", comment).setString("telefon",
								telefon).setString("fax", fax).setString(
								"mobil", mobil).setString("title", title)
						.setInteger("FREIGABE_ID", FREIGABE_ID).setInteger(
								"CONTACT_ID", CONTACT_ID).executeUpdate();
				res = "Success" + updatedEntities;
				tx.commit();
				HibernateUtil.closeSession(idf);
				res = Emailmanagement.getInstance().updateContactEmail(EMailID,
						CONTACT_ID, email);
			} catch (HibernateException ex) {
				log.error(ex);
			} catch (Exception ex2) {
				log.error(ex2);
			}
		} else {
			res = "Error: Keine Berechtigung";
		}
		return res;
	}

	public String addContact(long user_level, int USER_ID, String firstname,
			String lastname, String adress, String zip, String town,
			String state, String comment, String telefon, String fax,
			String mobil, String title, String email, int FREIGABE_ID) {
		String res = "Add Contact";
		if (user_level > 1) {
			//			Contacts contacts = new Contacts();
			//			contacts.setFirstname(firstname);
			//			contacts.setLastname(lastname);
			//			contacts.setAdress(adress);
			//			contacts.setZip(zip);
			//			contacts.setTown(town);
			//			contacts.setState(state);
			//			contacts.setComment(comment);
			//			contacts.setTelefon(telefon);
			//			contacts.setFax(fax);
			//			contacts.setMobil(mobil);
			//			contacts.setUSER_ID(USER_ID);
			//			contacts.setTitle(title);
			//			contacts.setFREIGABE_ID(FREIGABE_ID);
			//			contacts.setStarttime(Calender.getInstance.getTimeStampMili());
			//			contacts.setUpdatetime(Calender.getInstance.getTimeStampMili());
			try {
				//	            Object idf = HibernateUtil.createSession(); 			Session session = HibernateUtil.getSession();
				//	            Transaction tx = session.beginTransaction();        
				//	            session.save(contacts);
				//	            session.flush();   
				//	            session.clear();
				//	            session.refresh(contacts);
				//	            res = "Success";
				//	            tx.commit();
				//	            HibernateUtil.closeSession(idf);
				//	            int Contact_iD = contacts.getCONTACT_ID();
				//	            res = ResHandler.getEmailmanagement().addEmailCon(email,Contact_iD);  
			} catch (HibernateException ex) {
				log.error(ex);
			} catch (Exception ex2) {
				log.error(ex2);
			}
		} else {
			res = "Error: Keine Berechtigung";
		}
		return res;
	}

	/**
	 * 
	 * @param USER_ID
	 * @return
	 */
	public String deleteUserContact(int USER_ID) {
		String result = "Fehler im Contactmanagement deleteContact";
		try {
			//			Object idf = HibernateUtil.createSession(); 			Session session = HibernateUtil.getSession();
			//	    	Transaction tx = session.beginTransaction();    
			//	    	Query query = session.createQuery("select c from contacts as c where c.USER_ID = :USER_ID");
			//			query.setInteger("USER_ID", USER_ID);
			//			int count = query.list().size();
			//			Contacts contacts[] = new Contacts[count];
			//			int k = 0;
			//			for (Iterator it2 = query.iterate(); it2.hasNext();) {
			//				contacts[k] = (Contacts) it2.next();
			//				k++;
			//			}
			//	    	tx.commit();
			//	    	HibernateUtil.closeSession(idf);	
			//            result = "Der Contact wurde erfolgreich gelöscht";
			//            for  (int vars=0;vars<contacts.length;vars++){
			//            	deleteContact(contacts[vars].getCONTACT_ID(),USER_ID,2);
			//	    	}
		} catch (HibernateException ex) {
			log.error(ex);
		} catch (Exception ex2) {
			log.error(ex2);
		}
		return result;
	}

	/**
	 * 
	 * @param CONTACT_ID
	 * @return
	 */
	public String deleteContact(long user_level, int CONTACT_ID, int USER_ID) {
		String result = "Fehler im Contactmanagement deleteContact";
		if (user_level > 1) {
			//    		try {
			//    	    	Object idf = HibernateUtil.createSession(); 			Session session = HibernateUtil.getSession();
			//    	    	Transaction tx = session.beginTransaction();    
			//    	    	String hqlDelete = "delete contacts where CONTACT_ID = :CONTACT_ID AND USER_ID = :USER_ID";
			//    	        int deletedEntities = session.createQuery( hqlDelete )
			//    	                            .setInteger( "CONTACT_ID", CONTACT_ID )
			//                                    .setInteger( "USER_ID", USER_ID )
			//    	                            .executeUpdate();
			//                tx.commit();
			//                HibernateUtil.closeSession(idf);
			//                result = "Der Contact wurde erfolgreich gelöscht"+deletedEntities;
			//                result = deleteContactFromAllGroups(CONTACT_ID);
			//    		} catch( HibernateException ex ) {
			//            	result += "Error: "+ex;
			//            } catch ( Exception ex2 ){
			//            	result += "Error: "+ex2;
			//            }
		} else {
			result = "Permission denied";
		}
		return result;
	}

	/**
	 * -------------------------------------------------------------------
	 * GROUP-MANAGEMENT
	 */

	public congroups[] getContactGroups(long USER_ID, long user_level) {
		congroups congroups[] = new congroups[1];
		if (user_level > 1) {
			try {
				//		    	Object idf = HibernateUtil.createSession(); 			Session session = HibernateUtil.getSession();
				//		    	Transaction tx = session.beginTransaction();    
				//		    	Query query = session.createQuery("select c from congroups as c where c.USER_ID = :USER_ID");
				//				query.setInteger("USER_ID", USER_ID);
				//				int count = query.list().size();
				//				congroups = new congroups[count];
				//				int k = 0;
				//				for (Iterator it2 = query.iterate(); it2.hasNext();) {
				//					congroups[k] = (congroups) it2.next();
				//					k++;
				//				}
				//		    	tx.commit();
				//		    	HibernateUtil.closeSession(idf);	
				//		    	for  (int vars=0;vars<congroups.length;vars++){
				//			    	congroups[vars].setStarttimeDE(Calender.getInstance.getDatumMili(congroups[vars].getStarttime()));
				//			    	congroups[vars].setUpdatetimeDE(Calender.getInstance.getDatumMili(congroups[vars].getUpdatetime()));		    		
				//		    	}	
			} catch (HibernateException ex) {
				log.error(ex);
			} catch (Exception ex2) {
				log.error(ex2);
			}
		} else {
			congroups[0] = new congroups();
			congroups[0].setComment("Error: Keine Berechtigung");
		}
		return congroups;
	}

	public congroups[] getContactGroupsFull(int USER_ID, int user_level) {
		congroups congroups[] = new congroups[1];
		if (user_level > 1) {
			try {
				//		    	Object idf = HibernateUtil.createSession(); 			Session session = HibernateUtil.getSession();
				//		    	Transaction tx = session.beginTransaction();    
				//		    	Query query = session.createQuery("select c from congroups as c where c.USER_ID = :USER_ID");
				//				query.setInteger("USER_ID", USER_ID);
				//				int count = query.list().size();
				//				congroups = new congroups[count];
				//				int k = 0;
				//				for (Iterator it2 = query.iterate(); it2.hasNext();) {
				//					congroups[k] = (congroups) it2.next();
				//					k++;
				//				}
				//		    	tx.commit();
				//		    	HibernateUtil.closeSession(idf);
				//		    	
				//		    	for  (int vars=0;vars<congroups.length;vars++){
				//		    		congroups[vars].setContactsgroups(getContactsByGroup(congroups[vars].getCONGROUP_ID()));
				//			    	congroups[vars].setStarttimeDE(Calender.getInstance.getDatumMili(congroups[vars].getStarttime()));
				//			    	congroups[vars].setUpdatetimeDE(Calender.getInstance.getDatumMili(congroups[vars].getUpdatetime()));		    		
				//		    	}		    	
			} catch (HibernateException ex) {
				log.error(ex);
			} catch (Exception ex2) {
				log.error(ex2);
			}
		} else {
			congroups[0] = new congroups();
			congroups[0].setComment("Error: Keine Berechtigung");
		}
		return congroups;
	}

	public congroups searchContactGroupsByID(long user_level, int USER_ID,
			int CONGROUP_ID, String searchstring, String searchcriteria,
			int searchMax, int start) {
		congroups congroups = new congroups();
		if (user_level > 1) {
			try {
				//		    	Object idf = HibernateUtil.createSession(); 			Session session = HibernateUtil.getSession();
				//		    	Transaction tx = session.beginTransaction();    
				//		    	Query query = session.createQuery("select c from congroups as c where c.CONGROUP_ID = :CONGROUP_ID AND c.USER_ID = :USER_ID");
				//				query.setInteger("CONGROUP_ID", CONGROUP_ID);
				//				query.setInteger("USER_ID", USER_ID);
				//				for (Iterator it2 = query.iterate(); it2.hasNext();) {
				//					congroups = (congroups) it2.next();
				//				}
				//		    	tx.commit();
				//		    	HibernateUtil.closeSession(idf);
				//		    	congroups.setStarttimeDE(Calender.getInstance.getDatumMili(congroups.getStarttime()));
				//		    	congroups.setUpdatetimeDE(Calender.getInstance.getDatumMili(congroups.getUpdatetime()));
				//	    		if (congroups.getCONGROUP_ID()!=0){
				//	    			congroups.setContactsgroups(searchContactsByGroup(congroups.getCONGROUP_ID(),searchstring,searchcriteria,searchMax,start));
				//	    		}
			} catch (HibernateException ex) {
				log.error(ex);
			} catch (Exception ex2) {
				log.error(ex2);
			}
		} else {
			congroups.setComment("Error: Keine Berechtigung");
		}
		return congroups;
	}

	private Contactgroups[] searchContactsByGroup(int CONGROUP_ID,
			String searchstring, String searchcriteria, int searchMax, int start) {
		Contactgroups contactgroups[] = new Contactgroups[1];
		try {
			//	    	Object idf = HibernateUtil.createSession(); 			Session session = HibernateUtil.getSession();
			//	    	Transaction tx = session.beginTransaction();    
			//	    	Query query = session.createQuery("select c from contactgroups as c where c.CONGROUP_ID = :CONGROUP_ID");
			//			query.setInteger("CONGROUP_ID", CONGROUP_ID);
			//			int count = query.list().size();
			//			Contactgroups contactgroupsTemp[] = new Contactgroups[count];
			//			int k = 0;
			//			for (Iterator it2 = query.iterate(); it2.hasNext();) {
			//				contactgroupsTemp[k] = (Contactgroups) it2.next();
			//				k++;
			//			}
			//	    	tx.commit();
			//	    	HibernateUtil.closeSession(idf);	
			//	    	ArrayList AList = new ArrayList();
			//	    	for  (int vars=0;vars<contactgroups.length;vars++){
			//	    		if (contactgroupsTemp[vars].getCONTACT_ID()!=0){
			//	    			Contacts contacts = searchContactByID(contactgroupsTemp[vars].getCONTACT_ID(),searchstring,searchcriteria,searchMax,start);
			//	    			if (contacts.getStarttime()>1000){
			//		    			contactgroupsTemp[vars].setContacts(contacts);
			//		    			AList.add(contactgroups[vars]);
			//	    			}
			//	    			
			//	    		} else if(contactgroups[vars].getCONUSER_ID()!=0) {
			//		    			users users = ResHandler.getUsermanagement().searchUserByID(contactgroupsTemp[vars].getCONUSER_ID(),searchstring,searchcriteria,searchMax,start);
			//		    			if (users.getLogin().length()>2){
			//			    			Contacts contacts = new Contacts();
			//			    			contacts.setCONTACT_ID(users.getUSER_ID());
			//			    			contacts.setFirstname(users.getFirstname());
			//			    			contacts.setLastname(users.getLastname());
			//			    			contacts.setConstyle(2);
			//			    			contacts.setAdress(users.getAdress());
			//			    			contacts.setEmails(users.getEmails());
			//			    			contactgroups[vars].setContacts(contacts);
			//			    			AList.add(contactgroups[vars]);
			//		    			}
			//		    	}	    		
			//	    	}
			//	    	contactgroups = new Contactgroups[AList.size()];
			//	    	k = 0;
			//			for (Iterator it2 = AList.iterator();it2.hasNext();) {
			//				contactgroups[k] = (Contactgroups) it2.next();
			//				k++;
			//			}
		} catch (HibernateException ex) {
			log.error(ex);
		} catch (Exception ex2) {
			log.error(ex2);
		}
		return contactgroups;
	}

	public congroups getContactGroupsByID(long user_level, int CONGROUP_ID,
			int USER_ID, int val) {
		congroups congroups = new congroups();
		System.out.println("user_level: " + user_level);
		if (user_level > 1) {
			try {
				//		    	Object idf = HibernateUtil.createSession(); 			Session session = HibernateUtil.getSession();
				//		    	Transaction tx = session.beginTransaction();    
				//		    	Query query = session.createQuery("select c from congroups as c where c.CONGROUP_ID = :CONGROUP_ID AND c.USER_ID = :USER_ID");
				//				query.setInteger("CONGROUP_ID", CONGROUP_ID);
				//				query.setInteger("USER_ID", USER_ID);
				//				for (Iterator it2 = query.iterate(); it2.hasNext();) {
				//					congroups = (congroups) it2.next();
				//				}
				//		    	tx.commit();
				//		    	HibernateUtil.closeSession(idf);
				//		    	
				//		    	congroups.setStarttimeDE(Calender.getInstance.getDatumMili(congroups.getStarttime()));
				//		    	congroups.setUpdatetimeDE(CalenderI.getDatumMili(congroups.getUpdatetime()));
				//		    	if (val==1){
				//		    		if (congroups.getCONGROUP_ID()!=0){
				//		    			congroups.setContactsgroups(getContactsByGroup(congroups.getCONGROUP_ID()));
				//		    		}
				//		    	}
			} catch (HibernateException ex) {
				log.error(ex);
			} catch (Exception ex2) {
				log.error(ex2);
			}
		} else {
			congroups.setComment("Error: Keine Berechtigung");
		}
		return congroups;
	}

	private congroups getGroupsByContact(int CONGROUP_ID) {
		congroups congroups = new congroups();
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session
					.createQuery("select c from congroups as c where c.CONGROUP_ID = :CONGROUP_ID");
			query.setInteger("CONGROUP_ID", CONGROUP_ID);
			for (Iterator it2 = query.iterate(); it2.hasNext();) {
				congroups = (congroups) it2.next();
			}
			tx.commit();
			HibernateUtil.closeSession(idf);
		} catch (HibernateException ex) {
			log.error(ex);
		} catch (Exception ex2) {
			log.error(ex2);
		}
		return congroups;
	}

	public String addContactGroup(long user_level, long USER_ID, String name,
			int freigabe, String description, String comment) {
		String result = "Fehler im Contactmanagement Add";
		if (user_level > 1) {
			congroups congroups = new congroups();
			congroups.setName(name);
			//			congroups.setUSER_ID(USER_ID);
			congroups.setFreigabe(freigabe);
			congroups.setStarttime(new Long(-1));
			congroups.setUpdatetime(new Long(-1));
			congroups.setDescription(description);
			congroups.setComment(comment);
			try {
				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				session.save(congroups);
				session.flush();
				session.clear();
				session.refresh(congroups);
				tx.commit();
				HibernateUtil.closeSession(idf);
				result = "Die Contactgroup wurde erfolgreich gespeichert";
			} catch (HibernateException ex) {
				log.error(ex);
			} catch (Exception ex2) {
				log.error(ex2);
			}
		} else {
			result = "Permission denied";
		}
		return result;
	}

	public String updateConGroup(long user_level, int USER_ID, int CONGROUP_ID,
			String name, int freigabe, String description, String comment) {
		String result = "Fehler im Contactmanagement Update";
		if (user_level > 1) {
			try {
				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				String hqlUpdate = "update congroups set name= :name, freigabe= :freigabe, description= :description, updatetime= :updatetime, comment= :comment where CONGROUP_ID = :CONGROUP_ID AND USER_ID = :USER_ID";
				int updatedEntities = session.createQuery(hqlUpdate).setString(
						"name", name).setInteger("freigabe", freigabe)
						.setString("description", description).setLong(
								"updatetime",
								new Long(-1))
						.setString("comment", comment).setInteger(
								"CONGROUP_ID", CONGROUP_ID).setInteger(
								"USER_ID", USER_ID).executeUpdate();
				//session.flush();

				tx.commit();
				HibernateUtil.closeSession(idf);
				result = "Erfolgreich" + updatedEntities;
			} catch (HibernateException ex) {
				result = "Error: " + ex;
			} catch (Exception ex2) {
				result = "Error: " + ex2;
			}
		} else {
			result = "Permission denied";
		}
		return result;
	}

	public String deleteContactgroup(int CONGROUP_ID, long user_level,
			int USER_ID) {
		String result = "Fehler im Contactmanagement Delete";
		if (user_level > 1) {
			try {
				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				String hqlDelete = "delete congroups where CONGROUP_ID = :CONGROUP_ID AND USER_ID = :USER_ID";
				int deletedEntities = session.createQuery(hqlDelete)
						.setInteger("CONGROUP_ID", CONGROUP_ID).setInteger(
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
		} else {
			result = "Permission denied";
		}
		return result;
	}

	public String deleteContactUsergroups(int USER_ID) {
		String result = "Fehler im Contactmanagement Delete";
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			String hqlDelete = "delete congroups where USER_ID = :USER_ID";
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

	private Contactgroups[] getContactsByGroup(int CONGROUP_ID) {
		Contactgroups contactgroups[] = new Contactgroups[1];
		try {
			//	    	Object idf = HibernateUtil.createSession(); 			Session session = HibernateUtil.getSession();
			//	    	Transaction tx = session.beginTransaction();    
			//	    	Query query = session.createQuery("select c from contactgroups as c where c.CONGROUP_ID = :CONGROUP_ID");
			//			query.setInteger("CONGROUP_ID", CONGROUP_ID);
			//			int count = query.list().size();
			//			contactgroups = new Contactgroups[count];
			//			int k = 0;
			//			for (Iterator it2 = query.iterate(); it2.hasNext();) {
			//				contactgroups[k] = (Contactgroups) it2.next();
			//				k++;
			//			}
			//	    	tx.commit();
			//	    	HibernateUtil.closeSession(idf);	
			//	    	for  (int vars=0;vars<contactgroups.length;vars++){
			//	    		if (contactgroups[vars].getCONTACT_ID()!=0){
			//	    		contactgroups[vars].setContacts(getContactsByID(contactgroups[vars].getCONTACT_ID(),2));
			//	    		} else if(contactgroups[vars].getCONUSER_ID()!=0) {
			//		    			users users = ResHandler.getUsermanagement().getUser(contactgroups[vars].getCONUSER_ID());
			//		    			Contacts contacts = new Contacts();
			//		    			contacts.setCONTACT_ID(users.getUSER_ID());
			//		    			contacts.setFirstname(users.getFirstname());
			//		    			contacts.setLastname(users.getLastname());
			//		    			contacts.setConstyle(2);
			//		    			contacts.setAdress(users.getAdress());
			//		    			contacts.setEmails(users.getEmails());
			//		    			contactgroups[vars].setContacts(contacts);
			//		    	}	    		
			//	    	}
		} catch (HibernateException ex) {
			contactgroups[0] = new Contactgroups();
			contactgroups[0].setComment("Error: "+ex);
		} catch (Exception ex2) {
			contactgroups[0] = new Contactgroups();
			contactgroups[0].setComment("Error: " +ex2);
		}
		return contactgroups;
	}

	private Contactgroups[] getGroupByContact(int CONTACT_ID) {
		Contactgroups contactgroups[] = new Contactgroups[1];
		try {
			//	    	Object idf = HibernateUtil.createSession(); 			Session session = HibernateUtil.getSession();
			//	    	Transaction tx = session.beginTransaction();    
			//	    	Query query = session.createQuery("select c from contactgroups as c where c.CONTACT_ID = :CONTACT_ID");
			//			query.setInteger("CONTACT_ID", CONTACT_ID);
			//			int count = query.list().size();
			//			contactgroups = new Contactgroups[count];
			//			int k = 0;
			//			for (Iterator it2 = query.iterate(); it2.hasNext();) {
			//				contactgroups[k] = (Contactgroups) it2.next();
			//				k++;
			//			}
			//	    	tx.commit();
			//	    	HibernateUtil.closeSession(idf);	
			//	    	for  (int vars=0;vars<contactgroups.length;vars++){
			//	    		getGroupsByContact(contactgroups[vars].getCONGROUP_ID());
			//	    	}
		} catch (HibernateException ex) {
			contactgroups[0] = new Contactgroups();
			contactgroups[0].setComment("Error: "+ex);
		} catch (Exception ex2) {
			contactgroups[0] = new Contactgroups();
			contactgroups[0].setComment("Error: " +ex2);
		}
		return contactgroups;
	}

	private boolean getCheckDuplicate(int CONTACT_ID, int CONUSER_ID,
			int CONGROUP_ID) {
		boolean ret = true;
		try {
			//	    	Object idf = HibernateUtil.createSession(); 			Session session = HibernateUtil.getSession();
			//	    	Transaction tx = session.beginTransaction(); 
			//	    	Query query = session.createQuery("select c from contactgroups as c where c.CONTACT_ID = :CONTACT_ID AND c.CONUSER_ID = :CONUSER_ID");
			//			query.setInteger("CONTACT_ID", CONTACT_ID);
			//			query.setInteger("CONUSER_ID", CONUSER_ID);
			//			int count = query.list().size();
			//			Contactgroups contactgroups[] = new Contactgroups[count];
			//			int k = 0;
			//			for (Iterator it2 = query.iterate(); it2.hasNext();) {
			//				contactgroups[k] = (Contactgroups) it2.next();
			//				k++;
			//			}
			//	    	tx.commit();
			//	    	HibernateUtil.closeSession(idf);	
			//	    	for  (int vars=0;vars<contactgroups.length;vars++){
			//	    		if (CONGROUP_ID == contactgroups[vars].getCONGROUP_ID()){
			//	    			ret = false;
			//	    			break;
			//	    		}
			//	    	}

		} catch (HibernateException ex) {
			System.out.println("Error: "+ex);
		} catch (Exception ex2) {
			System.out.println("Error: " +ex2);
		}
		return ret;
	}

	public String addContactToGroup(long user_level, int CONTACT_ID,
			int CONUSER_ID, int CONGROUP_ID, String comment) {
		String result = "Fehler im Contactmanagement addContactToGroup";
		if (user_level > 1) {

			boolean NewFlag = getCheckDuplicate(CONTACT_ID, CONUSER_ID,
					CONGROUP_ID);

			//            if (NewFlag){
			//    			Contactgroups contactgroups = new Contactgroups();
			//    			contactgroups.setCONTACT_ID(CONTACT_ID);
			//    			contactgroups.setCONGROUP_ID(CONGROUP_ID);
			//    			contactgroups.setCONUSER_ID(CONUSER_ID);
			//    			contactgroups.setStarttime(CalenderI.getTimeStampMili());
			//    			contactgroups.setUpdatetime(CalenderI.getTimeStampMili());
			//    			contactgroups.setComment(comment);
			//    			try {
			//    		    	Object idf = HibernateUtil.createSession(); 			Session session = HibernateUtil.getSession();
			//    		    	Transaction tx = session.beginTransaction();    
			//    		    	session.save(contactgroups);
			//    	            session.flush();   
			//    	            session.clear();
			//    	            session.refresh(contactgroups);
			//    	            tx.commit();
			//    	            HibernateUtil.closeSession(idf);
			//    	            result = "Der Contact wurde erfolgreich der Group hinzugefügt";
			//    			} catch( HibernateException ex ) {
			//    	        	result += "Error: "+ex;
			//    	        } catch ( Exception ex2 ){
			//    	        	result += "Error: "+ex2;
			//    	        }
			//            } else {
			//                result = "Dieser COntact befindet sich schon in dieser Gruppe";
			//            }
		} else {
			result = "Permission denied";
		}
		return result;
	}

	public Contactgroups getContactsByUID(int UID) {
		Contactgroups contactgroups = new Contactgroups();
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session
					.createQuery("select c from contactgroups as c where c.UID = :UID");
			query.setInteger("UID", UID);
			for (Iterator it2 = query.iterate(); it2.hasNext();) {
				contactgroups = (Contactgroups) it2.next();
			}
			tx.commit();
			HibernateUtil.closeSession(idf);
		} catch (HibernateException ex) {
			System.out.println("Error: "+ex);
		} catch (Exception ex2) {
			System.out.println("Error: " +ex2);
		}
		return contactgroups;
	}

	private boolean checkupdate(int UID, int CONGROUP_ID) {
		boolean ret = true;
		try {
			//			Contactgroups contactgroupsOne = getContactsByUID(UID);
			//			int CONTACT_ID = contactgroupsOne.getCONTACT_ID();
			//			int CONUSER_ID = contactgroupsOne.getCONUSER_ID();
			//			ret = getCheckDuplicate(CONTACT_ID, CONUSER_ID,CONGROUP_ID);
			//			
		} catch (HibernateException ex) {
			System.out.println("Error: "+ex);
		} catch (Exception ex2) {
			System.out.println("Error: " +ex2);
		}
		return ret;
	}

	public String updateContactGroup(long user_level, int UID, int CONGROUP_ID,
			String comment) {
		String result = "Fehler im Contactmanagement addContactToGroup";
		try {
			if (user_level > 1) {
				boolean NewFlag = checkupdate(UID, CONGROUP_ID);

				if (NewFlag) {
					Object idf = HibernateUtil.createSession();
					Session session = HibernateUtil.getSession();
					Transaction tx = session.beginTransaction();
					String hqlUpdate = "update contactgroups set CONGROUP_ID= :CONGROUP_ID, updatetime= :updatetime, comment= :comment where UID = :UID";
					int updatedEntities = session.createQuery(hqlUpdate)
							.setInteger("CONGROUP_ID", CONGROUP_ID).setLong(
									"updatetime",
									new Long(-1))
							.setString("comment", comment).setInteger("UID",
									UID).executeUpdate();
					tx.commit();
					HibernateUtil.closeSession(idf);
					result = "Erfolgreich" + updatedEntities;
				} else {
					result = "Dieser COntact befindet sich schon in dieser Gruppe";
				}
			} else {
				result = "Permission denied";
			}
		} catch (HibernateException ex) {
			log.error("[getUserForGroup]",ex);
		} catch (Exception ex2) {
			log.error("[getUserForGroup]",ex2);
		}
		return result;
	}

	public String deleteContactFromGroup(int USER_ID, long user_level,
			int CONTACT_ID, int CONUSER_ID, int CONGROUP_ID) {
		String result = "Fehler im Contactmanagement deleteContactFromGroup";
		if (user_level > 1) {
			congroups congroups = getGroupsByContact(CONGROUP_ID);
			if (USER_ID == congroups.getUSER_ID()) {
				try {
					Object idf = HibernateUtil.createSession();
					Session session = HibernateUtil.getSession();
					Transaction tx = session.beginTransaction();
					String hqlDelete = "delete contactgroups where CONGROUP_ID = :CONGROUP_ID AND CONTACT_ID = :CONTACT_ID AND CONUSER_ID = :CONUSER_ID";
					int deletedEntities = session.createQuery(hqlDelete)
							.setInteger("CONGROUP_ID", CONGROUP_ID).setInteger(
									"CONTACT_ID", CONTACT_ID).setInteger(
									"CONUSER_ID", CONUSER_ID).executeUpdate();
					tx.commit();
					HibernateUtil.closeSession(idf);
					result = "Der Contact wurde erfolgreich von der Group gelöscht"
							+ deletedEntities;
				} catch (HibernateException ex) {
					result += "Error: " + ex;
				} catch (Exception ex2) {
					result += "Error: " + ex2;
				}
			} else {
				result = "Permission denied Sec";
				System.out
						.println("**** Security Log USER TRIES TO MOVE OHTERS - CONGROUP_ID: "
								+ CONGROUP_ID
								+ " | USER_ID:  "
								+ USER_ID
								+ " | CONTACT_ID: " + CONTACT_ID);
			}
		} else {
			result = "Permission denied";
		}
		return result;
	}

	public String deleteContactFromAllGroups(int CONTACT_ID) {
		String result = "Fehler im Contactmanagement deleteContactFromGroup";
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			String hqlDelete = "delete contactgroups where CONTACT_ID = :CONTACT_ID";
			int deletedEntities = session.createQuery(hqlDelete).setInteger(
					"CONTACT_ID", CONTACT_ID).executeUpdate();
			tx.commit();
			HibernateUtil.closeSession(idf);
			result = "Der Contact wurde erfolgreich von der Group gelöscht"
					+ deletedEntities + " CONTACT_ID:";
		} catch (HibernateException ex) {
			result += "Error: " + ex;
		} catch (Exception ex2) {
			result += "Error: " + ex2;
		}
		return result;
	}

	public String deleteGroupsFromAllContacts(int CONGROUP_ID) {
		String result = "Fehler im Contactmanagement deleteContactFromGroup";
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			String hqlDelete = "delete contactgroups where CONGROUP_ID = :CONGROUP_ID";
			int deletedEntities = session.createQuery(hqlDelete).setInteger(
					"CONTACT_ID", CONGROUP_ID).executeUpdate();
			tx.commit();
			HibernateUtil.closeSession(idf);
			result = "Der Contact wurde erfolgreich von der Group gelöscht"
					+ deletedEntities;
		} catch (HibernateException ex) {
			result += "Error: " + ex;
		} catch (Exception ex2) {
			result += "Error: " + ex2;
		}
		return result;
	}

}
