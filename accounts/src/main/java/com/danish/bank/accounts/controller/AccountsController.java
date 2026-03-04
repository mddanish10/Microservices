package com.danish.bank.accounts.controller;

import com.danish.bank.accounts.constants.AccountsConstants;
import com.danish.bank.accounts.dto.AccountsDto;
import com.danish.bank.accounts.dto.CustomerDto;
import com.danish.bank.accounts.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountsController {


    public ResponseEntity<ResponseDto> create(@RequestBody CustomerDto customerDto){


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body( new ResponseDto((AccountsConstants.STATUS_201),AccountsConstants.MESSAGE_200));
    }

}
