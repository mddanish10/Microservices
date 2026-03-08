package com.danish.bank.accounts.controller;

import com.danish.bank.accounts.constants.AccountsConstants;
import com.danish.bank.accounts.dto.AccountsDto;
import com.danish.bank.accounts.dto.CustomerDto;
import com.danish.bank.accounts.dto.ResponseDto;
import com.danish.bank.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AccountsController {
      private IAccountsService iAccountsService;

      @PostMapping(value = "/create")
    public ResponseEntity<ResponseDto> create(@RequestBody CustomerDto customerDto){
         iAccountsService.createAccount(customerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body( new ResponseDto((AccountsConstants.STATUS_201),AccountsConstants.MESSAGE_200));
    }

    @GetMapping(value = "/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam String mobileNumber){
        CustomerDto customerDto = iAccountsService.fetchAccountDetails(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK)
                .body(customerDto);
    }


    @PutMapping(value = "/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@RequestBody CustomerDto customerDto){
         boolean isUpdate=  iAccountsService.updateAccount(customerDto);
         if(isUpdate){
             return ResponseEntity.status(HttpStatus.OK)
                     .body(new ResponseDto((AccountsConstants.STATUS_200),AccountsConstants.MESSAGE_200));
         }
         else {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                     .body(new ResponseDto((AccountsConstants.STATUS_500),AccountsConstants.MESSAGE_500));
         }


    }

    @DeleteMapping(value = "/delete")
    private ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam String mobileNumber){
          boolean isDeleted=iAccountsService.deleteAccount(mobileNumber);
          if(isDeleted){
              return ResponseEntity.status(HttpStatus.OK).
                      body( new ResponseDto((AccountsConstants.STATUS_200),AccountsConstants.MESSAGE_200));
          }
          else {
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                      .body(new ResponseDto((AccountsConstants.STATUS_500),AccountsConstants.MESSAGE_500));
          }
    }

}
