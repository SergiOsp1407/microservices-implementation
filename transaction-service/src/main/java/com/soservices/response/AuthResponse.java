package com.soservices.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author $ {USER}
 * Class used to authorization response in user's singin
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String jwt;
    private String message;
    private Boolean status;
}
