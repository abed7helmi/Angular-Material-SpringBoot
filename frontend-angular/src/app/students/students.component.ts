import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {Router} from "@angular/router";
import {StudentService} from "../services/student.service";
import {Student} from "../model/student.model";

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css']
})
export class StudentsComponent implements OnInit , AfterViewInit{

  public dataSource! : MatTableDataSource<Student> ;
  public displayedColumns = ["id","firstName" , "lastName","payments"]
  students! : Array<Student>
  // pour faire la pagination et le sort
  @ViewChild(MatPaginator) paginator! : MatPaginator;
  @ViewChild(MatSort) sort! : MatSort;
  constructor(private router : Router , private studentService : StudentService) { }

  ngOnInit(): void {

    this.studentService.getStudents().subscribe({
      next : value => {
        this.students = value
        this.dataSource = new MatTableDataSource<Student>(this.students)
      },
      error : err => {
        console.log(err)
      }
    })

  }

  // apres l'initialisation des données
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator
    this.dataSource.sort = this.sort;
  }

  filterStudents(event: Event) {
    let value = (event.target as HTMLInputElement).value;
    this.dataSource.filter = value;
  }

  getPayments(student : Student) {
    this.router.navigateByUrl("admin/student-details/"+student.code)
  }

}
