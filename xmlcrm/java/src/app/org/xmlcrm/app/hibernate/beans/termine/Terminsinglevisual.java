package org.xmlcrm.app.hibernate.beans.termine;

public class Terminsinglevisual {
	private int TERMIN_ID;	
    private String start;
    private String end;  
    private int duration; 
	private String place;
    private int visualrowlength;
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public int getTERMIN_ID() {
		return TERMIN_ID;
	}
	public void setTERMIN_ID(int termin_id) {
		TERMIN_ID = termin_id;
	}
	public int getVisualrowlength() {
		return visualrowlength;
	}
	public void setVisualrowlength(int visualrowlength) {
		this.visualrowlength = visualrowlength;
	}	
    
}
