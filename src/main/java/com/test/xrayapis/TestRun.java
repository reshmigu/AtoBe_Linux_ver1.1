package com.test.xrayapis;

import java.util.Arrays;
import java.util.Date;

public class TestRun {

	private int id;
	private String status;
	private String color;
	private String testKey;
	private String testExecKey;
	private String assignee;
	private String executedBy;
	private long duration;

	private Date startedOn;
	private Date finishedOn;
	private Date startedOnIso;
	private Date finishedOnIso;
	private String[] defects;
	private String[] evidences;
	private String[] steps;
	private String[] testEnvironments;
	private String[] fixVersions;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getTestKey() {
		return testKey;
	}

	public void setTestKey(String testKey) {
		this.testKey = testKey;
	}

	public String getTestExecKey() {
		return testExecKey;
	}

	public void setTestExecKey(String testExecKey) {
		this.testExecKey = testExecKey;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getExecutedBy() {
		return executedBy;
	}

	public void setExecutedBy(String executedBy) {
		this.executedBy = executedBy;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public Date getStartedOn() {
		return startedOn;
	}

	public void setStartedOn(Date startedOn) {
		this.startedOn = startedOn;
	}

	public Date getFinishedOn() {
		return finishedOn;
	}

	public void setFinishedOn(Date finishedOn) {
		this.finishedOn = finishedOn;
	}

	public Date getStartedOnIso() {
		return startedOnIso;
	}

	public void setStartedOnIso(Date startedOnIso) {
		this.startedOnIso = startedOnIso;
	}

	public Date getFinishedOnIso() {
		return finishedOnIso;
	}

	public void setFinishedOnIso(Date finishedOnIso) {
		this.finishedOnIso = finishedOnIso;
	}

	public String[] getDefects() {
		return defects;
	}

	public void setDefects(String[] defects) {
		this.defects = defects;
	}

	public String[] getEvidences() {
		return evidences;
	}

	public void setEvidences(String[] evidences) {
		this.evidences = evidences;
	}

	public String[] getSteps() {
		return steps;
	}

	public void setSteps(String[] steps) {
		this.steps = steps;
	}

	public String[] getTestEnvironments() {
		return testEnvironments;
	}

	public void setTestEnvironments(String[] testEnvironments) {
		this.testEnvironments = testEnvironments;
	}

	public String[] getFixVersions() {
		return fixVersions;
	}

	public void setFixVersions(String[] fixVersions) {
		this.fixVersions = fixVersions;
	}

	@Override
	public String toString() {
		return "TestRun [id=" + id + ", status=" + status + ", color=" + color + ", testKey=" + testKey
				+ ", testExecKey=" + testExecKey + ", assignee=" + assignee + ", executedBy=" + executedBy
				+ ", duration=" + duration + ", startedOn=" + startedOn + ", finishedOn=" + finishedOn
				+ ", startedOnIso=" + startedOnIso + ", finishedOnIso=" + finishedOnIso + ", defects="
				+ Arrays.toString(defects) + ", evidences=" + Arrays.toString(evidences) + ", steps="
				+ Arrays.toString(steps) + ", testEnvironments=" + Arrays.toString(testEnvironments) + ", fixVersions="
				+ Arrays.toString(fixVersions) + "]";
	}

}
