package com.supertal.assignment.model.auth;

import com.supertal.assignment.model.auth.UsersPojo;
import org.springframework.security.core.userdetails.User;

/**
 * @author Jabed Hasan<jabedhasan21@gmail.com>
 * @created on 26/01/2022 - 20:20
 */
public class UsersHelper extends User {
    private static final long serialVersionUID = 1L;

    public UsersHelper(UsersPojo user) {
        super(user.getUsername(), user.getPassword(), user.getListOfgrantedAuthorities());
    }
}
