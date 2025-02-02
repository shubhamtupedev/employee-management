package com.employeemanagement.service.impl;

import com.employeemanagement.common.ApplicationErrorCodes;
import com.employeemanagement.entity.Users;
import com.employeemanagement.entity.dto.ApplicationUsersPrincipalDto;
import com.employeemanagement.exception.UsernameNotFoundException;
import com.employeemanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username.toUpperCase()).orElseThrow(() -> new UsernameNotFoundException(ApplicationErrorCodes.USER_NOT_FOUND_ERROR));
        return new ApplicationUsersPrincipalDto(user);
    }
}
