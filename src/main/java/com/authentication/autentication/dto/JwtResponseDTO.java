package com.authentication.autentication.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class JwtResponseDTO {

    private String accessToken;
    private String refreshToken;
}
