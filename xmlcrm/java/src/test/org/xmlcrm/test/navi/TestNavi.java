package org.xmlcrm.test.navi;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.xmlcrm.app.hibernate.beans.basic.Sessiondata;
import org.xmlcrm.app.hibernate.beans.user.Users;
import org.xmlcrm.app.remote.MainService;
import org.xmlcrm.app.hibernate.beans.basic.*;

import junit.framework.TestCase;

public class TestNavi extends TestCase {
	
	public TestNavi(String testname){
		super(testname);
	}
	
	public void testGetNavi(){
		
		MainService mService = new MainService();
		Sessiondata sessionData = mService.getsessiondata();
		
		Users us = mService.loginUser(sessionData.getSession_id(), "swagner", "67810");
		
		System.out.println("us: "+us.getFirstname());
		
		List ll = mService.getNavi(sessionData.getSession_id(),1);
		
		System.out.println("NaviGlobal size: "+ll.size());
		
		for (Iterator it2 = ll.iterator(); it2.hasNext();) {
			Naviglobal navigl = (Naviglobal) it2.next();
			System.out.println(navigl.getLabel().getValue());
			Set s = navigl.getMainnavi();
			
			for (Iterator it3 = s.iterator(); it3.hasNext();) {
				Navimain navim = (Navimain) it3.next();
				System.out.println("-->"+navim.getLabel().getValue());
				
				for (Iterator it4 = navim.getSubnavi().iterator(); it4.hasNext();) {
					Navisub navis = (Navisub) it4.next();
					System.out.println("---->"+navis.getLabel().getValue());
				}			
				
			}			
		}
		
	}

}
