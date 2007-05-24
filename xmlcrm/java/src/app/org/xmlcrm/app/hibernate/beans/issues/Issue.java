package org.xmlcrm.app.hibernate.beans.issues;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author asafarian
 *
 */
public class Issue implements Comparable<Issue> {

	private Issue parentIssue = null;
	private List<Issue> subIssues =null;
	private int id =0;
	private String title ="";
	private String content ="";
	
	public Issue()
	{
		subIssues = new LinkedList<Issue>();
	}
	
	public String getContent() { 
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<Issue> getSubIssues() {
		return subIssues;
	}
	public void setSubIssues(List<Issue> subIssues) {
		this.subIssues = subIssues;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int compareTo(Issue o2) {
		return this.getTitle().compareTo(o2.getTitle()) ;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Issue getParentIssue() {
		return parentIssue;
	}

	public void setParentIssue(Issue parentIssue) {
		this.parentIssue = parentIssue;
	}
	
	public boolean removeSubIssue(Issue issue) {
		boolean removed = false;
		if(issue!=null && this.getSubIssues()!=null)
		{
			if(this.getSubIssues().remove(issue))
			{
				removed = true;
			}else
			{
				for( Issue subIssue : this.getSubIssues() )
				{
					removed = subIssue.removeSubIssue(issue);
					if(removed)
					{
						break;
					}
					
				}
			}
			
		}
		return removed;
	}
	
}
