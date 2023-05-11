package com.fquer.TezArsivlemeSistemi.service;

import com.fquer.TezArsivlemeSistemi.dto.UserDto;
import com.fquer.TezArsivlemeSistemi.exception.NotFoundException;
import com.fquer.TezArsivlemeSistemi.model.User;
import com.fquer.TezArsivlemeSistemi.model.UserType;
import com.fquer.TezArsivlemeSistemi.repository.UserRepository;
import com.fquer.TezArsivlemeSistemi.request.UserLoginRequest;
import com.fquer.TezArsivlemeSistemi.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTypeService userTypeService;

    public ResponseEntity<Void> createUser(UserRequest newUserRequest) {
        User foundedUser = userRepository.findByUserMail(newUserRequest.getUserMail());
        if (foundedUser != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        else {
            UserType userType = userTypeService.getUserTypeById(newUserRequest.getUserTypeId());
            User newUser = new User();
            newUser.setUserName(newUserRequest.getUserName());
            newUser.setUserSurname(newUserRequest.getUserSurname());
            newUser.setUserMail(newUserRequest.getUserMail());
            newUser.setUserType(userType);
            newUser.setUserPassword(newUserRequest.getUserPassword());
            User createdUser = userRepository.save(newUser);
            if (createdUser != null) {
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(()->new NotFoundException(userId));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<UserDto> checkUserLogin(UserLoginRequest userLoginRequest) {
        User foundUser = userRepository.findByUserMailAndUserPassword(userLoginRequest.getUserMail(), userLoginRequest.getUserPassword());
        if (foundUser != null) {
            return new ResponseEntity<>(new UserDto(foundUser), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }
}
