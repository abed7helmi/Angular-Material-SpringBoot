import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {Payment, Student} from "../model/student.model";

@Injectable({
  providedIn: 'root'
})
export class StudentService {



  constructor(private http : HttpClient) { }

  public getAllPayments(): Observable<Array<Payment>> {
    return this.http.get<Array<Payment>>(`${environment.backendHost}/payments`)
  }

  public getStudents(): Observable<Array<Student>> {
    return this.http.get<Array<Student>>(`${environment.backendHost}/students`)
  }
  public getStudentsPayments(code : string): Observable<Array<Payment>> {
    return this.http.get<Array<Payment>>(`${environment.backendHost}/students/${code}/payments`)
  }

}
