package com.authentication.autentication.dto;

import com.authentication.autentication.common.CoreConstant;
import com.authentication.autentication.enums.UserGender;
import com.authentication.autentication.model.AppRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto extends GenericDto {


    @NotBlank(message = CoreConstant.Exception.INVALID_FIRST_NAME)
    private String firstName;

    @NotBlank(message = CoreConstant.Exception.INVALID_LAST_NAME)
    private String lastName;

    @Email(message = CoreConstant.Exception.INVALID_EMAIL)
    @NotNull(message = CoreConstant.Exception.INVALID_EMAIL)
    private String email;

    @NotBlank(message = CoreConstant.Exception.INVALID_PASSWORD)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotBlank(message = CoreConstant.Exception.INVALID_PHONE_NUMBER)
    private String phoneNumber;

    @NotNull(message = CoreConstant.Exception.INVALID_GENDER)
    private UserGender gender;

    private Set<AppRole> roles;
}
