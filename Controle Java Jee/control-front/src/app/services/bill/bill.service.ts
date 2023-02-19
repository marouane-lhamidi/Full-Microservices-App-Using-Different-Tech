import { Injectable } from '@angular/core';
import {Bill, Options} from "../../model/Bill.model";
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {SecurityService} from "../keycloak/security.service";

@Injectable({
  providedIn: 'root'
})
export class BillService {


  basedUrl = environment.api_url + "BILLING-SERVER/fullBills";

  constructor(private http: HttpClient, private securityService:SecurityService) {

  }


  public getBills(options: Options) : Observable<any> {
    const url = `${this.basedUrl}?page=${options.page-1}&size=${options.size}`;
    return this.http.get<Bill[]>(url, {headers:{"Authorization":"Bearer "+this.securityService.kcService.getKeycloakInstance().token}}).pipe(map(response => response));
  }



}
