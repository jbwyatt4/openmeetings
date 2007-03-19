package org.xmlcrm.app.hibernate.beans.termine;

import java.util.Date;

/**
 * 
 * @hibernate.class table="termine_user"
 *
 */
public class Termine_User {
	
	private Long termine_user_id;
	private String comment;
	private Date endtermin;
	private Long invitor_id;
	private String message;
	private Date starttermin;
	private Long termin_id;
	private Long terminstatus;
	private Long user_id;
	private Date starttime;
	private Date  updatetime;
	private Boolean deleted;

	private Terminestatus termine_status;	   
    private Termine termine;


	public Termine_User() {
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
     * @hibernate.property
     *  column="terminstatus"
     *  type="long"
     */
	public Long getTerminstatus() {
		return terminstatus;
	}
	public void setTerminstatus(Long terminstatus) {
		this.terminstatus = terminstatus;
	}
    
    /**
     * 
     * @hibernate.id
     *  column="termine_user_id"
     *  generator-class="increment"
     */  
	public Long getTermine_user_id() {
		return termine_user_id;
	}
	public void setTermine_user_id(Long termine_user_id) {
		this.termine_user_id = termine_user_id;
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
