package com.supertal.assignment.model.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Jabed Hasan<jabedhasan21@gmail.com>
 * @created on 26/01/2022 - 20:21
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsersPojo {
    private String username;
    private String password;
    private Collection<GrantedAuthority> listOfgrantedAuthorities = new ArrayList<GrantedAuthority>();
}
