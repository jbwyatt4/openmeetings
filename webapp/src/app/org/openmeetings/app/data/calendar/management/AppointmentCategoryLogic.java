
package org.openmeetings.app.data.calendar.management;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmeetings.app.data.calendar.daos.AppointmentCategoryDaoImpl;
import org.openmeetings.app.data.calendar.daos.AppointmentDaoImpl;
import org.openmeetings.app.hibernate.beans.calendar.Appointment;
import org.openmeetings.app.hibernate.beans.calendar.AppointmentCategory;

public class AppointmentCategoryLogic {
	
	private static final Log log = LogFactory.getLog(AppointmentCategoryLogic.class);
	private static AppointmentCategoryLogic instance = null;

	public static synchronized AppointmentCategoryLogic getInstance() {
		if (instance == null) {
			instance = new AppointmentCategoryLogic();
		}

		return instance;
	}
	
	public List<AppointmentCategory> getAppointmentCategoryList(Long userId ){
		try {	
			return AppointmentCategoryDaoImpl.getInstance().getAppointmentCategoryList(userId);
		}catch(Exception err){
			log.error("[getAppointmentCategory]",err);
		}
		return null;
	}
	
	
	
/*	
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
	
	public void saveAppointment(String appointmentName, Long userId, String appointmentLocation,String appointmentDescription, 
			Date appointmentstart, Date appointmentend, 
			Boolean isDaily, Boolean isWeekly, Boolean isMonthly, Boolean isYearly, Long categoryId){
		
		try{
		AppointmentDaoImpl.getInstance().addAppointment(appointmentName, userId, appointmentLocation, appointmentDescription,
				appointmentstart, appointmentend, isDaily, isWeekly, isMonthly, isYearly, categoryId);
		}catch(Exception err){
			log.error("[saveAppointment]",err);
		}
	}
	
	public void deleteAppointment(Long appointmentId){
		try{
		AppointmentDaoImpl.getInstance().deleteAppointement(appointmentId);
		}catch(Exception err){
		log.error("[deleteAppointment]",err);	
		}
		
	}
	
	public void updateAppointment(Long appointmentId, String appointmentName,Long userId, String appointmentDescription, 
			Date appointmentstart, Date appointmentend,
			Boolean isDaily, Boolean isWeekly, Boolean isMonthly, Boolean isYearly, Long categoryId ){
		
		try {
			AppointmentDaoImpl.getInstance().updateAppointment(appointmentId,
					appointmentName, userId, appointmentDescription, appointmentstart,
					appointmentend, isDaily, isWeekly, isMonthly, isYearly,
					categoryId);
		} catch (Exception err) {
			log.error("[updateAppointment]",err);
		}
	} 
	*/
}
