package org.xmlcrm.test.userdata;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.Transaction;

import org.xmlcrm.app.hibernate.utils.HibernateUtil;
import org.xmlcrm.app.hibernate.beans.basic.Sessiondata;
import org.xmlcrm.app.hibernate.beans.user.Users;
import org.xmlcrm.app.remote.MainService;
import org.xmlcrm.app.data.basic.Configurationmanagement;
import org.xmlcrm.app.data.user.Usermanagement;

public class RegisterUser extends TestCase{
	
	Session session = HibernateUtil.currentSession();

	public RegisterUser(String testname) {
		super(testname);
		// TODO Auto-generated constructor stub
	}
	
	public void testRegisterUser(){
		
		MainService mService = new MainService();
		Sessiondata sessionData = mService.getsessiondata();
		
		Users us = mService.loginUser(sessionData.getSession_id(), "swagner", "67810");		
		
		//Configurationmanagement.getInstance().addConfByKey(3, "allow_frontend_register", "1", 2, "coment");

		
		//registerUser(String SID, String Username, String Userpass, String lastname, String firstname, String email, int age, String adresse, String Zip, String state, String town)
		String answer = mService.registerUser(sessionData.getSession_id(),"swagner6", "678101", "Wagner", "Sebastian", "seba.wagner@gmail.com", 25, "Bleichstraﬂe 92", "75173", "Germany", "Pforzheim", 1);
		
		System.out.println("++++ answer: "+answer);
		
	}
/*
 * registerUser(long USER_LEVEL,String Username, String Userpass, String lastname, String firstname, String email, int age, String adresse, String Zip, String state, String town)
 */
}
