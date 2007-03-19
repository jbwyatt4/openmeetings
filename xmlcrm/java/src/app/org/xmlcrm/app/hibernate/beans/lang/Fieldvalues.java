package org.xmlcrm.app.hibernate.beans.lang;

import java.util.Date;
import java.util.Set;

/**
 * 
 * @hibernate.class table="fieldvalues"
 *
 */
public class Fieldvalues {

	private Long fieldvalues_id;
	private String name;
	private Date starttime;
	private Date updatetime;
	private Boolean deleted;
	
	public Set fieldlanguagesvalues;
	
    public Fieldvalues() {
		super();
	}

    /**
     * 
     * @hibernate.id
     *  column="fieldvalues_id"
     *  generator-class="increment"
     */ 
	public Long getFieldvalues_id() {
		return fieldvalues_id;
	}
	public void setFieldvalues_id(Long fieldvalues_id) {
		this.fieldvalues_id = fieldvalues_id;
	}	

    /**
     * @hibernate.property
     *  column="name"
     *  type="string"
     */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

    /**
     * @hibernate.set 
     * table = "fieldlanguagesvalues" 
     * inverse = "true" 
     * cascade = "none"
     * @hibernate.collection-one-to-many 
     * class = "org.xmlcrm.app.hibernate.beans.lang.Fieldlanguagesvalues"
     * @hibernate.collection-key 
     * column = "fieldvalues_id"
     */	
	public Set getFieldlanguagesvalues() {
		return fieldlanguagesvalues;
	}
	public void setFieldlanguagesvalues(Set fieldlanguagesvalues) {
		this.fieldlanguagesvalues = fieldlanguagesvalues;
	}

	
}
