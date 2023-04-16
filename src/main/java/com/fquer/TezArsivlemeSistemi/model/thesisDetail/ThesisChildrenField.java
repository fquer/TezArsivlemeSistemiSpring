package com.fquer.TezArsivlemeSistemi.model.thesisDetail;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="thesisChildrenField")
public class ThesisChildrenField {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;
    private String thesisChildrenFieldName;
}
