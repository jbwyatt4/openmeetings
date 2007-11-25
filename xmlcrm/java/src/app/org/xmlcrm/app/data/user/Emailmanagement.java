package org.xmlcrm.app.data.user;

import java.util.Date;
import java.util.List;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.xmlcrm.app.data.basic.Configurationmanagement;
import org.xmlcrm.app.hibernate.beans.adresses.Emails;
import org.xmlcrm.app.hibernate.utils.HibernateUtil;
import org.xmlcrm.utils.mail.MailHandler;
import org.xmlcrm.app.hibernate.beans.adresses.Adresses_Emails;
import org.xmlcrm.app.hibernate.beans.domain.Organisation;
import org.xmlcrm.app.templates.RegisterUserTemplate;

public class Emailmanagement {

	private static final Log log = LogFactory.getLog(Emailmanagement.class);

	public Emailmanagement() {
	}

	private static Emailmanagement instance = null;

	public static synchronized Emailmanagement getInstance() {
		if (instance == null) {
			instance = new Emailmanagement();
		}
		return instance;
	}

	private boolean checkUserLevel(int user_level) {
		if (user_level > 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Get a Mail-Object by its Mail-Id
	 * @param mail_id
	 * @return
	 */
	public Emails getEmailById(long mail_id) {
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("select c from Emails as c where c.mail_id = :mail_id AND deleted != :deleted");
			query.setLong("mail_id", mail_id);
			query.setString("deleted", "true");
			List ll = query.list();
			tx.commit();
			HibernateUtil.closeSession(idf);
			if (ll.size() > 0) {
				return (Emails) ll.get(0);
			}
		} catch (HibernateException ex) {
			log.error("[getEmailById]" ,ex);
		} catch (Exception ex2) {
			log.error("[getEmailById]" ,ex2);
		}
		return null;
	}

	public List getemails(Long USER_ID) {
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session
					.createQuery("select c from emails as c where c.USER_ID = :USER_ID");
			query.setLong("USER_ID", USER_ID.longValue());
			List lt = query.list();
			tx.commit();
			HibernateUtil.closeSession(idf);
			return lt;
		} catch (HibernateException ex) {
			log.error(ex);
		} catch (Exception ex2) {
			log.error(ex2);
		}
		return null;
	}
	
	public Adresses_Emails getAdresses_EmailsByMail(String email) {
		try {
			String hql = "select c from Adresses_Emails as c " +
					" where c.mail.email = :email ";
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setString("email", email);
			Adresses_Emails e = (Adresses_Emails) query.uniqueResult();
			tx.commit();
			HibernateUtil.closeSession(idf);
			return e;
		} catch (HibernateException ex) {
			log.error(ex);
		} catch (Exception ex2) {
			log.error(ex2);
		}
		return null;
	}	

