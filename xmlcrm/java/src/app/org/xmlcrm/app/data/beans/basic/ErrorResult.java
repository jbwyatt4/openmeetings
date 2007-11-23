package org.xmlcrm.app.data.beans.basic;

public class ErrorResult {
	
	private Long errorId;
	private String errmessage;

	public ErrorResult(Long errorId, String errmessage) {
		super();
		this.errorId = errorId;
		this.errmessage = errmessage;
	}

	public Long getErrorId() {
		return errorId;
	}

	public void setErrorId(Long errorId) {
		this.errorId = errorId;
	}

	public String getErrmessage() {
		return errmessage;
	}

	public void setErrmessage(String errmessage) {
		this.errmessage = errmessage;
	}

}
