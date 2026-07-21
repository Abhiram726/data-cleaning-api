package com.abhiram.datacleaner.upload;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ParserFactory {

    private final CsvDatasetParser csvDatasetParser;

    public ParserFactory(CsvDatasetParser csvDatasetParser) {
        this.csvDatasetParser = csvDatasetParser;
    }

    public DatasetParser getParser(MultipartFile file) {

        String fileName = file.getOriginalFilename();

        if (fileName == null) {
            throw new IllegalArgumentException("File name cannot be null.");
        }

        if (fileName.toLowerCase().endsWith(".csv")) {
            return csvDatasetParser;
        }

        throw new IllegalArgumentException("Unsupported file type.");
    }
}