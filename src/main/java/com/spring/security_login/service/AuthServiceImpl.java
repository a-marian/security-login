package com.spring.security_login.service;

import com.spring.security_login.dto.SignupDTO;
import com.spring.security_login.entity.User;
import com.spring.security_login.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean createUser(SignupDTO signupDTO){
        //Check if user already exist
        if(userRepository.existByEmail(signupDTO.email())){
            return false;
        }
        User user = new User();
        BeanUtils.copyProperties(signupDTO, user);
        //hash password
        String encryptedPassword= passwordEncoder.encode(signupDTO.password());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        return true;
    }
}
