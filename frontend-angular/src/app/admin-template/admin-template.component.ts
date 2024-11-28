import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../services/authentification.service";

@Component({
  selector: 'app-admin-template',
  templateUrl: './admin-template.component.html',
  styleUrls: ['./admin-template.component.css']
})
export class AdminTemplateComponent implements OnInit {

  constructor(public authService : AuthenticationService) {
  }
  ngOnInit() {
  }

  logout() {
    this.authService.logout();
  }
}
