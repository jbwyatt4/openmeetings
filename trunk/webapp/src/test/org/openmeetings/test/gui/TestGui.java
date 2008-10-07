package org.openmeetings.test.gui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openmeetings.webstart.gui.StartScreen;


public class TestGui{
	
	private static final Logger log = LoggerFactory.getLogger(TestGui.class);
	
	public TestGui(String testname){
		//super(testname);
	}
	
	public static void main(String[] args){
		new StartScreen("","","","","");
	}
}
