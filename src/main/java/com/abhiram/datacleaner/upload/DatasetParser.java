package com.abhiram.datacleaner.upload;

import org.springframework.web.multipart.MultipartFile;

public interface DatasetParser {

    Dataset parse(MultipartFile file) throws Exception;

}