package org.openmeetings.test.init;

import junit.framework.TestCase;

import org.openmeetings.app.data.basic.Languagemanagement;

public class AdddefaultLanugages extends TestCase {

	public AdddefaultLanugages(String testname){
		super(testname);
	}
	
	public void testAdddefaultLanugages(){
		
		Languagemanagement.getInstance().addLanguage("deutsch",false);
		Languagemanagement.getInstance().addLanguage("english",false);
		Languagemanagement.getInstance().addLanguage("french",false);
		
	}
}
