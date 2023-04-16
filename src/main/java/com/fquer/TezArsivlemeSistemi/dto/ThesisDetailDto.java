package com.fquer.TezArsivlemeSistemi.dto;

import com.fquer.TezArsivlemeSistemi.model.thesisDetail.*;
import lombok.Data;

import java.util.List;

@Data
public class ThesisDetailDto {
    private List<ThesisType> thesisTypes;
    private List<ThesisChildrenField> thesisChildrenFields;
    private List<ThesisGroup> thesisGroups;
    private List<ThesisInstitute> thesisInstitutes;
    private List<ThesisLanguage> thesisLanguages;
    private List<ThesisMainField> thesisMainFields;
    private List<ThesisUniversity> thesisUniversities;

    public ThesisDetailDto(
            List<ThesisType> thesisTypes,
            List<ThesisChildrenField> thesisChildrenFields,
            List<ThesisGroup> thesisGroups,
            List<ThesisInstitute> thesisInstitutes,
            List<ThesisLanguage> thesisLanguages,
            List<ThesisMainField> thesisMainFields,
            List<ThesisUniversity> thesisUniversities ) {
        this.thesisTypes = thesisTypes;
        this.thesisChildrenFields = thesisChildrenFields;
        this.thesisGroups = thesisGroups;
        this.thesisInstitutes = thesisInstitutes;
        this.thesisLanguages = thesisLanguages;
        this.thesisMainFields = thesisMainFields;
        this.thesisUniversities = thesisUniversities;
    }
}
