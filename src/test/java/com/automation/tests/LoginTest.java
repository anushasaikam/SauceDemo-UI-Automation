package com.automation.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;
import com.automation.pages.LoginPage;

public class LoginTest extends BaseTest {
     @Test
    public void testLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        System.out.println("Entered UserName: standard_user");
        loginPage.enterPassword("secret_sauce");
        System.out.println("Entered Password: secret_sauce");
        loginPage.clickLogin();
        System.out.println("Clicked on Login Button");
   
    
        Assert.assertTrue(driver.findElement(By.className("app_logo")).getText().contains("Swag Labs"));
        System.out.println("Login Successful: Swag Labs logo is displayed");
  


}

@Test
    public void testLoginWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("invalid_user");
        System.out.println("Entered UserName: invalid_user");
        loginPage.enterPassword("invalid_password");
        System.out.println("Entered Password: invalid_password");
        loginPage.clickLogin();
        System.out.println("Clicked on Login Button");

        Assert.assertTrue(driver.findElement(By.xpath("//h3[@data-test='error']")).getText().contains("Epic sadface: Username and password do not match any user in this service"));
        System.out.println("Login Failed: Error message is displayed");
    }
}
