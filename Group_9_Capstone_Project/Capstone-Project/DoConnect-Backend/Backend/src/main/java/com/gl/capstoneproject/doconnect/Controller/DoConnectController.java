package com.gl.capstoneproject.doconnect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.capstoneproject.doconnect.Model.Admins;
import com.gl.capstoneproject.doconnect.Model.Answers;
import com.gl.capstoneproject.doconnect.Model.Questions;
import com.gl.capstoneproject.doconnect.Service.AdminService;
import com.gl.capstoneproject.doconnect.Service.AnswerService;
import com.gl.capstoneproject.doconnect.Service.QuestionsService;
import com.gl.capstoneproject.doconnect.Service.UserService;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
public class DoConnectController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private QuestionsService quesService; 
	
	@Autowired
	private AnswerService ansService;
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("user/login")
	public String login(@RequestBody String body) {
		return userService.login(body);
	}
	
	@PostMapping("user/register")
	public String registration(@RequestBody String body) {		 
		return userService.register(body);
	}
	
	@PostMapping("admin/login")
	public String adminLogin(@RequestBody Admins admin) {
		return adminService.adminLogin(admin);
	}
	
	@PostMapping("admin/register")
	public String adminRegister(@RequestBody Admins admin) {
		return adminService.adminRegister(admin);
	}
	
	@PostMapping("question/ask")
	public String askquestion(@RequestBody Questions questions) {
		return quesService.askQuestion(questions);
	}
	
	@PostMapping("question/search")
	public String searchQuestion(@RequestBody String searchText) {
		return quesService.searchQuestion(searchText);
	}
	
	@PostMapping("answer/save")
	public String saveAns(@RequestBody Answers ans) {
		return ansService.saveAnswer(ans);////need to pass question id to backend
	}
	
	@GetMapping("admin/question/unapproved")
	public String getUnapprovedQuestions() {
		return quesService.getUnapprovedQuestions();
	}
	
	@GetMapping("admin/answer/unapproved")
	public String getUnapprovedAnswers() {
		return ansService.getUnapprovedAnswers();
	}
	
	@GetMapping("admin/question/approved")
	public String getApprovedQuestions() {
		return quesService.getApprovedQuestions();
	}
	
	@GetMapping("admin/allquestions")
	public String getAllQuestions() {
		return quesService.getAllQuestions();
	}
	
	@GetMapping("admin/answer/approved")
	public String getApprovedAnswers() {
		return ansService.getApprovedAnswers();
	}
	
	@PostMapping("admin/question/approve")
	public String approveQuestion(@RequestParam String questionId) {
		return quesService.approveQuestion(questionId);////need to pass question id to backend
	}

	@PostMapping("admin/answer/approve")
	public String approveAnswer(@RequestParam String answerId) {
		return ansService.approveAnswer(answerId);
	}
	
	@PostMapping("admin/question/delete")
	public Boolean deleteQuestion(@RequestParam String questionId) {
		return quesService.deleteQuestion(questionId);//need to pass question id to backend
	}
	
	@PostMapping("admin/answer/delete")
	public Boolean deleteAnswer(@RequestParam String answerId) {
		return ansService.deleteAnswer(answerId);
	}
	
	@PostMapping("answer/byquestionid")
	public String findAnswerByQuestionId(@RequestParam String questionId) {
		return ansService.getAnswersByQuestionId(questionId);
	}
	
}