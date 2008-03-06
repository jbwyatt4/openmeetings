package org.xmlcrm.app.templates;

import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.xmlcrm.app.data.basic.Configurationmanagement;

public class ResetPasswordTemplate extends VelocityLoader{
	
	private static final String tamplateName = "resetPass_";

	private static final Log log = LogFactory.getLog(FeedbackTemplate.class);

	private ResetPasswordTemplate() {
		super();
	}

	private static ResetPasswordTemplate instance = null;

	public static synchronized ResetPasswordTemplate getInstance() {
		if (instance == null) {
			instance = new ResetPasswordTemplate();
		}
		return instance;
	}

	public String getResetPasswordTemplate(String reset_link){
        try {
	        /* lets make a Context and put data into it */
	
	        VelocityContext context = new VelocityContext();
	
	        context.put("reset_link", reset_link);
	        context.put("reset_link2", reset_link);
	
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
