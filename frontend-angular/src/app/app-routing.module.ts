import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./login/login.component";
import {PaymentsComponent} from "./payments/payments.component";
import {StudentsComponent} from "./students/students.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {ProfileComponent} from "./profile/profile.component";
import {LoadStudentsComponent} from "./load-students/load-students.component";
import {LoadPaymentsComponent} from "./load-payments/load-payments.component";

const routes: Routes = [
  {path : "", component : LoginComponent},
  {path : "home", component : HomeComponent},
  {path : "login", component : LoginComponent},
  {path : "profile", component : ProfileComponent},
  {path : "dashboard", component : DashboardComponent},
  {path : "students", component : StudentsComponent},
  {path : "payments", component : PaymentsComponent},
  {path : "loadStudents", component : LoadStudentsComponent},
  {path : "loadPayments", component : LoadPaymentsComponent},



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
