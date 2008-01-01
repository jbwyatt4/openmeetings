package org.xmlcrm.app.remote;


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
import org.red5.server.api.stream.ISubscriberStream;
import org.xmlcrm.app.conference.configutils.BandwidthConfigFactory;
import org.xmlcrm.app.conference.configutils.CustomBandwidth;
import org.xmlcrm.app.conference.configutils.UserConfigFactory;
import org.xmlcrm.app.quartz.scheduler.QuartzSessionClear;

import org.xmlcrm.app.conference.videobeans.RoomClient;

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
	
	private void initBandWidthConfigs(){
		try {
			log.debug("Init Stuff Config parser: ");
			
		} catch (Exception err){
			log.error("Err: "+err);
		}

	}

	@Override
	public boolean appStart(IScope scope) {
		instance = this;
		// init your handler here
		initBandWidthConfigs();
		//System.out.println("################## appStart    ");
		QuartzSessionClear bwHelp = new QuartzSessionClear();
		String jobName = addScheduledJob(300000,bwHelp);
		log.error("jobName: "+jobName);
		return true;
	}

	@Override
	public boolean roomConnect(IConnection conn, Object[] params) {
		log.error("roomConnect    : " + conn.getHost() + " "+ conn.getClient() + " " + conn.getClient().getId());
		return true;
	}

	@Override
	public void roomDisconnect(IConnection conn) {
		log.debug("roomDisconnect: " + conn.getHost() + " "+ conn.getClient() + " " + conn.getClient().getId());
	}

	@Override
	public boolean roomStart(IScope room) {
		log.error("roomStart " + room + room.getClients().size() + " "+ room.getContextPath() + " " + room.getName());
		return true;
	}

	@Override
	public void roomStop(IScope room) {
		log.error("roomStop " + room + room.getClients().size() + " "+ room.getContextPath() + " " + room.getName());
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
			rcm.setConnectedSince(new Date());
			rcm.setStreamid(client.getId());
			rcm.setUserroom("");
			
			rcm.setUserport(conn.getRemotePort());
			rcm.setUserip(conn.getRemoteAddress());
			rcm.setSwfurl(conn.getConnectParams().get("swfUrl").toString());			
			log.error("##### : " + rcm.getStreamid()); // just a unique number
			
			//Set the moderation for the CLient on startup
			log.error("Current clients in this room: "+conn.getScope().getClients().size());		
			
			log.error("This client is not the moderator"+rcm.getStreamid());
			rcm.setIsMod(new Boolean(false));
			
			ClientList.put(rcm.getStreamid(),rcm);

		} catch (Exception err){
			log.error("roomJoin",err);
		}		
		return true;
	}
	
	/**
	 * @deprecated
	 * @return
	 */
	public RoomClient logicalRoomEnter(){
		try {
			IConnection current = Red5.getConnectionLocal();
			RoomClient currentClient = ClientList.get(current.getClient().getId());	
			String roomname = currentClient.getUserroom();
			String orgdomain = currentClient.getDomain();				
			String streamid = currentClient.getStreamid();
			
			
			//check if room has no Moderation yet
			//get all Clients of room
			Set<String> keys = ClientList.keySet();
			Iterator<String> iter = keys.iterator();
			int roomcount = 0;
			while (iter.hasNext()) {
				String key = (String) iter.next();
				RoomClient rcl = ClientList.get(key);
				
				log.error("#+#+#+#+##+## logicalRoomEnter ClientList key: "+rcl.getStreamid());
				log.error("#+#+#+#+##+## logicalRoomEnter ClientList key: "+rcl.getUserroom());
				
				//Check if the Client is in the same room and same domain 
				//and is not the same like we have just declared to be moderating this room
				if(roomname.equals(rcl.getUserroom()) && orgdomain.equals(rcl.getDomain()) && !streamid.equals(rcl.getStreamid())){
					log.debug("set to ++ for client: "+rcl.getStreamid()+" "+roomcount);
					roomcount++;
				}				
			}
			
			if (roomcount==0){
				log.error("Room is empty so set this user to be moderation role");
				currentClient.setIsMod(true);
				ClientList.put(streamid, currentClient);
				return currentClient;
			} else {
				log.error("Room is already somebody so set this user not to be moderation role");
				currentClient.setIsMod(false);
				ClientList.put(streamid, currentClient);
				return currentClient;
			}
			
		} catch (Exception err) {
			log.error("[logicalRoomEnter] ",err);
		}
		return null;
	}

	/**
	 * Logic must be before roomDisconnect cause otherwise you cannot throw a message to each one
	 * 
	 */
	@Override
	public void roomLeave(IClient client, IScope room) {
		log.error("roomLeave " + client.getId() + " "+ room.getClients().size() + " " + room.getContextPath() + " "+ room.getName());
		try {
			IConnection current = Red5.getConnectionLocal();
			RoomClient currentClient = ClientList.get(current.getClient().getId());
			String roomname = currentClient.getUserroom();
			String orgdomain = currentClient.getDomain();	
			//String streamid = currentClient.getStreamid();
			
			log.error("##### roomLeave :. " + currentClient.getStreamid()); // just a unique number


			log.error("removing USername "+currentClient.getUsername()+" "+currentClient.getConnectedSince()+" streamid: "+currentClient.getStreamid());
			

			this.disconnectUser(currentClient);
			//If this Room is empty clear the Room Poll List
			HashMap<String,RoomClient> rcpList = this.getClientListByRoomAndDomain(roomname, orgdomain);
			log.error("roomLeave rcpList size: "+rcpList.size());
			if (rcpList.size()==0){
				RemoteService.clearRoomPollList(roomname,orgdomain);
//				log.debug("clearRoomPollList cleared");
			}
			
			//Notify all clients of the same scope (room) with domain and room
			//except the current disconnected cause it could throw an exception
			
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				log.error("hasNext == true");
				IConnection cons = it.next();
				log.error("cons Host: "+cons);
				if (cons instanceof IServiceCapableConnection) {
					if (!cons.equals(current)){
						log.error("sending roomDisconnect to " + cons);
						RoomClient rcl = ClientList.get(cons.getClient().getId());
						//Check if the Client is in the same room and same domain except its the current one
						if(orgdomain.equals(rcl.getDomain())){					
							((IServiceCapableConnection) cons).invoke("roomDisconnect",new Object[] { currentClient }, this);
							log.error("sending roomDisconnect to " + cons);
						}
					}
				}
			}			
			
		} catch (Exception err){
			log.error("[roomDisconnect]",err);
		}		
	}
	
	/**
	 * this means a user has left a room but only logically, he didn't leave the app he jusst left the room
	 * 
	 *
	 */
	public void logicalRoomLeave() {
		log.error("logicalRoomLeave ");
		try {
			IConnection current = Red5.getConnectionLocal();
			RoomClient currentClient = ClientList.get(current.getClient().getId());
			String roomname = currentClient.getUserroom();
			String orgdomain = currentClient.getDomain();	
			currentClient.setUserroom("");
			currentClient.setRoom_id(null);
			currentClient.setIsRecording(false);
			log.error("##### logicalRoomLeave :. " + currentClient.getStreamid()); // just a unique number


			log.error("removing USername "+currentClient.getUsername()+" "+currentClient.getConnectedSince()+" streamid: "+currentClient.getStreamid());
			ClientList.put(currentClient.getStreamid(),currentClient);
			
			//If this Room is empty clear the Room Poll List
			HashMap<String,RoomClient> rcpList = this.getClientListByRoomAndDomain(roomname, orgdomain);
			log.error("logicalRoomLeave rcpList size: "+rcpList.size());
			if (rcpList.size()==0){
				RemoteService.clearRoomPollList(roomname,orgdomain);
				log.error("logicalRoomLeave clearRoomPollList cleared");
			}
			
			//Notify all clients of the same scope (room) with domain and room
			
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				log.error("hasNext == true");
				IConnection cons = it.next();
				log.error("cons Host: "+cons);
				if (cons instanceof IServiceCapableConnection) {
					if (!cons.equals(current)){
						log.error("sending roomDisconnect to " + cons);
						RoomClient rcl = ClientList.get(cons.getClient().getId());
						//Check if the Client is in the same room and same domain except its the current one
						if(roomname.equals(rcl.getUserroom()) && orgdomain.equals(rcl.getDomain())){					
							((IServiceCapableConnection) cons).invoke("logicalRoomLeaveDis",new Object[] { currentClient }, this);
							log.error("sending roomDisconnect to " + cons);
						}
					}
				}
			}			
			
		} catch (Exception err){
			log.error("[roomDisconnect]",err);
		}		
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
		
		log.debug("appDisconnect ID: "+conn.getClient().getId());
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

		IConnection current = Red5.getConnectionLocal();
		String streamid = current.getClient().getId();
		RoomClient currentClient = ClientList.get(streamid);
		String roomname = currentClient.getUserroom();
		String orgdomain = currentClient.getDomain();			
		// Notify all the clients that the stream had been started
		log.error("start streamPublishStart broadcast start: "+ stream.getPublishedName());
		
		Iterator<IConnection> it = current.getScope().getConnections();
		while (it.hasNext()) {
			IConnection conn = it.next();
			if (conn.equals(current)){
				continue;
			} else {
				if (conn instanceof IServiceCapableConnection) {
					RoomClient rcl = ClientList.get(conn.getClient().getId());
					log.error("is this users still alive? :"+rcl);
					//Check if the Client is in the same room and same domain 
					if(roomname.equals(rcl.getUserroom()) && orgdomain.equals(rcl.getDomain())){
						IServiceCapableConnection iStream = (IServiceCapableConnection) conn;
//							log.info("IServiceCapableConnection ID " + iStream.getClient().getId());
						iStream.invoke("newStream",new Object[] { currentClient }, this);
						log.debug("sending notification to " + conn+" ID: ");
						if (rcl.getIsRecording()){
							StreamService.addRecordingByStreamId(current, streamid, currentClient, rcl.getRoomRecordingName());
						}
					}
				}
			}
		}		
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
		log.error("start streamBroadcastClose broadcast close: "+ stream.getPublishedName());
		try {
			sendClientBroadcastNotifications(stream,"closeStream",ClientList.get(Red5.getConnectionLocal().getClient().getId()));
		} catch (Exception e){
			log.error("[streamBroadcastClose]",e);
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
			RoomClient currentClient = ClientList.get(current.getClient().getId());
			String roomname = currentClient.getUserroom();
			String orgdomain = currentClient.getDomain();	
			
			// Notify all the clients that the stream had been started
			log.error("sendClientBroadcastNotifications: "+ stream.getPublishedName());
			log.error("sendClientBroadcastNotifications : "+ currentClient+ " " + currentClient.getStreamid());
			
//			Notify all clients of the same scope (room)
			Iterator<IConnection> it = current.getScope().getConnections();
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
						RoomClient rcl = ClientList.get(conn.getClient().getId());
						log.error("is this users still alive? :"+rcl);
						//Check if the Client is in the same room and same domain 
						if(roomname.equals(rcl.getUserroom()) && orgdomain.equals(rcl.getDomain())){
							conn.ping();
							IServiceCapableConnection iStream = (IServiceCapableConnection) conn;
	//							log.info("IServiceCapableConnection ID " + iStream.getClient().getId());
							iStream.invoke(clientFunction,new Object[] { rc }, this);
							log.debug("sending notification to " + conn+" ID: ");
						}
					}
				}
			}
		} catch (Exception err) {
			log.error("[sendClientBroadcastNotifications]" , err);
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
			log.error("*..*setModerator id: " + id);
			
			IConnection current = Red5.getConnectionLocal();
			String streamid = current.getClient().getId();
			
			RoomClient rlc = ClientList.get(id);
			String roomname = rlc.getUserroom();
			String orgdomain = rlc.getDomain();
			
			rlc.setIsMod(new Boolean(true));
			//Put the mod-flag to true for this client
			ClientList.put(id, rlc);
			
			//Now set it false for all other clients of this room
			Set<String> keys = ClientList.keySet();
			Iterator<String> iter = keys.iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				RoomClient rcl = ClientList.get(key);
				//Check if the Client is in the same room and same domain 
				//and is not the same like we have just declared to be moderating this room
				if(roomname.equals(rcl.getUserroom()) && orgdomain.equals(rcl.getDomain()) && !id.equals(rcl.getStreamid())){
					log.debug("set to false for client: "+rcl);
					rcl.setIsMod(new Boolean(false));
					ClientList.put(key, rcl);
				}				
			}
	
			//Notify all clients of the same scope (room)
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				IConnection conn = it.next();
				RoomClient rcl = ClientList.get(conn.getClient().getId());
				//Check if the Client is in the same room and same domain 
				if(roomname.equals(rcl.getUserroom()) && orgdomain.equals(rcl.getDomain())){
					if (conn instanceof IServiceCapableConnection) {
						((IServiceCapableConnection) conn).invoke("setNewModerator",new Object[] { rlc }, this);
						log.debug("sending setNewModerator to " + conn);
					}
				}
			}
		} catch (Exception err){
			log.error("[setModerator]",err);
			returnVal = err.toString();
		}
		return returnVal;
	}
	
	
	public HashMap<String,RoomClient> setRoomValues(String userroom, Long room_id, String orgdomain){
		try {

			HashMap <String,RoomClient> roomClientList = new HashMap<String,RoomClient>();
			IConnection current = Red5.getConnectionLocal();
			String streamid = current.getClient().getId();
			RoomClient currentClient = ClientList.get(streamid);
			currentClient.setDomain(orgdomain);
			currentClient.setRoom_id(room_id);
			currentClient.setUserroom(userroom);
			currentClient.setRoomEnter(new Date());
			ClientList.put(streamid, currentClient);
			
			log.error("##### setRoomValues : " + currentClient.getUsername()+" "+currentClient.getStreamid()); // just a unique number

			//Check for Moderation
			//LogicalRoom ENTER
			Set<String> keys = ClientList.keySet();
			Iterator<String> iter = keys.iterator();
			int roomcount = 0;
			while (iter.hasNext()) {
				String key = (String) iter.next();
				RoomClient rcl = ClientList.get(key);
//				log.error("#+#+#+#+##+## logicalRoomEnter ClientList key: "+rcl.getStreamid());
//				log.error("#+#+#+#+##+## logicalRoomEnter ClientList key: "+rcl.getUserroom());
				//Check if the Client is in the same room and same domain 
				//and is not the same like we have just declared to be moderating this room
				if(userroom.equals(rcl.getUserroom()) && orgdomain.equals(rcl.getDomain()) && !streamid.equals(rcl.getStreamid())){
//					log.debug("set to ++ for client: "+rcl.getStreamid()+" "+roomcount);
					roomcount++;
					roomClientList.put(key, rcl);
				}				
			}
			
			if (roomcount==0){
				log.error("Room is empty so set this user to be moderation role");
				currentClient.setIsMod(true);
				ClientList.put(streamid, currentClient);
			} else {
				log.error("Room is already somebody so set this user not to be moderation role");
				currentClient.setIsMod(false);
				ClientList.put(streamid, currentClient);
			}	
			
			return roomClientList;
		} catch (Exception err){
			log.error("[setRoomValues]",err);
		}
		return null;
	}	
	
	/**
	 * this is necessary to switch the Domain while connected <b>before</b> entering a room 
	 * cause users can access public rooms or rooms of their domain
	 * public rooms have always the domain "public" while private/organisation rooms have 
	 * their own ones
	 * @param orgdomain
	 * @deprecated
	 * @return
	 */
	public RoomClient setUserDomain(String orgdomain){
		try {

			log.error("#*#*#*#*#*#*# setUserDomain orgdomain: "+orgdomain);
			
			IConnection current = Red5.getConnectionLocal();
			log.error("current: "+current.getScope().getName());
			
			log.error(current.getClient());
			log.error(current.getClient().getId());
			String streamid = current.getClient().getId();
			RoomClient currentClient = ClientList.get(streamid);
			log.error("[setUsername] id: "+currentClient.getStreamid());
			currentClient.setDomain(orgdomain);

			ClientList.put(streamid, currentClient);
			log.error("##### setUserDomain : " + currentClient.getUsername()+" "+currentClient.getStreamid()); // just a unique number
			
			return currentClient;
		} catch (Exception err){
			log.error("[setUserDomain]",err);
		}
		return null;
	}
	
	/**
	 * this is set initial directly after login/loading language
	 * it will set the domain which is essential for entering a conference-room
	 * @param userId
	 * @param username
	 * @param firstname
	 * @param lastname
	 * @param orgdomain
	 * @return
	 */
	public RoomClient setUsername(String userId, String username, String firstname, String lastname, String orgdomain){
		try {

			log.error("#*#*#*#*#*#*# setUsername userId: "+userId+" username: "+username+" firstname: "+firstname+" lastname: "+lastname);
			
			IConnection current = Red5.getConnectionLocal();
			log.error("current: "+current.getScope().getName());
			
			log.error(current.getClient());
			log.error(current.getClient().getId());
			String streamid = current.getClient().getId();
			RoomClient currentClient = ClientList.get(streamid);
			log.error("[setUsername] id: "+currentClient.getStreamid());
			currentClient.setUsername(username);
			currentClient.setDomain(orgdomain);

			currentClient.setUserObject(userId, username, firstname, lastname);
			ClientList.put(streamid, currentClient);
			log.error("##### setUsername : " + currentClient.getUsername()+" "+currentClient.getStreamid()); // just a unique number
			
			return currentClient;
		} catch (Exception err){
			log.error("[setUsername]",err);
		}
		return null;
	}
	
	/**
	 * this method will be called right at the start of each conference
	 * it will add the user to the list of users of a room
	 * @param userroom
	 * @deprecated
	 * @return
	 */
	public RoomClient setUserroom(String userroom){
		
		RoomClient currentClient = null;
		try {

			log.error("#*#*#*#*#*#*# setUserroom userroom: "+userroom);
			
			IConnection current = Red5.getConnectionLocal();
			log.error("current: "+current.getScope().getName());
			
			log.error(current.getClient());
			log.error(current.getClient().getId());
			String streamid = current.getClient().getId();
			currentClient = ClientList.get(streamid);
			log.error("[setUsername] id: "+currentClient.getStreamid());
			currentClient.setUserroom(userroom);

			ClientList.put(streamid, currentClient);
			log.error("##### setUserroom : " + currentClient.getUsername()+" "+currentClient.getStreamid()); // just a unique number		
		} catch (Exception err){
			log.error("[setUserroom]",err);
		}
		return currentClient;
	}	
	
	public int setAudienceModus(String colorObj, int userPos){
		try {
			IConnection current = Red5.getConnectionLocal();
			
			RoomClient currentClient = ClientList.get(current.getClient().getId());
			log.error("xmlcrm setUserObjectOneFour: "+currentClient.getUsername());
			currentClient.setUsercolor(colorObj);
			currentClient.setUserpos(userPos);
			String roomname = currentClient.getUserroom();
			String orgdomain = currentClient.getDomain();			
			//Notify all clients of the same scope (room)
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				IConnection conn = it.next();
				if (conn.equals(current)){
					continue;
				} else {				
					RoomClient rcl = ClientList.get(conn.getClient().getId());
					//Check if the Client is in the same room and same domain 
					if(roomname.equals(rcl.getUserroom()) && orgdomain.equals(rcl.getDomain())){	
						log.error("setUserObjectOneFour Found Client to " + conn);
						log.error("setUserObjectOneFour Found Client to " + conn.getClient());
						if (conn instanceof IServiceCapableConnection) {
							((IServiceCapableConnection) conn).invoke("setAudienceModusClient",new Object[] { currentClient }, this);
							log.error("sending setUserObjectNewOneFour to " + conn);
						}
					}
				}
			}				
		} catch (Exception err) {
			log.error("[setUserObjectOne2Four]",err);
		}
		return -1;		
	}

	/**
	 * Get all ClientList Objects of that room and domain
	 * @TODO: Check if this Function is deprecated
	 * @return
	 */
	public HashMap<String,RoomClient> getClientListScope(){
		HashMap <String,RoomClient> roomClientList = new HashMap<String,RoomClient>();
		try {
			IConnection current = Red5.getConnectionLocal();
			RoomClient currentClient = ClientList.get(current.getClient().getId());
			log.error("xmlcrm getClientListScope: "+currentClient.getUsername());			
			String roomname = currentClient.getUserroom();
			String orgdomain = currentClient.getDomain();				
			Set<String> keys = ClientList.keySet();
			Iterator<String> iter = keys.iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				log.debug("getClientList key: "+key);
				RoomClient rcl = ClientList.get(key);
				//same room, same domain
				if (rcl.getUserroom().equals(roomname) && rcl.getDomain().equals(orgdomain)) roomClientList.put(key, rcl);
			}
		} catch (Exception err) {
			log.error("[getClientListScope]",err);
		}
		return roomClientList;
	}
	
	/**
	 * Get all ClientList Objects of that room and domain
	 * This Function is needed cause it is invoked internally AFTER the current user has been already removed
	 * from the ClientList to see if the Room is empty again and the PollList can be removed
	 * 
	 * @return
	 */
	public HashMap<String,RoomClient> getClientListByRoomAndDomain(String roomname, String orgdomain){
		HashMap <String,RoomClient> roomClientList = new HashMap<String,RoomClient>();
		try {			
			Set<String> keys = ClientList.keySet();
			Iterator<String> iter = keys.iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				log.debug("getClientList key: "+key);
				RoomClient rcl = ClientList.get(key);
				//same room, same domain
				if (rcl.getUserroom().equals(roomname) && rcl.getDomain().equals(orgdomain)) roomClientList.put(key, rcl);
			}
		} catch (Exception err) {
			log.error("[getClientListBYRoomAndDomain]",err);
		}
		return roomClientList;
	}
	
	public HashMap<String,RoomClient> sendMessageByRoomAndDomain(String roomname, String orgdomain, Object message){
		HashMap <String,RoomClient> roomClientList = new HashMap<String,RoomClient>();
		try {			
			
			IScope scopeHibernate = scope.getScope("hibernate");
			
			if (scopeHibernate!=null){

				//Notify all clients of the same scope (room)
				Iterator<IConnection> it = scope.getScope("hibernate").getConnections();
				
				if (it!=null) {
					while (it.hasNext()) {
						IConnection conn = it.next();				
						RoomClient rcl = ClientList.get(conn.getClient().getId());
						//Check if the Client is in the same room and same domain 
						if(roomname.equals(rcl.getUserroom()) && orgdomain.equals(rcl.getDomain())){					
							if (conn instanceof IServiceCapableConnection) {
								((IServiceCapableConnection) conn).invoke("newMessageByRoomAndDomain",new Object[] { message }, this);
								log.error("sending newMessageByRoomAndDomain to " + conn);
							}
						}
					}
				} else {
					log.error("sendMessageByRoomAndDomain connections is empty ");
				}
			} else {
				log.error("sendMessageByRoomAndDomain servlet not yet started ");
			}
			
		} catch (Exception err) {
			log.error("[getClientListBYRoomAndDomain]",err);
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
			RoomClient currentClient = ClientList.get(current.getClient().getId());
			String roomname = currentClient.getUserroom();
			String orgdomain = currentClient.getDomain();	
			
			log.debug("orgdomain: "+orgdomain);
			log.debug("roomname: "+roomname);	
			
			currentMod = getCurrentModeratorByRoom(roomname,orgdomain);

			log.debug("Who is this moderator"+currentMod);
		} catch (Exception err){
			log.error("[getCurrentModerator]",err);
		}
		return currentMod;
	}
	
	/**
	 * Internal Funciton to get the current Moderator in this room
	 * 
	 * @param roomname
	 * @return
	 */
	private RoomClient getCurrentModeratorByRoom(String roomname, String orgdomain) throws Exception{
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
			if(roomname.equals(rcl.getUserroom()) && orgdomain.equals(rcl.getDomain()) && rcl.getIsMod()){
				log.debug("found client who is the Moderator: "+rcl);
				currentModStreamid = rcl;
			}				
		}
		return currentModStreamid;
	}
	
	public int sendVars(Object vars) {
		log.debug("*..*sendVars: " + vars);
		try {
			// Check if this User is the Mod:
			IConnection current = Red5.getConnectionLocal();
			RoomClient currentClient = ClientList.get(current.getClient().getId());
			String roomname = currentClient.getUserroom();
			String orgdomain = currentClient.getDomain();	
			log.debug("***** id: " + currentClient.getStreamid());
			
			boolean ismod = currentClient.getIsMod();
			
			log.error("*..*ismod: " + ismod);
	
			if (ismod) {
				Iterator<IConnection> it = current.getScope().getConnections();
				while (it.hasNext()) {
					IConnection conn = it.next();
					if (conn instanceof IServiceCapableConnection) {
						RoomClient rcl = ClientList.get(conn.getClient().getId());
						log.debug("*..*idremote: " + rcl.getStreamid());
						log.debug("*..*my idstreamid: " + currentClient.getStreamid());
						if (!currentClient.getStreamid().equals(rcl.getStreamid()) && roomname.equals(rcl.getUserroom()) && orgdomain.equals(rcl.getDomain())) {
							((IServiceCapableConnection) conn).invoke("sendVarsToWhiteboard", new Object[] { vars },this);
							log.debug("sending sendVarsToWhiteboard to " + conn);
						}
						if (rcl.getIsRecording()){
							StreamService.addWhiteBoardEvent(rcl.getRoomRecordingName(),vars);
						}						
					}
				}				
				return 1;
			} else {
				// log.debug("*..*you are not allowed to send: "+ismod);
				return -1;
			}
		} catch (Exception err) {
			log.error("[sendVars]",err);
		}
		return -1;
	}

	public int sendVarsModeratorGeneral(Object vars) {
		log.debug("*..*sendVars: " + vars);
		try {
			IConnection current = Red5.getConnectionLocal();
			RoomClient currentClient = ClientList.get(current.getClient().getId());
			String roomname = currentClient.getUserroom();
			String orgdomain = currentClient.getDomain();	
			log.debug("***** id: " + currentClient.getStreamid());
			
			boolean ismod = currentClient.getIsMod();
	
			if (ismod) {
				log.debug("CurrentScope :"+current.getScope().getName());
				Iterator<IConnection> it = current.getScope().getConnections();
				while (it.hasNext()) {
					IConnection conn = it.next();
					if (conn instanceof IServiceCapableConnection) {
						RoomClient rcl = ClientList.get(conn.getClient().getId());
						log.debug("*..*idremote: " + rcl.getStreamid());
						log.debug("*..*my idstreamid: " + currentClient.getStreamid());
						if (!currentClient.getStreamid().equals(rcl.getStreamid()) && roomname.equals(rcl.getUserroom()) && orgdomain.equals(rcl.getDomain())) {
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
		} catch (Exception err) {
			log.error("[sendVarsModeratorGeneral]",err);
		}
		return -1;
	}

	public int sendMessage(Object newMessage) {
		try {
			IConnection current = Red5.getConnectionLocal();
			RoomClient currentClient = ClientList.get(current.getClient().getId());
			String roomname = currentClient.getUserroom();
			String orgdomain = currentClient.getDomain();	
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				IConnection conn = it.next();
				if (conn instanceof IServiceCapableConnection) {
					RoomClient rcl = ClientList.get(conn.getClient().getId());
					log.debug("*..*idremote: " + rcl.getStreamid());
					log.debug("*..*my idstreamid: " + currentClient.getStreamid());
					if (roomname.equals(rcl.getUserroom()) && orgdomain.equals(rcl.getDomain())) {
						((IServiceCapableConnection) conn).invoke("sendVarsToMessage",new Object[] { newMessage }, this);
						log.debug("sending sendVarsToMessage to " + conn);		
					}
				}
			}
		} catch (Exception err) {
			log.error("[sendMessage]",err);
		}
		return 1;
	}


	public int sendMessageWithClient(Object newMessage) {
		try {
			IConnection current = Red5.getConnectionLocal();
			RoomClient currentClient = ClientList.get(current.getClient().getId());
			String roomname = currentClient.getUserroom();
			String orgdomain = currentClient.getDomain();	
			
			HashMap<String,Object> hsm = new HashMap<String,Object>();
			hsm.put("client", currentClient);
			hsm.put("message", newMessage);
			
			//broadcast to everybody in the room/domain
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				IConnection conn = it.next();
				if (conn instanceof IServiceCapableConnection) {
					RoomClient rcl = ClientList.get(conn.getClient().getId());
					log.debug("*..*idremote: " + rcl.getStreamid());
					log.debug("*..*my idstreamid: " + currentClient.getStreamid());
					if (roomname.equals(rcl.getUserroom()) && orgdomain.equals(rcl.getDomain())) {
						((IServiceCapableConnection) conn).invoke("sendVarsToMessageWithClient",new Object[] { hsm }, this);
						log.debug("sending sendVarsToMessageWithClient to " + conn);
					}
				}
			}
		} catch (Exception err) {
			log.error("[sendMessageWithClient] ",err);
			return -1;
		}
		return 1;
	}

	public int sendMessageWithClientById(Object newMessage, String clientId) {
		try {
			IConnection current = Red5.getConnectionLocal();
			RoomClient currentClient = ClientList.get(current.getClient().getId());
			
			log.error("### sendMessageWithClientById ###"+clientId);
			
			HashMap<String,Object> hsm = new HashMap<String,Object>();
			hsm.put("client", currentClient);
			hsm.put("message", newMessage);
			
			//broadcast Message to specific user with id
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				IConnection conn = it.next();
				if (conn instanceof IServiceCapableConnection) {
					log.error("### sendMessageWithClientById 1 ###"+clientId);
					log.error("### sendMessageWithClientById 2 ###"+conn.getClient().getId());
					if (conn.getClient().getId().equals(clientId)){
						((IServiceCapableConnection) conn).invoke("sendVarsToMessageWithClient",new Object[] { hsm }, this);
						log.error("sendingsendVarsToMessageWithClient ByID to " + conn);
					}
				}
			}
		} catch (Exception err) {
			log.error("[sendMessageWithClient] ",err);
			return -1;
		}		
		return 1;
	}
	

	public void sendMessageWithClientByUserId(Object message, String userId) {
		try {
			
			IScope scopeHibernate = scope.getScope("hibernate");
			
			if (scopeHibernate!=null){
				//Notify the clients of the same scope (room) with user_id
				Iterator<IConnection> it = scope.getScope("hibernate").getConnections();
				if (it!=null) {
					while (it.hasNext()) {
						IConnection conn = it.next();				
						RoomClient rcl = ClientList.get(conn.getClient().getId());
						if (rcl.getUser_id().equals(userId)){
							((IServiceCapableConnection) conn).invoke("newMessageByRoomAndDomain",new Object[] { message }, this);
						}
					}
				}
			} else {
				//Scope not yet started
			}
		} catch (Exception err) {
			log.error("[sendMessageWithClient] ",err);
			err.printStackTrace();
		}		
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
	 * current room and domain
	 * 
	 * @return iterator of broadcast stream names
	 */
	public List<String> getStreams() {
		List <String> roomClientListScopeNames = new LinkedList<String>();
		try {
			//Get ClientObjects of all Clients in this Room
			IConnection current = Red5.getConnectionLocal();
			RoomClient currentClient = ClientList.get(current.getClient().getId());
			String roomname = currentClient.getUserroom();
			String orgdomain = currentClient.getDomain();
			String streamid = currentClient.getStreamid();
			
			log.error("getStreams roomname: "+roomname);
			Set<String> keys = ClientList.keySet();
			Iterator<String> iter = keys.iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				log.error("getStreams key: "+key);
				RoomClient rcl = ClientList.get(key);
				if (rcl.getUserroom().equals(roomname) && rcl.getDomain().equals(orgdomain) && !rcl.getStreamid().equals(streamid)) roomClientListScopeNames.add(rcl.getStreamid());
			}
		} catch (Exception err) {
			log.error("[getStreams]",err);
		}
		return roomClientListScopeNames;
		//return getBroadcastStreamNames(conn.getScope());
	}
	
	/**
	 * This Method will return all streams of the parent stream (which should be all including
	 * the child-scope streams independend from room/domain
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
		log.error("Received result " + call.getResult() + " for "+ call.getServiceMethodName());
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
	
	public void disconnectUser(RoomClient rcl){
		try {
			ClientList.remove(rcl.getStreamid());
			
		} catch (Exception err) {
			log.error("[disconnectUser]",err);
		}
	}
	
}