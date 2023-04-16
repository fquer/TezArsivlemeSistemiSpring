package com.fquer.TezArsivlemeSistemi.service;

import com.fquer.TezArsivlemeSistemi.model.File;
import com.fquer.TezArsivlemeSistemi.model.Thesis;
import com.fquer.TezArsivlemeSistemi.repository.ThesisRepository;
import com.fquer.TezArsivlemeSistemi.request.ThesisCreateRequest;
import com.fquer.TezArsivlemeSistemi.service.thesisDetail.ThesisTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ThesisService {

    @Autowired
    private ThesisRepository thesisRepository;
    @Autowired
    private ThesisTypeService thesisTypeService;
    @Autowired
    private FileService fileService;

    public ResponseEntity<List<Thesis>> getAllTheses() {
        return new ResponseEntity<>(thesisRepository.findAll(), HttpStatus.OK);
    }
    public ResponseEntity<String> createThesis(ThesisCreateRequest thesisCreateRequest) throws IOException {
        File file = fileService.addFile(thesisCreateRequest.getThesisFile(), thesisCreateRequest.getUserId());

        Thesis newThesis = new Thesis();
        newThesis.setThesisTitle(thesisCreateRequest.getThesisTitle());
        newThesis.setThesisTopic(thesisCreateRequest.getThesisTopic());
        newThesis.setThesisLanguage(thesisCreateRequest.getThesisLanguage());
        newThesis.setThesisGroup(thesisCreateRequest.getThesisGroup());
        newThesis.setThesisUniversity(thesisCreateRequest.getThesisUniversity());
        newThesis.setThesisInstitute(thesisCreateRequest.getThesisInstitute());
        newThesis.setThesisMainField(thesisCreateRequest.getThesisMainField());
        newThesis.setThesisChildrenField(thesisCreateRequest.getThesisChildrenField());
        newThesis.setThesisType(thesisTypeService.getThesisTypeById(thesisCreateRequest.getThesisTypeId()));
        newThesis.setThesisFile(file);

        thesisRepository.save(newThesis);
        return new ResponseEntity<>(file.getFileId(), HttpStatus.OK);
    }
}
