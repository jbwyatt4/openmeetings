package org.xmlcrm.app.data.record;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.xmlcrm.app.data.conference.Roommanagement;
import org.xmlcrm.app.hibernate.beans.recording.Recording;
import org.xmlcrm.app.hibernate.beans.rooms.Rooms;
import org.xmlcrm.app.hibernate.beans.user.Users;
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
	
	public Long addRecording(String name, Long duration, String xmlString, Long rooms_id, Users recordedby, String  comment) throws Exception{
		Recording recording = new Recording();
		recording.setDeleted("false");
		recording.setDuration(duration);
		recording.setComment(comment);
		recording.setRecordedby(recordedby);
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
	
	public List<Recording> getRecordings(){
		try {
			String hql = "select c from Recording as c where c.deleted != :deleted";
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setString("deleted", "true");
			List<Recording> ll = query.list();
			tx.commit();
			HibernateUtil.closeSession(idf);
			return ll;
		} catch (HibernateException ex) {
			log.error(ex);
		} catch (Exception ex2) {
			log.error(ex2);
		}
		return null;
	}
	
	public List<Recording> getRecordingsByRoom(Long rooms_id){
		try {
			String hql = "select c from Recording as c where c.rooms.rooms_id = :rooms_id AND c.deleted != :deleted";
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setLong("rooms_id", rooms_id);
			query.setString("deleted", "true");
			List<Recording> ll = query.list();
			tx.commit();
			HibernateUtil.closeSession(idf);
			return ll;
		} catch (HibernateException ex) {
			log.error(ex);
		} catch (Exception ex2) {
			log.error(ex2);
		}
		return null;
	}
	
	public List<Recording> getRecordingsByWhereClause(String where){
		try {
			String hql = "select c from Recording as c where " + where + " c.deleted != :deleted";
			log.error("getRecordingsByWhereClause: "+hql);
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setString("deleted", "true");
			List<Recording> ll = query.list();
			tx.commit();
			HibernateUtil.closeSession(idf);
			return ll;
		} catch (HibernateException ex) {
			log.error(ex);
		} catch (Exception ex2) {
			log.error(ex2);
		}
		return null;
	}
	
	
	public Recording getRecordingById(Long recording_id) {
		try {
			String hql = "select c from Recording as c where c.recording_id = :recording_id AND deleted != :deleted";
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setLong("recording_id", recording_id);
			query.setString("deleted", "true");
			Recording rec = (Recording) query.uniqueResult();
			tx.commit();
			HibernateUtil.closeSession(idf);
			return rec;
		} catch (HibernateException ex) {
			log.error(ex);
		} catch (Exception ex2) {
			log.error(ex2);
		}
		return null;
	}	
	
	public void updateRecording(Recording rec){
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.update(rec);
			tx.commit();
			HibernateUtil.closeSession(idf);
		} catch (HibernateException ex) {
			log.error(ex);
		} catch (Exception ex2) {
			log.error(ex2);
		}
	}
	
	
}
