package org.xmlcrm.app.hibernate.beans.termine;

import java.util.Date;

/**
 * 
 * @hibernate.class table="termine_todo_user"
 *
 */
public class Termine_Todo_User {
	
	private Long termine_todo_user_id;
	private String comment;
	private Long owner_id;
	private Integer priority;
	private Date starttime;
	private Long todo_id;
	private Date  updatetime;
	private Long user_id;
	private Boolean deleted;
	
	private Termine_Todolist termine_todolist;

	public Termine_Todo_User() {
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
     *  column="owner_id"
     *  type="long"
     */ 
	public Long getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(Long owner_id) {
		this.owner_id = owner_id;
	}

    /**
     * @hibernate.property
     *  column="priority"
     *  type="int"
     */ 
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	public Termine_Todolist getTermine_todolist() {
		return termine_todolist;
	}
	public void setTermine_todolist(Termine_Todolist termine_todolist) {
		this.termine_todolist = termine_todolist;
	}

    /**
     * @hibernate.property
     *  column="todo_id"
     *  type="long"
     */ 
	public Long getTodo_id() {
		return todo_id;
	}
	public void setTodo_id(Long todo_id) {
		this.todo_id = todo_id;
	}
    
    /**
     * 
     * @hibernate.id
     *  column="termine_todo_user_id"
     *  generator-class="increment"
     */ 
	public Long getTermine_todo_user_id() {
		return termine_todo_user_id;
	}
	public void setTermine_todo_user_id(Long termine_todo_user_id) {
		this.termine_todo_user_id = termine_todo_user_id;
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
