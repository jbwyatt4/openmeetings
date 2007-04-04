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


public class RegisterUserTemplate {

	private static final Log log = LogFactory.getLog(RegisterUserTemplate.class);

	public RegisterUserTemplate() {
	}

	private static RegisterUserTemplate instance = null;

	public static synchronized RegisterUserTemplate getInstance() {
		if (instance == null) {
			instance = new RegisterUserTemplate();
		}
		return instance;
	}

	public String getRegisterUserTemplate(){
		try
        {
			IScope scope = Red5.getConnectionLocal().getScope().getParent();
			
			String current_dir = scope.getResource("WEB-INF/").getFile().getAbsolutePath();	
            Velocity.init(current_dir+"/velocity.properties");
        }
        catch(Exception e)
        {
        	log.error("Problem initializing Velocity : " + e );
            System.out.println("Problem initializing Velocity : " + e );
            return null;
        }

        /* lets make a Context and put data into it */

        VelocityContext context = new VelocityContext();

        context.put("name", "Velocity");
        context.put("project", "Jakarta");

        /* lets render a template */

        StringWriter w = new StringWriter();

        try
        {

            Velocity.mergeTemplate("example2.vm", "ISO-8859-1", context, w );
        }
        catch (Exception e )
        {
        	log.error("Problem merging template : " + e );
            System.out.println("Problem merging template : " + e );
        }

        System.out.println(" template : " + w );
        
        return w.toString();
	}
}
