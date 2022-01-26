package com.supertal.assignment.service.auth;

import com.supertal.assignment.model.UsersHelper;
import com.supertal.assignment.model.auth.UsersPojo;
import com.supertal.assignment.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Jabed Hasan<jabedhasan21@gmail.com>
 * @created on 26/01/2022 - 20:18
 */
@Service
public class UsersService implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public UsersHelper loadUserByUsername(final String username) throws UsernameNotFoundException {
        try {
            UsersPojo usersPojo = usersRepository.getUserDetails(username);
            return new UsersHelper(usersPojo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
    }
}
