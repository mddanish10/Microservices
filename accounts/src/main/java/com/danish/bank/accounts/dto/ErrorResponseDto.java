package com.danish.bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "Error Response",
        description = "Schema to send error response"
)
@Data @AllArgsConstructor
public class ErrorResponseDto {

    @Schema(
            description = "Api path invoked by client"
    )
    private String apiPath;

    @Schema(
            description = "error code representing the error happened"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "message representing the error happened"
    )
    private String errorMsg;

    @Schema(
            description = "timestamp of the error occurrence"
    )
    private LocalDateTime errorTime;

}
