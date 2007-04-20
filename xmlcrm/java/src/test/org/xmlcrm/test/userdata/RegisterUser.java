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
		Long user_id = mService.registerUser(sessionData.getSession_id(),"username3", "userpass", "lastname", 
				"firstname", "email", new java.util.Date(), "street", "No 14", 
				"faxno", "zip", 1, "town", 1);
		System.out.println("++++ user_id: "+user_id);
		
	}

}
