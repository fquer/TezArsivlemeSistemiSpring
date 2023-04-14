package com.fquer.TezArsivlemeSistemi.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="thesisType")
public class ThesisType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;
    private String thesisTypeName;
}
