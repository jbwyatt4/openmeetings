package org.openmeetings.app.remote;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmeetings.app.conference.videobeans.RoomClient;
import org.openmeetings.app.conference.whiteboard.WhiteboardManagement;
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
	
	private WhiteBoardService() {}
	
	private static WhiteBoardService instance = null;
	
	public static synchronized WhiteBoardService getInstance() {
		if (instance == null) {
			instance = new WhiteBoardService();
		}
		return instance;
	}
	
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
							if (room_id!=null && room_id == rcl.getRoom_id()) {
								returnVal++;
								((IServiceCapableConnection) conn).invoke("sendSyncCompleteFlag", new Object[] { wSyncLockObject },this);
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

	/*
	 * Image Sync Sequence
	 * 
	 */
	
	public WhiteboardSyncLockObject startNewImagesSyncprocess(String image_id){
		try {
			
			log.debug("startNewImagesSyncprocess: "+image_id);
			
			IConnection current = Red5.getConnectionLocal();
			String streamid = current.getClient().getId();
			RoomClient currentClient = Application.getClientList().get(streamid);
			Long room_id = currentClient.getRoom_id();
			
			WhiteboardSyncLockObject wSyncLockObject = new WhiteboardSyncLockObject();
			wSyncLockObject.setAddtime(new Date());
			wSyncLockObject.setRoomclient(currentClient);
			wSyncLockObject.setImageLoader(true);
			wSyncLockObject.setCurrentLoadingItem(true);
			wSyncLockObject.setStarttime(new Date());
			
			Map<String,WhiteboardSyncLockObject> syncListImage = Application.getWhiteBoardImagesSyncListByRoomAndImageid(room_id,image_id);
			syncListImage.put(currentClient.getPublicSID(), wSyncLockObject);
			Application.setWhiteBoardImagesSyncListByRoomAndImageid(room_id, image_id, syncListImage);
			
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				IConnection conn = it.next();
				if (conn instanceof IServiceCapableConnection) {
					RoomClient rcl = Application.getClientList().get(conn.getClient().getId());
					log.debug("sending :"+rcl);
					if (room_id!=null && room_id == rcl.getRoom_id()) {
						log.debug("sendImagesSyncFlag :"+rcl);
						((IServiceCapableConnection) conn).invoke("sendImagesSyncFlag", new Object[] { wSyncLockObject },this);
					}
				}
			}	
			
			return wSyncLockObject;
			
			
		} catch (Exception err) {
			log.error("[startNewImagesSyncprocess]",err);
		}
		return null;
	}
	
	public int sendCompletedImagesSyncEvent(String image_id) {
		try {
			
			log.debug("sendCompletedImagesSyncEvent: "+image_id);
			
			IConnection current = Red5.getConnectionLocal();
			String streamid = current.getClient().getId();
			RoomClient currentClient = Application.getClientList().get(streamid);
			Long room_id = currentClient.getRoom_id();
			
			Map<String,WhiteboardSyncLockObject> syncListImage = Application.getWhiteBoardImagesSyncListByRoomAndImageid(room_id,image_id);

			log.debug("sendCompletedImagesSyncEvent syncListImage: "+syncListImage);
			
			WhiteboardSyncLockObject wSyncLockObject = syncListImage.get(currentClient.getPublicSID());
			
			if (wSyncLockObject == null) {
				log.error("WhiteboardSyncLockObject not found for this Client "+currentClient.getPublicSID());
				log.error("WhiteboardSyncLockObject not found for this syncListImage "+syncListImage);
				return -2;
			} else if (!wSyncLockObject.isCurrentLoadingItem()) {
				log.warn("WhiteboardSyncLockObject was not started yet "+syncListImage);
				return -3;
			} else {
				
				log.debug("sendCompletedImagesSyncEvent remove: "+currentClient.getPublicSID());
				
				syncListImage.remove(currentClient.getPublicSID());
				Application.setWhiteBoardImagesSyncListByRoomAndImageid(room_id, image_id, syncListImage);
				
				int numberOfInitial = Application.getWhiteBoardImagesSyncListByRoomid(room_id).size();
				
				log.debug("sendCompletedImagesSyncEvent numberOfInitial: "+numberOfInitial);
				
				if (numberOfInitial==0){
					int returnVal = 0;
					Iterator<IConnection> it = current.getScope().getConnections();
					while (it.hasNext()) {
						IConnection conn = it.next();
						if (conn instanceof IServiceCapableConnection) {
							RoomClient rcl = Application.getClientList().get(conn.getClient().getId());
							if (room_id!=null && room_id == rcl.getRoom_id()) {
								returnVal++;
								((IServiceCapableConnection) conn).invoke("sendImagesSyncCompleteFlag", new Object[] { wSyncLockObject },this);
							}
						}
					}	
					return returnVal;
				} else {
					return -4;
				}
			}
			
			
		} catch (Exception err) {
			log.error("[sendCompletedImagesSyncEvent]",err);
		}
		return -1;
	}
	
	
