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

/**
 * 
 * @author sebastianwagner
 *
 */
public class ChatService implements IPendingServiceCallback {

	private static final Log log = LogFactory.getLog(ChatService.class);
	
	private static LinkedHashMap<String,List<HashMap<String,Object>>> myChats = new LinkedHashMap<String,List<HashMap<String,Object>>>();
	
	public int sendMessageWithClient(Object newMessage) {
		try {
			IConnection current = Red5.getConnectionLocal();
			RoomClient currentClient = Application.getClientList().get(current.getClient().getId());
			String roomname = currentClient.getUserroom();
			String orgdomain = currentClient.getDomain();	
			
			HashMap<String,Object> hsm = new HashMap<String,Object>();
			hsm.put("client", currentClient);
			hsm.put("message", newMessage);
			
			String chatroom = "_"+roomname+"_"+orgdomain;
			
			List<HashMap<String,Object>> myChatList = myChats.get(chatroom);
			if (myChatList==null) myChatList = new LinkedList<HashMap<String,Object>>();
			
			if (myChatList.size()==50) myChatList.remove(0);
			myChatList.add(hsm);
			myChats.put(chatroom,myChatList);
			
			System.out.println("SET CHATROOM: "+chatroom);
			
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
					}
				}
			}
		} catch (Exception err) {
			log.error("[ChatService sendMessageWithClient] ",err);
			return -1;
		}
		return 1;
	}
	
	public List<HashMap<String,Object>> getRoomChatHistory() {
		try {
			IConnection current = Red5.getConnectionLocal();
			RoomClient currentClient = Application.getClientList().get(current.getClient().getId());
			String roomname = currentClient.getUserroom();
			String orgdomain = currentClient.getDomain();
			
			String chatroom = "_"+roomname+"_"+orgdomain;
			System.out.println("GET CHATROOM: "+chatroom);
			
			List<HashMap<String,Object>> myChatList = myChats.get(chatroom);
			if (myChatList==null) myChatList = new LinkedList<HashMap<String,Object>>();	
			
			return myChatList;
		} catch (Exception err) {
			log.error("[getRoomChatHistory] ",err);
			return null;
		}
	}

	public void resultReceived(IPendingServiceCall arg0) {
		// TODO Auto-generated method stub
		log.error("resultReceived ChatService "+arg0);
	}
	
}
