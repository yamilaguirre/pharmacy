package com.pharmacy.repository;

import com.pharmacy.model.Supplier;
import com.pharmacy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
