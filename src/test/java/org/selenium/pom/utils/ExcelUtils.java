package org.selenium.pom.utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.selenium.pom.constants.FrameworkConstants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ExcelUtils {

    private ExcelUtils(){}

    public static List<Map<String,String>>  getTestDetails() {
        List<Map<String,String>> list  = new ArrayList<>();
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(FrameworkConstants.getExcelPath());
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheet(FrameworkConstants.sheetName);


            Map<String, String>  map = null;


            int lastRowNum = sheet.getLastRowNum();
            int lastColNum = sheet.getRow(0).getLastCellNum();

            for(int i=1;i<lastRowNum;i++){
                map = new HashMap<>();
                for(int j = 0;j<lastColNum;j++){
                        String key = sheet.getRow(0).getCell(j).getStringCellValue();
                        String value = sheet.getRow(0).getCell(j).getStringCellValue()
                        map.put(key,value);
                }
                list.add(map);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}
