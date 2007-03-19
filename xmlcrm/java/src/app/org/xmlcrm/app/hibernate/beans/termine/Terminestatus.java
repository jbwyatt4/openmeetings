package org.xmlcrm.app.hibernate.beans.termine;

/**
 * 
 * @hibernate.class table="terminestatus"
 *
 */
public class Terminestatus {
	
	private Integer status_id;
	private String comment;
	private String description;
	
	private Long starttime;
	private Long  updatetime;
	private Long user_id;
	
    private String starttimeDE;
    private String  updatetimeDE;
    
	public Terminestatus() {
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
     *  column="starttime"
     *  type="long"
     */ 
	public Long getStarttime() {
		return starttime;
	}
	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}
	
	public String getStarttimeDE() {
		return starttimeDE;
	}
	public void setStarttimeDE(String starttimeDE) {
		this.starttimeDE = starttimeDE;
	}
    
    /**
     * 
     * @hibernate.id
     *  column="status_id"
     *  generator-class="increment"
     */  
	public Integer getStatus_id() {
		return status_id;
	}
	public void setStatus_id(Integer status_id) {
		this.status_id = status_id;
	}

    /**
     * @hibernate.property
     *  column="updatetime"
     *  type="long"
     */
	public Long getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Long updatetime) {
		this.updatetime = updatetime;
	}
	
	public String getUpdatetimeDE() {
		return updatetimeDE;
	}
	public void setUpdatetimeDE(String updatetimeDE) {
		this.updatetimeDE = updatetimeDE;
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
