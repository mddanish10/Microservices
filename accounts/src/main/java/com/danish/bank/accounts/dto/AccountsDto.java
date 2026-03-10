package com.danish.bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
        name = "Account",
        description = "Account information of the customer"
)
@Data
@AllArgsConstructor
public class AccountsDto {

     @Schema(
            description = "accountNumber of the customer", example = "1232214567"
     )
    @NotEmpty(message = "account Number should not be null or empty")
    @Pattern(regexp ="(^$|[0-9]{10})",message = "accountNumber should be of proper format")
    private Long accountNumber;

    @Schema(
            description = "accountType of the customer", example = "savings/current/salary"
    )
    @NotEmpty(message ="account type should not be null")
    private String accountType;

    @Schema(
            description = "branch Address of the customer", example = "Belthara Road Ballia"
    )
    @NotEmpty(message ="Branch address should not be null")
    private String branchAddress;

    public AccountsDto(){}
}
