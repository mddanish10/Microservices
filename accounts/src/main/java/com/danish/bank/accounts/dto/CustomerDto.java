package com.danish.bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account Information"
)
public class CustomerDto {

    @Schema(
            description = "Name of the customer",example = "Danish Khan"
    )
    @NotEmpty(message = "Name should not be null or empty")
    @Size(min = 5, max = 20,message = "The length of the customer name should be between 5 and 20")
    private String name;

    @Schema(
            description = "email of the customer",example = "Danish@email.com"
    )
    @NotEmpty(message = "email should not be null or empty")
    @Email(message = "is should of email format")
    private String email;

    @Schema(
            description = "MobileNumber of the customer",example = "1234567890"
    )
    @NotEmpty(message = "mobile number should not be null or empty")
    @Pattern(regexp ="(^$|[0-9]{10})",message = "mobile number should be of proper format")
    private String mobileNumber;

    @Schema(
            description = "Account information of customer"
    )
    private AccountsDto accountsDto;

    public CustomerDto() {

    }
}
