package com.example.billingsupplierservice.service;

import com.example.billingsupplierservice.entities.Bill;
import com.example.billingsupplierservice.entities.ProductItem;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

@Service
public class BillSupplierService {
    Random r = new Random();

    @Bean
    public Supplier<Bill> pageEventSupplier(){
        return ()-> Bill.builder()
                .customerId((long) r.nextInt(3)+1)
                .productItems(random())
                .billingDate(new Date())
                .build();
    }

    public List<ProductItem> random(){
        List<ProductItem> productItems = new ArrayList<>();
        productItems.add(ProductItem.builder().quantity((1+ r.nextInt(100))).productId(1L).build());
        productItems.add(ProductItem.builder().quantity((1+ r.nextInt(100))).productId(2L).build());
        productItems.add(ProductItem.builder().quantity((1+ r.nextInt(100))).productId(3L).build());
        return productItems;
    }

}
