package org.xmlcrm.app.hibernate.beans.adresses;

import java.util.Date;

/**
 * 
 * @hibernate.class table="adresses_phones"
 *
 */
public class Adresses_Phones {
	
	private Long adresses_phone_id;
	private Long phone_id;
	private Long adresses_id;
	private Date starttime;
	private Date updatetime;
	private Boolean deleted;
	
	public Adresses_Phones() {
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
     * 
     * @hibernate.id
     *  column="adresses_phone_id"
     *  generator-class="increment"
     */ 	
	public Long getAdresses_phone_id() {
		return adresses_phone_id;
	} 	
	public void setAdresses_phone_id(Long adresses_phone_id) {
		this.adresses_phone_id = adresses_phone_id;
	}
    
    /**
     * @hibernate.property
     *  column="phone_id"
     *  type="long"
     */	
	public Long getPhone_id() {
		return phone_id;
	}
	public void setPhone_id(Long phone_id) {
		this.phone_id = phone_id;
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
     *  type="boolean"
     */	
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}
