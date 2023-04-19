package com.fquer.TezArsivlemeSistemi.service;

import com.fquer.TezArsivlemeSistemi.dto.ThesisDto;
import com.fquer.TezArsivlemeSistemi.exception.NotFoundException;
import com.fquer.TezArsivlemeSistemi.model.File;
import com.fquer.TezArsivlemeSistemi.model.Thesis;
import com.fquer.TezArsivlemeSistemi.repository.ThesisRepository;
import com.fquer.TezArsivlemeSistemi.request.ThesisCreateRequest;
import com.fquer.TezArsivlemeSistemi.service.thesisDetail.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ThesisService {

    @Autowired
    private ThesisRepository thesisRepository;
    @Autowired
    private ThesisTypeService thesisTypeService;
    @Autowired
    private ThesisLanguageService thesisLanguageService;
    @Autowired
    private ThesisGroupService thesisGroupService;
    @Autowired
    private ThesisUniversityService thesisUniversityService;
    @Autowired
    private ThesisInstituteService thesisInstituteService;
    @Autowired
    private ThesisMainFieldService thesisMainFieldService;
    @Autowired
    private ThesisChildrenFieldService thesisChildrenFieldService;
    @Autowired
    private FileService fileService;

    public ResponseEntity<List<ThesisDto>> getAllTheses() {
        return new ResponseEntity<>(thesisRepository.findAll().stream().map(thesis -> new ThesisDto(thesis)).collect(Collectors.toList()), HttpStatus.OK);
    }
    public ResponseEntity<String> createThesis(ThesisCreateRequest thesisCreateRequest) throws IOException {
        File file = fileService.uploadFile(thesisCreateRequest.getThesisFile(), thesisCreateRequest.getUserId());

        Thesis newThesis = new Thesis();
        newThesis.setThesisTitle(thesisCreateRequest.getThesisTitle());
        newThesis.setThesisTopic(thesisCreateRequest.getThesisTopic());
        newThesis.setThesisLanguage(thesisLanguageService.getThesisLanguageById(thesisCreateRequest.getThesisLanguage()));
        newThesis.setThesisGroup(thesisGroupService.getThesisGroupById(thesisCreateRequest.getThesisGroup()));
        newThesis.setThesisUniversity(thesisUniversityService.getThesisUniversityById(thesisCreateRequest.getThesisUniversity()));
        newThesis.setThesisInstitute(thesisInstituteService.getThesisInstituteById(thesisCreateRequest.getThesisInstitute()));
        newThesis.setThesisMainField(thesisMainFieldService.getThesisMainFieldById(thesisCreateRequest.getThesisMainField()));
        newThesis.setThesisChildrenField(thesisChildrenFieldService.getThesisChildrenFieldById(thesisCreateRequest.getThesisChildrenField()));
        newThesis.setThesisType(thesisTypeService.getThesisTypeById(thesisCreateRequest.getThesisType()));
        newThesis.setThesisFile(file);

        thesisRepository.save(newThesis);
        return new ResponseEntity<>(file.getFileId(), HttpStatus.OK);
    }

    public ResponseEntity<List<ThesisDto>> getAllThesesByUserId(String userId) {
        return new ResponseEntity<>(thesisRepository.findAllByThesisFileUserId(userId).stream().map(thesis -> new ThesisDto(thesis)).collect(Collectors.toList()), HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteThesis(String id) {
        if (thesisRepository.existsById(id)) {
            Thesis thesis = thesisRepository.findById(id).orElseThrow(()->new NotFoundException(id));
            fileService.deleteFile(thesis.getThesisFile().getId(), thesis.getThesisFile().getFileId(), thesis.getThesisFile().getPreviewImageId());
            thesisRepository.deleteThesisById(id);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
