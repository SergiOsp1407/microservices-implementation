package com.soservices.controller;

import com.soservices.config.JwtProvider;
import com.soservices.modal.User;
import com.soservices.repository.UserRepository;
import com.soservices.request.LoginRequest;
import com.soservices.response.AuthResponse;
import com.soservices.service.CustomerUserServiceImplementation;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author $ {USER}
 **/

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerUserServiceImplementation customerUserDetails;

    //SignUp method implementation
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception{

        String email = user.getUserEmail();
        String password = user.getUserPassword();
        String fullName = user.getFullUserName();
        String role = user.getUserRole();

        User emailExists = userRepository.findByUserEmail(email);

        if(emailExists != null) {
            throw new Exception("Email Already used with another account");
        }

        //If email does not exist then create the user
        User createdUser = new User();
        createdUser.setUserEmail(email);
        createdUser.setFullUserName(fullName);
        createdUser.setUserRole(role);
        createdUser.setUserPassword(passwordEncoder.encode(password));
//        createdUser.setUserPassword("...");
        User savedUser = userRepository.save(createdUser);


        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Successfully registered user");
        authResponse.setStatus(true);

        return new ResponseEntity<>(authResponse, HttpStatus.OK);

    }

    //SignIn method implementation
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest){


        String username = loginRequest.getUserEmail();
        String password = loginRequest.getUserPassword();

        System.out.println(username + "--------" + password);

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();

        authResponse.setMessage("User successfully logged in");
        authResponse.setJwt(token);
        authResponse.setStatus(true);

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customerUserDetails.loadUserByUsername(username);

        System.out.println("sign in userDetails: " + userDetails);

        if(userDetails == null) {
            System.out.println("sign in userDetails - null " + userDetails);
            throw new BadCredentialsException("Invalid username or password");
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            System.out.println("sign in userDetails - incorrect password" + userDetails);
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

    }

}
