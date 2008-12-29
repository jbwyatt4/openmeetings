package org.openmeetings.app.documents;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openmeetings.app.data.basic.Configurationmanagement;
import org.openmeetings.app.remote.Application;
import org.openmeetings.utils.math.CalendarPatterns;

public class GenerateSWF {
	
	private static final Logger log = LoggerFactory.getLogger(GenerateSWF.class);

	private static GenerateSWF instance;

	private GenerateSWF() {}

	public static synchronized GenerateSWF getInstance() {
		if (instance == null) {
			instance = new GenerateSWF();
		}
		return instance;
	}
	
	public HashMap<String,Object> generateSWF(String current_dir, String originalFolder, String destinationFolder, String fileNamePure) {
		HashMap<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("process", "generateSWF");		
		try {
			
			//Init variables
			String[] cmd;
			String executable_fileName = "";	
			String pathToSWFTools = Configurationmanagement.getInstance().getConfKey(3,"swftools_path").getConf_value();
			//If SWFTools Path is not blank a File.seperator at the end of the path is needed
			if(!pathToSWFTools.equals("") && !pathToSWFTools.endsWith(File.separator)){
				pathToSWFTools = pathToSWFTools + File.separator;
			}
			

			//If no Windows Platform
			if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") == -1) {
				String runtimeFile = "swfconverter.sh";
				executable_fileName = Application.batchFileFir+"SWFCONVERT_" 
						+ CalendarPatterns.getTimeForStreamId(new Date()) +"_"+ runtimeFile;
		
				cmd = new String[1];
				cmd[0] = executable_fileName;
			} else {
				String runtimeFile = "swfconverter.bat";
				executable_fileName = Application.batchFileFir+"SWFCONVERT_" 
						+ CalendarPatterns.getTimeForStreamId(new Date()) +"_"+ runtimeFile;
				
				cmd = new String[4];
				cmd[0] = "cmd.exe";
				cmd[1] = "/C";
				cmd[2] = "start";
				cmd[3] = executable_fileName;
			}
			log.debug("executable_fileName: "+executable_fileName);
			
			//Create the Content of the Converter Script (.bat or .sh File)
			String fileContent = pathToSWFTools + "pdf2swf"
					+ " " + "\"" + originalFolder + fileNamePure + ".pdf\""
					+ " " + "\"" + destinationFolder + fileNamePure+".swf\""
					+ Application.lineSeperator + "exit";
				
			//execute the Script
			FileOutputStream fos = new FileOutputStream(executable_fileName);
			fos.write(fileContent.getBytes());
			fos.close();
			
			//make new shell script executable
			//in JAVA6 this can be done directly through the api
			if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") == -1) {
				MakeExectuable.getInstance().setExecutable(executable_fileName);
			}
			
			Runtime rt = Runtime.getRuntime();			
			
			for (int i=0;i<cmd.length;i++){
				log.debug("cmd: "+cmd[i]);
			}
			
			returnMap.put("command", cmd.toString());
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
	
