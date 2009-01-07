package org.openmeetings.app.quartz.scheduler;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import org.slf4j.LoggerFactory; 
import org.slf4j.Logger;

import org.red5.server.api.scheduling.IScheduledJob;
import org.red5.server.api.scheduling.ISchedulingService;
import org.openmeetings.app.data.basic.Sessionmanagement;
import org.openmeetings.app.data.record.WhiteboardConvertionJobManager;
import org.openmeetings.app.remote.red5.Application;
 
public class QuartzZombieJob implements IScheduledJob {

	private static Logger log = LoggerFactory.getLogger(QuartzZombieJob.class.getName());

	public void execute(ISchedulingService service) {
		try {
			
			//cntxt.getScheduler().rescheduleJob("Income Session", "SessionClear Generation", cntxt.getTrigger());
			
			// TODO Check for Zombies
			
			Application.getInstance().clearZombiesFromAllConnection();

		} catch (Exception err){
			log.error("execute",err);
		}
	}
	

}
