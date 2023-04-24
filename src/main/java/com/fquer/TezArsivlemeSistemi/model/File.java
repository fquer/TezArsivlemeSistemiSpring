package com.fquer.TezArsivlemeSistemi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


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
    private LocalDateTime uploadDate;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
