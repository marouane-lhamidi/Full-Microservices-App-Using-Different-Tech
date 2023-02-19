package com.example.billingservice.model;

import lombok.*;


@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder @ToString
public class ProductItemKafka {
    private int quantity;
    private Long productId;
}