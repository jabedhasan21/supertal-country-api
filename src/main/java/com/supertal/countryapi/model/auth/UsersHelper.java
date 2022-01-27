package com.supertal.countryapi.model.auth;

/**
 * @author Jabed Hasan<jabedhasan21@gmail.com>
 * @created on 26/01/2022 - 20:20
 */
public class UsersHelper extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    public UsersHelper(User user) {
        super(user.getEmail(), user.getPassword(), user.getListOfgrantedAuthorities());
    }
}
