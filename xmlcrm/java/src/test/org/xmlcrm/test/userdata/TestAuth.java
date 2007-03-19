package org.xmlcrm.test.userdata;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.Transaction;

import org.xmlcrm.app.hibernate.utils.HibernateUtil;
import org.xmlcrm.app.hibernate.beans.basic.Sessiondata;
import org.xmlcrm.app.remote.MainService;
import org.xmlcrm.utils.math.MD5Calc;

public class TestAuth extends TestCase{
	Session session = HibernateUtil.currentSession();

	public TestAuth(String testname) {
		super(testname);
		// TODO Auto-generated constructor stub
	}
	
	public void testTestAuth() {
		Transaction transaction = session.beginTransaction();
		
		MainService mService = new MainService();
		Sessiondata sessionData = mService.getsessiondata();
		
		System.out.println("sessionData: "+sessionData.getSession_id());
		
		MD5Calc md5 = new MD5Calc("MD5");
		
		String tTemp = md5.do_checksum("test");
		
		System.out.println("tTemp: "+tTemp);
		
		transaction.commit();
		session.clear();
	}	

}
