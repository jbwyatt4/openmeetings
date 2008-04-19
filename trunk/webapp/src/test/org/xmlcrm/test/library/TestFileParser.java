package org.openmeetings.test.library;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmeetings.app.documents.LibraryWmlLoader;

import junit.framework.TestCase;


public class TestFileParser extends TestCase {
	
	private static final Log log = LogFactory.getLog(TestFileParser.class);
	
	public TestFileParser(String testname){
		super(testname);
	}
	
	public void testLoadWmlFile(){
		
		try {
			
			LibraryWmlLoader.getInstance().loadWmlFile("src/","filename1");
			
		} catch (Exception err) {
			
			log.error("TestLoadWmlFile",err);
			
		}
		
	}
	
}
