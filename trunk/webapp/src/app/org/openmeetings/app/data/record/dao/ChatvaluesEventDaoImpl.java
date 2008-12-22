package org.openmeetings.app.data.record.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openmeetings.app.hibernate.beans.recording.ChatvaluesEvent;
import org.openmeetings.app.hibernate.utils.HibernateUtil;

public class ChatvaluesEventDaoImpl {

	private static final Log log = LogFactory.getLog(ChatvaluesEventDaoImpl.class);

	private ChatvaluesEventDaoImpl() {
	}

	private static ChatvaluesEventDaoImpl instance = null;

	public static synchronized ChatvaluesEventDaoImpl getInstance() {
		if (instance == null) {
			instance = new ChatvaluesEventDaoImpl();
		}

		return instance;
	}
	
	public Long addChatvaluesEvent(ChatvaluesEvent chatvaluesEvent) {
		try {
			
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			
			Long chatvaluesEventId = (Long) session.save(chatvaluesEvent);
			
			tx.commit();
			HibernateUtil.closeSession(idf);
			
			return chatvaluesEventId;
		} catch (HibernateException ex) {
			log.error("[addChatvaluesEvent]: " , ex);
		} catch (Exception ex2) {
			log.error("[addChatvaluesEvent]: " , ex2);
		}
		return null;
	}
	
}
