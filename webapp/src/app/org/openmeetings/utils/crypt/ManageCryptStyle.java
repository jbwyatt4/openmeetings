package org.openmeetings.utils.crypt;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmeetings.app.data.basic.Configurationmanagement;

public class ManageCryptStyle {
	
	private static final Log log = LogFactory.getLog(ManageCryptStyle.class);
	
	private ManageCryptStyle() {}

	private static ManageCryptStyle instance = null;

	public static synchronized ManageCryptStyle getInstance() {
		if (instance == null) {
			instance = new ManageCryptStyle();
		}
		return instance;
	}	
	
	public CryptStringAdapter getInstanceOfCrypt() {
		try {
			
			log.debug("getInstanceOfCrypt: "+this);
			
			log.debug("getInstanceOfCrypt: "+Configurationmanagement.getInstance());
			
			//String configKeyCryptClassName = "org.openmeetings.utils.crypt.MD5Implementation";
			String configKeyCryptClassName = Configurationmanagement.getInstance().getConfKey(3,"crypt_ClassName").getConf_value();
			
			log.debug("configKeyCryptClassName: "+configKeyCryptClassName);
			
			CryptStringAdapter t = (CryptStringAdapter) Class.forName(configKeyCryptClassName).newInstance();
			//t.getTime();
			return t;

		} catch (Exception err) {
			log.error("[getInstanceOfCrypt]",err);
		}
		return null;
	}

}
