import { Component, OnInit } from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css']
})
export class StudentsComponent implements OnInit {

  public students : any ;
  public dataSource : any ;
  public displayedColumns = ["id","firstName" , "lastName","payments"]
  constructor() { }

  ngOnInit(): void {

    this.students = []
    for (let i = 1; i<100 ; i++){

      this.students.push(
        {
          id : i,
          firstName : Math.random().toString(20),
          lastName : Math.random().toString(20),
        }
      );

    }

    this.dataSource = new MatTableDataSource(this.students)
    console.log(this.dataSource)

  }

}
