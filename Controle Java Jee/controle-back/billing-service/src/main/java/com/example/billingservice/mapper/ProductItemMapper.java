package com.example.billingservice.mapper;

import com.example.billingservice.dto.ProductItemResponse;
import com.example.billingservice.entities.ProductItem;
import com.example.billingservice.model.Product;
import com.example.billingservice.model.ProductItemKafka;

import java.util.ArrayList;
import java.util.List;

public class ProductItemMapper {
    private ProductItemMapper(){}

    public static List<ProductItem> productItems(List<ProductItemKafka> productItemKafka){
        List<ProductItem> productItems = new ArrayList<>();
        productItemKafka.forEach(p ->
                productItems.add(ProductItem.builder()
                        .productId(p.getProductId())
                        .quantity(p.getQuantity())
                        .build())
        );
        return productItems;
    }

    public static ProductItemResponse productItem(ProductItem item, Product product){
        ProductItemResponse response = new ProductItemResponse();
        response.setId(item.getId());
        response.setProduct(product);
        response.setQuantity(item.getQuantity());
        response.setProductId(item.getProductId());
        response.setPrice(item.getQuantity() * product.getPrice());
        return response;
    }


}
