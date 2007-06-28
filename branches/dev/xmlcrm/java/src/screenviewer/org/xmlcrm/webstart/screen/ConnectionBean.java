package org.xmlcrm.webstart.screen;

public class ConnectionBean {
	
	/**Connection Settings**/
	
	public static String connectionURL = "http://macbook:5080/xmlcrm/ScreenServlet";
	
	public static String SID = "1010";
	
	public static String room = "1";
	
	public static String domain = "public";
	
	/**Intervall Settings**/
	
	public static int intervallSeconds = 3;
	
	public static String quartzScreenJobName = "grabScreen";
	
	/**Picture Quality Settings
	 * Some guidelines: 0.75 high quality
     *           		0.5  medium quality
     *           		0.25 low quality
	 * **/
	
	public static Float imgQuality = new Float(0.60);
	
	/**
	 * current loading to server
	 */
	public static boolean isloading = false;
	
	
	/**
	 * image recalcing value's from the virtual Screen drawer
	 */
	public static int vScreenWidth = 200;
	
}
