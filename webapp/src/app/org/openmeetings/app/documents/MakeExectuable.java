package org.openmeetings.app.documents;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MakeExectuable {
	
	private static final Logger log = LoggerFactory.getLogger(MakeExectuable.class);

	private static MakeExectuable instance;

	private MakeExectuable() {}

	public static synchronized MakeExectuable getInstance() {
		if (instance == null) {
			instance = new MakeExectuable();
		}
		return instance;
	}
	
	public void setExecutable(String fileName){
		try {
			
			log.debug("setExecutable: "+fileName);
			
			String[] cmd = new String[1];
			cmd[0] = "chmod +x "+fileName;
			
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec( "chmod +x "+fileName );
			
			InputStream stderr = proc.getErrorStream();
			InputStreamReader isr = new InputStreamReader(stderr);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			String error = "";
			while ((line = br.readLine()) != null){
				error += line;
				log.debug("line: "+line);
			}
			int exitVal = proc.waitFor();
			log.debug("exitVal: "+exitVal);
			
		} catch (Exception err) {
			log.error("[setExecutable]",err);
		}
	}

}
