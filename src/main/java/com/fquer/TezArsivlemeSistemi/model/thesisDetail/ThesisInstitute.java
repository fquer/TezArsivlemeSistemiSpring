package com.fquer.TezArsivlemeSistemi.model.thesisDetail;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="thesisInstitute")
public class ThesisInstitute {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;
    private String thesisInstituteName;
}
