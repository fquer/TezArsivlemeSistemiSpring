package com.fquer.TezArsivlemeSistemi.service;

import com.fquer.TezArsivlemeSistemi.model.File;
import com.fquer.TezArsivlemeSistemi.model.LoadFile;
import com.fquer.TezArsivlemeSistemi.model.User;
import com.fquer.TezArsivlemeSistemi.repository.FileRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;


@Service
public class FileService {

    @Autowired
    private GridFsTemplate template;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private GridFsOperations operations;

    public File addFile(MultipartFile upload, String userId) throws IOException {

        PDDocument document = PDDocument.load(upload.getBytes());
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(0, 25);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", baos);
        byte[] imageBytes = baos.toByteArray();

        document.close();

        InputStream inputStream = new ByteArrayInputStream(imageBytes);
        Object previewImageID = template.store(inputStream,upload.getOriginalFilename() + "_preview.jpg");

        DBObject metadata = new BasicDBObject();
        metadata.put("fileSize", upload.getSize());

        Object fileID = template.store(upload.getInputStream(), upload.getOriginalFilename(), upload.getContentType(), metadata);
        User user = userService.getUserById(userId);
        File file = new File();
        file.setFileName(upload.getOriginalFilename());
        file.setFileId(fileID.toString());
        file.setPreviewImageId(previewImageID.toString());
        file.setUser(user);
        return fileRepository.save(file);
    }


    public ResponseEntity<ByteArrayResource> getFile(String id) throws IOException {

        GridFSFile gridFSFile = template.findOne( new Query(Criteria.where("_id").is(id)) );

        LoadFile loadFile = new LoadFile();

        if (gridFSFile != null && gridFSFile.getMetadata() != null) {
            loadFile.setFilename( gridFSFile.getFilename() );

            loadFile.setFileType( gridFSFile.getMetadata().get("_contentType").toString() );

            loadFile.setFileSize( gridFSFile.getMetadata().get("fileSize").toString() );

            loadFile.setFile( IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()) );
        }


        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(loadFile.getFileType() ))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + loadFile.getFilename() + "\"")
                .body(new ByteArrayResource(loadFile.getFile()));
    }

    public ResponseEntity<ByteArrayResource> getPreviewImage(String id) throws IOException {
        GridFSFile gridFSFile = template.findOne( new Query(Criteria.where("_id").is(id)) );

        byte[] imageBytes = operations.getResource(gridFSFile).getContentAsByteArray();

        ByteArrayResource resource = new ByteArrayResource(imageBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(imageBytes.length)
                .body(resource);
    }

}
