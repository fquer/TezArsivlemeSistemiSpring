package com.fquer.TezArsivlemeSistemi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fquer.TezArsivlemeSistemi.request.UserRequest;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;
    private String userName;
    private String userSurname;
    private String userMail;
    @OneToOne
    private UserType userType;
    private String userPassword;
    @OneToMany(mappedBy = "user")
    private Set<File> files;
}
