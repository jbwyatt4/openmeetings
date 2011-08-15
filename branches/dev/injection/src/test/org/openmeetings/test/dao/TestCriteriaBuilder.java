package org.openmeetings.test.dao;

import static junit.framework.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.openmeetings.app.persistence.beans.basic.Configuration;
import org.openmeetings.test.AbstractOpenmeetingsSpringTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class TestCriteriaBuilder extends AbstractOpenmeetingsSpringTest {
	@PersistenceContext
	EntityManager em;
	
	@Test
	public void testCriteriaBuilder() throws Exception{
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Configuration> cq = cb.createQuery(Configuration.class);
		Root<Configuration> c = cq.from(Configuration.class);
		Predicate condition = cb.equal(c.get("deleted"), "false");
		cq.where(condition);
		TypedQuery<Configuration> q = em.createQuery(cq); 
		List<Configuration> result = q.getResultList();

		assertTrue("result should be empty", result.size()==0);

		// add new configuration
		Configuration conf = new Configuration();
		conf.setStarttime(new Date());
		conf.setDeleted("false");
		conf.setConf_key("key1");
		conf.setConf_value("value1");

		conf = em.merge(conf);
		
		result = q.getResultList();
		assertTrue("result should not be empty", result.size()>0);

		// delete configuration
		Long id = conf.getConfiguration_id();

		conf = em.find(Configuration.class, id);
		em.remove(conf);

		result = q.getResultList();
		assertTrue("result should be empty", result.size() == 0);
	}

}
