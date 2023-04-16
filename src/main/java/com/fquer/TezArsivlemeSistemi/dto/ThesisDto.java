package com.fquer.TezArsivlemeSistemi.dto;

import com.fquer.TezArsivlemeSistemi.model.File;
import com.fquer.TezArsivlemeSistemi.model.Thesis;
import com.fquer.TezArsivlemeSistemi.model.thesisDetail.*;

import lombok.Data;

@Data
public class ThesisDto {
    private String id;
    private String thesisTitle;
    private String thesisTopic;
    private ThesisLanguage thesisLanguage;
    private ThesisGroup thesisGroup;
    private ThesisUniversity thesisUniversity;
    private ThesisInstitute thesisInstitute;
    private ThesisMainField thesisMainField;
    private ThesisChildrenField thesisChildrenField;
    private ThesisType thesisType;
    private FileDto thesisFile;

    public ThesisDto(Thesis thesis) {
        this.id = thesis.getId();
        this.thesisTitle = thesis.getThesisTitle();
        this.thesisTopic = thesis.getThesisTopic();
        this.thesisLanguage = thesis.getThesisLanguage();
        this.thesisGroup = thesis.getThesisGroup();
        this.thesisUniversity = thesis.getThesisUniversity();
        this.thesisInstitute = thesis.getThesisInstitute();
        this.thesisMainField= thesis.getThesisMainField();
        this.thesisChildrenField = thesis.getThesisChildrenField();
        this.thesisType = thesis.getThesisType();
        this.thesisFile = new FileDto(thesis.getThesisFile());
    }
}
