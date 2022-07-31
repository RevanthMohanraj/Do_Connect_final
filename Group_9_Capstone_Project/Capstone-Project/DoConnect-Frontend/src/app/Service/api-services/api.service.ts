import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiServices {

  baseUrl="http://localhost:8080/"
  url="http://localhost:8083/api/chat"
  constructor(private httpclient:HttpClient) { }
  addMessage(message:any){
    return this.httpclient.post(this.url+"/sendMessage",message);
  }
  getMessages(){
    return this.httpclient.get<any>(this.url+"/getMessage")
  }
  register(user:any,type:any):Observable<any>
  {
    let endPoint = type == 'user' ? 'user/register' : 'admin/register';
    return this.httpclient.post(this.baseUrl+endPoint,user);
  }

  login(user:any,type:any): Observable<any>
  {
    let endPoint =  type == 'user' ? 'user/login' : 'admin/login';
    return this.httpclient.post(this.baseUrl+endPoint,user);
  } 

  askQuestion(questionInfo:any): Observable<any>
  {
    let endPoint = 'question/ask';
    return this.httpclient.post(this.baseUrl+endPoint,questionInfo);
  } 

  searchQuestion(questionInfo:any): Observable<any>
  {
    let endPoint = 'question/search';
    return this.httpclient.post(this.baseUrl+endPoint,questionInfo);
  } 

  getUnApprovedQuestions(){
    let endPoint = 'admin/question/unapproved';
    return this.httpclient.get(this.baseUrl+endPoint);
  }

  approveQuestion(questionObject:any){
    let endPoint = 'admin/question/approve';
    return this.httpclient.post(this.baseUrl+endPoint,questionObject);
  }

  deleteQuestion(questionObject:any){
    let endPoint = 'admin/question/delete';
    return this.httpclient.post(this.baseUrl+endPoint,questionObject);
  }

  approveAnswer(answerObject:any){
    let endPoint = 'admin/answer/approve';
    return this.httpclient.post(this.baseUrl+endPoint,answerObject);
  }

  deleteAnswer(answerObject:any){
    let endPoint = 'admin/answer/delete';
    return this.httpclient.post(this.baseUrl+endPoint,answerObject);
  }

  getAllQuestions(){
    let endPoint = 'admin/allquestions';
    return this.httpclient.get(this.baseUrl+endPoint);
  }

  getAllAnswersByQuestionId(questionInfo:any){
    let endPoint = 'answer/byquestionid';
    return this.httpclient.post(this.baseUrl+endPoint,questionInfo);
  }

  getApprovedQuestions(){
    let endPoint = 'admin/question/approved';
    return this.httpclient.get(this.baseUrl+endPoint);
  }

  postAnswer(answerInfo:any){
    let endPoint = 'answer/save';
    return this.httpclient.post(this.baseUrl+endPoint,answerInfo);
  }

  getApprovedAnswers(){
    let endPoint = 'admin/answer/approved';
    return this.httpclient.get(this.baseUrl+endPoint);
  }
}
