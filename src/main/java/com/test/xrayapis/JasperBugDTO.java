package com.test.xrayapis;

public class JasperBugDTO {
	private String testCaseId;
	private String testSummary;
	private String testStatus;
	private String linkedBugId;
	private String testCaseLink;
	private String bugLink;
	public String getTestCaseLink() {
		return testCaseLink;
	}
	public void setTestCaseLink(String testCaseLink) {
		this.testCaseLink = testCaseLink;
	}
	public String getBugLink() {
		return bugLink;
	}
	public void setBugLink(String bugLink) {
		this.bugLink = bugLink;
	}
	public String getTestCaseId() {
		return testCaseId;
	}
	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}
	public String getTestSummary() {
		return testSummary;
	}
	public void setTestSummary(String testSummary) {
		this.testSummary = testSummary;
	}
	public String getTestStatus() {
		return testStatus;
	}
	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}
	public String getLinkedBugId() {
		return linkedBugId;
	}
	public void setLinkedBugId(String linkedBugId) {
		this.linkedBugId = linkedBugId;
	}
}
