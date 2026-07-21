package com.abhiram.datacleaner.upload;

import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.NamedCsvRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class CsvDatasetParser implements DatasetParser {

    @Override
    public Dataset parse(MultipartFile file) throws Exception {

        List<String> headers = null;
        List<Map<String, Object>> rows = new ArrayList<>();

        try (CsvReader<NamedCsvRecord> csvReader =
                     CsvReader.builder()
                             .ofNamedCsvRecord(new InputStreamReader(file.getInputStream()))) {

            for (NamedCsvRecord record : csvReader) {

                if (headers == null) {
                    headers = new ArrayList<>(record.getHeader());
                }

                Map<String, Object> row =
                        new LinkedHashMap<>(record.getFieldsAsMap());

                rows.add(row);
            }
        }

        // ===== DEBUG =====
        System.out.println();
        System.out.println("Headers:\n");
        System.out.println(headers);

        System.out.println("\nRows:\n");
        for (Map<String, Object> row : rows) {
            System.out.println(row+"\n");
        }
        System.out.println();
        // =================

        return new Dataset(
                file.getOriginalFilename(),
                headers,
                rows
        );
    }
}