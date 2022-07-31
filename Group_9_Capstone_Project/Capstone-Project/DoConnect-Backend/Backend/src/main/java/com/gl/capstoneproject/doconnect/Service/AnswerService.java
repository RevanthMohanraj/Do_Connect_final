package com.gl.capstoneproject.doconnect.Service;

import com.gl.capstoneproject.doconnect.Model.Answers;

public interface AnswerService {

	public String saveAnswer(Answers body);
	public String getUnapprovedAnswers();
	public String approveAnswer(String answerId);
	public Boolean deleteAnswer(String answerId);
	public String getApprovedAnswers();
	public String getAnswersByQuestionId(String questionID);
}
