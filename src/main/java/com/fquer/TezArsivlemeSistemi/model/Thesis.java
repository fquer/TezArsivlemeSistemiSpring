package com.fquer.TezArsivlemeSistemi.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="thesis")
public class Thesis {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;
    private String thesisTitle;
    private String thesisTopic;
    private String thesisLanguage;
    private String thesisGroup;
    private String thesisUniversity;
    private String thesisInstitute;
    private String thesisMainField;
    private String thesisChildrenField;
    @OneToOne
    private ThesisType thesisType;
    @OneToOne
    @JoinColumn(name = "thesisFileId", nullable = false)
    private File thesisFile;
}
