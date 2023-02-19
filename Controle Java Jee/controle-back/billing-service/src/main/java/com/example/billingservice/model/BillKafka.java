package com.example.billingservice.model;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder @ToString
public class BillKafka {
    private Date billingDate;
    private List<ProductItemKafka> productItems = new ArrayList<>();
    private Long customerId;
}
