package com.example.billingsupplierservice.entities;

import lombok.*;


@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class ProductItem {
    private int quantity;
    private Long productId;
}