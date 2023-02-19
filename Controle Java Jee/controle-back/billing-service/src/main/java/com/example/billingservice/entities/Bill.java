package com.example.billingservice.entities;

import com.example.billingservice.model.Customer;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity @AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder @ToString
public class Bill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date billingDate;
    @OneToMany(fetch = FetchType.EAGER)
    private List<ProductItem> productItems = new ArrayList<>();
    private Long customerId;
    @Transient
    private Customer customer;
}
