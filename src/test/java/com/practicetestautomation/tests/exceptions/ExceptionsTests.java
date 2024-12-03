package com.practicetestautomation.tests.exceptions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionsTests {

    private WebDriver driver;
    private Logger logger;

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void SetUp(@Optional("chrome") String browser) {
        logger = Logger.getLogger(ExceptionsTests.class.getName());
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

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
    }

    @AfterMethod(alwaysRun = true)
    public void TearDown() {
        driver.quit();
        logger.info("Browser is closed");
    }

    @Test
    public void NoSuchElementExceptionTest() {

        WebElement addButton = driver.findElement(By.xpath("//button[@id='add_btn']"));
        addButton.click();

        WebElement row2InputField = driver.findElement(By.xpath("//div[@id='row2']/input"));
        Assert.assertTrue(row2InputField.isDisplayed(), "Row 2 is not displayed");
    }
}
