package com.fquer.TezArsivlemeSistemi.controller;

import com.fquer.TezArsivlemeSistemi.dto.ThesisDetailDto;
import com.fquer.TezArsivlemeSistemi.model.thesisDetail.*;
import com.fquer.TezArsivlemeSistemi.service.ThesisDetailService;
import com.fquer.TezArsivlemeSistemi.service.thesisDetail.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/thesisDetail")
@CrossOrigin
public class ThesisDetailController {
    @Autowired
    private ThesisDetailService thesisDetailService;
    @Autowired
    private ThesisTypeService thesisTypeService;
    @Autowired
    private ThesisChildrenFieldService thesisChildrenFieldService;
    @Autowired
    private ThesisGroupService thesisGroupService;
    @Autowired
    private ThesisInstituteService thesisInstituteService;
    @Autowired
    private ThesisLanguageService thesisLanguageService;
    @Autowired
    private ThesisMainFieldService thesisMainFieldService;
    @Autowired
    private ThesisUniversityService thesisUniversityService;

    @GetMapping(value = "/getAll")
    public ResponseEntity<ThesisDetailDto> getThesisDeatilTypes() {
        return thesisDetailService.getAllThesisDetails();
    }

    @PostMapping(value = "/type/add")
    public ResponseEntity<Void> createThesisType(@RequestBody ThesisType newThesisType) {
        ThesisType thesisType = thesisTypeService.createThesisType(newThesisType);
        if (thesisType != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/childrenField/add")
    public ResponseEntity<Void> createThesisChildrenField(@RequestBody ThesisChildrenField newThesisChildrenField) {
        ThesisChildrenField thesisChildrenField = thesisChildrenFieldService.createThesisChildrenField(newThesisChildrenField);
        if (thesisChildrenField != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/group/add")
    public ResponseEntity<Void> createGroup(@RequestBody ThesisGroup newThesisGroup) {
        ThesisGroup thesisGroup = thesisGroupService.createThesisGroup(newThesisGroup);
        if (thesisGroup != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/institute/add")
    public ResponseEntity<Void> createInstitute(@RequestBody ThesisInstitute newThesisInstitute) {
        ThesisInstitute thesisInstitute = thesisInstituteService.createThesisInstitute(newThesisInstitute);
        if (thesisInstitute != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/language/add")
    public ResponseEntity<Void> createLanguage(@RequestBody ThesisLanguage newThesisLanguage) {
        ThesisLanguage thesisLanguage = thesisLanguageService.createThesisLanguage(newThesisLanguage);
        if (thesisLanguage != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/mainField/add")
    public ResponseEntity<Void> createMainField(@RequestBody ThesisMainField newThesisMainField) {
        ThesisMainField thesisMainField = thesisMainFieldService.createThesisMainField(newThesisMainField);
        if (thesisMainField != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/university/add")
    public ResponseEntity<Void> createUniversity(@RequestBody ThesisUniversity newThesisUniversity) {
        ThesisUniversity thesisUniversity = thesisUniversityService.createThesisUniversity(newThesisUniversity);
        if (thesisUniversity != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
