package org.openmeetings.app.data.record;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmeetings.app.data.record.dao.RecordingConversionJobDaoImpl;
import org.openmeetings.app.data.record.dao.RecordingDaoImpl;
import org.openmeetings.app.data.record.dao.WhiteBoardEventDaoImpl;
import org.openmeetings.app.hibernate.beans.recording.Recording;
import org.openmeetings.app.hibernate.beans.recording.RecordingConversionJob;
import org.openmeetings.app.hibernate.beans.recording.WhiteBoardEvent;
import org.openmeetings.app.remote.Application;
import org.openmeetings.app.remote.StreamService;
import org.openmeetings.utils.math.CalendarPatterns;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class WhiteboardConvertionJobManager {
	
	//This is the amount of time the Conversion Job will create an SVN
	//So 200 Milliseconds == 5 Images per second
	private static Long numberOfMilliseconds = 200L;
	
	private static boolean isRunning = false;

	private static final Log log = LogFactory.getLog(WhiteboardConvertionJobManager.class);

	private WhiteboardConvertionJobManager() {
	}

	private static WhiteboardConvertionJobManager instance = null;

	public static synchronized WhiteboardConvertionJobManager getInstance() {
		if (instance == null) {
			instance = new WhiteboardConvertionJobManager();
		}

		return instance;
	}
	
	public synchronized void initJobs() {
		try {
			if (!isRunning) {
				isRunning = true;
				
				List<Recording> recordingsNonConversionStarted = RecordingDaoImpl.getInstance().getRecordingWhiteboardToConvert();
	
				for (Recording recording : recordingsNonConversionStarted) {
					recording.setWhiteBoardConverted(true);
					RecordingDaoImpl.getInstance().updateRecording(recording);
				}
				//log.debug("initJobs: "+recordingsNonConversionStarted.size());
				
				for (Recording recording : recordingsNonConversionStarted) {
					
					RecordingConversionJob recordingConversionJob = new RecordingConversionJob();
					recordingConversionJob.setRecording(recording);
					recordingConversionJob.setStarted(new Date());
					recordingConversionJob.setEndTimeInMilliSeconds(0L);
					recordingConversionJob.setImageNumber(0L);
					recordingConversionJob.setCurrentWhiteBoardAsXml("");
					
					RecordingConversionJobDaoImpl.getInstance().addRecordingConversionJob(recordingConversionJob);
					
				}
				
				
				processJobs();
				
				
				isRunning = false;
			} else {
				
				log.debug("CANNOT PROCESS WAIT FOR FREE SLOT");
				
			}
		} catch (Exception err) {
			log.error("[initJobs]",err);
		}
	}

	public synchronized void processJobs() {
		try {
			
			List<RecordingConversionJob> listOfConversionJobs = RecordingConversionJobDaoImpl.getInstance().getRecordingConversionJobs();
			
			log.debug("processJobs: "+listOfConversionJobs.size());
			
			for (RecordingConversionJob recordingConversionJob : listOfConversionJobs) {
				
				//log.debug("TIME INITIAL : "+recordingConversionJob.getEndTimeInMilliSeconds());
				
				if (recordingConversionJob.getEndTimeInMilliSeconds().equals(0L)) {
					
					log.debug("DRAW INITIAL IMAGE");
					
					XStream xStream_temp = new XStream(new XppDriver());
					xStream_temp.setMode(XStream.NO_REFERENCES);
					
					String roomRecordingInXML = recordingConversionJob.getRecording().getRoomRecording().getInitwhiteboardvarsInXml();
					
					Map initWhiteBoardObjects = (Map) xStream_temp.fromXML(roomRecordingInXML);
					
					this.generateFileAsSVG(initWhiteBoardObjects, roomRecordingInXML, recordingConversionJob);
			       
				} else {
					
					log.debug("DRAW NEXT IMAGE");
					
					if (recordingConversionJob.getRecording().getDuration() >= recordingConversionJob.getEndTimeInMilliSeconds()) {
					
						List<WhiteBoardEvent> whiteBoardEventList = WhiteBoardEventDaoImpl.getInstance().
								getWhiteboardEventsInRange(
										recordingConversionJob.getEndTimeInMilliSeconds(), 
										recordingConversionJob.getEndTimeInMilliSeconds()+(numberOfMilliseconds-1), 
										recordingConversionJob.getRecording().getRoomRecording().getRoomrecordingId());
						
						
						log.debug("whiteBoardEventList SIZE "+whiteBoardEventList.size());
						
						XStream xStream_temp = new XStream(new XppDriver());
						xStream_temp.setMode(XStream.NO_REFERENCES);
						
						String roomRecordingInXML = recordingConversionJob.getCurrentWhiteBoardAsXml();
						Map whiteBoardObjects = (Map) xStream_temp.fromXML(roomRecordingInXML);
							
						//Do simulate Whiteboard Events in Temp Object
						
						for (WhiteBoardEvent whiteBoardEvent : whiteBoardEventList) {
							
							log.debug("whiteBoardEvent: "+whiteBoardEvent.getStarttime());
							
							XStream xStream_temp_action = new XStream(new XppDriver());
							xStream_temp_action.setMode(XStream.NO_REFERENCES);
							
							Map actionObj = (Map) xStream_temp_action.fromXML(whiteBoardEvent.getAction());
							
							log.debug("whiteBoardEvent: "+actionObj);
							
							Date dateOfEvent = (Date) actionObj.get(1);
							String action = actionObj.get(2).toString();	
							Map actionObject = (Map) actionObj.get(3);
							
							log.debug("action: "+action);
							
							if (action.equals("draw") || action.equals("redo")){
								
								//log.debug(actionObject);
								//log.debug(actionObject.size()-1);
								//log.debug(actionObject.get(actionObject.size()-1));
								
								String objectOID = actionObject.get(actionObject.size()-1).toString();
								log.debug("objectOID: "+objectOID);
								whiteBoardObjects.put(objectOID, actionObject);
							} else if (action.equals("clear")) {
								whiteBoardObjects = new HashMap<String,Map>();
							} else if (action.equals("delete") || action.equals("undo")) {
								String objectOID = actionObject.get(actionObject.size()-1).toString();
								log.debug("removal of objectOID: "+objectOID);
								whiteBoardObjects.remove(objectOID);
							} else if (action.equals("size") || action.equals("editProp") 
									|| action.equals("editText")) {
								String objectOID = actionObject.get(actionObject.size()-1).toString();
								//Map roomItem = (Map) whiteBoardObjects.get(objectOID);
								whiteBoardObjects.put(objectOID, actionObject);
								
							} else {
								log.warn("Unkown Type: "+action+" actionObject: "+actionObject);
							}
							
						}
						
						XStream xStream_temp_store = new XStream(new XppDriver());
						xStream_temp_store.setMode(XStream.NO_REFERENCES);
						String roomRecordingInXMLToSave = xStream_temp_store.toXML(whiteBoardObjects);
						
						this.generateFileAsSVG(whiteBoardObjects, roomRecordingInXMLToSave, recordingConversionJob);
					
					} else {
						
						log.debug("THIS FILE IS PROCESSED: "+recordingConversionJob.getRecordingConversionJobId());
						
					}
				}
				
			}
			
		} catch (Exception err) {
			log.error("[processJobs]",err);
		}
	}
	
	private void generateFileAsSVG(Map whiteBoardObjects, String roomRecordingInXML, RecordingConversionJob recordingConversionJob) throws Exception {
		
		// Get a DOMImplementation.
        DOMImplementation domImpl =
            GenericDOMImplementation.getDOMImplementation();

        // Create an instance of org.w3c.dom.Document.
        //String svgNS = "http://www.w3.org/2000/svg";
        String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;

        Document document = domImpl.createDocument(svgNS, "svg", null);
        
        // Get the root element (the 'svg' element).
        Element svgRoot = document.getDocumentElement();

        
        // Set the width and height attributes on the root 'svg' element.
        svgRoot.setAttributeNS(null, "width", ""+660);
        svgRoot.setAttributeNS(null, "height", ""+620);
        

        // Create an instance of the SVG Generator.
        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
        
        svgGenerator = WhiteboardMapToSVG.getInstance().convertMapToSVG(svgGenerator, whiteBoardObjects);
		
        // Finally, stream out SVG to the standard output using
        // UTF-8 encoding.
        boolean useCSS = true; // we want to use CSS style attributes
        //Writer out = new OutputStreamWriter(System.out, "UTF-8");
        
       //log.debug(out.toString());
        
//       String firstImageName = this.generateFileName(recordingConversionJob.getRecordingConversionJobId(), fileNumber);
//       log.debug("Write File To: "+firstImageName);
//       
//       FileWriter fileWriter = new FileWriter(firstImageName);
//       
//       svgGenerator.stream(fileWriter, useCSS);
       
//       StringWriter stringWriter = new StringWriter();
//       
//       svgGenerator.stream(stringWriter, useCSS);
//       
//       log.debug("stringWriter"+stringWriter.toString());

		String firstImageName = this.generateSVGFileDebug(recordingConversionJob.getRecordingConversionJobId(), recordingConversionJob.getImageNumber());
		log.debug("Write File To: " + firstImageName);

		FileWriter fileWriter = new FileWriter(firstImageName);
		svgGenerator.stream(fileWriter, useCSS);

		recordingConversionJob.setEndTimeInMilliSeconds(recordingConversionJob.getEndTimeInMilliSeconds() + numberOfMilliseconds);
		recordingConversionJob.setCurrentWhiteBoardAsXml(roomRecordingInXML);
		recordingConversionJob.setImageNumber(recordingConversionJob.getImageNumber()+1);
		RecordingConversionJobDaoImpl.getInstance().updateRecordingConversionJobs(recordingConversionJob);
       
	}
	
	private String generateSVGFileDebug(Long conversionJobId, Long imageNumber) throws Exception {
       String recordingRootDir  = "/Users/swagner/Documents/work/red5_distros/red5_r3200_snapshot/webapps/openmeetings/test/";
       
       String recordingFileDir = recordingRootDir + File.separatorChar + conversionJobId;
       File recordingFileDirFolder = new File(recordingFileDir);
       if (!recordingFileDirFolder.exists()) {
    	   recordingFileDirFolder.mkdir();
       }
       Double numberOfFolder = Math.floor(imageNumber / 100);
       String folderDir = ""+numberOfFolder.intValue();
       
       String batchFileSVGDir = recordingFileDir + File.separatorChar + folderDir;
       File recordingBatchFileDirFolder = new File(batchFileSVGDir);
       if (!recordingBatchFileDirFolder.exists()) {
    	   recordingBatchFileDirFolder.mkdir();
       } 
       
       return batchFileSVGDir + File.separatorChar + imageNumber + ".svg";
	}
	
	private String generateSVGFileName(Long conversionJobId, Long fileNumber) throws Exception {
		String recordingRootDir = Application.webAppPath + File.separatorChar + "upload" + File.separatorChar + StreamService.folderForRecordings;
       File recordingRootDirFolder = new File(recordingRootDir);
       if (!recordingRootDirFolder.exists()) {
    	   recordingRootDirFolder.mkdir();
       }
       
       String recordingFileDir = recordingRootDir + File.separatorChar + conversionJobId;
       File recordingFileDirFolder = new File(recordingFileDir);
       if (!recordingFileDirFolder.exists()) {
    	   recordingFileDirFolder.mkdir();
       }
       
       return recordingFileDir + File.separatorChar + "record+" + fileNumber + ".svg";
	}
	
}
