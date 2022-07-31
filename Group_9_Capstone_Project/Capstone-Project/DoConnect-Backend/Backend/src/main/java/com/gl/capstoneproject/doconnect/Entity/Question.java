package com.gl.capstoneproject.doconnect.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="questions")
@Data
public class Question {
	
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
	@Id
	private String id;
	private String question;
	private String askedBy;
	private String askedAt;
	private Boolean approval;
	
}
