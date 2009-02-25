
package org.openmeetings.app.data.calendar.management;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.red5.logging.Red5LoggerFactory;
import org.openmeetings.app.data.calendar.daos.AppointmentDaoImpl;
import org.openmeetings.app.data.calendar.daos.AppointmentReminderTypDaoImpl;
import org.openmeetings.app.data.conference.Roommanagement;
import org.openmeetings.app.hibernate.beans.calendar.Appointment;
import org.openmeetings.app.hibernate.beans.calendar.AppointmentReminderTyps;
import org.openmeetings.app.hibernate.beans.rooms.Rooms;


public class AppointmentLogic {
	
	private static final Logger log = Red5LoggerFactory.getLogger(AppointmentLogic.class, "openmeetings");
	private static AppointmentLogic instance = null;

	public static synchronized AppointmentLogic getInstance() {
		if (instance == null) {
			instance = new AppointmentLogic();
		}

		return instance;
	}
	
	public List<Appointment> getAppointmentByRange(Long userId ,Date starttime, Date endtime){
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
	
	public Long saveAppointment(String appointmentName, Long userId, String appointmentLocation,String appointmentDescription, 
			Date appointmentstart, Date appointmentend, 
			Boolean isDaily, Boolean isWeekly, Boolean isMonthly, Boolean isYearly, Long categoryId, Long remind){
		
		log.debug("Appointmentlogic.saveAppointment");
		
		// create a Room
		Long room_id = Roommanagement.getInstance().addRoom(
				3,					// Userlevel
				appointmentName,	// name	
				1,					// RoomType	
				"",					// Comment
				new Long(8),		// Number of participants
				true,				// public
				null,				// Organisations
				270,				// Video Width
				280,				// Video height
				2,					// Video X
				2,					// Video Y
				400,				// Modeartionpanel X
				true,				// Whiteboard
				276,				// Whiteboard x
				2,					// Whiteboard y
				592,				// WB height
				660,				// WB width
				true,				// Show Files Panel
				2,					// Files X
				284,				// Files Y
				310,				// Files height
				270,				// Files width
				true);				// Appointment
		
		log.debug("Appointmentlogic.saveAppointment : Room - " + room_id);
		
		Rooms room = Roommanagement.getInstance().getRoomById(room_id);
		
		if(room == null)
			log.error("Room " + room_id + " could not be found!");
		else
			log.debug("Room " + room_id + " ok!");
		
		try{
			return AppointmentDaoImpl.getInstance().addAppointment(appointmentName, userId, appointmentLocation, appointmentDescription,
				appointmentstart, appointmentend, isDaily, isWeekly, isMonthly, isYearly, categoryId, remind, room);
		}catch(Exception err){
			log.error("[saveAppointment]",err);
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
	
	public Long updateAppointment(Long appointmentId, String appointmentName, String appointmentDescription, 
			Date appointmentstart, Date appointmentend,
			Boolean isDaily, Boolean isWeekly, Boolean isMonthly, Boolean isYearly, Long categoryId, Long remind, List mmClient ){
		
		try {
			return AppointmentDaoImpl.getInstance().updateAppointment(appointmentId, 
					appointmentName, appointmentDescription, appointmentstart, 
					appointmentend, isDaily, isWeekly, isMonthly, isYearly, categoryId, remind, 
					mmClient);
		} catch (Exception err) {
			log.error("[updateAppointment]",err);
		}
		return null;
	}
}
