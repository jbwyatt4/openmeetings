package org.openmeetings.test.init;

import org.junit.Test;
import org.openmeetings.app.data.basic.FieldLanguageDaoImpl;
import org.openmeetings.test.AbstractOpenmeetingsSpringTest;
import org.springframework.beans.factory.annotation.Autowired;

public class TestAddDefaultLanugages extends AbstractOpenmeetingsSpringTest {

	@Autowired
	private FieldLanguageDaoImpl fieldLanguageDaoImpl;

	@Test
	public void testAdddefaultLanugages() {

		fieldLanguageDaoImpl.addLanguage("deutsch", false);
		fieldLanguageDaoImpl.addLanguage("english", false);
		fieldLanguageDaoImpl.addLanguage("french", false);

	}
}
