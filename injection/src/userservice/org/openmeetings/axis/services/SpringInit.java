package org.openmeetings.axis.services;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.description.AxisService;
import org.apache.axis2.engine.ServiceLifeCycle;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringInit implements ServiceLifeCycle {

	private static final String SPRING_CONTEXT_XML = "openmeetings-applicationContext.xml";

	public OMElement springInit(OMElement ignore) {
		return null;
	}

	public void shutDown(ConfigurationContext configctx, AxisService service) {

	}

	public void startUp(ConfigurationContext configctx, AxisService service) {

		try {

			System.out
					.println("************* SERVICE INIT *********************");

			ClassLoader classLoader = service.getClassLoader();
			ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
					new String[] { SPRING_CONTEXT_XML }, false);
			applicationContext.setClassLoader(classLoader);
			applicationContext.refresh();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
