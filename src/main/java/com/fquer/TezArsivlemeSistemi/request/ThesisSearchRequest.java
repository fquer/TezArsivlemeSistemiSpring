package com.fquer.TezArsivlemeSistemi.request;

import lombok.Data;

@Data
public class ThesisSearchRequest {
    private String thesisId;
    private String thesisTitle;
    private String thesisTopic;
    private String thesisLanguage;
    private String thesisGroup;
    private String thesisUniversity;
    private String thesisInstitute;
    private String thesisMainField;
    private String thesisChildrenField;
    private String thesisType;
}
