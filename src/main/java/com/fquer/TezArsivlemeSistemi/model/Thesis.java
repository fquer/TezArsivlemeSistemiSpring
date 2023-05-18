package com.fquer.TezArsivlemeSistemi.model;

import com.fquer.TezArsivlemeSistemi.model.thesisDetail.*;
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
    @OneToOne
    private ThesisLanguage thesisLanguage;
    @OneToOne
    private ThesisGroup thesisGroup;
    @OneToOne
    private ThesisUniversity thesisUniversity;
    @OneToOne
    private ThesisInstitute thesisInstitute;
    @OneToOne
    private ThesisMainField thesisMainField;
    @OneToOne
    private ThesisChildrenField thesisChildrenField;
    @OneToOne
    private ThesisType thesisType;
    private String thesisWrittenYear;
    private String thesisAdvisor;
    @OneToOne
    @JoinColumn(name = "thesisFileId", nullable = false)
    private File thesisFile;
}
