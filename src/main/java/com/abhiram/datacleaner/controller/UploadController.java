package com.abhiram.datacleaner.controller;

import com.abhiram.datacleaner.service.UploadService;
import com.abhiram.datacleaner.upload.UploadResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class UploadController {

    private final UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping("/datasets")
    public ResponseEntity<UploadResponse> upload(
            @RequestParam("file") MultipartFile file
    ) throws Exception {
        System.out.println("===== Upload endpoint hit =====");
        
        UploadResponse response = uploadService.upload(file);

        return ResponseEntity.ok(response);
    }
}