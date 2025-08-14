package com.pharmacy.repository;

import com.pharmacy.model.Batch;
import com.pharmacy.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {
}
