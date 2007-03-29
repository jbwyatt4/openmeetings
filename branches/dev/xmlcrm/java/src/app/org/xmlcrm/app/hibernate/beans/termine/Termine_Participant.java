package org.xmlcrm.app.hibernate.beans.termine;

import java.util.Date;

/**
 * 
 * @hibernate.class table="termine_participant"
 *
 */
public class Termine_Participant {
	
	private Long termine_participant_id;
	private Long user_id;
	private Long termin_id;
	private Date updatetime;
	private Date starttime;
	private String deleted;
	
	public Termine_Participant() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    /**
     * @hibernate.property
     *  column="termin_id"
     *  type="long"
     */ 
	public Long getTermin_id() {
		return termin_id;
	}
	public void setTermin_id(Long termin_id) {
		this.termin_id = termin_id;
	}
    
    /**
     * 
     * @hibernate.id
     *  column="termine_participant_id"
     *  generator-class="increment"
     */ 
	public Long getTermine_participant_id() {
		return termine_participant_id;
	}
	public void setTermine_participant_id(Long termine_participant_id) {
		this.termine_participant_id = termine_participant_id;
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
     *  column="user_id"
     *  type="long"
     */ 
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	
}
