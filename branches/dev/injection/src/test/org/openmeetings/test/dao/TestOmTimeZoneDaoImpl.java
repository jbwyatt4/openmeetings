package org.openmeetings.test.dao;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import org.openmeetings.app.data.basic.dao.OmTimeZoneDaoImpl;
import org.openmeetings.app.persistence.beans.basic.OmTimeZone;
import org.openmeetings.test.AbstractOpenmeetingsSpringTest;
import org.springframework.beans.factory.annotation.Autowired;

public class TestOmTimeZoneDaoImpl extends AbstractOpenmeetingsSpringTest {

	@Autowired
	private OmTimeZoneDaoImpl omTimeZoneDaoImpl;

	final public void testOmTimeZoneDaoImpl() {
		OmTimeZoneDaoImpl timeZoneDao = omTimeZoneDaoImpl;
		assertNotNull("Cann't access to time zones dao implimentation",
				timeZoneDao);
		assertTrue("Count of time zones should be more than zero : "
				+ timeZoneDao.getOmTimeZones().size(), timeZoneDao
				.getOmTimeZones().size() > 0);

		// add new time zone
		String tzName = "Test_TimeZoneName";
		String tzLabel = "Test_TimeZoneLabel";
		String clName = "Test_iCal";
		Integer tzOrderId = 10000;

		// add new time zone
		Long tzId = timeZoneDao.addOmTimeZone(tzName, tzLabel, clName,
				tzOrderId);
		assertTrue("Time zones should be positive number : " + tzId, tzId > 0);

		// get time zone
		OmTimeZone omTimeZone = timeZoneDao.getOmTimeZoneById(tzId);
		assertNotNull("Time zone should not be null", omTimeZone);
		assertEquals("Time zone name should be " + tzName, tzName,
				omTimeZone.getJname());
		assertEquals("Time zone label should be " + tzLabel, tzLabel,
				omTimeZone.getLabel());
		assertEquals("Time zone iCal should be " + clName, clName,
				omTimeZone.getIcal());
		assertEquals("Time zone order ID should be " + tzOrderId, tzOrderId,
				omTimeZone.getOrderId());

		// delete time zone is not implemented for OmTimeZoneDaoImpl.
	}

}
