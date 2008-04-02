package org.xmlcrm.app.hibernate.beans.adresses;

import java.util.Date;

/**
 * 
 * @hibernate.class table="phones"
 *
 */
public class Phones {
	
	private Long phone_id;
	private String phonevalue;
	private String comment;
	private Date starttime;
	private Date updatetime;
	private String deleted;
	
	public Phones() {
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
     * 
     * @hibernate.id
     *  column="phone_id"
     *  generator-class="increment"
     */  	
	public Long getPhone_id() {
		return phone_id;
	}
	public void setPhone_id(Long phone_id) {
		this.phone_id = phone_id;
	}

    /**
     * @hibernate.property
     *  column="phonevalue"
     *  type="string"
     */ 	
	public String getPhonevalue() {
		return phonevalue;
	}
	public void setPhonevalue(String phonevalue) {
		this.phonevalue = phonevalue;
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
}
