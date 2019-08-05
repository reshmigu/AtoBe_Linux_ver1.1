package com.test.xrayapis;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class JasperReportDTO {

	public String getXrayLink() {
		return xrayLink;
	}
	public void setXrayLink(String xrayLink) {
		this.xrayLink = xrayLink;
	}
	private String projectName;
	private String issueId;
	private String issueIdLink;
	private String summary;
	private String description;
	private String executedBy;
	private String assignee;
	private Date startedDate;
	private Date endDate;
	private String xrayLink;
	private List<JasperBugDTO> jasperBugDTO;
	
	
	public List<JasperBugDTO> getJasperBugDTO() {
		return jasperBugDTO;
	}
	public void setJasperBugDTO(List<JasperBugDTO> jasperBugDTO) {
		this.jasperBugDTO = jasperBugDTO;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getIssueId() {
		return issueId;
	}
	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStartedDate() {
		return startedDate;
	}
	public void setStartedDate(Date localDate) {
		this.startedDate = localDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getExecutedBy() {
		return executedBy;
	}
	public void setExecutedBy(String executedBy) {
		this.executedBy = executedBy;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getIssueIdLink() {
		return issueIdLink;
	}
	public void setIssueIdLink(String issueIdLink) {
		this.issueIdLink = issueIdLink;
	}


}
