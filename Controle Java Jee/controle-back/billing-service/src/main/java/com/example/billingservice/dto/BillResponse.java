package com.example.billingservice.dto;

import com.example.billingservice.entities.ProductItem;
import com.example.billingservice.model.Customer;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class BillResponse {
    private Long id;
    private Date billingDate;
    private List<ProductItemResponse> productItems = new ArrayList<>();
    private Long customerId;
    private Double totalToPay;
    private Customer customer;
}
