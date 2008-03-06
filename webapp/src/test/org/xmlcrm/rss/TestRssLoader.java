package org.xmlcrm.rss;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.xmlcrm.app.rss.LoadAtomRssFeed;

public class TestRssLoader extends TestCase {
	
	private static final Log log = LogFactory.getLog(TestRssLoader.class);
	
	public TestRssLoader(String testname){
		super(testname);
	}
	
	public void testLoadRssFeed(){
		String url = "http://groups.google.com/group/openmeetings-user/feed/atom_v1_0_msgs.xml";
		
		LoadAtomRssFeed.getInstance().parseRssFeed(url);
	}

}
