package org.openmeetings.app.hibernate.beans.recording;

public class WhiteBoardEvent {

	private Long starttime;
	private String action;
	
	//this is only Filled if send to client
	private Object actionObj;
	
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
	public Object getActionObj() {
		return actionObj;
	}
	public void setActionObj(Object actionObj) {
		this.actionObj = actionObj;
	}
	
}
