import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TabDirective } from 'ngx-bootstrap/tabs';
import { catchError, map } from 'rxjs';
import { ApiServices } from '../Service/api-services/api.service';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class QuestionComponent implements OnInit {

  currentUser: any = localStorage.getItem("loggedinUser");
  currentUserType: any = localStorage.getItem("loggedInUserType");
  myQuestion: any;
  searchingText: any;
  isLoading = false;
  isSearching = false;
  questionsWFA: any = [];
  approvedQuestions: any = [];
  messages: any = [];
  message: any;
  smessage: String | undefined;
  value: any | undefined;
  user: any;

  constructor(
    private apiService: ApiServices, private router: Router) { 
    }

  ngOnInit(): void {
    this.loadApprovedQuestions();
    this.apiService.getMessages().subscribe({
      next: resp => {
        this.messages = resp
        console.log(this.messages)
      },
      error: err => {

      }
    });
  }

  sendmsg() {
    this.user = localStorage.getItem("loggedinUser");
    this.smessage = this.message;
    this.value = { message: this.message };
    this.value = { fromUser: this.user, message: this.smessage };
    this.send(this.value);
  }

  send(values: any) {

    console.log(values.message)
    this.apiService.addMessage(values).subscribe({
      next: resp => {
        alert("message submitted")
      },
      error: err => {
        alert("message shouldn't be empty")
      }
    })
  }

  onSelect(data: TabDirective) {
    if (this.currentUserType == 'admin') {
      // this.loadUnApproovedQuestions();
      this.loadAllQuestions();
    } else {
      this.loadWFAQuestionsByThisUser();
    }

  }

  loadWFAQuestionsByThisUser() {
    this.apiService.getAllQuestions().subscribe(result => {
      // console.log(result);
      this.questionsWFA = result;
      this.questionsWFA = this.questionsWFA.filter((qus: any) => (qus.askedBy == this.currentUser && qus.approval == false));
      for (let qus of this.questionsWFA) {
        this.loadAnswerByQuestionId(qus);
      }
    })
  }

  loadAllQuestions() {
    this.apiService.getAllQuestions().subscribe(result => {
      // console.log(result);
      this.questionsWFA = result;
      for (let qus of this.questionsWFA) {
        this.loadAnswerByQuestionId(qus);
      }
    })
  }

  loadAnswerByQuestionId(questionObject: any) {
    let formData: FormData = new FormData();
    formData.append('questionId', questionObject.id);
    this.apiService.getAllAnswersByQuestionId(formData).subscribe(result => {
      questionObject.allAnswers = result;
      console.log(questionObject);
    });
  }

  loadUnApproovedQuestions() {
    this.apiService.getUnApprovedQuestions().subscribe(result => {
      // console.log(result);
      this.questionsWFA = result;
    })
  }

  loadApprovedQuestions() {
    this.apiService.getApprovedQuestions().subscribe(result => {
      // console.log(result);
      this.approvedQuestions = result;

      for (let qus of this.approvedQuestions) {
        this.loadApprovedAnswers(qus);
      }
    })
  }

  loadApprovedAnswers(questionObject: any) {
    let formData: FormData = new FormData();
    formData.append('questionId', questionObject.id);
    this.apiService.getAllAnswersByQuestionId(formData).subscribe(result => {
      questionObject.allAnswers = [];
      questionObject.allAnswers = result;
      questionObject.allAnswers = questionObject.allAnswers.filter((answer: any) => answer.approval == true);
      console.log(questionObject);
      
    });
  }

  postAnswer(questionObject: any) {
    let answerInfo = {
      questionId: questionObject.id,
      answeredBy: this.currentUser,
      answer: questionObject.currentAnswer
    }
    questionObject.isLoading = true;
    this.apiService.postAnswer(answerInfo).pipe(
      map(resp => {
        console.log(resp)
      }),
      catchError(err => {
        throw err;
      })
    )
      .subscribe(
        resp => console.log(resp),
        err => {
          questionObject.isLoading = false;
          if (err.error.text == 'Your answer is saved') {
            alert("Your Answer posted successfully");
            questionObject.currentAnswer = "";
          }
        }
      );
  }

  approveQuestion(questionObject: any) {
    let formData: FormData = new FormData();
    formData.append('questionId', questionObject.id);
    questionObject.isLoading = true;
    this.apiService.approveQuestion(formData).subscribe(result => {
      console.log(result);
      questionObject.isLoading = false;
      this.loadAllQuestions();
    });
  }

  deleteQuestion(questionObject: any) {
    let formData: FormData = new FormData();
    formData.append('questionId', questionObject.id);
    questionObject.isDeleting = true;
    this.apiService.deleteQuestion(formData).subscribe(result => {
      console.log(result);
      questionObject.isDeleting = false;
      this.loadAllQuestions();
    });
  }

  approveAnswer(answerObject: any) {
    let formData: FormData = new FormData();
    formData.append('answerId', answerObject.id);
    answerObject.isLoading = true;
    this.apiService.approveAnswer(formData).subscribe(result => {
      console.log(result);
      answerObject.isLoading = false;
      this.loadAllQuestions();
    });
  }

  deleteAnswer(answerObject: any) {
    let formData: FormData = new FormData();
    formData.append('answerId', answerObject.id);
    answerObject.isDeleting = true;
    this.apiService.deleteAnswer(formData).subscribe(result => {
      console.log(result);
      answerObject.isDeleting = false;
      this.loadAllQuestions();
    });
  }

  searchYourQuestion() {
    if (this.searchingText == '') {
      this.searchingText = ' ';
    }
    this.isSearching = true;
    this.apiService.searchQuestion(this.searchingText).subscribe(result => {
      this.isSearching = false;
      this.approvedQuestions = result;

      for (let qus of this.approvedQuestions) {
        this.loadApprovedAnswers(qus);
      }
    })
  }

  askYourQuestion() {
    let questionInfo = {
      question: this.myQuestion,
      askedBy: localStorage.getItem("loggedinUser"),
      approval: false
    }

    this.isLoading = true;

    this.apiService.askQuestion(questionInfo).pipe(
      map(resp => {
        console.log(resp)
      }),
      catchError(err => {
        throw err;
      })
    )
      .subscribe(
        resp => console.log(resp),
        err => {
          this.isLoading = false;
          if (err.error.text == 'question posted') {
            alert("question posted successfully");
            this.myQuestion = "";
          }
        }
      );
  }
}
