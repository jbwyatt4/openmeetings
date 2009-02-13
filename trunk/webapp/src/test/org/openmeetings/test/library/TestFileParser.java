package org.openmeetings.test.library;

import org.slf4j.Logger;
import org.red5.logging.Red5LoggerFactory;
import org.openmeetings.app.documents.LibraryWmlLoader;

import junit.framework.TestCase;


public class TestFileParser extends TestCase {
	
	private static final Logger log = Red5LoggerFactory.getLogger(TestFileParser.class, "openmeetings");
	
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
