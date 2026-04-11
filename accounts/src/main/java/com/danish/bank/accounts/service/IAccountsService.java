package com.danish.bank.accounts.service;

import com.danish.bank.accounts.dto.AccountsDto;
import com.danish.bank.accounts.dto.CustomerDto;

public interface IAccountsService {
    void createAccount(CustomerDto customerDto);
    CustomerDto fetchAccountDetails(String mobileNumber);
    boolean updateAccount (CustomerDto customerDto);
    boolean deleteAccount(String mobileNumber);
}
