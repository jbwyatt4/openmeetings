package org.xmlcrmred5.videobeans;

import java.util.List;
import java.util.Date;
import org.xmlcrmred5.videobeans.RoomClient;

public class RoomPoll {
	
	Long roomPollId;
	String roomScopeName;
	RoomClient createdBy;
	String pollQuestion;
	String clientdomain;
	List<RoomPollAnswers> roomPollAnswerList;
	Date pollDate;
	Integer pollTypeId;
	/**
	 * @return the createdBy
	 */
	public RoomClient getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(RoomClient createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the pollDate
	 */
	public Date getPollDate() {
		return pollDate;
	}
	/**
	 * @param pollDate the pollDate to set
	 */
	public void setPollDate(Date pollDate) {
		this.pollDate = pollDate;
	}
	/**
	 * @return the pollQuestion
	 */
	public String getPollQuestion() {
		return pollQuestion;
	}
	/**
	 * @param pollQuestion the pollQuestion to set
	 */
	public void setPollQuestion(String pollQuestion) {
		this.pollQuestion = pollQuestion;
	}
	/**
	 * @return the roomPollAnswerList
	 */
	public List<RoomPollAnswers> getRoomPollAnswerList() {
		return roomPollAnswerList;
	}
	/**
	 * @param roomPollAnswerList the roomPollAnswerList to set
	 */
	public void setRoomPollAnswerList(List<RoomPollAnswers> roomPollAnswerList) {
		this.roomPollAnswerList = roomPollAnswerList;
	}
	/**
	 * @return the roomPollId
	 */
	public Long getRoomPollId() {
		return roomPollId;
	}
	/**
	 * @param roomPollId the roomPollId to set
	 */
	public void setRoomPollId(Long roomPollId) {
		this.roomPollId = roomPollId;
	}
	/**
	 * @return the roomScopeName
	 */
	public String getRoomScopeName() {
		return roomScopeName;
	}
	/**
	 * @param roomScopeName the roomScopeName to set
	 */
	public void setRoomScopeName(String roomScopeName) {
		this.roomScopeName = roomScopeName;
	}
	/**
	 * @return the pollTypeId
	 */
	public Integer getPollTypeId() {
		return pollTypeId;
	}
	/**
	 * @param pollTypeId the pollTypeId to set
	 */
	public void setPollTypeId(Integer pollTypeId) {
		this.pollTypeId = pollTypeId;
	}
	public String getClientdomain() {
		return clientdomain;
	}
	public void setClientdomain(String clientdomain) {
		this.clientdomain = clientdomain;
	}
		

}
