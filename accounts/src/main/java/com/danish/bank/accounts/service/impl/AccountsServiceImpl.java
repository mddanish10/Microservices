package com.danish.bank.accounts.service.impl;

import com.danish.bank.accounts.dto.CustomerDto;
import com.danish.bank.accounts.repository.AccountsRepository;
import com.danish.bank.accounts.repository.CustomerRepository;
import com.danish.bank.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;


    @Override
    public void create(CustomerDto customerDto) {

    }
}
