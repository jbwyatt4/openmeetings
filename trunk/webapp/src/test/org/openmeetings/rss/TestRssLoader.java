package org.openmeetings.rss;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.red5.logging.Red5LoggerFactory;

import org.openmeetings.app.rss.LoadAtomRssFeed;

public class TestRssLoader extends TestCase {
	
	private static final Logger log = Red5LoggerFactory.getLogger(TestRssLoader.class, "openmeetings");
	
	public TestRssLoader(String testname){
		super(testname);
	}
	
	public void testLoadRssFeed(){
		String url = "http://groups.google.com/group/openmeetings-user/feed/atom_v1_0_msgs.xml";
		
		LoadAtomRssFeed.getInstance().parseRssFeed(url);
	}

}
