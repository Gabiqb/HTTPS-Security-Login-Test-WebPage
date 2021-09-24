package com.LoginTest.server.repositories;

import com.LoginTest.server.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);


}
