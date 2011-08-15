package org.openmeetings.test.dao;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.openmeetings.app.data.user.dao.PrivateMessagesDaoImpl;
import org.openmeetings.app.persistence.beans.lang.Fieldvalues;
import org.openmeetings.app.persistence.beans.user.Salutations;
import org.openmeetings.test.AbstractOpenmeetingsSpringTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class TestSalutationEntity extends AbstractOpenmeetingsSpringTest {
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private PrivateMessagesDaoImpl privateMessagesDao;
	
	@Test
	public void testAddSalutation() throws Exception {
		ArrayList<Long> Ids = new ArrayList<Long>();
		Ids.add(11L);
		privateMessagesDao.updatePrivateMessagesToTrash(Ids, true, 0L);

		Salutations sl = new Salutations();
		sl.setDeleted("false");
		sl.setName("addSalutation");
		sl.setFieldvalues_id(1000L);
		sl.setStarttime(new Date());

		em.merge(sl);
	}
	
	@Test
	public void testAddFieldvalues() throws Exception {

		Fieldvalues fl = new Fieldvalues();
		fl.setStarttime(new Date());
		fl.setName("test value");
		fl.setDeleted("false");

		em.merge(fl);
	}
}
