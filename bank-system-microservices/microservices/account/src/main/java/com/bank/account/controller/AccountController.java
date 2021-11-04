package com.bank.account.controller;

import com.bank.account.model.Account;
import com.bank.account.model.Customer;
import com.bank.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @PostMapping("/myAccount")
    public Account getAccountDetails(@RequestBody Customer customer) {

        Account accounts = accountRepository.findById(customer.getId()).orElse(null);
        if (accounts != null) {
            return accounts;
        } else {
            return null;
        }

    }
}
