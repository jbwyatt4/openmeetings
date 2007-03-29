package org.xmlcrm.app.hibernate.beans.termine;

import java.util.Date;
import org.xmlcrm.app.hibernate.beans.user.Users;

/**
 * 
 * @hibernate.class table="termine"
 *
 */
public class Termine {
	
	private int termin_id;
	private String comment;
	private String description;
	private Date endtermin;
	private Integer open;
	private Long owner_id;
	private String place;
	private Date starttermin;
	private Long status_id;
	private Date starttime;
	private Date  updatetime;
	
    private String starthour;
    private String startmin;
    private String startday;
    private String startmonth;
    private String startyear;
    private String endhour;
    private String endmin;
    private String endday;
    private String endmonth;
    private String endyear;   
    private String durationhour;
    private String durationminutes;
    private int duration; 
    private String deleted;
	
    private Users owner_user;
    private int visualrowlength;
    private Terminestatus termine_status;

	public Termine() {
		super();
		// TODO Auto-generated constructor stub
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
     *  column="description"
     *  type="string"
     */ 	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public String getDurationhour() {
		return durationhour;
	}
	public void setDurationhour(String durationhour) {
		this.durationhour = durationhour;
	}
	
	public String getDurationminutes() {
		return durationminutes;
	}
	public void setDurationminutes(String durationminutes) {
		this.durationminutes = durationminutes;
	}
	
	public String getEndday() {
		return endday;
	}
	public void setEndday(String endday) {
		this.endday = endday;
	}
	
	public String getEndhour() {
		return endhour;
	}
	public void setEndhour(String endhour) {
		this.endhour = endhour;
	}
	
	public String getEndmin() {
		return endmin;
	}
	public void setEndmin(String endmin) {
		this.endmin = endmin;
	}
	
	public String getEndmonth() {
		return endmonth;
	}
	public void setEndmonth(String endmonth) {
		this.endmonth = endmonth;
	}
    
    /**
     * @hibernate.property
     *  column="endtermin"
     *  type="java.util.Date"
     */ 
	public Date getEndtermin() {
		return endtermin;
	}
	public void setEndtermin(Date endtermin) {
		this.endtermin = endtermin;
	}
	
	public String getEndyear() {
		return endyear;
	}
	public void setEndyear(String endyear) {
		this.endyear = endyear;
	}
    
    /**
     * @hibernate.property
     *  column="open"
     *  type="int"
     */ 	
	public Integer getOpen() {
		return open;
	}
	public void setOpen(Integer open) {
		this.open = open;
	}
    
    /**
     * @hibernate.property
     *  column="owner_id"
     *  type="long"
     */ 
	public Long getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(Long owner_id) {
		this.owner_id = owner_id;
	}
	
	public Users getOwner_user() {
		return owner_user;
	}
	public void setOwner_user(Users owner_user) {
		this.owner_user = owner_user;
	}
    
    /**
     * @hibernate.property
     *  column="place"
     *  type="string"
     */ 
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	
	public String getStartday() {
		return startday;
	}
	public void setStartday(String startday) {
		this.startday = startday;
	}
	
	public String getStarthour() {
		return starthour;
	}
	public void setStarthour(String starthour) {
		this.starthour = starthour;
	}
	
	public String getStartmin() {
		return startmin;
	}
	public void setStartmin(String startmin) {
		this.startmin = startmin;
	}
	
	public String getStartmonth() {
		return startmonth;
	}
	public void setStartmonth(String startmonth) {
		this.startmonth = startmonth;
	}
    
    /**
     * @hibernate.property
     *  column="starttermin"
     *  type="java.util.Date"
     */	
	public Date getStarttermin() {
		return starttermin;
	}
	public void setStarttermin(Date starttermin) {
		this.starttermin = starttermin;
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
     *  column="status_id"
     *  type="long"
     */		
	public Long getStatus_id() {
		return status_id;
	}
	public void setStatus_id(Long status_id) {
		this.status_id = status_id;
	}
    
    /**
     * 
     * @hibernate.id
     *  column="termin_id"
     *  generator-class="increment"
     */  	
	public int getTermin_id() {
		return termin_id;
	}
	public void setTermin_id(int termin_id) {
		this.termin_id = termin_id;
	}
	
	public Terminestatus getTermine_status() {
		return termine_status;
	}
	public void setTermine_status(Terminestatus termine_status) {
		this.termine_status = termine_status;
	}

	
	public int getVisualrowlength() {
		return visualrowlength;
	}
	public void setVisualrowlength(int visualrowlength) {
		this.visualrowlength = visualrowlength;
	}

}
