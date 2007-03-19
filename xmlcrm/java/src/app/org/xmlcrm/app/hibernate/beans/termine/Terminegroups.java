package org.xmlcrm.app.hibernate.beans.termine;

import java.util.Date;

/**
 * 
 * @hibernate.class table="terminegroups"
 *
 */
public class Terminegroups {
	
	private Long terminegroup_id;
	private String comment;
	private Date endtermin; 
	private Long invitor_id;
	private String message;
	private Date starttermin;	
	private Long termin_id;	
	private Long status_id;
	private Long uid;
	private Date starttime;
	private Date  updatetime;
	private Boolean deleted;
    
    private Termine termine;
    private Terminestatus termine_status;

	public Terminegroups() {
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
     *  column="endtermin"
     *  type="java.util.Date"
     */ 
	public Date getEndtermin() {
		return endtermin;
	}
	public void setEndtermin(Date endtermin) {
		this.endtermin = endtermin;
	}
    
    /**
     * @hibernate.property
     *  column="invitor_id"
     *  type="long"
     */ 
	public Long getInvitor_id() {
		return invitor_id;
	}
	public void setInvitor_id(Long invitor_id) {
		this.invitor_id = invitor_id;
	}

    /**
     * @hibernate.property
     *  column="message"
     *  type="string"
     */ 
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	
	public Termine getTermine() {
		return termine;
	}
	public void setTermine(Termine termine) {
		this.termine = termine;
	}
	
	public Terminestatus getTermine_status() {
		return termine_status;
	}
	public void setTermine_status(Terminestatus termine_status) {
		this.termine_status = termine_status;
	}
    
    /**
     * 
     * @hibernate.id
     *  column="terminegroup_id"
     *  generator-class="increment"
     */ 
	public Long getTerminegroup_id() {
		return terminegroup_id;
	}
	public void setTerminegroup_id(Long terminegroup_id) {
		this.terminegroup_id = terminegroup_id;
	}
    
    /**
     * @hibernate.property
     *  column="uid"
     *  type="long"
     */
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
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
     *  type="boolean"
     */	
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
    
    
    
}

