package com.authentication.autentication.service;

import com.authentication.autentication.dto.JwtResponseDTO;
import com.authentication.autentication.dto.LoginDto;
import com.authentication.autentication.dto.RefreshTokenRequestDto;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AuthenticationService {
    JwtResponseDTO accessToken(LoginDto loginDto) throws UsernameNotFoundException;

    JwtResponseDTO refreshToken(RefreshTokenRequestDto refreshTokenRequestDto);
}
