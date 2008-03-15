package org.xmlcrm.app.templates;

import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.xmlcrm.app.data.basic.Configurationmanagement;

public class InvitationTemplate extends VelocityLoader{
	
	private static final String tamplateName = "invitation.vm";

	private static final Log log = LogFactory.getLog(InvitationTemplate.class);

	private InvitationTemplate() {
		super();
	}

	private static InvitationTemplate instance = null;

	public static synchronized InvitationTemplate getInstance() {
		if (instance == null) {
			instance = new InvitationTemplate();
		}
		return instance;
	}

	public String getRegisterInvitationTemplate(String user, String message, String invitation_link){
        try {
	        /* lets make a Context and put data into it */
	
	        VelocityContext context = new VelocityContext();
	
	        context.put("user", user);
	        context.put("message", message);
	        context.put("invitation_link", invitation_link);
	        context.put("invitation_link2", invitation_link);
	
	        /* lets render a template */
	
	        StringWriter w = new StringWriter();
	        
	        String default_lang_id = Configurationmanagement.getInstance().getConfKey(3,"default_lang_id").getConf_value();
	        
            Velocity.mergeTemplate(tamplateName, "UTF-8", context, w );
            
            return w.toString();         
            
        } catch (Exception e ) {
        	log.error("Problem merging template : " , e );
            //System.out.println("Problem merging template : " + e );
        }
        return null;
	}
}