//	private int getNumberOfImageLoaders(Map<String,WhiteboardSyncLockObject> syncListRoom) throws Exception {
//		int number = 0;
//		for (Iterator<String> iter = syncListRoom.keySet().iterator();iter.hasNext();) {
//			WhiteboardSyncLockObject lockObject = syncListRoom.get(iter.next());
//			if (lockObject.isImageLoader()){
//				number++;
//			}
//		}
//		return number;
//	}	
//	

	/*
	 * Image Sync Sequence
	 * 
	 */
	
	public WhiteboardSyncLockObject startNewSWFSyncprocess(){
		try {
			
			IConnection current = Red5.getConnectionLocal();
			String streamid = current.getClient().getId();
			RoomClient currentClient = Application.getClientList().get(streamid);
			Long room_id = currentClient.getRoom_id();
			
			WhiteboardSyncLockObject wSyncLockObject = new WhiteboardSyncLockObject();
			wSyncLockObject.setAddtime(new Date());
			wSyncLockObject.setRoomclient(currentClient);
			wSyncLockObject.setSWFLoader(true);
			
			Map<String,WhiteboardSyncLockObject> syncListRoom = Application.getWhiteBoardSWFSyncListByRoomid(room_id);
			

			wSyncLockObject.setCurrentLoadingItem(true);
			wSyncLockObject.setStarttime(new Date());
		
			syncListRoom.put(currentClient.getPublicSID(), wSyncLockObject);
			Application.setWhiteBoardSWFSyncListByRoomid(room_id, syncListRoom);
			
			Iterator<IConnection> it = current.getScope().getConnections();
			while (it.hasNext()) {
				IConnection conn = it.next();
				if (conn instanceof IServiceCapableConnection) {
					RoomClient rcl = Application.getClientList().get(conn.getClient().getId());
					if (room_id!=null && room_id == rcl.getRoom_id()) {
						((IServiceCapableConnection) conn).invoke("sendSWFSyncFlag", new Object[] { wSyncLockObject },this);
					}
				}
			}	
			
			return wSyncLockObject;
			
			
		} catch (Exception err) {
			log.error("[startNewSWFSyncprocess]",err);
		}
		return null;
	}
	
	public int sendCompletedSWFSyncEvent() {
		try {
			
			IConnection current = Red5.getConnectionLocal();
			String streamid = current.getClient().getId();
			RoomClient currentClient = Application.getClientList().get(streamid);
			Long room_id = currentClient.getRoom_id();
			
			Map<String,WhiteboardSyncLockObject> syncListRoom = Application.getWhiteBoardSWFSyncListByRoomid(room_id);

			WhiteboardSyncLockObject wSyncLockObject = syncListRoom.get(currentClient.getPublicSID());
			
			if (wSyncLockObject == null) {
				log.error("WhiteboardSyncLockObject not found for this Client "+syncListRoom);
				return -2;
			} else if (!wSyncLockObject.isCurrentLoadingItem()) {
				log.warn("WhiteboardSyncLockObject was not started yet "+syncListRoom);
				return -3;
			} else {
				syncListRoom.remove(currentClient.getPublicSID());
				Application.setWhiteBoardSWFSyncListByRoomid(room_id, syncListRoom);
				
				int numberOfInitial = this.getNumberOfSWFLoaders(syncListRoom);
				
				if (numberOfInitial==0){
					int returnVal = 0;
					Iterator<IConnection> it = current.getScope().getConnections();
					while (it.hasNext()) {
						IConnection conn = it.next();
						if (conn instanceof IServiceCapableConnection) {
							RoomClient rcl = Application.getClientList().get(conn.getClient().getId());
							if (room_id!=null && room_id == rcl.getRoom_id()) {
								returnVal++;
								((IServiceCapableConnection) conn).invoke("sendSWFSyncCompleteFlag", new Object[] { wSyncLockObject },this);
							}
						}
					}	
					return returnVal;
				} else {
					return -4;
				}
			}
			
			
		} catch (Exception err) {
			log.error("[sendCompletedSWFSyncEvent]",err);
		}
		return -1;
	}
	
	
	private int getNumberOfSWFLoaders(Map<String,WhiteboardSyncLockObject> syncListRoom) throws Exception {
		int number = 0;
		for (Iterator<String> iter = syncListRoom.keySet().iterator();iter.hasNext();) {
			WhiteboardSyncLockObject lockObject = syncListRoom.get(iter.next());
			if (lockObject.isSWFLoader()){
				number++;
			}
		}
		return number;
	}	
	
	public void removeUserFromAllLists(IConnection current, RoomClient currentClient){
		try {
			
			Long room_id = currentClient.getRoom_id();
			
			//TODO: Maybe we should also check all rooms, independent from the current
			//room_id if there is any user registered
			if (room_id != null) {
				
				
				//Check Initial Loaders
				Map<String,WhiteboardSyncLockObject> syncListRoom = Application.getWhiteBoardSyncListByRoomid(room_id);
				
				WhiteboardSyncLockObject wSyncLockObject = syncListRoom.get(currentClient.getPublicSID());
				
				if (wSyncLockObject != null) {
					syncListRoom.remove(currentClient.getPublicSID());
				}
				Application.setWhiteBoardSyncListByRoomid(room_id, syncListRoom);
				
				int numberOfInitial = this.getNumberOfInitialLoaders(syncListRoom);
				
				if (numberOfInitial==0){
					Iterator<IConnection> it = current.getScope().getConnections();
					while (it.hasNext()) {
						IConnection conn = it.next();
						if (conn instanceof IServiceCapableConnection) {
							RoomClient rcl = Application.getClientList().get(conn.getClient().getId());
							if (room_id!=null && room_id == rcl.getRoom_id()) {
								//do not send to current
								if (!rcl.getPublicSID().equals(currentClient.getPublicSID())) {
									((IServiceCapableConnection) conn).invoke("sendSyncCompleteFlag", new Object[] { wSyncLockObject },this);
								}
							}
						}
					}	
				}
				
				
				//Check Image Loaders
				Map<String,Map<String,WhiteboardSyncLockObject>> syncListRoomImages = Application.getWhiteBoardImagesSyncListByRoomid(room_id);
				
				for (Iterator<String> iter = syncListRoomImages.keySet().iterator();iter.hasNext();) {
					String image_id = iter.next();
					Map<String,WhiteboardSyncLockObject> syncListImages = syncListRoomImages.get(image_id);
					WhiteboardSyncLockObject wImagesSyncLockObject = syncListImages.get(currentClient.getPublicSID());
					if (wImagesSyncLockObject != null) {
						syncListImages.remove(currentClient.getPublicSID());
					}
					Application.setWhiteBoardImagesSyncListByRoomAndImageid(room_id, image_id, syncListImages);
				}
				
				int numberOfImageLoaders = Application.getWhiteBoardImagesSyncListByRoomid(room_id).size();
				
				if (numberOfImageLoaders==0){
					Iterator<IConnection> it = current.getScope().getConnections();
					while (it.hasNext()) {
						IConnection conn = it.next();
						if (conn instanceof IServiceCapableConnection) {
							RoomClient rcl = Application.getClientList().get(conn.getClient().getId());
							if (room_id!=null && room_id == rcl.getRoom_id()) {
								//do not send to current
								if (!rcl.getPublicSID().equals(currentClient.getPublicSID())) {
									((IServiceCapableConnection) conn).invoke("sendImagesSyncCompleteFlag", new Object[] { "remove" },this);
								} else {
									log.debug("IS current");
								}
							}
						}
					}	
				}
				
				
//				//TODO: Check SWF Loaders
//				Map<String,WhiteboardSyncLockObject> syncListSWF = Application.getWhiteBoardSWFSyncListByRoomid(room_id);
//				
//				WhiteboardSyncLockObject wSWFSyncLockObject = syncListImages.get(currentClient.getPublicSID());
//				
//				if (wSWFSyncLockObject != null) {
//					syncListSWF.remove(currentClient.getPublicSID());
//				}
//				Application.setWhiteBoardSWFSyncListByRoomid(room_id, syncListSWF);
//				
//				int numberOfSWFLoaders = this.getNumberOfSWFLoaders(syncListImages);
//				
//				if (numberOfSWFLoaders==0){
//					Iterator<IConnection> it = current.getScope().getConnections();
//					while (it.hasNext()) {
//						IConnection conn = it.next();
//						if (conn instanceof IServiceCapableConnection) {
//							RoomClient rcl = Application.getClientList().get(conn.getClient().getId());
//							if (room_id!=null && room_id == rcl.getRoom_id()) {
//								//do not send to current
//								if (!rcl.getPublicSID().equals(currentClient.getPublicSID())) {
//									((IServiceCapableConnection) conn).invoke("sendSWFSyncCompleteFlag", new Object[] { wSWFSyncLockObject },this);
//								}
//							}
//						}
//					}	
//				}
//				
//				
			}
			
		} catch (Exception err) {
			log.error("[removeUserFromAllLists]",err);
		}
	}
	
	public void resultReceived(IPendingServiceCall arg0) {
		// TODO Auto-generated method stub
		log.debug("resultReceived: "+arg0);
	}

}
