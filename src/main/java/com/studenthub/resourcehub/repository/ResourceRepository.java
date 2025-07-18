package com.studenthub.resourcehub.repository;




import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.studenthub.resourcehub.entity.Resource;
import com.studenthub.resourcehub.entity.User;

public interface ResourceRepository extends JpaRepository<Resource, Long>{
    Page<Resource> findByUser(User user, org.springframework.data.domain.Pageable pageable);
}
