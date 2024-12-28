package com.soservices.transaction_service.repository;

import com.soservices.transaction_service.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author $ {USER}
 **/
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);
}
