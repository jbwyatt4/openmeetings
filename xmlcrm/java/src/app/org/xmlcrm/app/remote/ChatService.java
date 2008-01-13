package org.xmlcrm.app.remote;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.red5.server.api.IConnection;
import org.red5.server.api.Red5;
import org.red5.server.api.service.IPendingServiceCall;
import org.red5.server.api.service.IPendingServiceCallback;
import org.red5.server.api.service.IServiceCapableConnection;
import org.xmlcrm.app.conference.videobeans.RoomClient;
import org.xmlcrm.utils.stringhandlers.ChatString;

/**
 * 
 * @author sebastianwagner
 *
 */
public class ChatService implements IPendingServiceCallback {

	//the overall chatroom is jsut another room
	private static final String overallChatRoomName = "overall";
	
	//number of items in the Chatroom history
	private static final int chatRoomHistory = 50;
	
	private static final Log log = LogFactory.getLog(ChatService.class);
	
	private static LinkedHashMap<String,List<HashMap<String,Object>>> myChats = new LinkedHashMap<String,List<HashMap<String,Object>>>();
	
	/**
	 * sends a Chat-Message
	 * to all members of the Chatroom
	 * and all additional users (waitng for a free entry for example)
	 * @param newMessage
	 * @return
	 */
	public int sendMessageWithClient(Object newMessage) {
		try {
			IConnection current = Red5.getConnectionLocal();
			RoomClient currentClient = Application.getClientList().get(current.getClient().getId());
			String roomname = currentClient.getUserroom();
			String orgdomain = currentClient.getDomain();	
			
			if (currentClient.getIsChatNotification()){
				roomname = currentClient.getChatUserroom();
				orgdomain = currentClient.getChatDomain();
			}
			
			HashMap<String,Object> hsm = new HashMap<String,Object>();
			hsm.put("client", currentClient);
			hsm.put("message", newMessage);
			
			String chatroom = "_"+roomname+"_"+orgdomain;
			
			List<HashMap<String,Object>> myChatList = myChats.get(chatroom);
			if (myChatList==null) myChatList = new LinkedList<HashMap<String,Object>>();
			
			if (myChatList.size()==chatRoomHistory) myChatList.remove(0);
			myChatList.add(hsm);
			myChats.put(chatroom,myChatList);
			
			log.debug("SET CHATROOM: "+chatroom);
			
			//broadcast to everybody in the room/domain
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				IConnection conn = it.next();
				if (conn instanceof IServiceCapableConnection) {
					RoomClient rcl = Application.getClientList().get(conn.getClient().getId());
					log.debug("*..*idremote: " + rcl.getStreamid());
					log.debug("*..*my idstreamid: " + currentClient.getStreamid());
					if (roomname.equals(rcl.getUserroom()) && orgdomain.equals(rcl.getDomain())) {
						((IServiceCapableConnection) conn).invoke("sendVarsToMessageWithClient",new Object[] { hsm }, this);
						log.debug("sending sendVarsToMessageWithClient to " + conn);
						if (rcl.getIsRecording()){
							StreamService.addChatEvent(rcl.getRoomRecordingName(),hsm);
						}							
					} else if (rcl.getIsChatNotification()) {
						if (roomname.equals(rcl.getChatUserroom()) && orgdomain.equals(rcl.getChatDomain())) {
							((IServiceCapableConnection) conn).invoke("sendVarsToMessageWithClient",new Object[] { hsm }, this);
						}
					}
				}
			}
		} catch (Exception err) {
			log.error("[ChatService sendMessageWithClient] ",err);
			return -1;
		}
		return 1;
	}
	
	public List<HashMap<String,Object>> clearChat() {
		try {
			IConnection current = Red5.getConnectionLocal();
			RoomClient currentClient = Application.getClientList().get(current.getClient().getId());
			String roomname = currentClient.getUserroom();
			String orgdomain = currentClient.getDomain();
			
			String chatroom = "_"+roomname+"_"+orgdomain;
			log.error("GET CHATROOM: "+chatroom);
			
			List<HashMap<String,Object>> myChatList = myChats.get(chatroom);
			myChatList = new LinkedList<HashMap<String,Object>>();
			
			myChats.put(chatroom,myChatList);
			
			return myChatList;
			
		} catch (Exception err) {
			log.error("[clearChat] ",err);
			return null;
		}
	}
	
	public List<HashMap<String,Object>> getRoomChatHistory() {
		try {
			IConnection current = Red5.getConnectionLocal();
			RoomClient currentClient = Application.getClientList().get(current.getClient().getId());
			String roomname = currentClient.getUserroom();
			String orgdomain = currentClient.getDomain();
			
			String chatroom = "_"+roomname+"_"+orgdomain;
			log.debug("GET CHATROOM: "+chatroom);
			
			List<HashMap<String,Object>> myChatList = myChats.get(chatroom);
			if (myChatList==null) myChatList = new LinkedList<HashMap<String,Object>>();	
			
			return myChatList;
		} catch (Exception err) {
			log.error("[getRoomChatHistory] ",err);
			return null;
		}
	}
	
	/**
	 * gets the chat history by string for non-conference-clients
	 * @param roomname
	 * @param orgdomain
	 * @return
	 */
	public List<HashMap<String,Object>> getRoomChatHistoryByString(String roomname, String orgdomain) {
		try {
			
			String chatroom = "_"+roomname+"_"+orgdomain;
			log.debug("GET CHATROOM: "+chatroom);
			
			List<HashMap<String,Object>> myChatList = myChats.get(chatroom);
			if (myChatList==null) myChatList = new LinkedList<HashMap<String,Object>>();	
			
			return myChatList;
		} catch (Exception err) {
			log.error("[getRoomChatHistory] ",err);
			return null;
		}
	}	
	
	/**
	 * adds a Client to the additional List of Users to Chat
	 * @param userroom
	 * @param room_id
	 * @param orgdomain
	 * @return
	 */
	public Long addClientToChatNotification(String userroom, Long room_id, String orgdomain){
		try {
			IConnection current = Red5.getConnectionLocal();
			RoomClient currentClient = Application.getClientList().get(current.getClient().getId());					
			String streamid = currentClient.getStreamid();
			
			currentClient.setIsChatNotification(true);
			currentClient.setChatDomain(orgdomain);
			currentClient.setChatUserroom(userroom);
			
			Application.getClientList().put(streamid, currentClient);
		} catch (Exception err) {
			log.error("[addClientToCahtNotification]",err);
		}
		return new Long(-1);
	} 
	
	/**
	 * remove's a Client from the additional List of users to chat
	 * @param userroom
	 * @param room_id
	 * @param orgdomain
	 * @return
	 */
	public Long removeClientFromChatNotification(){
		try {
			IConnection current = Red5.getConnectionLocal();
			RoomClient currentClient = Application.getClientList().get(current.getClient().getId());					
			String streamid = currentClient.getStreamid();
			
			currentClient.setIsChatNotification(false);
			currentClient.setChatDomain("");
			currentClient.setChatUserroom("");
			
			Application.getClientList().put(streamid, currentClient);
		} catch (Exception err) {
			log.error("[addClientToCahtNotification]",err);
		}
		return new Long(-1);
	}
	

	public void resultReceived(IPendingServiceCall arg0) {
		// TODO Auto-generated method stub
		log.error("resultReceived ChatService "+arg0);
	}
	
	/**
	 * sends a message to all connected users
	 * @param SID
	 * @param newMessage
	 * @return
	 */
	public int sendMessageToOverallChat(Object newMessage) {
		try {
			IConnection current = Red5.getConnectionLocal();
			RoomClient currentClient = Application.getClientList().get(current.getClient().getId());
			
			log.error(newMessage.getClass().getName());
			LinkedHashMap messageMap = (LinkedHashMap) newMessage;
			//adding delimiter space, cause otherwise an emoticon in the last string would not be found
			String messageText = messageMap.get(4).toString()+" ";
			log.error("messageText"+messageText);
			LinkedList<String[]> parsedStringObjects = ChatString.getInstance().parseChatString(messageText);
			log.error("parsedStringObjects"+parsedStringObjects.size());
			messageMap.put(9, parsedStringObjects);
			newMessage = messageMap;
			
			
			HashMap<String,Object> hsm = new HashMap<String,Object>();
			hsm.put("client", currentClient);
			hsm.put("message", newMessage);
			
			List<HashMap<String,Object>> myChatList = myChats.get(overallChatRoomName);
			if (myChatList==null) myChatList = new LinkedList<HashMap<String,Object>>();
			
			if (myChatList.size()==chatRoomHistory) myChatList.remove(0);
			myChatList.add(hsm);
			myChats.put(overallChatRoomName,myChatList);
			
			log.debug("SET CHATROOM: "+overallChatRoomName);
			
			//broadcast to everybody in the room/domain
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				IConnection conn = it.next();
				if (conn instanceof IServiceCapableConnection) {
					RoomClient rcl = Application.getClientList().get(conn.getClient().getId());
					log.debug("*..*idremote: " + rcl.getStreamid());
					log.debug("*..*my idstreamid: " + currentClient.getStreamid());
					((IServiceCapableConnection) conn).invoke("sendVarsToOverallChat",new Object[] { hsm }, this);
				}
			}
		} catch (Exception err) {
			log.error("[ChatService sendMessageToOverallChat] ",err);
			return -1;
		}
		return 1;
	}
	
	
	/**
	 * gets the chat history of overallChat
	 * @param roomname
	 * @param orgdomain
	 * @return
	 */
	public List<HashMap<String,Object>> getOverallChatHistory() {
		try {
			
			List<HashMap<String,Object>> myChatList = myChats.get(overallChatRoomName);
			if (myChatList==null) myChatList = new LinkedList<HashMap<String,Object>>();	
			
			return myChatList;
		} catch (Exception err) {
			log.error("[getRoomChatHistory] ",err);
			return null;
		}
	}	
	
	/**
	 * clear the overallChat history
	 * @return
	 */
	public List<HashMap<String,Object>> clearOverallChat() {
		try {
			
			List<HashMap<String,Object>> myChatList = myChats.get(overallChatRoomName);
			myChatList = new LinkedList<HashMap<String,Object>>();
			
			myChats.put(overallChatRoomName,myChatList);
			
			return myChatList;
			
		} catch (Exception err) {
			log.error("[clearChat] ",err);
			return null;
		}
	}	
	
}
