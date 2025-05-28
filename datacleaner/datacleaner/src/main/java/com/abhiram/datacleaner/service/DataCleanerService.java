package com.abhiram.datacleaner.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.abhiram.datacleaner.model.CleanedData;
import com.abhiram.datacleaner.repository.CleanedDataRepo;

import java.nio.file.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataCleanerService {

    @Autowired
    private CleanedDataRepo cleanedDataRepo;

    public void cleanAndSaveData() {
        try {
            // Path to your local file
            Path filePath = Paths.get("src/main/resources/raw-data.txt");

            // Read lines from the file
            List<String> lines = Files.readAllLines(filePath);

            // Clean data (remove empty/blank lines and trim)
            List<String> cleanedLines = lines.stream()
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .collect(Collectors.toList());

            // Save cleaned lines to database
            for (String line : cleanedLines) {
                CleanedData data = new CleanedData();
                data.setValue(line);
                cleanedDataRepo.save(data);
            }

            System.out.println("✅ Data cleaned and saved successfully!");

        } catch (IOException e) {
            System.err.println("❌ Failed to read file: " + e.getMessage());
        }
    }
}