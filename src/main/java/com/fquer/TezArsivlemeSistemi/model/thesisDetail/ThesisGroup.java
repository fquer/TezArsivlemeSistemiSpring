package com.fquer.TezArsivlemeSistemi.model.thesisDetail;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="thesisGroup")
public class ThesisGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;
    private String thesisGroupName;
}
