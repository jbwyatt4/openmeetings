package org.xmlcrm.app.templates;

import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.xmlcrm.app.data.basic.Configurationmanagement;

public class ScreenCastTemplate extends VelocityLoader{
	
	private static final String tamplateName = "screencast_template";

	private static final Log log = LogFactory.getLog(ScreenCastTemplate.class);

	private ScreenCastTemplate() {
		super();
	}

	private ScreenCastTemplate(String path) {
		super(path);
	}
	
	private static ScreenCastTemplate instance = null;

	public static synchronized ScreenCastTemplate getInstance() {
		if (instance == null) {
			instance = new ScreenCastTemplate();
		}
		return instance;
	}
	public static synchronized ScreenCastTemplate getInstance(String path) {
		if (instance == null) {
			instance = new ScreenCastTemplate(path);
		}
		return instance;
	}	

	public String getScreenTemplate(String rtmphostlocal, String red5httpport, String SID, String ROOM, String DOMAIN){
        try {
	        /* lets make a Context and put data into it */
	
	        VelocityContext context = new VelocityContext();
	
	        context.put("rtmphostlocal", rtmphostlocal);
	        context.put("red5httpport", red5httpport);
	        context.put("SID", SID);
	        context.put("ROOM", ROOM);
	        context.put("DOMAIN", DOMAIN);
	
	        /* lets render a template */
	
	        StringWriter w = new StringWriter();
	        
	        String template = tamplateName+".vm";
	        
            Velocity.mergeTemplate(template, "UTF-8", context, w );
            
            return w.toString();         
            
        } catch (Exception e ) {
        	log.error("Problem merging template : " , e );
            //System.out.println("Problem merging template : " + e );
        }
        return null;
	}
}
