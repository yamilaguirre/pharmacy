package com.pharmacy.repository;

import com.pharmacy.model.Invoice;
import com.pharmacy.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
