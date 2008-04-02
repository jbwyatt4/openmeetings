package org.xmlcrm.app.quartz.scheduler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.red5.server.api.scheduling.IScheduledJob;
import org.red5.server.api.scheduling.ISchedulingService;
import org.xmlcrm.app.data.basic.Sessionmanagement;
 
public class QuartzSessionClear implements IScheduledJob {

	private static final Log log = LogFactory.getLog(QuartzSessionClear.class);

	public void execute(ISchedulingService service) {
		try {
			
			//cntxt.getScheduler().rescheduleJob("Income Session", "SessionClear Generation", cntxt.getTrigger());
			
			// TODO Generate report
			Sessionmanagement.getInstance().clearSessionTable();
		} catch (Exception err){
			log.error("execute",err);
		}
	}
	

}
