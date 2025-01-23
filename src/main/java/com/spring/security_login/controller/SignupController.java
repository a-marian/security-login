package com.spring.security_login.controller;

import com.spring.security_login.dto.SignupDTO;
import com.spring.security_login.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignupController {

    private final AuthService authService;

    @Autowired
    public SignupController(AuthService authService) {
        this.authService = authService;
    }

    public ResponseEntity<String> signupCustomer(@RequestBody SignupDTO signupDTO){
        boolean isUserCreated=  (authService.createUser(signupDTO));
        if(isUserCreated){
            return ResponseEntity.status(HttpStatus.CREATED).body("User created succesfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not created");
        }
    }
}
