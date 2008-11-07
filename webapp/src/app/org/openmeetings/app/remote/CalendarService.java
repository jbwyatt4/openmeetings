package org.openmeetings.app.remote;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmeetings.app.data.basic.AuthLevelmanagement;
import org.openmeetings.app.data.basic.Sessionmanagement;
import org.openmeetings.app.data.calendar.management.AppointmentLogic;
import org.openmeetings.app.data.calendar.management.MeetingMemberLogic;
import org.openmeetings.app.data.user.Usermanagement;
import org.openmeetings.app.hibernate.beans.calendar.Appointment;
import org.openmeetings.app.hibernate.beans.calendar.AppointmentReminderTyps;

public class CalendarService {
	
	private static final Log log = LogFactory.getLog(CalendarService.class);
	
	private static CalendarService instance = null;

	public static synchronized CalendarService getInstance() {
		if (instance == null) {
			instance = new CalendarService();
		}

		return instance;
	}

	public List<Appointment> getAppointmentByRange(String SID, Date starttime, Date endtime) {
		try {
			Long users_id = Sessionmanagement.getInstance().checkSession(SID);
	        Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);
	        if (AuthLevelmanagement.getInstance().checkUserLevel(user_level)) {
					        	
	        	return AppointmentLogic.getInstance().getAppointmentByRange(users_id ,starttime, endtime);
	        }
		} catch (Exception err) {
			log.error("[getAppointmentByRange]",err);
		}
		return null;
	}
	
	public List<AppointmentReminderTyps> getAppointmentReminderTypList(String SID, Long userId) {
		try {
			Long users_id = Sessionmanagement.getInstance().checkSession(SID);
	        Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);
	        if (AuthLevelmanagement.getInstance().checkUserLevel(user_level)) {
					        	
	        	return AppointmentLogic.getInstance().getAppointmentReminderTypList(userId);
	        }
		} catch (Exception err) {
			log.error("[getAppointmentReminderTypList]",err);
		}
		return null;
	}
	
	public Appointment getNextAppointment(String SID){
		
		try{
			
			Long users_id = Sessionmanagement.getInstance().checkSession(SID);
	        Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);
	        if (AuthLevelmanagement.getInstance().checkUserLevel(user_level)) {
					        	
	        	return AppointmentLogic.getInstance().getNextAppointment();
	        }
		} catch (Exception err) {
			log.error("[getNextAppointmentById]",err);
		}
		return null;
			
		}
	
	public List<Appointment> searchAppointmentByName(String SID, String appointmentName){
			
			try{
				
				Long users_id = Sessionmanagement.getInstance().checkSession(SID);
		        Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);
		        if (AuthLevelmanagement.getInstance().checkUserLevel(user_level)) {
						        	
		        	return AppointmentLogic.getInstance().searchAppointmentByName(appointmentName);
		        }
			} catch (Exception err) {
				log.error("[searchAppointmentByName]",err);
			}
			return null;
				
			}
	
	public Long saveAppointment(String SID, String appointmentName,Long userId, String appointmentLocation,String appointmentDescription, 
			Date appointmentstart, Date appointmentend, 
			Boolean isDaily, Boolean isWeekly, Boolean isMonthly, Boolean isYearly, Long categoryId, Long remind){
		
		try{
			
			Long users_id = Sessionmanagement.getInstance().checkSession(SID);
	        Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);
	        if (AuthLevelmanagement.getInstance().checkUserLevel(user_level)) {
					        	
	        return	 AppointmentLogic.getInstance().saveAppointment(appointmentName, userId, appointmentLocation, 
	        			appointmentDescription, appointmentstart, appointmentend, isDaily, isWeekly, isMonthly, 
	        			isYearly, categoryId, remind);
	        }
		} catch (Exception err) {
			log.error("[saveAppointment]",err);
		}
		return null;
		
			
		}
	
	public Long updateAppointment(String SID,Long appointmentId ,String appointmentName, Long userId, String appointmentLocation,String appointmentDescription, 
			Date appointmentstart, Date appointmentend, 
			Boolean isDaily, Boolean isWeekly, Boolean isMonthly, Boolean isYearly, Long categoryId, Long remind){
		
		try{
			
			Long users_id = Sessionmanagement.getInstance().checkSession(SID);
	        Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);
	        if (AuthLevelmanagement.getInstance().checkUserLevel(user_level)) {
					        	
	        	return AppointmentLogic.getInstance().updateAppointment(appointmentId, appointmentName, userId, 
	        			 appointmentDescription, appointmentstart, appointmentend, isDaily, isWeekly, isMonthly, 
	        			 isYearly, categoryId, remind);
	        }
		} catch (Exception err) {
			log.error("[updateAppointment]",err);
		}
		return null;
		
			
		}
	
	public Long deleteAppointment(String SID,Long appointmentId){
		
		try{
			
			Long users_id = Sessionmanagement.getInstance().checkSession(SID);
	        Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);
	        if (AuthLevelmanagement.getInstance().checkUserLevel(user_level)) {
					        	
	        	return AppointmentLogic.getInstance().deleteAppointment(appointmentId);
	        }
		} catch (Exception err) {
			log.error("[deleteAppointment]",err);
		}
		return null;
		
			
		}
	
	public Long addMeetingMember(String SID, String firstname, String lastname, String memberStatus,
			String appointmentStatus, Long appointmentId, Long userid, String email){
		
		try{
			
			Long users_id = Sessionmanagement.getInstance().checkSession(SID);
	        Long user_level = Usermanagement.getInstance().getUserLevelByID(users_id);
	        if (AuthLevelmanagement.getInstance().checkUserLevel(user_level)) {
					        	
	        return	 MeetingMemberLogic.getInstance().addMeetingMember( firstname,  lastname,  memberStatus,
	    			 appointmentStatus,  appointmentId,  userid,  email);
	        }
		} catch (Exception err) {
			log.error("[addMeetingMember]",err);
		}
		return null;
	
	}
}
