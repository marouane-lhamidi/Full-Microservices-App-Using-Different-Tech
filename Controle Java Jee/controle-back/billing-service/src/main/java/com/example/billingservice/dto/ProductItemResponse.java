package com.example.billingservice.dto;
import lombok.*;

import com.example.billingservice.model.Product;
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class ProductItemResponse {
    private Long id;
    private int quantity;
    private Double price;
    private Long productId;
    private Product product;
}
