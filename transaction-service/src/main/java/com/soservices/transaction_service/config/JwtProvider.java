package com.soservices.transaction_service.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author $ {USER}
 * Class used for generating JWT, getting emails from JWT
 **/
public class JwtProvider {

    static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public static String generateToken(Authentication authentication) {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String roles = populateAuthorities(authorities);

        String jwt = Jwts.builder().setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + 86400000)).claim("email",authentication.getName()).claim("authorities",roles).signWith(key).compact();

        return jwt;

    }

    private static String populateAuthorities(Collection<? extends GrantedAuthority> collection) {

        Set<String> auths = new HashSet<>();

        for (GrantedAuthority grantedAuthority : collection) {
            auths.add(grantedAuthority.getAuthority());

        }
        return String.join(",", auths);

    }
}
