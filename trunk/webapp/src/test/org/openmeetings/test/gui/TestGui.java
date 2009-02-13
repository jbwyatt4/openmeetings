package org.openmeetings.test.gui;

import org.slf4j.Logger;
import org.red5.logging.Red5LoggerFactory;
import org.openmeetings.webstart.gui.StartScreen;


public class TestGui{
	
	private static final Logger log = Red5LoggerFactory.getLogger(TestGui.class, "openmeetings");
	
	public TestGui(String testname){
		//super(testname);
	}
	
	public static void main(String[] args){
		new StartScreen("","","","","");
	}
}
