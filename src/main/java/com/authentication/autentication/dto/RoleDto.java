package com.authentication.autentication.dto;

import com.authentication.autentication.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleDto extends GenericDto{
    private UserRole roleName;
}
