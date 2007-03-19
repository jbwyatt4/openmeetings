package org.xmlcrmred5.videoconference;


import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.red5.server.adapter.ApplicationAdapter;
import org.red5.server.api.IClient;
import org.red5.server.api.IConnection;
import org.red5.server.api.IScope;
import org.red5.server.api.Red5;
import org.red5.server.api.IBandwidthConfigure;
import org.red5.server.api.service.IPendingServiceCall;
import org.red5.server.api.service.IPendingServiceCallback;
import org.red5.server.api.service.IServiceCapableConnection;
import org.red5.server.api.stream.IBroadcastStream;
import org.red5.server.api.stream.IPlayItem;
import org.red5.server.api.stream.IPlaylistSubscriberStream;
import org.red5.server.api.stream.IStreamAwareScopeHandler;
//import org.red5.server.api.stream.IStreamCapableConnection;
import org.red5.server.api.stream.ISubscriberStream;
import org.xmlcrmred5.configutils.BandwidthConfigFactory;
import org.xmlcrmred5.configutils.CustomBandwidth;
import org.xmlcrmred5.configutils.UserConfigFactory;
//import org.red5.server.api.stream.support.SimpleBandwidthConfigure;


//import org.xmlcrmred5.configutils.BandWidthHelper;
import org.xmlcrmred5.videobeans.RoomClient;
import org.xmlcrmred5.utils.Calender;

/**
 * 
 * @author swagner
 * 
 */

