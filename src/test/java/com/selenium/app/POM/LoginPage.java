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
    int test_wait_normal = 1;
    int test_wait_long = 2;
    int test_wait_extended = 3;
    String depositAmount = "1500";
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

    @FindBy(css = ".ng-scope > .ng-scope form[role='form'] > .btn.btn-default")
    WebElement loginButton;

    @FindBy(css = ".ng-scope > .ng-scope  .ng-scope > div > div:nth-of-type(1) > strong")
    WebElement welcomeText;

    @FindBy(xpath = "//body[@class='ng-scope']/div[@class='ng-scope']//button[@class='btn logout']")
    WebElement logOutButton;

    @FindBy(css = "select#accountSelect")
    WebElement accNumberDropdown;

    @FindBy(css = ".ng-scope > .ng-scope  .ng-scope > div > div:nth-of-type(2) > strong:nth-of-type(1)")
    WebElement accNumberViewer;

    @FindBy(css = "[ng-click='home\\(\\)']")
    WebElement homeButton;

    @FindBy(css = ".center:nth-child(3) .ng-binding:nth-of-type(2)")
    WebElement balanceViewer;

    //User Deposit Test Case Resources

    @FindBy(css = "[ng-click='transactions\\(\\)']")
    WebElement transactionsButton;

    @FindBy(css = "[ng-click='deposit\\(\\)']")
    WebElement depositButton;

    @FindBy(css = "[ng-click='withdrawl\\(\\)']")
    WebElement withdrawButton;

    @FindBy(xpath = "//body[@class='ng-scope']/div[@class='ng-scope']/div/div[@class='ng-scope']//div[@class='container-fluid mainBox ng-scope']//form[@role='form']//input[@type='number']")
    WebElement depositInputField;

    @FindBy(css = ".ng-scope > .ng-scope .container-fluid.mainBox.ng-scope  form[role='form'] > button")
    WebElement depositConfirmationButton;

    @FindBy(css = "[ng-show='message']")
    WebElement depositSuccessMessage;

    /**
     * We'll define the methods here.
     */

    public void navigatingToWebPage(){
        test.visitTo(configReader.getAppURL());
    }

    public void userSuccessfulLogin(){
        test.validateTitle("XYZ Bank");
        test.waitForElementVisibility(customerPageButton, element_wait_max);
        test.validateElementsToHaveExpectedText(customerPageButton, "Customer Login");
        test.clickingElement(customerPageButton);
        test.waitForElementVisibility(customerDropDownHeader, element_wait_max);
        test.validateElementsToHaveExpectedText(customerDropDownHeader, "Your Name :");
        test.selectDropDownByVisibleText(customerDropDownSelect, "Harry Potter");
        test.assertUrl(configReader.customerPageUrl());
        test.waitForElementVisibility(loginButton, element_wait_max);
        test.validateElementsToHaveExpectedText(loginButton, "Login");
        test.waitForElementIsClickable(loginButton, element_wait_min);
        test.clickingElement(loginButton);
        test.waitForElementVisibility(welcomeText, element_wait_min);
        test.validateElementsToHaveExpectedText(welcomeText, "Welcome Harry Potter !!");
        test.assertUrl(configReader.customerLoginPageUrl());
        test.selectDropDownByVisibleText(accNumberDropdown, "1004");
        test.validateElementsToHaveExpectedText(accNumberViewer, "1004");
    }

    public void userSuccessfulLogout(){
        test.waitForElementIsClickable(logOutButton, element_wait_min);
        test.validateElementsToHaveExpectedText(logOutButton, "Logout");
        test.waitForElementIsClickable(logOutButton, element_wait_min);
        test.clickingElement(logOutButton);
        test.waitForElementVisibility(customerDropDownHeader, element_wait_max);
        test.assertUrl(configReader.customerPageUrl());
    }

    public void userMakesDeposit () {
        test.waitForElementVisibility(customerDropDownHeader, element_wait_max);
        test.validateElementsToHaveExpectedText(customerDropDownHeader, "Your Name :");
        test.selectDropDownByVisibleText(customerDropDownSelect, "Harry Potter");
        test.assertUrl(configReader.customerPageUrl());
        test.waitForElementVisibility(loginButton, element_wait_max);
        test.validateElementsToHaveExpectedText(loginButton, "Login");
        test.waitForElementIsClickable(loginButton, element_wait_min);
        test.clickingElement(loginButton);
        test.waitForElementVisibility(welcomeText, element_wait_min);
        test.validateElementsToHaveExpectedText(welcomeText, "Welcome Harry Potter !!");
        test.assertUrl(configReader.customerLoginPageUrl());
        test.selectDropDownByVisibleText(accNumberDropdown, "1004");
        test.validateElementsToHaveExpectedText(accNumberViewer, "1004");
        test.waitForElementVisibility(depositButton, element_wait_min);
        test.clickingElement(depositButton);
        test.waitForElementVisibility(depositInputField, element_wait_min);
        test.enteringText(depositInputField, depositAmount);
        test.waitForElementVisibility(depositConfirmationButton, element_wait_min);
        test.clickingElement(depositConfirmationButton);
        test.waitForElementVisibility(depositSuccessMessage, element_wait_min);
        test.validateElementsToHaveExpectedText(depositSuccessMessage, "Deposit Successful");
        this.userSuccessfulLogout();
        test.clickingElement(homeButton);
        test.wait(test_wait_normal);
    }

    public void userDepositValidation () {
        this.userSuccessfulLogin();
        test.assertingValueToBeBiggerThanExpectedValue(balanceViewer, depositAmount);
    }
}
