package com.supertal.countryapi.repository;

import com.supertal.countryapi.model.auth.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Jabed Hasan<jabedhasan21@gmail.com>
 * @created on 27/01/2022 - 14:04
 */
public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByEmail(String email);
}
