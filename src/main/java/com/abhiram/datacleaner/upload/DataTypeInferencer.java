package com.abhiram.datacleaner.upload;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Component
public class DataTypeInferencer {

    public DataType inferType(String value) {

        if (value == null || value.isBlank()) {
            return DataType.STRING;
        }

        value = value.trim();

        // Integer
        try {
            Integer.parseInt(value);
            return DataType.INTEGER;
        } catch (NumberFormatException ignored) {
        }

        // Double
        try {
            Double.parseDouble(value);
            return DataType.DOUBLE;
        } catch (NumberFormatException ignored) {
        }

        // Boolean
        if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {

            return DataType.BOOLEAN;
        }

        // Date (yyyy-MM-dd)
        try {
            LocalDate.parse(value);
            return DataType.DATE;
        } catch (DateTimeParseException ignored) {
        }

        return DataType.STRING;
    }
    public Object convertValue(String value) {

        if (value == null || value.isBlank()) {
            return value;
        }

        value = value.trim();

        DataType dataType = inferType(value);

        return switch (dataType) {

            case INTEGER -> Integer.parseInt(value);

            case DOUBLE -> Double.parseDouble(value);

            case BOOLEAN -> Boolean.parseBoolean(value);

            case DATE -> LocalDate.parse(value);

            case STRING -> value;
        };
    }
}