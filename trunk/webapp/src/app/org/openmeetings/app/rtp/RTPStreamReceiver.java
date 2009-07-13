package org.openmeetings.app.rtp;

import java.net.InetAddress;

import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.rtp.Participant;
import javax.media.rtp.RTPManager;
import javax.media.rtp.ReceiveStream;
import javax.media.rtp.ReceiveStreamListener;
import javax.media.rtp.SessionAddress;
import javax.media.rtp.SessionListener;
import javax.media.rtp.event.ByeEvent;
import javax.media.rtp.event.NewReceiveStreamEvent;
import javax.media.rtp.event.ReceiveStreamEvent;
import javax.media.rtp.event.RemotePayloadChangeEvent;
import javax.media.rtp.event.SessionEvent;


import org.openmeetings.servlet.outputhandler.ScreenRequestHandler;
import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;


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
public class RTPStreamReceiver implements  ReceiveStreamListener,SessionListener{
	
	private static final Logger log = Red5LoggerFactory.getLogger(RTPStreamReceiver.class, "openmeetings");
	
	/** contains sessionData */
	RTPScreenSharingSession sessionData = null;
	
	/** The basic RTPmanager for a romm, receiving the webstart client stream */
	private RTPManager basicManager = null;
	
	/** Clients/Viewer */
	public RTPManager[] clientmanagers;
	
	/** Received Stream */
	ReceiveStream stream;
	
	boolean dataReceived = false;
    Object dataSync = new Object();

	
	/**
	 * 
	 * @param session
	 */
	//----------------------------------------------------------------------
	public RTPStreamReceiver(RTPScreenSharingSession session) throws Exception {
		log.debug("RTPStreamReceiver Konstruktor");
		this.sessionData = session;
		
		basicManager = RTPManager.newInstance();
		//basicManager.addSessionListener(this);
		basicManager.addReceiveStreamListener(this);
		basicManager.addSessionListener(this);
		
		SessionAddress localAddr = new SessionAddress( InetAddress.getLocalHost(), sessionData.getIncomingRTPPort());
		
		basicManager.initialize(localAddr);
		basicManager.addTarget(new SessionAddress(InetAddress.getByName(session.getSharingIpAddress()), sessionData.getIncomingRTPPort()));
		
		
		long then = System.currentTimeMillis();
		long waitingPeriod = 30000;  // wait for a maximum of 30 secs.

		try{
		    synchronized (dataSync) {
			while (!dataReceived && 
				System.currentTimeMillis() - then < waitingPeriod) {
			    if (!dataReceived)
				System.err.println("  - Waiting for RTP data to arrive...");
			    dataSync.wait(1000);
			}
		    }
		} catch (Exception e) {log.error("Error on synchronize : " + e.getMessage());};

		if (!dataReceived) {
		    System.err.println("No RTP data was received.");
		    basicManager.dispose();
		    
		}

		log.debug("RTPStreamReceiver Konstruktor done");
	}
	//----------------------------------------------------------------------

	/* (non-Javadoc)
	 * @see javax.media.ControllerListener#controllerUpdate(javax.media.ControllerEvent)
	 */
	public synchronized void controllerUpdate(ControllerEvent arg0) {
		log.debug("RTPStreamreceiver.controllerUpdate");
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.media.rtp.ReceiveStreamListener#update(javax.media.rtp.event.ReceiveStreamEvent)
	 */
	public void update(ReceiveStreamEvent evt) {
		log.debug("RTPStreamReceiver.update");
		
		Participant participant = evt.getParticipant();	// could be null.
		ReceiveStream stream = evt.getReceiveStream();  // could be null.

		if (evt instanceof RemotePayloadChangeEvent) {
	     
		    System.err.println("  - Received an RTP PayloadChangeEvent.");
		    System.err.println("Sorry, cannot handle payload change.");
		    System.exit(0);

		}
	    
		else if (evt instanceof NewReceiveStreamEvent) {

		    try {
		    	stream = ((NewReceiveStreamEvent)evt).getReceiveStream();
		    	
		    	synchronized (dataSync) {
				    dataReceived = true;
				    dataSync.notifyAll();
				}

			
		    } catch (Exception e) {
			System.err.println("NewReceiveStreamEvent exception " + e.getMessage());
			return;
		    }
	        
		}

		else if (evt instanceof ByeEvent) {

		     System.err.println("  - Got \"bye\" from: " + participant.getCNAME());
		   
		}
	}
	
	
	@Override
	public void update(SessionEvent arg0) {
		log.debug("SessionListener.update");
		
	}

	/**
	 * Adding a new Viewer
	 */
	//------------------------------------------------------------------------------------------
	public void addNewViewer(String destinationAddress, int port) throws Exception{
		log.debug("addNewViewer : destinationAddr = " + destinationAddress + ", port=" + port);
		
		RTPManager manager = RTPManager.newInstance();
		SessionAddress localAddr = new SessionAddress( InetAddress.getLocalHost(), sessionData.getOutgoingRTPPort());
		SessionAddress destAddr = new SessionAddress( InetAddress.getByName(destinationAddress), port);
		
		manager.initialize(localAddr);
		
		class listener implements ReceiveStreamListener{

			@Override
			public void update(ReceiveStreamEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		}
		
		listener list = new listener();
		
		manager.addReceiveStreamListener(list);
		manager.addTarget(destAddr);
		
		if(stream == null)
			throw new Exception("no origin stream available");
		
		manager.createSendStream(stream.getDataSource(), 0);
		
	}
	//------------------------------------------------------------------------------------------

}
