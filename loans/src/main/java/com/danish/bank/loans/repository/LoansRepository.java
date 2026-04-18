package com.danish.bank.loans.repository;

import com.danish.bank.loans.entities.Loans;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoansRepository extends JpaRepository<Loans,Long> {


    Optional<Loans> findByMobileNumber(String mobileNumber);

    Optional<Loans> findByLoanNumber(String loanNumber);
}
