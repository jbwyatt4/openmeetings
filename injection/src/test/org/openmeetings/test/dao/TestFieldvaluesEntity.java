package org.openmeetings.test.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.openmeetings.app.persistence.beans.lang.Fieldlanguagesvalues;
import org.openmeetings.app.persistence.beans.lang.Fieldvalues;
import org.openmeetings.test.AbstractOpenmeetingsSpringTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class TestFieldvaluesEntity extends AbstractOpenmeetingsSpringTest {
	@PersistenceContext
	EntityManager em;
	
	@Test
	public void testAddFieldvalues() throws Exception {

		Fieldlanguagesvalues flv = new Fieldlanguagesvalues();
		flv.setStarttime(new Date());
		flv.setValue("test");
		flv.setLanguage_id(33L);
		flv.setDeleted("false");

		flv = em.merge(flv);

		Fieldvalues fl = new Fieldvalues();
		fl.setStarttime(new Date());
		fl.setName("test value");
		fl.setFieldlanguagesvalue(flv);
		fl.setDeleted("false");

		em.merge(fl);

	}

}
