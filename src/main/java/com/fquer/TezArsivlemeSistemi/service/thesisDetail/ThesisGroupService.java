package com.fquer.TezArsivlemeSistemi.service.thesisDetail;

import com.fquer.TezArsivlemeSistemi.exception.NotFoundException;
import com.fquer.TezArsivlemeSistemi.model.thesisDetail.ThesisGroup;
import com.fquer.TezArsivlemeSistemi.repository.thesisDetail.ThesisGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ThesisGroupService {
    @Autowired
    private ThesisGroupRepository thesisGroupRepository;

    public ThesisGroup createThesisGroup(ThesisGroup newThesisGroup) {
        return thesisGroupRepository.save(newThesisGroup);
    }

    public ThesisGroup getThesisGroupById(String thesisGroupId) {
        return thesisGroupRepository.findById(thesisGroupId).orElseThrow(()->new NotFoundException(thesisGroupId));
    }

    public List<ThesisGroup> getAllThesisGroups() {
        return thesisGroupRepository.findAll();
    }
}
