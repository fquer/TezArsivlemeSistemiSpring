package com.fquer.TezArsivlemeSistemi.service;

import com.fquer.TezArsivlemeSistemi.dto.ThesisDetailDto;
import com.fquer.TezArsivlemeSistemi.exception.NotFoundException;
import com.fquer.TezArsivlemeSistemi.model.thesisDetail.ThesisMainField;
import com.fquer.TezArsivlemeSistemi.model.thesisDetail.ThesisType;
import com.fquer.TezArsivlemeSistemi.model.thesisDetail.ThesisUniversity;
import com.fquer.TezArsivlemeSistemi.repository.thesisDetail.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThesisDetailService {
    @Autowired
    private ThesisTypeRepository thesisTypeRepository;
    @Autowired
    private ThesisChildrenFieldRepository thesisChildrenFieldRepository;
    @Autowired
    private ThesisGroupRepository thesisGroupRepository;
    @Autowired
    private ThesisInstituteRepository thesisInstituteRepository;
    @Autowired
    private ThesisLanguageRepository thesisLanguageRepository;
    @Autowired
    private ThesisMainFieldRepository thesisMainFieldRepository;
    @Autowired
    private ThesisUniversityRepository thesisUniversityRepository;


    public ResponseEntity<ThesisDetailDto> getAllThesisDetails() {
        return new ResponseEntity<>(new ThesisDetailDto(
                thesisTypeRepository.findAll(),
                thesisChildrenFieldRepository.findAll(),
                thesisGroupRepository.findAll(),
                thesisInstituteRepository.findAll(),
                thesisLanguageRepository.findAll(),
                thesisMainFieldRepository.findAll(),
                thesisUniversityRepository.findAll()), HttpStatus.OK);
    }
}
