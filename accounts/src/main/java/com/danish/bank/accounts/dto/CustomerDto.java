package com.danish.bank.accounts.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class CustomerDto {

    @NotEmpty(message = "Name should not be null or empty")
    @Size(min = 5, max = 20,message = "The length of the customer name should be between 5 and 20")
    private String name;

    @NotEmpty(message = "email should not be null or empty")
    @Email(message = "is should of email format")
    private String email;

    @NotEmpty(message = "mobile number should not be null or empty")
    @Pattern(regexp ="(^$|[0-9]{10})",message = "mobile number should be of proper format")
    private String mobileNumber;

    private AccountsDto accountsDto;

    public CustomerDto() {

    }
}
