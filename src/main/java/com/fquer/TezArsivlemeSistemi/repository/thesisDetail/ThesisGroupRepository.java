package com.fquer.TezArsivlemeSistemi.repository.thesisDetail;

import com.fquer.TezArsivlemeSistemi.model.thesisDetail.ThesisGroup;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ThesisGroupRepository extends MongoRepository<ThesisGroup, String> {
}
