package com.soservices.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author $ {USER}
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String userEmail;
    private String userPassword;
}
