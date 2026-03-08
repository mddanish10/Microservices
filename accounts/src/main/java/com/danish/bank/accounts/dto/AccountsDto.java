package com.danish.bank.accounts.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountsDto {

    @NotEmpty(message = "mobile number should not be null or empty")
    @Pattern(regexp ="(^$|[0-9]{10})",message = "mobile number should be of proper format")
    private Long accountNumber;

    @NotEmpty(message ="account type should not be null")
    private String accountType;


    @NotEmpty(message ="Branch address should not be null")
    private String branchAddress;

    public AccountsDto(){}
}
