package org.selenium.pom.utils;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.selenium.pom.constants.FrameworkConstants;
import org.apache.poi.ss.usermodel.CellType;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public final class ExcelUtils {

    private ExcelUtils() {
    }

    public static List<Map<String, String>> getTestDetails(String sheetName) {
        List<Map<String, String>> list = new ArrayList<>();

        FileInputStream fileInputStream = null;
        XSSFWorkbook workbook = null;

        try {
            fileInputStream = new FileInputStream(FrameworkConstants.getExcelPath());
            workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheet(sheetName);

            System.out.println("workbook" +workbook);
            System.out.println("sheet name" +sheet.getRow(1).getCell(2).getStringCellValue());

            if (sheet == null) {
                throw new IllegalArgumentException("Sheet with name '" + FrameworkConstants.getRunmangersheet() + "' does not exist.");
            }

            int lastRowNum = sheet.getLastRowNum();

            for (int i = 1; i <= lastRowNum; i++) {
                XSSFRow row = sheet.getRow(i);
                if (row == null || isRowEmpty(row)) {
                    continue;
                }
                Map<String, String> map = new HashMap<>();
                int lastColNum = sheet.getRow(i).getLastCellNum();

                for (int j = 0; j < lastColNum; j++) {
                    String key = sheet.getRow(0).getCell(j).getStringCellValue();
                    String value = row.getCell(j).getCellType() == CellType.BLANK ? "" : row.getCell(j).getStringCellValue();
                    map.put(key, value);
                }
                list.add(map);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading Excel file", e);
        } finally {
            try {

                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace(); // handle or log this exception as needed
            }
        }
        System.out.println("list" +list);
        return list;
    }


    private static boolean isRowEmpty(XSSFRow row) {
        if (row == null) {
            return true;
        }
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            if (row.getCell(c) != null && row.getCell(c).getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }
}