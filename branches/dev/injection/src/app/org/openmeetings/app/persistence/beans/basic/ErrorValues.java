package org.openmeetings.app.persistence.beans.basic;

import java.io.Serializable;
import java.util.Date;

import org.openmeetings.app.persistence.beans.lang.Fieldvalues;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "errorvalues")
public class ErrorValues implements Serializable {
	private static final long serialVersionUID = -1892810463706968018L;

	@Id
	@Column(name="errorvalues_id")
	private Long errorvalues_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="errortype_id", updatable=false, insertable=false)
	private ErrorType errorType;

    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="fieldvalues_id", updatable=false, insertable=false)
	private Fieldvalues fieldvalues;

	@Column(name="starttime")
	private Date starttime;
	@Column(name="updatetime")
	private Date updatetime;
	@Column(name="deleted")
	private String deleted;

	public Long getErrorvalues_id() {
		return errorvalues_id;
	}
	public void setErrorvalues_id(Long errorvalues_id) {
		this.errorvalues_id = errorvalues_id;
	}
	
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
    
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public Fieldvalues getFieldvalues() {
		return fieldvalues;
	}
	public void setFieldvalues(Fieldvalues fieldvalues) {
		this.fieldvalues = fieldvalues;
	}
	
	public ErrorType getErrorType() {
		return errorType;
	}
	public void setErrorType(ErrorType errorType) {
		this.errorType = errorType;
	}
	
	public Long getFieldvalues_id() {
		return fieldvalues.getFieldvalues_id();
	}
	
	public void setFieldvalues_id(Long fieldvalues_id) {
		fieldvalues.setFieldvalues_id(fieldvalues_id);
	}
	
	public Long getErrortype_id() {
		return errorType.getErrortype_id();
	}
	
	public void setErrortype_id(Long errortype_id) {
		errorType.setErrortype_id(errortype_id);
	}
}
