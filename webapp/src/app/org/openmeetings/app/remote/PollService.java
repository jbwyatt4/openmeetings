package org.openmeetings.app.remote;

import java.util.HashMap;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.red5.server.adapter.ApplicationAdapter;
import org.red5.server.api.IClient;
import org.red5.server.api.IConnection;
//import org.red5.server.api.IScope;
import org.red5.server.api.Red5;
import org.red5.server.api.service.IServiceCapableConnection;
import org.openmeetings.app.conference.configutils.CustomBandwidth;

import org.openmeetings.app.conference.videobeans.RoomPoll;
import org.openmeetings.app.conference.videobeans.RoomPollAnswers;
import org.openmeetings.app.conference.videobeans.PollType;
import org.openmeetings.app.hibernate.beans.recording.RoomClient;

/**
 * 
 * @author swagner
 *
 */
public class PollService {
	
	private static final Logger log = LoggerFactory.getLogger(PollService.class);
	
	private static HashMap<Long,RoomPoll> pollList = new HashMap<Long,RoomPoll>();

	/**
	 * @deprecated
	 * @param userName
	 * @param userPass
	 * @return
	 */
	public String loginUser(String userName,String userPass){
		try {
			if (Red5.getConnectionLocal().getScope().getName().equals("admin")){
				IClient myclient = Red5.getConnectionLocal().getClient();
				String role = Application.getUserFactory().checkUser(userName, userPass);
				myclient.setAttribute("role", role);
				return role;
			}
		} catch (Exception err) {
			log.error("Error: "+err);
		}
		return "no";
	}
	
	/**
	 * @deprecated
	 * @return
	 */
	public String getUserScope(){
		log.debug("getUserScope");
		return Red5.getConnectionLocal().getScope().getName();
	}
	
	/**
	 * @deprecated
	 * @return
	 */
	public HashMap<String,CustomBandwidth> getBandwidthConfig(){
		IClient myclient = Red5.getConnectionLocal().getClient();
		log.debug("getBandwidthConfig: "+myclient.getAttribute("role"));
		if(myclient.getAttribute("role").equals("admin")){
			log.debug("getBandwidthConfig");
			HashMap<String,CustomBandwidth> bandWidthSzenario = Application.getBwFactory().getDokeosBandwidthConfig();
			return bandWidthSzenario;
		}
		return null;
	}
	
	/**
	 * Save a new ServerSide Bandwidth File
	 * This server side configuration is disabled cause of bad audio-effects
	 * @param config
	 * @return
	 * @deprecated
	 */
	public String saveBandwidthConfig(HashMap config){
		IClient myclient = Red5.getConnectionLocal().getClient();
		log.debug("getBandwidthConfig: "+myclient.getAttribute("role"));
		if(myclient.getAttribute("role").equals("admin")){
			log.debug("getBandwidthConfig"+config);
			log.debug("saveBandwidthConfig",config);
			
			// {videoBandwidth=0, upstreamBandwidth=8192, overallBandwidth=2097152, minpingTime=500, maxpingTime=1000, maxBurst=8388608, groupName=Group 1, downstreamBandwidth=8192, description=This is for a bad Client Connection, burst=8388608, audioBandwidth=0, bandwidthName=badconnection}
		    
			CustomBandwidth ctc = new CustomBandwidth();
//			ctc.setAudioBandwidth(Long.parseLong(config.get("audioBandwidth").toString()));
//			ctc.setBurst(Long.parseLong(config.get("burst").toString()));
//			ctc.setDescription(config.get("description").toString());
//			ctc.setDownstreamBandwidth(Long.parseLong(config.get("downstreamBandwidth").toString()));
//			ctc.setGroupName(config.get("groupName").toString());
//			ctc.setMaxBurst(Integer.parseInt(config.get("maxBurst").toString()));
//			ctc.setMinpingTime(Integer.parseInt(config.get("minpingTime").toString()));
//			ctc.setOverallBandwidth(Long.parseLong(config.get("overallBandwidth").toString()));
//			ctc.setUpstreamBandwidth(Long.parseLong(config.get("upstreamBandwidth").toString()));
//			ctc.setVideoBandwidth(Long.parseLong(config.get("videoBandwidth").toString()));
			
			HashMap<String,CustomBandwidth> bandWidthSzenario = Application.getBwFactory().getDokeosBandwidthConfig();
			bandWidthSzenario.put(config.get("bandwidthName").toString(), ctc);
			
			Application.getBwFactory().setDokeosBandwidthConfig(bandWidthSzenario);
			
			return "";
		}
		return null;
		
	}
	
	public String createPoll(String pollQuestion,int pollTypeId){
		String returnValue="";
		try {
			log.debug("createPoll: "+pollQuestion);
			
			IConnection currentcon = Red5.getConnectionLocal();

			
			HashMap<String,RoomClient> ClientList = Application.getClientList();
			RoomClient rc = ClientList.get(currentcon.getClient().getId());
			
			Long uniqueRoomPollName = rc.getRoom_id();
			
			log.debug("rc: "+rc.getStreamid()+" "+rc.getUsername()+" "+rc.getIsMod());
			
			if (rc.getIsMod()){
				RoomPoll roomP = new RoomPoll();
				
				roomP.setCreatedBy(rc);
				roomP.setPollDate(new Date());
				roomP.setPollQuestion(pollQuestion);
				roomP.setPollTypeId(pollTypeId);
				roomP.setRoom_id(rc.getRoom_id());
				List<RoomPollAnswers> rpA = new LinkedList<RoomPollAnswers>();
				roomP.setRoomPollAnswerList(rpA);
				
				pollList.put(uniqueRoomPollName, roomP);
				
				sendNotification(currentcon,"newPoll",new Object[] { roomP });
				returnValue="200";
			} else {
				returnValue="202";
			}

			
		} catch (Exception err){
			returnValue="203";
			log.error("[createPoll]",err);
		}
		return returnValue;
	}	

