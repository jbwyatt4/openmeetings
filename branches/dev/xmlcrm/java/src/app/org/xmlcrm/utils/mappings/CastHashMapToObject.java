package org.xmlcrm.utils.mappings;

import java.util.LinkedHashMap;
import java.util.Iterator;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

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
			Object returnObject = targetClass.newInstance();
			log.error("returnObject");
			log.error(returnObject);
			log.error( "class " + targetClass.getName() ); 
			log.error (" number of declared fields: " + targetClass.getDeclaredFields().length );
			LinkedHashMap<String,LinkedHashMap<String,Object>> structuredMethodMap = StructureMethodList.getInstance().parseClassToMethodList(targetClass);
			
			for ( Field anyField : targetClass.getDeclaredFields() )  { 
				String fieldName = anyField.getName(); 
				Class fieldType = anyField.getType();
				String fieldTypeName = anyField.getType().getName(); 

				if (this.compareTypeNameToBasicTypes(fieldTypeName)) {
					//log.info("Found Type: " + fieldName);
					//Get value from  set 
					Object t = values.get(fieldName);
					//log.info("fieldName Value: "+t);
					//log.info("fieldName Value: "+anyField.getModifiers());
					int mod = anyField.getModifiers();
					
					if (Modifier.isPrivate(mod) && !Modifier.isFinal(mod)){
						
						//log.info("is private so get setter method "+fieldName);
						LinkedHashMap<String,Object> methodSummery = structuredMethodMap.get(fieldName);
						
						if (methodSummery!=null) {
							if (methodSummery.get("setter")!=null) {
	
								String methodSetterName = methodSummery.get("setter").toString();
								Class[] paramTypes = (Class[]) methodSummery.get("setterParamTypes");
								Method m = targetClass.getMethod(methodSetterName, paramTypes);
								
								Class paramType = paramTypes[0];
								//log.error("paramType: "+paramType.getName());
								if (paramType.isPrimitive() && t==null){
									//cannot cast null to primitve
								} else {
									Object[] arguments = new Object[]{ t }; 
									m.invoke(returnObject,arguments);
								}
							
							} else {
								log.error("could not find a setter-method from Structured table. Is there a setter-method for " + fieldName + " in Class " + targetClass.getName());
							}
						} else {
							log.error("could not find a method from Structured table. Is there a method for " + fieldName + " in Class " + targetClass.getName());
						}
						
					} else if (Modifier.isPublic(mod) && !Modifier.isFinal(mod)){
						
						//Is public attribute so set it directly
						anyField.set(returnObject, t);
						
					} else if (Modifier.isFinal(mod)) {
						log.error("Final attributes cannot be changed ");
					} else {
						log.error("Unhandled Modifier Type: " + mod);
					}
					
				} else {
					Object valueOfHashMap = values.get(fieldName);
					String valueTypeOfHashMap = valueOfHashMap.getClass().getName();
					if (this.compareTypeNameToAllowedListTypes(valueTypeOfHashMap)) {
					
						log.error("is allowed List Type "+ fieldType.getName());
	
						//Get value from  set 
						Object t = this.castByGivenObject((LinkedHashMap)valueOfHashMap, fieldType);
						int mod = anyField.getModifiers();
						
						if (Modifier.isPrivate(mod) && !Modifier.isFinal(mod)){
							
							log.info("is private so get setter method "+fieldName);
							LinkedHashMap<String,Object> methodSummery = structuredMethodMap.get(fieldName);
							
							if (methodSummery!=null) {
								if (methodSummery.get("setter")!=null) {
		
									String methodSetterName = methodSummery.get("setter").toString();
									Class[] paramTypes = (Class[]) methodSummery.get("setterParamTypes");
									Method m = targetClass.getMethod(methodSetterName, paramTypes);
									
									Class paramType = paramTypes[0];
									log.error("paramType: "+paramType.getName());
									if (paramType.isPrimitive() && t==null){
										//cannot cast null to primitve
									} else {
										Object[] arguments = new Object[]{ t }; 
										m.invoke(returnObject,arguments);
									}
								
								} else {
									log.error("could not find a setter-method from Structured table. Is there a setter-method for " + fieldName + " in Class " + targetClass.getName());
								}
							} else {
								log.error("could not find a method from Structured table. Is there a method for " + fieldName + " in Class " + targetClass.getName());
							}
						} else if (Modifier.isPublic(mod) && !Modifier.isFinal(mod)){
							
							//Is public attribute so set it directly
							anyField.set(returnObject, t);
							
						} else if (Modifier.isFinal(mod)) {
							log.error("Final attributes cannot be changed ");
						} else {
							log.error("Unhandled Modifier Type: " + mod);
						}
					}
				}
			} 

			return returnObject;
		} catch (Exception ex) {
			log.error("[castByGivenObject]: " ,ex);
		}
		return null;
	}
	
	private boolean compareTypeNameToBasicTypes(String fieldTypeName) {
		try {
			
			for (Iterator it = CastBasicTypes.getCompareTypesSimple().iterator();it.hasNext();) {
				if (fieldTypeName.equals(it.next())) return true;
			}
			
			return false;
		} catch (Exception ex) {
			log.error("[compareTypeNameToBasicTypes]",ex);
			return false;
		}
	}
	
	private boolean compareTypeNameToAllowedListTypes(String fieldTypeName) {
		try {
			log.error("compareTypeNameToAllowedListTypes"+ fieldTypeName);
			for (Iterator it = CastBasicTypes.getAllowedListTypes().iterator();it.hasNext();) {
				if (fieldTypeName.equals(it.next())) return true;
			}
			
			return false;
		} catch (Exception ex) {
			log.error("[compareTypeNameToBasicTypes]",ex);
			return false;
		}
	}
	
}
