package org.xmlcrm.app.remote;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
	
	private int issuesCounter = 1;
	
	
	// Create initial Data
	private void initData()	{
//		projektDBTable.put(new Integer(1), new Project(1,"Project DA - RAT - Videoconferencing"));
//		projektDBTable.put(new Integer(2), new Project(2,"Project DA - Request Flash/DHTML"));
		
		// Create P1

		Project p1 = new Project(1,"Project DA - RAT - Videoconferencing");

		// Add Issue

		Issue rootIssue = new Issue();

		Issue issue = new Issue();
		issue.setId(issuesCounter++);
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
		issue.setId(issuesCounter++);
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
		try {
			log.error("setIssueContent projectid "+projectid);
			log.error("setIssueContent issueId "+issueId);
			Project project = projektDBTable.get(new Integer( projectid));
			Map<Integer, Issue> projectIssuesMap = project.getIssuesDBTable();
			Issue issue = projectIssuesMap.get(new Integer(issueId));
			
			log.error("found issues id: "+issue.getId());
			issue.setTitle(title);
			issue.setContent(content);
			
			projectIssuesMap.put(new Integer(issueId), issue);
			project.setIssuesDBTable(projectIssuesMap);
			projektDBTable.put(new Integer( projectid), project);
			
		} catch (Exception err){
			log.error("setIssueContent",err);
		}
	}
	
	// Create new Issue in a given Node of the IssueTree , with Parrent Objekt
	public int createIssueForParent(int projectid,int parentIssueId, String title, String content)
	{
		try {
			log.error("createIssueForParent projectid "+projectid);
			log.error("createIssueForParent parentIssueId "+parentIssueId);
			
			Project project = projektDBTable.get(new Integer( projectid));
			Map<Integer, Issue> projectIssuesMap = project.getIssuesDBTable();
			Issue parentissue = projectIssuesMap.get(new Integer(parentIssueId));
			
			log.error("found parentIssue id: "+parentissue.getId());
			Issue issue = new Issue();
			issue.setId(issuesCounter++);
			issue.setTitle(title);
			issue.setContent(content);
			issue.setParentIssue(parentissue);
			
			parentissue.getSubIssues().add(issue);
			
			projectIssuesMap.put(new Integer(parentIssueId), parentissue);
			project.setIssuesDBTable(projectIssuesMap);
			projektDBTable.put(new Integer( projectid), project);			
			
			return issue.getId();
		} catch (Exception err){
			log.error("createIssueForParent",err);
		}
		return -1;
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
