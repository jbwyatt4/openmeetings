package org.xmlcrm.app.hibernate.beans.shop;

public class lieferarten {
	private int LIEFER_ID;
	private String beschreibung;		
	private long starttime;
	private long updatetime;
	private int freigeschalten;
	private String comment;	
	private String starttimeDE;
	private String updatetimeDE;
	public lieferarten() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getFreigeschalten() {
		return freigeschalten;
	}
	public void setFreigeschalten(int freigeschalten) {
		this.freigeschalten = freigeschalten;
	}
	public int getLIEFER_ID() {
		return LIEFER_ID;
	}
	public void setLIEFER_ID(int liefer_id) {
		LIEFER_ID = liefer_id;
	}
	public long getStarttime() {
		return starttime;
	}
	public void setStarttime(long starttime) {
		this.starttime = starttime;
	}
	public String getStarttimeDE() {
		return starttimeDE;
	}
	public void setStarttimeDE(String starttimeDE) {
		this.starttimeDE = starttimeDE;
	}
	public long getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(long updatetime) {
		this.updatetime = updatetime;
	}
	public String getUpdatetimeDE() {
		return updatetimeDE;
	}
	public void setUpdatetimeDE(String updatetimeDE) {
		this.updatetimeDE = updatetimeDE;
	}
	
}
