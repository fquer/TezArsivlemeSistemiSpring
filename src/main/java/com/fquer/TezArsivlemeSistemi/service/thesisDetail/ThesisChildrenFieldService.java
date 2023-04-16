package com.fquer.TezArsivlemeSistemi.service.thesisDetail;

import com.fquer.TezArsivlemeSistemi.exception.NotFoundException;
import com.fquer.TezArsivlemeSistemi.model.thesisDetail.ThesisChildrenField;
import com.fquer.TezArsivlemeSistemi.repository.thesisDetail.ThesisChildrenFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThesisChildrenFieldService {
    @Autowired
    private ThesisChildrenFieldRepository thesisChildrenFieldRepository;

    public ThesisChildrenField createThesisChildrenField(ThesisChildrenField newThesisChildrenField) {
        return thesisChildrenFieldRepository.save(newThesisChildrenField);
    }

    public ThesisChildrenField getThesisChildrenFieldById(String ThesisChildrenFieldId) {
        return thesisChildrenFieldRepository.findById(ThesisChildrenFieldId).orElseThrow(()->new NotFoundException(ThesisChildrenFieldId));
    }

    public List<ThesisChildrenField> getAllThesisChildrenFields() {
        return thesisChildrenFieldRepository.findAll();
    }
}
