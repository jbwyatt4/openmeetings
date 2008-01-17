package org.xmlcrm.app.hibernate.beans.contact;

import java.util.Date;
import org.xmlcrm.app.hibernate.beans.adresses.Emails;

/**
 * 
 * @hibernate.class table="contacts"
 *
 */
public class Contacts {
	
	private Long contact_id;
	private Long age;
	private String comment;
	private String firstname;
	private String lastname;
	private Date starttime;
	private Date updatetime;
	private String deleted;
	
	private Long adresses_id;
	private Long titel_id;
	private Long freigabe_id;
    
	private Contactgroups contactgroups[];
    private contactfreigabe contactfreigabe;

	public Contacts() {
		super();
		// TODO Auto-generated constructor stub
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
     * @hibernate.property
     *  column="age"
     *  type="long"
     */	
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}

    /**
     * @hibernate.property
     *  column="comment"
     *  type="string"
     */		
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}	
    
    /**
     * 
     * @hibernate.id
     *  column="contact_id"
     *  generator-class="increment"
     */  	
	public Long getContact_id() {
		return contact_id;
	}
	public void setContact_id(Long contact_id) {
		this.contact_id = contact_id;
	}
	
	public contactfreigabe getContactfreigabe() {
		return contactfreigabe;
	}
	public void setContactfreigabe(contactfreigabe contactfreigabe) {
		this.contactfreigabe = contactfreigabe;
	}
	
	public Contactgroups[] getContactgroups() {
		return contactgroups;
	}
	public void setContactgroups(Contactgroups[] contactgroups) {
		this.contactgroups = contactgroups;
	}

    /**
     * @hibernate.property
     *  column="firstname"
     *  type="string"
     */		
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

    /**
     * @hibernate.property
     *  column="freigabe_id"
     *  type="long"
     */		
	public Long getFreigabe_id() {
		return freigabe_id;
	}
	public void setFreigabe_id(Long freigabe_id) {
		this.freigabe_id = freigabe_id;
	}

    /**
     * @hibernate.property
     *  column="lastname"
     *  type="string"
     */		
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

    /**
     * @hibernate.property
     *  column="titel_id"
     *  type="long"
     */		
	public Long getTitel_id() {
		return titel_id;
	}
	public void setTitel_id(Long titel_id) {
		this.titel_id = titel_id;
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
