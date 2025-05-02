package com.automation.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {

    public static Object[][] getCsvData(String filePath) {
        List<Object[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Skip the header row
                    continue;
                }
                String[] values = line.split(",");
                if (values.length == 3) { // Ensure each row has exactly 3 columns
                    data.add(new Object[] { values[0], values[1], values[2] });
                } else {
                    throw new RuntimeException("Invalid data format in CSV file: " + filePath);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read CSV file: " + filePath, e);
        }
        return data.toArray(new Object[0][]);
    }
}
