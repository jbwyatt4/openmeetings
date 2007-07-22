package org.xmlcrm.app.documents;

public class CreateLibraryPresentation {
	
	private static CreateLibraryPresentation instance;

	private CreateLibraryPresentation() {}

	public static synchronized CreateLibraryPresentation getInstance() {
		if (instance == null) {
			instance = new CreateLibraryPresentation();
		}
		return instance;
	}
	
	
}
