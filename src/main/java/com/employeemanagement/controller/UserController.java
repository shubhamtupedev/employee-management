package com.employeemanagement.controller;

import com.employeemanagement.entity.dto.ApiResponseDto;
import com.employeemanagement.entity.dto.LoginRequestDto;
import com.employeemanagement.entity.dto.LoginResponseDto;
import com.employeemanagement.utility.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping(value = "login", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<ApiResponseDto<LoginResponseDto>> loginUser(@Valid LoginRequestDto loginRequestDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername().toUpperCase(), loginRequestDto.getPassword()));
        String token = null;
        try {
            token = jwtUtils.generateTokenFromUsername(loginRequestDto.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }

        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setMessage("User logged in successfully.");
        loginResponseDto.setJwtToken(token);

        return new ResponseEntity<>(new ApiResponseDto<>(loginResponseDto), HttpStatus.OK);
    }



}
