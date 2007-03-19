package org.xmlcrm.app.hibernate.beans.user;

import java.util.Date;

/**
 * 
 * @hibernate.class table="titles"
 *
 */
public class Titles {
	
	private Long title_id;
	private String name;
	private Date starttime;
	private Date updatetime;
	private Boolean deleted;
	
	public Titles() {
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
     *  column="title_id"
     *  generator-class="increment"
     */  
	public Long getTitle_id() {
		return title_id;
	}
	public void setTitle_id(Long title_id) {
		this.title_id = title_id;
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
