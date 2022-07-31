package com.gl.capstoneproject.doconnect.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gl.capstoneproject.doconnect.Config.MailConfig;
import com.gl.capstoneproject.doconnect.Entity.Question;
import com.gl.capstoneproject.doconnect.Model.Questions;
import com.gl.capstoneproject.doconnect.Repository.AdminRepository;
import com.gl.capstoneproject.doconnect.Repository.QuestionRepository;
import com.gl.capstoneproject.doconnect.Service.QuestionsService;
import com.gl.capstoneproject.doconnect.Util.HashGenerator;

@Service
public class QuestionserviceImpl implements QuestionsService {

	@Autowired
	private QuestionRepository quesRepo;

	@Autowired
	private AdminRepository adminRepo;

	@Autowired
	private MailConfig mail;

	ObjectMapper mapper = new ObjectMapper();

	public String askQuestion(Questions questions) {
		try {
			String question = questions.getQuestion();
			String askedBy = questions.getAskedBy();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			String askedAt = dateFormat.format(date);

			Question ques = new Question();
			ques.setQuestion(question);
			ques.setId(HashGenerator.getHashCode());
			ques.setAskedBy(askedBy);
			ques.setAskedAt(askedAt);
			ques.setApproval(false);
			quesRepo.save(ques);

			String mailBody = "User " + askedBy + " has asked an question! The details are as follows :\n"
					+ "Question : " + question + "\n" + "Asked By : " + askedBy + "\n" + "Asked At : " + askedAt + "\n"
					+ "Please validate and approve the question!";

			List<String> emails = adminRepo.FindAllAdminEmails();
			for (String email : emails) {
				mail.sendMail(email, "New Question!!", mailBody);
			}
			;

			return "question posted";
		} catch (Exception e) {
			return null;
		}
	}

	public String searchQuestion(String searchText) {
		try {
			List<Question> searchResults = quesRepo.findByQuestionIgnoreCaseContaining(searchText);
			String response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(searchResults);
			return response;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getUnapprovedQuestions() {
		try {
			List<Question> unapprovedQuestions = quesRepo.findAllUnapprovedQuestions();
			String response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(unapprovedQuestions);
			return response;
		} catch (Exception e) {
			return null;
		}
	}
	

	@Override
	public String approveQuestion(String id) {
		try {
			quesRepo.updateAsApproved(id);
			Question question = quesRepo.findByQuestionId(id);
			String response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(question);
			return response;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Boolean deleteQuestion(String id) {
		quesRepo.deleteQuestion(id);
		return true;
	}

	@Override
	public String getApprovedQuestions() {
		// TODO Auto-generated method stub
		try {
		List<Question> approvedQuestions=quesRepo.findAllApprovedQuestions();
		String response=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(approvedQuestions);
		return response;
		}catch(Exception e){
			return null;
			
		}
	}
	
	@Override
	public String getAllQuestions() {
		// TODO Auto-generated method stub
		try {
		List<Question> approvedQuestions=quesRepo.findAll();
		String response=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(approvedQuestions);
		return response;
		}catch(Exception e){
			return null;
			
		}
	}
}