package com.fquer.TezArsivlemeSistemi.service.thesisDetail;

import com.fquer.TezArsivlemeSistemi.exception.NotFoundException;
import com.fquer.TezArsivlemeSistemi.model.thesisDetail.ThesisLanguage;
import com.fquer.TezArsivlemeSistemi.repository.thesisDetail.ThesisLanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThesisLanguageService {
    @Autowired
    private ThesisLanguageRepository thesisLanguageRepository;

    public ThesisLanguage createThesisLanguage(ThesisLanguage newThesisLanguage) {
        return thesisLanguageRepository.save(newThesisLanguage);
    }

    public ThesisLanguage getThesisLanguageById(String thesisLanguageId) {
        return thesisLanguageRepository.findById(thesisLanguageId).orElseThrow(()->new NotFoundException(thesisLanguageId));
    }

    public List<ThesisLanguage> getAllThesisLanguages() {
        return thesisLanguageRepository.findAll();
    }
}
