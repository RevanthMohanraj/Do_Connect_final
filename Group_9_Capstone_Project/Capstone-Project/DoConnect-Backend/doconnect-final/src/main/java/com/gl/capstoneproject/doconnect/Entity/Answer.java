package com.gl.capstoneproject.doconnect.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="answers")
@Data
public class Answer {
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Question getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Question questionId) {
		this.questionId = questionId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAnsweredBy() {
		return answeredBy;
	}
	public void setAnsweredBy(String answeredBy) {
		this.answeredBy = answeredBy;
	}
	public String getAnsweredAt() {
		return answeredAt;
	}
	public void setAnsweredAt(String answeredAt) {
		this.answeredAt = answeredAt;
	}
	public Boolean getApproval() {
		return approval;
	}
	public void setApproval(Boolean approval) {
		this.approval = approval;
	}
	@Id
	private String id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="question_id",nullable=false)
	private Question questionId;
	
	private String answer; 
	private String answeredBy;
	private String answeredAt;
	private Boolean approval;
}