package com.pharmacy.repository;

import com.pharmacy.model.Customer;
import com.pharmacy.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
