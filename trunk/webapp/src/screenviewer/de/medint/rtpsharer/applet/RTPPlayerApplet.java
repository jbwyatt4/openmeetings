
package de.medint.rtpsharer.applet;

import java.applet.Applet;
import javax.media.rtp.*;
import javax.media.rtp.rtcp.*;
import javax.media.rtp.event.*;

import com.sun.media.rtp.RTPSessionMgr;

import java.awt.*;
import java.net.*;
import java.awt.event.*;
import java.lang.String;
import javax.media.*;
import javax.media.protocol.*;

import java.io.IOException;


public class RTPPlayerApplet extends Applet implements ControllerListener, ReceiveStreamListener, SessionListener, ActionListener{
    
	/** Destination Host */
    InetAddress destaddr;
    
    /** Address */
    String address;
    
    /** Connection Port */
    String portstr;
    
    /** Media Type */
    String media;
    
    /** Player Window*/
    Player videoplayer = null;
    
     
    Component visualComponent = null;
    
    Component controlComponent = null;
    
    Panel panel = null;
    
    Button videobutton = null;
    
    GridBagLayout gridbag = null;
    
    GridBagConstraints c = null;
    
    int width = 320;
    int height =0;
    
    boolean dataReceived = false;
    
    Object dataSync = new Object();
    
    RTPManager rtpManager;

    
    /**
     * Applet initialization
     */
    //-----------------------------------------------------------------------------------------------
    public void init(){
    	
    	System.out.println("init");
    	
        setLayout( new BorderLayout() );
        Panel buttonPanel = new Panel();
        buttonPanel.setLayout( new FlowLayout() );
        add("North", buttonPanel);
       
        
        // Retrieiving params from Enclosing HTMLPage
        media = getParameter("video");
        
        if (media.equals("On")){
            address = getParameter("videosession");
            portstr = getParameter("videoport");
            
            System.out.println("Desstination Address Red5 Host : " + address);
            System.out.println("Destination RTP PORT: " + portstr);
           
        }
        
        try {
    	    InetAddress ipAddr;
    	    SessionAddress localAddr = new SessionAddress();
    	    SessionAddress destAddr;

    	    rtpManager = RTPManager.newInstance();
    	    rtpManager.addSessionListener(this);
    	    rtpManager.addReceiveStreamListener(this);

    		ipAddr = InetAddress.getByName(address);

    		localAddr= new SessionAddress( InetAddress.getLocalHost(),Integer.parseInt(portstr));
            destAddr = new SessionAddress( ipAddr,Integer.parseInt(portstr));
    	
    			
    		rtpManager.initialize( localAddr);

    		// You can try out some other buffer size to see
    		// if you can get better smoothness.
    		rtpManager.addTarget(destAddr);
    		
    		

          } catch (Exception e){
              System.err.println("Cannot create the RTP Session: " + e.getMessage());
              return ;
          }

    	// Wait for data to arrive before moving on.

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
    	} catch (Exception e) {}

