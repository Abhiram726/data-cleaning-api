package com.abhiram.datacleaner.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.abhiram.datacleaner.service.DataCleanerService;

@RestController
@RequestMapping("/clean")
public class DataCleanerController {

    @Autowired
    private DataCleanerService dataCleanerService;

    @PostMapping
    public String cleanData() {
        // Call your cleaning logic from the service
        dataCleanerService.cleanAndSaveData();
        return "Data cleaned and stored successfully!";
    }
}
