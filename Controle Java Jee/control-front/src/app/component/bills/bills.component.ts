import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {BillService} from "../../services/bill/bill.service";
import {Bill, Options, Response} from "../../model/Bill.model";
import {BillInformationComponent} from "./bill-information/bill-information.component";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-bills',
  templateUrl: './bills.component.html',
  styleUrls: ['./bills.component.css']
})
export class BillsComponent implements OnInit {
  options: Options = {
    orderBy: 'Name',
    orderDir: 'ASC',
    page: 1,
    search: '',
    size: 10
  };
  response : Response = {
    content : [],
    filtered : 0,
    totalPages : 0
  };
  getBillsSub !: Subscription;
  maxVisiblePages = 5;
  searchFormGroup! : FormGroup;

  constructor(private billService : BillService,
              private fb: FormBuilder,
              public modalService: NgbModal) { }

  ngOnInit(): void {
    this.searchFormGroup= this.fb.group({
      keyword: this.fb.control(null)
    })
    this.getPageBills();

  }

  getPageBills(){
    this.getBillsSub = this.billService.getBills(this.options).subscribe(response =>
      this.response = response
    );
  }

  get numbers(): number[] {
    return Array(this.endPage - this.startPage + 1).fill(0).map((_, i) => i + this.startPage);
  }

  get startPage(): number {
    let start = this.options.page - Math.floor(this.maxVisiblePages / 2);
    return Math.max(start, 1);
  }

  get endPage(): number {
    let end = this.options.page + Math.floor(this.maxVisiblePages / 2);
    return Math.min(end, this.response.totalPages);
  }

  next(event: Event) {
    event.preventDefault(); // Prevents the default behavior of the event
    this.options.page++;
    this.getPageBills();
  }

  prev(event: Event) {
    event.preventDefault(); // Prevents the default behavior of the event
    this.options.page--;
    this.getPageBills();
  }

  to(page: number, event: Event) {
    event.preventDefault(); // Prevents the default behavior of the event
    this.options.page = page;
    this.getPageBills();
  }

  size(size: number) {
    this.options.size = size;
    this.options.page = 1;
    this.getPageBills();
  }
  openInformationModal(b: Bill) {
    const modalRef = this.modalService.open(BillInformationComponent, { size: 'lg', centered: true });
    modalRef.componentInstance.bill = b;

  }
}
