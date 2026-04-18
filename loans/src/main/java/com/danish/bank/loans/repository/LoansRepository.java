package com.danish.bank.loans.repository;

import com.danish.bank.loans.entities.Loans;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoansRepository extends JpaRepository<Loans,Long> {


}
