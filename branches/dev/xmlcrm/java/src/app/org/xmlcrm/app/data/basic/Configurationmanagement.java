package org.xmlcrm.app.data.basic;

import java.util.Iterator;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.xmlcrm.app.data.basic.AuthLevelmanagement;
import org.xmlcrm.app.hibernate.beans.basic.Configuration;
import org.xmlcrm.app.hibernate.utils.HibernateUtil;
import org.xmlcrm.utils.math.Calender;

public class Configurationmanagement {

	private static final Log log = LogFactory
			.getLog(Configurationmanagement.class);

	private Configurationmanagement() {
	}

	private static Configurationmanagement instance = null;

	public static synchronized Configurationmanagement getInstance() {
		if (instance == null) {
			instance = new Configurationmanagement();
		}

		return instance;
	}

	public Configuration getConfKey(long USER_LEVEL, String CONF_KEY) {
		Configuration configuration = new Configuration();
		System.out.println("getConfKey: " + USER_LEVEL + " " + CONF_KEY);
		if (AuthLevelmanagement.getInstance().checkAdminLevel(USER_LEVEL)) {
			try {
				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				Query query = session
						.createQuery("select c from Configuration as c where c.conf_key = :conf_key");
				query.setString("conf_key", CONF_KEY);

				System.out.println("query: ");
				for (Iterator it = query.iterate(); it.hasNext();) {
					configuration = (Configuration) it.next();
				}
				tx.commit();
				HibernateUtil.closeSession(idf);
				//     	configuration.setUsers(ResHandler.getUsermanagement().getUser(configuration.getUser_id().intValue()));
			} catch (HibernateException ex) {
				log.error("[getConfKey]: " ,ex);
			} catch (Exception ex2) {
				log.error("[getConfKey]: " ,ex2);
			}
		} else {
			configuration.setComment("Error: Permission denied");
		}
		System.out.println("return: " + configuration);
		System.out.println("return: " + configuration.getConf_value());
		return configuration;
	}

	public Configuration[] getAllConf(long USER_LEVEL) {
		Configuration[] configuration = new Configuration[1];
		if (AuthLevelmanagement.getInstance().checkAdminLevel(USER_LEVEL)) {
			try {
				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				Query query = session.createQuery("from configuration");
				int count = query.list().size();
				configuration = new Configuration[count];
				int k = 0;
				for (Iterator it = query.iterate(); it.hasNext();) {
					configuration[k] = (Configuration) it.next();
					k++;
				}
				tx.commit();
				HibernateUtil.closeSession(idf);
			} catch (HibernateException ex) {
				log.error("[getAllConf]: " ,ex);
			} catch (Exception ex2) {
				log.error("[getAllConf]: " ,ex2);
			}
		} else {
			configuration[0] = new Configuration();
			configuration[0].setComment("Error: Permission denied");
		}
		return configuration;
	}

	public String addConfByKey(long USER_LEVEL, String CONF_KEY,
			String CONF_VALUE, Integer USER_ID, String comment) {
		String ret = "Add Configuration";
		if (AuthLevelmanagement.getInstance().checkAdminLevel(USER_LEVEL)) {
			Configuration configuration = new Configuration();
			configuration.setConf_key(CONF_KEY);
			configuration.setConf_value(CONF_VALUE);
			configuration.setStarttime(new Date());
			configuration.setUpdatetime(new Date());
			configuration.setComment(comment);
			if (USER_ID!=null) configuration.setUser_id(new Long(USER_ID));

			try {
				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				session.save(configuration);
				session.flush();
				session.clear();
				session.refresh(configuration);
				tx.commit();
				HibernateUtil.closeSession(idf);
				ret = "Erfolgreich";
			} catch (HibernateException ex) {
				log.error("[addConfByKey]: " ,ex);
			} catch (Exception ex2) {
				log.error("[addConfByKey]: " ,ex2);
			}
		} else {
			ret = "Error: Permission denied";
		}
		return ret;
	}

	public String updateConfByUID(long USER_LEVEL, int UID, String CONF_KEY,
			String CONF_VALUE, int USER_ID, String comment) {
		String res = "Update Configuration";
		if (AuthLevelmanagement.getInstance().checkAdminLevel(USER_LEVEL)) {
			try {
				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				String hqlUpdate = "update configuration set CONF_KEY= :CONF_KEY, CONF_VALUE = :CONF_VALUE, USER_ID = :USER_ID, updatetime = :updatetime, comment = :comment where UID= :UID";
				int updatedEntities = session.createQuery(hqlUpdate).setString(
						"CONF_KEY", CONF_KEY).setString("CONF_VALUE",
						CONF_VALUE).setInteger("USER_ID", USER_ID)
						.setLong("updatetime",
								Calender.getInstance().getTimeStampMili())
						.setString("comment", comment).setInteger("UID", UID)
						.executeUpdate();
				res = "Success" + updatedEntities;
				tx.commit();
				HibernateUtil.closeSession(idf);
			} catch (HibernateException ex) {
				log.error("[updateConfByUID]: " ,ex);
			} catch (Exception ex2) {
				log.error("[updateConfByUID]: " ,ex2);
			}
		} else {
			res = "Error: Permission denied";
		}
		return res;
	}

	public String deleteConfByUID(long USER_LEVEL, int UID) {
		String res = "Delete Configuration";
		if (AuthLevelmanagement.getInstance().checkAdminLevel(USER_LEVEL)) {
			try {
				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				String hqlUpdate = "delete configuration where UID= :UID";
				int updatedEntities = session.createQuery(hqlUpdate)
						.setInteger("UID", UID).executeUpdate();
				res = "Success" + updatedEntities;
				tx.commit();
				HibernateUtil.closeSession(idf);
			} catch (HibernateException ex) {
				log.error("[deleteConfByUID]: " ,ex);
			} catch (Exception ex2) {
				log.error("[deleteConfByUID]: " ,ex2);
			}
		} else {
			res = "Error: Permission denied";
		}
		return res;
	}
}
