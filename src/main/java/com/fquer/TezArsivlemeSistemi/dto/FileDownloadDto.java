package com.fquer.TezArsivlemeSistemi.dto;

import lombok.Data;

@Data
public class FileDownloadDto {
    private String filename;
    private String fileType;
    private String fileSize;
    private byte[] file;
}