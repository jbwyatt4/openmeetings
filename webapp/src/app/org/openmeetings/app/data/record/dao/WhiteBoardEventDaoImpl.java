package org.openmeetings.app.data.record.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openmeetings.app.hibernate.beans.recording.WhiteBoardEvent;
import org.openmeetings.app.hibernate.utils.HibernateUtil;

public class WhiteBoardEventDaoImpl {

	private static final Log log = LogFactory.getLog(WhiteBoardEventDaoImpl.class);

	private WhiteBoardEventDaoImpl() {
	}

	private static WhiteBoardEventDaoImpl instance = null;

	public static synchronized WhiteBoardEventDaoImpl getInstance() {
		if (instance == null) {
			instance = new WhiteBoardEventDaoImpl();
		}

		return instance;
	}
	
	public Long addWhiteBoardEvent(WhiteBoardEvent whiteBoardEvent) {
		try {
			
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			
			Long whiteBoardEventId = (Long) session.save(whiteBoardEvent);
			
			tx.commit();
			HibernateUtil.closeSession(idf);
			
			return whiteBoardEventId;
		} catch (HibernateException ex) {
			log.error("[addWhiteBoardEvent]: " , ex);
		} catch (Exception ex2) {
			log.error("[addWhiteBoardEvent]: " , ex2);
		}
		return null;
	}
	
}