	/**
	 * This Function generate a SWF
	 * @param images
	 * @param outputfile
	 * @param fps
	 * @return
	 */
	public HashMap<String,Object> generateSWFByImages(List<String> images, String outputfile, int fps) {
		HashMap<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("process", "generateSWFByImages");		
		try {
			
			//Init variables
			String[] cmd;
			String executable_fileName = "";	
			String pathToSWFTools = Configurationmanagement.getInstance().getConfKey(3,"swftools_path").getConf_value();
			//If SWFTools Path is not blank a File.seperator at the end of the path is needed
			if(!pathToSWFTools.equals("") && !pathToSWFTools.endsWith(File.separator)){
				pathToSWFTools = pathToSWFTools + File.separator;
			}
			

			//If no Windows Platform
			if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") == -1) {
				String runtimeFile = "swfconverter.sh";
				executable_fileName = Application.batchFileFir+"SWFIMAGECONVERT_" 
						+ CalendarPatterns.getTimeForStreamId(new Date()) +"_"+ runtimeFile;
		
				cmd = new String[1];
				cmd[0] = executable_fileName;
			} else {
				String runtimeFile = "swfconverter.bat";
				executable_fileName = Application.batchFileFir+"SWFIMAGECONVERT_" 
						+ CalendarPatterns.getTimeForStreamId(new Date()) +"_"+ runtimeFile;
				
				cmd = new String[4];
				cmd[0] = "cmd.exe";
				cmd[1] = "/C";
				cmd[2] = "start";
				cmd[3] = executable_fileName;
			}
			log.debug("executable_fileName: "+executable_fileName);
			
			//Create the Content of the Converter Script (.bat or .sh File)
			String fileContent = pathToSWFTools + "png2swf"
					+ " -o " + "\"" + outputfile + "\""
					+ " -r " + fps
					+ " -z ";
			
			for (Iterator<String> iter = images.iterator();iter.hasNext();) {
				String fileName = iter.next();
				fileContent += " " + "\"" + fileName +"\"";
			}
			
			fileContent += Application.lineSeperator + "exit";
				
			//execute the Script
			FileOutputStream fos = new FileOutputStream(executable_fileName);
			fos.write(fileContent.getBytes());
			fos.close();
			
			//make new shell script executable
			//in JAVA6 this can be done directly through the api
			if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") == -1) {
				MakeExectuable.getInstance().setExecutable(executable_fileName);
			}
			
			Runtime rt = Runtime.getRuntime();			
			
			for (int i=0;i<cmd.length;i++){
				log.debug("cmd: "+cmd[i]);
			}
			
			returnMap.put("command", cmd.toString());
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
	
	/**
	 * Combines a bunch of SWFs to one swf by concatenate
	 * @param swfs
	 * @param outputswf
	 * @param fps
	 * @return
	 */
	public HashMap<String,Object> generateSWFByCombine(List<String> swfs, String outputswf, int fps) {
		HashMap<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("process", "generateSWFByImages");		
		try {
			
			//Init variables
			String[] cmd;
			String executable_fileName = "";	
			String pathToSWFTools = Configurationmanagement.getInstance().getConfKey(3,"swftools_path").getConf_value();
			//If SWFTools Path is not blank a File.seperator at the end of the path is needed
			if(!pathToSWFTools.equals("") && !pathToSWFTools.endsWith(File.separator)){
				pathToSWFTools = pathToSWFTools + File.separator;
			}
			

			//If no Windows Platform
			if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") == -1) {
				String runtimeFile = "swfconverter.sh";
				executable_fileName = Application.batchFileFir+"SWFCOMBINE_" 
						+ CalendarPatterns.getTimeForStreamId(new Date()) +"_"+ runtimeFile;
		
				cmd = new String[1];
				cmd[0] = executable_fileName;
			} else {
				String runtimeFile = "swfconverter.bat";
				executable_fileName = Application.batchFileFir+"SWFIMAGECONVERT_" 
						+ CalendarPatterns.getTimeForStreamId(new Date()) +"_"+ runtimeFile;
				
				cmd = new String[4];
				cmd[0] = "cmd.exe";
				cmd[1] = "/C";
				cmd[2] = "start";
				cmd[3] = executable_fileName;
			}
			log.debug("executable_fileName: "+executable_fileName);
			
			//Create the Content of the Converter Script (.bat or .sh File)
			String fileContent = pathToSWFTools + "swfcombine"
					+ " -o " + "\"" + outputswf + "\""
					+ " -r " + fps
					+ " -z -a ";
			
			for (Iterator<String> iter = swfs.iterator();iter.hasNext();) {
				String fileName = iter.next();
				fileContent += " " + "\"" + fileName +"\"";
			}
			
			fileContent += Application.lineSeperator + "exit";
				
			//execute the Script
			FileOutputStream fos = new FileOutputStream(executable_fileName);
			fos.write(fileContent.getBytes());
			fos.close();
			
			//make new shell script executable
			//in JAVA6 this can be done directly through the api
			if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") == -1) {
				MakeExectuable.getInstance().setExecutable(executable_fileName);
			}
			
			Runtime rt = Runtime.getRuntime();			
			
			for (int i=0;i<cmd.length;i++){
				log.debug("cmd: "+cmd[i]);
			}
			
			returnMap.put("command", cmd.toString());
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
