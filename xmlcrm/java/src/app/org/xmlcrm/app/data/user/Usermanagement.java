package org.xmlcrm.app.data.user;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Order;

import org.xmlcrm.app.hibernate.beans.user.*;
import org.xmlcrm.app.hibernate.beans.adresses.Adresses_Emails;
import org.xmlcrm.app.hibernate.beans.adresses.Emails;
import org.xmlcrm.app.hibernate.utils.HibernateUtil;
import org.xmlcrm.app.data.basic.AuthLevelmanagement;
import org.xmlcrm.app.data.basic.Configurationmanagement;
import org.xmlcrm.app.data.beans.basic.SearchResult;
import org.xmlcrm.app.data.user.Organisationmanagement;
import org.xmlcrm.utils.math.*;


import org.xmlcrm.app.data.basic.*;

/**
 * 
 * @author swagner
 *
 */
public class Usermanagement {

	private static final Log log = LogFactory.getLog(Usermanagement.class);

	private static Usermanagement instance = null;

	private Usermanagement() {
	}

	public static synchronized Usermanagement getInstance() {
		if (instance == null) {
			instance = new Usermanagement();
		}
		return instance;
	}
	
	/**
	 * query for a list of users
	 * @param users_id
	 * @param USER_LEVEL
	 * @param start
	 * @param max
	 * @param orderby
	 * @return
	 */
	public SearchResult getUsersList(long USER_LEVEL, int start, int max, String orderby, boolean asc){
		try {
			if (AuthLevelmanagement.getInstance().checkAdminLevel(USER_LEVEL)){
				SearchResult sresult = new SearchResult();
				sresult.setObjectName(Users.class.getName());
				sresult.setRecords(this.selectMaxFromUsers());
				//get all users
				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				Criteria crit = session.createCriteria(Users.class);
				crit.add(Restrictions.eq("deleted", "false"));
				if (asc) crit.addOrder(Order.asc(orderby));
				else crit.addOrder(Order.desc(orderby));
				crit.setMaxResults(max);
				crit.setFirstResult(start);
				sresult.setResult(crit.list());
				tx.commit();
				HibernateUtil.closeSession(idf);
				return sresult;				
			}
		} catch (HibernateException ex) {
			log.error("[getUsersList] "+ex);
		} catch (Exception ex2) {
			log.error("[getUsersList] "+ex2);
		}
		return null;
	}
	
	/**
	 * returns the maximum
	 * @return
	 */
	public Long selectMaxFromUsers(){
		try {
			//get all users
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("select max(c.user_id) from Users c where c.deleted = 'false'"); 
			List ll = query.list();
			tx.commit();
			HibernateUtil.closeSession(idf);
			log.error((Long)ll.get(0));
			return (Long)ll.get(0);				
		} catch (HibernateException ex) {
			log.error("[selectMaxFromUsers] "+ex);
		} catch (Exception ex2) {
			log.error("[selectMaxFromUsers] "+ex2);
		}
		return null;
	}
	
	public Users checkAdmingetUserById(long USER_LEVEL, long user_id){
		if (AuthLevelmanagement.getInstance().checkAdminLevel(USER_LEVEL)) {
			return this.getUser(user_id);
		}
		return null;
	}

	/**
	 * 
	 * @param user_id
	 * @return
	 */
	public Users getUser(long user_id) {
		Users users = new Users();
		if (user_id > 0) {
			try {
				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				Query query = session.createQuery("select c from Users as c where c.user_id = :user_id");
				query.setLong("user_id", user_id);
				for (Iterator it2 = query.iterate(); it2.hasNext();) {
					users = (Users) it2.next();
				}
				tx.commit();
				HibernateUtil.closeSession(idf);
				// TODO: Einbinden der Benutzergruppen
				// users.setUsergroups(ResHandler.getGroupmanagement().getUserGroups(user_id));
			} catch (HibernateException ex) {
				log.error(ex);
			} catch (Exception ex2) {
				log.error(ex2);
			}
		} else {
			users.setFirstname("Error: No USER_ID given");
		}
		return users;
	}

