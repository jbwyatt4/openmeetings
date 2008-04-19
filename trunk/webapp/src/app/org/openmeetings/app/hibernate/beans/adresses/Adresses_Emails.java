package org.openmeetings.app.hibernate.beans.adresses;

import java.util.Date;

/**
 * 
 * @hibernate.class table="adresses_emails"
 * lazy="false"
 *
 */
public class Adresses_Emails {

	private Long adresses_emails_id;
	private Emails mail;
	private Long adresses_id;
	private Date starttime;
	private Date updatetime;
	private String deleted;
	
	public Adresses_Emails() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    /**
     * 
     * @hibernate.id
     *  column="adresses_emails_id"
     *  generator-class="increment"
     */	
	public Long getAdresses_emails_id() {
		return adresses_emails_id;
	}
	public void setAdresses_emails_id(Long adresses_emails_id) {
		this.adresses_emails_id = adresses_emails_id;
	}
	
    /**
     * @hibernate.property
     *  column="adresses_id"
     *  type="long"
     */  	
	public Long getAdresses_id() {
		return adresses_id;
	}
	public void setAdresses_id(Long adresses_id) {
		this.adresses_id = adresses_id;
	}

    /**
	 * @hibernate.many-to-one
	 * column = "mail_id"
	 * class = "org.openmeetings.app.hibernate.beans.adresses.Emails"
	 * insert="true"
	 * update="true"
	 * outer-join="true"
	 * lazy="false"
     */			
	public Emails getMail() {
		return mail;
	}
	public void setMail(Emails mail) {
		this.mail = mail;
	}
	
    /**
     * @hibernate.property
     *  column="starttime"
     *  type="java.util.Date"
     */ 
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	
    /**
     * @hibernate.property
     *  column="updatetime"
     *  type="java.util.Date"
     */	
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

    /**
     * @hibernate.property
     *  column="deleted"
     *  type="string"
     */	
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
	
}
