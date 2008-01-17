package org.xmlcrm.test.gui;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmlcrm.webstart.gui.StartScreen;


public class TestGui{
	
	private static final Log log = LogFactory.getLog(TestGui.class);
	
	public TestGui(String testname){
		//super(testname);
	}
	
	public static void main(String[] args){
		new StartScreen("","","","");
	}
}
