package com.studenthub.resourcehub.service;

import java.io.IOException;

import java.util.Optional;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.studenthub.resourcehub.dto.ResourceResponse;
import com.studenthub.resourcehub.entity.Resource;
import com.studenthub.resourcehub.entity.ResourceCategory;
import com.studenthub.resourcehub.entity.ResourceType;
import com.studenthub.resourcehub.entity.User;
import com.studenthub.resourcehub.exception.ResourceNotFoundException;
import com.studenthub.resourcehub.repository.ResourceCategoryRepository;
import com.studenthub.resourcehub.repository.ResourceRepository;
import com.studenthub.resourcehub.repository.ResourceTypeRepository;
import com.studenthub.resourcehub.repository.UserRepository;



import org.springframework.data.domain.PageRequest;


import org.springframework.data.domain.Sort;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final UserRepository userRepository;
    private final ResourceCategoryRepository categoryRepository;
    private final ResourceTypeRepository typeRepository;
    private final FileStorageService fileStorageService;
    private final AuthService authService;

    public Resource uploadResource(MultipartFile file, String title, String description, Long categoryId, Long typeId,
            String subject) throws IOException {
        // Get current logged-in user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found" + username));

        // Save file to disk
        String savedFilename = fileStorageService.storeFile(file);

        // Fetch category & type
        Optional<ResourceCategory> categoryOpt = categoryRepository.findById(categoryId);
        Optional<ResourceType> typeOpt = typeRepository.findById(typeId);

        ResourceCategory category = categoryOpt
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + categoryId));

        ResourceType type = typeOpt
                .orElseThrow(() -> new ResourceNotFoundException("Type not found with ID: " + typeId));

        Resource resource = Resource.builder()
                .title(title)
                .description(description)
                .fileUrl("/files/" + savedFilename)
                .subject(subject)
                .category(category)
                .type(type)
                .user(user)
                .build();

        return resourceRepository.save(resource);
    }

    public org.springframework.data.domain.Page<ResourceResponse> getResourcesByCurrentUser(int page, int size) {
        User user = authService.getCurrentUser();
        org.springframework.data.domain.Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        org.springframework.data.domain.Page<Resource> resourcePage = resourceRepository.findByUser(user, pageable);
        return resourcePage.map(this::mapToResponse);
    }

    private ResourceResponse mapToResponse(Resource resource) {
        return ResourceResponse.builder()
                .id(resource.getId())
                .title(resource.getTitle())
                .description(resource.getDescription())
                .subject(resource.getSubject())
                .fileUrl(resource.getFileUrl())
                .category(resource.getCategory())
                .type(resource.getType())
                .createdAt(resource.getCreatedAt())
                .updatedAt(resource.getUpdatedAt())
                .build();
    }

    public void deleteResource(Long resourceId) {
        resourceRepository.deleteById(resourceId);
    }

}
