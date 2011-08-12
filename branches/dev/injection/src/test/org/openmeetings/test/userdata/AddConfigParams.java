package org.openmeetings.test.userdata;

import junit.framework.TestCase;

import org.openmeetings.app.data.basic.Configurationmanagement;
import org.openmeetings.app.persistence.beans.basic.Sessiondata;
import org.openmeetings.app.persistence.beans.user.Users;
import org.openmeetings.app.remote.MainService;
import org.springframework.beans.factory.annotation.Autowired;

public class AddConfigParams extends TestCase {
	@Autowired //FIXME
	private Configurationmanagement cfgManagement;
	
	public AddConfigParams(String testname){
		super(testname);
	}
	
	public void testAddConfigParams(){
		
		MainService mService = new MainService();
		Sessiondata sessionData = mService.getsessiondata();
		
		Users us = (Users) mService.loginUser(sessionData.getSession_id(), "wagner", "test",false,null,-1L);		
		
		String ret = "";
		
		//ret = Configurationmanagement.getInstance().addConfByKey(3, "allow_frontend_register", "1", us.getUser_id().intValue(), "coment");
		
		System.out.println("ret: "+ret);
		
		ret = cfgManagement.addConfByKey(3, "default_group_id", "1", us.getUser_id(), "coment");
		
		
		
	}

}
