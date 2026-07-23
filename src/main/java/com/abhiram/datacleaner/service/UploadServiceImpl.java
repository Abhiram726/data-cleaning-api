package com.abhiram.datacleaner.service;

import com.abhiram.datacleaner.upload.Dataset;
import com.abhiram.datacleaner.upload.DatasetParser;
import com.abhiram.datacleaner.upload.ParserFactory;
import com.abhiram.datacleaner.upload.UploadResponse;
import com.abhiram.datacleaner.upload.DataTypeInferencer;

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

//        DataTypeInferencer dataTypeInferencer = new DataTypeInferencer();
//        System.out.println(dataTypeInferencer.inferType("25"));
//        System.out.println(dataTypeInferencer.inferType("25.75"));
//        System.out.println(dataTypeInferencer.inferType("true"));
//        System.out.println(dataTypeInferencer.inferType("2026-07-21"));
//        System.out.println(dataTypeInferencer.inferType("John"));
//
//        Object value1 = dataTypeInferencer.convertValue("25");
//        Object value2 = dataTypeInferencer.convertValue("25.75");
//        Object value3 = dataTypeInferencer.convertValue("true");
//        Object value4 = dataTypeInferencer.convertValue("2026-07-21");
//        Object value5 = dataTypeInferencer.convertValue("John");
//
//        System.out.println(value1.getClass().getSimpleName());
//        System.out.println(value2.getClass().getSimpleName());
//        System.out.println(value3.getClass().getSimpleName());
//        System.out.println(value4.getClass().getSimpleName());
//        System.out.println(value5.getClass().getSimpleName());

        return new UploadResponse(
                datasetId,
                dataset.getFileName(),
                dataset.getRows().size(),
                dataset.getColumns().size(),
                "UPLOADED"
        );
    }
}