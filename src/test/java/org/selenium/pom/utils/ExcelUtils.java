package org.selenium.pom.utils;

import org.selenium.pom.constants.FrameworkConstants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public final class ExcelUtils {

    private ExcelUtils(){}


    public static List<Map<String,String>>  getTestDetails() throws FileNotFoundException {

        FileInputStream fileInputStream = new FileInputStream(FrameworkConstants.getExcelPath());










    }
}
