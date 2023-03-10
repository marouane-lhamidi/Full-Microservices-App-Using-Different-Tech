package com.example.billingservice.repositories;

import com.example.billingservice.entities.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> getBillByCustomerId(Long customerId);
    Page<Bill> findAll(Pageable pageable);

}
