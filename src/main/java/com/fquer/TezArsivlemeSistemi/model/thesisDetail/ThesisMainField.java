package com.fquer.TezArsivlemeSistemi.model.thesisDetail;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="thesisMainField")
public class ThesisMainField {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;
    private String thesisMainFieldName;
}
