package org.xmlcrm.test.init;

import junit.framework.TestCase;

import org.xmlcrm.app.data.basic.Fieldmanagment;

public class AddDefaultfield extends TestCase {
	
	public AddDefaultfield (String testname){
		super(testname);
	}
	
	public void testaddDefaultField(){
		
		Fieldmanagment.getInstance().addField("conferencing");
		Fieldmanagment.getInstance().addFieldValueByFieldAndLanguage(7, 1, "Konferenz");
		Fieldmanagment.getInstance().addFieldValueByFieldAndLanguage(7, 2, "Confernce");
		Fieldmanagment.getInstance().addFieldValueByFieldAndLanguage(7, 3, "Conférence");
		Fieldmanagment.getInstance().addFieldValueByFieldAndLanguage(7, 4, "Conferencia");	
		
	}

}
