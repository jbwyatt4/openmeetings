package org.openmeetings.app.persistence.beans.basic;

import java.io.Serializable;
import java.util.Date;

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
	@Column(name = "errorvalues_id")
	private Long errorvalues_id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "errortype_id", updatable = true, insertable = true)
	private ErrorType errorType;

	@Column(name = "fieldvalues_id")
	private Long fieldvalues_id;
	@Column(name = "starttime")
	private Date starttime;
	@Column(name = "updatetime")
	private Date updatetime;
	@Column(name = "deleted")
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

	public ErrorType getErrorType() {
		return errorType;
	}

	public void setErrorType(ErrorType errorType) {
		this.errorType = errorType;
	}

	public Long getFieldvalues_id() {
		return fieldvalues_id;
	}

	public void setFieldvalues_id(Long fieldvalues_id) {
		this.fieldvalues_id = fieldvalues_id;
	}

	public Long getErrortype_id() {
		return errorType.getErrortype_id();
	}

	public void setErrortype_id(Long errortype_id) {
		errorType.setErrortype_id(errortype_id);
	}
}
