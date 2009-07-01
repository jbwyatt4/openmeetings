package de.medint.rtpsharer.main;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.URL;
import java.net.URLConnection;


/**
 * 
 * @author o.becherer
 *
 */
public class ServletFunctions {
	
	/**
	 * 
	 * @param servletUrl
	 * @param jpegQuality
	 * @param height
	 * @param width
	 */
	//---------------------------------------------------------------------------------------------------------
	public static void sendStartSignal(String servletUrl, float jpegQuality, int height, int width, String room, String SID, String sharerIP) throws Exception{
		
		
		//Building ServletUrl
		String url = servletUrl + "?method=streamer_start&room=" + room + "&height=" + height + "&width=" + width + "&quality=" + jpegQuality + "&sid=" + SID + "&sharerIP=" + sharerIP;
		
		URLConnection c = getConnection(url);
		
		// open a stream which can write to the url
		DataOutputStream dstream = new DataOutputStream(c.getOutputStream());

		dstream.writeUTF("ServletCall");
		dstream.flush();
		dstream.close();
		
		 // read the output from the URL
		DataInputStream in = new DataInputStream(new BufferedInputStream(c.getInputStream()));
		
		String sIn = in.readLine();
		while (sIn != null) {
			if (sIn != null) {
				System.out.println(sIn);
			}
			sIn += in.readLine();
		}
		
		c = null;
		
	}
	//---------------------------------------------------------------------------------------------------------
	
	
	/**
	 * 
	 * @param servletUrl
	 * @param jpegQuality
	 * @param height
	 * @param width
	 */
	//---------------------------------------------------------------------------------------------------------
	public static void sendStopSignal(String servletUrl, String room, String SID) throws Exception{
		
		
		//Building ServletUrl
		String url = servletUrl + "?method=streamer_stop&room=" + room + "&sid=" + SID;
		
		URLConnection c = getConnection(url);
		
		// open a stream which can write to the url
		DataOutputStream dstream = new DataOutputStream(c.getOutputStream());

		dstream.writeUTF("ServletCall");
		dstream.flush();
		dstream.close();
		
		 // read the output from the URL
		DataInputStream in = new DataInputStream(new BufferedInputStream(c.getInputStream()));
		
		String sIn = in.readLine();
		while (sIn != null) {
			if (sIn != null) {
				System.out.println(sIn);
			}
			sIn += in.readLine();
		}
		
		c = null;
		
	}
	//---------------------------------------------------------------------------------------------------------
	
	
	/**
	 * Opening URLConnection
	 */
	//---------------------------------------------------------------------------------------------------------
	private static URLConnection getConnection(String servletUrl) throws Exception{
		
		URL u = new URL(servletUrl);
		URLConnection c = u.openConnection();

		// post multipart data
		c.setDoOutput(true);
		c.setDoInput(true);
		c.setUseCaches(false);
		
		return c;

	}
	//---------------------------------------------------------------------------------------------------------
	
}
