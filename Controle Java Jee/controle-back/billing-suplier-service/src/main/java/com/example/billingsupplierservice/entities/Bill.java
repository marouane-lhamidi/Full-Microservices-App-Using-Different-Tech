package com.example.billingsupplierservice.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class Bill {
    private Date billingDate;
    private List<ProductItem> productItems = new ArrayList<>();
    private Long customerId;
}
