package com.fquer.TezArsivlemeSistemi.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name="file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;
    private String fileId;
    private String previewImageId;
    private String fileName;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
