import { trigger } from '@angular/animations';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { switchMap } from "rxjs/operators";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  field = "";
  trigger = new Subject<string>();
  value: Observable<any> = this.trigger.pipe(
    switchMap(userID => this.http.get("/api/", {
      params: {
        userID,
      }
    })),
  );

  constructor(
    private http: HttpClient
  ) {}

  makeRequest() {
    this.trigger.next(this.field);
  }
}
