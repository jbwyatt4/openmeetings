package org.xmlcrm.app.hibernate.beans.recording;

import java.util.Date;
import java.util.LinkedHashMap;

import org.xmlcrm.app.hibernate.beans.rooms.Rooms;

/**
 * 
 * @hibernate.class table="recording"
 * lazy="false"
 *
 */
public class Recording {
	
	private Long recording_id;
	private String name;
	private Long duration;	
	private String xmlString;
	private String comment;
	private Rooms rooms;
	private Date starttime;
	private Date updatetime;
	private String deleted;
	
	private LinkedHashMap<String,Object> roomRecording;

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
     *  column="recording_id"
     *  generator-class="increment"
     */ 
    public Long getRecording_id() {
		return recording_id;
	}
	public void setRecording_id(Long recording_id) {
		this.recording_id = recording_id;
	}
    
    /**
     * @hibernate.many-to-one
     *  cascade="none"
     *  column="roomtypes_id"
     *  lazy="false"
     *  class="org.xmlcrm.app.hibernate.beans.rooms.Rooms"
     *  not-null="false"
     *  outer-join="true"
     */ 
	public Rooms getRooms() {
		return rooms;
	}
	public void setRooms(Rooms rooms) {
		this.rooms = rooms;
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
     *  column="duration"
     *  type="long"
     */		
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	
    /**
     * @hibernate.property
     *  column="xmlString"
     *  type="text"
     */		
	public String getXmlString() {
		return xmlString;
	}
	public void setXmlString(String xmlString) {
		this.xmlString = xmlString;
	}
	
	public LinkedHashMap<String, Object> getRoomRecording() {
		return roomRecording;
	}
	public void setRoomRecording(LinkedHashMap<String, Object> roomRecording) {
		this.roomRecording = roomRecording;
	}
	
}
