package com.example.billingservice.mapper;

import com.example.billingservice.dto.BillResponse;
import com.example.billingservice.dto.ProductItemResponse;
import com.example.billingservice.entities.Bill;
import com.example.billingservice.entities.ProductItem;
import com.example.billingservice.model.BillKafka;
import com.example.billingservice.model.Customer;
import com.example.billingservice.model.Product;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class BillMapper {
    private BillMapper(){}

    public static Bill bill(BillKafka billKafka){
        Bill bill = new Bill();
        bill.setBillingDate(billKafka.getBillingDate());
        bill.setCustomerId(billKafka.getCustomerId());
        List<ProductItem> productItems = ProductItemMapper.productItems(billKafka.getProductItems());
        productItems.forEach(p -> p.setBill(bill));
        bill.setProductItems(productItems);
        return bill;
    }
    public static Bill billToStore(BillKafka billKafka){
        Bill bill = new Bill();
        bill.setBillingDate(billKafka.getBillingDate());
        bill.setCustomerId(billKafka.getCustomerId());
        return bill;
    }
    public static BillResponse billToReturn(Bill bill){
        BillResponse response = new BillResponse();
        AtomicReference<Double> total  = new AtomicReference<>(0.0);


        response.setId( bill.getId());
        response.setBillingDate(bill.getBillingDate());
        response.setCustomer(bill.getCustomer());
        response.setCustomerId(bill.getCustomerId());
        bill.getProductItems().forEach(b->{
            ProductItemResponse productItemResponse = ProductItemMapper.productItem( b, b.getProduct());
            response.getProductItems().add(productItemResponse);
            total.set(total.get() + productItemResponse.getPrice());
        });
        response.setTotalToPay(total.get());
        return response;
    }
}
