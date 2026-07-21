package com.abhiram.datacleaner.upload;

public class UploadResponse {

    private String datasetId;
    private String fileName;
    private int rowCount;
    private int columnCount;
    private String status;

    public UploadResponse(String datasetId,
                          String fileName,
                          int rowCount,
                          int columnCount,
                          String status) {

        this.datasetId = datasetId;
        this.fileName = fileName;
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.status = status;
    }

    public String getDatasetId() {
        return datasetId;
    }

    public String getFileName() {
        return fileName;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public String getStatus() {
        return status;
    }
}