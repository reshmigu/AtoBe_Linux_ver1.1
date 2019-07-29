package com.test.xrayapis;

import java.util.Arrays;
import java.util.Date;

public class TestExecution {
	private String id;
	private String status;
	private String assignee;
	private String executedBy;
	private Date startedOn;
	private Date finishedOn;
	private String[] defects;
	private String[] evidences;
	private String key;
	private int rank;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "TestExecution [id=" + id + ", status=" + status + ", assignee=" + assignee + ", executedBy="
				+ executedBy + ", startedOn=" + startedOn + ", finishedOn=" + finishedOn + ", defects="
				+ Arrays.toString(defects) + ", evidences=" + Arrays.toString(evidences) + ", key=" + key + ", rank="
				+ rank + "]";
	}
}
