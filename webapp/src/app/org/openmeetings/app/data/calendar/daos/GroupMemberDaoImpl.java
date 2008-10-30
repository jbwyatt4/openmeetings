package org.openmeetings.app.data.calendar.daos;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openmeetings.app.data.basic.Configurationmanagement;
import org.openmeetings.app.data.basic.Languagemanagement;
import org.openmeetings.app.data.user.Addressmanagement;
import org.openmeetings.app.hibernate.beans.adresses.Adresses;
import org.openmeetings.app.hibernate.beans.calendar.Appointment;
import org.openmeetings.app.hibernate.beans.calendar.AppointmentCategory;
import org.openmeetings.app.hibernate.beans.calendar.GroupMember;
import org.openmeetings.app.hibernate.utils.HibernateUtil;

public class GroupMemberDaoImpl {
	
	private static final Log log = LogFactory.getLog(Configurationmanagement.class);

	private GroupMemberDaoImpl() {
	}

	private static GroupMemberDaoImpl instance = null;

	public static synchronized GroupMemberDaoImpl getInstance() {
		if (instance == null) {
			instance = new GroupMemberDaoImpl();
		}

		return instance;
	}
	
	public GroupMember getGroupMemberById(Long groupMemberId) {
		try {
			log.debug("getAppointmentCategoryById: "+ groupMemberId);
			
			String hql = "select app from GroupMember app " +
					"WHERE app.deleted != :deleted " +
					"AND app.groupMemberId = :groupMemberId";
			
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setString("deleted", "true");
			query.setLong("groupMemberId",groupMemberId);
			
			GroupMember groupMember = (GroupMember) query.uniqueResult();
			tx.commit();
			HibernateUtil.closeSession(idf);
			
			return groupMember;
		} catch (HibernateException ex) {
			log.error("[getGroupMemberById]: " + ex);
		} catch (Exception ex2) {
			log.error("[getGroupMemberById]: " + ex2);
		}
		return null;
	}
	
	public void updateGroupMember(GroupMember groupmember) {
		if (groupmember.getGroupMemberId() > 0) {
			try {
				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				session.update(groupmember);
				tx.commit();
				HibernateUtil.closeSession(idf);
			} catch (HibernateException ex) {
				log.error("[updateGroupMember] ",ex);
			} catch (Exception ex2) {
				log.error("[updateGroupMember] ",ex2);
			}
		} else {
			log.error("[updateUser] "+"Error: No GroupMemberId given");
		}
	}
	
	public void updateGroupMember(Long groupMemberId, String firstname, String lastname, 
			Date age, String memberStatus,String appointmentStatus, String password, 
			Long adresses_id, Long appointmentId) {
		try {
			
			
			GroupMember gm = this.getGroupMemberById(groupMemberId);
			/*
			if (gm == null) {
				log.debug("ALERT Object with ID: "+ groupMemberId +" does not exist yet");
				return null;
			}*/
									
			gm.setFirstname(firstname);
			gm.setLastname(lastname);
			gm.setAge(age);
			gm.setAddresses(Addressmanagement.getInstance().getAdressbyId(adresses_id));
			//gm.setLanguageId(Languagemanagement.getInstance().getFieldLanguageById(languageId));
			gm.setMemberStatus(memberStatus);
			gm.setAppointmentStatus(appointmentStatus);
			gm.setAppointment(AppointmentDaoImpl.getInstance().getAppointmentById(appointmentId));
			gm.setPassword(password);
								
			
			gm.setDeleted("false");
			gm.setUpdatetime(new Date());
			
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			
			session.update(gm);

			tx.commit();
			HibernateUtil.closeSession(idf);
			
			
		} catch (HibernateException ex) {
			log.error("[updateGroupMember]: ",ex);
		} catch (Exception ex2) {
			log.error("[updateGroupMember]: ",ex2);
		}
		
	}
	
	public Long addGroupMember(String firstname, String lastname, 
			Date age, String memberStatus,String appointmentStatus, String password, 
			Long adresses_id, Long appointmentId) {
		try {
			
			GroupMember gm = new GroupMember();
			
			gm.setFirstname(firstname);
			gm.setLastname(lastname);
			gm.setAge(age);
			gm.setAddresses(Addressmanagement.getInstance().getAdressbyId(adresses_id));
			//gm.setLanguageId(Languagemanagement.getInstance().getFieldLanguageById(languageId));
			gm.setMemberStatus(memberStatus);
			gm.setAppointmentStatus(appointmentStatus);
			gm.setAppointment(AppointmentDaoImpl.getInstance().getAppointmentById(appointmentId));
			gm.setPassword(password);
								
			gm.setStarttime(new Date());
			gm.setDeleted("false");
						
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			
			Long group_member_id = (Long)session.save(gm);

			tx.commit();
			HibernateUtil.closeSession(idf);
			
			return group_member_id;
		} catch (HibernateException ex) {
			log.error("[addGroupMember]: ",ex);
		} catch (Exception ex2) {
			log.error("[addGroupMember]: ",ex2);
		}
		return null;
	}
	
	public void deleteGroupMember(Long groupMemberId) {
		try {
			
			GroupMember gm = this.getGroupMemberById(groupMemberId);
			
			log.debug("ac: "+gm);
			
			if (gm == null) {
				log.debug("Already deleted / Could not find: "+groupMemberId);
				return;
			}
			gm.setUpdatetime(new Date());
			gm.setDeleted("true");
			
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.update(gm);
						
			tx.commit();
			HibernateUtil.closeSession(idf);
			
		} catch (HibernateException ex) {
			log.error("[deleteAppointementCategory]: " + ex);
		} catch (Exception ex2) {
			log.error("[deleteAppointementCategory]: " + ex2);
		}
	}

}
