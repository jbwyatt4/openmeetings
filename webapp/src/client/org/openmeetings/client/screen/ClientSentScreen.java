package org.openmeetings.client.screen;

import org.apache.log4j.Logger;

/**
 * @author sebastianwagner
 *
 */
public class ClientSentScreen extends Thread {
	
	public static boolean threadRunning = false;
	
	private static Logger log = Logger.getLogger(ClientSentScreen.class);

	private int modeIndex = 0;
	
	@Override
	public void run() {
		try {
			
			log.debug("ClientSentScreen RUN");
			
			while (threadRunning) {
			
				modeIndex++;
				
				new ClientCaptureScreen(modeIndex);
				
				if (modeIndex==4) {
					modeIndex = 0;
				}
				
				//Wait for half 200 milliseconds for the next run
				ClientSentScreen.sleep(200);
			
			}
			
		} catch (Exception err) {
			log.error("[ClientSentScreen]",err);
		}
	}

}
