package com.bank.loan.repository;

import com.bank.loan.domain.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoanRepository  extends JpaRepository<Loan, Long> {
    Loan findByCustomerIdOrderByStartDtDesc(int customerId);
}
