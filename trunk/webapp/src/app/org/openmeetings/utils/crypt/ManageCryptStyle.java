package org.openmeetings.utils.crypt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openmeetings.app.data.basic.Configurationmanagement;

public class ManageCryptStyle {
	
	private static final Logger log = LoggerFactory.getLogger(ManageCryptStyle.class);
	
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
