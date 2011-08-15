package org.openmeetings.test.userdata;

import org.junit.Test;
import org.openmeetings.app.data.basic.Configurationmanagement;
import org.openmeetings.app.persistence.beans.basic.Sessiondata;
import org.openmeetings.app.persistence.beans.user.Users;
import org.openmeetings.app.remote.MainService;
import org.openmeetings.test.AbstractOpenmeetingsSpringTest;
import org.springframework.beans.factory.annotation.Autowired;

public class TestAddConfigParams extends AbstractOpenmeetingsSpringTest {
	@Autowired
	private Configurationmanagement cfgManagement;
	@Autowired
	private MainService mainService;

	@Test
	public void testAddConfigParams(){
		
		Sessiondata sessionData = mainService.getsessiondata();
		
		Users us = (Users) mainService.loginUser(sessionData.getSession_id(), "wagner", "test",false,null,-1L);		
		
		String ret = "";
		
		//ret = Configurationmanagement.getInstance().addConfByKey(3, "allow_frontend_register", "1", us.getUser_id().intValue(), "coment");
		
		System.out.println("ret: "+ret);
		
		ret = cfgManagement.addConfByKey(3, "default_group_id", "1", us.getUser_id(), "coment");
		
		
		
	}

}
