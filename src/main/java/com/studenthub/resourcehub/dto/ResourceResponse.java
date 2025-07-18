package com.studenthub.resourcehub.dto;

import java.time.LocalDateTime;

import com.studenthub.resourcehub.entity.ResourceCategory;
import com.studenthub.resourcehub.entity.ResourceType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResourceResponse {

    private Long id;
    private String title;
    private String description;
    private String subject;
    private String fileUrl;
    private ResourceCategory category;
    private ResourceType type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
