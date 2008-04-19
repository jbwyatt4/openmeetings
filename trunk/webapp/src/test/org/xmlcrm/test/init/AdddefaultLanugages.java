package org.openmeetings.test.init;

import junit.framework.TestCase;

import org.openmeetings.app.data.basic.Languagemanagement;

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
