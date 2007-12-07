package org.xmlcrm.app.data.record;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Recordingmanagement {

	private static final Log log = LogFactory.getLog(Recordingmanagement.class);

	private static Recordingmanagement instance;

	private Recordingmanagement() {}

	public static synchronized Recordingmanagement getInstance() {
		if (instance == null) {
			instance = new Recordingmanagement();
		}
		return instance;
	}
	
	
}
