package org.openmeetings.test.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.openmeetings.app.persistence.beans.basic.ErrorValues;
import org.openmeetings.test.AbstractOpenmeetingsSpringTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class TestErrorValuesEntity extends AbstractOpenmeetingsSpringTest {

	@PersistenceContext
	EntityManager em;
	
	@Test
	public void testAddErrorValues() throws Exception {

		ErrorValues eValue = new ErrorValues();
		eValue.setErrorvalues_id(1L);
		eValue.setErrortype_id(1L);
		eValue.setDeleted("false");
		eValue.setStarttime(new Date());
		eValue.setFieldvalues_id(new Long(322));

		em.merge(eValue);
	}
}
