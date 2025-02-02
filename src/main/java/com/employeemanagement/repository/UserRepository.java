package com.employeemanagement.repository;

import com.employeemanagement.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    public Optional<Users> findByUsername(String username);
}
