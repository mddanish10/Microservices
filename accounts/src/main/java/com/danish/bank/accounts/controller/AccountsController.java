package com.danish.bank.accounts.controller;

import com.danish.bank.accounts.constants.AccountsConstants;
import com.danish.bank.accounts.dto.AccountsDto;
import com.danish.bank.accounts.dto.CustomerDto;
import com.danish.bank.accounts.dto.ErrorResponseDto;
import com.danish.bank.accounts.dto.ResponseDto;
import com.danish.bank.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(
        name = "Accounts Controller ",
        description = "This controller performs CRUD operation for Accounts API"
)
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class AccountsController {
    private IAccountsService iAccountsService;

    @Operation(
            summary = "Post method",
            description = "This performs Create operation in Controller"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody CustomerDto customerDto) {
        iAccountsService.createAccount(customerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto((AccountsConstants.STATUS_201), AccountsConstants.MESSAGE_200));
    }

    @Operation(
            summary = "Get method",
            description = "This performs read or fetch operation in Controller"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping(value = "/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                           @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number should be of proper format")
                                                           String mobileNumber) {
        CustomerDto customerDto = iAccountsService.fetchAccountDetails(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK)
                .body(customerDto);
    }

    @Operation(
            summary = "Put method",
            description = "This performs update operation in Controller"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Request processed successfully"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "update operation failed. Please try again or contact Dev team"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "An error occurred. Please try again or contact Dev team",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            )
    }
    )
    @PutMapping(value = "/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdate = iAccountsService.updateAccount(customerDto);
        if (isUpdate) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto((AccountsConstants.STATUS_200), AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto((AccountsConstants.STATUS_417), AccountsConstants.MESSAGE_417_UPDATE));
        }


    }


    @Operation(
            summary = "delete method",
            description = "This performs delete operation in Controller"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Request processed successfully"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "delete operation failed. Please try again or contact Dev team"
            ),

            @ApiResponse(
                    responseCode = "500",
                    description = "An error occurred. Please try again or contact Dev team",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            )
    }
    )
    @DeleteMapping(value = "/delete")
    private ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number should be of proper format")
                                                             String mobileNumber) {
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).
                    body(new ResponseDto((AccountsConstants.STATUS_200), AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto((AccountsConstants.STATUS_417), AccountsConstants.MESSAGE_417_DELETE));
        }
    }

}
