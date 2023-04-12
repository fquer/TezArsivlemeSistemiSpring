package com.fquer.TezArsivlemeSistemi.controller;

import com.fquer.TezArsivlemeSistemi.dto.UserDto;
import com.fquer.TezArsivlemeSistemi.model.UserType;
import com.fquer.TezArsivlemeSistemi.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userType")
@CrossOrigin
public class UserTypeController {

    @Autowired
    private UserTypeService userTypeService;

    @GetMapping(value = "/getAll")
    public List<UserType> getAllUserTypes() {
        return userTypeService.getAllUserTypes();
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Void> createUserType(@RequestBody UserType newUserType) {
        UserType userType = userTypeService.createUserType(newUserType);
        if (userType != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
