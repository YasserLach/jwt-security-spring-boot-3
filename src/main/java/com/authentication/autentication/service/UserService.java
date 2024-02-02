package com.authentication.autentication.service;

import com.authentication.autentication.dto.UserDto;
import com.authentication.autentication.model.User;
import org.springframework.web.bind.MethodArgumentNotValidException;


public interface UserService {

     UserDto addNewUser(UserDto userDto) throws MethodArgumentNotValidException;
    // User findByEmail(String email);

}
