package com.soservices.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author $ {USER}
 **/

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userPassword;
    private String userEmail;
    private String userRole;
    private String fullUserName;


}
