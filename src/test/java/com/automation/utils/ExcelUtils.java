package com.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
    public static Object[][] getSheetData(String filePath, String sheetName) {
        try (Workbook wb = new XSSFWorkbook(new FileInputStream(filePath))) {
            Sheet sheet = wb.getSheet(sheetName);
            Iterator<Row> rows = sheet.iterator();
            Row headerRow = rows.next();
            List<String> headers = new ArrayList<>();
            headerRow.forEach(cell -> headers.add(cell.getStringCellValue()));

            List<Map<String, String>> data = new ArrayList<>();
            while (rows.hasNext()) {
                Row row = rows.next();
                Map<String,String> rowMap = new LinkedHashMap<>();
                for (int i = 0; i < headers.size(); i++) {
                    Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    rowMap.put(headers.get(i), cell.toString());
                }
                data.add(rowMap);
            }

            Object[][] result = new Object[data.size()][headers.size()];
            for (int i = 0; i < data.size(); i++) {
                Map<String,String> row = data.get(i);
                result[i] = row.values().toArray();
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read Excel file", e);
        }
    }
}