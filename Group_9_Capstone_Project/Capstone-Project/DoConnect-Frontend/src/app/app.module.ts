import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { QuestionComponent } from './question/question.component';
import { TabsModule } from 'ngx-bootstrap/tabs';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSliderModule } from '@angular/material/slider';
import { MaterialModule } from './material/material.module';


@NgModule({
  declarations: [
    AppComponent,
    UserLoginComponent,
    AdminLoginComponent,
    RegisterComponent,
    LoginComponent,
    QuestionComponent,
    NavBarComponent,
  ],
  imports: [
    BrowserModule,
    MatSliderModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MaterialModule,
    TabsModule.forRoot(),
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
