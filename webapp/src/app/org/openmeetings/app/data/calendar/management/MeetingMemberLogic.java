
package org.openmeetings.app.data.calendar.management;


import java.util.Date;

import org.slf4j.Logger;
import org.red5.logging.Red5LoggerFactory;
import org.openmeetings.app.data.calendar.daos.MeetingMemberDaoImpl;
import org.openmeetings.app.data.conference.Invitationmanagement;
import org.openmeetings.app.hibernate.beans.calendar.Appointment;
import org.openmeetings.app.hibernate.beans.calendar.MeetingMember;
import org.openmeetings.app.hibernate.beans.invitation.Invitations;

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
			
			Long invitationId = null;
			
			
			if(point.getRemind().getTypId() == 1){
				log.debug("no reminder required");
			}
			else if(point.getRemind().getTypId() == 2){
				log.debug("Reminder for Appointment : simple email");
				invitationId = Invitationmanagement.getInstance().addInvitationLink(
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
				
				invitationId = Invitationmanagement.getInstance().addInvitationIcalLink(new Long(2), //userlevel
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
			
			// Setting InvitationId within MeetingMember
			
			if(invitationId != null){
				MeetingMember member = getMemberById(memberId);
				Invitations invi = Invitationmanagement.getInstance().getInvitationbyId(invitationId);
			
				member.setInvitation(invi);
				
				updateMeetingMember(member);
				
			}
			
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
	
	/**
	 * @author becherer
	 * @param member
	 * @return
	 */
	//--------------------------------------------------------------------------------------------
	public Long updateMeetingMember(MeetingMember member){
		log.debug("updateMeetingMember");
		
		return MeetingMemberDaoImpl.getInstance().updateMeetingMember(member).getMeetingMemberId();
	}
	//--------------------------------------------------------------------------------------------
	
	/**
	 * @author becherer
	 * @param memberId
	 */
	//--------------------------------------------------------------------------------------------
	public MeetingMember getMemberById(Long memberId){
		log.debug("getMemberById");
		
		return MeetingMemberDaoImpl.getInstance().getMeetingMemberById(memberId);
	}
	//--------------------------------------------------------------------------------------------
	
	
	/**
	 * 
	 * @param meetingMemberId
	 * @return
	 */
	public Long deleteMeetingMember(Long meetingMemberId ){
		log.debug("meetingMemberLogic.deleteMeetingMember");
		
		try {
			
			MeetingMember member = MeetingMemberDaoImpl.getInstance().getMeetingMemberById(meetingMemberId);
			
			if(member == null){
				log.error("could not find meeting member!");
				return null;
			}
			
			Long returnValue =  MeetingMemberDaoImpl.getInstance().deleteMeetingMember(meetingMemberId);
			
			Appointment point = member.getAppointment();
			
			// cancel invitation
			Invitationmanagement.getInstance().cancelInvitation(point, member);
			
			return returnValue;
			
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
