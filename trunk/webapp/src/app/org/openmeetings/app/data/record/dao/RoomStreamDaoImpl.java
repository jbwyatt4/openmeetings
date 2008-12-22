package org.openmeetings.app.data.record.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openmeetings.app.hibernate.beans.recording.RoomStream;
import org.openmeetings.app.hibernate.utils.HibernateUtil;

public class RoomStreamDaoImpl {

	private static final Log log = LogFactory.getLog(RoomStreamDaoImpl.class);

	private RoomStreamDaoImpl() {
	}

	private static RoomStreamDaoImpl instance = null;

	public static synchronized RoomStreamDaoImpl getInstance() {
		if (instance == null) {
			instance = new RoomStreamDaoImpl();
		}

		return instance;
	}
	
	public Long addRoomStream(RoomStream roomStream) {
		try {
			
			//Fill and remove duplicated RoomClient Objects
			if (roomStream.getRcl() != null) {
				roomStream.setRcl(RoomClientDaoImpl.getInstance().getAndAddRoomClientByPublicSID(roomStream.getRcl()));
			}
			
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			
			Long roomStreamId = (Long) session.save(roomStream);
			
			tx.commit();
			HibernateUtil.closeSession(idf);
			
			return roomStreamId;
		} catch (HibernateException ex) {
			log.error("[addRoomStream]: " , ex);
		} catch (Exception ex2) {
			log.error("[addRoomStream]: " , ex2);
		}
		return null;
	}
}
