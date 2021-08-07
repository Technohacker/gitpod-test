import { trigger } from '@angular/animations';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { switchMapTo } from "rxjs/operators";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'angular';
  trigger = new Subject();
  value: Observable<any> = this.trigger.pipe(
    switchMapTo(this.http.get("/api/")),
  );

  constructor(
    private http: HttpClient
  ) {}

  makeRequest() {
    this.trigger.next();
  }
}
