package com.fquer.TezArsivlemeSistemi.service.thesisDetail;

import com.fquer.TezArsivlemeSistemi.exception.NotFoundException;
import com.fquer.TezArsivlemeSistemi.model.thesisDetail.ThesisInstitute;
import com.fquer.TezArsivlemeSistemi.repository.thesisDetail.ThesisInstituteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThesisInstituteService {
    @Autowired
    private ThesisInstituteRepository thesisInstituteRepository;

    public ThesisInstitute createThesisInstitute(ThesisInstitute newThesisInstitute) {
        return thesisInstituteRepository.save(newThesisInstitute);
    }

    public ThesisInstitute getThesisInstituteById(String thesisInstituteId) {
        return thesisInstituteRepository.findById(thesisInstituteId).orElseThrow(()->new NotFoundException(thesisInstituteId));
    }

    public List<ThesisInstitute> getAllThesisInstitutes() {
        return thesisInstituteRepository.findAll();
    }
}
