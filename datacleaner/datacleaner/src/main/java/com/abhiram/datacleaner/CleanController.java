package com.abhiram.datacleaner;

import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class CleanController {

    private final List<String> expectedKeys = List.of("name", "email", "city");

    @PostMapping("/clean")
    public Map<String, Object> cleanData(@RequestBody Map<String, Object> rawData) throws IOException {
        Map<String, Object> cleaned = new LinkedHashMap<>();

        for (String key : expectedKeys) {
            Object value = rawData.getOrDefault(key, "N/A");

            if (value instanceof String str) {
                str = str.trim();

                if (key.equalsIgnoreCase("email")) {
                    str = str.isEmpty() || !isValidEmail(str) ? "Invalid Email" : str.toLowerCase();
                } else {
                    str = str.isEmpty() ? "N/A" : toTitleCase(removeSpecialChars(str));
                }

                cleaned.put(key, str);
            } else {
                cleaned.put(key, value != null ? value : "N/A");
            }
        }

        logCleanedData(cleaned);
        return cleaned;
    }

    private String toTitleCase(String input) {
        if (input == null || input.isEmpty()) return input;
        return Arrays.stream(input.toLowerCase().split(" "))
                .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1))
                .collect(Collectors.joining(" "));
    }

    private String removeSpecialChars(String input) {
        return input.replaceAll("[^a-zA-Z0-9\\s]", "");
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$");
    }

    private void logCleanedData(Map<String, Object> cleaned) throws IOException {
        String log = "Cleaned @ " + new Date() + ":\n" + cleaned.toString() + "\n\n";
        Files.write(Paths.get("cleaned_data_log.txt"),
                log.getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }
}
