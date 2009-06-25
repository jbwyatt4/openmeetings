package org.openmeetings.app.documents;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openmeetings.app.data.basic.Configurationmanagement;
import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;

public class GenerateSWF {
	
	private static final Logger log = Red5LoggerFactory.getLogger(GeneratePDF.class, "openmeetings");
	
	private static GenerateSWF instance;

	private GenerateSWF() {}

	public static synchronized GenerateSWF getInstance() {
		if (instance == null) {
			instance = new GenerateSWF();
		}
		return instance;
	}

	final static boolean isPosix = System.getProperty("os.name").toUpperCase()
			.indexOf("WINDOWS") == -1;
	
	final static String execExt = isPosix ? "" : ".exe"; 

	static HashMap<String, Object> executeScript(String process, String[] argv) {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("process", process);
		log.debug("process: " + process);
		log.debug("args: " + Arrays.toString(argv));
		try {
			Runtime rt = Runtime.getRuntime();
			returnMap.put("command", Arrays.toString(argv));
			Process proc = rt.exec(argv);

			InputStream stderr = proc.getErrorStream();
			InputStreamReader isr = new InputStreamReader(stderr);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			String error = "";
			while ((line = br.readLine()) != null) {
				error += line;
				log.debug("line: " + line);
			}
			returnMap.put("error", error);
			int exitVal = proc.waitFor();
			log.debug("exitVal: " + exitVal);
			returnMap.put("exitValue", exitVal);
		} catch (Throwable t) {
			t.printStackTrace();
			returnMap.put("error", t.getMessage());
			returnMap.put("exitValue", -1);
		}
		return returnMap;
	}
	
	private String getPathToSwfTools() {
		String pathToSWFTools = Configurationmanagement.getInstance()
				.getConfKey(3, "swftools_path").getConf_value();
		// If SWFTools Path is not blank a File.separator at the end of the path
		// is needed
		if (!pathToSWFTools.equals("")
				&& !pathToSWFTools.endsWith(File.separator)) {
			pathToSWFTools = pathToSWFTools + File.separator;
		}
		return pathToSWFTools;
	}
	
	public HashMap<String, Object> generateSwf(String current_dir,
			String originalFolder, String destinationFolder, String fileNamePure) {
		// Create the Content of the Converter Script (.bat or .sh File)
		String[] argv = new String[] { getPathToSwfTools() + "pdf2swf" + execExt,
				originalFolder + fileNamePure + ".pdf",
				destinationFolder + fileNamePure + ".swf" };

		return executeScript("generateSwf", argv);
	}
	
	/**
	 * Generates an SWF from the list of files.
	 */
	public HashMap<String, Object> generateSwfByImages(List<String> images,
			String outputfile, int fps) {
		List<String> argvList = Arrays.asList(new String[] {
				getPathToSwfTools() + "png2swf" + execExt, "-o", outputfile,
				"-r", Integer.toString(fps), "-z" });
		
		argvList.addAll(images);
		return executeScript("generateSwfByImages", (String[]) argvList.toArray());
	}
	
	/**
	 * Combines a bunch of SWFs into one SWF by concatenate.
	 */
	public HashMap<String, Object> generateSWFByCombine(List<String> swfs,
			String outputswf, int fps) {
		List<String> argvList = Arrays.asList(new String[] {
				getPathToSwfTools() + "swfcombine" + execExt, "-o", outputswf,
				"-r", Integer.toString(fps), "-z", "-a" });

		argvList.addAll(swfs);
		return executeScript("generateSwfByImages", (String[]) argvList.toArray());
	}
	
	
	public HashMap<String, Object> generateSWFByFFMpeg(String inputWildCard,
			String outputswf, int fps, int width, int height) {
		// FIXME: ffmpeg should be on the system path
		String[] argv = new String[] { "ffmpeg" + execExt, "-r",
				Integer.toString(fps), "-i", inputWildCard, "-s",
				width + "x" + height, "-b", "750k", "-ar", "44100", "-y",
				outputswf };

		return executeScript("generateSWFByFFMpeg", argv);
	}

}
