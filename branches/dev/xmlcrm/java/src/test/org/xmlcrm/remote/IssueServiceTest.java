package org.xmlcrm.remote;


import static org.junit.Assert.*;

import org.junit.Test;
import org.xmlcrm.app.hibernate.beans.issues.Issue;
import org.xmlcrm.app.remote.IssueService;

public class IssueServiceTest {

	private IssueService issueService = new IssueService();
	
	@Test
	public void testGetProjects() {
		assert(issueService.getProjects().size()>0);
		assert(issueService.getProjects().get(0).getIssuesList().size()>0);
	}

	@Test
	public void testGetIssuesForProject() {
		assert(issueService.getProjects().get(0).getIssuesList().size()>0);
	}

	@Test
	public void testGetIssueById() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetIssueContent() {
		Issue i_org = issueService.getProjects().get(0).getIssuesList().get(0);
		
		i_org.setContent("Test");
		
		Issue i_copy = issueService.getProjects().get(0).getIssuesList().get(0);
		assertTrue(i_org.getContent().equals(i_copy.getContent()));
	}

	@Test
	public void testCreateIssueForParent() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteIssue() {
		fail("Not yet implemented");
	}

	@Test
	public void testMoveIssue() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testSetContent() {
		Issue issue = issueService.getIssueById(1,1);
		assertNotNull(issue);
		
		issueService.setIssueContent(1, issue.getId(), "Test 1", "Test Content");
		
		issue = issueService.getIssueById(1,1);
		
		assertTrue(issue.getContent().equals("Test Content"));
		assertTrue(issue.getTitle().equals("Test 1"));
		
		
	}
	
	

}
