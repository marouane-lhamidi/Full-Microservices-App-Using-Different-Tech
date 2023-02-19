package com.example.billingservice.controllers;

import com.example.billingservice.dto.BillResponse;
import com.example.billingservice.entities.Bill;
import com.example.billingservice.service.BillingService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;


@RestController @AllArgsConstructor
public class BillController {
    BillingService billingService;
    @GetMapping(path = "/fullBill/{id}")
    public Bill getBill(@PathVariable Long id){
        return billingService.getBillById(id);
    }
    @GetMapping(path = "/fullBills")
    @PreAuthorize("hasAuthority('USER')")
    public Page<BillResponse> getBills(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size){
        return billingService.getBills(page, size);
    }
    @GetMapping(path = "/fullBills/add")
    public Bill addBill(){
        return billingService.addBill();
    }

}
