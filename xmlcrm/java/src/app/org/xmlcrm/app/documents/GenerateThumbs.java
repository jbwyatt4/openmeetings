package org.xmlcrm.app.documents;

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
	
	public String generateThumb(String current_dir, String filepath) {
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
					+ "_thumb_"+name+".jpg";
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
			return "<error type='generateThumb'>"+t.getMessage()+"</ERROR>";
		}
	}
	
	public String generateBatchThumb(String current_dir, String inputfile, String outputpath) {
		try {
			String runtimeFile = "thumbnail.bat";
			if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") == -1) {
				runtimeFile = "thumbnail.sh";
			}
			Runtime rt = Runtime.getRuntime();

			String command = current_dir + "jod" + File.separatorChar
					+ runtimeFile + " " + inputfile + " " + outputpath
					+ "_thumb_pages-%03d.jpg";
			String error = "<command type='generateBatchThumb'>"+ command +"</command>";
			Process proc = rt.exec(command);
			InputStream stderr = proc.getErrorStream();
			InputStreamReader isr = new InputStreamReader(stderr);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			error += "<error type='generateBatchThumb'>";
			while ((line = br.readLine()) != null)
				error += line;
			error += "</error>";
			int exitVal = proc.waitFor();
			error += "<exitvalue type='generateBatchThumb'>"+ exitVal +"</exitvalue>";
			return error;
		} catch (Throwable t) {
			t.printStackTrace();
			return "<error type='generateBatchThumb'>"+t.getMessage()+"</ERROR>";
		}
	}	
}
