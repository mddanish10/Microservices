package com.danish.bank.accounts.service.impl;

import com.danish.bank.accounts.constants.AccountsConstants;
import com.danish.bank.accounts.dto.CustomerDto;
import com.danish.bank.accounts.entities.Accounts;
import com.danish.bank.accounts.entities.Customer;
import com.danish.bank.accounts.exception.CustomerAlreadyExistsException;
import com.danish.bank.accounts.mapper.CustomerMapper;
import com.danish.bank.accounts.repository.AccountsRepository;
import com.danish.bank.accounts.repository.CustomerRepository;
import com.danish.bank.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;


    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer =CustomerMapper.mapToCustomer(customerDto, new Customer());
           Optional<Customer> optionalCustomer= customerRepository.findByMobileNumber(customerDto.getMobileNumber());
           if(optionalCustomer.isPresent()){
               throw  new CustomerAlreadyExistsException(" Customer with given mobileNumber is already exist"+customerDto.getMobileNumber());
           }
           customer.setCreatedAt(LocalDateTime.now());
           customer.setCreatedBy("anonymos");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(
                crateNewAccount(savedCustomer));

    }

    public Accounts crateNewAccount(Customer customer){
          Accounts newAccount = new Accounts();
          newAccount.setCustomerId(customer.getCustomerId());
          Long randomAccNumber = 1000000000L+ new Random().nextInt((900000000));
          newAccount.setAccountNumber(randomAccNumber);
          newAccount.setAccountType(AccountsConstants.SAVINGS);
          newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("anonymos");
        return newAccount;
    }
}
