package org.openmeetings.app.data.record.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openmeetings.app.hibernate.beans.recording.RecordingClient;
import org.openmeetings.app.hibernate.utils.HibernateUtil;

public class RecordingClientDaoImpl {

	private static final Log log = LogFactory.getLog(RecordingClientDaoImpl.class);

	private RecordingClientDaoImpl() {
	}

	private static RecordingClientDaoImpl instance = null;

	public static synchronized RecordingClientDaoImpl getInstance() {
		if (instance == null) {
			instance = new RecordingClientDaoImpl();
		}

		return instance;
	}
	
	public Long addRecordingClient(RecordingClient recordingClient) {
		try {
			
			//Fill and remove duplicated RoomClient Objects
			if (recordingClient.getRcl() != null) {
				recordingClient.setRcl(RoomClientDaoImpl.getInstance().getAndAddRoomClientByPublicSID(recordingClient.getRcl()));
			}
			
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			
			Long recordingClientId = (Long) session.save(recordingClient);
			
			session.flush();
			session.clear();
			session.refresh(recordingClient);
			
			tx.commit();
			HibernateUtil.closeSession(idf);
			
			return recordingClientId;
		} catch (HibernateException ex) {
			log.error("[addRecordingClient]: " , ex);
		} catch (Exception ex2) {
			log.error("[addRecordingClient]: " , ex2);
		}
		return null;
	}
}
