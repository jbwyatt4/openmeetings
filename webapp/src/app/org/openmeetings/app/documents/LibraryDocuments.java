package org.openmeetings.app.documents;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LibraryDocuments {
	
	private static final Log log = LogFactory.getLog(LibraryDocuments.class);

	private static LibraryDocuments instance;

	private LibraryDocuments() {}

	public static synchronized LibraryDocuments getInstance() {
		if (instance == null) {
			instance = new LibraryDocuments();
		}
		return instance;
	}
}
