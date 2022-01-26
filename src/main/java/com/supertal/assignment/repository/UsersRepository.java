package com.supertal.assignment.repository;

import com.supertal.assignment.model.auth.UsersPojo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Jabed Hasan<jabedhasan21@gmail.com>
 * @created on 26/01/2022 - 20:25
 */
@Repository
public class UsersRepository {

    public UsersPojo getUserDetails(String username) {
        // ToDo: Check username & password
        Collection<GrantedAuthority> listOfgrantedAuthorities = new ArrayList<GrantedAuthority>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_SYSTEM_ADMIN");
        listOfgrantedAuthorities.add(grantedAuthority);
        UsersPojo user = new UsersPojo();
        user.setUsername(username);
        //ToDo: rm password
        user.setPassword("$2a$08$fL7u5xcvsZl78su29x1ti.dxI.9rYO8t0q5wk2ROJ.1cdR53bmaVG");
        user.setListOfgrantedAuthorities(listOfgrantedAuthorities);
        return user;
    }
}
