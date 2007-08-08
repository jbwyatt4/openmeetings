package org.xmlcrm.utils;

import java.util.LinkedHashMap;

import junit.framework.TestCase;

import org.xmlcrm.utils.mappings.CastHashMapToObject;


public class TestReflectionApi extends TestCase {

	
	public TestReflectionApi(String testname){
		super(testname);
	}
	
	public void testReflectionApi(){
		try {
			LinkedHashMap values = new LinkedHashMap();
			values.put("attribute1", true);
			values.put("attribute2", null);
			values.put("attribute3", 1);
			values.put("attribute10", 1F);
			values.put("attribute6", 1L);
			values.put("attribute13", new java.util.Date());
			values.put("attribute12", "Stringeling");
			
			LinkedHashMap subBeanForReflection = new LinkedHashMap();
			subBeanForReflection.put("attribute12", "subBeanForReflection String");
			
			values.put("subBeanForReflection", subBeanForReflection);
			TestBeanForReflection myObject = (TestBeanForReflection) CastHashMapToObject.getInstance().castByGivenObject(values, TestBeanForReflection.class);
			
			System.out.println("testReflectionApi "+myObject);
			
			//test if cast has been successful
			System.out.println("attribute12: " + myObject.getAttribute12());
			System.out.println("subBeanForReflection attribute12: " + myObject.getSubBeanForReflection().getAttribute12());
			
			
		} catch (Exception ex) {
			System.out.println("TestBeanForReflection ex" + ex);
			ex.printStackTrace();
		}
	}

}
