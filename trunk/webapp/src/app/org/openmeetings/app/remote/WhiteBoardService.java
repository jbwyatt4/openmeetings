package org.openmeetings.app.remote;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmeetings.app.conference.videobeans.RoomClient;
import org.openmeetings.app.conference.whiteboard.WhiteboardSyncLockObject;
import org.red5.server.api.IConnection;
import org.red5.server.api.Red5;
import org.red5.server.api.service.IPendingServiceCall;
import org.red5.server.api.service.IPendingServiceCallback;
import org.red5.server.api.service.IServiceCapableConnection;

/**
 * 
 * @author sebastianwagner
 *
 */
public class WhiteBoardService implements IPendingServiceCallback {
	
	private static final Log log = LogFactory.getLog(WhiteBoardService.class);	

	/**
	 * Loading the List of Objects on the whiteboard
	 * @return HashMap<String,Map>
	 */
	public LinkedList<Map> getRoomItems(){
		try {
			IConnection current = Red5.getConnectionLocal();
			String streamid = current.getClient().getId();
			RoomClient currentClient = Application.getClientList().get(streamid);
			Long room_id = currentClient.getRoom_id();
			
			log.debug("getRoomItems: "+room_id);
			HashMap<String,Map> roomItems = Application.getWhiteBoardObjectListByRoomId(room_id);
			
			LinkedList<Map> itemList = new LinkedList<Map>();
			for (Iterator<String> it = roomItems.keySet().iterator();it.hasNext();){
				itemList.add(roomItems.get(it.next()));
			}
			
			
			return itemList;
		} catch (Exception err) {
			log.error("[getRoomItems]",err);
		}
		return null;
	}
	
	public WhiteboardSyncLockObject startNewSyncprocess(){
		try {
			
			IConnection current = Red5.getConnectionLocal();
			String streamid = current.getClient().getId();
			RoomClient currentClient = Application.getClientList().get(streamid);
			Long room_id = currentClient.getRoom_id();
			
			WhiteboardSyncLockObject wSyncLockObject = new WhiteboardSyncLockObject();
			wSyncLockObject.setAddtime(new Date());
			wSyncLockObject.setRoomclient(currentClient);
			wSyncLockObject.setInitialLoaded(true);
			
			Map<String,WhiteboardSyncLockObject> syncListRoom = Application.getWhiteBoardSyncListByRoomid(room_id);
			

			wSyncLockObject.setCurrentLoadingItem(true);
			wSyncLockObject.setStarttime(new Date());
		
			syncListRoom.put(currentClient.getPublicSID(), wSyncLockObject);
			Application.setWhiteBoardSyncListByRoomid(room_id, syncListRoom);
			
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				IConnection conn = it.next();
				if (conn instanceof IServiceCapableConnection) {
					RoomClient rcl = Application.getClientList().get(conn.getClient().getId());
					if (room_id!=null && room_id == rcl.getRoom_id()) {
						((IServiceCapableConnection) conn).invoke("sendSyncFlag", new Object[] { wSyncLockObject },this);
					}
				}
			}	
			
			return wSyncLockObject;
			
			
		} catch (Exception err) {
			log.error("[startNewSyncprocess]",err);
		}
		return null;
	}
	
	public int sendCompletedSyncEvent() {
		try {
			
			IConnection current = Red5.getConnectionLocal();
			String streamid = current.getClient().getId();
			RoomClient currentClient = Application.getClientList().get(streamid);
			Long room_id = currentClient.getRoom_id();
			
			Map<String,WhiteboardSyncLockObject> syncListRoom = Application.getWhiteBoardSyncListByRoomid(room_id);

			WhiteboardSyncLockObject wSyncLockObject = syncListRoom.get(currentClient.getPublicSID());
			
			if (wSyncLockObject == null) {
				log.error("WhiteboardSyncLockObject not found for this Client "+syncListRoom);
				return -2;
			} else if (!wSyncLockObject.isCurrentLoadingItem()) {
				log.warn("WhiteboardSyncLockObject was not started yet "+syncListRoom);
				return -3;
			} else {
				syncListRoom.remove(currentClient.getPublicSID());
				Application.setWhiteBoardSyncListByRoomid(room_id, syncListRoom);
				
				int numberOfInitial = this.getNumberOfInitialLoaders(syncListRoom);
				
				if (numberOfInitial==0){
					int returnVal = 0;
					Iterator<IConnection> it = current.getScope().getConnections();
					while (it.hasNext()) {
						IConnection conn = it.next();
						if (conn instanceof IServiceCapableConnection) {
							RoomClient rcl = Application.getClientList().get(conn.getClient().getId());
							if (rcl.getPublicSID().equals(currentClient.getPublicSID())){
								if (room_id!=null && room_id == rcl.getRoom_id()) {
									returnVal++;
									((IServiceCapableConnection) conn).invoke("sendSyncCompleteFlag", new Object[] { wSyncLockObject },this);
								}
							}
						}
					}	
					return returnVal;
				} else {
					return -4;
				}
			}
			
			
		} catch (Exception err) {
			log.error("[sendCompletedSyncEvent]",err);
		}
		return -1;
	}
	
	private int getNumberOfInitialLoaders(Map<String,WhiteboardSyncLockObject> syncListRoom) throws Exception {
		int number = 0;
		for (Iterator<String> iter = syncListRoom.keySet().iterator();iter.hasNext();) {
			WhiteboardSyncLockObject lockObject = syncListRoom.get(iter.next());
			if (lockObject.isInitialLoaded()){
				number++;
			}
		}
		return number;
	}

	public void resultReceived(IPendingServiceCall arg0) {
		// TODO Auto-generated method stub
		log.debug("resultReceived: "+arg0);
	}

}
