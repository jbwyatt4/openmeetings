package org.openmeetings.app.hibernate.beans.recording;

import java.util.Date;

import org.openmeetings.app.conference.videobeans.RoomClient;

public class RecordingClient {
	
	
	private String remoteAdress;
	private Boolean roomenter;
	private Date startdate;
	private Long starttime;
	private RoomClient rcl;
	
	
	public String getRemoteAdress() {
		return remoteAdress;
	}
	public void setRemoteAdress(String remoteAdress) {
		this.remoteAdress = remoteAdress;
	}
	public Boolean getRoomenter() {
		return roomenter;
	}
	public void setRoomenter(Boolean roomenter) {
		this.roomenter = roomenter;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
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
	
	
	

}
