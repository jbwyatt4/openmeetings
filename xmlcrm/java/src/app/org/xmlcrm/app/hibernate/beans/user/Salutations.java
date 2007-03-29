package org.xmlcrm.app.hibernate.beans.user;

import java.util.Date;

/**
 * 
 * @hibernate.class table="salutations"
 *
 */
public class Salutations {
	
	private Long salutations_id;
	private String name;
	private Date starttime;
	private Date updatetime;
	private String deleted;
	
	public Salutations() {
		super();
		// TODO Auto-generated constructor stub
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
     * 
     * @hibernate.id
     *  column="salutations_id"
     *  generator-class="increment"
     */  
	public Long getSalutations_id() {
		return salutations_id;
	}
	public void setSalutations_id(Long salutations_id) {
		this.salutations_id = salutations_id;
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
