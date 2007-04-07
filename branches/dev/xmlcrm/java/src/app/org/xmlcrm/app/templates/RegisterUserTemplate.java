package org.xmlcrm.app.templates;

import java.io.StringWriter;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.VelocityContext;

import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.MethodInvocationException;
import org.red5.server.api.IScope;
import org.red5.server.api.Red5;
import org.xmlcrm.app.data.basic.Configurationmanagement;


public class RegisterUserTemplate extends VelocityLoader{
	
	private static final String tamplateName = "register_mail_";

	private static final Log log = LogFactory.getLog(RegisterUserTemplate.class);

	private RegisterUserTemplate() {
		super();
	}

	private static RegisterUserTemplate instance = null;

	public static synchronized RegisterUserTemplate getInstance() {
		if (instance == null) {
			instance = new RegisterUserTemplate();
		}
		return instance;
	}

	public String getRegisterUserTemplate(String username, String userpass, String email){
        try {
	        /* lets make a Context and put data into it */
	
	        VelocityContext context = new VelocityContext();
	
	        context.put("username", username);
	        context.put("userpass", userpass);
	        context.put("mail", email);
	
	        /* lets render a template */
	
	        StringWriter w = new StringWriter();
	        
	        String template = tamplateName+Configurationmanagement.getInstance().getConfKey(3,"default_lang").getConf_value()+".vm";
	        
            Velocity.mergeTemplate(template, "ISO-8859-1", context, w );
            
            System.out.println(" template : " + w );
            
            return w.toString();         
            
        } catch (Exception e ) {
        	log.error("Problem merging template : " + e );
            System.out.println("Problem merging template : " + e );
        }
        return null;
	}
}
