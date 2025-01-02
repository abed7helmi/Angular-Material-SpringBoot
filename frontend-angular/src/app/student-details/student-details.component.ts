import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {StudentService} from "../services/student.service";
import {Payment} from "../model/student.model";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-student-details',
  templateUrl: './student-details.component.html',
  styleUrls: ['./student-details.component.css']
})
export class StudentDetailsComponent implements OnInit {

  studentCode! : string;
  studentPayments! : Array<Payment>
  paymentsDataSource !: MatTableDataSource<Payment>
  public displayedColumns = ['id','date','type','status','amount','firstName'];

  constructor(private router : ActivatedRoute, private studentService : StudentService) { }

  ngOnInit(): void {
    this.studentCode = this.router.snapshot.params['code'];
    this.studentService.getStudentsPayments(this.studentCode).subscribe({
      next : value => {
        this.studentPayments = value
        this.paymentsDataSource = new MatTableDataSource<Payment>(this.studentPayments)

      },
      error : err => {
        console.log(err)
      }
    })
  }

}
