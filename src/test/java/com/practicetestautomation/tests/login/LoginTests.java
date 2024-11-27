package com.practicetestautomation.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

public class LoginTests {

    private WebDriver driver;
    private Logger logger;

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void SetUp(@Optional("chrome") String browser) {
        logger = Logger.getLogger(LoginTests.class.getName());
        logger.setLevel(Level.INFO);
        logger.info("Running test in + " + browser);
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            default:
                logger.warning("Configuration for " + browser + " is missing, so running tests in Chrome by default");
                driver = new ChromeDriver();
                break;
        }
        driver.get("https://practicetestautomation.com/practice-test-login/");
    }

    @AfterMethod(alwaysRun = true)
    public void TearDown() {
        driver.quit();
        logger.info("Browser is closed");
    }

    @Test(groups = {"positive", "regression", "smoke"})
    public void TestLoginFunctionality() {
        logger.info("Starting test login functionality");
        WebElement usernameInputField = driver.findElement(By.xpath("//input[@id='username']"));
        WebElement passwordInputField = driver.findElement(By.xpath("//input[@id='password']"));
        WebElement submitButton = driver.findElement(By.xpath("//button[@id='submit']"));

        logger.info("Type username");
        usernameInputField.sendKeys("student");
        logger.info("Type password");
        passwordInputField.sendKeys("Password123");
        logger.info("Click submit");
        submitButton.click();

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("Verify login functionality");
        String expectedUrl = "https://practicetestautomation.com/logged-in-successfully/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

        String expectedMessage = "Congratulations student. You successfully logged in!";
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains(expectedMessage));

        WebElement logOutButton = driver.findElement(By.linkText("Log out"));
        Assert.assertTrue(logOutButton.isDisplayed());
    }

    @Parameters({"username", "password", "expectedErrorMessage"})
    @Test(groups = {"negative", "regression"})
    public void NegativeLoginTest(String username, String password, String expectedErrorMessage) {
        logger.info("Starting negative login test functionality");
        WebElement usernameInputField = driver.findElement(By.xpath("//input[@id='username']"));
        logger.info("Type username:" + username);
        usernameInputField.sendKeys(username);

        WebElement passwordInputField = driver.findElement(By.xpath("//input[@id='password']"));
        logger.info("Type password:" + password);
        passwordInputField.sendKeys(password);

        WebElement submitButton = driver.findElement(By.xpath("//button[@id='submit']"));
        logger.info("Click submit button");
        submitButton.click();

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        logger.info("Verify error message: " + expectedErrorMessage);
        WebElement errorMessage = driver.findElement(By.id("error"));
        Assert.assertTrue(errorMessage.isDisplayed());

        String actualIncorrectUserNameMessage = errorMessage.getText();

        Assert.assertEquals(actualIncorrectUserNameMessage, expectedErrorMessage);
    }
}
