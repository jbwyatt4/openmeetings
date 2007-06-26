package org.xmlcrm.webstart.screen;

import org.quartz.Job;
import org.quartz.JobExecutionException;
import org.quartz.JobExecutionContext;

public class ScreenJob implements Job {
	
    public ScreenJob() { }

    public void execute(JobExecutionContext context) throws JobExecutionException {
    	System.out.println("ScreenJob is executing.");
    	new CaptureScreen(ConnectionBean.connectionURL,ConnectionBean.SID,ConnectionBean.room,ConnectionBean.domain);
    }

}
