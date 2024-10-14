package com.soservices.service;

import com.soservices.config.JwtProvider;
import com.soservices.modal.User;
import com.soservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author $ {USER}
 **/
@Service
public class UserServiceImplementation implements UserService{


    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserProfile(String jwt) {
        String userEmail = JwtProvider.getEmailFromJwtToken(jwt);
        return userRepository.findByUserEmail(userEmail);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
