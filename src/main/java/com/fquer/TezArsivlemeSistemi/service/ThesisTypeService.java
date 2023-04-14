package com.fquer.TezArsivlemeSistemi.service;

import com.fquer.TezArsivlemeSistemi.exception.NotFoundException;
import com.fquer.TezArsivlemeSistemi.model.ThesisType;
import com.fquer.TezArsivlemeSistemi.repository.ThesisTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThesisTypeService {
    @Autowired
    private ThesisTypeRepository thesisTypeRepository;

    public ThesisType createThesisType(ThesisType newThesisType) {
        return thesisTypeRepository.save(newThesisType);
    }

    public ThesisType getThesisTypeById(String thesisTypeId) {
        return thesisTypeRepository.findById(thesisTypeId).orElseThrow(()->new NotFoundException(thesisTypeId));
    }

    public List<ThesisType> getAllThesisTypes() {
        return thesisTypeRepository.findAll();
    }
}
