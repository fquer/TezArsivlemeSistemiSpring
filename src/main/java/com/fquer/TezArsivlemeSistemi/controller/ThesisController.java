package com.fquer.TezArsivlemeSistemi.controller;

import com.fquer.TezArsivlemeSistemi.model.Thesis;
import com.fquer.TezArsivlemeSistemi.request.ThesisCreateRequest;
import com.fquer.TezArsivlemeSistemi.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/thesis")
@CrossOrigin
public class ThesisController {

    @Autowired
    private ThesisService thesisService;

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<Thesis>> getAllTheses() {
        return thesisService.getAllTheses();
    }

    @PostMapping(value = "/add")
    public ResponseEntity<String> createThesis(@ModelAttribute ThesisCreateRequest thesisCreateRequest) throws IOException {
        return thesisService.createThesis(thesisCreateRequest);
    }
}
