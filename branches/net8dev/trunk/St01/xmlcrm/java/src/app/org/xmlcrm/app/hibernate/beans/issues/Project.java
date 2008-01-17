package org.xmlcrm.app.hibernate.beans.issues;

import java.io.Serializable;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;

import java.util.Map;

public class Project implements Serializable {

	private List<Issue> issuesList = new ArrayList<Issue>();

	private Map<Integer, Issue> issuesDBTable = new HashMap<Integer, Issue>();

	private int id = 0;

	private String title = "";

	public Project(int id, String string) {

		this.id = id;

		this.title = string;

	}

	public Map<Integer, Issue> getIssuesDBTable() {

		return issuesDBTable;

	}

	public void setIssuesDBTable(Map<Integer, Issue> issuesDBTable) {

		this.issuesDBTable = issuesDBTable;

	}

	public List<Issue> getIssuesList() {

		return issuesList;

	}

	public void setIssuesList(List<Issue> issuesList) {

		this.issuesList = issuesList;

	}

	public String getTitle() {

		return title;

	}

	public void setTitle(String title) {

		this.title = title;

	}

	public int getId() {

		return id;

	}

	public void setId(int id) {

		this.id = id;

	}

}
