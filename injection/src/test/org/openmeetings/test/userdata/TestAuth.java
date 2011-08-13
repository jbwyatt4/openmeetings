package org.openmeetings.test.userdata;

import junit.framework.TestCase;

import org.openmeetings.app.persistence.beans.basic.Sessiondata;
import org.openmeetings.app.remote.MainService;
import org.openmeetings.utils.crypt.ManageCryptStyle;
import org.springframework.beans.factory.annotation.Autowired;

public class TestAuth extends TestCase {

	@Autowired
	private ManageCryptStyle manageCryptStyle;

	public TestAuth(String testname) {
		super(testname);
		// TODO Auto-generated constructor stub
	}

	public void testTestAuth() {

		MainService mService = new MainService();
		Sessiondata sessionData = mService.getsessiondata();

		System.out.println("sessionData: " + sessionData.getSession_id());

		String tTemp = manageCryptStyle.getInstanceOfCrypt().createPassPhrase(
				"test");

		System.out.println("tTemp: " + tTemp);

	}

}
