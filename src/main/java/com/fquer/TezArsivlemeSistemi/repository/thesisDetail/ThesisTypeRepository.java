package com.fquer.TezArsivlemeSistemi.repository.thesisDetail;

import com.fquer.TezArsivlemeSistemi.model.thesisDetail.ThesisType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ThesisTypeRepository extends MongoRepository<ThesisType, String> {
}
