package org.xmlcrm.webstart.screen;

import org.quartz.Job;
import org.quartz.JobExecutionException;
import org.quartz.JobExecutionContext;

import org.xmlcrm.webstart.beans.ConnectionBean;
import org.xmlcrm.webstart.gui.StartScreen;

public class ScreenJob implements Job {
	
    public ScreenJob() { }

    public void execute(JobExecutionContext context) throws JobExecutionException {
    	System.out.println("ScreenJob is executing.");
    	if (ConnectionBean.isloading){
    		StartScreen.instance.showBandwidthWarning("Your Bandwidth is bad. Frames have been droped. You can alter the Quality settings to reduce Bandwidth usage.");
    	} else {
    		new CaptureScreen(ConnectionBean.connectionURL,ConnectionBean.SID,ConnectionBean.room,ConnectionBean.domain);
    	}
    }

}
