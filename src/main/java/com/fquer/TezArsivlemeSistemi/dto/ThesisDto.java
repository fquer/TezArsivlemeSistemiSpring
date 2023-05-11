package com.fquer.TezArsivlemeSistemi.dto;

import com.fquer.TezArsivlemeSistemi.model.File;
import com.fquer.TezArsivlemeSistemi.model.Thesis;
import com.fquer.TezArsivlemeSistemi.model.thesisDetail.*;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ThesisDto {
    private String id;
    private String thesisTitle;
    private ThesisLanguage thesisLanguage;
    private ThesisGroup thesisGroup;
    private ThesisUniversity thesisUniversity;
    private ThesisInstitute thesisInstitute;
    private ThesisMainField thesisMainField;
    private ThesisChildrenField thesisChildrenField;
    private ThesisType thesisType;
    private FileDto thesisFile;
    private LocalDateTime thesisUploadDate;
    private String thesisWrittenYear;
    private UserDto user;

    public ThesisDto(Thesis thesis) {
        this.id = thesis.getId();
        this.thesisTitle = thesis.getThesisTitle();
        this.thesisLanguage = thesis.getThesisLanguage();
        this.thesisGroup = thesis.getThesisGroup();
        this.thesisUniversity = thesis.getThesisUniversity();
        this.thesisInstitute = thesis.getThesisInstitute();
        this.thesisMainField= thesis.getThesisMainField();
        this.thesisChildrenField = thesis.getThesisChildrenField();
        this.thesisType = thesis.getThesisType();
        this.thesisFile = new FileDto(thesis.getThesisFile());
        this.thesisUploadDate = thesis.getThesisFile().getUploadDate();
        this.thesisWrittenYear = thesis.getThesisWrittenYear();
        this.user = new UserDto(thesis.getThesisFile().getUser());
    }
}
