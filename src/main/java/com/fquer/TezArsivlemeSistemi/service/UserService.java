package com.fquer.TezArsivlemeSistemi.service;

import com.fquer.TezArsivlemeSistemi.exception.NotFoundException;
import com.fquer.TezArsivlemeSistemi.model.User;
import com.fquer.TezArsivlemeSistemi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(()->new NotFoundException(userId));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
