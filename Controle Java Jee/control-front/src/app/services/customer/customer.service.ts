import { Injectable } from '@angular/core';
import {Customer} from "../../model/Customer.model";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {SecurityService} from "../keycloak/security.service";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  customers!: Customer[];
  basedUrl = environment.api_url + "CUSTOMER-SERVER/customers";

  constructor(private http: HttpClient, private securityService:SecurityService) {

  }


  public getCustomers() : Observable<Customer[]>{
    return this.http.get<Customer[]>(this.basedUrl, {headers:{"Authorization":"Bearer "+this.securityService.kcService.getKeycloakInstance().token}});
  }

  public handleCustomerDelete(id: number) : Observable<void>{
    return this.http.delete<void>(`${this.basedUrl}/${id}`, {headers:{"Authorization":"Bearer "+this.securityService.kcService.getKeycloakInstance().token}});
  }

  addNewCustomer(customer: Customer): Observable<Customer> {
    customer.id= 0;
    return this.http.post<Customer>(`${this.basedUrl}`, customer, {headers:{"Authorization":"Bearer "+this.securityService.kcService.getKeycloakInstance().token}});

  }
  editNewCustomer(customer: Customer): Observable<Customer> {
    return this.http.put<Customer>(`${this.basedUrl}/${customer.id}`, customer, {headers:{"Authorization":"Bearer "+this.securityService.kcService.getKeycloakInstance().token}});

  }
}
