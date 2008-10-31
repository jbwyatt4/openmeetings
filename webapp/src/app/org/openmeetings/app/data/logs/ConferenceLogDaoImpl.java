package org.openmeetings.app.data.logs;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openmeetings.app.hibernate.beans.logs.ConferenceLog;
import org.openmeetings.app.hibernate.beans.logs.ConferenceLogType;
import org.openmeetings.app.hibernate.utils.HibernateUtil;

public class ConferenceLogDaoImpl {

	private static final Log log = LogFactory.getLog(ConferenceLogDaoImpl.class);

	private ConferenceLogDaoImpl() {
	}

	private static ConferenceLogDaoImpl instance = null;

	public static synchronized ConferenceLogDaoImpl getInstance() {
		if (instance == null) {
			instance = new ConferenceLogDaoImpl();
		}
		return instance;
	}
	

	public Long addConferenceLog(String eventType, Long userId, String streamid, Long room_id, String userip) {
		try {
			
			ConferenceLogType confLogType = ConferenceLogTypeDaoImpl.getInstance().getConferenceLogTypeByEventName(eventType);
			if (confLogType == null) {
				ConferenceLogTypeDaoImpl.getInstance().addConferenceLogType(eventType);
				confLogType = ConferenceLogTypeDaoImpl.getInstance().getConferenceLogTypeByEventName(eventType);
			}
			
			ConferenceLog confLog = new ConferenceLog();
			confLog.setConferenceLogType(confLogType);
			confLog.setInserted(new Date());
			confLog.setUserId(userId);
			confLog.setStreamid(streamid);
			confLog.setRoom_id(room_id);
			confLog.setUserip(userip);
			
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			
			Long confLogId = (Long)session.save(confLog);

			tx.commit();
			HibernateUtil.closeSession(idf);
			
			return confLogId;
		} catch (HibernateException ex) {
			log.error("[addConferenceLog]: ",ex);
		} catch (Exception ex2) {
			log.error("[addConferenceLog]: ",ex2);
		}
		return null;
	}
	
	
}
