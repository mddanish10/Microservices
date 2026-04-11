package com.danish.bank.accounts.repository;

import com.danish.bank.accounts.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
     Optional<Customer> findByMobileNumber(String mobileNumber);
}
