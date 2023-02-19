package com.example.billingservice.service.impl;

import com.example.billingservice.dto.BillResponse;
import com.example.billingservice.entities.Bill;
import com.example.billingservice.entities.ProductItem;
import com.example.billingservice.feign.CustomerRestClient;
import com.example.billingservice.feign.ProductItemRestClient;
import com.example.billingservice.mapper.BillMapper;
import com.example.billingservice.mapper.ProductItemMapper;
import com.example.billingservice.model.BillKafka;
import com.example.billingservice.model.Customer;
import com.example.billingservice.model.Product;
import com.example.billingservice.repositories.BillRepository;
import com.example.billingservice.repositories.ProductItemRepository;
import com.example.billingservice.service.BillingService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
@Service @AllArgsConstructor
public class BillServiceImpl implements BillingService {
    BillRepository billRepository;
    ProductItemRepository productItemRepository;
    CustomerRestClient customerRestClient;
    ProductItemRestClient productItemRestClient;

    @Override
    public Bill getBillById(Long id) {
        Bill bill = billRepository.findById(id).get();
        Customer customer = customerRestClient.getCustomerById(bill.getCustomerId());
        bill.setCustomer(customer);
        bill.getProductItems().forEach(productItem -> {
            Product product = productItemRestClient.getProductById(productItem.getProductId());
            productItem.setProduct(product);
        });
        return bill;
    }

    @Override
    public Page<BillResponse> getBills(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Bill> bills= billRepository.findAll(pageable);
        bills.forEach(bill -> {
            Customer customer = customerRestClient.getCustomerById(bill.getCustomerId());
            bill.setCustomer(customer);
            bill.getProductItems().forEach(productItem -> {
                Product product = productItemRestClient.getProductById(productItem.getProductId());
                productItem.setProduct(product);
            });
        });

        return bills.map(new Function<>() {
            @Override
            public BillResponse apply(Bill bill) {

                return BillMapper.billToReturn(bill);
            }
        });
    }

    @Override
    public List<Bill> getBillByCustomerId(Long customerId) {
        List<Bill> bills= billRepository.getBillByCustomerId(customerId);
        bills.forEach(bill -> {
            Customer customer = customerRestClient.getCustomerById(bill.getCustomerId());
            bill.setCustomer(customer);
            bill.getProductItems().forEach(productItem -> {
                Product product = productItemRestClient.getProductById(productItem.getProductId());
                productItem.setProduct(product);
            });
        });
        return bills;
    }

    @Override
    public Bill addBill() {
        Customer customer = customerRestClient.getCustomerById(1L);
        Bill bill = billRepository.save(new Bill(null, new Date(), null, customer.getId(), null));
        List<ProductItem> productItems = new ArrayList<>();
        PagedModel<Product> products = productItemRestClient.getProducts();
        products.forEach(product -> {
            Integer quantity= 1+ new Random().nextInt(100);
            ProductItem productItem = new ProductItem();
            productItem.setId(null);
            productItem.setBill(bill);
            productItem.setProductId(product.getId());
            productItem.setQuantity(quantity);
            productItemRepository.save(productItem);
            productItems.add(productItem);
        });
        bill.setProductItems(productItems);
        return billRepository.save(bill);
    }

    @Bean
    public Consumer<BillKafka> pageEventConsumer(){
        return (input) -> {
            List<ProductItem> items = ProductItemMapper.productItems(input.getProductItems()).stream().map(
                    p->productItemRepository.save(p)).collect(Collectors.toList());
            Bill bill = BillMapper.billToStore(input);
            bill.setProductItems(items);
            Bill bill1 =billRepository.save(bill);
            System.out.println(bill1.getId());
        };
    }

}
