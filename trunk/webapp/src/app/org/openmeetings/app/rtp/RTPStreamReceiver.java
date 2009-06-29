package org.openmeetings.app.rtp;

import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.rtp.ReceiveStreamListener;
import javax.media.rtp.event.ReceiveStreamEvent;

/**
 * @author sebastianwagner
 * 
 * 
 * Receives the Stream and Adds a new RTPManager 
 * 
 * acts as the Proxy to Re-Stream the RTP Stream
 * 
 * it needs to be possible to have a method to addTarget and removeTarget from the RTPManager
 *
 */
public class RTPStreamReceiver extends Thread implements ControllerListener, ReceiveStreamListener {

	/* (non-Javadoc)
	 * @see javax.media.ControllerListener#controllerUpdate(javax.media.ControllerEvent)
	 */
	public void controllerUpdate(ControllerEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.media.rtp.ReceiveStreamListener#update(javax.media.rtp.event.ReceiveStreamEvent)
	 */
	public void update(ReceiveStreamEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
