package com.fquer.TezArsivlemeSistemi.service;

import com.fquer.TezArsivlemeSistemi.dto.ThesisDto;
import com.fquer.TezArsivlemeSistemi.exception.NotFoundException;
import com.fquer.TezArsivlemeSistemi.model.File;
import com.fquer.TezArsivlemeSistemi.model.Thesis;
import com.fquer.TezArsivlemeSistemi.repository.ThesisRepository;
import com.fquer.TezArsivlemeSistemi.request.ThesisCreateRequest;
import com.fquer.TezArsivlemeSistemi.request.ThesisSearchRequest;
import com.fquer.TezArsivlemeSistemi.service.thesisDetail.*;
import com.mongodb.client.model.CollationStrength;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.Normalizer;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;
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
    @Autowired
    private MongoTemplate mongoTemplate;

    public ResponseEntity<List<ThesisDto>> getAllTheses() {
        return new ResponseEntity<>(thesisRepository.findAll().stream().map(thesis -> new ThesisDto(thesis)).collect(Collectors.toList()), HttpStatus.OK);
    }
    public ResponseEntity<String> createThesis(ThesisCreateRequest thesisCreateRequest) throws IOException {
        File file = fileService.uploadFile(thesisCreateRequest.getThesisFile(), thesisCreateRequest.getUserId());

        Thesis newThesis = new Thesis();
        newThesis.setThesisTitle(thesisCreateRequest.getThesisTitle());
        newThesis.setThesisLanguage(thesisLanguageService.getThesisLanguageById(thesisCreateRequest.getThesisLanguage()));
        newThesis.setThesisGroup(thesisGroupService.getThesisGroupById(thesisCreateRequest.getThesisGroup()));
        newThesis.setThesisUniversity(thesisUniversityService.getThesisUniversityById(thesisCreateRequest.getThesisUniversity()));
        newThesis.setThesisInstitute(thesisInstituteService.getThesisInstituteById(thesisCreateRequest.getThesisInstitute()));
        newThesis.setThesisMainField(thesisMainFieldService.getThesisMainFieldById(thesisCreateRequest.getThesisMainField()));
        newThesis.setThesisChildrenField(thesisChildrenFieldService.getThesisChildrenFieldById(thesisCreateRequest.getThesisChildrenField()));
        newThesis.setThesisType(thesisTypeService.getThesisTypeById(thesisCreateRequest.getThesisType()));
        newThesis.setThesisWrittenYear(thesisCreateRequest.getThesisWrittenYear());
        newThesis.setThesisAdvisor(thesisCreateRequest.getThesisAdvisor());
        newThesis.setThesisFile(file);

        thesisRepository.save(newThesis);
        return new ResponseEntity<>(file.getFileId(), HttpStatus.OK);
    }

    public ResponseEntity<Void> updateThesis(ThesisCreateRequest thesisCreateRequest, String thesisId) throws IOException {
        Optional<Thesis> foundThesis = thesisRepository.findById(thesisId);

        if (foundThesis.isPresent()) {
            Thesis thesis = foundThesis.get();

            thesis.setThesisTitle(thesisCreateRequest.getThesisTitle());
            thesis.setThesisLanguage(thesisLanguageService.getThesisLanguageById(thesisCreateRequest.getThesisLanguage()));
            thesis.setThesisGroup(thesisGroupService.getThesisGroupById(thesisCreateRequest.getThesisGroup()));
            thesis.setThesisUniversity(thesisUniversityService.getThesisUniversityById(thesisCreateRequest.getThesisUniversity()));
            thesis.setThesisInstitute(thesisInstituteService.getThesisInstituteById(thesisCreateRequest.getThesisInstitute()));
            thesis.setThesisMainField(thesisMainFieldService.getThesisMainFieldById(thesisCreateRequest.getThesisMainField()));
            thesis.setThesisChildrenField(thesisChildrenFieldService.getThesisChildrenFieldById(thesisCreateRequest.getThesisChildrenField()));
            thesis.setThesisType(thesisTypeService.getThesisTypeById(thesisCreateRequest.getThesisType()));
            thesis.setThesisWrittenYear(thesisCreateRequest.getThesisWrittenYear());
            thesis.setThesisAdvisor(thesisCreateRequest.getThesisAdvisor());
            if (thesisCreateRequest.getThesisFile() != null) {
                fileService.deleteFile(thesis.getThesisFile().getId(), thesis.getThesisFile().getFileId(), thesis.getThesisFile().getPreviewImageId());
                File file = fileService.uploadFile(thesisCreateRequest.getThesisFile(), thesisCreateRequest.getUserId());
                thesis.setThesisFile(file);
            }

            thesisRepository.save(thesis);
            return new ResponseEntity<>(HttpStatus.OK);
        }


        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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

    public ResponseEntity<ThesisDto> getThesis(String id) {
        if (thesisRepository.existsById(id)) {
            Thesis thesis = thesisRepository.findById(id).orElseThrow(()->new NotFoundException(id));
            return new ResponseEntity<>(new ThesisDto(thesis), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<ThesisDto>> getLastCountThesis() {
        List<ThesisDto> foundedTheses = thesisRepository.findTop4ByOrderByThesisFileUploadDateDesc().stream().map(thesis -> new ThesisDto(thesis)).collect(Collectors.toList());
        return new ResponseEntity<>(foundedTheses, HttpStatus.OK);
    }

    public ResponseEntity<List<ThesisDto>> searchThesis(ThesisSearchRequest thesisSearchRequest) {
        Criteria criteria = new Criteria();

        if (!Objects.equals(thesisSearchRequest.getThesisTitle(), "")) {
            criteria.and("thesisTitle").regex(Pattern.compile(thesisSearchRequest.getThesisTitle(), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE ));
        }
        if (!Objects.equals(thesisSearchRequest.getThesisLanguage(), "")) {
            criteria.and("thesisLanguageId").is(thesisSearchRequest.getThesisLanguage());
        }
        if (!Objects.equals(thesisSearchRequest.getThesisAdvisor(), "")) {
            criteria.and("thesisAdvisor").regex(Pattern.compile(thesisSearchRequest.getThesisAdvisor(), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE ));
        }
        if (!Objects.equals(thesisSearchRequest.getThesisGroup(), "")) {
            criteria.and("thesisGroupId").is(thesisSearchRequest.getThesisGroup());
        }
        if (!Objects.equals(thesisSearchRequest.getThesisUniversity(), "")) {
            criteria.and("thesisUniversityId").is(thesisSearchRequest.getThesisUniversity());
        }
        if (!Objects.equals(thesisSearchRequest.getThesisInstitute(), "")) {
            criteria.and("thesisInstituteId").is(thesisSearchRequest.getThesisInstitute());
        }
        if (!Objects.equals(thesisSearchRequest.getThesisMainField(), "")) {
            criteria.and("thesisMainFieldId").is(thesisSearchRequest.getThesisMainField());
        }
        if (!Objects.equals(thesisSearchRequest.getThesisChildrenField(), "")) {
            criteria.and("thesisChildrenFieldId").is(thesisSearchRequest.getThesisChildrenField());
        }
        if (!Objects.equals(thesisSearchRequest.getThesisType(), "")) {
            criteria.and("thesisTypeId").is(thesisSearchRequest.getThesisType());
        }
        if (!Objects.equals(thesisSearchRequest.getThesisWrittenYear(), "")) {
            criteria.and("thesisWrittenYear").is(thesisSearchRequest.getThesisWrittenYear());
        }


        Query query = new Query(criteria);

        List<Thesis> thesisList = mongoTemplate.find(query, Thesis.class);
        List<ThesisDto> thesisDtoList = thesisList.stream().map(thesis -> new ThesisDto(thesis)).collect(Collectors.toList());
        return new ResponseEntity<>(thesisDtoList, HttpStatus.OK);

    }

    public ResponseEntity<List<ThesisDto>> searchThesisBasic(String generalSeachWord) {
        Pattern pattern = Pattern.compile(generalSeachWord, Pattern.CASE_INSENSITIVE);
        Criteria criteria = new Criteria();
        criteria.orOperator(
                Criteria.where("thesisTitle").regex(pattern),
                Criteria.where("thesisLanguage.thesisLanguageName").regex(pattern),
                Criteria.where("thesisGroup.thesisGroupName").regex(pattern),
                Criteria.where("thesisUniversity.thesisUniversityName").regex(pattern),
                Criteria.where("thesisInstitute.thesisInstituteName").regex(pattern),
                Criteria.where("thesisMainField.thesisMainFieldName").regex(pattern),
                Criteria.where("thesisType.thesisTypeName").regex(pattern),
                Criteria.where("thesisChildrenField.thesisChildrenFieldName").regex(pattern),
                Criteria.where("thesisWrittenYear").regex(pattern),
                Criteria.where("thesisAdvisor").regex(pattern),
                Criteria.where("thesisFile.uploadDate").regex(pattern),
                Criteria.where("thesisFile.user.userName").regex(pattern),
                Criteria.where("thesisFile.user.userSurname").regex(pattern)
        );

        Query query = new Query(criteria);

        List<Thesis> thesisList = mongoTemplate.find(query, Thesis.class);
        List<ThesisDto> thesisDtoList = thesisList.stream().map(thesis -> new ThesisDto(thesis)).collect(Collectors.toList());
        return new ResponseEntity<>(thesisDtoList, HttpStatus.OK);
    }
}
