package com.selenium.app.POM;

import com.selenium.app.baseConfiguration.ConfigReader;
import com.selenium.app.utility.TestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    int element_wait_max = 60;
    int element_wait_min = 30;
    int pause_normal = 1;
    int pause_long = 2;
    int pause_extended = 3;
    WebDriver driver;
    TestUtils test;
    ConfigReader configReader;
    Logger log = LogManager.getLogger("RegisterPage");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        test = new TestUtils(driver);
        configReader = new ConfigReader();
    }

    /**
     * Our Elements Will Be Stored Here.
     */

    @FindBy(css = "[ng-click='customer\\(\\)']")
    WebElement customerPageButton;

    @FindBy(css = ".ng-scope > .ng-scope form[role='form']  label")
    WebElement customerDropDownHeader;

    @FindBy(css = "select#userSelect")
    WebElement customerDropDownSelect;



    /**
     * We'll define the methods here.
     */

    public void navigatingToWebPage(){
        test.visitTo(configReader.getAppURL());
    }

    public void validationHeaderSections(){
        test.validateTitle("XYZ Bank");
        test.waitForElementVisibility(customerPageButton, element_wait_max);
        test.validateElementsToHaveExpectedText(customerPageButton, "Customer Login");
        test.clickingElement(customerPageButton);
        test.waitForElementVisibility(customerDropDownHeader, element_wait_max);
        test.validateElementsToHaveExpectedText(customerDropDownHeader, "Your Name :");
        test.selectDropDownByVisibleText(customerDropDownSelect, "Harry Potter");
        test.assertUrl(configReader.customerPageUrl());




        test.wait(3);
    }
}
