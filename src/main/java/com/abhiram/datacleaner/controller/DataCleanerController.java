package com.abhiram.datacleaner.controller;

import com.abhiram.datacleaner.RawData;
import com.abhiram.datacleaner.service.DataCleanerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/clean")
public class DataCleanerController {

    private final DataCleanerService dataCleanerService;

    public DataCleanerController(DataCleanerService dataCleanerService) {
        this.dataCleanerService = dataCleanerService;
    }

    @PostMapping
    public Map<String, Object> cleanData(@RequestBody RawData rawData) {
        return dataCleanerService.cleanData(rawData);
    }
}
