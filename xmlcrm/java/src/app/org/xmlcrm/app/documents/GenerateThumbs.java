package org.xmlcrm.app.documents;

import java.util.HashMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GenerateThumbs {

	private static GenerateThumbs instance;

	private GenerateThumbs() {}

	public static synchronized GenerateThumbs getInstance() {
		if (instance == null) {
			instance = new GenerateThumbs();
		}
		return instance;
	}
	
	public HashMap<String,Object> generateThumb(String current_dir, String filepath, int thumbSize) {
		HashMap<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("process", "generateThumb");		
		try {
			
			String runtimeFile = "thumbnail.bat";
			if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") == -1) {
				runtimeFile = "thumbnail.sh";
			}
			Runtime rt = Runtime.getRuntime();
			
			File f = new File(filepath);
			String name = f.getName();
			String folder = f.getParentFile().getAbsolutePath()+File.separatorChar;
			
			String command = current_dir + "jod" + File.separatorChar
					+ runtimeFile + " " + filepath + ".jpg " + folder
					+ "_thumb_"+name+".jpg "+thumbSize;
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
			returnMap.put("error", t.getMessage());
			returnMap.put("exitValue", -1);
			return returnMap;
		}
	}
	
	public HashMap<String,Object> generateBatchThumb(String current_dir, String inputfile, 
			String outputpath, int thumbSize) {
		HashMap<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("process", "generateBatchThumb");			
		try {
			String runtimeFile = "thumbnail.bat";
			if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") == -1) {
				runtimeFile = "thumbnail.sh";
			}
			Runtime rt = Runtime.getRuntime();

			String command = current_dir + "jod" + File.separatorChar
					+ runtimeFile + " " + inputfile + " " + outputpath
					+ "_thumb_pages-%03d.jpg "+thumbSize;
			returnMap.put("command",command);
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
			returnMap.put("error", t.getMessage());
			returnMap.put("exitValue", -1);
			return returnMap;
		}
	}	
}
