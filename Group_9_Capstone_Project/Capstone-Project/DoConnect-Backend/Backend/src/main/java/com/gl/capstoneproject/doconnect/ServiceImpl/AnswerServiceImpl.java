package com.gl.capstoneproject.doconnect.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gl.capstoneproject.doconnect.Config.MailConfig;
import com.gl.capstoneproject.doconnect.Entity.Answer;
import com.gl.capstoneproject.doconnect.Entity.Question;
import com.gl.capstoneproject.doconnect.Model.Answers;
import com.gl.capstoneproject.doconnect.Repository.AdminRepository;
import com.gl.capstoneproject.doconnect.Repository.AnswerRepository;
import com.gl.capstoneproject.doconnect.Repository.QuestionRepository;
import com.gl.capstoneproject.doconnect.Service.AnswerService;
import com.gl.capstoneproject.doconnect.Util.HashGenerator;
import com.mysql.cj.xdevapi.JsonArray;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private QuestionRepository quesRepo;

	@Autowired
	private AnswerRepository ansRepo;

	@Autowired
	private AdminRepository adminRepo;

	@Autowired
	private MailConfig mail;

	@Autowired
	private ObjectMapper mapper;

	public String saveAnswer(Answers body) {

		String questionId = body.getQuestionId();
		String answer = body.getAnswer();
		String answeredBy = body.getAnsweredBy();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		String answeredAt = dateFormat.format(date);

		Answer saveAns = new Answer();
		saveAns.setId(HashGenerator.getHashCode());

		Question que = new Question();
		que.setId(questionId);
		saveAns.setQuestionId(que);

		saveAns.setAnswer(answer);
		saveAns.setAnsweredAt(answeredAt);
		saveAns.setAnsweredBy(answeredBy);
		saveAns.setApproval(false);
		ansRepo.save(saveAns);

		String question = quesRepo.findQuestionById(body.getQuestionId());
		String mailBody = "User " + answeredBy + " has entered an answer! The details are as follows :\n"
				+ "Question : " + question + "\n" + "Answer : " + answer + "\n" + "Answered By : " + answeredBy + "\n"
				+ "Answered At : " + answeredAt + "\n" + "Please validate and approve the answer!";

		List<String> emails = adminRepo.FindAllAdminEmails();
		for (String email : emails) {
			mail.sendMail(email, "New Answer!!", mailBody);
		}

		return "Your answer is saved";
	}

	public String getUnapprovedAnswers() {
		try {
			List<Answer> unapprovedAnswers = ansRepo.findAllUnapprovedAnswers();
			String response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(unapprovedAnswers);
			return response;
		} catch (Exception e) {
			return null;
		}
	}

	public String approveAnswer(String answerId) {
		try {
			ansRepo.updateAsApproved(answerId);
			Answer answer = ansRepo.findByAnswerId(answerId);
			String response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(answer);
			System.out.println(response);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean deleteAnswer(String answerId) {
		ansRepo.deleteAnswer(answerId);
		
		return true;
	}

	@Override
	public String getApprovedAnswers() {
		try {
			List<Answer> approvedAnswers = ansRepo.findAllApprovedAnswers();
			String response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(approvedAnswers);
			return response;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public String getAnswersByQuestionId(String questionID) {
		try {
			List<Answer> answers = ansRepo.findAnswersByQuestionId(questionID);
			String response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(answers);
			return response;
		} catch (Exception e) {
			return null;
		}
	}
}