	/**
	 * login logic
	 * @param SID
	 * @param Username
	 * @param Userpass
	 * @return
	 */
	public Users loginUser(String SID, String Username, String Userpass) {
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			MD5Calc md5 = new MD5Calc("MD5");
			Criteria crit = session.createCriteria(Users.class);
			crit.add(Restrictions.eq("login", Username));
			crit.add(Restrictions.eq("deleted", "false"));
			crit.add(Restrictions.eq("status", 1));
			List ll = crit.list();
			log.debug("debug loginUser: " + Username);
			tx.commit();
			HibernateUtil.closeSession(idf);

			if (ll.size()==0) {
				Users usersA = new Users();
				usersA.setLevel_id(new Long(-1));
				usersA.setFirstname("Wrong - " + Username + " not Found");
				return usersA;
			} else {
				Users users = (Users) ll.get(0);
				String chsum = md5.do_checksum(Userpass);
				if (chsum.equals(users.getPassword())) {
					Sessionmanagement.getInstance().updateUser(SID, users.getUser_id());
					users.setUserlevel(getUserLevel(users.getLevel_id()));		
					updateLastLogin(users);
					return users;
				} else {
					Users usersA = new Users();
					usersA.setLevel_id(new Long(-1));
					usersA.setFirstname("Wrong -Invalid Password for" + Username);
					return usersA;
				}
			}
		
		} catch (HibernateException ex) {
			log.error("[loginUser]: ",ex);
		} catch (Exception ex2) {
			log.error("[loginUser]: ",ex2);
		}
		return null;
	}

	public String logout(String SID, int USER_ID) {
		String result = "Fehler im logout";
		Sessionmanagement.getInstance().updateUser(SID, 0);
		result = "Erfolgreich";
		return result;
	}

	private void updateLastLogin(Users us) {
		try {
			us.setLastlogin(new Date());
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.update(us);
			tx.commit();
			HibernateUtil.closeSession(idf);
		} catch (HibernateException ex) {
			log.error(ex);
		} catch (Exception ex2) {
			log.error(ex2);
		}
	}

	/**
	 * suche eines Bentzers
	 * @param USER_LEVEL
	 * @param searchstring
	 * @param max
	 * @param start
	 * @return
	 */
	public List searchUser(long USER_LEVEL, String searchcriteria, String searchstring, int max, int start, String orderby, boolean asc) {
		if (AuthLevelmanagement.getInstance().checkAdminLevel(USER_LEVEL)) {
			try {
				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				Criteria crit = session.createCriteria(Users.class);
				crit.add(Restrictions.ilike(searchcriteria, "%" + searchstring + "%"));
				if (asc) crit.addOrder(Order.asc(orderby));
				else crit.addOrder(Order.desc(orderby));
				crit.add(Restrictions.ne("deleted", "true"));
				crit.setMaxResults(max);
				crit.setFirstResult(start);
				List contactsZ = crit.list();
				tx.commit();
				HibernateUtil.closeSession(idf);
				return contactsZ;
			} catch (HibernateException ex) {
				log.error(ex);
			} catch (Exception ex2) {
				log.error(ex2);
			}
		}
		return null;
	}

	public List getUserdata(Long user_id) {
		if (user_id.longValue() > 0) {
			try {
				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				Query query = session.createQuery("select c from Userdata as c where c.user_id = :user_id AND deleted != :deleted");
				query.setLong("user_id", user_id.longValue());
				query.setString("deleted", "true");
				List ll = query.list();
				tx.commit();
				HibernateUtil.closeSession(idf);
				return ll;
			} catch (HibernateException ex) {
				log.error(ex);
			} catch (Exception ex2) {
				log.error(ex2);
			}
		}
		return null;
	}

	private int getUserdataNoByKey(Long USER_ID, String DATA_KEY) {
		int userdata = 0;
		if (USER_ID.longValue() > 0) {
			try {
				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				Query query = session.createQuery("select c from Userdata as c where c.user_id = :user_id AND c.data_key = :data_key AND deleted != :deleted");
				query.setLong("user_id", USER_ID.longValue());
				query.setString("data_key", DATA_KEY);
				query.setString("deleted", "true");
				userdata = query.list().size();
				tx.commit();
				HibernateUtil.closeSession(idf);
			} catch (HibernateException ex) {
				log.error(ex);
			} catch (Exception ex2) {
				log.error(ex2);
			}
		} else {
			System.out.println("Error: No USER_ID given");
		}
		return userdata;
	}

	public Userdata getUserdataByKey(Long user_id, String DATA_KEY) {
		Userdata userdata = new Userdata();
		if (user_id.longValue() > 0) {
			try {
				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				Query query = session.createQuery("select c from Userdata as c where c.user_id = :user_id AND c.data_key = :data_key AND deleted != :deleted");
				query.setLong("user_id", user_id.longValue());
				query.setString("data_key", DATA_KEY);
				query.setString("deleted", "true");
				for (Iterator it2 = query.iterate(); it2.hasNext();) {
					userdata = (Userdata) it2.next();
				}
				tx.commit();
				HibernateUtil.closeSession(idf);
			} catch (HibernateException ex) {
				log.error(ex);
			} catch (Exception ex2) {
				log.error(ex2);
			}
		} else {
			userdata.setComment("Error: No USER_ID given");
		}
		return userdata;
	}

	public Long updateUser(long USER_LEVEL, Long user_id, Long level_id,
			String login, String password, String lastname, String firstname,
			Date age, String street, String additionalname, String zip, long states_id, String town,
			int availible, String telefon, String fax,
			String mobil, String email, String comment, int status, LinkedHashMap organisations,
			int title_id) {

		if (AuthLevelmanagement.getInstance().checkUserLevel(USER_LEVEL) && user_id != 0) {
			try {
				Users us = this.getUser(user_id);
				// Check for duplicates
				boolean checkName = true;
				if (!login.equals(us.getLogin())){
					checkName = this.checkUserLogin(login);
				}
				boolean checkEmail = true;
				Adresses_Emails mail = null;
//				log.error("mail 1 update User: "+us.getAdresses().getAdresses_id());
//				log.error("mail 2 update User: "+us.getAdresses().getEmails().size());
				Iterator it = us.getAdresses().getEmails().iterator();
//				log.error("mail 3 update User: "+it);
				if (it.hasNext()){
//					log.error("mail 4 update User: has next");
					mail = (Adresses_Emails) it.next();
//					log.error("mail 5 update User naxt"+mail);
				}				
//				log.error("updateUser mail: "+mail);
//				log.error("updateUser email: "+email);
				if (!email.equals(mail.getMail().getEmail())){
					checkEmail = Emailmanagement.getInstance().checkUserEMail(email);
				}
				if (checkName && checkEmail) {
//					log.info("user_id " + user_id);
					
					us.setLastname(lastname);
					us.setFirstname(firstname);
					us.setAge(age);
					us.setLogin(login);
					us.setUpdatetime(new Date());
					us.setAvailible(availible);
					us.setStatus(status);
					us.setTitle_id(title_id);
					
					if (level_id != 0)
						us.setLevel_id(new Long(level_id));
					if (password.length() != 0) {
						if (password.length()>=4){
							MD5Calc md5 = new MD5Calc("MD5");
							us.setPassword(md5.do_checksum(password));
						} else {
							return new Long(-6);
						}
					}					
					
					//Todo implement Phone
					Adressmanagement.getInstance().updateAdress(us.getAdresses().getAdresses_id(), street, zip, town, states_id, additionalname, comment, fax);
					Emailmanagement.getInstance().updateUserEmail(mail.getMail().getMail_id(),user_id, email);
					
					if (organisations!=null){
						Organisationmanagement.getInstance().updateUserOrganisationsByUser(us, organisations);
					}
					
//					log.info("USER " + us.getLastname());
					Object idf = HibernateUtil.createSession();
					Session session = HibernateUtil.getSession();
					Transaction tx = session.beginTransaction();

					session.update(us);
					
					tx.commit();
					HibernateUtil.closeSession(idf);
					
					return us.getUser_id();
					
				} else {
					if (!checkName) {
						return new Long(-5);
					} else if (!checkEmail) {
						return new Long(-4);
					}
				}
			} catch (HibernateException ex) {
				log.error("[updateUser]",ex);
			} catch (Exception ex2) {
				log.error("[updateUser]",ex2);
			}
		} else {
			log.error("Error: Permission denied");
			return null;
		}
		return null;
	}

	public String resetPassword(int USER_ID) {
		String res = "Fehler beim Update";
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			MD5Calc md5 = new MD5Calc("MD5");
			String newPass = GenerateRandom.randomstring(5, 10);
			String hqlUpdate = "update users set password = :password where USER_ID= :USER_ID";
			int updatedEntities = session.createQuery(hqlUpdate).setString(
					"password", md5.do_checksum(newPass)).setInteger("USER_ID",
					USER_ID).executeUpdate();
			res = "Success" + updatedEntities;
			tx.commit();
			HibernateUtil.closeSession(idf);
			Emailmanagement.getInstance().sendNewPass(USER_ID, newPass);
		} catch (HibernateException ex) {
			log.error(ex);
		} catch (Exception ex2) {
			log.error(ex2);
		}
		return res;
	}

	public String updateUserdata(int DATA_ID, int USER_ID, String DATA_KEY,
			String DATA, String Comment) {
		String res = "Fehler beim Update";
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			String hqlUpdate = "update userdata set DATA_KEY= :DATA_KEY, USER_ID = :USER_ID, DATA = :DATA, updatetime = :updatetime, comment = :Comment where DATA_ID= :DATA_ID";
			int updatedEntities = session.createQuery(hqlUpdate).setString(
					"DATA_KEY", DATA_KEY).setInteger("USER_ID", USER_ID)
					.setString("DATA", DATA).setLong("updatetime",
							Calender.getInstance().getTimeStampMili())
					.setString("Comment", Comment).setInteger("DATA_ID",
							DATA_ID).executeUpdate();
			res = "Success" + updatedEntities;
			tx.commit();
			HibernateUtil.closeSession(idf);
		} catch (HibernateException ex) {
			log.error(ex);
		} catch (Exception ex2) {
			log.error(ex2);
		}
		return res;
	}

	public String updateUserdataByKey(Long USER_ID, String DATA_KEY,
			String DATA, String Comment) {
		String res = "Fehler beim Update";
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			String hqlUpdate = "update Userdata set data = :data, updatetime = :updatetime, "
					+ "comment = :comment where user_id= :user_id AND data_key = :data_key";
			int updatedEntities = session.createQuery(hqlUpdate).setString(
					"data", DATA).setLong("updatetime",
					Calender.getInstance().getTimeStampMili()).setString(
					"comment", Comment).setLong("user_id", USER_ID.longValue())
					.setString("data_key", DATA_KEY).executeUpdate();
			res = "Success" + updatedEntities;
			tx.commit();
			HibernateUtil.closeSession(idf);
		} catch (HibernateException ex) {
			log.error(ex);
		} catch (Exception ex2) {
			log.error(ex2);
		}
		return res;
	}

	public String deleteUserdata(int DATA_ID) {
		String res = "Fehler beim deleteUserdata";
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			String hqlUpdate = "delete userdata where DATA_ID= :DATA_ID";
			int updatedEntities = session.createQuery(hqlUpdate).setInteger("DATA_ID", DATA_ID).executeUpdate();
			res = "Success" + updatedEntities;
			tx.commit();
			HibernateUtil.closeSession(idf);
		} catch (HibernateException ex) {
			log.error(ex);
		} catch (Exception ex2) {
			log.error(ex2);
		}
		return res;
	}

	public String deleteUserdataByUserAndKey(int users_id, String DATA_KEY) {
		String res = "Fehler beim deleteUserdataByUserAndKey";
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			String hqlUpdate = "delete userdata where users_id= :users_id AND DATA_KEY = :DATA_KEY";
			int updatedEntities = session.createQuery(hqlUpdate).setInteger(
					"users_id", users_id).setString("DATA_KEY", DATA_KEY)
					.executeUpdate();
			res = "Success" + updatedEntities;
			tx.commit();
			HibernateUtil.closeSession(idf);
		} catch (HibernateException ex) {
			log.error(ex);
		} catch (Exception ex2) {
			log.error(ex2);
		}
		return res;
	}

	public String addUserdata(long USER_ID, String DATA_KEY, String DATA,
			String Comment) {
		String ret = "Fehler beim speichern der Userdata";
		Userdata userdata = new Userdata();
		userdata.setData_key(DATA_KEY);
		userdata.setData(DATA);
		userdata.setStarttime(new Date());
		userdata.setUpdatetime(null);
		userdata.setComment(Comment);
		userdata.setUser_id(new Long(USER_ID));
		userdata.setDeleted("false");
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.save(userdata);
			session.flush();
			session.clear();
			session.refresh(userdata);
			tx.commit();
			HibernateUtil.closeSession(idf);
			ret = "success";
		} catch (HibernateException ex) {
			log.error(ex);
		} catch (Exception ex2) {
			log.error(ex2);
		}
		return ret;
	}

	public Long deleteUserID(long USER_ID) {
		try {
			if (USER_ID != 0) {
				Users us = this.getUser(USER_ID);
				us.setDeleted("true");
				us.setUpdatetime(new Date());
				// result +=
				// Groupmanagement.getInstance().deleteUserFromAllGroups(new
				// Long(USER_ID));

				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				session.update(us);
				tx.commit();
				HibernateUtil.closeSession(idf);
				return us.getUser_id();
				// result +=
				// ResHandler.getBestellmanagement().deleteWarenkorbByUserID(USER_ID);
				// result +=
				// ResHandler.getEmailmanagement().deleteEMailByUserID(USER_ID);
				// result +=
				// ResHandler.getContactmanagement().deleteContactUsergroups(USER_ID);
				// result +=
				// ResHandler.getContactmanagement().deleteUserContact(USER_ID);

			}
		} catch (HibernateException ex) {
			log.error("[deleteUserID]" ,ex);
		} catch (Exception ex2) {
			log.error("[deleteUserID]" ,ex2);
		}
		return null;
	}

	private Userlevel getUserLevel(Long level_id) {
		Userlevel userlevel = new Userlevel();
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("select c from Userlevel as c where c.level_id = :level_id AND deleted != :deleted");
			query.setLong("level_id", level_id.longValue());
			query.setString("deleted", "true");
			for (Iterator it2 = query.iterate(); it2.hasNext();) {
				userlevel = (Userlevel) it2.next();
			}
			tx.commit();
			HibernateUtil.closeSession(idf);
		} catch (HibernateException ex) {
			log.error("[getUserLevel]" ,ex);
		} catch (Exception ex2) {
			log.error("[getUserLevel]" ,ex2);
		}
		return userlevel;
	}

	/**
	 * get user-role
	 * 1 - user
	 * 2 - moderator
	 * 3 - admin
	 * @param user_id
	 * @return
	 */
	public Long getUserLevelByID(long user_id) {
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			
			Query query = session.createQuery("select c from Users as c where c.user_id = :user_id AND deleted <> 'true'");
			query.setLong("user_id", user_id);
			List ll = query.list();
			
			tx.commit();
			HibernateUtil.closeSession(idf);
			
			if (ll.size()>0){
				Users users  = (Users) ll.get(0);
				return users.getLevel_id();
			}
		} catch (HibernateException ex) {
			log.error("[getUserLevelByID]" ,ex);
		} catch (Exception ex2) {
			log.error("[getUserLevelByID]" ,ex2);
		}
		return null;
	}

	/**
	 * Method to register a new User, User will automatically be added to the
	 * default USER_LEVEL(1) new users will be automatically added to the
	 * Organisation with the id specified in the configuration value
	 * default_domain_id
	 * 
	 * @param USER_LEVEL
	 * @param level_id
	 * @param availible
	 * @param status
	 * @param login
	 * @param Userpass
	 * @param lastname
	 * @param firstname
	 * @param email
	 * @param age
	 * @param street
	 * @param additionalname
	 * @param fax
	 * @param zip
	 * @param states_id
	 * @param town
	 * @param language_id
	 * @return
	 */
	public Long registerUser(String login, String Userpass, String lastname,
			String firstname, String email, Date age, String street,
			String additionalname, String fax, String zip, long states_id,
			String town, long language_id) {
		// Checks if FrontEndUsers can register
		if (Configurationmanagement.getInstance().getConfKey(3,"allow_frontend_register").getConf_value().equals("1")) {
			// TODO: add availible params sothat users have to verify their
			// login-data
			// TODO: add status from Configuration items
			Long user_id = this.registerUserInit(3, 1, 0, 1, login, Userpass,lastname, firstname, email, age, street, additionalname,fax, zip, states_id, town, language_id, true, new LinkedHashMap());
			// Get the default organisation_id of registered users
			if (user_id>0){
				long organisation_id = Long.valueOf(Configurationmanagement.getInstance().getConfKey(3,"default_domain_id").getConf_value()).longValue();
				Organisationmanagement.getInstance().addUserToOrganisation(user_id,organisation_id, user_id, "");
			}
			return user_id;
		}
		return null;
	}

	/**
	 * Adds a user including his adress-data,auth-date,mail-data
	 * 
	 * @param USER_LEVEL
	 * @param level_id
	 * @param availible
	 * @param status
	 * @param login
	 * @param Userpass
	 * @param lastname
	 * @param firstname
	 * @param email
	 * @param age
	 * @param street
	 * @param additionalname
	 * @param fax
	 * @param zip
	 * @param states_id
	 * @param town
	 * @param language_id
	 * @return new users_id OR null if an exception, -1 if an error, -4 if mail
	 *         already taken, -5 if username already taken, -3 if login or pass
	 *         or mail is empty
	 */
	public Long registerUserInit(long USER_LEVEL, long level_id, int availible,
			int status, String login, String Userpass, String lastname,
			String firstname, String email, Date age, String street,
			String additionalname, String fax, String zip, long states_id,
			String town, long language_id, boolean sendWelcomeMessage, LinkedHashMap organisations) {
		//TODO: make phoen number persistent
		// User Level must be at least Admin
		// Moderators will get a temp update of there UserLevel to add Users to
		// their Group
		if (AuthLevelmanagement.getInstance().checkModLevel(USER_LEVEL)) {
			// Check for required data
			if (!login.equals("") && !Userpass.equals("") && !email.equals("")) {

				// Check for duplicates
				boolean checkName = this.checkUserLogin(login);
				boolean checkEmail = Emailmanagement.getInstance().checkUserEMail(email);
				if (checkName && checkEmail) {
					
					long adress_id = Adressmanagement.getInstance().saveAdress(street, zip, town, states_id, additionalname, "",fax);
					long user_id = this.addUser(level_id, availible, status,firstname, login, lastname, language_id, Userpass,adress_id, age);
					long adress_emails_id = Emailmanagement.getInstance().registerEmail(email, adress_id, login, Userpass,"", sendWelcomeMessage);
					
					Organisationmanagement.getInstance().addUserOrganisationsByHashMap(user_id, organisations);
					
					if (adress_id > 0 && user_id > 0 && adress_emails_id > 0) {
						return user_id;
					} else {
						return new Long(-1);
					}
				} else {
					if (!checkName) {
						return new Long(-5);
					} else if (!checkEmail) {
						return new Long(-4);
					}
				}
			} else {
				return new Long(-3);
			}
		}
		return null;
	}

	/**
	 * @author swagner This Methdo adds a User to the User-Table
	 * @param level_id
	 *            The User Level, 1=User, 2=GroupAdmin/Moderator,
	 *            3=SystemAdmin/Admin
	 * @param availible
	 *            The user is activated
	 * @param status
	 *            The user is not blocked by System admins
	 * @param firstname
	 * @param login
	 *            Username for login
	 * @param lastname
	 * @param language_id
	 * @param Userpass
	 *            is MD5-crypted
	 * @param adress_id
	 * @return user_id or error null
	 */
	public Long addUser(long level_id, int availible, int status,
			String firstname, String login, String lastname, long language_id,
			String Userpass, long adress_id, Date age) {
		try {
			Users users = new Users();
			users.setFirstname(firstname);
			users.setLogin(login);
			users.setLastname(lastname);
			users.setAge(age);
			users.setAdresses(Adressmanagement.getInstance().getAdressbyId(adress_id));
			users.setAvailible(availible);
			users.setLastlogin(new Date());
			users.setLasttrans(new Long(0));
			users.setLevel_id(level_id);
			users.setStatus(status);
			users.setTitle_id(new Integer(1));
			users.setStarttime(new Date());
			users.setUpdatetime(new Date());
			// this is needed cause the language is not a needed data at
			// registering
			if (language_id != 0) {
				users.setLanguage_id(new Long(language_id));
			} else {
				users.setLanguage_id(null);
			}
			MD5Calc md5 = new MD5Calc("MD5");
			users.setPassword(md5.do_checksum(Userpass));
			users.setRegdate(new Date());
			users.setDeleted("false");

			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			long user_id = (Long) session.save(users);
			tx.commit();
			HibernateUtil.closeSession(idf);

			return user_id;

		} catch (HibernateException ex) {
			log.error("[registerUser]" ,ex);
		} catch (Exception ex2) {
			log.error("[registerUser]" ,ex2);
		}
		return null;
	}

	/**
	 * check for duplicates
	 * @param DataValue
	 * @return
	 */
	private boolean checkUserLogin(String DataValue) {
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("select c from Users as c where c.login = :DataValue AND deleted != :deleted");
			query.setString("DataValue", DataValue);
			query.setString("deleted", "true");
			int count = query.list().size();

			tx.commit();
			HibernateUtil.closeSession(idf);
			if (count != 0) {
				return false;
			}			
		} catch (HibernateException ex) {
			log.error("[checkUserData]" ,ex);
		} catch (Exception ex2) {
			log.error("[checkUserData]" ,ex2);
		}
		return true;
	}

	public void addUserLevel(String description, int myStatus) {
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Userlevel uslevel = new Userlevel();
			uslevel.setStarttime(new Date());
			uslevel.setDescription(description);
			uslevel.setStatuscode(new Integer(myStatus));
			uslevel.setDeleted("false");
			session.save(uslevel);
			tx.commit();
			HibernateUtil.closeSession(idf);
		} catch (HibernateException ex) {
			log.error("[addUserLevel]" ,ex);
		} catch (Exception ex2) {
			log.error("[addUserLevel]" ,ex2);
		}
	}

}
