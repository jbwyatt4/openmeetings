package org.openmeetings.app.data.record.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openmeetings.app.hibernate.beans.recording.ChatvaluesEvent;
import org.openmeetings.app.hibernate.beans.recording.RecordingConversionJob;
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
	
	public List<ChatvaluesEvent> getChatvaluesEventByRoomRecordingId(Long roomrecordingId) {
		try {
			
			String hql = "select c from ChatvaluesEvent as c " +
						"where c.roomRecording.roomrecordingId = :roomrecordingId";
			
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setLong("roomrecordingId", roomrecordingId);
			List<ChatvaluesEvent> ll = query.list();
			tx.commit();
			HibernateUtil.closeSession(idf);
			
			return ll;
	
		} catch (HibernateException ex) {
			log.error("[getChatvaluesEventByRoomRecordingId]: " , ex);
		} catch (Exception ex2) {
			log.error("[getChatvaluesEventByRoomRecordingId]: " , ex2);
		}
		return null;
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
