package org.xmlcrm.app.data.record;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.xmlcrm.app.data.conference.Roommanagement;
import org.xmlcrm.app.hibernate.beans.recording.Recording;
import org.xmlcrm.app.hibernate.beans.rooms.Rooms;
import org.xmlcrm.app.hibernate.utils.HibernateUtil;

public class Recordingmanagement {

	private static final Log log = LogFactory.getLog(Recordingmanagement.class);

	private static Recordingmanagement instance;

	private Recordingmanagement() {}

	public static synchronized Recordingmanagement getInstance() {
		if (instance == null) {
			instance = new Recordingmanagement();
		}
		return instance;
	}
	
	public Long addRecording(String name, Long duration, String xmlString, Long rooms_id) throws Exception{
		Recording recording = new Recording();
		recording.setDeleted("false");
		recording.setDuration(duration);
		recording.setComment("");
		recording.setName(name);
		recording.setXmlString(xmlString);
		recording.setRooms(Roommanagement.getInstance().getRoomById(rooms_id));
		recording.setStarttime(new java.util.Date());
		return this.addRecording(recording);
	}
	
	public Long addRecording(Recording recording) {
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Long recording_id = (Long) session.save(recording);
			tx.commit();
			HibernateUtil.closeSession(idf);
			return recording_id;
		} catch (HibernateException ex) {
			log.error("[addRecording] ",ex);
		} catch (Exception ex2) {
			log.error("[addRecording] ",ex2);
		}
		return new Long(-1);
	}
}
