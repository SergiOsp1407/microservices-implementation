package com.soservices.service;

import com.soservices.modal.User;
import com.soservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author $ {USER}
 **/
@Service
public class CustomerUserServiceImplementation implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserEmail(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found with email" + username);
        }

        List<GrantedAuthority>authorities = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getUserPassword(), authorities);
    }
}
