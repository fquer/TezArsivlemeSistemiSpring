package com.fquer.TezArsivlemeSistemi.service;

import com.fquer.TezArsivlemeSistemi.model.File;
import com.fquer.TezArsivlemeSistemi.model.Thesis;
import com.fquer.TezArsivlemeSistemi.model.User;
import com.fquer.TezArsivlemeSistemi.repository.ThesisRepository;
import com.fquer.TezArsivlemeSistemi.request.ThesisCreateRequest;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
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
        newThesis.setThesisType(thesisTypeService.getThesisTypeById(thesisCreateRequest.getThesisTypeId()));
        newThesis.setThesisFile(file);

        thesisRepository.save(newThesis);
        return new ResponseEntity<>(file.getFileId(), HttpStatus.OK);
    }
}
