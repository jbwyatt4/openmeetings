package org.openmeetings.app.data.beans.basic;

import java.util.List;

public class SearchResult {
	
	private String objectName;
	private Long records;
	private List result;
	
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public Long getRecords() {
		return records;
	}
	public void setRecords(Long records) {
		this.records = records;
	}
	public List getResult() {
		return result;
	}
	public void setResult(List result) {
		this.result = result;
	}
	
	

}
