package com.example.kafkastreamdata.entities;

import lombok.*;


@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class ProductItem {
    private int quantity;
    private Long productId;
}
