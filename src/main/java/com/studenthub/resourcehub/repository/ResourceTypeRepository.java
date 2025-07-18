package com.studenthub.resourcehub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studenthub.resourcehub.entity.ResourceType;

public interface ResourceTypeRepository extends JpaRepository<ResourceType, Long>{
    Optional<ResourceType> findByTypeName(String typeName);
    boolean existsByTypeName(String typeName);
}
