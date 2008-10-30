package org.openmeetings.app.hibernate.beans.calendar;



import java.util.Date;

import org.openmeetings.app.hibernate.beans.user.Users;

/**
 * 
 * @hibernate.class table="appointments"
 * lazy="false"
 *
 */

public class Appointment {
	

	private Long appointmentId;
	private String appointmentName;
	private String appointmentLocation;
	private Date appointmentStarttime;
	private Date appointmentEndtime;
	private String appointmentDescription;
	private AppointmentCategory appointmentCategory; 
	private Users userId;
	
	private Date starttime;
	private Date updatetime;
	private String deleted;
	private String comment;
	
	private Boolean isDaily;
	private Boolean isWeekly;
	private Boolean isMonthly;
	private Boolean isYearly;
	


	/**
     * 
     * @hibernate.id
     *  column="appointment_id"
     *  generator-class="increment"
     */  
	public Long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}
	
	/**
     * @hibernate.many-to-one
     *  cascade="none"
     *  column="user_id"
     *  lazy="false"
     *  class="org.openmeetings.app.hibernate.beans.user.Users"
     *  not-null="false"
     *  outer-join="true"
     */ 
	public Users getUserId() {
		return userId;
	}
	public void setUserId(Users userId) {
		this.userId = userId;
	}
	/**
     * @hibernate.property
     *  column="appointmentname"
     *  type="string"
     */ 
	public String getAppointmentName() {
		return appointmentName;
	}
	public void setAppointmentName(String appointmentName) {
		this.appointmentName = appointmentName;
	}
	/**
     * @hibernate.property
     *  column="location"
     *  type="string"
     */ 
	public String getAppointmentLocation() {
		return appointmentLocation;
	}



	public void setAppointmentLocation(String appointmentLocation) {
		this.appointmentLocation = appointmentLocation;
	}


	/**
     * @hibernate.property
     *  column="appointment_starttime"
     *  type="java.util.Date"
     */ 
	public Date getAppointmentStarttime() {
		return appointmentStarttime;
	}



	public void setAppointmentStarttime(Date appointmentStarttime) {
		this.appointmentStarttime = appointmentStarttime;
	}


	/**
     * @hibernate.property
     *  column="appointment_endtime"
     *  type="java.util.Date"
     */ 
	public Date getAppointmentEndtime() {
		return appointmentEndtime;
	}



	public void setAppointmentEndtime(Date appointmentEndtime) {
		this.appointmentEndtime = appointmentEndtime;
	}


	/**
     * @hibernate.property
     *  column="description"
     *  type="string"
     */ 
	public String getAppointmentDescription() {
		return appointmentDescription;
	}



	public void setAppointmentDescription(String appointmentDescription) {
		this.appointmentDescription = appointmentDescription;
	}


	/**
     * @hibernate.many-to-one
     *  cascade="none"
     *  column="category_id"
     *  lazy="false"
     *  class="org.openmeetings.app.hibernate.beans.calendar.AppointmentCategory"
     *  not-null="false"
     *  outer-join="true"
     */ 
	public AppointmentCategory getAppointmentCategory() {
		return appointmentCategory;
	}



	public void setAppointmentCategory(AppointmentCategory appointmentCategory) {
		this.appointmentCategory = appointmentCategory;
	}


	 /**
     * @hibernate.property
     *  column="starttime"
     *  type="java.util.Date"
     */  
	public Date getStarttime() {
		return starttime;
	}



	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}


	/**
     * @hibernate.property
     *  column="updatetime"
     *  type="java.util.Date"
     */  
	public Date getUpdatetime() {
		return updatetime;
	}



	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}


	/**
     * @hibernate.property
     *  column="deleted"
     *  type="string"
     */	
	public String getDeleted() {
		return deleted;
	}



	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}


	/**
     * @hibernate.property
     *  column="comment"
     *  type="string"
     */ 
	public String getComment() {
		return comment;
	}



	public void setComment(String comment) {
		this.comment = comment;
	}
	
	/**
     * @hibernate.property
     *  column="isweekly"
     *  type="boolean"
     */ 
	public Boolean getIsWeekly() {
		return isWeekly;
	}
	public void setIsWeekly(Boolean isWeekly) {
		this.isWeekly = isWeekly;
	}
	
	/**
     * @hibernate.property
     *  column="ismonthly"
     *  type="boolean"
     */ 
	public Boolean getIsMonthly() {
		return isMonthly;
	}
	public void setIsMonthly(Boolean isMonthly) {
		this.isMonthly = isMonthly;
	}
	
	/**
     * @hibernate.property
     *  column="isyearly"
     *  type="boolean"
     */ 
	public Boolean getIsYearly() {
		return isYearly;
	}
	public void setIsYearly(Boolean isYearly) {
		this.isYearly = isYearly;
	}
	
	/**
     * @hibernate.property
     *  column="isdaily"
     *  type="boolean"
     */ 
	public Boolean getIsDaily() {
		return isDaily;
	}
	public void setIsDaily(Boolean isDaily) {
		this.isDaily = isDaily;
	}
	
	
}


