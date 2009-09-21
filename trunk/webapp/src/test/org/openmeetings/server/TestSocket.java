package org.openmeetings.server;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
//import org.red5.logging.Red5LoggerFactory;
//import org.slf4j.Logger;

/**
 * @author sebastianwagner
 *
 */
public class TestSocket extends TestCase {
	
	//private static final Logger log = Red5LoggerFactory.getLogger(TestSocket.class, "openmeetings");
	private static Logger log = Logger.getLogger(TestSocket.class);
	
	
	public TestSocket(String testname){
		super(testname);
	}
	
	public void testTestSocket(){
		try {
			
//			ServerSocketProcess serverSocketProcess = new ServerSocketProcess();
//			
//			serverSocketProcess.run();
			
			System.out.println("TEST COMPLETE _ _");
		
		} catch (Exception err) {
			log.error("[TestSocket] ",err);
		}
		
	}

}
