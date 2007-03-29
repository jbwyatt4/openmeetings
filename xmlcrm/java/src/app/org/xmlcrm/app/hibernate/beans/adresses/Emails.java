package org.xmlcrm.app.hibernate.beans.adresses;

import java.util.Date;

/**
 * 
 * @hibernate.class table="emails"
 *
 */
public class Emails {

	private Long mail_id;
	private String email;
	private String comment;
	private Date starttime;
	private Date updatetime;
	private String deleted;
	
	public Emails() {
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
     *  column="email"
     *  type="string"
     */ 	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
    /**
     * 
     * @hibernate.id
     *  column="mail_id"
     *  generator-class="increment"
     */ 	
	public Long getMail_id() {
		return mail_id;
	}
	public void setMail_id(Long mail_id) {
		this.mail_id = mail_id;
	}
    
    /**
     * @hibernate.property
     *  column="startdate"
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
     *  column="updatedate"
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
	
}
