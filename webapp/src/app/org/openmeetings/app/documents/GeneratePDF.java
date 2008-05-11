package org.openmeetings.app.documents;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.transaction.util.FileHelper;
import org.openmeetings.app.remote.Application;
import org.openmeetings.utils.math.CalendarPatterns;

public class GeneratePDF {
	
	private static final Log log = LogFactory.getLog(GeneratePDF.class);
	
	private static GeneratePDF instance;

	private GeneratePDF() {}

	public static synchronized GeneratePDF getInstance() {
		if (instance == null) {
			instance = new GeneratePDF();
		}
		return instance;
	}
	
	public HashMap<String,HashMap> convertPDF(String current_dir, String fileName, String fileExt,
			String roomName, boolean fullProcessing, String completeName)
			throws Exception {
		
		HashMap<String,HashMap> returnError = new HashMap<String,HashMap>();
		
		HashMap<String,Object> processPDF = new HashMap<String,Object>();
		processPDF.put("process","processPDF");
		
		String working_imgdir = current_dir + "upload" + File.separatorChar + roomName
				+ File.separatorChar;
		String working_pptdir = current_dir + "uploadtemp" + File.separatorChar
				+ roomName + File.separatorChar;

		String fileFullPath = working_pptdir + fileName + fileExt;
		String destinationFolder = working_imgdir + fileName;

		File f = new File(destinationFolder + File.separatorChar);
		if (f.exists()) {
			int recursiveNumber = 0;
			String tempd = destinationFolder + "_" + recursiveNumber;
			while (f.exists()) {
				recursiveNumber++;
				tempd = destinationFolder + "_" + recursiveNumber;
				f = new File(tempd);

			}
			destinationFolder = tempd;
		}

		boolean b = f.mkdir();
		if (!b) {
			processPDF.put("error", "convertPDF + ERROR: Folder could not create " + f.getAbsolutePath());
			processPDF.put("exitValue",-1);
		} else {
			processPDF.put("exitValue",0);
		}
		returnError.put("processPDF", processPDF);
		
		String outputfolder = destinationFolder + File.separatorChar;
		destinationFolder = destinationFolder + File.separatorChar;

		log.debug("fullProcessing: "+fullProcessing);
		if (fullProcessing) {
			HashMap<String,Object> processOpenOffice = this.doConvertExec(current_dir, fileFullPath, destinationFolder,fileName);
			returnError.put("processOpenOffice", processOpenOffice);
			HashMap<String,Object> processThumb = GenerateThumbs.getInstance().generateBatchThumb(current_dir, destinationFolder + fileName + ".pdf", destinationFolder, 80);
			returnError.put("processThumb", processThumb);		
			HashMap<String,Object> processSWF = GenerateSWF.getInstance().generateSWF(current_dir, destinationFolder, destinationFolder, fileName);
			returnError.put("processSWF", processSWF);	
		} else {
			HashMap<String,Object> processThumb = GenerateThumbs.getInstance().generateBatchThumb(current_dir, fileFullPath, destinationFolder, 80);
			returnError.put("processThumb", processThumb);
			HashMap<String,Object> processSWF = GenerateSWF.getInstance().generateSWF(current_dir, (new File(fileFullPath)).getParentFile().getAbsolutePath()+File.separatorChar , destinationFolder, fileName);
			returnError.put("processSWF", processSWF);				
		}
				
		//now it should be completed so copy that file to the expected location
		File fileToBeMoved = new File(completeName + fileExt);
		File fileWhereToMove = new File(outputfolder+ fileName + fileExt);
		fileWhereToMove.createNewFile();
		FileHelper.moveRec(fileToBeMoved, fileWhereToMove);
		
		if (fullProcessing) {
			HashMap<String,Object> processXML = CreateLibraryPresentation.getInstance().generateXMLDocument(outputfolder, fileName + fileExt, fileName + ".pdf", fileName + ".swf");
			returnError.put("processXML", processXML);	
		} else {
			HashMap<String,Object> processXML = CreateLibraryPresentation.getInstance().generateXMLDocument(outputfolder, fileName + fileExt, null, fileName + ".swf");
			returnError.put("processXML", processXML);
		}
		
		return returnError;
	}	
	
