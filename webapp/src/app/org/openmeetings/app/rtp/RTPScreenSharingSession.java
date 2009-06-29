package org.openmeetings.app.rtp;

import org.openmeetings.app.hibernate.beans.rooms.Rooms;
import org.openmeetings.app.hibernate.beans.user.Users;

/**
 * represents a ScreenSharingSession within Conference
 * @author o.becherer
 *
 */
public class RTPScreenSharingSession {
	
	/** User sharing his Desktop */
	private Users sharingUser = null;
	
	/** Ip Address of sharing user */
	private  String sharingIpAddress = "127.0.0.1";
	
	/** RTP Port incoming(Port the Shareres Client sends on)*/
	private  int incomingRTPPort = 0;
	
	/** RTP Port outgoing (Port on which the RTP Stream is spread to single clients*/
	private  int outgoingRTPPort = 0;
	
	/** Stream Width */
	private  int streamWidth = 1024;
	
	/** StreamHeight */
	private  int streamHeight = 768;
	
	/** ConferenceRoom */
	private Rooms room = null;
	
	/** JpegQuality */
	private  float jpegQuality = 1f;

	public Users getSharingUser() {
		return sharingUser;
	}

	public void setSharingUser(Users sharingUser) {
		this.sharingUser = sharingUser;
	}

	public String getSharingIpAddress() {
		return sharingIpAddress;
	}

	public void setSharingIpAddress(String sharingIpAddress) {
		this.sharingIpAddress = sharingIpAddress;
	}

	public int getIncomingRTPPort() {
		return incomingRTPPort;
	}

	public void setIncomingRTPPort(int incomingRTPPort) {
		this.incomingRTPPort = incomingRTPPort;
	}

	public int getOutgoingRTPPort() {
		return outgoingRTPPort;
	}

	public void setOutgoingRTPPort(int outgoingRTPPort) {
		this.outgoingRTPPort = outgoingRTPPort;
	}

	public int getStreamWidth() {
		return streamWidth;
	}

	public void setStreamWidth(int streamWidth) {
		this.streamWidth = streamWidth;
	}

	public int getStreamHeight() {
		return streamHeight;
	}

	public void setStreamHeight(int streamHeight) {
		this.streamHeight = streamHeight;
	}

	public Rooms getRoom() {
		return room;
	}

	public void setRoom(Rooms room) {
		this.room = room;
	}

	public float getJpegQuality() {
		return jpegQuality;
	}

	public void setJpegQuality(float jpegQuality) {
		this.jpegQuality = jpegQuality;
	}
	
	
	
	
}