	public List getemailsCon(int CONTACT_ID) {
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session
					.createQuery("select c from emails as c where c.CONTACT_ID = :CONTACT_ID");
			query.setInteger("CONTACT_ID", CONTACT_ID);
			List lt = query.list();
			tx.commit();
			HibernateUtil.closeSession(idf);
			return lt;
		} catch (HibernateException ex) {
			log.error(ex);
		} catch (Exception ex2) {
			log.error(ex2);
		}
		return null;
	}

	/**
	 * Adds a mail with its connection to the adress table
	 * @param EMail
	 * @param adresses_id
	 * @param Username
	 * @param Userpass
	 * @param comment
	 * @param sendWelcomeMail
	 * @return the new mail_id or -1
	 */
	public Long registerEmail(String EMail, long adresses_id, String Username,
			String Userpass, String comment) {
		Long mail_id = this.registerEmail(EMail, Username, Userpass, comment);
		if (mail_id != null) {
			try {			
				
				Adresses_Emails addr_emails = new Adresses_Emails();
				addr_emails.setAdresses_id(adresses_id);
				addr_emails.setMail(this.getEmailById(mail_id));
				addr_emails.setStarttime(new Date());
				addr_emails.setDeleted("false");

				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				long addr_emails_id = (Long) session.save(addr_emails);
				tx.commit();
				HibernateUtil.closeSession(idf);
				log.error("registerEmail addr_emails: " + addr_emails_id);
				

				return mail_id;
			} catch (HibernateException ex) {
				log.error("Error: " ,ex);
			} catch (Exception ex2) {
				log.error("Error: " ,ex2);
			}
		}
		return null;
	}

	/**
	 * adds a mail-address to the mail table
	 * @param EMail
	 * @param Username
	 * @param Userpass
	 * @param comment
	 * @return
	 */
	public Long registerEmail(String EMail, String Username, String Userpass,
			String comment) {
		try {
			Emails emails = new Emails();
			emails.setEmail(EMail);
			emails.setStarttime(new Date());
			emails.setComment(comment);
			emails.setDeleted("false");

			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			long email_id = (Long) session.save(emails);
			tx.commit();
			HibernateUtil.closeSession(idf);
			log.error("registerEmail id: " + email_id);

			return email_id;

		} catch (HibernateException ex) {
			log.error("Error: " ,ex);
		} catch (Exception ex2) {
			log.error("Error: " ,ex2);
		}

		return null;

	}

	/**
	 * sends a mail adress to the user with his account data
	 * @param Username
	 * @param Userpass
	 * @param EMail
	 * @return
	 * @throws Exception
	 */
	public String sendMail(String Username, String Userpass, String EMail) {
		String succ = "valid email";
		String template = RegisterUserTemplate.getInstance().getRegisterUserTemplate(Username,Userpass,EMail);
		succ = MailHandler.sendMail(EMail, Configurationmanagement.getInstance().getConfKey(3,"register_mail_subject").getConf_value(), template);

		return succ;
	}

	public String addEmailCon(String EMail, int CONTACT_ID) {
		String succ = "invalid email";
		//		Emails emails = new Emails();
		//		long time = CalenderI.getTimeStampMili();
		//		emails.setEmail(EMail);
		//		emails.setCONTACT_ID(CONTACT_ID);
		//		emails.setStartdate(time);
		//		emails.setUpdatedate(time);
		//        try {   
		//            Object idf = HibernateUtil.createSession(); 			Session session = HibernateUtil.getSession();
		//            Transaction tx = session.beginTransaction();
		//            session.save(emails);
		//            session.flush();   
		//            session.clear();
		//            session.refresh(emails);
		//            tx.commit();
		//            HibernateUtil.closeSession(idf);   
		//            succ = "valid email";
		//        } catch( HibernateException ex ) {
		//        	succ = "Error: "+ex;
		//        } catch ( Exception ex2 ){
		//        	succ = "Error: "+ex2;
		//        }		
		return succ;
	}

	/**
	 * delete a Email-Object by a given Id
	 * @param mail_id
	 */
	public void deleteEMailByID(long mail_id) {
		try {
			Emails mail = this.getEmailById(mail_id);
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.delete(mail);			
			tx.commit();
			HibernateUtil.closeSession(idf);
		} catch (HibernateException ex) {
			log.error(ex);
		} catch (Exception ex2) {
			log.error(ex2);
		}
	}

	//TODO: Change code sothat it doesn't usw HQL
	public String deleteEMailByUserID(int USER_ID) {
		String result = "Fehler im Bestellvorgang";
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			String hqlDelete = "delete emails where USER_ID = :USER_ID";
			int deletedEntities = session.createQuery(hqlDelete).setInteger(
					"USER_ID", USER_ID).executeUpdate();
			//session.flush(); 

			tx.commit();
			HibernateUtil.closeSession(idf);
			result = "Erfolgreich" + deletedEntities;
		} catch (HibernateException ex) {
			log.error(ex);
		} catch (Exception ex2) {
			log.error(ex2);
		}
		return result;
	}

	/**
	 * Checks if a mail is already taken by someone else
	 * @param email
	 * @return
	 */
	public boolean checkUserEMail(String email) {
		try {
			if (email.length()==0) return true;
			log.error("checkUserMail: " + email);
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("select c from Emails as c where c.email = :email AND c.deleted != :deleted");
			query.setString("email", email);
			query.setString("deleted", "true");
			int count = query.list().size();
			log.error("size: " + count);
			tx.commit();
			HibernateUtil.closeSession(idf);
			
			if (count > 0) {
				return false;
			}			
		} catch (HibernateException ex) {
			log.error("Error: " ,ex);
		} catch (Exception ex2) {
			log.error("Error: " ,ex2);
		}
		return true;
	}

	/**
	 * update a Email-Object by a given id
	 * @param mail_id
	 * @param user_id
	 * @param email
	 * @return
	 */
	public Emails updateUserEmail(long mail_id, Long user_id, String email) {
		try {
			Emails mail = this.getEmailById(mail_id);
			mail.setEmail(email);
			mail.setUpdatetime(new Date());
            Object idf = HibernateUtil.createSession(); 			
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();        
            session.update(mail);
            tx.commit();
            HibernateUtil.closeSession(idf);
            return mail;
		} catch (HibernateException ex) {
			log.error("[updateUserEmail] "+ex);
		} catch (Exception ex2) {
			log.error("[updateUserEmail] "+ex2);
		}
		return null;
	}

	public String updateContactEmail(int MAIL_ID, int Contact_ID, String email) {
		String res = "Fehler beim Update";
		try {
			//            Object idf = HibernateUtil.createSession(); 			Session session = HibernateUtil.getSession();
			//            Transaction tx = session.beginTransaction();        
			//            String hqlUpdate = "update emails set email= :email, CONTACT_ID = :CONTACT_ID, updatedate = :updatedate where MAIL_ID= :MAIL_ID";
			//            int updatedEntities = session.createQuery( hqlUpdate )
			//                                .setString("email",email)
			//                                .setInteger( "CONTACT_ID", Contact_ID )
			//                                .setLong( "updatedate", CalenderI.getTimeStampMili() )
			//                                .setInteger( "MAIL_ID", MAIL_ID )
			//                                .executeUpdate();
			//            res = "Success"+updatedEntities;
			//            tx.commit();
			//            HibernateUtil.closeSession(idf);
		} catch (HibernateException ex) {
			log.error(ex);
		} catch (Exception ex2) {
			log.error(ex2);
		}
		return res;
	}

}
