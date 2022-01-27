package com.supertal.assignment.service.impl;

import com.supertal.assignment.exception.EntityNotFoundException;
import com.supertal.assignment.model.auth.UsersHelper;
import com.supertal.assignment.model.auth.User;
import com.supertal.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Jabed Hasan<jabedhasan21@gmail.com>
 * @created on 26/01/2022 - 20:18
 */
@Service
public class UsersServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UsersHelper loadUserByUsername(final String email) throws UsernameNotFoundException {
        try {
            Collection<GrantedAuthority> listOfgrantedAuthorities = new ArrayList<GrantedAuthority>();
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_SYSTEM_ADMIN");
            listOfgrantedAuthorities.add(grantedAuthority);
            User user = userRepository.findByEmail(email);

            user.setListOfgrantedAuthorities(listOfgrantedAuthorities);
            return new UsersHelper(user);
        } catch (Exception e) {
            e.printStackTrace();
            //throw new UsernameNotFoundException("User " + email + " was not found in the database");
            throw new EntityNotFoundException(User.class, "email", email);
        }
    }
}
