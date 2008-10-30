package org.openmeetings.app.hibernate.beans.calendar;

import java.util.Date;

import org.openmeetings.app.hibernate.beans.adresses.Adresses;

/**
 * 
 * @hibernate.class table="group_members"
 * lazy="false"
 *
 */

public class GroupMember {
	
	private Long groupMemberId;
	private String firstname;
	private String lastname;
	private Date age;
	private String memberStatus; // internal, external.
	private String appointmentStatus; //status of the appointment denial, acceptance, wait. 
	private String password;
	private Long languageId;
	private Adresses addresses;
	private Appointment appointment;
		
	private Date starttime;
	private Date updatetime;
	private String deleted;
	private String comment;
	
	/**
     * 
     * @hibernate.id
     *  column="group_member_id"
     *  generator-class="increment"
     */  
	public Long getGroupMemberId() {
		return groupMemberId;
	}
	public void setGroupMemberId(Long groupMemberId) {
		this.groupMemberId = groupMemberId;
	}
	/**
     * @hibernate.property
     *  column="firstname"
     *  type="string"
     */ 
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	/**
     * @hibernate.property
     *  column="lastname"
     *  type="string"
     */ 
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
     * @hibernate.property
     *  column="age"
     *  type="java.util.Date"
     */ 
	public Date getAge() {
		return age;
	}
	public void setAge(Date age) {
		this.age = age;
	}
	/**
     * @hibernate.property
     *  column="member_status"
     *  type="string"
     */ 
	public String getMemberStatus() {
		return memberStatus;
	}
	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}
	
	/**
     * @hibernate.property
     *  column="appointment_status"
     *  type="string"
     */ 
   public String getAppointmentStatus() {
		return appointmentStatus;
	}
	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}
	
	/**
     * @hibernate.property
     *  column="password"
     *  type="string"
     */ 
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
     * @hibernate.property
     *  column="language_id"
     *  type="string"
     */ 
	public Long getLanguageId() {
		return languageId;
	}
	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}
	
	/**
     * @hibernate.many-to-one
     *  cascade="none"
     *  column="adresses_id"
     *  lazy="false"
     *  class="org.openmeetings.app.hibernate.beans.adresses.Adresses"
     *  not-null="false"
     *  outer-join="true"
     */ 
	public Adresses getAddresses() {
		return addresses;
	}
	public void setAddresses(Adresses addresses) {
		this.addresses = addresses;
	}
	
    /**
     * @hibernate.many-to-one
     *  cascade="none"
     *  column="appointment_id"
     *  lazy="false"
     *  class="org.openmeetings.app.hibernate.beans.calendar.Appointment"
     *  not-null="false"
     *  outer-join="true"
     */ 
	public Appointment getAppointment() {
		return appointment;
	}
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
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
	
	

}
