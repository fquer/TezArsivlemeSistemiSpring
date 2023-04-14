package com.fquer.TezArsivlemeSistemi.repository;

import com.fquer.TezArsivlemeSistemi.model.Thesis;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ThesisRepository extends MongoRepository<Thesis, String> {
}