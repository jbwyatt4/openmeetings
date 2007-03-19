package org.xmlcrm.app.hibernate.beans.shop;

public class zahlungsarten {
	private int ZAHLUNGS_ID;
	private String beschreibung;		
	private long starttime;
	private long updatetime;
	private int freigeschalten;
	private String comment;	
	private String starttimeDE;
	private String updatetimeDE;	
	public zahlungsarten() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	public int getFreigeschalten() {
		return freigeschalten;
	}
	public void setFreigeschalten(int freigeschalten) {
		this.freigeschalten = freigeschalten;
	}
	public int getZAHLUNGS_ID() {
		return ZAHLUNGS_ID;
	}
	public void setZAHLUNGS_ID(int zahlungs_id) {
		ZAHLUNGS_ID = zahlungs_id;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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
