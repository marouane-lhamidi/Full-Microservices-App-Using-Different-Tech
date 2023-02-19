import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AnalyticsService {

  constructor(private http: HttpClient) { }


  public getData() : Observable<any>{
    return this.http.get<any>("http://localhost:8089/analytics");
  }
}
