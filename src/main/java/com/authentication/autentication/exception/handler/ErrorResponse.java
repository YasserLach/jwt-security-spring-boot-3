package com.authentication.autentication.exception.handler;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class ErrorResponse {

    private HttpStatus status;
    private String message;
    private int code;
    private LocalDateTime timestamp;

}
