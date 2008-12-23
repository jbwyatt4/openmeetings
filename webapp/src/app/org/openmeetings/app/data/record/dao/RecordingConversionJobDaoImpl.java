package org.openmeetings.app.data.record.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openmeetings.app.hibernate.beans.recording.Recording;
import org.openmeetings.app.hibernate.beans.recording.RecordingConversionJob;
import org.openmeetings.app.hibernate.utils.HibernateUtil;

public class RecordingConversionJobDaoImpl {
	
	private static final Log log = LogFactory.getLog(RecordingConversionJobDaoImpl.class);

	private RecordingConversionJobDaoImpl() {
	}

	private static RecordingConversionJobDaoImpl instance = null;

	public static synchronized RecordingConversionJobDaoImpl getInstance() {
		if (instance == null) {
			instance = new RecordingConversionJobDaoImpl();
		}

		return instance;
	}
	
	public Long addRecordingConversionJob(RecordingConversionJob recordingConversionJob) {
		try {
			
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			
			Long recordingConversionJobId = (Long) session.save(recordingConversionJob);
			
			tx.commit();
			HibernateUtil.closeSession(idf);
			
			return recordingConversionJobId;
		} catch (HibernateException ex) {
			log.error("[addRecordingConversionJob]: " , ex);
		} catch (Exception ex2) {
			log.error("[addRecordingConversionJob]: " , ex2);
		}
		return null;
	}
	
	public List<RecordingConversionJob> getRecordingConversionJobs() {
		try {
			
			String hql = "select c from RecordingConversionJob as c " +
						"where c.ended IS NULL ";
			
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			List<RecordingConversionJob> ll = query.list();
			tx.commit();
			HibernateUtil.closeSession(idf);
			
			return ll;
	
		} catch (HibernateException ex) {
			log.error("[getRecordingConversionJobs]: " , ex);
		} catch (Exception ex2) {
			log.error("[getRecordingConversionJobs]: " , ex2);
		}
		return null;
	}
	
	public void updateRecordingConversionJobs(RecordingConversionJob recordingConversionJob) {
		try {
			
			log.debug("updateRecordingConversionJobs: "+recordingConversionJob.getRecordingConversionJobId());
			
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.update(recordingConversionJob);
			tx.commit();
			HibernateUtil.closeSession(idf);
			
		} catch (HibernateException ex) {
			log.error("[updateRecordingConversionJobs]: " , ex);
		} catch (Exception ex2) {
			log.error("[updateRecordingConversionJobs]: " , ex2);
		}
		
	}
	
}
