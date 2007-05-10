package org.xmlcrm.app.documents;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InstallationDocument {
	
	private static final Log log = LogFactory.getLog(InstallationDocument.class);
	
	public static final String installFolderName = "install/";

	public static final String installFileName = "install.xml";
	
	private static InstallationDocument instance;

	private InstallationDocument() {}

	public static synchronized InstallationDocument getInstance() {
		if (instance == null) {
			instance = new InstallationDocument();
		}
		return instance;
	}
}
