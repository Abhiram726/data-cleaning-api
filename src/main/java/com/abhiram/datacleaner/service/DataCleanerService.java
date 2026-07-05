package com.abhiram.datacleaner.service;

import com.abhiram.datacleaner.RawData;
import com.abhiram.datacleaner.model.CleanedData;
import com.abhiram.datacleaner.repository.CleanedDataRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.regex.Pattern;

@Service
public class DataCleanerService {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.\\w+$");

    private final CleanedDataRepo cleanedDataRepo;

    public DataCleanerService(CleanedDataRepo cleanedDataRepo) {
        this.cleanedDataRepo = cleanedDataRepo;
    }

    @Transactional
    public Map<String, Object> cleanData(RawData rawData) {
        Map<String, Object> cleaned = new LinkedHashMap<>();
        cleaned.put("name", cleanText(rawData.name()));
        cleaned.put("email", cleanEmail(rawData.email()));
        cleaned.put("city", cleanText(rawData.city()));

        cleanedDataRepo.save(new CleanedData(
                cleaned.get("name").toString(),
                cleaned.get("email").toString(),
                cleaned.get("city").toString()));
        return cleaned;
    }

    private String cleanText(String value) {
        if (value == null) {
            return "N/A";
        }

        String cleaned = removeSpecialChars(value.trim()).replaceAll("\\s+", " ").trim();
        return cleaned.isEmpty() ? "N/A" : toTitleCase(cleaned);
    }

    private String cleanEmail(String value) {
        if (value == null) {
            return "Invalid Email";
        }

        String email = value.trim().toLowerCase();
        return EMAIL_PATTERN.matcher(email).matches() ? email : "Invalid Email";
    }

    private String toTitleCase(String input) {
        StringJoiner titleCase = new StringJoiner(" ");

        for (String word : input.toLowerCase().split("\\s+")) {
            titleCase.add(Character.toUpperCase(word.charAt(0)) + word.substring(1));
        }

        return titleCase.toString();
    }

    private String removeSpecialChars(String input) {
        return input.replaceAll("[^a-zA-Z0-9\\s]", "");
    }
}
