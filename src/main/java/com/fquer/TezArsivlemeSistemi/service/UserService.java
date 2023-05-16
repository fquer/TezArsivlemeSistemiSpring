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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTypeService userTypeService;

    private  JavaMailSender mailSender;

    public UserService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

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

    public ResponseEntity<Void> resetPassword(String email) {
        User foundedUser = userRepository.findByUserMail(email);
        if (foundedUser != null) {
            UUID guid = UUID.randomUUID();
            foundedUser.setPasswordResetGUID(guid.toString());
            userRepository.save(foundedUser);
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("beykoz.tez.arsivleme.sistemi@gmail.com");
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject("Beykoz Tez Arsivleme Sistemi | Parola Sıfırlama");
            simpleMailMessage.setText("Beykoz Tez Arsivleme Sistemi\nParolanızı  http://localhost:3000/resetPasswordSubmit/" + guid.toString() + " adresinden sifirlayabilirsiniz.");
            this.mailSender.send(simpleMailMessage);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity<Void> resetPasswordWithToken(String token, String newPassword) {
        User foundedUser = userRepository.findByPasswordResetGUID(token);
        if (foundedUser != null) {
            foundedUser.setPasswordResetGUID(null);
            foundedUser.setUserPassword(newPassword);
            userRepository.save(foundedUser);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
