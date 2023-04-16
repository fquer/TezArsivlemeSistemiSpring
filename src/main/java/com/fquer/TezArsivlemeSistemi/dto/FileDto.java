package com.fquer.TezArsivlemeSistemi.dto;

import com.fquer.TezArsivlemeSistemi.model.File;
import lombok.Data;

@Data
public class FileDto {
    private String id;
    private String fileId;
    private String fileName;
    private UserDto user;

    public FileDto(File file) {
        this.id = file.getId();
        this.fileId = file.getFileId();
        this.fileName = file.getFileName();
        this.user = new UserDto(file.getUser());
    }
}
