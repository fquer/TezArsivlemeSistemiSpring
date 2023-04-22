package com.fquer.TezArsivlemeSistemi.controller;

import com.fquer.TezArsivlemeSistemi.dto.ThesisDto;
import com.fquer.TezArsivlemeSistemi.model.Thesis;
import com.fquer.TezArsivlemeSistemi.request.ThesisCreateRequest;
import com.fquer.TezArsivlemeSistemi.request.ThesisGetAllByUserIdRequest;
import com.fquer.TezArsivlemeSistemi.request.ThesisUpdateRequest;
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
    public ResponseEntity<List<ThesisDto>> getAllTheses() {
        return thesisService.getAllTheses();
    }

    @PostMapping(value = "/add")
    public ResponseEntity<String> createThesis(@ModelAttribute ThesisCreateRequest thesisCreateRequest) throws IOException {
        return thesisService.createThesis(thesisCreateRequest);
    }

    @PutMapping(value = "/{thesisId}")
    public ResponseEntity<Void> updateThesis(@ModelAttribute ThesisCreateRequest thesisCreateRequest, @PathVariable String thesisId) throws IOException {
        return thesisService.updateThesis(thesisCreateRequest, thesisId);
    }
    @GetMapping(value = "/getAllByUserId/{userId}")
    public ResponseEntity<List<ThesisDto>> getAllThesesByUserId(@PathVariable("userId") String userId) {
        return thesisService.getAllThesesByUserId(userId);
    }

    @DeleteMapping("/{thesisId}")
    public ResponseEntity<Void> deleteThesis(@PathVariable String thesisId) {
        return thesisService.deleteThesis(thesisId);
    }

    @GetMapping("/{thesisId}")
    public ResponseEntity<ThesisDto> getThesis(@PathVariable String thesisId) {
        return thesisService.getThesis(thesisId);
    }
}
