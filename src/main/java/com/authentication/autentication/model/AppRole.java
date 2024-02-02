package com.authentication.autentication.model;

import com.authentication.autentication.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class AppRole extends GenericEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole roleName;

}
