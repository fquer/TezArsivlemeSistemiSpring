package com.fquer.TezArsivlemeSistemi.service.thesisDetail;

import com.fquer.TezArsivlemeSistemi.exception.NotFoundException;
import com.fquer.TezArsivlemeSistemi.model.thesisDetail.ThesisUniversity;
import com.fquer.TezArsivlemeSistemi.repository.thesisDetail.ThesisUniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThesisUniversityService {
    @Autowired
    private ThesisUniversityRepository thesisUniversityRepository;

    public ThesisUniversity createThesisUniversity(ThesisUniversity newThesisUniversity) {
        return thesisUniversityRepository.save(newThesisUniversity);
    }

    public ThesisUniversity getThesisUniversityById(String thesisUniversityId) {
        return thesisUniversityRepository.findById(thesisUniversityId).orElseThrow(()->new NotFoundException(thesisUniversityId));
    }

    public List<ThesisUniversity> getAllThesisUniversitys() {
        return thesisUniversityRepository.findAll();
    }
}
