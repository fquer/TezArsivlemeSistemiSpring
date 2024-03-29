package com.fquer.TezArsivlemeSistemi.repository;

import com.fquer.TezArsivlemeSistemi.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUserMailAndUserPassword(String userMail, String userPassword);
    User findByUserMail(String userMail);
    User findByPasswordResetGUID(String guid);
}
