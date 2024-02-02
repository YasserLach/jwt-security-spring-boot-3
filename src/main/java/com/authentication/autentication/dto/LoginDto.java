package com.authentication.autentication.dto;

import com.authentication.autentication.common.CoreConstant;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginDto {
    @NotBlank(message = CoreConstant.Exception.INVALID_EMAIL)
    private String username;

    @NotBlank(message = CoreConstant.Exception.INVALID_PASSWORD)
    private String password;
}
