package com.fquer.TezArsivlemeSistemi.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="userType")
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;
    private String userTypeName;
}
