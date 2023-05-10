package com.fquer.TezArsivlemeSistemi.controller;

import com.fquer.TezArsivlemeSistemi.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("userId") String userId) throws IOException {
        return new ResponseEntity<>(fileService.uploadFile(file, userId), HttpStatus.OK);
    }

    @GetMapping("/download/file/{id}")
    public ResponseEntity<ByteArrayResource> getFile(@PathVariable String id) throws IOException {
        return fileService.getFile(id);
    }

    @GetMapping("/download/preview/{id}")
    public ResponseEntity<StreamingResponseBody> getPreviewImage(@PathVariable String id) throws IOException {
        return fileService.getPreviewImage(id);
    }

}
