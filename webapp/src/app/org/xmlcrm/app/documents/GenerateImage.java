package org.xmlcrm.app.documents;

import java.util.HashMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.xmlcrm.app.data.basic.Configurationmanagement;
import org.xmlcrm.app.data.user.Usermanagement;
import org.xmlcrm.app.hibernate.beans.user.Users;

public class GenerateImage {

	private static GenerateImage instance;

	private GenerateImage() {}

	public static synchronized GenerateImage getInstance() {
		if (instance == null) {
			instance = new GenerateImage();
		}
		return instance;
	}



	public HashMap<String,HashMap> convertImage(String current_dir, String fileName, String fileExt, 
						    String roomName, String fileNameShort, 
						    boolean fullProcessing) throws Exception {
		
		HashMap<String,HashMap> returnMap = new HashMap<String,HashMap>();

		String working_imgdir = current_dir + "upload" + File.separatorChar + roomName + File.separatorChar;
		String working_pptdir = current_dir + "uploadtemp" + File.separatorChar + roomName + File.separatorChar;

		String fileFullPath = working_pptdir + fileName+ fileExt;

		File f = new File(working_imgdir + fileName + fileExt);
		if (f.exists()) {
			int recursiveNumber = 0;
			String tempd = fileName + "_" + recursiveNumber;
			while (f.exists()) {
				recursiveNumber++;
				tempd = fileName + "_" + recursiveNumber;
				f = new File(working_imgdir + tempd + fileExt);
			}
			fileName = tempd;
		}

		String destinationFile = working_imgdir + fileName;
		HashMap processJPG = this.convertSingleJpg(current_dir, fileFullPath, destinationFile);
		HashMap processThumb = GenerateThumbs.getInstance().generateThumb("_thumb_", current_dir, destinationFile, 50);
		
		returnMap.put("processJPG", processJPG);
		returnMap.put("processThumb", processThumb);
		
		//Delete old one
		File fToDelete = new File(fileFullPath);
		fToDelete.delete();

		return returnMap;
	}

	public HashMap<String,HashMap> convertImageUserProfile(String current_dir, String fileName, String fileExt, Long users_id, 
			String fileNameShort, boolean fullProcessing) throws Exception {
		
		HashMap<String,HashMap> returnMap = new HashMap<String,HashMap>();

		String working_imgdir = current_dir + "upload" + File.separatorChar + "profiles" + File.separatorChar + "profile_"+users_id + File.separatorChar;
		String working_pptdir = current_dir + "uploadtemp" + File.separatorChar + "profiles" + File.separatorChar + "profile_"+users_id + File.separatorChar;

		String fileFullPath = working_pptdir + fileName+fileExt;

		File f = new File(working_imgdir + fileName + fileExt);
		if (f.exists()) {
			int recursiveNumber = 0;
			String tempd = fileName + "_" + recursiveNumber;
			while (f.exists()) {
				recursiveNumber++;
				tempd = fileName + "_" + recursiveNumber;
				f = new File(working_imgdir + tempd + fileExt);
			}
			fileName = tempd;
		}

		String destinationFile = working_imgdir + fileName;
		HashMap<String,Object> processJPG = this.convertSingleJpg(current_dir, fileFullPath, destinationFile);
		
		HashMap<String,Object> processThumb1 = GenerateThumbs.getInstance().generateThumb("_chat_", current_dir, destinationFile, 40);
		HashMap<String,Object> processThumb2 = GenerateThumbs.getInstance().generateThumb("_profile_", current_dir, destinationFile, 126);
		HashMap<String,Object> processThumb3 = GenerateThumbs.getInstance().generateThumb("_big_", current_dir, destinationFile, 240);		
		
		returnMap.put("processJPG", processJPG);
		returnMap.put("processThumb1", processThumb1);
		returnMap.put("processThumb2", processThumb2);
		returnMap.put("processThumb3", processThumb3);
		
		//Delete old one
		File fToDelete = new File(fileFullPath);
		fToDelete.delete();
		
		File fileNameToStore = new File(destinationFile+".jpg");
		String pictureuri = fileNameToStore.getName();
		Users us = Usermanagement.getInstance().getUser(users_id);
		us.setUpdatetime(new java.util.Date());
		us.setPictureuri(pictureuri);
		Usermanagement.getInstance().updateUser(us);		

		return returnMap;
	}
	
	/**
	 * -density 150 -resize 800\> 
	 * 
	 */

	private HashMap<String,Object> convertSingleJpg(String current_dir, String inputFile, String outputfile) {
		HashMap<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("process", "convertSingleJpg");
		try {

			String runtimeFile = "pngconverter.bat";
			if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") == -1) {
				runtimeFile = "pngconverter.sh";
			}
			Runtime rt = Runtime.getRuntime();

			String pathToIMagick = Configurationmanagement.getInstance().getConfKey(3,"imagemagick_path").getConf_value();
			
			String command = current_dir + "jod" + File.separatorChar
					+ runtimeFile + " " + inputFile + " " 
					+ outputfile + ".jpg" + " "
					+ pathToIMagick;
			returnMap.put("command", command);
			Process proc = rt.exec(command);
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
			returnMap.put("error",t.getMessage());
			returnMap.put("exitValue", -1);
			return returnMap;
		}
	}

	
}
