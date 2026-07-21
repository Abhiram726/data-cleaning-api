package com.abhiram.datacleaner.service;

import com.abhiram.datacleaner.upload.UploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    UploadResponse upload(MultipartFile file) throws Exception;

}