    	if (!dataReceived) {
    	    System.err.println("No RTP data was received.");
    	    
    	    return ;
    	}

           
      }
    //-----------------------------------------------------------------------------------------------
    
    /**
     * ControllerListener for the Players.
     */
    public synchronized void controllerUpdate(ControllerEvent ce) {

    	System.out.println("ControllerUpdate");
        Player player = null;
        
        Controller controller = (Controller)ce.getSource();
        
        if (controller instanceof Player)
            player  =(Player)ce.getSource();
        
        if (player == null)
            return;
        
        
        if (ce instanceof RealizeCompleteEvent) {
        	System.out.println("RealizeCompleteEvent");
            // add the video player's visual component to the applet
            if (( visualComponent =
                  player.getVisualComponent())!= null){
                width = visualComponent.getPreferredSize().width;
                height += visualComponent.getPreferredSize().height;
                if (panel == null) {
                    panel = new Panel();
                    repositionPanel(width, height);
                    panel.setLayout(new BorderLayout());
                }
                panel.add("Center", visualComponent);
                panel.validate();
            }
            // add the player's control component to the applet
            if (( controlComponent = 
                  player.getControlPanelComponent()) != null){
                height += controlComponent.getPreferredSize().height;
                if (panel == null) {
                    panel = new Panel();
                    panel.setLayout(new BorderLayout());
                }
                repositionPanel(width, height);
                panel.add("South", controlComponent);
                panel.validate();
            }
            
            if (panel != null){
                add("Center", panel);
                invalidate();
            }
        }

        if (ce instanceof SizeChangeEvent) {
        	System.out.println("SizeChangeEvent");
            if (panel != null){
                SizeChangeEvent sce = (SizeChangeEvent) ce;
                int nooWidth = sce.getWidth();
                int nooHeight = sce.getHeight();
                
                // Add the height of the default control component
                if (controlComponent != null)
                    nooHeight += controlComponent.getPreferredSize().height;
                
                // Set the new panel bounds and redraw
                repositionPanel(nooWidth, nooHeight);
            }
        }
        validate();
    }

    
    
    // start player on applet start
    public void start(){
    	
    	
        if (videoplayer != null){
            videoplayer.start();
        }
        
    }
    // applet has been stopped, stop and deallocate all the RTP players.
    public void stop(){
        if (videoplayer != null){
            videoplayer.close();
        }
       
    }

    // applet has been destroyed by the browser. Close the Session
    // Manager. 
    public void destroy(){
        // close the video and audio RTP SessionManagers
         
        if(rtpManager != null)
        	rtpManager.dispose();
        
        super.destroy();
    }
            
   
    
    public void actionPerformed(ActionEvent event){
        Button button = (Button)event.getSource();
       
    }
    
    /**
     * SessionListener.
     */
    public synchronized void update(SessionEvent evt) {
    	if (evt instanceof NewParticipantEvent) {
    		Participant p = ((NewParticipantEvent)evt).getParticipant();
    		System.err.println("  - A new participant had just joined: " + p.getCNAME());
    	}
    }
    

    /**
     * ReceiveStreamListener
     */
    public synchronized void update( ReceiveStreamEvent evt) {

    	RTPManager mgr = (RTPManager)evt.getSource();
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
			DataSource ds = stream.getDataSource();
			
			videoplayer = javax.media.Manager.createPlayer(ds);
			if (videoplayer == null)
			    return;

			videoplayer.addControllerListener(this);
			videoplayer.realize();

	
			// Find out the formats.
			RTPControl ctl = (RTPControl)ds.getControl("javax.media.rtp.RTPControl");
			if (ctl != null){
			    System.err.println("  - Recevied new RTP stream: " + ctl.getFormat());
			} else
			    System.err.println("  - Recevied new RTP stream");
	
			if (participant == null)
			    System.err.println("      The sender of this stream had yet to be identified.");
			
		else {
		    System.err.println("      The stream comes from: " + participant.getCNAME()); 
		}

		
		// Notify intialize() that a new stream had arrived.
		synchronized (dataSync) {
		    dataReceived = true;
		    dataSync.notifyAll();
		}

	    } catch (Exception e) {
		System.err.println("NewReceiveStreamEvent exception " + e.getMessage());
		return;
	    }
        
	}

	else if (evt instanceof StreamMappedEvent) {

	     if (stream != null && stream.getDataSource() != null) {
		DataSource ds = stream.getDataSource();
		// Find out the formats.
		RTPControl ctl = (RTPControl)ds.getControl("javax.media.rtp.RTPControl");
		System.err.println("  - The previously unidentified stream ");
		if (ctl != null)
		    System.err.println("      " + ctl.getFormat());
		System.err.println("      had now been identified as sent by: " + participant.getCNAME());
	     }
	}

	else if (evt instanceof ByeEvent) {

	     System.err.println("  - Got \"bye\" from: " + participant.getCNAME());
	    
	     
	}
    	
    	

    }
    
    void repositionPanel(int width, int height) {
        panel.setBounds(0,
                        0,
                        width,
                        height);
        panel.validate();
    }
    /*
    public String getAddress(){
        return  address;
    }
    
    public int getPort(){
        // the port has to be returned as an integer
        return StrToInt(portstr);
    }
    
    public String getMedia(){
        return media;
    }
    
    private int StrToInt(String str){
        if (str == null)
            return -1;
        Integer retint = new Integer(str);
        return  retint.intValue();
    }

    public synchronized void controllerUpdate(ControllerEvent event) {
    	System.out.println("ControllerUpdate");
        Player player = null;
        
        Controller controller = (Controller)event.getSource();
        
        if (controller instanceof Player)
            player  =(Player)event.getSource();
        
        if (player == null)
            return;
        
        
        if (event instanceof RealizeCompleteEvent) {
        	System.out.println("RealizeCompleteEvent");
            // add the video player's visual component to the applet
            if (( visualComponent =
                  player.getVisualComponent())!= null){
                width = visualComponent.getPreferredSize().width;
                height += visualComponent.getPreferredSize().height;
                if (panel == null) {
                    panel = new Panel();
                    repositionPanel(width, height);
                    panel.setLayout(new BorderLayout());
                }
                panel.add("Center", visualComponent);
                panel.validate();
            }
            // add the player's control component to the applet
            if (( controlComponent = 
                  player.getControlPanelComponent()) != null){
                height += controlComponent.getPreferredSize().height;
                if (panel == null) {
                    panel = new Panel();
                    panel.setLayout(new BorderLayout());
                }
                repositionPanel(width, height);
                panel.add("South", controlComponent);
                panel.validate();
            }
            
            if (panel != null){
                add("Center", panel);
                invalidate();
            }
        }

        if (event instanceof SizeChangeEvent) {
        	System.out.println("SizeChangeEvent");
            if (panel != null){
                SizeChangeEvent sce = (SizeChangeEvent) event;
                int nooWidth = sce.getWidth();
                int nooHeight = sce.getHeight();
                
                // Add the height of the default control component
                if (controlComponent != null)
                    nooHeight += controlComponent.getPreferredSize().height;
                
                // Set the new panel bounds and redraw
                repositionPanel(nooWidth, nooHeight);
            }
        }
        validate();
    }

     void repositionPanel(int width, int height) {
        panel.setBounds(0,
                        0,
                        width,
                        height);
        panel.validate();
    }

    public void update( ReceiveStreamEvent event){
    	System.out.println("Update");
        SessionManager source =(SessionManager)event.getSource();
        Player newplayer = null;
        // create a new player if a new recvstream is detected
        if (event instanceof NewReceiveStreamEvent){
        	System.out.print("Update.NewStreamEvent");
            try{
            	
                ReceiveStream stream = ((NewReceiveStreamEvent)event).getReceiveStream();
                DataSource dsource = stream.getDataSource();
                newplayer = Manager.createPlayer(dsource);
                
            }catch (Exception e){
            	System.err.println("RTPPlayerApplet Exception " + e.getMessage());
            	e.printStackTrace();
            }
            if (newplayer == null){
                return;
            }
            // if this is the first video player, we need to listen to
            // its events. Add me as a ControllerListener before
            // starting the player
            if (source == videomgr){
                if (videoplayer == null){
                    videoplayer = newplayer;
                    newplayer.addControllerListener(this);
                    newplayer.start();
                }               
                else{
                	
                	videoplayer.stop();
                	
                	 StartSessionManager(address,StrToInt(portstr),"video");
                	videoplayer = newplayer;
                    videoplayer.addControllerListener(this);
                    videoplayer.start();
                    
                }
            }
        }

        
        if (event instanceof RemotePayloadChangeEvent){
            // we received a payload change event. If a player was not
            // created for this ReceiveStream, create a player. If the
            // player already exists, RTPSM and JMF have taken care of
            // switching the payloads and we dont do anything.
            // If this is the first video player add me as the
            // controllerlistener before starting the player, else
            // just create a new player window.
        }
        
    }// end of RTPSessionUpdate
        
    private SessionManager StartSessionManager(String destaddrstr,
                                                  int port,
                                                  String media){
    	
    	System.out.println("StartSessionManager");
        // this method create a new RTPSessionMgr and adds this applet
        // as a SessionListener, before calling initSession() and startSession()
        SessionManager mymgr = new RTPSessionMgr();
        if (media.equals("video"))
            videomgr = mymgr;
       
        if (mymgr == null)
            return null;
        
        mymgr.addReceiveStreamListener(this);
        
        System.out.println("StartSessionManager.added receiveStreamListener");
        
        //if (media.equals("audio"))
        //  EncodingUtil.Init((SessionManager)mymgr);
        
        // for initSession() we must generate a CNAME and fill in the
        // RTP Session address and port
        String cname = mymgr.generateCNAME();
        String username = "jmf-user";

        SessionAddress localaddr = new SessionAddress();
        
        
        try{
            destaddr = InetAddress.getByName(destaddrstr);
        }catch (UnknownHostException e){
            System.err.println("Exception resolving destinationAddress :  " + e.getMessage());
            e.printStackTrace();
        }    
        SessionAddress sessaddr = new SessionAddress(destaddr,
                                                           port,
                                                           destaddr,
                                                           port+1);
        
        SourceDescription[] userdesclist = new SourceDescription[4];
        int i;
        for(i=0; i< userdesclist.length;i++){
            if (i == 0){
                userdesclist[i] = new
                    SourceDescription(SourceDescription.SOURCE_DESC_EMAIL,
                                    "jmf-user@sun.com",
                                    1,
                                    false);
                continue;
            }

            if (i == 1){
                userdesclist[i] = new
              SourceDescription(SourceDescription.SOURCE_DESC_NAME,
                                    username,
                                    1,
                                    false);
                continue;
            }
            if ( i == 2){
                userdesclist[i] = new 
                    SourceDescription(SourceDescription.SOURCE_DESC_CNAME,
                                          cname,
                                      1,
                                      false);
                continue;
            }
            if (i == 3){ 
                userdesclist[i] = new
            SourceDescription(SourceDescription.SOURCE_DESC_TOOL,
                                  "JMF RTP Player v2.0",
                                  1,
                                  false);
                continue;
            }
        }// end of for
        
        // call initSession() and startSession() of the RTPsessionManager
        try{
            mymgr.initSession(localaddr,
                              mymgr.generateSSRC(),
                              userdesclist,
                              0.05,
                              0.25);
            mymgr.startSession(sessaddr,1,null);
            
           
        }catch (SessionManagerException e){
          System.err.println("RTPPlayerApplet: RTPSM Exception " + e.getMessage());
          e.printStackTrace();
          return null;
        }catch (IOException e){
           System.err.println("RTPPlayerApplet: IO Exception " + e.getMessage());
           e.printStackTrace();
           return null;
        }
        
        
        return mymgr;
    }       
	*/
}// end of class

