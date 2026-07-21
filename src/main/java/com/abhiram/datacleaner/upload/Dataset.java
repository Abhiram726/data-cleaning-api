package com.abhiram.datacleaner.upload;

import java.util.List;
import java.util.Map;

public class Dataset {

    private String fileName;
    private List<String> columns;
    private List<Map<String, Object>> rows;

    public Dataset(String fileName,
                   List<String> columns,
                   List<Map<String, Object>> rows) {

        this.fileName = fileName;
        this.columns = columns;
        this.rows = rows;
    }

    public String getFileName() {
        return fileName;
    }

    public List<String> getColumns() {
        return columns;
    }

    public List<Map<String, Object>> getRows() {
        return rows;
    }
}