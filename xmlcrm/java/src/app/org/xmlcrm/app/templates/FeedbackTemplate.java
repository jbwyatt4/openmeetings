package org.xmlcrm.app.templates;

import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.xmlcrm.app.data.basic.Configurationmanagement;

public class FeedbackTemplate extends VelocityLoader{
	
	private static final String tamplateName = "feedback_";

	private static final Log log = LogFactory.getLog(FeedbackTemplate.class);

	private FeedbackTemplate() {
		super();
	}

	private static FeedbackTemplate instance = null;

	public static synchronized FeedbackTemplate getInstance() {
		if (instance == null) {
			instance = new FeedbackTemplate();
		}
		return instance;
	}

	public String getFeedBackTemplate(String username, String email, String message){
        try {
	        /* lets make a Context and put data into it */
	
	        VelocityContext context = new VelocityContext();
	
	        context.put("username", username);
	        context.put("email", email);
	        context.put("message", message);
	
	        /* lets render a template */
	
	        StringWriter w = new StringWriter();
	        
	        String template = tamplateName+Configurationmanagement.getInstance().getConfKey(3,"default_lang").getConf_value()+".vm";
	        
            Velocity.mergeTemplate(template, "UTF-8", context, w );
            
            return w.toString();         
            
        } catch (Exception e ) {
        	log.error("Problem merging template : " , e );
            //System.out.println("Problem merging template : " + e );
        }
        return null;
	}
}
