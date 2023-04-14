package com.fquer.TezArsivlemeSistemi.repository;

import com.fquer.TezArsivlemeSistemi.model.ThesisType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ThesisTypeRepository extends MongoRepository<ThesisType, String> {
}
