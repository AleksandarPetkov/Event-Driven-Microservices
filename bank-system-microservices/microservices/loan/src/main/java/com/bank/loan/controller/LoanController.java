package com.bank.loan.controller;

import com.bank.loan.domain.Customer;
import com.bank.loan.domain.Loan;
import com.bank.loan.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoanController {

    private final LoanRepository loanRepository;

    @Autowired
    public LoanController(LoanRepository loanController){
        this.loanRepository = loanController;
    }

    @PostMapping("/myLoans")
    public Loan loansDetails(@RequestBody Customer customer) {
       Loan loans = loanRepository.findByCustomerIdOrderByStartDtDesc(customer.getId());
        if (loans != null) {
            return loans;
        } else {
            return null;
        }
    }
}
