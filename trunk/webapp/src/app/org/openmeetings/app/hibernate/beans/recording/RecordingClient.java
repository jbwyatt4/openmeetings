package org.openmeetings.app.hibernate.beans.recording;

import java.util.Date;

import org.openmeetings.app.conference.videobeans.RoomClient;

/**
 * 
 * @hibernate.class table="recordingclient"
 * lazy="false"
 *
 */
public class RecordingClient {
	
	private Long recordingclient_id;
	private String remoteAdress;
	private Boolean roomenter;
	private Date startdate;
	private Long starttime;
	private RoomClient rcl;
	private String rclInXml;
	
	
	/**
     * 
     * @hibernate.id
     *  column="recordingclient_id"
     *  generator-class="increment"
     */
	public Long getRecordingclient_id() {
		return recordingclient_id;
	}
	public void setRecordingclient_id(Long recordingclient_id) {
		this.recordingclient_id = recordingclient_id;
	}
	
	/**
     * @hibernate.property
     *  column="remoteaddress"
     *  type="string"
     */	
	public String getRemoteAdress() {
		return remoteAdress;
	}
	public void setRemoteAdress(String remoteAdress) {
		this.remoteAdress = remoteAdress;
	}
	
	/**
     * @hibernate.property
     *  column="roomenter"
     *  type="boolean"
     */	
	public Boolean getRoomenter() {
		return roomenter;
	}
	public void setRoomenter(Boolean roomenter) {
		this.roomenter = roomenter;
	}
	
	/**
     * @hibernate.property
     *  column="startdate"
     *  type="java.util.Date"
     */	
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
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
	
	public RoomClient getRcl() {
		return rcl;
	}
	public void setRcl(RoomClient rcl) {
		this.rcl = rcl;
	}
	
	/**
     * @hibernate.property
     *  column="rcl_in_xml"
     *  type="string"
     */	
	public String getRclInXml() {
		return rclInXml;
	}
	public void setRclInXml(String rclInXml) {
		this.rclInXml = rclInXml;
	}

}
