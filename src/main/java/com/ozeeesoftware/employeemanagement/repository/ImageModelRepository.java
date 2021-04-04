package com.ozeeesoftware.employeemanagement.repository;

import com.ozeeesoftware.employeemanagement.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageModelRepository extends JpaRepository<ImageModel,Long> {
}
