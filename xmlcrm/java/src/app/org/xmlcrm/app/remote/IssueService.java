package org.xmlcrm.app.remote;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmlcrm.app.hibernate.beans.issues.Issue;
import org.xmlcrm.app.hibernate.beans.issues.Project;

/**
 * 
 * @author asafarian
 *
 */
public class IssueService {

	private static final Log log = LogFactory.getLog(IssueService.class);
	
	// Set Global Saver Maps for Objects
	private static Map<Integer, Project> projektDBTable = new HashMap<Integer, Project>();
	
	
	// Create initial Data
	private void initData()	{
//		projektDBTable.put(new Integer(1), new Project(1,"Project DA - RAT - Videoconferencing"));
//		projektDBTable.put(new Integer(2), new Project(2,"Project DA - Request Flash/DHTML"));
		
		// Create P1

		Project p1 = new Project(1,"Project DA - RAT - Videoconferencing");

		// Add Issue

		Issue rootIssue = new Issue();

		Random rand = new Random();
		Issue issue = new Issue();
		issue.setId(rand.nextInt());
		issue.setTitle("VideoConf Issue 1");
		issue.setContent("Main Content Issue 1");
		issue.setParentIssue(null);
		p1.getIssuesDBTable().put(issue.getId(), rootIssue);
		p1.getIssuesList().add(issue);

		projektDBTable.put(new Integer(1), p1);

		// Create P2

		Project p2 = new Project(2,"Project DA - Request Flash/DHTML");

		// Add Issue
		issue = new Issue();
		issue.setId(rand.nextInt());
		issue.setTitle("erstes Issue");
		issue.setContent("Content 1");
		issue.setParentIssue(null);
		p2.getIssuesDBTable().put(issue.getId(), rootIssue);
		p2.getIssuesList().add(issue);	
		
		projektDBTable.put(new Integer(2), p2);
	}
	
	// get Project List
	public List<Project> getProjects()
	{
		try {
			List<Project> projectlist = new LinkedList<Project>();
			
			//init data swagner
			if (projektDBTable.size()==0) this.initData();
			
			for (Project project : projektDBTable.values()) {
				projectlist.add(project);
			}
			
			ProjectComparator comp = new ProjectComparator();
			Collections.sort(projectlist, comp);
			return projectlist;
		} catch (Exception err) {
			log.error("getProjects",err);
		}
		return null;
	}
	
	// Get Issue List (Tree) for Project
	public List<Issue> getIssuesForProject(int projectid)
	{
		return projektDBTable.get(new Integer( projectid)).getIssuesList();
	}
	
	// Get Content (Issue Object) for Issue
	public Issue getIssueById(int projectid,int issueId)
	{
		return projektDBTable.get(new Integer( projectid)).getIssuesDBTable().get(new Integer(issueId));
	}
	
	// Set Content of Issue by Id
	public void setIssueContent(int projectid,int issueId, String title, String content)
	{
		Issue issue = projektDBTable.get(new Integer( projectid)).getIssuesDBTable().get(new Integer(issueId));
		issue.setTitle(title);
		issue.setContent(content);
	}
	
	// Create new Issue in a given Node of the IssueTree , with Parrent Objekt
	public int createIssueForParent(int projectid,int parentIssueId, String title, String content)
	{
		Random rand = new Random();
		Issue parentIssue = projektDBTable.get(new Integer( projectid)).getIssuesDBTable().get(new Integer(parentIssueId));
		Issue issue = new Issue();
		issue.setId(rand.nextInt());
		issue.setTitle(title);
		issue.setContent(content);
		issue.setParentIssue(parentIssue);
		
		parentIssue.getSubIssues().add(issue);
		
		return issue.getId();
	}
	
	// Delete Issue by Id
	public int deleteIssue(int projectid,int issueId)
	{
		boolean removed = projektDBTable.get(new Integer( projectid)).getIssuesDBTable().remove(new Integer(issueId))!=null;
		
		return  removed ? 0 : 1;
	}
	
	// Move Issue
	public int moveIssue(int projectid,int issueToMoveId, int parentIssueId )
	{
		Issue parentIssue = projektDBTable.get(new Integer( projectid)).getIssuesDBTable().get(new Integer(parentIssueId));
		Issue issue = projektDBTable.get(new Integer( projectid)).getIssuesDBTable().get(new Integer(issueToMoveId));
		issue.getParentIssue().getSubIssues().remove(issue);
		
		issue.setParentIssue(parentIssue);
		parentIssue.getSubIssues().add(issue);
		
		return 1;
	}
	
	private class IssueComparator implements Comparator<Issue>
	{

		public int compare(Issue o1, Issue o2) {
			if(o1!=null || o2!=null)
			return 0;
			
			return o1.getTitle().compareTo(o2.getTitle()) ;
		}
		
	}
	
	private class ProjectComparator implements Comparator<Project>
	{

		public int compare(Project o1, Project o2) {
			if(o1!=null || o2!=null)
			return 0;
			
			return o1.getTitle().compareTo(o2.getTitle()) ;
		}
		
	}

}
