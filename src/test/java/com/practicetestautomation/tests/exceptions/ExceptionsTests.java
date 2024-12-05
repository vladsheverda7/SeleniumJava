package com.practicetestautomation.tests.exceptions;

import com.practicetestautomation.pageobjects.ExceptionPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.*;

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

        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod(alwaysRun = true)
    public void TearDown() {
        driver.quit();
        logger.info("Browser is closed");
    }

    @Test
    public void noSuchElementExceptionTest() {
        ExceptionPage exceptionPage = new ExceptionPage(driver);
        exceptionPage.visit();
        exceptionPage.clickAddButton();

        Assert.assertTrue(exceptionPage.isRow2InputFieldDisplayed(), "Row 2 is not displayed");
    }

    @Test
    public void timeoutExceptionTest() {
        ExceptionPage exceptionPage = new ExceptionPage(driver);
        exceptionPage.visit();
        exceptionPage.clickAddButton();

        Assert.assertTrue(exceptionPage.isRow2InputFieldDisplayed(), "Row 2 is not displayed");
    }

    @Test
    public void elementNotInteractableException() {
        ExceptionPage exceptionPage = new ExceptionPage(driver);
        exceptionPage.visit();
        exceptionPage.clickAddButton();
        exceptionPage.isRow2InputFieldDisplayed();
        exceptionPage.enterRow2InputFieldValue("Sushi");
        exceptionPage.clickSave2ButtonLocator();

        Assert.assertEquals(exceptionPage.getConfirmationMessage(), "Row 2 was saved");
    }

    @Test
    public void invalidElementStateException() {
        String newValue = "Sushi";

        ExceptionPage exceptionPage = new ExceptionPage(driver);
        exceptionPage.visit();
        exceptionPage.clearRow1InputField();
        exceptionPage.enterRow1InputFieldValue(newValue);
        exceptionPage.clickSave1ButtonLocator();

        Assert.assertEquals(exceptionPage.getConfirmationMessage(), "Row 1 was saved");

        Assert.assertEquals(exceptionPage.getRow1FieldValue(), newValue);
    }

    @Test
    public void staleElementReferenceException() {
        ExceptionPage exceptionPage = new ExceptionPage(driver);
        exceptionPage.visit();
        exceptionPage.clickAddButton();

        Assert.assertTrue(exceptionPage.isInstructionsHidden());
    }
}
