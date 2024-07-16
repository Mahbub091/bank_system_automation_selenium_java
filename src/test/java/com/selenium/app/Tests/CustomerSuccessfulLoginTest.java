package com.selenium.app.Tests;

import com.selenium.app.POM.LoginPage;
import com.selenium.app.baseConfiguration.BaseTest;
import com.selenium.app.baseConfiguration.ConfigReader;
import com.selenium.app.utility.CustomUtils;
import com.selenium.app.utility.TestUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CustomerSuccessfulLoginTest extends BaseTest {

    WebDriver driver;
    LoginPage loginPage;
    TestUtils test;
    CustomUtils customUtils;
    ConfigReader configReader;

    @BeforeClass
    public void setupTest() {
        this.driver = driverManager.getDriver();
        loginPage = new LoginPage(driver);
        test = new TestUtils(driver);
        customUtils= new CustomUtils(driver);
        configReader = new ConfigReader();
    }
    @Test(testName = "Test_01_01", description = "Validating Successful Login & Logout for Harry Potter Account")
    public void Test_01_01() {
        loginPage.navigatingToWebPage();
        loginPage.userSuccessfulLogin();
        loginPage.userSuccessfulLogout();
    }

    @Test(testName = "Test_01_02", description = "Harry Potter Makes Successful Deposit")
    public void Test_01_02() {
        loginPage.userMakesDeposit();
    }

    @Test(testName = "Test_01_03", description = "Validating Harry Potter Deposit Is Valid")
    public void Test_01_03() {
        loginPage.userDepositValidation();
    }
}
