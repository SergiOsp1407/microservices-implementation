package com.soservices.transaction_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

/**
 * @author $ {USER}
 **/
@Configuration
public class ApplicationConfiguration {

    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(
                managment -> managment.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                )
        ).authorizeHttpRequests(
                Authorize -> Authorize.requestMatchers("/api/**").authenticated().anyRequest().permitAll()
        ).addFilterBefore(null, BasicAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        return null;
    }
}
