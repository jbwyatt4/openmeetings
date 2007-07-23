package org.xmlcrm.app.documents;

import java.util.HashMap;

public class CreateLibraryPresentation {
	
	private static CreateLibraryPresentation instance;

	private CreateLibraryPresentation() {}

	public static synchronized CreateLibraryPresentation getInstance() {
		if (instance == null) {
			instance = new CreateLibraryPresentation();
		}
		return instance;
	}
	
	
	public HashMap<String,Object> generateXMLDocument(String targetDirectory, String originalDocument, 
			String pdfDocument){
		HashMap<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("process", "generateXMLDocument");		
		try {
			
						
			
			return returnMap;
		} catch (Exception err) {
			err.printStackTrace();
			returnMap.put("error", err.getMessage());
			returnMap.put("exitValue", -1);
			return returnMap;
		}
	}
	
	
}
