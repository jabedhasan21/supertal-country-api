package com.supertal.assignment.model.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
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
@Document(collection = "users")
public class User {
    @Id
    private ObjectId id;
    private String name;

    @Indexed(unique = true)
    private String email;
    private String password;

    @Transient
    private Collection<GrantedAuthority> listOfgrantedAuthorities = new ArrayList<GrantedAuthority>();
}
