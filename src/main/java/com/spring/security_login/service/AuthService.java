package com.spring.security_login.service;


import com.spring.security_login.dto.SignupDTO;

public interface AuthService {

    boolean createUser(SignupDTO signupDTO);

}
