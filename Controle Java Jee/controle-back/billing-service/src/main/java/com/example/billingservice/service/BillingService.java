package com.example.billingservice.service;

import com.example.billingservice.dto.BillResponse;
import com.example.billingservice.entities.Bill;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BillingService {
    Bill getBillById(Long id);
    Page<BillResponse> getBills(int page, int size);
    List<Bill> getBillByCustomerId(Long customerId);
    Bill addBill();

}
