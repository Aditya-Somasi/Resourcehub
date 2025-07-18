package com.studenthub.resourcehub.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private Path uploadPath;

    @PostConstruct
    public void init() throws IOException {
        uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(uploadPath); 
    }

    public String storeFile(MultipartFile file) throws IOException {
        String originalFilename = Path.of(file.getOriginalFilename()).getFileName().toString(); // avoid path injection
        String safeFilename = System.currentTimeMillis() + "_" + originalFilename;
        Path targetLocation = uploadPath.resolve(safeFilename);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        return safeFilename;
    }

    public Path loadFile(String filename) {
        return uploadPath.resolve(filename).normalize();
    }
}
