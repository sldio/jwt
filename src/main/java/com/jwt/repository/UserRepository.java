package com.jwt.repository;

import com.jwt.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByName(String firstName);
    User findById(String id);
}
