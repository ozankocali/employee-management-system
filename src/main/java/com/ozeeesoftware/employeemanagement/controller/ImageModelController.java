package com.ozeeesoftware.employeemanagement.controller;

import com.ozeeesoftware.employeemanagement.service.ImageModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class ImageModelController {

    @Autowired
    private ImageModelServiceImpl imageModelService;

    @PostMapping("/image/upload")
    public ResponseEntity<Object> storeImage(@RequestParam("file") MultipartFile image){
        return imageModelService.storeImage(image);
    }

    @PostMapping("/image/addProfileImage/{userId}")
    public ResponseEntity<Object> addProfileImage(@RequestParam("file") MultipartFile image,@PathVariable long userId){
        return imageModelService.addProfileImage(image,userId);
    }

    @GetMapping("/image/downloadImage/{fileName:.+}")
    public ResponseEntity<Resource> loadFileAsResource(@PathVariable String fileName, HttpServletRequest httpServletRequest){

        Resource resource=imageModelService.loadFileAsResource(fileName);

        String contentType=null;

        try {
            contentType=httpServletRequest.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }catch (Exception exception){
            return ResponseEntity.notFound().build();
        }

        if (contentType==null){
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+resource.getFilename()+"\"")
                .body(resource);

    }


}
