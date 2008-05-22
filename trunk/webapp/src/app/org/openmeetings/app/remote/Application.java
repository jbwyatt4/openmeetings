package org.openmeetings.app.remote;

 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
import org.red5.server.api.service.IPendingServiceCall;
import org.red5.server.api.service.IPendingServiceCallback;
import org.red5.server.api.service.IServiceCapableConnection;
import org.red5.server.api.stream.IBroadcastStream;
import org.red5.server.api.stream.IPlayItem;
import org.red5.server.api.stream.IPlaylistSubscriberStream;
import org.red5.server.api.stream.IStreamAwareScopeHandler;
import org.red5.server.api.stream.ISubscriberStream;
import org.openmeetings.app.conference.configutils.BandwidthConfigFactory;
import org.openmeetings.app.conference.configutils.UserConfigFactory;
import org.openmeetings.app.quartz.scheduler.QuartzSessionClear;
import org.openmeetings.utils.crypt.ManageCryptStyle;
import org.openmeetings.utils.stringhandlers.ChatString;
import org.openmeetings.app.conference.videobeans.RoomClient;
import org.openmeetings.app.data.user.Usermanagement;
import org.openmeetings.app.hibernate.beans.user.Users;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * 
 * @author swagner
 * 
 */

public class Application extends ApplicationAdapter implements
		IPendingServiceCallback, IStreamAwareScopeHandler {

	private static final Log log = LogFactory.getLog(Application.class);

	private static HashMap<String,RoomClient> ClientList = new HashMap<String,RoomClient>();
	
	/*
	 * EMoticons FileList
	 */
	public static LinkedList<LinkedList<String>> emotfilesList = new LinkedList<LinkedList<String>>();
	
	private static BandwidthConfigFactory bwFactory = null;
	private static UserConfigFactory userFactory = null;
	
	//This is the Folder where all executables are written
	//TODO:fix hardcoded name of webapp
	public static String batchFileFir = "webapps"+File.separatorChar
									+"openmeetings"+File.separatorChar
									+"jod" + File.separatorChar;
	public static String lineSeperator = System.getProperty("line.separator");
	
	//The Global WebApp Path
	public static String webAppPath = "";
	public static String configDirName = "conf";
	
	private static long broadCastCounter = 0;
	
	private static Application instance = null;
	
	public static synchronized Application getInstance(){
		return instance;
	}

	@Override
	public boolean appStart(IScope scope) {
		try {
			webAppPath = scope.getResource("/").getFile().getAbsolutePath();
			log.debug("webAppPath : "+webAppPath);
			//batchFileFir = webAppPath + File.separatorChar + "jod" + File.separatorChar;
			scope.getResource("public/").getFile().getParentFile().getAbsolutePath();
			String filePath = scope.getResource("public/").getFile().getAbsolutePath();
			this.loadEmot(filePath);
			instance = this;
			// init your handler here
			//System.out.println("################## appStart    ");
			QuartzSessionClear bwHelp = new QuartzSessionClear();
			String jobName = addScheduledJob(300000,bwHelp);
			log.debug("jobName: "+jobName);
		} catch (Exception err) {
			log.error("[appStart]",err);
		}
		return true;
	}
	
	public void loadEmot(String filePath){
		try {
			String fileName = filePath + File.separatorChar + "emoticons" + File.separatorChar + "emotes.xml";
			XStream xStream = new XStream(new XppDriver());
			xStream.setMode(XStream.NO_REFERENCES);
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
		    String xmlString = "";
		    while (reader.ready()) {
		    	xmlString += reader.readLine();
		    }
		    emotfilesList = (LinkedList<LinkedList<String>>) xStream.fromXML(xmlString);
		    ChatString.getInstance().replaceAllRegExp();
		    log.debug("loadEmot completed");
		} catch (Exception err) {
			log.error("[loadEmot]",err);
		}
	}	
	

	@Override
	public boolean roomConnect(IConnection conn, Object[] params) {
		log.debug("roomConnect    : " + conn.getHost() + " "+ conn.getClient() + " " + conn.getClient().getId());
		return true;
	}

	@Override
	public void roomDisconnect(IConnection conn) {
		log.debug("roomDisconnect: " + conn.getHost() + " "+ conn.getClient() + " " + conn.getClient().getId());
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
			log.debug("Client connected xmlcrmred5 jar " + client.getId() + " conn "+ client);
			log.debug("Setting stream xmlcrmred5 xmlcrmred5 id: " + getClients().size()); // just a unique number
			service.invoke("setId", new Object[] { client.getId() },this);
	
			//Store the Connection into a bean and add it to the HashMap
			RoomClient rcm = new RoomClient();
			rcm.setConnectedSince(new Date());
			rcm.setStreamid(client.getId());
			long thistime = new Date().getTime();
			rcm.setPublicSID(ManageCryptStyle.getInstance().getInstanceOfCrypt().createPassPhrase(String.valueOf(thistime).toString()));
			
			rcm.setUserport(conn.getRemotePort());
			rcm.setUserip(conn.getRemoteAddress());
			rcm.setSwfurl(conn.getConnectParams().get("swfUrl").toString());			
			log.debug("##### : " + rcm.getStreamid()); // just a unique number
			
			//Set the moderation for the CLient on startup
			log.debug("Current clients in this room: "+conn.getScope().getClients().size());		
			
			log.debug("This client is not the moderator"+rcm.getStreamid());
			rcm.setIsMod(new Boolean(false));
			
			ClientList.put(rcm.getStreamid(),rcm);

		} catch (Exception err){
			log.error("roomJoin",err);
		}		
		return true;
	}
	
	/**
	 * this function is invoked directly after initial connecting
	 * @return
	 */
	public String getPublicSID() {
		IConnection current = Red5.getConnectionLocal();
		RoomClient currentClient = ClientList.get(current.getClient().getId());	
		return currentClient.getPublicSID();
	}
	
	/**
	 * this function is invoked after a reconnect
	 * @param newPublicSID
	 */
	public void overwritePublicSID(String newPublicSID) {
		IConnection current = Red5.getConnectionLocal();
		RoomClient currentClient = ClientList.get(current.getClient().getId());	
		currentClient.setPublicSID(newPublicSID);
		ClientList.put(current.getClient().getId(), currentClient);
	}
	
	/**
	 * @deprecated
	 * @return
	 */
	public RoomClient logicalRoomEnter(){
		try {
			IConnection current = Red5.getConnectionLocal();
			RoomClient currentClient = ClientList.get(current.getClient().getId());	
			Long room_id = currentClient.getRoom_id();			
			String streamid = currentClient.getStreamid();
			
			
			//check if room has no Moderation yet
			//get all Clients of room
			Set<String> keys = ClientList.keySet();
			Iterator<String> iter = keys.iterator();
			int roomcount = 0;
			while (iter.hasNext()) {
				String key = (String) iter.next();
				RoomClient rcl = ClientList.get(key);
				
				log.debug("#+#+#+#+##+## logicalRoomEnter ClientList key: "+rcl.getStreamid());
				log.debug("#+#+#+#+##+## logicalRoomEnter ClientList key: "+rcl.getRoom_id());
				
				//Check if the Client is in the same room and same domain 
				//and is not the same like we have just declared to be moderating this room
				if(room_id == rcl.getRoom_id() && room_id!=null && !streamid.equals(rcl.getStreamid())){
					log.debug("set to ++ for client: "+rcl.getStreamid()+" "+roomcount);
					roomcount++;
				}				
			}
			
			if (roomcount==0){
				log.debug("Room is empty so set this user to be moderation role");
				currentClient.setIsMod(true);
				ClientList.put(streamid, currentClient);
				return currentClient;
			} else {
				log.debug("Room is already somebody so set this user not to be moderation role");
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
		log.debug("roomLeave " + client.getId() + " "+ room.getClients().size() + " " + room.getContextPath() + " "+ room.getName());
		try {
			IConnection current = Red5.getConnectionLocal();
			RoomClient currentClient = ClientList.get(current.getClient().getId());
			Long room_id = currentClient.getRoom_id();

			//String streamid = currentClient.getStreamid();
			
			log.debug("##### roomLeave :. " + currentClient.getStreamid()); // just a unique number


			log.debug("removing USername "+currentClient.getUsername()+" "+currentClient.getConnectedSince()+" streamid: "+currentClient.getStreamid());
			
			//stop and save any recordings
			if (currentClient.getIsRecording()) {
				log.debug("*** roomLeave Current Client is Recording - stop that");
				StreamService.stopRecordAndSave(current, currentClient.getRoomRecordingName(), currentClient);
				
				//set to true and overwrite the default one cause otherwise no notification is send
				currentClient.setIsRecording(true);
			}

			this.disconnectUser(currentClient);
			//If this Room is empty clear the Room Poll List
			HashMap<String,RoomClient> rcpList = this.getClientListByRoomAndDomain(room_id);
			log.debug("roomLeave rcpList size: "+rcpList.size());
			if (rcpList.size()==0){
				PollService.clearRoomPollList(room_id);
//				log.debug("clearRoomPollList cleared");
			}
			
			//Notify all clients of the same scope (room) with domain and room
			//except the current disconnected cause it could throw an exception
			
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				log.debug("hasNext == true");
				IConnection cons = it.next();
				log.debug("cons Host: "+cons);
				if (cons instanceof IServiceCapableConnection) {
					if (!cons.equals(current)){
						log.debug("sending roomDisconnect to " + cons);
						RoomClient rcl = ClientList.get(cons.getClient().getId());
						//Send to all connected users	
						((IServiceCapableConnection) cons).invoke("roomDisconnect",new Object[] { currentClient }, this);
						log.debug("sending roomDisconnect to " + cons);
						//only to the members of the current room
						if(room_id == rcl.getRoom_id() && room_id!=null){			
							//add Notification if another user is recording
							log.debug("###########[roomLeave]");
							if (rcl.getIsRecording()){
								log.debug("*** roomLeave Any Client is Recording - stop that");
								StreamService.addRoomClientEnterEventFunc(rcl, rcl.getRoomRecordingName(), rcl.getUserip(), false);
								StreamService.stopRecordingShowForClient(cons, currentClient, rcl.getRoomRecordingName(), false);
							}
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
		log.debug("logicalRoomLeave ");
		try {
			IConnection current = Red5.getConnectionLocal();
			RoomClient currentClient = ClientList.get(current.getClient().getId());
			Long room_id = currentClient.getRoom_id();	
			
			//stop and save any recordings if this user is recording
			if (currentClient.getIsRecording()) {
				StreamService.stopRecordAndSave(current, currentClient.getRoomRecordingName(), currentClient);
				
				//set to true and overwrite the default one cause otherwise no notification is send
				currentClient.setIsRecording(true);
			}			
			
			log.debug("##### logicalRoomLeave :. " + currentClient.getStreamid()); // just a unique number
			
			//If this Room is empty clear the Room Poll List
			HashMap<String,RoomClient> rcpList = this.getClientListByRoomAndDomain(room_id);
			log.debug("logicalRoomLeave rcpList size: "+rcpList.size());
			if (rcpList.size()==0){
				PollService.clearRoomPollList(room_id);
				log.debug("logicalRoomLeave clearRoomPollList cleared");
			}
			
			//Notify all clients of the same scope (room) with domain and room
			
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				IConnection cons = it.next();
				//log.debug("cons Host: "+cons);
				if (cons instanceof IServiceCapableConnection) {
					if (!cons.equals(current)){
						//log.debug("sending roomDisconnect to " + cons);
						RoomClient rcl = ClientList.get(cons.getClient().getId());
						//Check if the Client is in the same room and same domain except its the current one
						if(room_id == rcl.getRoom_id() && room_id!=null){					
							((IServiceCapableConnection) cons).invoke("logicalRoomLeaveDis",new Object[] { currentClient }, this);
							log.debug("sending roomDisconnect to " + cons);
							
							//add Notification if another user is recording in this room
							if (rcl.getIsRecording()){
								log.debug("*** logicalRoomLeave Any Client is Recording - stop that");
								StreamService.addRoomClientEnterEventFunc(rcl, rcl.getRoomRecordingName(), rcl.getUserip(), false);
								StreamService.stopRecordingShowForClient(cons, currentClient, rcl.getRoomRecordingName(), true);
							}
						}
						
					}
				}
			}	
			currentClient.setRoom_id(null);			
			currentClient.setIsRecording(false);
			
			log.debug("removing USername "+currentClient.getUsername()+" "+currentClient.getConnectedSince()+" streamid: "+currentClient.getStreamid());
			ClientList.put(currentClient.getStreamid(),currentClient);
			
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
		Long room_id = currentClient.getRoom_id();	
					
		// Notify all the clients that the stream had been started
		log.debug("start streamPublishStart broadcast start: "+ stream.getPublishedName() + "CONN " + current);
		
		Iterator<IConnection> it = current.getScope().getConnections();
		while (it.hasNext()) {
			IConnection conn = it.next();
			if (conn.equals(current)){
				RoomClient rcl = ClientList.get(conn.getClient().getId());
				if (rcl.getIsRecording()){
					StreamService.addRecordingByStreamId(current, streamid, currentClient, rcl.getRoomRecordingName());
				}
				continue;
			} else {
				if (conn instanceof IServiceCapableConnection) {
					RoomClient rcl = ClientList.get(conn.getClient().getId());
					//log.debug("is this users still alive? :"+rcl);
					//Check if the Client is in the same room and same domain 
					if(room_id == rcl.getRoom_id() && room_id!=null){
						IServiceCapableConnection iStream = (IServiceCapableConnection) conn;
//							log.info("IServiceCapableConnection ID " + iStream.getClient().getId());
						iStream.invoke("newStream",new Object[] { currentClient }, this);
						if (rcl.getIsRecording()){
							StreamService.addRecordingByStreamId(current, streamid, currentClient, rcl.getRoomRecordingName());
						}
					}
				}
			}
		}		
	}

	
	/**
	 * This method handles the Event after a stream has been removed all connected
	 * Clients in the same room will get a notification
	 * 
	 * @return void
	 * 
	 */
	public void streamBroadcastClose(IBroadcastStream stream) {

		// Notify all the clients that the stream had been started
		log.debug("start streamBroadcastClose broadcast close: "+ stream.getPublishedName());
		try {
			RoomClient rcl = ClientList.get(Red5.getConnectionLocal().getClient().getId());
			
			sendClientBroadcastNotifications(stream,"closeStream",rcl);
		} catch (Exception e){
			log.error("[streamBroadcastClose]",e);
		}
	}
	
	/**
	 * This method handles the notification room-based
	 * 
	 * @return void
	 * 
	 */	
	private void sendClientBroadcastNotifications(IBroadcastStream stream,String clientFunction, RoomClient rc){
		try {

			// Store the local so that we do not send notification to ourself back
			IConnection current = Red5.getConnectionLocal();
			String streamid = current.getClient().getId();
			RoomClient currentClient = ClientList.get(streamid);
			Long room_id = currentClient.getRoom_id();	
				
			
			// Notify all the clients that the stream had been started
			log.debug("sendClientBroadcastNotifications: "+ stream.getPublishedName());
			log.debug("sendClientBroadcastNotifications : "+ currentClient+ " " + currentClient.getStreamid());
			
//			Notify all clients of the same scope (room)
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				IConnection conn = it.next();
				if (conn.equals(current)){
					//there is a Bug in the current implementation of the appDisconnect
					if (clientFunction.equals("closeStream")){
						RoomClient rcl = ClientList.get(conn.getClient().getId());
						if (clientFunction.equals("closeStream") && rcl.getIsRecording()){
							log.debug("*** sendClientBroadcastNotifications Any Client is Recording - stop that");
							StreamService.stopRecordingShowForClient(conn, currentClient, rcl.getRoomRecordingName(), false);
						}
						// Don't notify current client
						current.ping();
					}
					continue;
				} else {
					if (conn instanceof IServiceCapableConnection) {
						RoomClient rcl = ClientList.get(conn.getClient().getId());
						log.debug("is this users still alive? :"+rcl);
						//Check if the Client is in the same room and same domain 
						if(room_id == rcl.getRoom_id() && room_id!=null){
							//conn.ping();
							IServiceCapableConnection iStream = (IServiceCapableConnection) conn;
	//							log.info("IServiceCapableConnection ID " + iStream.getClient().getId());
							iStream.invoke(clientFunction,new Object[] { rc }, this);
							log.debug("sending notification to " + conn+" ID: ");

							//if this close stream event then stop the recording of this stream
							if (clientFunction.equals("closeStream") && rcl.getIsRecording()){
								log.debug("*** sendClientBroadcastNotifications Any Client is Recording - stop that");
								StreamService.stopRecordingShowForClient(conn, currentClient, rcl.getRoomRecordingName(), false);
							}
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
			log.debug("*..*setModerator id: " + id);
			
			IConnection current = Red5.getConnectionLocal();
			String streamid = current.getClient().getId();
			
			RoomClient rlc = ClientList.get(id);
			Long room_id = rlc.getRoom_id();
			
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
				if(room_id == rcl.getRoom_id() && room_id!=null && !id.equals(rcl.getStreamid())){
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
				if(room_id == rcl.getRoom_id() && room_id!=null){
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
	
	/**
	 * there will be set an attribute called "broadCastCounter"
	 * this is the name this user will publish his stream
	 * @return long broadCastId
	 */
	public long getBroadCastId(){
		try {
			IConnection current = Red5.getConnectionLocal();
			String streamid = current.getClient().getId();
			RoomClient currentClient = ClientList.get(streamid);
			currentClient.setBroadCastID(broadCastCounter++);				
			ClientList.put(streamid, currentClient);
			return currentClient.getBroadCastID();
		} catch (Exception err){
			log.error("[getBroadCastId]",err);
		}
		return -1;
	}
	
	/**
	 * this must be set _after_ the Video/Audio-Settings have been chosen (see editrecordstream.lzx)
	 * but _before_ anything else happens, it cannot be applied _after_ the stream has started!
	 * avsettings can be:
	 * av - video and audio
	 * a - audio only
	 * v - video only
	 * n - no a/v only static image
	 * furthermore 
	 * @param avsetting
	 * @param newMessage
	 * @return
	 */
	public RoomClient setUserAVSettings(String avsettings, Object newMessage){
		try {

			IConnection current = Red5.getConnectionLocal();
			String streamid = current.getClient().getId();
			RoomClient currentClient = ClientList.get(streamid);
			currentClient.setAvsettings(avsettings);
			Long room_id = currentClient.getRoom_id();					
			ClientList.put(streamid, currentClient);
			
			HashMap<String,Object> hsm = new HashMap<String,Object>();
			hsm.put("client", currentClient);
			hsm.put("message", newMessage);			
			
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				IConnection conn = it.next();				
				RoomClient rcl = ClientList.get(conn.getClient().getId());
				//Check if the Client is in the same room and same domain 
				if(room_id == rcl.getRoom_id()){	
					log.debug("setUserObjectOneFour Found Client to " + conn);
					log.debug("setUserObjectOneFour Found Client to " + conn.getClient());
					if (conn instanceof IServiceCapableConnection) {
						((IServiceCapableConnection) conn).invoke("sendVarsToMessageWithClient",new Object[] { hsm }, this);
						log.debug("sending setUserObjectNewOneFour to " + conn);
						
						//if somebody is recording in this room add the event
						if (rcl.getIsRecording()) {
							StreamService.addRoomClientAVSetEvent(currentClient, rcl.getRoomRecordingName(), conn.getRemoteAddress());
						}
					}
				}
			}
			
			return currentClient;
		} catch (Exception err){
			log.error("[setUserAVSettings]",err);
		}
		return null;
	}	
	 
	public HashMap<String,RoomClient> setRoomValues(Long room_id){
		try {

			HashMap <String,RoomClient> roomClientList = new HashMap<String,RoomClient>();
			IConnection current = Red5.getConnectionLocal();
			String streamid = current.getClient().getId();
			RoomClient currentClient = ClientList.get(streamid);
			currentClient.setRoom_id(room_id);
			currentClient.setRoomEnter(new Date());
			ClientList.put(streamid, currentClient);
			
			log.debug("##### setRoomValues : " + currentClient.getUsername()+" "+currentClient.getStreamid()); // just a unique number

			//Check for Moderation
			//LogicalRoom ENTER
			Set<String> keys = ClientList.keySet();
			Iterator<String> iter = keys.iterator();
			int roomcount = 0;
			while (iter.hasNext()) {
				String key = (String) iter.next();
				RoomClient rcl = ClientList.get(key);
				log.debug("#+#+#+#+##+## logicalRoomEnter ClientList key: "+rcl.getRoom_id()+" "+room_id);
				//Check if the Client is in the same room and same domain 
				//and is not the same like we have just declared to be moderating this room
				if(room_id==rcl.getRoom_id() && room_id!=null && !streamid.equals(rcl.getStreamid())){
					log.debug("set to ++ for client: "+rcl.getStreamid()+" "+roomcount);
					roomcount++;
					roomClientList.put(key, rcl);
				}				
			}
			
			if (roomcount==0){
				log.debug("Room is empty so set this user to be moderation role");
				currentClient.setIsMod(true);
				ClientList.put(streamid, currentClient);
			} else {
				log.debug("Room is already somebody so set this user not to be moderation role");
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

			log.debug("#*#*#*#*#*#*# setUserDomain orgdomain: "+orgdomain);
			
			IConnection current = Red5.getConnectionLocal();
			log.debug("current: "+current.getScope().getName());
			
			log.debug(current.getClient());
			log.debug(current.getClient().getId());
			String streamid = current.getClient().getId();
			RoomClient currentClient = ClientList.get(streamid);
			log.debug("[setUsername] id: "+currentClient.getStreamid());

			ClientList.put(streamid, currentClient);
			log.debug("##### setUserDomain : " + currentClient.getUsername()+" "+currentClient.getStreamid()); // just a unique number
			
			return currentClient;
		} catch (Exception err){
			log.error("[setUserDomain]",err);
		}
		return null;
	}
	
	/**
	 * this is set initial directly after login/loading language
	 * @param userId
	 * @param username
	 * @param firstname
	 * @param lastname
	 * @param orgdomain
	 * @return
	 */
	public RoomClient setUsername(Long userId, String username, String firstname, String lastname){
		try {
			//log.debug("#*#*#*#*#*#*# setUsername userId: "+userId+" username: "+username+" firstname: "+firstname+" lastname: "+lastname);
			IConnection current = Red5.getConnectionLocal();			
			String streamid = current.getClient().getId();
			RoomClient currentClient = ClientList.get(streamid);
			//log.debug("[setUsername] id: "+currentClient.getStreamid());
			currentClient.setUsername(username);
			currentClient.setUserObject(userId, username, firstname, lastname);
			
			//only fill this value from User-REcord
			//cause invited users have non
			//you cannot set the firstname,lastname from the UserRecord
			Users us = Usermanagement.getInstance().getUser(userId);
			if (us!=null && us.getPictureuri()!=null){
				//set Picture-URI
				System.out.println("###### SET PICTURE URI");
				currentClient.setPicture_uri(us.getPictureuri());
			}
			ClientList.put(streamid, currentClient);
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

			log.debug("#*#*#*#*#*#*# setUserroom userroom: "+userroom);
			
			IConnection current = Red5.getConnectionLocal();
			log.debug("current: "+current.getScope().getName());
			
			log.debug(current.getClient());
			log.debug(current.getClient().getId());
			String streamid = current.getClient().getId();
			currentClient = ClientList.get(streamid);
			log.debug("[setUsername] id: "+currentClient.getStreamid());

			ClientList.put(streamid, currentClient);
			log.debug("##### setUserroom : " + currentClient.getUsername()+" "+currentClient.getStreamid()); // just a unique number		
		} catch (Exception err){
			log.error("[setUserroom]",err);
		}
		return currentClient;
	}	
	
	public int setAudienceModus(String colorObj, int userPos){
		try {
			IConnection current = Red5.getConnectionLocal();
			
			RoomClient currentClient = ClientList.get(current.getClient().getId());
			log.debug("xmlcrm setUserObjectOneFour: "+currentClient.getUsername());
			currentClient.setUsercolor(colorObj);
			currentClient.setUserpos(userPos);
			Long room_id = currentClient.getRoom_id();	
						
			//Notify all clients of the same scope (room)
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				IConnection conn = it.next();
				if (conn.equals(current)){
					continue;
				} else {				
					RoomClient rcl = ClientList.get(conn.getClient().getId());
					//Check if the Client is in the same room and same domain 
					if(room_id == rcl.getRoom_id() && room_id!=null){	
						log.debug("*** setAudienceModus Found Client to " + conn);
						log.debug("*** setAudienceModus Found Client to " + conn.getClient());
						if (conn instanceof IServiceCapableConnection) {
							((IServiceCapableConnection) conn).invoke("setAudienceModusClient",new Object[] { currentClient }, this);
							log.debug("sending setAudienceModusClient to " + conn);
							//if any user in this room is recording add this client to the list
							if (rcl.getIsRecording()) {
								log.debug("currentClient "+currentClient.getPublicSID());
								StreamService.addRoomClientEnterEventFunc(currentClient, rcl.getRoomRecordingName(), currentClient.getUserip(), true);
							}							
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
			log.debug("xmlcrm getClientListScope: "+currentClient.getUsername());			
			Long room_id = currentClient.getRoom_id();	
							
			Set<String> keys = ClientList.keySet();
			Iterator<String> iter = keys.iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				log.debug("getClientList key: "+key);
				RoomClient rcl = ClientList.get(key);
				//same room, same domain
				if (rcl.getRoom_id()==room_id && rcl.getRoom_id()!=null) roomClientList.put(key, rcl);
			}
		} catch (Exception err) {
			log.debug("[getClientListScope]",err);
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
	public HashMap<String,RoomClient> getClientListByRoomAndDomain(Long room_id){
		HashMap <String,RoomClient> roomClientList = new HashMap<String,RoomClient>();
		try {			
			Set<String> keys = ClientList.keySet();
			Iterator<String> iter = keys.iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				log.debug("getClientList key: "+key);
				RoomClient rcl = ClientList.get(key);
				//same room, same domain
				if (room_id == rcl.getRoom_id() && room_id != null) roomClientList.put(key, rcl);
			}
		} catch (Exception err) {
			log.error("[getClientListBYRoomAndDomain]",err);
		}
		return roomClientList;
	}
	
	/**
	 * TODO: Check if needed and remove
	 * @deprecated
	 * @param room_id
	 * @param message
	 * @return
	 */
	public HashMap<String,RoomClient> sendMessageByRoomAndDomain(Long room_id, Object message){
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
						if(room_id == rcl.getRoom_id() && room_id!=null){					
							if (conn instanceof IServiceCapableConnection) {
								((IServiceCapableConnection) conn).invoke("newMessageByRoomAndDomain",new Object[] { message }, this);
								log.debug("sending newMessageByRoomAndDomain to " + conn);
							}
						}
					}
				} else {
					log.error("sendMessageByRoomAndDomain connections is empty ");
				}
			} else {
				log.debug("sendMessageByRoomAndDomain servlet not yet started ");
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
			Long room_id = currentClient.getRoom_id();		
			
			currentMod = getCurrentModeratorByRoom(room_id);

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
	private RoomClient getCurrentModeratorByRoom(Long room_id) throws Exception{
		RoomClient currentModStreamid = null;
		Set<String> keys = ClientList.keySet();
		Iterator<String> iter = keys.iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			RoomClient rcl = ClientList.get(key);
//			
			log.debug("*..*unsetModerator ClientList key: "+rcl.getStreamid());
//			
			//Check if the Client is in the same room
			if(room_id == rcl.getRoom_id() && room_id!=null && rcl.getIsMod()){
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
			Long room_id = currentClient.getRoom_id();	
				
			log.debug("***** id: " + currentClient.getStreamid());
			
			boolean ismod = currentClient.getIsMod();
			
			log.debug("*..*ismod: " + ismod);
	
			if (ismod) {
				Iterator<IConnection> it = current.getScope().getConnections();
				while (it.hasNext()) {
					IConnection conn = it.next();
					if (conn instanceof IServiceCapableConnection) {
						RoomClient rcl = ClientList.get(conn.getClient().getId());
						log.debug("*..*idremote: " + rcl.getStreamid());
						log.debug("*..*my idstreamid: " + currentClient.getStreamid());
						if (room_id == rcl.getRoom_id() && room_id!=null) {
							if (!currentClient.getStreamid().equals(rcl.getStreamid())) {
								((IServiceCapableConnection) conn).invoke("sendVarsToWhiteboard", new Object[] { vars },this);
							}
							log.debug("sending sendVarsToWhiteboard to " + conn);
							if (rcl.getIsRecording()){
								StreamService.addWhiteBoardEvent(rcl.getRoomRecordingName(),vars);
							}								
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
			Long room_id = currentClient.getRoom_id();	
				
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
						if (!currentClient.getStreamid().equals(rcl.getStreamid()) && room_id == rcl.getRoom_id() && room_id!=null) {
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
			Long room_id = currentClient.getRoom_id();	
				
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				IConnection conn = it.next();
				if (conn instanceof IServiceCapableConnection) {
					RoomClient rcl = ClientList.get(conn.getClient().getId());
					log.debug("*..*idremote: " + rcl.getStreamid());
					log.debug("*..*my idstreamid: " + currentClient.getStreamid());
					if (room_id == rcl.getRoom_id() && room_id!=null) {
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
			Long room_id = currentClient.getRoom_id();	
				
			
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
					if (room_id == rcl.getRoom_id() && room_id!=null) {
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
			
			log.debug("### sendMessageWithClientById ###"+clientId);
			
			HashMap<String,Object> hsm = new HashMap<String,Object>();
			hsm.put("client", currentClient);
			hsm.put("message", newMessage);
			
			//broadcast Message to specific user with id
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				IConnection conn = it.next();
				if (conn instanceof IServiceCapableConnection) {
					log.debug("### sendMessageWithClientById 1 ###"+clientId);
					log.debug("### sendMessageWithClientById 2 ###"+conn.getClient().getId());
					if (conn.getClient().getId().equals(clientId)){
						((IServiceCapableConnection) conn).invoke("sendVarsToMessageWithClient",new Object[] { hsm }, this);
						log.debug("sendingsendVarsToMessageWithClient ByID to " + conn);
					}
				}
			}
		} catch (Exception err) {
			log.error("[sendMessageWithClient] ",err);
			return -1;
		}		
		return 1;
	}
	
	/**
	 * update the Session Object after changing the user-record
	 * @param users_id
	 */
	public void updateUserSessionObject(Long users_id, String pictureuri){
		try {			
			Users us = Usermanagement.getInstance().getUser(users_id);
			for (Iterator<String> itList = ClientList.keySet().iterator();itList.hasNext();) {
				String red5Id  = itList.next();
				RoomClient rcl = ClientList.get(red5Id);
				
				if (rcl.getUser_id().equals(users_id)){
					log.info("updateUserSessionObject #### FOUND USER rcl1: "+rcl.getUser_id()+ " NEW PIC: "+pictureuri);
					rcl.setPicture_uri(pictureuri);
					rcl.setUsername(us.getLogin());
					rcl.setFirstname(us.getFirstname());
					rcl.setLastname(us.getLastname());
					ClientList.put(red5Id, rcl);
				}
			}
		} catch (Exception err) {
			log.error("[updateUserSessionObject]",err);
		}
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
			Long room_id = currentClient.getRoom_id();	
			
			String streamid = currentClient.getStreamid();
			
			log.debug("getStreams room_id: "+room_id);
			Set<String> keys = ClientList.keySet();
			Iterator<String> iter = keys.iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				log.debug("getStreams key: "+key);
				RoomClient rcl = ClientList.get(key);
				if (rcl.getRoom_id()==room_id && rcl.getRoom_id()!=null && !rcl.getStreamid().equals(streamid)) roomClientListScopeNames.add(rcl.getStreamid());
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
		log.debug("Received result " + call.getResult() + " for "+ call.getServiceMethodName());
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