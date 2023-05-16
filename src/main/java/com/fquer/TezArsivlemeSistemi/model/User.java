package com.fquer.TezArsivlemeSistemi.model;

import jakarta.persistence.*;
import lombok.Data;


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
    private String passwordResetGUID;
}
