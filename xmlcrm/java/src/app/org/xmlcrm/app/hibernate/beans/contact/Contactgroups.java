package org.xmlcrm.app.hibernate.beans.contact;

import java.util.Date;

/**
 * 
 * @hibernate.class table="contactgroups"
 *
 */
public class Contactgroups {

	private Long contactgroup_id;
	private String comment;
	private String description;
	private String name;
	private Long freigabe_id;
	
	private Date starttime;
	private Date updatetime;
	private Boolean deleted;
	
	public Contactgroups() {
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
     *  column="contactgroup_id"
     *  generator-class="increment"
     */  	
	public Long getContactgroup_id() {
		return contactgroup_id;
	}
	public void setContactgroup_id(Long contactgroup_id) {
		this.contactgroup_id = contactgroup_id;
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

    /**
     * @hibernate.property
     *  column="freigabe_id"
     *  type="long"
     */	
	public Long getFreigabe_id() {
		return freigabe_id;
	}
	public void setFreigabe_id(Long freigabe_id) {
		this.freigabe_id = freigabe_id;
	}

    /**
     * @hibernate.property
     *  column="name"
     *  type="string"
     */	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
