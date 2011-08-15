package org.openmeetings.test.record;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openmeetings.app.data.record.WhiteboardConvertionJobManager;
import org.openmeetings.test.AbstractOpenmeetingsSpringTest;
import org.springframework.beans.factory.annotation.Autowired;

public class TestBatchConversion extends AbstractOpenmeetingsSpringTest {
	
	private static final Logger log = Logger.getLogger(TestBatchConversion.class);
	@Autowired
	private WhiteboardConvertionJobManager whiteboardConvertionJobManager;
	
	@Test
	public void testBatchConversion(){
		try {
			
			for (int i=0;i<350;i++) {
				whiteboardConvertionJobManager.initJobs();
			}
			
		} catch (Exception err) {
			
			log.error("testBatchConversion",err);
			
		}
		
	}

}
