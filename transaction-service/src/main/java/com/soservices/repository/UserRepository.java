package com.soservices.repository;

import com.soservices.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author $ {USER}
 **/
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUserEmail(String email);


}
