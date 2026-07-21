package com.abhiram.datacleaner.upload;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class CsvDatasetParser implements DatasetParser {

    @Override
    public Dataset parse(MultipartFile file) throws Exception {

        Reader reader = new InputStreamReader(file.getInputStream());

        CSVParser csvParser = CSVFormat.DEFAULT
                .builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .build()
                .parse(reader);

        List<String> columns = new ArrayList<>(csvParser.getHeaderMap().keySet());

        List<Map<String, String>> rows = new ArrayList<>();

        for (CSVRecord record : csvParser) {

            Map<String, String> row = new LinkedHashMap<>();

            for (String column : columns) {
                row.put(column, record.get(column));
            }

            rows.add(row);
        }

        return new Dataset(
                file.getOriginalFilename(),
                columns,
                rows
        );
    }
}