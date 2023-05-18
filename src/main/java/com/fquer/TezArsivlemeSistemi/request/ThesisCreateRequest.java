package com.fquer.TezArsivlemeSistemi.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ThesisCreateRequest {
    private String thesisTitle;
    private String thesisLanguage;
    private String thesisGroup;
    private String thesisUniversity;
    private String thesisInstitute;
    private String thesisMainField;
    private String thesisChildrenField;
    private String thesisType;
    private String thesisWrittenYear;
    private String thesisAdvisor;
    private MultipartFile thesisFile;
    private String userId;
}