public class Application extends ApplicationAdapter implements
		IPendingServiceCallback, IStreamAwareScopeHandler {

	private static final Log log = LogFactory.getLog(Application.class);

	private static HashMap<String,RoomClient> ClientList = new HashMap<String,RoomClient>();
	
	private static BandwidthConfigFactory bwFactory = null;
	private static UserConfigFactory userFactory = null;
	
	private static Application instance = null;
	
	public static synchronized Application getInstance(){
		return instance;
	}
	
	public Application(){
		instance = this;
	}
	
	private void initBandWidthConfigs(){
		try {
			log.debug("Init Stuff Config parser: ");
			bwFactory = new BandwidthConfigFactory();
			bwFactory.getBandwidthConfigFile();
			
			userFactory = new UserConfigFactory();
			userFactory.getUserConfigFile();
		} catch (Exception err){
			log.error("Err: "+err);
		}

	}

	@Override
	public boolean appStart(IScope scope) {
		// init your handler here
		initBandWidthConfigs();
		System.out.println("appStart    ");
		return true;
	}

	@Override
	public boolean roomConnect(IConnection conn, Object[] params) {
		log.info("roomConnect    : " + conn.getHost() + " "+ conn.getClient() + " " + conn.getClient().getId());
		return true;
	}

	@Override
	public void roomDisconnect(IConnection conn) {
		log.debug("roomDisconnect: " + conn.getHost() + " "+ conn.getClient() + " " + conn.getClient().getId());
		try {
			IConnection current = Red5.getConnectionLocal();

			String streamid = current.getClient().getId();
			
			log.info("##### roomDisconnect :.remove " + streamid); // just a unique number
			log.info(ClientList);
			log.info(ClientList.get(streamid));
			RoomClient rc = ClientList.get(streamid);

			log.debug("removing USername "+rc.getUsername()+" "+rc.getConnectedSince()+" streamid: "+rc.getStreamid());
			ClientList.remove(streamid);
			
			//If this Room is empty clear the Room Poll List
			HashMap<String,RoomClient> rcpList = this.getClientListScope();
			log.debug("roomDisconnect rcpList size: "+rcpList.size());
			if (rcpList.size()==0){
				RemoteService.clearRoomPollList(current.getScope().getName());
//				log.debug("clearRoomPollList cleared");
			}
			
			//Notify all clients of the same scope (room)
//			log.debug("scope to ... "+scope.getName()+" "+scope.getContextPath()+" "+scope.getType());
			Iterator<IConnection> it = scope.getConnections();
			
//			log.debug("sending to ... ");
			while (it.hasNext()) {
				log.debug("hasNext == true");
				IConnection cons = it.next();
//				log.debug("cons Host: "+cons.getHost());
				if (cons instanceof IServiceCapableConnection) {
//					log.debug("sending roomDisconnect to " + cons);
					((IServiceCapableConnection) cons).invoke("roomDisconnect",new Object[] { rc }, this);
//					log.debug("sending roomDisconnect to " + cons);
				}
			}			
			
		} catch (Exception err){
			log.error("roomDisconnect: "+err);
			log.error(err);
		}
	}

	@Override
	public boolean roomStart(IScope room) {
		log.debug("roomStart " + room + room.getClients().size() + " "+ room.getContextPath() + " " + room.getName());
		return true;
	}

	@Override
	public void roomStop(IScope room) {
		log.debug("roomStop " + room + room.getClients().size() + " "+ room.getContextPath() + " " + room.getName());
	}

	@Override
	public boolean roomJoin(IClient client, IScope room) {
		try {
			
			IConnection conn = Red5.getConnectionLocal();
			IServiceCapableConnection service = (IServiceCapableConnection) conn;
			log.error("Client connected xmlcrmred5 jar " + client.getId() + " conn "+ client);
			log.error("Setting stream xmlcrmred5 xmlcrmred5 id: " + getClients().size()); // just a unique number
			service.invoke("setId", new Object[] { client.getId() },this);
	
			//Store the Connection into a bean and add it to the HashMap
			RoomClient rcm = new RoomClient();
			rcm.setConnectedSince(new Date());//in miliseconds since 1970
			rcm.setFormatedDate(Calender.getInstance().getTimeMili(rcm.getConnectedSince().getTime()));
			rcm.setStreamid(client.getId());
			rcm.setUserroom(room.getName());
			
			rcm.setUserport(conn.getRemotePort());
			rcm.setUserip(conn.getRemoteAddress());
			rcm.setSwfurl(conn.getConnectParams().get("swfUrl").toString());
//			IBandwidthConfigure bandwidthConfig = conn.getClient().getBandwidthConfigure();
			
			log.info("##### : " + rcm.getStreamid()); // just a unique number
			
			//conn.ping();
			//int getLastPing = conn.getLastPingTime();
			
			//log.debug("getLastPing: "+getLastPing);
			
			//Start Bandwidth detection
//			BandWidthHelper bwHelp = new BandWidthHelper();
//			String jobName = addScheduledJob(750,bwHelp);
//			bwHelp.setSchedulerName(jobName);
//			bwHelp.setConnection(conn);
//			bwHelp.setRefInstance(this);
			
//			log.debug("bandwidthConfig: "+bandwidthConfig);
			
//			Set the moderation for the CLient on startup
			log.error("Current clients in this room: "+conn.getScope().getClients().size());		
			
			if (conn.getScope().getClients().size()==0){
				log.error("This client is the moderator"+rcm.getStreamid());
				rcm.setIsMod(new Boolean(true));
			} else {
				log.error("This client is not the moderator"+rcm.getStreamid());
				rcm.setIsMod(new Boolean(false));
			}
			ClientList.put(rcm.getStreamid(),rcm);

		} catch (Exception err){
			log.error(err);
			System.out.print(err);
		}		
		return true;
	}

	@Override
	public void roomLeave(IClient client, IScope room) {
		log.debug("roomLeave " + client.getId() + " "+ room.getClients().size() + " " + room.getContextPath() + " "+ room.getName());
	}

	/**
	 * This Method will be called every time a Client connects
	 * 
	 * @return boolean
	 */
	@Override
	public boolean appConnect(IConnection conn, Object[] params) {
		log.debug("appConnect " + conn + " "+ conn.getClient().getId() + " ");
		return true;
	}
	
	/**
	 * this method is called every time a client disconencts
	 */
	@Override
	public void appDisconnect(IConnection conn){
		
		log.debug("appDisconnect: ");
		//key = streamid
		
	}


	/**
	 * This method handles the Event after a stream has been added all conencted
	 * Clients in the same room will gat a notification
	 * 
	 * @return void
	 * 
	 */
	public void streamPublishStart(IBroadcastStream stream) {

		// Notify all the clients that the stream had been started
		log.debug("start streamPublishStart broadcast start: "+ stream.getPublishedName());
		
		sendClientBroadcastNotifications(stream,"newStream",ClientList.get(Red5.getConnectionLocal().getClient().getId()));
	}

	
	/**
	 * This method handles the Event after a stream has been removed all conencted
	 * Clients in the same room will gat a notification
	 * 
	 * @return void
	 * 
	 */
	public void streamBroadcastClose(IBroadcastStream stream) {

		// Notify all the clients that the stream had been started
		log.debug("start streamBroadcastClose broadcast close: "+ stream.getPublishedName());
		try {
			sendClientBroadcastNotifications(stream,"closeStream",ClientList.get(Red5.getConnectionLocal().getClient().getId()));
		} catch (Exception e){
			log.error(e);
		}
	}
	
	/**
	 * This method handles the notification roombased
	 * 
	 * @return void
	 * 
	 */	
	private void sendClientBroadcastNotifications(IBroadcastStream stream,String clientFunction, RoomClient rc){
		try {

			// Store the local sothat we do not send notification to ourself back
			IConnection current = Red5.getConnectionLocal();
			
			// Notify all the clients that the stream had been started
			log.debug("sendClientBroadcastNotifications: "+ stream.getPublishedName());

			// First we have to get the scopeName of this stream
			String thisScopesName = stream.getScope().getName();
//			log.debug("stream thisScopesName: " + thisScopesName);
//			log.debug("this scopeName another one "+scope.getName());

			// Then we have to get the name of all Scopes to check which one is
			// in the same room
			// Sothat only people in the same room will get notifications
			Iterator<String> scopeNames = scope.getScopeNames();

			while (scopeNames.hasNext()) {
				String scopeName = scopeNames.next();
//				log.debug("stream scopeName: " + scopeName + " thisScopesName "+ thisScopesName);
				
				// Compare the two scopeNames 
				// TODO: Why do we have to add a *:* in front of the scopeName to compare them?
				if (scopeName.equals(":" + thisScopesName)) {
					// Found the Scope
//					log.debug("+** stream scopeName Found: " + scopeName);

					// Get all Clients of this Scope
					
//					log.debug("this scopeName another one "+scope.getName());
//					log.debug("this scopeName another type "+scope.getType());					
					
					IScope roomScope = scope.getScope(thisScopesName);
//					log.debug("type of the found scope: "+ roomScope.getName());
//					log.debug("type of the found scope: "+ roomScope.getType());

					// Send them a notification of this stream
					Iterator<IConnection> it = roomScope.getConnections();
					while (it.hasNext()) {
						IConnection conn = it.next();
						if (conn.equals(current)){
							//there is a Bug in the current implementation of the appDisconnect
							if (clientFunction.equals("closeStream")){
								// Don't notify current client
								log.info("-----> current found : "+current.getRemoteAddress()+" "+current.getRemotePort());
								current.ping();
								log.info("-----> current ping time : "+current.getLastPingTime());
								
								log.info("-----> current closed: "+current.getSessionId());
							}
							continue;
						} else {
							if (conn instanceof IServiceCapableConnection) {
								conn.ping();
								IServiceCapableConnection iStream = (IServiceCapableConnection) conn;
	//							log.info("IServiceCapableConnection ID " + iStream.getClient().getId());
								iStream.invoke(clientFunction,new Object[] { rc }, this);
								log.debug("sending notification to " + conn+" ID: ");
							}
						}
					}

				}
			}
		} catch (Exception err) {
			System.out.println("Exception err: " + err);
		}
	}
	
	/*
	 * Functions to handle the moderation
	 */
	
	/**
	 * This Method will be invoked by each client if he applies for the moderation
	 * 
	 * @param id
	 * @return
	 */

	public String setModerator(String id) {
		String returnVal = "setModerator";
		try {
			
			// IConnection current = Red5.getConnectionLocal();
			// String id = current.getClient().getId();
			log.error("*..*setModerator id: " + id);
			
			IConnection current = Red5.getConnectionLocal();
			String streamid = current.getClient().getId();
			String roomname = current.getScope().getName();
			
			log.debug("streamid: "+streamid);
			log.debug("roomname: "+roomname);
			
			RoomClient rlc = ClientList.get(id);
			
			log.debug("RooymClient rlc: "+rlc.getUserroom());
			roomname = rlc.getUserroom();
			rlc.setIsMod(new Boolean(true));
			//Put the mod-flag to true for this client
			ClientList.put(id, rlc);
			
			//Now set it false for all other clients of this room
			Set<String> keys = ClientList.keySet();
			Iterator<String> iter = keys.iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				RoomClient rcl = ClientList.get(key);
				
				log.debug("#+#+#+#+##+## unsetModerator ClientList key: "+rcl.getStreamid());
				log.debug("#+#+#+#+##+## unsetModerator ClientList key: "+rcl.getUserroom());
				
				//Check if the Client is in the same room
				if(roomname.equals(rcl.getUserroom()) && !id.equals(rcl.getStreamid())){
					log.debug("set to false for client: "+rcl);
					rcl.setIsMod(new Boolean(false));
					ClientList.put(key, rcl);
				}				
			}
	
			//Notify all clients of the same scope (room)
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				IConnection conn = it.next();
				if (conn instanceof IServiceCapableConnection) {
					((IServiceCapableConnection) conn).invoke("setNewModerator",new Object[] { rlc }, this);
					log.debug("sending setNewModerator to " + conn);
				}
			}
		} catch (Exception err){
			log.error(err);
			System.out.print(err);
			returnVal = err.toString();
		}
		return returnVal;
	}
	
	public RoomClient setUsername(String userId, String username, String firstname, String lastname){
		
		RoomClient currentClient = null;
		try {

			log.error("#*#*#*#*#*#*# setUsername userId: "+userId+" username: "+username+" firstname: "+firstname+" lastname: "+lastname);
			
			IConnection current = Red5.getConnectionLocal();
			log.error("current: "+current.getScope().getName());
			
			log.error(current.getClient());
			log.error(current.getClient().getId());
			String streamid = current.getClient().getId();
			currentClient = ClientList.get(streamid);
			log.error("[setUsername] id: "+currentClient.getStreamid());
			currentClient.setUsername(username);

			currentClient.setUserObject(userId, username, firstname, lastname);
			ClientList.put(streamid, currentClient);
			log.error("##### setUsername : " + currentClient.getUsername()+" "+currentClient.getStreamid()); // just a unique number		
		} catch (Exception err){
			log.error(err);
			log.error("setUsername: "+err);
		}
		return currentClient;
	}
	
	public int setUserObjectOne2Four(String colorObj, int userPos){
		try {
			IConnection current = Red5.getConnectionLocal();
			
			RoomClient currentClient = ClientList.get(current.getClient().getId());
			log.error("xmlcrm setUserObjectOne2Four: "+currentClient.getUsername());
			currentClient.setUsercolor(colorObj);
			currentClient.setUserpos(userPos);
			//Notify all clients of the same scope (room)
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				IConnection conn = it.next();
				if (conn.equals(current)){
					continue;
				} else {				
					if (conn instanceof IServiceCapableConnection) {
						((IServiceCapableConnection) conn).invoke("setUserObjectNewOne2Four",new Object[] { currentClient }, this);
						log.debug("sending setNewModerator to " + conn);
					}
				}
			}				
		} catch (Exception err) {
			log.error("xmlcrm [setUserObject]"+err);
		}
		return -1;		
	}

	/**
	 * Get all ClientList Objects of that room
	 * @return
	 */
	public HashMap<String,RoomClient> getClientListScope(){
		HashMap <String,RoomClient> roomClientList = new HashMap<String,RoomClient>();
		try {
			//Get ClientObjects of all Clients in this Room
			String currentScopeName = Red5.getConnectionLocal().getScope().getName();
			log.debug("getClientList currentScopeName: "+currentScopeName);
			Set<String> keys = ClientList.keySet();
			Iterator<String> iter = keys.iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				log.debug("getClientList key: "+key);
				RoomClient rcl = ClientList.get(key);
				if (rcl.getUserroom().equals(currentScopeName)) roomClientList.put(key, rcl);
			}
		} catch (Exception err) {
			log.error(err);
		}
		return roomClientList;
	}
	
	/**
	 * Get all connected Clients
	 * @return
	 */
	public HashMap<String,RoomClient> getClientListAll(){
		IClient myclient = Red5.getConnectionLocal().getClient();
		log.debug("getClientListAll: "+myclient.getAttribute("role"));
		if(myclient.getAttribute("role").equals("admin"))
			return ClientList;
		else 
			return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public RoomClient getCurrentModerator(){
		RoomClient currentMod = null;
		try {
			log.debug("*..*getCurrentModerator id: ");
			
			IConnection current = Red5.getConnectionLocal();
			String streamid = current.getClient().getId();
			String roomname = current.getScope().getName();
			
			log.debug("streamid: "+streamid);
			log.debug("roomname: "+roomname);	
			
			currentMod = getCurrentModeratorByRoom(roomname);

			log.debug("Who is this moderator"+currentMod);
		} catch (Exception err){
			log.error(err);
			System.out.println("getCurrentModerator: "+err);
		}
		return currentMod;
	}
	
	/**
	 * Internal Funciton to get the current Moderator in this room
	 * 
	 * @param roomname
	 * @return
	 */
	private RoomClient getCurrentModeratorByRoom(String roomname) throws Exception{
		RoomClient currentModStreamid = null;
		Set<String> keys = ClientList.keySet();
		Iterator<String> iter = keys.iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			RoomClient rcl = ClientList.get(key);
//			
			log.debug("*..*unsetModerator ClientList key: "+rcl.getStreamid());
			log.debug("*..*unsetModerator ClientList key: "+rcl.getUserroom());
//			
			//Check if the Client is in the same room
			if(roomname.equals(rcl.getUserroom()) && rcl.getIsMod()){
				log.debug("found client who is the Moderator: "+rcl);
				currentModStreamid = rcl;
			}				
		}
		return currentModStreamid;
	}
	
	public int sendVars(Object vars) {
		log.debug("*..*sendVars: " + vars);
		boolean ismod = false;
		// Check if this User is the Mod:
		IConnection current = Red5.getConnectionLocal();
		String id = current.getClient().getId();
		log.debug("***** id: " + id);
		
		RoomClient hasMap = (RoomClient) ClientList.get(id);
		ismod = hasMap.getIsMod();
		
		log.debug("*..*ismod: " + ismod);

		if (ismod) {
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				IConnection conn = it.next();
				if (conn instanceof IServiceCapableConnection) {
					String idremote = conn.getClient().getId();
					log.debug("*..*idremote: " + idremote);
					log.debug("*..*my id: " + id);
					if (!id.equals(idremote)) {
						((IServiceCapableConnection) conn).invoke("sendVarsToWhiteboard", new Object[] { vars },this);
						log.debug("sending sendVarsToWhiteboard to " + conn);
					}
				}
			}
			return 1;
		} else {
			// log.debug("*..*you are not allowed to send: "+ismod);
			return -1;
		}
	}

	public int sendVarsModeratorGeneral(Object vars) {
		log.debug("*..*sendVars: " + vars);
		boolean ismod = false;
		// Check if this User is the Mod:
		IConnection current = Red5.getConnectionLocal();
		String id = current.getClient().getId();
		log.debug("***** id: " + id);

		RoomClient hasMap = (RoomClient) ClientList.get(id);
		ismod = hasMap.getIsMod();

		log.debug("*..*ismod: " + ismod);

		if (ismod) {
			log.debug("CurrentScope :"+current.getScope().getName());
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				IConnection conn = it.next();
				if (conn instanceof IServiceCapableConnection) {
					String idremote = conn.getClient().getId();
					log.debug("*..*idremote: " + idremote);
					log.debug("*..*my id: " + id);
					if (!id.equals(idremote)) {
						((IServiceCapableConnection) conn).invoke("sendVarsToModeratorGeneral",	new Object[] { vars }, this);
						log.debug("sending sendVarsToWhiteboard to " + conn);
					}
				}
			}
			return 1;
		} else {
			// log.debug("*..*you are not allowed to send: "+ismod);
			return -1;
		}
		
	}

	public int sendMessage(Object newMessage) {
		IConnection current = Red5.getConnectionLocal();
		Iterator<IConnection> it = current.getScope().getConnections();
		while (it.hasNext()) {
			IConnection conn = it.next();
			if (conn instanceof IServiceCapableConnection) {
				((IServiceCapableConnection) conn).invoke("sendVarsToMessage",new Object[] { newMessage }, this);
				log.debug("sending sendVarsToMessage to " + conn);
			}
		}
		return 1;
	}


	public int sendMessageWithClient(Object newMessage) {
		try {
			IConnection current = Red5.getConnectionLocal();
			Iterator<IConnection> it = current.getScope().getConnections();
			log.debug("***** id: " + current.getClient().getId());
			RoomClient rcm = (RoomClient) ClientList.get(current.getClient().getId());	
			HashMap<String,Object> hsm = new HashMap<String,Object>();
			hsm.put("client", rcm);
			hsm.put("message", newMessage);
			while (it.hasNext()) {
				IConnection conn = it.next();
				if (conn instanceof IServiceCapableConnection) {
					((IServiceCapableConnection) conn).invoke("sendVarsToMessageWithClient",new Object[] { hsm }, this);
					log.debug("sending sendVarsToMessageWithClient to " + conn);
				}
			}
		} catch (Exception err) {
			log.error("[sendMessageWithClient] "+err);
			return -1;
		}
		return 1;
	}

	public int sendMessageWithClientById(Object newMessage, String clientId) {
		try {
			IConnection current = Red5.getConnectionLocal();
			Iterator<IConnection> it = current.getScope().getConnections();
			log.debug("***** id: " + current.getClient().getId());
			RoomClient rcm = (RoomClient) ClientList.get(current.getClient().getId());	
			HashMap<String,Object> hsm = new HashMap<String,Object>();
			hsm.put("client", rcm);
			hsm.put("message", newMessage);
			while (it.hasNext()) {
				IConnection conn = it.next();
				if (conn instanceof IServiceCapableConnection) {
					if (conn.getClient().getId().equals(clientId)){
						((IServiceCapableConnection) conn).invoke("sendVarsToMessageWithClient",new Object[] { hsm }, this);
						log.debug("sendingsendVarsToMessageWithClient ByID to " + conn);
					}
				}
			}
		} catch (Exception err) {
			log.error("[sendMessageWithClient] "+err);
			return -1;
		}		
		return 1;
	}
	
	
	public void streamBroadcastStart(IBroadcastStream stream) {
		log.debug("### streamBroadcastStart Name: " + stream.getName());
	}

	public void streamPlaylistItemPlay(IPlaylistSubscriberStream stream,IPlayItem item, boolean isLive) {
	}

	public void streamPlaylistItemStop(IPlaylistSubscriberStream stream,IPlayItem item) {

	}

	public void streamPlaylistVODItemPause(IPlaylistSubscriberStream stream,IPlayItem item, int position) {

	}

	public void streamPlaylistVODItemResume(IPlaylistSubscriberStream stream,IPlayItem item, int position) {

	}

	public void streamPlaylistVODItemSeek(IPlaylistSubscriberStream stream,IPlayItem item, int position) {

	}

	public void streamSubscriberClose(ISubscriberStream stream) {
		log.debug("### streamSubscriberClose Name: " + stream.getName() + "|"+ stream.getStreamId() + "|"+ stream.getConnection().getScope() + "|" + stream.hashCode());
	}

	public void streamSubscriberStart(ISubscriberStream stream) {
		log.debug("### streamSubscriberStart Name: " + stream.getName());
	}

	/**
	 * Get streams. called from client its based on the current scope so this will only return the streams of the
	 * current room
	 * 
	 * @return iterator of broadcast stream names
	 */
	public List<String> getStreams() {
		List <String> roomClientListScopeNames = new LinkedList<String>();
		try {
			//Get ClientObjects of all Clients in this Room
			String currentScopeName = Red5.getConnectionLocal().getScope().getName();
			String currentStreamid = Red5.getConnectionLocal().getClient().getId();
			log.debug("getStreams currentScopeName: "+currentScopeName);
			Set<String> keys = ClientList.keySet();
			Iterator<String> iter = keys.iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				log.debug("getStreams key: "+key);
				RoomClient rcl = ClientList.get(key);
				if (rcl.getUserroom().equals(currentScopeName) && !rcl.getStreamid().equals(currentStreamid)) roomClientListScopeNames.add(rcl.getStreamid());
			}
		} catch (Exception err) {
			log.error(err);
		}
		return roomClientListScopeNames;
		//return getBroadcastStreamNames(conn.getScope());
	}
	
	/**
	 * This Method will return all streams of the parent stream (which should be all including
	 * the child-scope streams
	 * 
	 * @return
	 */
	public List<String> getAllStreams(){
		return getBroadcastStreamNames(scope);
	}
	  

	/**
	 * Handle callback from service call.
	 */
	public void resultReceived(IPendingServiceCall call) {
		log.info("Received result " + call.getResult() + " for "+ call.getServiceMethodName());
	}
	
	public int getYourPingTime(){
		IConnection conn = Red5.getConnectionLocal();
		conn.ping();
		int lastPingTime = conn.getLastPingTime();
		return lastPingTime;
	}
	
	public void detectedBandwidth(int averagePingTime, String schedulerName, IConnection conn){
		try {
			log.debug("overAllPingTime: "+averagePingTime);
			removeScheduledJob(schedulerName);
			
			log.debug("averagePingTime "+averagePingTime);
			
			measureBandwidth(conn);
			
			CustomBandwidth ctx = bwFactory.getBandwidthForClient(averagePingTime);
			
			log.debug("----> "+ctx.getGroupName());
			
			
			((IServiceCapableConnection) conn).invoke("detectedBandWidth", new Object[] { ctx },this);
			
			//conn.getClient().setBandwidthConfigure(ctx);
			
			IBandwidthConfigure bandwidthConfig = conn.getClient().getBandwidthConfigure();
			
			log.debug("bandwidthConfig: "+bandwidthConfig);			

			if (bandwidthConfig!=null){
//				long burst = bandwidthConfig.getChannelBandwidth()
//				long maxburst = bandwidthConfig.getMaxBurst();
//				long upstreamBandWidth = bandwidthConfig.getUpstreamBandwidth();
//				long audiobandwidth = bandwidthConfig.getAudioBandwidth();
//				long videobandwidth = bandwidthConfig.getVideoBandwidth();
//				long overallBandwidth = bandwidthConfig.getOverallBandwidth();
//				long downstreamBandwidth = bandwidthConfig.getDownstreamBandwidth();
//			
//				log.debug("burst: "+burst);
//				log.debug("maxburst: "+maxburst);
//				log.debug("upstreamBandWidth: "+upstreamBandWidth);
//				log.debug("audiobandwidth: "+audiobandwidth);
//				log.debug("videobandwidth: "+videobandwidth);
//				log.debug("overallBandwidth: "+overallBandwidth);
//				log.debug("downstreamBandwidth: "+downstreamBandwidth);
			} else {
				log.debug("bandwidthConfig is null!");
			}
		
		} catch (Exception err) {
			log.error("Exception "+err);
		}
		
		
	}

	/* (non-Javadoc)
	 * @see org.red5.server.api.stream.IStreamAwareScopeHandler#streamRecordStart(org.red5.server.api.stream.IBroadcastStream)
	 */
	public void streamRecordStart(IBroadcastStream stream) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return the bwFactory
	 */
	public static BandwidthConfigFactory getBwFactory() {
		return bwFactory;
	}

	/**
	 * @return the userFactory
	 */
	public static UserConfigFactory getUserFactory() {
		return userFactory;
	}

	/**
	 * @return the clientList
	 */
	public static HashMap<String, RoomClient> getClientList() {
		return ClientList;
	}
	
}