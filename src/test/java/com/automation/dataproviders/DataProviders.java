package com.automation.dataproviders;

import org.testng.annotations.DataProvider;
import com.automation.utils.CsvUtils;

public class DataProviders {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        String path = System.getProperty("user.dir") + "/src/test/resources/testdata/LoginData.csv";
        System.out.println("Reading CSV file from: " + path); // Debugging log
        return CsvUtils.getCsvData(path);
    }
}
