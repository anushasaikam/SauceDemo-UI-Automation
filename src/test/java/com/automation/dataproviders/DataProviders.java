package com.automation.dataproviders;

import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
            {"standard_user", "secret_sauce", "Swag Labs"},
            {"locked_out_user", "secret_sauce", "Swag Labs"}
        };
    }
}
