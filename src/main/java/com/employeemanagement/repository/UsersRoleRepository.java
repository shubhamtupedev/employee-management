package com.employeemanagement.repository;

import com.employeemanagement.entity.UsersRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRoleRepository extends JpaRepository<UsersRoles, Long> {
}
