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
    @OneToOne
    private ThesisType thesisType;
    @OneToOne
    @JoinColumn(name = "thesisFileId", nullable = false)
    private File thesisFile;
}
