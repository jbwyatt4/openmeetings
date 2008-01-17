package org.xmlcrm.test.init;

import junit.framework.TestCase;

import org.xmlcrm.app.data.basic.Languagemanagement;

public class AdddefaultLanugages extends TestCase {

	public AdddefaultLanugages(String testname){
		super(testname);
	}
	
	public void testAdddefaultLanugages(){
		
		Languagemanagement.getInstance().addLanguage("deutsch");
		Languagemanagement.getInstance().addLanguage("english");
		Languagemanagement.getInstance().addLanguage("french");
		
	}
}
