package com.studenthub.resourcehub.repository;

import java.util.Optional;
import com.studenthub.resourcehub.entity.ResourceCategory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ResourceCategoryRepository extends JpaRepository<ResourceCategory, Long> {
    Optional<ResourceCategory> findByCategoryName(String categoryName);
    boolean existsByCategoryName(String categoryName);
}
