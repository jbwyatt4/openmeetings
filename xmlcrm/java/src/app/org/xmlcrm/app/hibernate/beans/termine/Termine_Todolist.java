package org.xmlcrm.app.hibernate.beans.termine;

import java.util.Date;

/**
 * 
 * @hibernate.class table="termine_todolist"
 *
 */
public class Termine_Todolist {
	
	private Long termine_todolist_id;
	private String comment; 
	private String description;
    private String name;    
	private Date starttime;
	private Long status_id;
	private String teilnehmer;
	private Date  updatetime;	
	private Boolean deleted;
    

	public Termine_Todolist() {
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
     *  column="teilnehmer"
     *  type="string"
     */ 
	public String getTeilnehmer() {
		return teilnehmer;
	}
	public void setTeilnehmer(String teilnehmer) {
		this.teilnehmer = teilnehmer;
	}
    
    /**
     * 
     * @hibernate.id
     *  column="termine_todolist_id"
     *  generator-class="increment"
     */   
	public Long getTermine_todolist_id() {
		return termine_todolist_id;
	}
	public void setTermine_todolist_id(Long termine_todolist_id) {
		this.termine_todolist_id = termine_todolist_id;
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