	/**
	 * Generate PDF and thumbs (and swf)
	 * @param current_dir
	 * @param fileFullPath
	 * @param destinationFolder
	 * @param outputfile
	 * @return
	 */
	public HashMap<String,Object> doConvertExec(String current_dir , String fileFullPath, 
			String destinationFolder, String outputfile) {
		HashMap<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("process", "doConvertExec");				
		try {
			
			
			String[] cmd;
			String executable_fileName = "";
			
			//If no Windows Platform
			if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") == -1) {
				String runtimeFile = "jodconverter.sh";
				executable_fileName = Application.batchFileFir+"PDFCONVERT_" 
						+ CalendarPatterns.getTimeForStreamId(new Date()) +"_"+ runtimeFile;
		
				cmd = new String[1];
				cmd[0] = executable_fileName;
			} else {
				String runtimeFile = "jodconverter.bat";
				executable_fileName = Application.batchFileFir+"PDFCONVERT_" 
						+ CalendarPatterns.getTimeForStreamId(new Date()) +"_"+ runtimeFile;
				
				cmd = new String[4];
				cmd[0] = "cmd.exe";
				cmd[1] = "/C";
				cmd[2] = "start";
				cmd[3] = executable_fileName;
			}
			log.debug("executable_fileName: "+executable_fileName);
			
//			String runtimeFile = "jodconverter.bat";
//			String command = "cmd.exe /c start "+current_dir + "jod" + File.separatorChar
//				+ runtimeFile + " java " + fileFullPath + " "
//				+ destinationFolder + outputfile + ".pdf " + current_dir
//				+ "jod" + File.separatorChar;
//			
//			if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") == -1) {
//				runtimeFile = "jodconverter.sh";
//				command = current_dir + "jod" + File.separatorChar
//					+ runtimeFile + " java " + fileFullPath + " "
//					+ destinationFolder + outputfile + ".pdf " + current_dir
//					+ "jod" + File.separatorChar;				
//			}
			
			//Path to all JARs of JOD
			String jodClassPathFolder = current_dir + "jod" + File.separatorChar;
			
			//Create the Content of the Converter Script (.bat or .sh File)
			String fileContent = "java" +
					" " + "-cp" + " " +
					".:" + 
					"\"" + jodClassPathFolder + "commons-cli-1.0.jar\":" +
					"\"" + jodClassPathFolder + "commons-io-1.3.1.jar\":" +
					"\"" + jodClassPathFolder + "jodconverter-2.2.0.jar\":" +
					"\"" + jodClassPathFolder + "jodconverter-cli-2.2.0.jar\":" +
					"\"" + jodClassPathFolder + "juh-2.2.0.jar\":" +
					"\"" + jodClassPathFolder + "jurt-2.2.0.jar\":" +
					"\"" + jodClassPathFolder + "ridl-2.2.0.jar\":" +
					"\"" + jodClassPathFolder + "slf4j-api-1.4.0.jar\":" +
					"\"" + jodClassPathFolder + "slf4j-jdk14-1.4.0.jar\":" +
					"\"" + jodClassPathFolder + "unoil-2.2.0.jar\":" +
					"\"" + jodClassPathFolder + "xstream-1.2.2.jar\"" +
					" " + "-jar" +
					" " + "\"" + jodClassPathFolder +"jodconverter-cli-2.2.0.jar\"" +
					" " + "\"" + fileFullPath + "\"" +
					" " + "\"" + destinationFolder + outputfile + ".pdf\"" +
					Application.lineSeperator + "exit";
			
			
			//execute the Script
			FileOutputStream fos = new FileOutputStream(executable_fileName);
			fos.write(fileContent.getBytes());
			fos.close();
			
			//make new shell script executable
			//in JAVA6 this can be done directly through the api
			if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") == -1) {
				MakeExectuable.getInstance().setExecutable(executable_fileName);
			}
			
			log.debug("command: "+cmd);
			
			Runtime rt = Runtime.getRuntime();
			returnMap.put("command",cmd.toString());
			Process proc = rt.exec(cmd);
			InputStream stderr = proc.getErrorStream();
			InputStreamReader isr = new InputStreamReader(stderr);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			String error = "";
			while ((line = br.readLine()) != null)
				error += line;
			returnMap.put("error", error);
			int exitVal = proc.waitFor();
			returnMap.put("exitValue", exitVal);
			return returnMap;
		} catch (Throwable t) {
			t.printStackTrace();
			returnMap.put("error", t.getMessage());
			returnMap.put("exitValue", -1);
			return returnMap;
		}
	}	
	
}
