package com.authentication.autentication.repository;

import com.authentication.autentication.enums.UserRole;
import com.authentication.autentication.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole,Long> {

    AppRole findByRoleName(UserRole roleName);

}
