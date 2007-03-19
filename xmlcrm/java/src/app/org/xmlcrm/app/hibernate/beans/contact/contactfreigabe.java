package org.xmlcrm.app.hibernate.beans.contact;

public class contactfreigabe {
	private int FREIGABE_ID;
	private String description;
	private long starttime;
	private long updatetime;
	private String starttimeDE;
	private String updatetimeDE;		
	private String comment;
	public contactfreigabe() {
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
	public int getFREIGABE_ID() {
		return FREIGABE_ID;
	}
	public void setFREIGABE_ID(int freigabe_id) {
		FREIGABE_ID = freigabe_id;
	}
	public long getStarttime() {
		return starttime;
	}
	public void setStarttime(long starttime) {
		this.starttime = starttime;
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
