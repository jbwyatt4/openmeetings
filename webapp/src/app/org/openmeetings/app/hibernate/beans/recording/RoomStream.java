package org.openmeetings.app.hibernate.beans.recording;

import java.util.Date;
import org.openmeetings.app.conference.videobeans.RoomClient;

public class RoomStream {
	
	private String streamName;
	private Boolean streamstart;
	private Boolean avset;
	private String remoteAdress;
	private Date startdate;
	private Long starttime;
	private RoomClient rcl;
	
	public String getStreamName() {
		return streamName;
	}
	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}
	public Boolean getStreamstart() {
		return streamstart;
	}
	public void setStreamstart(Boolean streamstart) {
		this.streamstart = streamstart;
	}
	public Boolean getAvset() {
		return avset;
	}
	public void setAvset(Boolean avset) {
		this.avset = avset;
	}
	public String getRemoteAdress() {
		return remoteAdress;
	}
	public void setRemoteAdress(String remoteAdress) {
		this.remoteAdress = remoteAdress;
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
