package org.xmlcrm.app.hibernate.beans.rooms;

import java.util.Date;
import java.util.List;

import org.xmlcrm.app.conference.videobeans.RoomClient;

/**
 * 
 * @hibernate.class table="rooms"
 * lazy="false"
 *
 */
public class Rooms {
	
	private Long rooms_id;
	private String name;
	private String comment;
	private RoomTypes roomtype;
	private Date starttime;
	private Date updatetime;
	private String deleted;
	private Boolean ispublic;
	private Long numberOfPartizipants = new Long(4);
	
	private List<RoomClient> currentusers;
    
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
     *  column="rooms_id"
     *  generator-class="increment"
     */ 
	public Long getRooms_id() {
		return rooms_id;
	}
	public void setRooms_id(Long rooms_id) {
		this.rooms_id = rooms_id;
	}
    
    /**
     * @hibernate.many-to-one
     *  cascade="none"
     *  column="roomtypes_id"
     *  lazy="false"
     *  class="org.xmlcrm.app.hibernate.beans.rooms.RoomTypes"
     *  not-null="false"
     *  outer-join="true"
     */ 
	public RoomTypes getRoomtype() {
		return roomtype;
	}
	public void setRoomtype(RoomTypes roomtype) {
		this.roomtype = roomtype;
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
	
    /**
     * @hibernate.property
     *  column="ispublic"
     *  type="boolean"
     */	
	public Boolean getIspublic() {
		return ispublic;
	}
	public void setIspublic(Boolean ispublic) {
		this.ispublic = ispublic;
	}
	
	public List<RoomClient> getCurrentusers() {
		return currentusers;
	}
	public void setCurrentusers(List<RoomClient> currentusers) {
		this.currentusers = currentusers;
	}
	
	/**
     * @hibernate.property
     *  column="numberOfPartizipants"
     *  type="long"
     */	
	public Long getNumberOfPartizipants() {
		return numberOfPartizipants;
	}
	public void setNumberOfPartizipants(Long numberOfPartizipants) {
		this.numberOfPartizipants = numberOfPartizipants;
	}
	
}
