package org.openmeetings.test.init;

import junit.framework.TestCase;

import org.openmeetings.app.data.basic.FieldLanguageDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class AdddefaultLanugages extends TestCase {

	@Autowired
	private FieldLanguageDaoImpl fieldLanguageDaoImpl;

	public AdddefaultLanugages(String testname) {
		super(testname);
	}

	public void testAdddefaultLanugages() {

		fieldLanguageDaoImpl.addLanguage("deutsch", false);
		fieldLanguageDaoImpl.addLanguage("english", false);
		fieldLanguageDaoImpl.addLanguage("french", false);

	}
}
