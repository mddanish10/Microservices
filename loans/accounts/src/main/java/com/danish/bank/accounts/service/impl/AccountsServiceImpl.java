package com.danish.bank.accounts.service.impl;

import com.danish.bank.accounts.constants.AccountsConstants;
import com.danish.bank.accounts.dto.AccountsDto;
import com.danish.bank.accounts.dto.CustomerDto;
import com.danish.bank.accounts.entities.Accounts;
import com.danish.bank.accounts.entities.Customer;
import com.danish.bank.accounts.exception.CustomerAlreadyExistsException;
import com.danish.bank.accounts.exception.ResourceNotFoundException;
import com.danish.bank.accounts.mapper.AccountMapper;
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
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccountDetails(String mobileNumber) {
        System.out.println("before Customer object");
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        System.out.println("after Customer object");

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).
                orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));
        System.out.println("before Account object");
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountMapper.mapToAccountsDto(accounts, new AccountsDto()));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto!=null){
           Accounts accounts= accountsRepository.findById(accountsDto.getAccountNumber()).
            orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", accountsDto.getAccountNumber().toString()));
         AccountMapper.mapToAccounts(accountsDto,accounts);
         accounts=accountsRepository.save(accounts);

         Long customerID=accounts.getCustomerId();
         Customer customer =customerRepository.findById(customerID).
                 orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customerID.toString()));
         CustomerMapper.mapToCustomer(customerDto,customer);
         customerRepository.save(customer);
         isUpdated=true;


        }

        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        customerRepository.deleteById(customer.getCustomerId());
        accountsRepository.deleteByCustomerId(customer.getCustomerId());

        return true;
    }
}
