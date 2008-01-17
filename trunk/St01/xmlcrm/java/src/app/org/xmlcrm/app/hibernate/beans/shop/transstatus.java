package org.xmlcrm.app.hibernate.beans.shop;

public class transstatus {
	private int STATUS_ID;
	private String description;
	private String comment;
	private long starttime;
	private long updatetime;
	private String starttimeDE;
	private String updatetimeDE;	
	public transstatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getStarttime() {
		return starttime;
	}
	public void setStarttime(long starttime) {
		this.starttime = starttime;
	}
	public int getSTATUS_ID() {
		return STATUS_ID;
	}
	public void setSTATUS_ID(int status_id) {
		STATUS_ID = status_id;
	}
	public long getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(long updatetime) {
		this.updatetime = updatetime;
	}
	public String getStarttimeDE() {
		return starttimeDE;
	}
	public void setStarttimeDE(String starttimeDE) {
		this.starttimeDE = starttimeDE;
	}
	public String getUpdatetimeDE() {
		return updatetimeDE;
	}
	public void setUpdatetimeDE(String updatetimeDE) {
		this.updatetimeDE = updatetimeDE;
	}	
	
}
