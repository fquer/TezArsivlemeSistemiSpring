package com.fquer.TezArsivlemeSistemi.service.thesisDetail;

import com.fquer.TezArsivlemeSistemi.exception.NotFoundException;
import com.fquer.TezArsivlemeSistemi.model.thesisDetail.ThesisMainField;
import com.fquer.TezArsivlemeSistemi.repository.thesisDetail.ThesisMainFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThesisMainFieldService {
    @Autowired
    private ThesisMainFieldRepository thesisMainFieldRepository;

    public ThesisMainField createThesisMainField(ThesisMainField newThesisMainField) {
        return thesisMainFieldRepository.save(newThesisMainField);
    }

    public ThesisMainField getThesisMainFieldById(String thesisMainFieldId) {
        return thesisMainFieldRepository.findById(thesisMainFieldId).orElseThrow(()->new NotFoundException(thesisMainFieldId));
    }

    public List<ThesisMainField> getAllThesisMainFields() {
        return thesisMainFieldRepository.findAll();
    }
}
