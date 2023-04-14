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
    private UserService userService;
    @Autowired
    private GridFsTemplate template;

    public ResponseEntity<List<Thesis>> getAllTheses() {
        return new ResponseEntity<>(thesisRepository.findAll(), HttpStatus.OK);
    }
    public ResponseEntity<String> createThesis(ThesisCreateRequest thesisCreateRequest) throws IOException {
        DBObject metadata = new BasicDBObject();
        metadata.put("fileSize", thesisCreateRequest.getThesisFile().getSize());
        Object fileID = template.store(thesisCreateRequest.getThesisFile().getInputStream(), thesisCreateRequest.getThesisFile().getOriginalFilename(), thesisCreateRequest.getThesisFile().getContentType(), metadata);

        User user = userService.getUserById(thesisCreateRequest.getUserId());

        File file = new File();
        file.setFileName(thesisCreateRequest.getThesisFile().getOriginalFilename());
        file.setFileId(fileID.toString());
        file.setUser(user);

        Thesis newThesis = new Thesis();
        newThesis.setThesisTitle(thesisCreateRequest.getThesisTitle());
        newThesis.setThesisTopic(thesisCreateRequest.getThesisTopic());
        newThesis.setThesisType(thesisTypeService.getThesisTypeById(thesisCreateRequest.getThesisTypeId()));
        newThesis.setThesisFile(file);

        Thesis thesis = thesisRepository.save(newThesis);
        return new ResponseEntity<>(fileID.toString(), HttpStatus.OK);
    }
}
