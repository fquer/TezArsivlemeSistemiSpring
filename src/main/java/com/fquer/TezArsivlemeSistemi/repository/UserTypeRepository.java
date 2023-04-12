package com.fquer.TezArsivlemeSistemi.repository;

import com.fquer.TezArsivlemeSistemi.model.UserType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserTypeRepository extends MongoRepository<UserType, String> {
}
