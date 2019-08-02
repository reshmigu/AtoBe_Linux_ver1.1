package com.test.xrayapis;

public class CreateIssueDTO {

	private String key;
	private String name;
	private String description;
	private String summary;
	private String testKey;
	public String getTestKey() {
		return testKey;
	}
	public void setTestKey(String testKey) {
		this.testKey = testKey;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
}
