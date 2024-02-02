package com.authentication.autentication.controller;

import com.authentication.autentication.dto.JwtResponseDTO;
import com.authentication.autentication.dto.LoginDto;
import com.authentication.autentication.dto.RefreshTokenRequestDto;
import com.authentication.autentication.dto.UserDto;
import com.authentication.autentication.exception.EmailAlreadyExistException;
import com.authentication.autentication.service.AuthenticationService;
import com.authentication.autentication.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
@Tag(
        name = "Authentication",
        description = "The API for the authentication"
)

public class AuthController {

    private final UserService userService;
    private final AuthenticationService authenticationService;


    @Operation(
            summary = "Adding a new user",
            description = "API to add a new user to our database"
    )
    @PostMapping("/Signup")
    public ResponseEntity<UserDto> addNewUser(@RequestBody @Valid UserDto userDto) throws MethodArgumentNotValidException, EmailAlreadyExistException {
        UserDto newUser = this.userService.addNewUser(userDto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Login a user",
            description = "API to log a user to our application"
    )
    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> AuthenticateAndGetToken(@RequestBody LoginDto loginDto) throws UsernameNotFoundException {
        JwtResponseDTO jwtResponseDTO = authenticationService.accessToken(loginDto);
        return new ResponseEntity<>(jwtResponseDTO,HttpStatus.ACCEPTED);
    }



    @Operation(
            summary = "Creating a new refresh Token",
            description = "This API is used for generating a new refreshToken"
    )
    @PostMapping("/refresh")
    public ResponseEntity<JwtResponseDTO> refresh(@RequestBody RefreshTokenRequestDto refreshTokenRequestDto) {
        JwtResponseDTO jwtResponseDTO = authenticationService.refreshToken(refreshTokenRequestDto);
        return new ResponseEntity<>(jwtResponseDTO,HttpStatus.ACCEPTED);
    }






}