	public static void clearRoomPollList(Long room_id){
		try {
			log.debug("clearRoomPollList: "+room_id);
			if(pollList.get(room_id)!=null){
				pollList.remove(room_id);
			}
		} catch (Exception err){
			log.error("[clearRoomPollList]",err);
		}
	}
	
	public void sendNotification(IConnection current,String clientFunction, Object[]obj) throws Exception{
		//Notify all clients of the same scope (room)
		RoomClient rc = Application.getClientList().get(current.getClient().getId());
		Iterator<IConnection> it = current.getScope().getConnections();
		while (it.hasNext()) {
			IConnection conn = it.next();
			if (conn instanceof IServiceCapableConnection) {
				RoomClient rcl = Application.getClientList().get(conn.getClient().getId());
				if (rcl.getRoom_id().equals(rc.getRoom_id()) && rcl.getRoom_id()!=null){
					((IServiceCapableConnection) conn).invoke(clientFunction,obj,Application.getInstance());
					log.debug("sending "+clientFunction+" to " + conn+" "+conn.getClient().getId());
				}
			}
		}
	}
	
	public List<PollType> getPollTypeList(){
		List<PollType> pollTypesList = new LinkedList<PollType>();
		try {
			
			PollType pt = new PollType();
			pt.setPollTypeLabelid(26);
			pt.setIsNumericAnswer("no");
			pt.setPollTypesId(1);
			pollTypesList.add(pt);
			
			pt = new PollType();
			pt.setPollTypeLabelid(27);
			pt.setIsNumericAnswer("yes");
			pt.setPollTypesId(2);
			pollTypesList.add(pt);
			
		} catch (Exception err){
			log.error("[getPollTypeList]",err);
		}
		return pollTypesList;
	}
	
	public int vote(int pollvalue,int pollTypeId){
		int returnVal=0;
		try {
			IConnection current = Red5.getConnectionLocal();
			RoomClient rc = Application.getClientList().get(current.getClient().getId());
			
			if (rc==null){
				log.error("RoomClient IS NULL for ClientID: "+current.getClient().getId());
				return -1;
			}
			//get Poll
			RoomPoll roomP = pollList.get(rc.getRoom_id());
			
			if (roomP==null){
				log.error("POLL IS NULL for RoomId: "+rc.getRoom_id());
				return -1;
			}
			
			log.debug("vote: "+pollvalue+" "+pollTypeId+" "+roomP.getPollQuestion());
			
			//Check if this user has already voted
			if(this.hasVoted(roomP,rc.getStreamid())){
				log.debug("hasVoted: true");
				return -1;
			} else {
				log.debug("hasVoted: false");
				RoomPollAnswers rpA = new RoomPollAnswers();
				if (pollTypeId==1){
					log.debug("boolean");
					//Is boolean Question
					if (pollvalue==1){
						rpA.setAnswer(new Boolean(true));
					} else {
						rpA.setAnswer(new Boolean(false));
					}
				} else if(pollTypeId==2){
					log.debug("numeric");
					rpA.setPointList(pollvalue);
				}
				rpA.setVotedClients(rc);
				rpA.setVoteDate(new Date());
				roomP.getRoomPollAnswerList().add(rpA);
				return 1;
			}		
		} catch (Exception err){
			log.error("vote");
			log.error("[vote]",err);
		}
		return returnVal;
	}
	
	private boolean hasVoted(RoomPoll roomP,String streamid) throws Exception{
		List<RoomPollAnswers> answerList = roomP.getRoomPollAnswerList();
		Iterator<RoomPollAnswers> iter = answerList.iterator();
		log.debug("hasVoted: "+streamid);
		while (iter.hasNext()){
			RoomPollAnswers rpA = iter.next();
			RoomClient rc = rpA.getVotedClients();
			if (rc.getStreamid().equals(streamid)){
				return true;
			}
		}
		return false;
	}
	
	public RoomPoll getVotes(){
		try {
			IConnection current = Red5.getConnectionLocal();
			RoomClient rc = Application.getClientList().get(current.getClient().getId());
			
			//get Poll
			return pollList.get(rc.getRoom_id());	
		} catch (Exception err) {
			log.error("[getVotes]",err);
		}
		return null;
	}
	
	public RoomPoll getPoll(){
		try {
			IConnection current = Red5.getConnectionLocal();
			RoomClient rc = Application.getClientList().get(current.getClient().getId());
			
			//get Poll
			return pollList.get(rc.getRoom_id());
		} catch (Exception err) {
			log.error("[getPoll]",err);
		}
		return null;
	}
	
	public int checkHasVoted(){
		try {
			IConnection current = Red5.getConnectionLocal();
			RoomClient rc = Application.getClientList().get(current.getClient().getId());
			
			//get Poll
			RoomPoll roomP = pollList.get(rc.getRoom_id());
			
			if (roomP!=null){
				log.debug("checkHasVoted: "+roomP.getPollQuestion());
				//Check if this user has already voted
				if(this.hasVoted(roomP,rc.getStreamid())){
					return -1;
				} else {
					return 1;
				}
			} else {
				return -2;
			}
		} catch (Exception err){
			log.error("checkHasVoted");
			log.error("[checkHasVoted]",err);
		}
		return 0;
	}
	
}
