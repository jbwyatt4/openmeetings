package org.xmlcrm.app.conference.videobeans;

import java.util.Date;
import java.util.LinkedList;

public class RoomClient {
	 
	/*
	 * login name
	 */
	String username = "";
	
	/*
	 * a unique id
	 */
	String streamid = "";
	
	/*
	 * this indicated if two people are in the same room
	 * if they have the same userroom _and_ domain
	 * in public rooms the domain == 'public'
	 * in private rooms the domain is the organisation_id
	 * its necessary to check both cause otherwise its theoretically possible
	 * to make a room private _and_ public, then the room would have the same ID but a different domain
	 * 
	 */
	String userroom = "";
	String domain = "";
	
	/*
	 * true indicates that this user is Moderating
	 * in Events rooms (only 1 Video) this means that this user is currently 
	 * sharing its video/audio
	 * 
	 */
	Boolean isMod = false;
	Date connectedSince;
	String formatedDate;
	
	/*
	 * the color of the user, only needed in 4x4 Conference, in these rooms each user has its own
	 * color 
	 */
	String usercolor;
	Integer userpos;
	/*
	 * client IP
	 */
	String userip;
	/*
	 * client Port
	 */
	int userport;
	/*
	 * current room idd while conferencing
	 */
	Long room_id;
	
	Date roomEnter = null;
	
	/*
	 * some vars _not_ directly connected to the user-record from the database
	 * cause a user is not _forced_ to login he can also be an invited user, so user_id
	 * might be null or 0 even if somebody is already in a conference room
	 * 
	 */
	Long user_id = null;
	String firstname = "";
	String lastname = "";
	String mail;
	String lastLogin;
	String official_code;
	String picture_uri;
	String language = "";
	
	/*
	 * these vars are necessary to send notifications from the chatroom of a 
	 * conference to outside of the conference room
	 */
	Boolean isChatNotification = false;
	String chatUserroom = "";
	String chatDomain = "";
	
	/*
	 * avsettings can be:
	 * av - video and audio
	 * a - audio only
	 * v - video only
	 * n - no av only static Image
	 */
	String avsettings = "";
	
	String swfurl;
	Boolean isRecording = false;
	String roomRecordingName;
	
	public RoomClient() {
		super();
	}
	
	public void setUserObject(Long user_id, String username, String firstname, String lastname) {
		this.user_id = user_id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	/**
	 * @return the connectedSince
	 */
	public Date getConnectedSince() {
		return connectedSince;
	}
	/**
	 * @param connectedSince the connectedSince to set
	 */
	public void setConnectedSince(Date connectedSince) {
		this.connectedSince = connectedSince;
	}
	/**
	 * @return the isMod
	 */
	public Boolean getIsMod() {
		return isMod;
	}
	/**
	 * @param isMod the isMod to set
	 */
	public void setIsMod(Boolean isMod) {
		this.isMod = isMod;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the userroom
	 */
	public String getUserroom() {
		return userroom;
	}
	/**
	 * @param userroom the userroom to set
	 */
	public void setUserroom(String userroom) {
		this.userroom = userroom;
	}
	/**
	 * @return the streamid
	 */
	public String getStreamid() {
		return streamid;
	}
	/**
	 * @param streamid the streamid to set
	 */
	public void setStreamid(String streamid) {
		this.streamid = streamid;
	}
	
	/**
	 * @return the formatedDate
	 */
	public String getFormatedDate() {
		return formatedDate;
	}
	/**
	 * @param formatedDate the formatedDate to set
	 */
	public void setFormatedDate(String formatedDate) {
		this.formatedDate = formatedDate;
	}
	/**
	 * @return the usercolor
	 */
	public String getUsercolor() {
		return usercolor;
	}
	/**
	 * @param usercolor the usercolor to set
	 */
	public void setUsercolor(String usercolor) {
		this.usercolor = usercolor;
	}
	/**
	 * @return the userpos
	 */
	public Integer getUserpos() {
		return userpos;
	}
	/**
	 * @param userpos the userpos to set
	 */
	public void setUserpos(Integer userpos) {
		this.userpos = userpos;
	}
	/**
	 * @return the userip
	 */
	public String getUserip() {
		return userip;
	}
	/**
	 * @param userip the userip to set
	 */
	public void setUserip(String userip) {
		this.userip = userip;
	}
	/**
	 * @return the swfurl
	 */
	public String getSwfurl() {
		return swfurl;
	}
	/**
	 * @param swfurl the swfurl to set
	 */
	public void setSwfurl(String swfurl) {
		this.swfurl = swfurl;
	}
	/**
	 * @return the userport
	 */
	public int getUserport() {
		return userport;
	}
	/**
	 * @param userport the userport to set
	 */
	public void setUserport(int userport) {
		this.userport = userport;
	}
	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * @return the lastLogin
	 */
	public String getLastLogin() {
		return lastLogin;
	}
	/**
	 * @param lastLogin the lastLogin to set
	 */
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * @return the official_code
	 */
	public String getOfficial_code() {
		return official_code;
	}
	/**
	 * @param official_code the official_code to set
	 */
	public void setOfficial_code(String official_code) {
		this.official_code = official_code;
	}
	/**
	 * @return the picture_uri
	 */
	public String getPicture_uri() {
		return picture_uri;
	}
	/**
	 * @param picture_uri the picture_uri to set
	 */
	public void setPicture_uri(String picture_uri) {
		this.picture_uri = picture_uri;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Long getRoom_id() {
		return room_id;
	}
	public void setRoom_id(Long room_id) {
		this.room_id = room_id;
	}

	public Date getRoomEnter() {
		return roomEnter;
	}
	public void setRoomEnter(Date roomEnter) {
		this.roomEnter = roomEnter;
	}

	public Boolean getIsChatNotification() {
		return isChatNotification;
	}
	public void setIsChatNotification(Boolean isChatNotification) {
		this.isChatNotification = isChatNotification;
	}

	public String getChatUserroom() {
		return chatUserroom;
	}
	public void setChatUserroom(String chatUserroom) {
		this.chatUserroom = chatUserroom;
	}

	public String getChatDomain() {
		return chatDomain;
	}
	public void setChatDomain(String chatDomain) {
		this.chatDomain = chatDomain;
	}

	public Boolean getIsRecording() {
		return isRecording;
	}
	public void setIsRecording(Boolean isRecording) {
		this.isRecording = isRecording;
	}

	public String getRoomRecordingName() {
		return roomRecordingName;
	}
	public void setRoomRecordingName(String roomRecordingName) {
		this.roomRecordingName = roomRecordingName;
	}

	public String getAvsettings() {
		return avsettings;
	}
	public void setAvsettings(String avsettings) {
		this.avsettings = avsettings;
	}

}
