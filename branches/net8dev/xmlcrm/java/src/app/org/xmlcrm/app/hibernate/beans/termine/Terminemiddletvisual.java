package org.xmlcrm.app.hibernate.beans.termine;

public class Terminemiddletvisual {
    private int TERMIN_ID;  
    private String start;
    private String end;  
    private int duration; 
    private String place;
    private String message;
    private String description; 
    private String comment;
    private int open;     
    private int visualrowlength;
    private int terminstatus;    
    public int getTerminstatus() {
        return terminstatus;
    }
    public void setTerminstatus(int terminstatus) {
        this.terminstatus = terminstatus;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
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
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public int getOpen() {
        return open;
    }
    public void setOpen(int open) {
        this.open = open;
    }    
}
