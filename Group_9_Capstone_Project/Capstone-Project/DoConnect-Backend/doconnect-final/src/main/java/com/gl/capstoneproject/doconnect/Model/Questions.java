package com.gl.capstoneproject.doconnect.Model;

import lombok.Data;

@Data
public class Questions {
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAskedBy() {
		return askedBy;
	}
	public void setAskedBy(String askedBy) {
		this.askedBy = askedBy;
	}
	public String getAskedAt() {
		return askedAt;
	}
	public void setAskedAt(String askedAt) {
		this.askedAt = askedAt;
	}
	public Boolean getApproval() {
		return approval;
	}
	public void setApproval(Boolean approval) {
		this.approval = approval;
	}
	private String id;
	private String question;
	private String askedBy;
	private String askedAt;
	private Boolean approval;
}