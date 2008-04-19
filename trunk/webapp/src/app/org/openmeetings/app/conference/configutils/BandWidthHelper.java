package org.openmeetings.app.conference.configutils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.red5.server.api.IConnection;
import org.red5.server.api.scheduling.IScheduledJob;
import org.red5.server.api.scheduling.ISchedulingService;

import org.openmeetings.app.remote.Application;

public class BandWidthHelper implements IScheduledJob {

	private Application refInstance = null;
	
	private String schedulerName = "";
	
	private int overAllPingTime;
	
	private int i =0;
	
	private int maxI = 5;
	
	private static final Log log = LogFactory.getLog(BandWidthHelper.class);
	
	private IConnection currentConnection = null;
	
	/**
	 * 
	 * @param sName
	 */
	public void setSchedulerName(String sName){
		this.schedulerName = sName;
	}
	
	/**
	 * 
	 * @param iConn
	 */
	public void setConnection(IConnection iConn){
		this.currentConnection = iConn;
	}

	/**
	 * @param refInstance the refInstance to set
	 */
	public void setRefInstance(Application refInstance) {
		this.refInstance = refInstance;
	}

	/**
	 * @param maxI the maxI to set
	 */
	public void setMaxI(int maxI) {
		this.maxI = maxI;
	}

	/* (non-Javadoc)
	 * @see org.red5.server.api.scheduling.IScheduledJob#execute(org.red5.server.api.scheduling.ISchedulingService)
	 */
	public void execute(ISchedulingService service) {
		try {
			log.debug("Scheduler: "+service+"  "+schedulerName);
			this.calcAverageUsersPing(currentConnection);
			if (i==maxI){
				//Remove that job and calc average
				//efInstance.detectedBandwidth((overAllPingTime/maxI),schedulerName,currentConnection);
			}
			i++;
		} catch (Exception err){
			//
			log.error("Err: "+err);
		}
	}
	
	public synchronized void calcAverageUsersPing(IConnection conn) throws Exception{
		conn.ping();
		overAllPingTime+= conn.getLastPingTime();
		log.debug("Ping "+conn.getHost()+" Ping"+conn.getLastPingTime());
	}
}
