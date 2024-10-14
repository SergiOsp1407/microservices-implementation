package com.soservices.service;

import com.soservices.modal.User;

import java.util.List;

/**
 * @author $ {USER}
 **/
public interface UserService {

    public User getUserProfile(String jwt);
    public List<User> getAllUsers();


}
