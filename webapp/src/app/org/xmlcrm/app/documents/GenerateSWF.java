package org.openmeetings.app.documents;

import java.util.HashMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.openmeetings.app.data.basic.Configurationmanagement;

public class GenerateSWF {

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
			
			String pathToSWFTools = Configurationmanagement.getInstance().getConfKey(3,"swftools_path").getConf_value();
			
			String runtimeFile = "swfconverter.bat";
			String command = "cmd.exe /c start "+current_dir + "jod" + File.separatorChar + runtimeFile + " " 
				+ originalFolder + fileNamePure + ".pdf "
				+ destinationFolder + fileNamePure+".swf" + " "
				+ pathToSWFTools;
			
			if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") == -1) {
				runtimeFile = "swfconverter.sh";
				command = current_dir + "jod" + File.separatorChar + runtimeFile + " " 
					+ originalFolder + fileNamePure + ".pdf "
					+ destinationFolder + fileNamePure+".swf" + " "
					+ pathToSWFTools;
			}
			System.out.println("generateSWF command: "+command);
			
			Runtime rt = Runtime.getRuntime();			
			
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

}
