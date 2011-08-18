package org.openmeetings.test.error;

import java.util.List;

import org.junit.Test;
import org.openmeetings.app.data.basic.ErrorManagement;
import org.openmeetings.app.data.basic.Fieldmanagment;
import org.openmeetings.app.persistence.beans.basic.ErrorType;
import org.openmeetings.app.persistence.beans.basic.ErrorValues;
import org.openmeetings.app.persistence.beans.lang.Fieldlanguagesvalues;
import org.openmeetings.app.remote.LanguageService;
import org.openmeetings.test.AbstractOpenmeetingsSpringTest;
import org.springframework.beans.factory.annotation.Autowired;
import static junit.framework.Assert.*;

public class TestErrorManagement extends AbstractOpenmeetingsSpringTest {
	private static final long START_ERRORVALUES_ID = 666;
	@Autowired
	private LanguageService languageService;
	@Autowired
	private ErrorManagement errorManagement;
	@Autowired
	private Fieldmanagment fieldmanagment;
	
	private Long getAvailableErrorValuesId() {
		ErrorValues ev = null;
		Long result = START_ERRORVALUES_ID;
		{
			ev = errorManagement.getErrorValuesById(++result);
		} while(ev != null);
		return result;
	}
	
	@Test
	public void createErrorValueAndTest() {
		List<ErrorType> types = errorManagement.getErrorTypes();
		List<Fieldlanguagesvalues> flv = fieldmanagment.getAllFieldsByLanguage(languageService.getDefaultLanguage().longValue());
		Long errorValuesId = getAvailableErrorValuesId();
		Long errorTypeId = types.get(0).getErrortype_id();
		Long fieldValuesId = flv.get(0).getFieldvalues_id();
		assertEquals("Errorvalues Id should persists", errorValuesId, errorManagement.addErrorValues(errorValuesId, errorTypeId, fieldValuesId));
		
		ErrorValues ev = errorManagement.getErrorValuesById(errorValuesId);
		assertNotNull("Error type should not be null", ev.getErrorType());
		assertEquals("Error type should persists", errorTypeId, ev.getErrortype_id());
		assertNotNull("Fieldvalues should not be null", ev.getFieldvalues());
		assertEquals("Fieldvalues should persists", fieldValuesId, ev.getFieldvalues_id());
	}
}
