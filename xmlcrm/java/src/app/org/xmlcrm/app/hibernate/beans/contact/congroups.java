package org.xmlcrm.app.hibernate.beans.contact;


public class congroups {
	private int CONGROUP_ID;
	private int USER_ID;
	private int freigabe;	
	private String name;
	private String description;	
	private long starttime;
	private long updatetime;
	private String starttimeDE;
	private String updatetimeDE;	
	private String comment;
	private Contactgroups contactsgroups[];
	public congroups() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getCONGROUP_ID() {
		return CONGROUP_ID;
	}
	public void setCONGROUP_ID(int congroup_id) {
		CONGROUP_ID = congroup_id;
	}	
	public int getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(int user_id) {
		USER_ID = user_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Contactgroups[] getContactsgroups() {
		return contactsgroups;
	}
	public void setContactsgroups(Contactgroups[] contactsgroups) {
		this.contactsgroups = contactsgroups;
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
	public int getFreigabe() {
		return freigabe;
	}
	public void setFreigabe(int freigabe) {
		this.freigabe = freigabe;
	}
	
}
