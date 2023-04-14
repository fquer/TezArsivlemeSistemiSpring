package com.fquer.TezArsivlemeSistemi.controller;

import com.fquer.TezArsivlemeSistemi.model.ThesisType;
import com.fquer.TezArsivlemeSistemi.service.ThesisTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/thesisType")
@CrossOrigin
public class ThesisTypeController {

    @Autowired
    private ThesisTypeService thesisTypeService;

    @GetMapping(value = "/getAll")
    public List<ThesisType> getAllThesisTypes() {
        return thesisTypeService.getAllThesisTypes();
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Void> createThesisType(@RequestBody ThesisType newThesisType) {
        ThesisType thesisType = thesisTypeService.createThesisType(newThesisType);
        if (thesisType != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
