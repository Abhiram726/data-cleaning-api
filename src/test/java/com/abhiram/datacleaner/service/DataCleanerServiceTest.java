package com.abhiram.datacleaner.service;

import com.abhiram.datacleaner.RawData;
import com.abhiram.datacleaner.model.CleanedData;
import com.abhiram.datacleaner.repository.CleanedDataRepo;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataCleanerServiceTest {

    @Test
    void cleanDataPersistsCleanedValues() {
        CapturingRepository repository = new CapturingRepository();
        DataCleanerService service = new DataCleanerService(repository.proxy());

        Map<String, Object> cleaned = service.cleanData(new RawData(
                "  alice  ",
                "ALICE@EXAMPLE.COM",
                "  new   york  "));

        assertEquals("Alice", cleaned.get("name"));
        assertEquals("alice@example.com", cleaned.get("email"));
        assertEquals("New York", cleaned.get("city"));

        CleanedData savedData = repository.savedData();
        assertEquals("Alice", savedData.getName());
        assertEquals("alice@example.com", savedData.getEmail());
        assertEquals("New York", savedData.getCity());
    }

    @Test
    void cleanDataHandlesBlankTextAndInvalidEmail() {
        CapturingRepository repository = new CapturingRepository();
        DataCleanerService service = new DataCleanerService(repository.proxy());

        Map<String, Object> cleaned = service.cleanData(new RawData(" @@@ ", "not-an-email", null));

        assertEquals("N/A", cleaned.get("name"));
        assertEquals("Invalid Email", cleaned.get("email"));
        assertEquals("N/A", cleaned.get("city"));
    }

    private static final class CapturingRepository {
        private CleanedData savedData;

        CleanedDataRepo proxy() {
            return (CleanedDataRepo) Proxy.newProxyInstance(
                    CleanedDataRepo.class.getClassLoader(),
                    new Class<?>[] {CleanedDataRepo.class},
                    (proxy, method, args) -> {
                        if ("save".equals(method.getName())) {
                            savedData = (CleanedData) args[0];
                            return savedData;
                        }
                        if ("toString".equals(method.getName())) {
                            return "CapturingRepository";
                        }
                        throw new UnsupportedOperationException(method.getName());
                    });
        }

        CleanedData savedData() {
            return savedData;
        }
    }
}
