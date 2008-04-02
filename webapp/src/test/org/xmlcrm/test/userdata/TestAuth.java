package org.xmlcrm.test.userdata;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.Transaction;

import org.xmlcrm.app.hibernate.utils.HibernateUtil;
import org.xmlcrm.app.hibernate.beans.basic.Sessiondata;
import org.xmlcrm.app.remote.MainService;
import org.xmlcrm.utils.crypt.ManageCryptStyle;

public class TestAuth extends TestCase{


	public TestAuth(String testname) {
		super(testname);
		// TODO Auto-generated constructor stub
	}
	
	public void testTestAuth() {
		
		MainService mService = new MainService();
		Sessiondata sessionData = mService.getsessiondata();
		
		System.out.println("sessionData: "+sessionData.getSession_id());
		
		String tTemp = ManageCryptStyle.getInstance().getInstanceOfCrypt().createPassPhrase("test");
		
		System.out.println("tTemp: "+tTemp);
		

	}	

}
