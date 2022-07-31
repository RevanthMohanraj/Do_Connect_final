package com.gl.capstoneproject.doconnect.Service;

import com.gl.capstoneproject.doconnect.Model.Questions;

public interface QuestionsService {
	
	public String askQuestion(Questions questions);
	public String searchQuestion(String searchText);
	public String getUnapprovedQuestions();
	public String getApprovedQuestions();
	public String approveQuestion(String id);
	public Boolean deleteQuestion(String id);
	public String getAllQuestions();
}