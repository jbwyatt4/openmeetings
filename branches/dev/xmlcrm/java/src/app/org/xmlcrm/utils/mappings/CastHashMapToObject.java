package org.xmlcrm.utils.mappings;

import java.util.LinkedHashMap;
import java.lang.reflect.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CastHashMapToObject {
	
	private static final Log log = LogFactory.getLog(CastHashMapToObject.class);
	
	private CastHashMapToObject() {}

	private static CastHashMapToObject instance = null;

	public static synchronized CastHashMapToObject getInstance() {
		if (instance == null) {
			instance = new CastHashMapToObject();
		}
		return instance;
	}	
	
	public Object castByGivenObject(LinkedHashMap values, Class targetClass){
		try {
			log.error( "class " + targetClass.getName() + " {" ); 
			for ( Field publicField : targetClass.getFields() ) 
			{ 
			  String fieldName = publicField.getName(); 
			  String fieldType = publicField.getType().getName(); 
			  log.error( fieldType + " || " + fieldName); 
			} 
			log.error ( "}" );
		} catch (Exception ex) {
			log.error("[castByGivenObject]: " ,ex);
		}
		return null;
	}

}
