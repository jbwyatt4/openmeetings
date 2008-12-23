package org.openmeetings.app.data.record;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmeetings.app.data.record.dao.RecordingConversionJobDaoImpl;
import org.openmeetings.app.data.record.dao.RecordingDaoImpl;
import org.openmeetings.app.hibernate.beans.recording.Recording;
import org.openmeetings.app.hibernate.beans.recording.RecordingConversionJob;
import org.openmeetings.app.remote.Application;
import org.openmeetings.app.remote.StreamService;
import org.openmeetings.utils.math.CalendarPatterns;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class WhiteboardConvertionJobManager {
	
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
				log.debug("initJobs: "+recordingsNonConversionStarted.size());
				
				for (Recording recording : recordingsNonConversionStarted) {
					
					RecordingConversionJob recordingConversionJob = new RecordingConversionJob();
					recordingConversionJob.setRecording(recording);
					recordingConversionJob.setStarted(new Date());
					recordingConversionJob.setEndTimeInMilliSeconds(0L);
					recordingConversionJob.setCurrentWhiteBoardAsXml("");
					
					RecordingConversionJobDaoImpl.getInstance().addRecordingConversionJob(recordingConversionJob);
					
				}
				
				
				//processJobs();
				
				
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
				
				log.debug("TIM INITIAL : "+recordingConversionJob.getEndTimeInMilliSeconds());
				
				if (recordingConversionJob.getEndTimeInMilliSeconds().equals(0L)) {
					
					log.debug("DRAW INITIAL IMAGE");
					
					XStream xStream_temp = new XStream(new XppDriver());
					xStream_temp.setMode(XStream.NO_REFERENCES);
					
					Map initWhiteBoardObjects = (Map) xStream_temp.fromXML(recordingConversionJob.getRecording().
															getRoomRecording().getInitwhiteboardvarsInXml());
					
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
			        
			        svgGenerator = WhiteboardMapToSVG.getInstance().convertMapToSVG(svgGenerator, initWhiteBoardObjects);
					
			        // Finally, stream out SVG to the standard output using
			        // UTF-8 encoding.
			        boolean useCSS = true; // we want to use CSS style attributes
			        //Writer out = new OutputStreamWriter(System.out, "UTF-8");
			        
			       //log.debug(out.toString());
			       
			       String recordingRootDir = Application.webAppPath + File.separatorChar + StreamService.folderForRecordings;
			       File recordingRootDirFolder = new File(recordingRootDir);
			       if (!recordingRootDirFolder.exists()) {
			    	   recordingRootDirFolder.mkdir();
			       }
			       
			       String recordingFileDir = recordingRootDir + File.separatorChar + recordingConversionJob.getRecordingConversionJobId();
			       File recordingFileDirFolder = new File(recordingFileDir);
			       if (!recordingFileDirFolder.exists()) {
			    	   recordingFileDirFolder.mkdir();
			       }
			       
			       String firstImageName = recordingFileDir + File.separatorChar + "record0.svg";
			       FileWriter fileWriter = new FileWriter(firstImageName);
			        
			       svgGenerator.stream(fileWriter, useCSS);
			       
			       recordingConversionJob.setEndTimeInMilliSeconds(1000L);
			       
			       RecordingConversionJobDaoImpl.getInstance().updateRecordingConversionJobs(recordingConversionJob);
			       
				} else {
					
					
					
				}
				
			}
			
		} catch (Exception err) {
			log.error("[processJobs]",err);
		}
	}
	
}
