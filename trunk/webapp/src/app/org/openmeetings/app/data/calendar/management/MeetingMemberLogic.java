
package org.openmeetings.app.data.calendar.management;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmeetings.app.data.calendar.daos.AppointmentDaoImpl;
import org.openmeetings.app.data.calendar.daos.MeetingMemberDaoImpl;
import org.openmeetings.app.hibernate.beans.calendar.Appointment;

public class MeetingMemberLogic {
	
	private static final Log log = LogFactory.getLog(MeetingMemberLogic.class);
	private static MeetingMemberLogic instance = null;

	public static synchronized MeetingMemberLogic getInstance() {
		if (instance == null) {
			instance = new MeetingMemberLogic();
		}

		return instance;
	}
	
	public Long addMeetingMember(String firstname, String lastname, String memberStatus,
			String appointmentStatus, Long appointmentId, Long userid, String email){
		
		try{
		return MeetingMemberDaoImpl.getInstance().addMeetingMember(firstname,  lastname,  memberStatus,
				 appointmentStatus,  appointmentId,  userid,  email);
		}catch(Exception err){
			log.error("[addMeetingMember]",err);
		}
		return null;
	}
	
	public Long updateMeetingMember(Long meetingMemberId, String firstname, String lastname, 
			 String memberStatus, String appointmentStatus, 
			 Long appointmentId, Long userid, String email ){
		
		try {
			return MeetingMemberDaoImpl.getInstance().updateMeetingMember(meetingMemberId,
					firstname, lastname, memberStatus, appointmentStatus, appointmentId, userid, email);
		} catch (Exception err) {
			log.error("[updateMeetingMember]",err);
		}
		return null;
	}
	
	
	
	public Long deleteMeetingMember(Long meetingMemberId ){
		
		try {
			return MeetingMemberDaoImpl.getInstance().deleteMeetingMember(meetingMemberId);
		} catch (Exception err) {
			log.error("[deleteMeetingMember]",err);
		}
		return null;
	}
	
/*	public List<Appointment> getAppointmentByRange(Long userId ,Date starttime, Date endtime){
		try {	
			return AppointmentDaoImpl.getInstance().getAppointmentsByRange(userId, starttime, endtime);
		}catch(Exception err){
			log.error("[getAppointmentByRange]",err);
		}
		return null;
	}
	//next appointment to current date
	public Appointment getNextAppointment(){
		try{
		return AppointmentDaoImpl.getInstance().getNextAppointment(new Date());
		}catch(Exception err){
			log.error("[getNextAppointmentById]",err);
		}	
		return null;
	}
	
	public List<Appointment> searchAppointmentByName(String appointmentName){
		try{
		return AppointmentDaoImpl.getInstance().searchAppointmentsByName(appointmentName) ;
		}catch(Exception err){
			log.error("[searchAppointmentByName]",err);	
		}
		return null;
	}
	
	
	
	public Long deleteAppointment(Long appointmentId){
		try{
		AppointmentDaoImpl.getInstance().deleteAppointement(appointmentId);
		return appointmentId;
		}catch(Exception err){
		log.error("[deleteAppointment]",err);	
		}
		return null;
		
	}
	
	public Long updateAppointment(Long appointmentId, String appointmentName,Long userId, String appointmentDescription, 
			Date appointmentstart, Date appointmentend,
			Boolean isDaily, Boolean isWeekly, Boolean isMonthly, Boolean isYearly, Long categoryId ){
		
		try {
			return AppointmentDaoImpl.getInstance().updateAppointment(appointmentId,
					appointmentName, userId, appointmentDescription, appointmentstart,
					appointmentend, isDaily, isWeekly, isMonthly, isYearly,
					categoryId);
		} catch (Exception err) {
			log.error("[updateAppointment]",err);
		}
		return null;
	}
	*/
}
