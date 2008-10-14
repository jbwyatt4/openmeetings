package org.openmeetings.app.hibernate.beans.recording;

import java.util.Date;
import java.util.List;

import org.openmeetings.app.conference.videobeans.RoomClient;
import org.openmeetings.app.hibernate.beans.rooms.Rooms;

public class RoomRecording {

	private String conferenceType;
	private Rooms room_setup;
	private String roomRecordingsTableString;
	private String comment;
	private Object initwhiteboardvars;
	private String recordingName;
	private Date starttime;
	private RoomClient startedby;
	private List<RecordingClient> roomClients;
	private List<RoomStream> roomStreams;
	private List<WhiteBoardEvent> whiteboard;
	private List<ChatvaluesEvent> chatvalues;
	private Date endtime;
	private RoomClient enduser;
	private String recordname;
	
	public String getConferenceType() {
		return conferenceType;
	}
	public void setConferenceType(String conferenceType) {
		this.conferenceType = conferenceType;
	}
	public Rooms getRoom_setup() {
		return room_setup;
	}
	public void setRoom_setup(Rooms room_setup) {
		this.room_setup = room_setup;
	}
	public String getRoomRecordingsTableString() {
		return roomRecordingsTableString;
	}
	public void setRoomRecordingsTableString(String roomRecordingsTableString) {
		this.roomRecordingsTableString = roomRecordingsTableString;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Object getInitwhiteboardvars() {
		return initwhiteboardvars;
	}
	public void setInitwhiteboardvars(Object initwhiteboardvars) {
		this.initwhiteboardvars = initwhiteboardvars;
	}
	public String getRecordingName() {
		return recordingName;
	}
	public void setRecordingName(String recordingName) {
		this.recordingName = recordingName;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public RoomClient getStartedby() {
		return startedby;
	}
	public void setStartedby(RoomClient startedby) {
		this.startedby = startedby;
	}
	public List<RecordingClient> getRoomClients() {
		return roomClients;
	}
	public void setRoomClients(List<RecordingClient> roomClients) {
		this.roomClients = roomClients;
	}
	public List<RoomStream> getRoomStreams() {
		return roomStreams;
	}
	public void setRoomStreams(List<RoomStream> roomStreams) {
		this.roomStreams = roomStreams;
	}
	public List<WhiteBoardEvent> getWhiteboard() {
		return whiteboard;
	}
	public void setWhiteboard(List<WhiteBoardEvent> whiteboard) {
		this.whiteboard = whiteboard;
	}
	public List<ChatvaluesEvent> getChatvalues() {
		return chatvalues;
	}
	public void setChatvalues(List<ChatvaluesEvent> chatvalues) {
		this.chatvalues = chatvalues;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public RoomClient getEnduser() {
		return enduser;
	}
	public void setEnduser(RoomClient enduser) {
		this.enduser = enduser;
	}
	public String getRecordname() {
		return recordname;
	}
	public void setRecordname(String recordname) {
		this.recordname = recordname;
	}
	
}
