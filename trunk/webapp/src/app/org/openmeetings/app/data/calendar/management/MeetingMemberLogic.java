
package org.openmeetings.app.data.calendar.management;


import java.util.Date;

import org.slf4j.Logger;
import org.red5.logging.Red5LoggerFactory;
import org.openmeetings.app.data.calendar.daos.MeetingMemberDaoImpl;
import org.openmeetings.app.data.conference.Invitationmanagement;
import org.openmeetings.app.hibernate.beans.calendar.Appointment;

public class MeetingMemberLogic {
	
	private static final Logger log = Red5LoggerFactory.getLogger(MeetingMemberLogic.class, "openmeetings");
	private static MeetingMemberLogic instance = null;

	public static synchronized MeetingMemberLogic getInstance() {
		if (instance == null) {
			instance = new MeetingMemberLogic();
		}

		return instance;
	}
	
	public Long addMeetingMember(String firstname, String lastname, String memberStatus,
			String appointmentStatus, Long appointmentId, Long userid, String email, String baseUrl, Long meeting_organizer){
		
		try{
			Long memberId =  MeetingMemberDaoImpl.getInstance().addMeetingMember(firstname,  lastname,  memberStatus,
				 appointmentStatus,  appointmentId,  userid,  email);
		
			
			// DefaultInvitation
			Appointment point = AppointmentLogic.getInstance().getAppointMentById(appointmentId);
			
			
			if(point.getRemind().getTypId() == 1){
				log.debug("no reminder required");
			}
			else if(point.getRemind().getTypId() == 2){
				log.debug("Reminder for Appointment : simple email");
				
				Invitationmanagement.getInstance().addInvitationLink(
						new Long(2), //userlevel
						firstname + " " + lastname, //username
						"Invitation to an openMeetings Event : " + point.getAppointmentName() + ", " + point.getAppointmentDescription() + ", Start : " + point.getAppointmentStarttime() + ", End : " + point.getAppointmentEndtime(), //message
						baseUrl, // baseURl
						email, //email
						"Invitation to an openmeetings Event : " + point.getAppointmentName(), //subject
						point.getRoom().getRooms_id(), // room_id
						"public",
						false, // passwordprotected
						"", // invitationpass
						1, // valid
						point.getAppointmentStarttime(), // valid from
						point.getAppointmentEndtime(), // valid to
						new Long(1) // created by
						);
				
			}
			else if(point.getRemind().getTypId() == 3){
				log.debug("Reminder for Appointment : iCal mail");
				
				Invitationmanagement.getInstance().addInvitationIcalLink(new Long(2), //userlevel
						firstname + " " + lastname, //username
						"Invitation to an openMeetings Event : " + point.getAppointmentName() + ", " + point.getAppointmentDescription() + ", Start : " + point.getAppointmentStarttime() + ", End : " + point.getAppointmentEndtime(), //message
						baseUrl, // baseURl
						email, //email
						"Invitation to an openmeetings Event : " + point.getAppointmentName(), //subject
						point.getRoom().getRooms_id(), // room_id
						"public",
						false, // passwordprotected
						"", // invitationpass
						1, // valid
						point.getAppointmentStarttime(), // valid from
						point.getAppointmentEndtime(), // valid to
						new Long(1), // created by
						point.getAppointmentId()
					);
			}
			
			
			//Invitationmanagement.getInstance().addInvitationLink(new Long(1), firstname + " " + lastname, "Invitation Link openMeeting", baseUrl, email, "InvitationLink OpenMeetings", point.getRoom().getRooms_id(), null, false, null, null, null, null, 1);
			
			return memberId;
		
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
