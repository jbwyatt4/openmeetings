package org.xmlcrm.app.templates;

import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.xmlcrm.app.data.basic.Configurationmanagement;
import org.xmlcrm.app.data.basic.Fieldmanagment;
import org.xmlcrm.app.hibernate.beans.lang.Fieldlanguagesvalues;

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

	public String getRegisterInvitationTemplate(String user, String message, String invitation_link, Long default_lang_id){
        try {
        	
        	Fieldlanguagesvalues labelid500 = Fieldmanagment.getInstance().getFieldByIdAndLanguage(new Long(500), default_lang_id);
        	Fieldlanguagesvalues labelid501 = Fieldmanagment.getInstance().getFieldByIdAndLanguage(new Long(501), default_lang_id);
        	Fieldlanguagesvalues labelid502 = Fieldmanagment.getInstance().getFieldByIdAndLanguage(new Long(502), default_lang_id);
        	Fieldlanguagesvalues labelid503 = Fieldmanagment.getInstance().getFieldByIdAndLanguage(new Long(503), default_lang_id);
        	Fieldlanguagesvalues labelid504 = Fieldmanagment.getInstance().getFieldByIdAndLanguage(new Long(504), default_lang_id);
        	Fieldlanguagesvalues labelid505 = Fieldmanagment.getInstance().getFieldByIdAndLanguage(new Long(505), default_lang_id);
        	
	        /* lets make a Context and put data into it */
	        VelocityContext context = new VelocityContext();
	
	        context.put("user", user);
	        context.put("message", message);
	        context.put("invitation_link", invitation_link);
	        context.put("invitation_link2", invitation_link);
	        context.put("labelid500", labelid500.getValue());
	        context.put("labelid501", labelid501.getValue());
	        context.put("labelid502", labelid502.getValue());
	        context.put("labelid503", labelid503.getValue());
	        context.put("labelid504", labelid504.getValue());
	        context.put("labelid505", labelid505.getValue());
	         
	
	        /* lets render a template */
	        StringWriter w = new StringWriter();
            Velocity.mergeTemplate(tamplateName, "UTF-8", context, w );
            
            return w.toString();         
            
        } catch (Exception e ) {
        	log.error("Problem merging template : " , e );
            //System.out.println("Problem merging template : " + e );
        }
        return null;
	}
}
