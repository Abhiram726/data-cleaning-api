package com.abhiram.datacleaner.service;

import com.abhiram.datacleaner.upload.Dataset;
import com.abhiram.datacleaner.upload.DatasetParser;
import com.abhiram.datacleaner.upload.ParserFactory;
import com.abhiram.datacleaner.upload.UploadResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService {

    private final ParserFactory parserFactory;

    public UploadServiceImpl(ParserFactory parserFactory) {
        this.parserFactory = parserFactory;
    }

    @Override
    public UploadResponse upload(MultipartFile file) throws Exception {

        if (file.isEmpty()) {
            throw new IllegalArgumentException("Please upload a file.");
        }

        String fileName = file.getOriginalFilename();

        if (fileName == null ||
                !(fileName.endsWith(".csv")
                        || fileName.endsWith(".xlsx")
                        || fileName.endsWith(".json"))) {

            throw new IllegalArgumentException("Only CSV, Excel and JSON files are supported.");
        }

        DatasetParser parser = parserFactory.getParser(file);

        Dataset dataset = parser.parse(file);

        String datasetId = UUID.randomUUID().toString();

        return new UploadResponse(
                datasetId,
                dataset.getFileName(),
                dataset.getRows().size(),
                dataset.getColumns().size(),
                "UPLOADED"
        );
    }
}