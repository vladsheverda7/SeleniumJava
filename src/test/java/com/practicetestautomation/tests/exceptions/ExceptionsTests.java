package com.practicetestautomation.tests.exceptions;

import com.practicetestautomation.pageobjects.ExceptionPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod(alwaysRun = true)
    public void TearDown() {
        driver.quit();
        logger.info("Browser is closed");
    }

    @Test
    public void NoSuchElementExceptionTest() {
        ExceptionPage exceptionPage = new ExceptionPage(driver);
        exceptionPage.visit();
        exceptionPage.clickAddButton();
        
        Assert.assertTrue(exceptionPage.isRow2InputFieldDisplayed(), "Row 2 is not displayed");
    }

    @Test
    public void timeoutExceptionTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));

        WebElement addButton = driver.findElement(By.xpath("//button[@id='add_btn']"));
        addButton.click();

        WebElement row2InputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));
        Assert.assertTrue(row2InputField.isDisplayed());
    }

    @Test
    public void ElementNotInteractableException() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));

        WebElement addButton = driver.findElement(By.xpath("//button[@id='add_btn']"));
        addButton.click();

        WebElement row2InputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));
        row2InputField.sendKeys("Sushi");

        WebElement saveButton = driver.findElement(By.xpath("//div[@id='row2']/button[@id='save_btn']"));
        saveButton.click();

        String expectedConfirmationMessage = "Row 2 was saved";

        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmation")));
        String actualConfirmationMessage = confirmationMessage.getText();

        Assert.assertEquals(actualConfirmationMessage, expectedConfirmationMessage);
    }

    @Test
    public void InvalidElementStateException() {

        String expectedValue = "Sushi";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));

        WebElement editButton = driver.findElement(By.xpath("//div[@id='row1']/button[@id='edit_btn']"));
        editButton.click();

        WebElement inputField = driver.findElement(By.xpath("//div[@id='row1']/input"));
        inputField.clear();
        inputField.sendKeys(expectedValue);

        WebElement saveButton = driver.findElement(By.xpath("//div[@id='row1']/button[@id='save_btn']"));
        saveButton.click();

        String expectedConfirmationMessage = "Row 1 was saved";
        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmation")));
        String actualConfirmationMessage = confirmationMessage.getText();

        Assert.assertEquals(actualConfirmationMessage, expectedConfirmationMessage);

        String actualValue = inputField.getAttribute("value");
        Assert.assertEquals(actualValue, expectedValue);
    }

    @Test
    public void StaleElementReferenceException() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));

        WebElement addButton = driver.findElement(By.xpath("//div[@id='row1']/button[@id='add_btn']"));
        addButton.click();

        Boolean isInvisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//p[@id='instructions']")));

        Assert.assertTrue(isInvisible);
    }
}
