package com.zerobase.restaurant_reservatation.user.domain.repository;

import com.zerobase.restaurant_reservatation.user.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
