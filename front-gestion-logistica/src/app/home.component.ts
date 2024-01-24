import { Component, OnInit } from '@angular/core';
import { AppService } from './app.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  templateUrl: './home.component.html'
})
export class HomeComponent {

  title = 'Demo';


  constructor(private app: AppService, private http: HttpClient) {

  }

  
  authenticated() { return this.app.authenticated; }

}
