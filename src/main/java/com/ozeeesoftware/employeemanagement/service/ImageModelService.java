package com.ozeeesoftware.employeemanagement.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ImageModelService {
    ResponseEntity storeImage(MultipartFile image);

    Resource loadFileAsResource(String fileName);

    ResponseEntity<Object> addProfileImage(MultipartFile image, long userId);
}
