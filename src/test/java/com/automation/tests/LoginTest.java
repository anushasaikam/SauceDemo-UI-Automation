package com.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.BeforeMethod;
import com.automation.base.BaseTest;
import com.automation.dataproviders.DataProviders;
import com.automation.pages.LoginPage;

@Listeners(com.automation.listeners.ExtentReportListener.class)
public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void initPage() {
        loginPage = new LoginPage(driver);
    }

    @Test(dataProvider = "loginData", dataProviderClass = DataProviders.class)
    public void loginTest(String username, String password, String expectedTitle) {
        loginPage.enterUsername(username)
                 .enterPassword(password)
                 .clickLogin();
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Page title mismatch");
    }
}

