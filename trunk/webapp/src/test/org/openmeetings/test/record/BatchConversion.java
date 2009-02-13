package org.openmeetings.test.record;

import junit.framework.TestCase;

import org.openmeetings.app.data.record.WhiteboardConvertionJobManager;
import org.slf4j.Logger;
import org.red5.logging.Red5LoggerFactory;

public class BatchConversion extends TestCase {
	
	private static final Logger log = Red5LoggerFactory.getLogger(BatchConversion.class, "openmeetings");
	
	public BatchConversion(String testname){
		super(testname);
	}
	
	public void testBatchConversion(){
		try {
			
			for (int i=0;i<350;i++) {
				WhiteboardConvertionJobManager.getInstance().initJobs();
			}
			
		} catch (Exception err) {
			
			log.error("testBatchConversion",err);
			
		}
		
	}

}
