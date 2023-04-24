package com.fquer.TezArsivlemeSistemi.repository;

import com.fquer.TezArsivlemeSistemi.model.Thesis;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ThesisRepository extends MongoRepository<Thesis, String> {
    List<Thesis> findAllByThesisFileUserId(String userId);
    void deleteThesisById(String id);
    List<Thesis> findTop5ByOrderByThesisFileUploadDateDesc();
}