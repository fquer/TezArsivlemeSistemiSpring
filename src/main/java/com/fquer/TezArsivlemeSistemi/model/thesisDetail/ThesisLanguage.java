package com.fquer.TezArsivlemeSistemi.model.thesisDetail;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="thesisLanguage")
public class ThesisLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;
    private String thesisLanguageName;
}
