package org.xmlcrm.app.hibernate.beans.domain;

import java.util.Date;

/**
 * 
 * @hibernate.class table="organisation_users"
 *
 */
public class Organisation_Users {

	private Long organisation_users_id;
	private Long organisation_id;
	private Long user_id;
	private Date starttime;
	private Date updatetime;
	private Boolean deleted;

    
    public Organisation_Users() {
		super();
	}

    /**
     * @hibernate.property
     *  column="organisation_id"
     *  type="long"
     */ 
	public Long getOrganisation_id() {
		return organisation_id;
	}
	public void setOrganisation_id(Long organisation_id) {
		this.organisation_id = organisation_id;
	}
	   
    /**
     * 
     * @hibernate.id
     *  column="organisation_users_id"
     *  generator-class="increment"
     */  
	public Long getOrganisation_users_id() {
		return organisation_users_id;
	}
	public void setOrganisation_users_id(Long organisation_users_id) {
		this.organisation_users_id = organisation_users_id;
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
