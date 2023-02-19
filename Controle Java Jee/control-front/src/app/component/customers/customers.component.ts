import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {CustomerService} from "../../services/customer/customer.service";
import {Customer} from "../../model/Customer.model";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {NewCustomerComponent} from "./new-customer/new-customer.component";
import {EditCustomerComponent} from "./edit-customer/edit-customer.component";
import {SecurityService} from "../../services/keycloak/security.service";

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {

  customersGetting: any;
  customer!: Customer;
  customers!: Customer[];
  errorMessage! : string;
  searchFormGroup! : FormGroup;

  constructor(private customerService : CustomerService,
              private fb: FormBuilder,
              public modalService: NgbModal,
              private sec: SecurityService) { }

  ngOnInit(): void {

    this.searchFormGroup= this.fb.group({
      keyword: this.fb.control(null)
    })
    this.getPageCustomers();

  }
  getPageCustomers(){
    this.customerService.getCustomers().subscribe(date=> {
      this.customersGetting= date;
      this.customers = this.customersGetting._embedded.customers;
      console.log(this.sec.kcService.getToken())
    })
  }


  openAddCustomer(){
    this.modalService.open(NewCustomerComponent);
  }

  openEditModal(customer: Customer) {
    const modalRef = this.modalService.open(EditCustomerComponent);
    modalRef.componentInstance.customer = customer;
  }

  handleCustomerDelete(p: Customer) {
    let conf = confirm("Are you sur you want to delete")
    if (!conf) return;
    this.customerService.handleCustomerDelete(p.id).subscribe({
      next: (data)=>{
        let id = this.customers.indexOf(p);
        this.customers.splice(id, 1);
      },
      error: (err)=>{
        this.errorMessage= err;
      }
    })

  }

}
