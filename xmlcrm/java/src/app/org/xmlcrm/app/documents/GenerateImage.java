package org.xmlcrm.app.documents;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GenerateImage {

	private static GenerateImage instance;

	private GenerateImage() {}

	public static synchronized GenerateImage getInstance() {
		if (instance == null) {
			instance = new GenerateImage();
		}
		return instance;
	}


	public String convertImage(String current_dir, String fileNameExt, String roomName,
			String fileNameShort, boolean fullProcessing) throws Exception {

		String working_imgdir = current_dir + "upload" + File.separatorChar + roomName + File.separatorChar;
		String working_pptdir = current_dir + "uploadtemp" + File.separatorChar + roomName + File.separatorChar;

		String fileFullPath = working_pptdir + fileNameExt;
		String newFileName = fileNameExt.substring(0, fileNameExt.length() - 4);
		String newFileNameExtensionOnly = fileNameExt.substring(fileNameExt
				.length() - 4, fileNameExt.length());

		File f = new File(working_imgdir + newFileName + newFileNameExtensionOnly);
		if (f.exists()) {
			int recursiveNumber = 0;
			String tempd = newFileName + "_" + recursiveNumber;
			while (f.exists()) {
				recursiveNumber++;
				tempd = newFileName + "_" + recursiveNumber;
				f = new File(working_imgdir + tempd + newFileNameExtensionOnly);
			}
			newFileName = tempd;
		}

		String destinationFile = working_imgdir + newFileName;
		String error = this.convertSingleJpg(current_dir, fileFullPath, destinationFile);

		//Delete old one
		File fToDelete = new File(fileFullPath);
		fToDelete.delete();

		return error;
	}
	
	/**
	 * -density 150 -resize 800\> 
	 * 
	 */

	private String convertSingleJpg(String current_dir, String inputFile, String outputfile) {
		try {
			String runtimeFile = "pngconverter.bat";
			if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") == -1) {
				runtimeFile = "pngconverter.sh";
			}
			Runtime rt = Runtime.getRuntime();

			String command = current_dir + "jod" + File.separatorChar
					+ runtimeFile + " " + inputFile + " " + outputfile + ".jpg";
			String error = "<command type='generateThumb'>"+ command +"</command>";
			Process proc = rt.exec(command);
			InputStream stderr = proc.getErrorStream();
			InputStreamReader isr = new InputStreamReader(stderr);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			error += "<error type='generateThumb'>";
			while ((line = br.readLine()) != null)
				error += line;
			error += "</error>";
			int exitVal = proc.waitFor();
			error += "<exitvalue type='generateThumb'>"+ exitVal +"</exitvalue>";
			return error;
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return null;
	}

	
}
