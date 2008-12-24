package org.openmeetings.app.hibernate.beans.recording;

import java.util.Date;

/**
 * 
 * @hibernate.class table="recording_conversion_job"
 * lazy="false"
 *
 */
public class RecordingConversionJob {
	
	private long recordingConversionJobId;
	private Long imageNumber = 0L;
	private Recording recording;
	private Date started;
	private Date ended;
	private String currentWhiteBoardAsXml;
	private Long endTimeInMilliSeconds;
	
	/**
     * 
     * @hibernate.id
     *  column="recording_conversion_job_id"
     *  generator-class="increment"
     */
	public long getRecordingConversionJobId() {
		return recordingConversionJobId;
	}
	public void setRecordingConversionJobId(long recordingConversionJobId) {
		this.recordingConversionJobId = recordingConversionJobId;
	}
	
	/**
	 * @hibernate.many-to-one
	 * column = "recording_id"
	 * class = "org.openmeetings.app.hibernate.beans.recording.Recording"
	 * insert="true"
	 * update="true"
	 * outer-join="true"
	 * lazy="false"
     */		
	public Recording getRecording() {
		return recording;
	}
	public void setRecording(Recording recording) {
		this.recording = recording;
	}
	
	/**
     * @hibernate.property
     *  column="imagenumber"
     *  type="long"
     */
	public Long getImageNumber() {
		return imageNumber;
	}
	public void setImageNumber(Long imageNumber) {
		this.imageNumber = imageNumber;
	}
	
	/**
     * @hibernate.property
     *  column="started"
     *  type="java.util.Date"
     */
	public Date getStarted() {
		return started;
	}
	public void setStarted(Date started) {
		this.started = started;
	}

	/**
     * @hibernate.property
     *  column="ended"
     *  type="java.util.Date"
     */	
	public Date getEnded() {
		return ended;
	}
	public void setEnded(Date ended) {
		this.ended = ended;
	}
	
	/**
     * @hibernate.property
     *  column="currentwhiteboardasxml"
     *  type="text"
     */	
	public String getCurrentWhiteBoardAsXml() {
		return currentWhiteBoardAsXml;
	}
	public void setCurrentWhiteBoardAsXml(String currentWhiteBoardAsXml) {
		this.currentWhiteBoardAsXml = currentWhiteBoardAsXml;
	}
	
	/**
     * @hibernate.property
     *  column="endtimeinmilliseconds"
     *  type="long"
     */
	public Long getEndTimeInMilliSeconds() {
		return endTimeInMilliSeconds;
	}
	public void setEndTimeInMilliSeconds(Long endTimeInMilliSeconds) {
		this.endTimeInMilliSeconds = endTimeInMilliSeconds;
	}
	

}
