package com.authentication.autentication.service.Impl;

import com.authentication.autentication.common.CoreConstant;
import com.authentication.autentication.dto.UserDto;
import com.authentication.autentication.enums.UserRole;
import com.authentication.autentication.exception.EmailAlreadyExistException;
import com.authentication.autentication.model.AppRole;
import com.authentication.autentication.model.User;
import com.authentication.autentication.repository.AppRoleRepository;
import com.authentication.autentication.repository.UserRepository;
import com.authentication.autentication.service.UserService;
import com.authentication.autentication.utils.MapHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final AppRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    MessageSource messageSource;



    @Override
    public UserDto addNewUser(UserDto userDto) {
        try {
            Optional<User> userEmail = userRepo.findByEmail(userDto.getEmail());
            if (userEmail.isPresent()) throw new EmailAlreadyExistException(CoreConstant.Exception.INVALID_EMAIL);
            AppRole appRole = this.roleRepository.findByRoleName(UserRole.USER);
            Set<AppRole>roles =new HashSet<>();
            roles.add(appRole);
            User userEntity = MapHelper.INSTANCE.dtoToentity(userDto);
            userEntity.setRoles(roles);
            userEntity.setCreatedAt(LocalDateTime.now());
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            User userSaved = this.userRepo.save(userEntity);
            return MapHelper.INSTANCE.entityToDto(userSaved);
        } catch (EmailAlreadyExistException e) {
            throw new EmailAlreadyExistException(messageSource.getMessage(CoreConstant.Exception.INVALID_EMAIL, null, null));
        }
    }


}
