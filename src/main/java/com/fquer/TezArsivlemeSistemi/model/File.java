package com.fquer.TezArsivlemeSistemi.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
public class File {
    private String fileId;
    private String fileName;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
