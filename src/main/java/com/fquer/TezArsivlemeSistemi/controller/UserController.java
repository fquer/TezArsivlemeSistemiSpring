package com.fquer.TezArsivlemeSistemi.controller;

import com.fquer.TezArsivlemeSistemi.dto.UserDto;
import com.fquer.TezArsivlemeSistemi.model.User;
import com.fquer.TezArsivlemeSistemi.request.UserLoginRequest;
import com.fquer.TezArsivlemeSistemi.request.UserRequest;
import com.fquer.TezArsivlemeSistemi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/getAll")
    public List<UserDto> getAllUser() {
        return userService.getAllUsers().stream().map(user -> new UserDto(user)).toList();
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Void> createUser(@RequestBody UserRequest newUserRequest) {
        User user = userService.createUser(newUserRequest);
        if (user != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
        return userService.checkUserLogin(userLoginRequest);
    }

}
