package com.studenthub.resourcehub.controller;

import java.io.IOException;


import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.studenthub.resourcehub.dto.ResourceResponse;
import com.studenthub.resourcehub.entity.Resource;
import com.studenthub.resourcehub.service.ResourceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @PostMapping
    public ResponseEntity<Resource> uploadResource(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam("typeId") Long typeId,
            @RequestParam(value = "subject", required = false) String subject) {
        try {
            Resource saved = resourceService.uploadResource(file, title, description, categoryId, typeId, subject);
            return ResponseEntity.ok(saved);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/my")
    public ResponseEntity<Page<ResourceResponse>> getMyResources(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Page<ResourceResponse> paginated = resourceService.getResourcesByCurrentUser(page, size);
        return ResponseEntity.ok(paginated);
    }
}
