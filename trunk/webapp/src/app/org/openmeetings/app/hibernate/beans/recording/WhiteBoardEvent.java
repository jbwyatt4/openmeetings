package org.openmeetings.app.hibernate.beans.recording;

public class WhiteBoardEvent {

	private Long starttime;
	private String action;
	
	public Long getStarttime() {
		return starttime;
	}
	public void setStarttime(Long l) {
		this.starttime = l;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
}
