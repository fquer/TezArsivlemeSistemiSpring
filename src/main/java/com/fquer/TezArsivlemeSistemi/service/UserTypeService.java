package com.fquer.TezArsivlemeSistemi.service;

import com.fquer.TezArsivlemeSistemi.exception.NotFoundException;
import com.fquer.TezArsivlemeSistemi.model.UserType;
import com.fquer.TezArsivlemeSistemi.repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTypeService {
    @Autowired
    private UserTypeRepository userTypeRepository;

    public UserType createUserType(UserType newUserType) {
        return userTypeRepository.save(newUserType);
    }

    public UserType getUserTypeById(String userTypeId) {
        return userTypeRepository.findById(userTypeId).orElseThrow(()->new NotFoundException(userTypeId));
    }

    public List<UserType> getAllUserTypes() {
        return userTypeRepository.findAll();
    }
}
