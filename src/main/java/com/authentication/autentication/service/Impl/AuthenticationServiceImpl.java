package com.authentication.autentication.service.Impl;

import com.authentication.autentication.common.CoreConstant;
import com.authentication.autentication.dto.JwtResponseDTO;
import com.authentication.autentication.dto.LoginDto;
import com.authentication.autentication.dto.RefreshTokenRequestDto;
import com.authentication.autentication.enums.TokenType;
import com.authentication.autentication.exception.ElementNotFoundException;
import com.authentication.autentication.exception.JwtTokenException;
import com.authentication.autentication.model.User;
import com.authentication.autentication.repository.UserRepository;
import com.authentication.autentication.service.AuthenticationService;
import com.authentication.autentication.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final MessageSource messageSource;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtResponseDTO accessToken(LoginDto loginDto) {
        userRepository.findByEmail(loginDto.getUsername()).orElseThrow(()-> new ElementNotFoundException(messageSource.getMessage(CoreConstant.Exception.NO_SUCH_ELEMENT_FOUND,null,null)));
        authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUsername(),
                            loginDto.getPassword()
                    )
            );
                var jwtToken = jwtUtil.generateToken(new HashMap<>(), loginDto.getUsername());
                var refreshToken = jwtUtil.generateRefreshToken(new HashMap<>(), loginDto.getUsername());
                return JwtResponseDTO.builder()
                        .accessToken(jwtToken)
                        .refreshToken(refreshToken)
                        .build();
    }





    @Override
    public JwtResponseDTO refreshToken(RefreshTokenRequestDto refreshTokenRequestDto) {
            String tokenType = jwtUtil.extractClaim(refreshTokenRequestDto.getToken(), claims -> claims.get("tokenType", String.class));
            String userEmail = jwtUtil.extractUsername(refreshTokenRequestDto.getToken());
            User user = userRepository.findByEmail(userEmail).orElseThrow();
            if (jwtUtil.isValidToken(refreshTokenRequestDto.getToken(), user) && Objects.equals(tokenType, String.valueOf(TokenType.REFRESH))) {
                return JwtResponseDTO.builder().accessToken(jwtUtil.generateToken(new HashMap<>(),user.getUsername()))
                        .refreshToken(jwtUtil.generateRefreshToken(new HashMap<>(), user.getUsername()))
                        .build();
            } else {
                throw new JwtTokenException(messageSource.getMessage(CoreConstant.Exception.INVALID_REFRESH_TOKEN,null,null));
            }


    }

}
